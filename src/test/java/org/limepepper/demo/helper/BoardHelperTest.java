package org.limepepper.demo.helper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.limepepper.demo.Config;
import org.limepepper.demo.model.Board;
import org.limepepper.demo.model.Move;
import org.limepepper.demo.model.Tile;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class BoardHelperTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void isValidMove() {
        // new board, black to play
        Board board = new Board();
        System.out.println(board.toJson());
        System.out.println(BoardHelper.toAnsiString(board));

        assertTrue(BoardHelper.isValidMove(board, 4, 3));
    }

    @Test
    public void loadFromJson() {
        Board board = new Board();
        String json = board.toJson();
        Board newBoard = Board.fromJson(json);
        assertEquals(board.toJson(), newBoard.toJson());
    }


    @Test
    public void generateMoves1() {
        Board newBoard = new Board();
        assertEquals(newBoard.getToPlay(), Tile.DARK);
        List<Move> moves = BoardHelper.generateMoves(newBoard);
        assertEquals(moves.size(), 4);

    }

    @Test
    public void generateMoves2() {
        Board newBoard = new Board();
        newBoard.setToPlay(Tile.LIGHT);
        assertEquals(newBoard.getToPlay(), Tile.LIGHT);
        List<Move> moves = BoardHelper.generateMoves(newBoard);
        assertEquals(moves.size(), 4);

    }

    @Test
    public void generateMoves3() {

        Board newBoard = new Board();
        newBoard.setToPlay(Tile.DARK);
        for (int i = 1; i <= Config.WIDTH; i++) {
            for (int j = 1; j <= Config.HEIGHT; j++) {
                newBoard.setTile(i, j, Tile.LIGHT);
            }
        }
        newBoard.setTile(8, 4, Tile.NONE);
        newBoard.setTile(8, 5, Tile.NONE);
        newBoard.setTile(7, 5, Tile.NONE);
        newBoard.setTile(7, 6, Tile.NONE);
        newBoard.setTile(8, 4, Tile.NONE);
        newBoard.setTile(8, 7, Tile.NONE);
        newBoard.setTile(8, 6, Tile.DARK);
        System.out.println(BoardHelper.toAnsiString(newBoard));
        //newBoard.setToPlay(Tile.DARK);
        List<Move> movesDark = BoardHelper.generateMoves(newBoard);
        assertEquals(movesDark.size(), 0);
        newBoard.setToPlay(Tile.LIGHT);
        List<Move> movesLight = BoardHelper.generateMoves(newBoard);
        assertEquals(movesLight.size(), 0);

//        System.out.println("valid: " + BoardHelper.isValidMove(newBoard, 8, 8));
//        BoardHelper.makeMove(newBoard, 8, 8);
//        System.out.println(newBoard.toAnsiString());
//        newBoard.setToPlay(Tile.LIGHT);
//        System.out.println(newBoard.toJson());
//        System.out.println(newBoard.toAnsiString());
//        System.out.println("valid: " + BoardHelper.isValidMove(newBoard, 7, 8));
//        BoardHelper.makeMove(newBoard, 7, 8);
//        System.out.println(newBoard.toAnsiString());
    }
}
