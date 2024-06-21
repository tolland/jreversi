package org.limepepper.demo;

import org.limepepper.demo.helper.IOHelper;
import org.limepepper.demo.model.Tile;

import java.util.Map;

public class Config {
    // standard board sizes
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    // pad each side with 1 extra row and column
    public static final int WIDTH_PADDED = WIDTH + 2;
    public static final int HEIGHT_PADDED = HEIGHT + 2;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static final Map<Integer, Character> chars = Map.of(
        1, 'A',
        2, 'B',
        3, 'C',
        4, 'D',
        5, 'E',
        6, 'F',
        7, 'G',
        8, 'H'
    );
    
    public static final Tile[][] emtpyBoard = new Tile[][]{
            new Tile[]{Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID},
            new Tile[]{Tile.VOID, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.VOID},
            new Tile[]{Tile.VOID, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.VOID},
            new Tile[]{Tile.VOID, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.VOID},

            new Tile[]{Tile.VOID, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.VOID},

            new Tile[]{Tile.VOID, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.VOID},

            new Tile[]{Tile.VOID, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.VOID},

            new Tile[]{Tile.VOID, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.VOID},

            new Tile[]{Tile.VOID, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.VOID},

            new Tile[]{Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID}
    };

    public static final long CORNER_TILE_BITS = -9151314442816847743L;
    public static final long EDGE_BITS = -1085102592571150095L;

    public static final String nboardCSquares = """
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
    public static long cSquares = IOHelper.nBoardToBitfield(nboardCSquares)[0];

    public static final String nboardXSquares = """
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
    public static long xSquares = IOHelper.nBoardToBitfield(nboardXSquares)[2];

    public static final String nboardASquares = """
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
    public static long aSquares = IOHelper.nBoardToBitfield(nboardASquares)[2];

    public static final String nboardBSquares = """
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
    public static long bSquares = IOHelper.nBoardToBitfield(nboardBSquares)[2];

    public static final String nboardCornerSquares = """
                    O - - - - - - O
                    - - - - - - - -
                    - - - - - - - -
                    - - - - - - - -
                    - - - - - - - -
                    - - - - - - - -
                    - - - - - - - -
                    O - - - - - - O
                    *
                """;
    public static long cornerSquares = IOHelper.nBoardToBitfield(nboardCornerSquares)[0];

    public static long otherSquares = ~(cornerSquares | aSquares | bSquares | xSquares | cSquares);

    public static int SCORE_CORNER = 60;
    public static int SCORE_A = 3;
    public static int SCORE_B = 5;
    public static int SCORE_C = -10;
    public static int SCORE_X = -10;
    public static int SCORE_OTHER = 1;


   // public static final long CQUARE_BITS =

}
