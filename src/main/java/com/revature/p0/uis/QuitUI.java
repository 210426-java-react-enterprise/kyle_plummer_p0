package com.revature.p0.uis;

import java.io.BufferedReader;

public class QuitUI extends UserInterface{

    public QuitUI(BufferedReader consoleReader) {
        super("/quit", consoleReader);
    }

    @Override
    public void render() {
        System.out.println("Goodbye...");
        app.quitApp();
    }
}
