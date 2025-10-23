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
        
        if (!facade.login("username1", "password1")) { 
            System.out.println("Login failed."); 
            return; 
        } 
    } 
    
    public void CreateAccount() { 
        System.out.println(); 
        if (facade.signup()) {
            System.out.println("Signup failed."); 
            return; 
        } 
        
        System.out.println("Login successful for username2"); 
    } 

    public void EnterRoom() {
        return;
    }

    public void ThreePuzzles() {
        return;
    }
    
    public void DataPersistence() {
        return;
    }

    public void GameCompletion() {
        return;
    }
}