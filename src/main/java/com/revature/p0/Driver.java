package com.revature.p0;

import com.revature.p0.utils.App;

import static com.revature.p0.utils.App.getApp;

/**
 * Program entry point. Holds the main loop which keeps application running as long as app says it should.
 */
public class Driver {
    private static App app;

    public static void main(String[] args) {

        app = getApp();
        app.navigate("/welcome");
        while(app.isRunning()) {
            app.goToDestination();
        }
    }
}
