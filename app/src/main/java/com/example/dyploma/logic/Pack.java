package com.example.dyploma.logic;

import java.util.ArrayList;
import java.util.List;

public class Pack {
    String packName;
    ArrayList<Round> roundList;


    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public ArrayList<Round> getRoundList() {
        return roundList;
    }

    public void setRoundList(ArrayList<Round> roundList) {
        this.roundList = roundList;
    }


    public Pack() {
    }

    public Pack(String packName, ArrayList<Round> roundList) {
        this.packName = packName;
        this.roundList = roundList;
    }
}
