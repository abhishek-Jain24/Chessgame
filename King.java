import java.util.*;

public class King extends Piece {

    public King(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public List<int[]> getValidMoves(Board board) {
        List<int[]> moves = new ArrayList<>();

        int[][] dirs = {
            {-1,-1}, {-1,0}, {-1,1},
            {0,-1},           {0,1},
            {1,-1},  {1,0},  {1,1}
        };

        for (int[] d : dirs) {
            int r = row + d[0];
            int c = col + d[1];

            if (isValid(r, c) && !isFriendly(board, r, c)) {
                moves.add(new int[]{r, c});
            }
        }

        return moves;
    }
}