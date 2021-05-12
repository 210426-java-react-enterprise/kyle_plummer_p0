package com.revature.p0.utils;

import com.revature.p0.pojos.AccountPOJO;
import com.revature.p0.pojos.UserPOJO;
import com.revature.p0.uis.*;
import com.revature.p0.utils.datastructures.LinkedList;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * App class - this class maintains objects for the inner workings of service classes,
 * navigates between user interface screens, controls if app keeps running in main loop.
 */
public class App {
    private static App thisApp;
    private boolean appRunning;
    private BufferedReader consoleReader;
    private LinkedList<UserInterface> userInterfaces;
    private static UserPOJO currentUser;
    private static AccountPOJO currentAccount;
    private static UserInterface destination;


    /**
     * Private constructor to create singleton app object. Stores reference to self,
     * reference to console reader, and list of user interface objects accessible in app.
     */
    private App() {
        thisApp = this;
        this.appRunning = true;
        this.consoleReader = new BufferedReader(new InputStreamReader(System.in));
        this.userInterfaces = new LinkedList<UserInterface>();

        //automate this list via some sort of file search on uis package path?
        userInterfaces.add(new WelcomeUI(consoleReader));
        userInterfaces.add(new LoginUI(consoleReader));
        userInterfaces.add(new RegisterUI(consoleReader));
        userInterfaces.add(new UserHomeUI(consoleReader));
        userInterfaces.add(new AccountHomeUI(consoleReader));
        userInterfaces.add(new NewAccountUI(consoleReader));
        userInterfaces.add(new DepositUI(consoleReader));
        userInterfaces.add(new WithdrawalUI(consoleReader));
        userInterfaces.add(new TransferUI(consoleReader));
        userInterfaces.add(new TransactionHistoryUI(consoleReader));
        userInterfaces.add(new AccountAccessUI(consoleReader));
        userInterfaces.add(new QuitUI(consoleReader));


    }

    /**
     * Factory method that returns reference to this singleton, invokes constructor if necessary.
     * @return reference to this singleton
     */
    public static App getApp() {
        if (thisApp == null) {
            thisApp = new App();
        }

        return thisApp;
    }

    /**
     * Sets the currentScreen reference to a different userinterface object, setting it up for
     * the next render() call to display that screen as soon as the current one returns.
     * @param route String path to associate with userinterface object
     */
    public void navigate(String route) {
        for (UserInterface ui : userInterfaces) {
            if(ui.getRoute().equals(route)) {
                destination = ui;
            }
        }
    }

    /**
     * Invokes the render() method of the currentScreen reference.
     */
    public void goToDestination() {
        //System.out.println("DEBUG: Rendering: " + destination.getRoute());
        destination.render();
    }

    public void setCurrentUser(UserPOJO user) {
        currentUser = user;
    }

    public UserPOJO getCurrentUser() {
        return currentUser;
    }

    public void setCurrentAccount(AccountPOJO currentAccount) {
        App.currentAccount = currentAccount;
    }

    public AccountPOJO getCurrentAccount() {
        return currentAccount;
    }

    public boolean isRunning() {
        return appRunning;
    }

    public void quitApp() {
        appRunning = false;
    }

}
