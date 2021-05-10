package com.revature.p0.uis;

import com.revature.p0.utils.App;

import java.io.BufferedReader;

public abstract class UserInterface {
    protected BufferedReader consoleReader;
    protected String route;
    protected App app;

    public UserInterface(String route, BufferedReader consoleReader) {
        this.route = route;
        this.consoleReader = consoleReader;
        this.app = App.getApp();
    }

    public String getRoute() {
        return route;
    }

    public abstract void render();
}
