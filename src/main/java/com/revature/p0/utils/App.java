package com.revature.p0.utils;

import com.revature.p0.uis.*;
import com.revature.p0.utils.datastructures.LinkedList;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    private static App thisApp;
    private boolean appRunning;
    private BufferedReader consoleReader;
    private LinkedList<UserInterface> userInterfaces;


    //instead of invoking navigate and render over and over as the user traverses the application
    //and never letting a render method complete and return, I want to try setting the next destination,
    //letting render() complete and return to the app loop, and then invoke the next desination. I will need
    //to pass objects sometimes, so I'll use traversalPOJOHolder to hold a single POJO at a time for UI traversals.
    ////////////////////////////////////////////////////////
    private static UserInterface destination;
    private static Object traversalPOJOHolder;
    /////////////////////////////////////////////////////////


    private App() {
        thisApp = this;
        this.appRunning = true;
        this.consoleReader = new BufferedReader(new InputStreamReader(System.in));
        this.userInterfaces = new LinkedList<UserInterface>();

        userInterfaces.add(new WelcomeUI(consoleReader));
        userInterfaces.add(new LoginUI(consoleReader));
        userInterfaces.add(new RegisterUI(consoleReader));
        userInterfaces.add(new QuitUI(consoleReader));


    }

    public static App getApp() {
        if (thisApp == null) {
            thisApp = new App();
        }

        return thisApp;
    }


//    public void addUserInterface(UserInterface ui) {
//        userInterfaces.add(ui);
//    }

    public void oldNavigate(String route) {
        for (UserInterface ui : userInterfaces) {
            if(ui.getRoute().equals(route)) {
                ui.render();
            }
        }
    }

    public void navigate(String route) {
        for (UserInterface ui : userInterfaces) {
            if(ui.getRoute().equals(route)) {
                destination = ui;
            }
        }
    }

    public void goToDestination() {
        destination.render();
    }

    public void storeObject(Object o) {
        traversalPOJOHolder = o;
    }

    public Object retrieveObject() {
        Object o = traversalPOJOHolder;
        traversalPOJOHolder = null;
        return o;
    }

    public boolean isRunning() {
        return appRunning;
    }

    public void quitApp() {
        appRunning = false;
    }

}
