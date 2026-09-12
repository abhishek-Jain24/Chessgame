/*FlowChart Piece.java :-   Create new Piece(King/Queen/...)
                                      ⬇️
                            Set Color and position from constructor
                                      ⬇️
                            Call getValidMoves() {Every piece have it's own logic}  
                                      ⬇️
                            check isValid() {Piece exist in board or not}   
                                      ⬇️
                            Check isFriendly() {Is this my piece ? }
                                      ⬇️
                            Return list with own logic of piece
*/

import java.util.List;

public abstract class Piece {
    protected String color;
    protected int row , col;

    public Piece(String color , int row , int col){
        this.color = color;
        this.row = row;
        this.col = col;
    }

    public abstract List<int[]> getValidMoves(Board board);

    public String getColor() {
        return color;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
    }

    protected boolean isValid(int r, int c){     //Check position in chess board
        return r>=0 && r<8 && c>=0 && c<8;
    }

    protected  boolean isFriendly(Board board, int r , int c){    //Check it , Any piece of same color on next position
        Piece p = board.getPiece(r,c);
        return p != null && p.getColor().equals(this.color);

    }


}