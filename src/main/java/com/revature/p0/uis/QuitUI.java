package com.revature.p0.uis;

import java.io.BufferedReader;

/**
 * User interface for exiting application. Calls app quit method to terminate main loop
 *
 * @author Kyle Plummer
 */
public class QuitUI extends UserInterface{

    public QuitUI(BufferedReader consoleReader) {
        super("/quit", consoleReader);
    }

    @Override
    public void render() {
        System.out.println("Quitting... Goodbye.");
        app.quitApp();
    }
}
