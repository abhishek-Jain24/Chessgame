/* 1)Create Board  (8 ✖️ 8) 
2)setPiece()
3)getPiece()
4)move()
5) print() --> print the board */






public class Board {
    private Piece[][] grid = new Piece[8][8];

    public Piece getPiece(int r, int c) {
        if (r < 0 || r > 7 || c < 0 || c > 7) return null;
        return grid[r][c];
    }

    public void setPiece(int r, int c, Piece p) {
        grid[r][c] = p;
        if (p != null) p.setPosition(r, c);
    }

    public void move(int fromR, int fromC, int toR, int toC) {
        grid[toR][toC] = grid[fromR][fromC];
        grid[fromR][fromC] = null;
        if (grid[toR][toC] != null)
            grid[toR][toC].setPosition(toR, toC);
    }

    // find King
    public int[] findKing(String color) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c];
                if (p instanceof King && p.getColor().equals(color)) {
                    return new int[]{r, c};
                }
            }
        }
        return null;
    }

    public void print() {
        System.out.println("  a b c d e f g h");
        for (int r = 0; r < 8; r++) {
            System.out.print((8 - r) + " ");
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c];
                if (p == null) System.out.print(". ");
                else {
                    char ch = p.getClass().getSimpleName().charAt(0);
                    System.out.print((p.getColor().equals("white")
                        ? Character.toUpperCase(ch)
                        : Character.toLowerCase(ch)) + " ");
                }
            }
            System.out.println();
        }
    }
}