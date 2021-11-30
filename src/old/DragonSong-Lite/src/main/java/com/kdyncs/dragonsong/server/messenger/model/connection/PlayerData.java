package com.kdyncs.dragonsong.server.messenger.model.connection;

public class PlayerData {

    private final String playerId;
    private final String characterId;
    private final String characterName;

    public PlayerData(String playerId, String characterId, String characterName) {
        this.playerId = playerId;
        this.characterId = characterId;
        this.characterName = characterName;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getCharacterId() {
        return characterId;
    }

    public String getCharacterName() {
        return characterName;
    }
}
