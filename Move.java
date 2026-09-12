public class Move {
    int fromR, fromC, toR, toC;
    Piece movedPiece;
    Piece capturedPiece;


    public Move(int fr, int fc, int tr, int tc, Piece moved, Piece captured) {
        this.fromR = fr; this.fromC = fc;
        this.toR = tr; this.toC = tc;
        this.movedPiece = moved;
        this.capturedPiece = captured;
    }
}
