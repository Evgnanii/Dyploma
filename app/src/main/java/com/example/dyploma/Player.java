package com.example.dyploma;

public class Player {
    String playerId;
    String playerName;
    String imageUrl;

    public Player() {
    }

    public Player(String playerId, String playerName, String imageUrl) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.imageUrl = imageUrl;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
