import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ChessGame game = new ChessGame("A1", "A2");
        Scanner sc = new Scanner(System.in);

        System.out.println("♟️ Chess Game Started!");
        System.out.println("Format: fromRow fromCol toRow toCol");
        System.out.println("Commands: undo | reset | exit\n");

        while (true) {
            game.printBoard();
            System.out.print("\n" + game.getCurrentTurn() + "'s turn: ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Game ended. Bye!");
                break;
            }

            if (input.equalsIgnoreCase("undo")) {
                game.undo();
                continue;
            }

            if (input.equalsIgnoreCase("reset")) {
                game = new ChessGame("A1", "A2");
                System.out.println("Game reset!");
                continue;
            }

            try {
                String[] parts = input.split(" ");
                int fr = Integer.parseInt(parts[0]);
                int fc = Integer.parseInt(parts[1]);
                int tr = Integer.parseInt(parts[2]);
                int tc = Integer.parseInt(parts[3]);
                game.makeMove(fr, fc, tr, tc);
            } catch (Exception e) {
                System.out.println("Invalid input! Use: fr fc tr tc");
            }
        }

        sc.close();
    }
}