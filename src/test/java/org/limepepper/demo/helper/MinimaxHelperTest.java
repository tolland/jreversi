package org.limepepper.demo.helper;

import org.junit.Test;
import org.limepepper.demo.Config;
import org.limepepper.demo.model.Board;
import org.limepepper.demo.model.Tile;

import static org.junit.Assert.*;

public class MinimaxHelperTest {


    @Test
    public void testMinimax() {
       // fail("Not yet implemented");
        assertTrue(true);


    }

    @Test
    public void testEvaluate() {
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
        Board newBoard = IOHelper.fromNBoard(nboard);
        System.out.println(BoardHelper.toAnsiString(newBoard));
        System.out.println(MinimaxHelper.evaluate(newBoard));
        assertEquals(newBoard.getToPlay(), Tile.LIGHT);

        long[] bitfields = IOHelper.nBoardToBitfield(nboard);
        long lightBits = bitfields[0];
        long darkBits = bitfields[1];
        long allBits = bitfields[2];
//        System.out.println(StringHelper.formatBits(bitfields[0]));
//        System.out.println(StringHelper.formatBits(bitfields[1]));
//        System.out.println(StringHelper.formatBits(bitfields[2]));

        System.out.println("lightBits:\n" + StringHelper.formatBits(lightBits));
        System.out.println("corner bits\n" + StringHelper.formatBits(Config.cornerSquares));

        int countLightCorners = Long.bitCount(lightBits & Config.cornerSquares);
        System.out.println("white has " + countLightCorners + " corners");

        int countDarkCorners = Long.bitCount(darkBits & Config.cornerSquares);
        System.out.println("black has " + countDarkCorners + " corners");


        int countLightASquares = Long.bitCount(lightBits & Config.aSquares);
        System.out.println("white has " + countLightASquares + " squares");

        int countDarkASquares = Long.bitCount(darkBits & Config.aSquares);
        System.out.println("black has " + countDarkASquares + " squares");


        System.out.println("lightBits:\n" + StringHelper.formatBits(Config.otherSquares));
    }


    @Test
    public void testEvaluate1() {
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
        Board board = IOHelper.fromNBoard(nboard);
        System.out.println(BoardHelper.toAnsiString(board));
        System.out.println("overall score "+ MinimaxHelper.evaluateTileValues(board));
    }
}
