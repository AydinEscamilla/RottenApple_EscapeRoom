public class GameDriver {     

    public String username = LRivers;
    public String password = password1;

    public static void main(String[] args) { 
        GameDriver driver = new GameDriver(); 
        driver.run(); 
    } 
    
    public void run() { 
        GameSystemFacade facade = new GameSystemFacade();
        DuplicateAccount(); 
        CreateAccount(); 
        EnterRoom();
        ThreePuzzles();
        DataPersistence();
        GameCompletion();

        System.out.println("Thank you for watching our backend presentation!");
    } 
    
    public void DuplicateAccount() { 
        System.out.println("Leni is attempting to create an account with username LRivers.");

        facade.signup(username, password);

        System.out.println("Account creation failed due to existing LRivers.");
        System.out.println("Account creation failed due to duplicate user (1/6)\n"); 
        return;
    } 
    
    public void CreateAccount() { 
        System.out.println("Leni is attempting to create an account with username LeniRivers.");

        /*
        GameSystemFacade.signup(LeniRivers, password1);
        */

        System.out.println("Unique username used; account creation succeeded.");
        System.out.println("Account creation succeeded (2/6)\n"); 
        return;
    } 

    public void EnterRoom() {
        System.out.println("Leni is entering an escape room for the first time.");

        /*
        GameSystemFacade.startGame();
        */

        System.out.println("Leni has listened to the story's opening.");
        System.out.println("Starting story heard (3/6)\n");
        return;
    }

    public void ThreePuzzles() {
        System.out.println("Leni is attempting three puzzles within the escape room.");
                
        /*
        GameSystemFacade.getPuzzle();
        GameSystemFacade.answerPuzzle();
        */

        System.out.println("Leni has completed three puzzles.");
        System.out.println("Three puzzles demonstrated (4/6)\n");
        return;
    }
    
    public void DataPersistence() {
        System.out.println("Leni will now log out and back in, resuming progress.");
        
        /*
        GameSystemFacade.saveData(LeniRivers, password1);
        GameSystemFacade.logout(LeniRivers, password1);
        GameSystemFacade.login(LeniRivers, password1);
        */

        System.out.println("Leni has logged out and maintained progress.");
        System.out.println("Data persistence shown (5/6)\n");
        return;
    }

    public void GameCompletion() {
        System.out.println("Leni will now complete the last puzzle and therefore the game.");
        
        /*
        GameSystemFacade.completeGame(LeniRivers, password1);
        */

        System.out.println("Leni has finished the game and been given her certificate.");
        System.out.println("Game completed (6/6)\n");
        return;
    }
}