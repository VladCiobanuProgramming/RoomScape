import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random(); // Integrating 'random numbers' function
        int HP = 75; // Player Initial Health Points
        int RoomsTravelled=0; // How many rooms player travelled
        int coinCurrency = 0; // Player starting coins amount
        System.out.println("Welcome to RoomScape!"); // Greet player
        System.out.println("You start with a HP of " + HP + "\n");
        Scanner scanner = new Scanner(System.in); // Integrating scanner type as user input

        while (HP > 0) { // While player has more than 0 HP (Player is alive)
            // Room Layout
            String RoomOutput = """
                      |--------Front-------|
                      |                    |
                      |                    |
                    left                  right
                      |                    |
                      |____________________|""";
            System.out.println(RoomOutput);
            System.out.println("\n Pick the direction you desire going to.");
            int RandEvent = rand.nextInt(3); // Number indicator for random events that may occur
            String UserDirectionPick;

            // Input direction loop
            while (true) {
                UserDirectionPick = scanner.nextLine().toLowerCase(); // Get user input and convert to lowercase

                // Check for valid input
                if (UserDirectionPick.equals("right") || UserDirectionPick.equals("left") || UserDirectionPick.equals("front")) {
                    System.out.printf("You went %s.\n", UserDirectionPick);
                    RoomsTravelled++; // Player travelled Room successfully
                    break; // Exit the loop if the input is valid
                } else {
                    System.out.printf("That is not a valid way to go. Please choose 'right', 'left', or 'front'.\n"); // Invalid input
                }
            }
            System.out.printf("\n ----------------\n"); // Split text between actions to make output more readable

            if (RandEvent == 0) {
                System.out.printf("You got in the room without difficulties.\n");  // Player got in the room without difficulties
            }

            int coinsAmount = rand.nextInt(100) + 1; // Generate coin amount
            if (RandEvent == 1) {
                System.out.printf("You found valuable loot! Gained +%d coins!\n", coinsAmount); // Player found loot worth 1-100 coins
                coinCurrency += coinsAmount; // Add coins to total
                System.out.printf("You now have %d coins.\n", coinCurrency);
            }

            if (RandEvent == 2) { // Player encountered enemy
                System.out.printf("You encountered a Monster! Fight or Flight?\n");
                int MonsterHP = 20; // Monster's HP
                String userDecision;

                // Flight or fight decision loop
                while (MonsterHP > 0 && HP > 0) { // while both the player and monster are alive
                    int FlightChance = rand.nextInt(4); // Generate flight chance each time
                    userDecision = scanner.nextLine().toLowerCase(); // user input to lowercase

                    if (userDecision.equals("flight") && FlightChance == 0) { // 25% chance for player to flee each time flight is picked
                        System.out.printf("You ran away successfully.\n");
                        break; // Exit the loop if fleeing is successful
                    } else if (userDecision.equals("flight")) { // unsuccessful flight attempt
                        int TakenAttackDamage = rand.nextInt(20) + 1; // Damage monster will deal
                        System.out.printf("Fleeing unsuccessful. Monster hit you for -%d HP!\n", TakenAttackDamage);
                        HP -= TakenAttackDamage;
                        System.out.printf("You now have %d HP remaining.\n", HP);
                    } else if (userDecision.equals("fight")) { // Player decides to fight back
                        int DamageDealt = rand.nextInt(20) + 1; // Damage player can deal
                        System.out.printf("You fought bravely and dealt %d damage to the monster!\n", DamageDealt);
                        MonsterHP -= DamageDealt;

                        // Monster counterattack
                        int TakenAttackDamage = rand.nextInt(20) + 1; // Amount of damage monster will cause
                        System.out.printf("The monster hit you for -%d HP!\n", TakenAttackDamage);
                        System.out.printf("Monster now has %d HP left!\n", MonsterHP); // Displays monster HP after both player and monster attack
                        HP -= TakenAttackDamage;
                        System.out.printf("You now have %d HP remaining.\n", HP); // Displays Player HP after both player and monster attack

                    } else {
                        System.out.printf("Invalid decision. Please choose 'Fight' or 'Flight'.\n"); // Input !=fight or flight
                    }
                }
                if (HP <= 0) {  // Player died , Player end stats
                    System.out.printf("\n ----------------\n");
                    System.out.println("You have been defeated by the monster. Game Over!");
                    System.out.printf("In your adventure you have gathered "+coinCurrency+" coins.\n");
                    System.out.printf("In your adventure you have travelled "+RoomsTravelled+" rooms.");
                }

                if (MonsterHP <= 0) {
                    System.out.printf("\n ---------------- Congrats !\n");
                    System.out.println("You have defeated the monster!");
                }
            }
        }

        scanner.close(); // Close the scanner at the end
    }
}
