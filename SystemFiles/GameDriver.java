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
    
    public void loginscenario() { 
        System.out.println(); 
        
        if (!facade.login("username1", "password1")) { 
            System.out.println("Login failed."); 
            return; 
        } 
        
        System.out.println("Login successful for username1");
    } 
    
    public void signupscenario() { 
        System.out.println(); 
        if (facade.signup()) {
            System.out.println("Signup failed."); 
            return; 
        } 
        
        System.out.println("Login successful for username2"); 
    } 
}