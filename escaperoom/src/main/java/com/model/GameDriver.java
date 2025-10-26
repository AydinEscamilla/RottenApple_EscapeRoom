package com.model;

import java.util.Scanner;

public class GameDriver {     
    
    public GameSystemFacade facade;
    public User user;
    public Room room;

    public GameDriver() {
        this.facade = new GameSystemFacade();
    }

    public static void main(String[] args) { 
        GameDriver driver = new GameDriver(); 
        driver.run(); 
    } 
    
    public void run() { 
        DuplicateAccount(); 
        
        CreateAccount(); 

        EnterRoom();

        //ThreePuzzles();

        //DataPersistence();
        
        //GameCompletion();

        System.out.println("Thank you for watching our backend presentation!");
    } 
    
    public void DuplicateAccount() { 
        String username = "LRivers";
        String password = "password1";
        
        System.out.println("Leni is attempting to create an account with username LRivers.");

        User result = facade.signup(username, password);

        System.out.println(result + "; account creation failed due to existing LRivers.");
        System.out.println("Account creation failed due to duplicate user (1/6)\n");   
    } 
    
    public void CreateAccount() { 
        String username = "LeniRivers";
        String password = "password4";

        System.out.println("Leni is attempting to create an account with username LeniRivers.");

        User newUser = facade.signup(username, password);

        System.out.println("Unique username used; account creation succeeded: " + newUser);
        System.out.println("Account creation succeeded (2/6)\n"); 
    } 

    public void EnterRoom() {
        System.out.println("Leni is entering an escape room for the first time.");

        Room roomChoice = facade.getRoom();

        System.out.println(roomChoice);

        facade.startNewGame(roomChoice);

        System.out.println("Leni has listened to the story's opening.");
        System.out.println("Starting story heard (3/6)\n");
    }

    public void ThreePuzzles() {
        System.out.println("Leni is attempting three puzzles within the escape room.");
                
        /*
        facade.getPuzzle();
        facade.answerPuzzle();
        */

        System.out.println("Leni has completed three puzzles.");
        System.out.println("Three puzzles demonstrated (4/6)\n");
        return;
    }
    
    public void DataPersistence() {
        System.out.println("Leni will now log out and back in, resuming progress.");
        
        /*
        facade.saveData(LeniRivers, password4);
        facade.logout(LeniRivers, password4);
        facade.login(LeniRivers, password4);
        facade.showProgress(LeniRivers, password4);
        */

        System.out.println("Leni has logged out and maintained progress.");
        System.out.println("Data persistence shown (5/6)\n");
        return;
    }

    public void GameCompletion() {
        System.out.println("Leni will now complete the last puzzle and therefore the game.");
        
        /*
        facade.openGame();
        facade.answerPuzzle();
        facade.completeGame(LeniRivers, password4);
        facade.showLeaderboard();
        facade.printCertificate();
        */

        System.out.println("Leni has finished the game and been given her certificate.");
        System.out.println("Game completed (6/6)\n");
        return;
    }

    public void AwaitEnter() {
        try {
            System.in.read();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}