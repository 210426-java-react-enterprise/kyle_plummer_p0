package com.revature.p0.uis;

import java.io.BufferedReader;

public class QuitUI extends UserInterface{

    public QuitUI(BufferedReader consoleReader) {
        super("/quit", consoleReader);
        //this.consoleReader = consoleReader;
    }

    @Override
    public void render() {
        System.out.println("END OF APPLICATION REACHED! QUITTING...");
        app.quitApp();
    }
}
