package com.revature.p0;

import com.revature.p0.utils.App;

import static com.revature.p0.utils.App.getApp;


public class Driver {
    private static App app;

    public static void main(String[] args) {

        app = getApp();
        app.navigate("/welcome");
        while(app.isRunning()) {
            //System.out.println("DEBUG: apprunning loop");
            app.goToDestination();
        }



    }
}
