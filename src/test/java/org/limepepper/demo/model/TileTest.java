package org.limepepper.demo.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TileTest {

    @Test
    public void opposite() {
        assertEquals(Tile.DARK.opposite(), Tile.LIGHT);
        assertEquals(Tile.LIGHT.opposite(), Tile.DARK);
        assertThrows(IllegalStateException.class, Tile.NONE::opposite);
        assertThrows(IllegalStateException.class, Tile.VOID::opposite);
    }

    @Test
    public void fromChar() {
        assertEquals(Tile.fromChar('X'), Tile.DARK);
        assertEquals(Tile.fromChar('O'), Tile.LIGHT);
        assertEquals(Tile.fromChar('.'), Tile.NONE);
        assertEquals(Tile.fromChar(' '), Tile.VOID);
    }

    @Test
    public void testToString() {
        System.out.println(Tile.DARK.toString());
        System.out.println(Tile.DARK);
        System.out.println(Tile.DARK.getShortName());
        System.out.println(Tile.DARK.name());
    }

    @Test
    public void getShortName() {
    }

    @Test
    public void valueOf() {
    }
}
