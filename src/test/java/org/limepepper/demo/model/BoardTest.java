package org.limepepper.demo.model;

import org.junit.Test;
import org.limepepper.demo.Config;
import org.limepepper.demo.helper.BoardHelper;
import org.limepepper.demo.helper.IOHelper;
import org.limepepper.demo.helper.StringHelper;
import org.limepepper.demo.helper.StringHelperTest;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void simple1() {
        Board board = new Board();
        assertEquals(board.getTile(0, 0), Tile.VOID);
        assertEquals(board.getTile(1, 1), Tile.NONE);
        assertEquals(board.getTile(8, 8), Tile.NONE);
        assertEquals(board.getTile(9, 9), Tile.VOID);
        assertEquals(board.getTile(5, 4), Tile.DARK);
        assertEquals(board.getToPlay(), Tile.DARK);
        System.out.println(BoardHelper.toAnsiString(board));
        System.out.println(StringHelper.formatBits(board.getLightBits()));
        System.out.println(StringHelper.formatBits(board.getDarkBits()));
    }

    @Test
    public void toJson() {
        Board board = new Board();
        String json = board.toJson();
        System.out.println(json);

    }

    @Test
    public void toJson0() {
        System.out.println(Tile.LIGHT);
        System.out.println(Tile.LIGHT.toString());
        System.out.println(Tile.DARK);
        System.out.println(Tile.DARK.toString());

        Tile[][] tiles = new Tile[][]{
                new Tile[]{Tile.LIGHT, Tile.DARK, Tile.NONE, Tile.VOID}
        };


        System.out.println(IOHelper.tilesToString(tiles));
    }

    @Test
    public void fromJson1() {
        Board board = new Board();
        board.setTile(2, 1, Tile.DARK);
        //System.out.print(BoardHelper.toAnsiString(board));
        String json = board.toJson();
        //System.out.println(json);
        Board newBoard = Board.fromJson(json);
        //System.out.println(newBoard.toJson());
        System.out.print(IOHelper.printArrayOfStrings(IOHelper.print2boards(board, newBoard)));
        assertEquals(board.toJson(), newBoard.toJson());

    }


    @Test
    public void toPrettyJson() {
        Board board = new Board();
        String json = BoardHelper.toPrettyJson(board);
        System.out.println(json);
        // JSONObject jo = new JSONObject(json);
        Board newBoard = Board.fromJson(json);

        assertEquals(board.toJson(), newBoard.toJson());


    }

    /**
     * Test of isComplete method, of class Board in initial state
     */
    @Test
    public void checkComplete1() {
        Board board = new Board();
        System.out.print(BoardHelper.toAnsiString(board));
        assertFalse(board.isComplete());
    }

    /**
     * Test of isComplete method, of class Board in empty state
     */
    @Test
    public void checkComplete2() {
        Board board = new Board(Config.emtpyBoard);
        System.out.print(BoardHelper.toAnsiString(board));
        assertTrue(board.isComplete());
    }

    /**
     * black can play, light cannot. Play would pass to black
     */
    @Test
    public void checkComplete3() {
        Board board = new Board(Config.emtpyBoard);
        board.setTile(1, 1, Tile.DARK);
        board.setTile(2, 1, Tile.LIGHT);
        System.out.print(BoardHelper.toAnsiString(board));
        assertFalse(board.isComplete());
    }

    /**
     * black can play, light cannot. Play would pass to black
     */
    @Test
    public void checkComplete4() {
        String nboardString = """
                    * * * * O O O O
                    * - - - - - - O
                    - - - - - - - -
                    - - - - - - - -
                    - - - - - - - -
                    - - - - - - - -
                    - - - - - - - -
                    - - - - - - - -
                    *
                """;
        Board board = IOHelper.fromNBoard(nboardString);
        System.out.print(BoardHelper.toAnsiString(board));
        assertTrue(board.isComplete());
        assertFalse(board.currentCanPlay());
        assertFalse(board.oppositeCanPlay());
    }

    @Test
    public void toAnsiString() {
        Board board = new Board();
        System.out.println(BoardHelper.toAnsiString(board));
    }

    @Test
    public void testCTileBits1() {
        String nboardString = """
                    - O - - - - O -
                    O - - - - - - O
                    - - - - - - - -
                    - - - - - - - -
                    - - - - - - - -
                    - - - - - - - -
                    O - - - - - - O
                    - O - - - - O -
                    *
                """;
        Board board = IOHelper.fromNBoard(nboardString);
        System.out.print(BoardHelper.toAnsiString(board));
        assertTrue(board.isComplete());
        assertFalse(board.currentCanPlay());
        assertFalse(board.oppositeCanPlay());
        System.out.println(board.getLightBits());
    }

    @Test
    public void testXTileBits1() {
        String nboardString = """
                    - - - - - - - -
                    - O - - - - O -
                    - - - - - - - -
                    - - - - - - - -
                    - - - - - - - -
                    - - - - - - - -
                    - O - - - - O -
                    - - - - - - - -
                    *
                """;
        Board board = IOHelper.fromNBoard(nboardString);
        System.out.print(BoardHelper.toAnsiString(board));
        assertTrue(board.isComplete());
        assertFalse(board.currentCanPlay());
        assertFalse(board.oppositeCanPlay());
        System.out.println(board.getLightBits());
        System.out.println(StringHelper.formatBits(18577348462920192L));
    }

    @Test
    public void testATileBits1() {
        String nboardString = """
                    - - O - - O - -
                    - - - - - - - -
                    O - - - - - - O
                    - - - - - - - -
                    - - - - - - - -
                    O - - - - - - O
                    - - - - - - - -
                    - - O - - O - -
                    *
                """;
        Board board = IOHelper.fromNBoard(nboardString);
        System.out.print(BoardHelper.toAnsiString(board));
        assertTrue(board.isComplete());
        assertFalse(board.currentCanPlay());
        assertFalse(board.oppositeCanPlay());
        System.out.println(board.getLightBits());
        System.out.println(StringHelper.formatBits(2594215222373842980L));
    }

    @Test
    public void testBTileBits1() {
        String nboardString = """
                    - - - O O - - -
                    - - - - - - - -
                    - - - - - - - -
                    O - - - - - - O
                    O - - - - - - O
                    - - - - - - - -
                    - - - - - - - -
                    - - - O O - - -
                    *
                """;
        Board board = IOHelper.fromNBoard(nboardString);
        System.out.print(BoardHelper.toAnsiString(board));
        assertTrue(board.isComplete());
        assertFalse(board.currentCanPlay());
        assertFalse(board.oppositeCanPlay());
        System.out.println(board.getLightBits());
        System.out.println(StringHelper.formatBits(1729382813125312536L));
    }
}
