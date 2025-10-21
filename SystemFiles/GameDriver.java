import java.util.Scanner;
import java.util.List;

public class GameDriver {
    private static Scanner scanner;
    private static GameSystemFacade facade;
    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    startNewGame();
                    break;
                case 4:
                    resumeGame();
                    break;
                case 5:
                    viewLeaderboard();
                    break;
                case 6:
                    changeSettings();
                    break;
                case 7:
                    quitGame();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n=== Escape Room Game ===");
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("3. Start New Game");
        System.out.println("4. Resume Game");
        System.out.println("5. View Leaderboard");
        System.out.println("6. Change Settings");
        System.out.println("7. Quit");
        System.out.print("Choose an option: ");
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }


    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = facade.login(username, password);
        if (user != null) {
            currentUser = user;
            System.out.println("Login successful. Welcome, " + user.getUsername() + "!");
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }

    private static void signUp() {
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        User newUser = facade.signUp(username, password);
        if (newUser != null) {
            currentUser = newUser;
            System.out.println("Account created successfully for " + newUser.getUsername() + ".");
        } else {
            System.out.println("Sign up failed. An error has occurred or username taken.");
        }
    }

    private static void startNewGame() {
        System.out.println("Starting a new game...");
        // facade.startNewGame(currentUser);
    }

    private static void resumeGame() {
        System.out.println("Resuming saved game...");
        // facade.resumeGame(currentUser);
    }

    private static void viewLeaderboard() {
        System.out.println("Displaying leaderboard...");
        // Leaderboard lb = facade.getLeaderboard();
    }

    private static void changeSettings() {
        System.out.println("Opening settings...");
        // facade.changeSetting(currentUser);
    }

    private static void quitGame() {
        System.out.println("Quitting game... Progress saved.");
        // facade.saveGame(currentUser);
        // facade.quitGame();
    }
}
