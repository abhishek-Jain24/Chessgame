import java.util.*;

public class Bishop extends Piece {

    public Bishop(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public List<int[]> getValidMoves(Board board) {
        List<int[]> moves = new ArrayList<>();

        int[][] dirs = {
            {-1,-1}, {-1,1}, {1,-1}, {1,1}   //Bishop diagonal
        };

        for (int[] d : dirs) {
            int r = row + d[0];
            int c = col + d[1];

            while (isValid(r, c)) {
                if (isFriendly(board, r, c)) break;
                moves.add(new int[]{r, c});
                if (board.getPiece(r, c) != null) break;
                r += d[0];
                c += d[1];
            }
        }

        return moves;
    }
}