import java.util.*;

public class Pawn extends Piece {

    public Pawn(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public List<int[]> getValidMoves(Board board) {
        List<int[]> moves = new ArrayList<>();

        int dir = color.equals("white") ? -1 : 1;
        int startRow = color.equals("white") ? 6 : 1;

        // 1.One step forward
        if (isValid(row + dir, col) && board.getPiece(row + dir, col) == null) {
            moves.add(new int[]{row + dir, col});

            // 2.Two step forward
            if (row == startRow && board.getPiece(row + 2 * dir, col) == null) {
                moves.add(new int[]{row + 2 * dir, col});
            }
        }

        // 3. Diagonal capture
        for (int dc : new int[]{-1, 1}) {
            int r = row + dir;
            int c = col + dc;
            if (isValid(r, c) && board.getPiece(r, c) != null
                && !isFriendly(board, r, c)) {
                moves.add(new int[]{r, c});
            }
        }

        return moves;
    }


}
