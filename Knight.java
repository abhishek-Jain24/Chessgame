import java.util.*;

public class Knight extends Piece {
    public Knight(String color , int row, int col){
        super(color, row, col);
    }

    @Override
    public List<int[]> getValidMoves(Board board){
        List<int[]> moves = new ArrayList<>();

        int[][] jumps = {
            {-2,-1}, {-2,1},
            {-1,-2}, {-1,2},
            {1,-2}, {1,2},
            {2,-1}, {2,1}
        };

        for(int[] j : jumps){
            int r = row +j[0];
            int c = col + j[1];

            if(isValid(r,c) && !isFriendly(board, r, c)){
                moves.add(new int[]{r,c});
            }
        }

        return moves;


    }
    
}
