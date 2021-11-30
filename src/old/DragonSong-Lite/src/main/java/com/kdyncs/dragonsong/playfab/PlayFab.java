package com.kdyncs.dragonsong.playfab;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import com.playfab.*;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

@Service
public class PlayFab {

    // Logging
    private static final Logger log = LogManager.getLogger();

    private PlayFabAuthenticationModels.GetEntityTokenResponse titleToken;

    @Autowired
    public PlayFab(PlayFabConfiguration config) {
        PlayFabSettings.DeveloperSecretKey = config.getKey();
        PlayFabSettings.TitleId=config.getTitle();
    }

    @PostConstruct
    public void init() {
        log.trace("Initializing PlayFab Service");
        refreshToken();
    }

    public ArrayList<PlayFabServerModels.CharacterResult> listCharacters(String playFabId) throws PlayFabException {
        var request = new PlayFabServerModels.ListUsersCharactersRequest();
        request.PlayFabId = playFabId;

        var result = PlayFabServerAPI.GetAllUsersCharacters(request);

        // Handle Error Condition
        if (result.Error != null) {
            log.warn("{}", result.Error.errorMessage);
            throw new PlayFabException();
        }

        return result.Result.Characters;
    }

    public ArrayList<PlayFabServerModels.FriendInfo> listFriends(String playFabId) throws PlayFabException {
        var request = new PlayFabServerModels.GetFriendsListRequest();
        request.PlayFabId = playFabId;

        var result = PlayFabServerAPI.GetFriendsList(request);

        // Handle Error Condition
        if (result.Error != null) {
            log.warn("{}", result.Error.errorMessage);
            throw new PlayFabException();
        }

        return result.Result.Friends;
    }

    public PlayFabGroupsModels.ListMembershipResponse listMemberships(String playFabId) throws PlayFabException {

        var request = new PlayFabGroupsModels.ListMembershipRequest();
        var entity = new PlayFabGroupsModels.EntityKey();

        entity.Id = playFabId;
        entity.Type = "title_player_account";

        request.Entity = entity;

        var result = PlayFabGroupsAPI.ListMembership(request);

        // Handle Error Condition
        if (result.Error != null) {
            log.warn("{}", result.Error.errorMessage);
            throw new PlayFabException();
        }

        return result.Result;
        
    }

    public Date getTokenExpiration() {

        // Sanity Check
        if (titleToken == null) {
            refreshToken();
        }

        return titleToken.TokenExpiration;
    }

    public Boolean isTokenExpired() {

        // Sanity Check
        if (titleToken == null) {
            refreshToken();
        }

        // Return true if after expiration
        return new Date().after(titleToken.TokenExpiration);
    }

    public PlayFabServerModels.CharacterResult findCharacter(ArrayList<PlayFabServerModels.CharacterResult> characters, String characterId ) {
        for (var character : characters) {
            if (character.CharacterId.equals(characterId)) {
                return character;
            }
        }

        return null;
    }

    // Autorefresh Token
    @Scheduled(fixedRate = 30000)
    private void refresh() {
        log.trace("Checking Token");

        var now = Instant.now();
        var exp = this.getTokenExpiration().toInstant();
        var duration = Duration.between(now, exp);

        // Refresh Token every 23 Hours
        if (duration.minusHours(1L).isNegative()) {
            log.trace("Refreshing Token");
            this.refreshToken();
        }
    }

    private void refreshToken() {
        this.titleToken = getTitleToken();
    }

    private PlayFabAuthenticationModels.GetEntityTokenResponse getTitleToken() {

        var request = new PlayFabAuthenticationModels.GetEntityTokenRequest();
        var result = PlayFabAuthenticationAPI.GetEntityToken(request);

        log.info("{}", result.Result.Entity.Id);
        log.info("{}", result.Result.Entity.Type);
        log.info("{}", result.Result.EntityToken);
        log.info("{}", result.Result.TokenExpiration);

        if (result.Error != null) {
            log.error("Issue Getting Title Entity Toke: {}", result.Error.errorMessage);
            throw new RuntimeException("Shits fucked yo");
        }

        return result.Result;
    }
}
