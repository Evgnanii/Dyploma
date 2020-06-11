package com.example.dyploma;

public class Lobby {
    boolean isFastStarOn;
    String creatorName;
    String player1;
    String player2;
    String player3;
    String presenter;
    int timeToAnswer;
    int timeToFinal;
    int timeToClick;
    boolean isMinus;
    boolean isOral;
    String lobbyName;
    String nameOfPackage;
    String password;
    String count;

    public Lobby(String lobbyName, String creatorName, String nameOfPackage, String password) {
        this.lobbyName = lobbyName;
        this.password = password;
        this.count = "0";
        this.player1 = "empty";
        this.player2 = "empty";
        this.player3 = "empty";
        this.presenter = "empty";
        this.creatorName = creatorName;
        this.timeToAnswer = Settings.timeToAnswer;
        this.timeToClick = Settings.timeToClick;
        this.timeToFinal = Settings.timeToFinal;
        this.isOral = Settings.isOral;
        this.isMinus = Settings.isMinus;
        this.isFastStarOn = Settings.isFastStarOn;
        this.nameOfPackage = nameOfPackage;
    }

    public void setFastStarOn(boolean fastStarOn) {
        isFastStarOn = fastStarOn;
    }

    public String getNameOfPackage() {
        return nameOfPackage;
    }

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

    public void setNameOfPackage(String nameOfPackage) {
        this.nameOfPackage = nameOfPackage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getPlayer3() {
        return player3;
    }

    public void setPlayer3(String player3) {
        this.player3 = player3;
    }

    public String getPresenter() {
        return presenter;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }
}
