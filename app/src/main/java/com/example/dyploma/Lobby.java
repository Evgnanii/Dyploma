package com.example.dyploma;

public class Lobby {
    boolean isFastStarOn;
    String creatorName;
    Player player1;
    Player player2;
    Player player3;
    Player presenter;
    int timeToAnswer;
    int timeToFinal;
    int timeToClick;
    boolean isMinus;
    boolean isOral;
    String lobbyName;


    public Lobby() {
    }

    public boolean isFastStarOn() {
        return isFastStarOn;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getPlayer3() {
        return player3;
    }

    public void setPlayer3(Player player3) {
        this.player3 = player3;
    }

    public Player getPresenter() {
        return presenter;
    }

    public void setPresenter(Player presenter) {
        this.presenter = presenter;
    }

    public int getTimeToAnswer() {
        return timeToAnswer;
    }

    public void setTimeToAnswer(int timeToAnswer) {
        this.timeToAnswer = timeToAnswer;
    }

    public int getTimeToFinal() {
        return timeToFinal;
    }

    public void setTimeToFinal(int timeToFinal) {
        this.timeToFinal = timeToFinal;
    }

    public int getTimeToClick() {
        return timeToClick;
    }

    public void setTimeToClick(int timeToClick) {
        this.timeToClick = timeToClick;
    }

    public boolean isMinus() {
        return isMinus;
    }

    public void setMinus(boolean minus) {
        isMinus = minus;
    }

    public boolean isOral() {
        return isOral;
    }

    public void setOral(boolean oral) {
        isOral = oral;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public void setLobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
    }

    public Lobby(String lobbyName, String creatorName) {
        this.lobbyName = lobbyName;
        this.creatorName = creatorName;
        this.timeToAnswer = Settings.timeToAnswer;
        this.timeToClick = Settings.timeToClick;
        this.timeToFinal = Settings.timeToFinal;
        this.isOral = Settings.isOral;
        this.isMinus = Settings.isMinus;
        this.isFastStarOn = Settings.isFastStarOn;
    }
}
