package org.limepepper.demo.helper;

import org.junit.Test;
import org.limepepper.demo.Config;
import org.limepepper.demo.model.Board;
import org.limepepper.demo.model.Tile;

import static org.junit.Assert.*;

public class StringHelperTest {

    @Test
    public void formatBits1() {
       // assertEquals("00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n", StringHelper.formatBits(0));
    }

    @Test
    public void formatBits2() {
        assertEquals("1 0 0 0 0 0 0 0\n0 0 0 0 0 0 0 0\n0 0 0 0 0 0 0 0\n0 0 0 0 0 0 0 0\n0 0 0 0 0 0 0 0\n0 0 0 0 0 0 0 0\n0 0 0 0 0 0 0 0\n0 0 0 0 0 0 0 0\n", StringHelper.formatBits(1));
    }

    @Test
    public void formatBits3() {
        //String buff = StringHelper.formatBits(07777777777);
        //System.out.println(buff);
        //assertEquals("00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n", buff);
        Board board = new Board(Config.emtpyBoard);
        board.setTile(1, 1, Tile.LIGHT);
        board.setTile(1, 8, Tile.LIGHT);
        board.setTile(8, 1, Tile.LIGHT);
        board.setTile(8, 8, Tile.LIGHT);

        System.out.print(BoardHelper.toAnsiString(board));
        System.out.println(StringHelper.formatBits(board.getLightBits()));
        System.out.println(board.getLightBits());

    }

    @Test
    public void formatCTileBits1() {
        //String buff = StringHelper.formatBits(07777777777);
        //System.out.println(buff);
        //assertEquals("00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n", buff);


//        System.out.print(BoardHelper.toAnsiString(board));
        System.out.println(StringHelper.formatBits(4792111478498951490L));
//        System.out.println(board.getLightBits());

    }


}
