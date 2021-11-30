package com.kdyncs.dragonsong.playfab;

import com.playfab.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PlayFabTest {

    // Logging
    private static final Logger log = LogManager.getLogger();

    @Autowired
    PlayFab playfab;

    private static String MASTER_PLAYER_ACCOUNT_ID = "B25ACEC763A7D090";
    private static String TITLE_PLAYER_ACCOUNT_ID = "20D36B8B17D140E1";

    @PostConstruct
    public void test() {
        testGetCharacters();
        testGetFriends();
        testGetGuild();
        testGetProfiles();
    }

    private void testGetCharacters() {

        log.info("Test Get Characters");

        // Form Request
        var request = new PlayFabServerModels.ListUsersCharactersRequest();
        request.PlayFabId = MASTER_PLAYER_ACCOUNT_ID;

        var result = PlayFabServerAPI.GetAllUsersCharacters(request);

        if (result.Error != null) {
            log.info("{}", result.Error.errorMessage);
        } else {
            for (var character : result.Result.Characters) {
                log.info("{}: {}: {}", character.CharacterId, character.CharacterName, character.CharacterType);
            }
        }
    }

    private void testGetProfiles() {

        log.info("Test Get Profiles");

        var request = new PlayFabProfilesModels.GetEntityProfileRequest();
        var entity = new PlayFabProfilesModels.EntityKey();

        entity.Id = TITLE_PLAYER_ACCOUNT_ID;
        entity.Type = "title_player_account";

        request.Entity = entity;

        var result = PlayFabProfilesAPI.GetProfile(request);
        // Handle Error Condition
        if (result.Error != null) {
            log.info("{}", result.Error.errorMessage);
        } else {

            var profile = result.Result.Profile;
            log.info("{}", profile.DisplayName);
            log.info("{}", profile.Entity.Id);
        }
    }

    private void testGetFriends() {

        log.info("Test Get Friends");

        // Form Request
        var request = new PlayFabServerModels.GetFriendsListRequest();


        request.PlayFabId = MASTER_PLAYER_ACCOUNT_ID;

        var result = PlayFabServerAPI.GetFriendsList(request);

        if (result.Error != null) {
            log.info("{}", result.Error.errorMessage);
        } else {
            for (var friend : result.Result.Friends) {
                log.info("{}: {}: {}", friend.FriendPlayFabId, friend.Username, friend.TitleDisplayName);
            }
        }
    }

    private  void testGetGuild() {

        log.info("Test Get Guild");

        var request = new PlayFabGroupsModels.ListMembershipRequest();
        var entity = new PlayFabGroupsModels.EntityKey();

        entity.Id = TITLE_PLAYER_ACCOUNT_ID;
        entity.Type = "title_player_account";

        request.Entity = entity;

        var result = PlayFabGroupsAPI.ListMembership(request);

        // Handle Error Condition
        if (result.Error != null) {
            log.info("{}", result.Error.errorMessage);
        } else {
            for (var group : result.Result.Groups) {
                log.info("{}", group.Group.Id);
                log.info("{}", group.GroupName);
            }
        }
    }
}
