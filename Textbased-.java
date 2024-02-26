import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextAdventureGame {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        startGame();
    }

    private static void startGame() {
        System.out.println("Welcome to the Text Adventure Game!");
        System.out.println("You find yourself in a mysterious land. Your choices will shape your destiny.");

        // Player creation
        Player player = createPlayer();

        // Begin the adventure
        explore(player);
    }

    private static Player createPlayer() {
        System.out.print("Enter your character's name: ");
        String playerName = scanner.nextLine();
        System.out.println("Welcome, " + playerName + "!");
        return new Player(playerName);
    }

    private static void explore(Player player) {
        System.out.println("You embark on a journey through the unknown. Many challenges lie ahead.");

        // Initial decision point
        Map<String, Runnable> decisions = new HashMap<>();
        decisions.put("left", () -> handleDecisionLeft(player));
        decisions.put("right", () -> handleDecisionRight(player));

        makeDecision("You encounter a fork in the road. Do you want to go left or right?", decisions);
    }

    private static void makeDecision(String prompt, Map<String, Runnable> decisions) {
        System.out.println(prompt);
        System.out.print("Enter your choice: ");

        String userChoice = scanner.nextLine().toLowerCase();

        if (decisions.containsKey(userChoice)) {
            decisions.get(userChoice).run();
        } else {
            handleInvalidInput();
        }
    }

    private static void handleDecisionLeft(Player player) {
        System.out.println("You chose to go left. You discover a hidden cave.");

        Map<String, Runnable> caveDecisions = new HashMap<>();
        caveDecisions.put("open", () -> System.out.println("You found treasure! Congratulations!"));
        caveDecisions.put("leave", () -> System.out.println("You decide to leave the chest untouched."));

        makeDecision("Inside the cave, you find a chest. Open it or leave it?", caveDecisions);
    }

    private static void handleDecisionRight(Player player) {
        System.out.println("You chose to go right. You encounter a friendly traveler.");

        Map<String, Runnable> travelerDecisions = new HashMap<>();
        travelerDecisions.put("accept", () -> System.out.println("You accept the map. It reveals new locations."));
        travelerDecisions.put("decline", () -> System.out.println("You politely decline the map."));

        makeDecision("The traveler offers you a map. Accept it or decline?", travelerDecisions);
    }

    private static void handleInvalidInput() {
        System.out.println("Invalid input. Please enter a valid choice.");
    }
}

class Player {
    private String name;
    private int health;

    public Player(String name) {
        this.name = name;
        this.health = 100; // Initial health
    }

    // Add getter and setter methods as needed

    public void decreaseHealth(int damage) {
        this.health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

}

