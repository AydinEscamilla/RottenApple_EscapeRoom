public class GameDriver { 
    private GameSystemFacade facade; 

    public GameDriver() { 
        facade = new GameSystemFacade(); 
    } 
    
    public static void main(String[] args) { 
        GameDriver driver = new GameDriver(); driver.run(); 
    } 
    
    public void run() { 
        DuplicateAccount(); 
        CreateAccount(); 
        EnterRoom();
        ThreePuzzles();
        DataPersistence();
        GameCompletion();
    } 
    
    public void DuplicateAccount() { 
        System.out.println(); 
        
        // do facade.signup, argument LRivers
        // this is a duplicate account, it should fail 
        /*if (!facade.login("username1", "password1")) { 
            System.out.println("Login failed."); 
            return; 
        }*/
        return;
    } 
    
    public void CreateAccount() { 
        System.out.println(); 
        
        // do facade.signup, argument LeniRivers
        // this is a unique account, it should work
        if (facade.signup()) {
            System.out.println("Signup failed."); 
            return; 
        } 
        
        System.out.println("Login successful for username2"); 
    } 

    public void EnterRoom() {
        // Leni chooses the escape room game
        // She hears the opening story (TTS and text)
        return;
    }

    // Leni completes 3 puzzles
    // In the process, she gets 2 items, uses 1
    // Use 2 different hints
    public void ThreePuzzles() {
        return;
    }
    
    // Log out, log back in with a progress tracker
    public void DataPersistence() {
        return;
    }

    // Finishes the fourth puzzle, and wins
    // Finishes game after logging back in
    // Call leaderboard, show Leni's place on it among 3 others
    // Certificate of Completion formatted text file
    public void GameCompletion() {
        return;
    }
}