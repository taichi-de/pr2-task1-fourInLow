import java.util.Random;
import java.util.Scanner;

public class FourInRow {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the \"Four in Row\" game! "
            + "Enter the name of the first Player..");
        Player player1 = new Player();
        player1.setName(sc.nextLine());
        System.out.println(player1.getName()+", you're the Player 1 (X). "
            + "Now, lets enter the name of the second Player..");
        Player player2 = new Player();
        player2.setName(sc.nextLine());
        System.out.println(player2.getName()+", you're the Player 2 (O). "
            + "Let's begin the Game!\n\nThis is your Game Field:");
        Game game = new Game();
        String[][] currentField = game.getField();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(currentField[i][j] + "\t");
            }
            System.out.println();
        }

        Random rand = new Random();
        game.currentTurn = rand.nextInt(2)+1;

        System.out.println("The first turn is: "+((game.currentTurn == 1) ?
            player1.getName()+" [X]" : player2.getName()+" [O]")+". Enter the Column to insert (from 1 to 7): ");
        int userInput;
        while(sc.hasNext()) {
            try {
                userInput = sc.nextInt();
                if (userInput == 9) break;
                System.out.println(game.makeMove(userInput));
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 7; j++) {
                        System.out.print(currentField[i][j] + "\t");
                    }
                    System.out.println();
                }
                if (game.checkGame(game.currentTurn) != 0) {
                    System.out.print("Wow, we have the Winner! It's the Player "
                        + ((game.winPlayer == 1) ? player1.getName() : player2.getName()) +
                        "\n\nThank you for playing with us!\n");
                    break;
                }
                System.out.print("It's turn of " + ((game.currentTurn == 1) ?
                    player1.getName() + " [X]" : player2.getName() + " [O]") + ": ");
            } catch (Exception e) {
                System.out.print("Input error! Exiting..");
                break;
            }
        }
    }
}
