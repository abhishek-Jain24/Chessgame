# Chessgame in Java

A console-based Chess game built from scratch in pure Java. No libraries used.

## Features

- Full 8x8 chess board
- All 6 pieces with individual move logic
- Turn-based gameplay
- Undo moves
- Reset game
- Check and Checkmate detection

## Concepts Used

OOPs:
- Abstraction (abstract Piece class)
- Inheritance (King, Queen extend Piece)
- Polymorphism (each piece has its own getValidMoves)
- Encapsulation

DSA:
- 2D Array (Board)
- Stack (Undo)
- ArrayList (Moves)

Design Pattern:
- Command Pattern (Move class)

## How to Run

    javac *.java
    java Main
 

## How to Play

Input format: fromRow fromCol toRow toCol

Rows: 0 (top) to 7 (bottom)
Cols: 0 (a) to 7 (h)

Example:

    6 4 4 4    White pawn e2 to e4
    1 4 3 4    Black pawn e7 to e5
    undo       Undo last move
    reset      New game
    exit       Quit

