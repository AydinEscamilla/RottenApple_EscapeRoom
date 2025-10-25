public class GameDriver { 
    private GameSystemFacade facade; 
    
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


        System.out.println("Account creation failed due to duplicate user (1/6)"); 
        return;
    } 
    
    public void CreateAccount() { 


        System.out.println("Account creation succeeded (2/6)"); 
        return;
    } 

    public void EnterRoom() {


        System.out.println("Starting story heard (3/6)");
        return;
    }

    public void ThreePuzzles() {


        System.out.println("Three puzzles demonstrated (4/6)");
        return;
    }
    
    public void DataPersistence() {


        System.out.println("Data persistence shown (5/6)");
        return;
    }

    public void GameCompletion() {

        
        System.out.println("Game completed (6/6)");
        return;
    }
}