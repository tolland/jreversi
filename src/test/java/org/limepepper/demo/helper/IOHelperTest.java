package org.limepepper.demo.helper;

import org.json.JSONArray;
import org.junit.Test;
import org.limepepper.demo.model.Board;
import org.limepepper.demo.model.Tile;

import static org.junit.Assert.assertEquals;

public class IOHelperTest {

    @Test
    public void tilesToJson() {
        Board board = new Board();
        String json = IOHelper.tilesToJson(board.getTiles());
        System.out.println(json);

        JSONArray ja = new JSONArray(json);
        System.out.println(ja.toString())  ;
        assertEquals(ja.length(), 10);
        assertEquals(ja.getJSONArray(0).length(), 10);
    }

    @Test
    public void nboardToBoard() {
        String board1 = """
                    * - - - - - - *
                    O - - - - - - O
                    - - - - - - - -
                    - - - * O - - -
                    - - - * O - - -
                    - - - - - - - -
                    - - - - - - - -
                    - - - - - - - -
                    *
                """;
        Board newBoard = IOHelper.fromNBoard(board1);
        System.out.println(newBoard.toJson());
        System.out.println(BoardHelper.toAnsiString(newBoard));
        assertEquals(newBoard.getTile(1, 1), Tile.DARK);
        assertEquals(newBoard.getTile(8, 1), Tile.DARK);
        assertEquals(newBoard.getTile(4, 4), Tile.DARK);
        assertEquals(newBoard.getTile(4, 5), Tile.DARK);
        assertEquals(newBoard.getTile(5, 4), Tile.LIGHT);
        assertEquals(newBoard.getTile(5, 5), Tile.LIGHT);
        assertEquals(newBoard.getToPlay(), Tile.DARK);

    }
    @Test
    public void nboardToBoard2() {
        String board1 = """
                    ********
                    **O**--*
                    ********
                    O**O****
                    *****O**
                    O**-*O**
                    **O*****
                    *****-**
                    O
                """;
        Board newBoard = IOHelper.fromNBoard(board1);
        System.out.println(newBoard.toJson());
        System.out.println(BoardHelper.toAnsiString(newBoard));
//        assertEquals(newBoard.getTile(1, 1), Tile.DARK);
//        assertEquals(newBoard.getTile(8, 1), Tile.DARK);
//        assertEquals(newBoard.getTile(4, 4), Tile.DARK);
//        assertEquals(newBoard.getTile(4, 5), Tile.DARK);
//        assertEquals(newBoard.getTile(5, 4), Tile.LIGHT);
//        assertEquals(newBoard.getTile(5, 5), Tile.LIGHT);
        assertEquals(newBoard.getToPlay(), Tile.LIGHT);

    }

    @Test
    public void nBoardToBitfield1() {

        String nboard = """
                    * * * * * * * *
                    * * O * * - - *
                    * * * * * * * *
                    O * * O * * * *
                    * * * * * O * *
                    O * * - * O * *
                    * * O * * * * *
                    * * * * * - * *
                    O
                """;
        long[] bitfields = IOHelper.nBoardToBitfield(nboard);
        System.out.println(StringHelper.formatBits(bitfields[0]));
        System.out.println(StringHelper.formatBits(bitfields[1]));
        System.out.println(StringHelper.formatBits(bitfields[2]));

    }
}
