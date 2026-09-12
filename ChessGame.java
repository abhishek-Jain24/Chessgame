import java.util.*;

public class ChessGame {
    private Board board;
    private Player whitePlayer, blackPlayer;
    private Stack<Move> history;
    private boolean isWhiteTurn;

    public ChessGame(String p1, String p2) {
        board = new Board();
        whitePlayer = new Player(p1, "white");
        blackPlayer = new Player(p2, "black");
        history = new Stack<>();
        isWhiteTurn = true;
        setupBoard();
    }

    private void setupBoard() {
        // Black pieces (top)
        board.setPiece(0, 0, new Rook("black", 0, 0));
        board.setPiece(0, 1, new Knight("black", 0, 1));
        board.setPiece(0, 2, new Bishop("black", 0, 2));
        board.setPiece(0, 3, new Queen("black", 0, 3));
        board.setPiece(0, 4, new King("black", 0, 4));
        board.setPiece(0, 5, new Bishop("black", 0, 5));
        board.setPiece(0, 6, new Knight("black", 0, 6));
        board.setPiece(0, 7, new Rook("black", 0, 7));
        for (int c = 0; c < 8; c++) board.setPiece(1, c, new Pawn("black", 1, c));

        // White pieces (bottom)
        board.setPiece(7, 0, new Rook("white", 7, 0));
        board.setPiece(7, 1, new Knight("white", 7, 1));
        board.setPiece(7, 2, new Bishop("white", 7, 2));
        board.setPiece(7, 3, new Queen("white", 7, 3));
        board.setPiece(7, 4, new King("white", 7, 4));
        board.setPiece(7, 5, new Bishop("white", 7, 5));
        board.setPiece(7, 6, new Knight("white", 7, 6));
        board.setPiece(7, 7, new Rook("white", 7, 7));
        for (int c = 0; c < 8; c++) board.setPiece(6, c, new Pawn("white", 6, c));
    }

    public boolean makeMove(int fr, int fc, int tr, int tc) {
        Piece p = board.getPiece(fr, fc);

        if (p == null) {
            System.out.println("No piece there!");
            return false;
        }

        String currentColor = isWhiteTurn ? "white" : "black";
        if (!p.getColor().equals(currentColor)) {
            System.out.println("Not your turn!");
            return false;
        }

        boolean valid = false;
        for (int[] m : p.getValidMoves(board)) {
            if (m[0] == tr && m[1] == tc) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            System.out.println("Invalid move!");
            return false;
        }

        Piece captured = board.getPiece(tr, tc);
        history.push(new Move(fr, fc, tr, tc, p, captured));
        board.move(fr, fc, tr, tc);

        // Check/Checkmate
        String opponentColor = isWhiteTurn ? "black" : "white";

        if (isCheckmate(opponentColor)) {
            board.print();
            System.out.println("\n CHECKMATE! " +
                (isWhiteTurn ? whitePlayer.getName() : blackPlayer.getName()) +
                " wins!");
            System.exit(0);
        }

        if (isKingInCheck(opponentColor)) {
            System.out.println(" CHECK!");
        }

        isWhiteTurn = !isWhiteTurn;
        return true;
    }

    // King on check
    public boolean isKingInCheck(String color) {
        int[] kingPos = board.findKing(color);
        if (kingPos == null) return false;

        String enemyColor = color.equals("white") ? "black" : "white";

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board.getPiece(r, c);
                if (p != null && p.getColor().equals(enemyColor)) {
                    for (int[] m : p.getValidMoves(board)) {
                        if (m[0] == kingPos[0] && m[1] == kingPos[1]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Checkmate 
    public boolean isCheckmate(String color) {
        if (!isKingInCheck(color)) return false;

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board.getPiece(r, c);
                if (p != null && p.getColor().equals(color)) {
                    for (int[] m : p.getValidMoves(board)) {
                        Piece captured = board.getPiece(m[0], m[1]);
                        board.move(r, c, m[0], m[1]);

                        boolean stillCheck = isKingInCheck(color);

                        board.setPiece(r, c, p);
                        board.setPiece(m[0], m[1], captured);

                        if (!stillCheck) return false;
                    }
                }
            }
        }
        return true;
    }

    public void undo() {
        if (history.isEmpty()) {
            System.out.println("Nothing to undo!");
            return;
        }
        Move m = history.pop();
        board.setPiece(m.fromR, m.fromC, m.movedPiece);
        board.setPiece(m.toR, m.toC, m.capturedPiece);
        isWhiteTurn = !isWhiteTurn;
        System.out.println("Move undone.");
    }

    public void printBoard() {
        board.print();
    }

    public String getCurrentTurn() {
        return isWhiteTurn ? whitePlayer.getName() : blackPlayer.getName();
    }
}