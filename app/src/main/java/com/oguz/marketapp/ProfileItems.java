package com.oguz.marketapp;

public class ProfileItems {

    private String process;
    private int enter;

    public ProfileItems(String process, int enter) {
        this.process = process;
        this.enter = enter;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public int getEnter() {
        return enter;
    }

    public void setEnter(int enter) {
        this.enter = enter;
    }
}
