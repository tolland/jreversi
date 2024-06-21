package org.limepepper.demo.helper;

import org.limepepper.demo.Config;
import org.limepepper.demo.model.Board;
import org.limepepper.demo.model.Tile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class IOHelper {
    private static final Logger logger = LoggerFactory.getLogger(IOHelper.class);

    /**
     * convenience method to format a Tile[][] as a string
     * this is not going to be parsable, but visually useful
     *
     * @param tiles
     * @return
     */
    public static String tilesToString(Tile[][] tiles) {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                sb.append(tiles[y][x].toString());
            }
            sb.append("\n");
        }

        return sb.toString();
    }
    /**
     * convenience method to format a Tile[][] as json
     *
     * @param tiles
     * @return json string
     */
    public static String tilesToJson(Tile[][] tiles) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int y = 0; y < tiles.length; y++) {
            sb.append("[");
            for (int x = 0; x < tiles[y].length; x++) {
                sb.append("\"");
                sb.append(tiles[y][x]);
                sb.append("\"");
                if(x < tiles[y].length - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
            if(y < tiles.length - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * read an nboard string formatted like so
     * <p><blockquote><pre>
     *     - O - - - - O -
     *     O - - - - - - O
     *     - - - - - - - -
     *     - - - O * - - -
     *     - - - * O - - -
     *     - - - - - - - -
     *     O - - - - - - O
     *     - O - - - - O -
     *     *
     * </pre></blockquote></p>
     * into a new Board object
     *
     * @param nboard
     * @return
     */
    public static Board fromNBoard(String nboard) {
        String allowedChars = "*XO-.";
        String result = removeAllExcept(nboard, allowedChars);
        logger.info(result);
        int len = result.length()-1;
        checkPerfectSquare(len);
        System.out.println(len);
        int size = (int) Math.sqrt(len);
        Tile[][] tiles = Config.emtpyBoard;
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char c = result.charAt(i*size+j);
                tiles[i+1][j+1] = Tile.fromChar(c);
                count++;
            }
        }
        char c = result.charAt(len);
        return new Board(tiles).setToPlay(Tile.fromChar(c));
    }

    /**
     * parse a nboard string into a bitfield for each light, dark and both
     *
     *
     * @param nboard
     * @return long[] {lightBitfield, darkBitfield, bitfield}
     */
    public  static final long[] nBoardToBitfield(String nboard) {
        String allowedChars = "*XxBbWwO-._";
        String result = removeAllExcept(nboard, allowedChars);
        logger.trace(result);
        //@TODO this strips the toPLay char, but this should be optional
        int len = result.length()-1;
        checkPerfectSquare(len);
        int size = (int) Math.sqrt(len);
        long lightBitfield = 0;
        long darkBitfield = 0;
        long bitfield = 0;
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Tile c = Tile.fromChar(result.charAt(i*size+j));
                if (c == Tile.LIGHT   ) {
                    lightBitfield = BitHelper.setBit(lightBitfield, count);
                    bitfield = BitHelper.setBit(bitfield, count);
                } else if (c == Tile.DARK) {
                    darkBitfield = BitHelper.setBit(darkBitfield, count);
                    bitfield = BitHelper.setBit(bitfield, count);
                }
                count++;
            }
        }
        return new long[]{lightBitfield, darkBitfield, bitfield};
    }

    /**
     * strip all characters from input except those in allowedChars
     *
     * useful for comments and spacing in nboard strings
     *
     * @param input
     * @param allowedChars
     * @return
     */
    public static String removeAllExcept(String input, String allowedChars) {
        // Escape special regex characters in allowedChars
        String escapedAllowedChars = allowedChars.replaceAll("([\\\\+*?\\[^\\]$(){}=!<>|:-])", "\\\\$1");
        String regex = "[^" + escapedAllowedChars + "]";
        return input.replaceAll(regex, "");
    }

    public static void checkPerfectSquare(int number) throws IllegalStateException {
        if (number < 0) {
            throw new IllegalStateException("Negative numbers cannot be perfect squares.");
        }

        int sqrt = (int) Math.sqrt(number);
        if (sqrt * sqrt != number) {
            throw new IllegalStateException(number + " is not a perfect square.");
        }

        //System.out.println(number + " is a perfect square.");
    }

    // @TODO boards need to have the same dimensions
    public static List<String> print2boards(Board board1, Board board2) {
        List<String> result = new ArrayList<>();
        String[] board1Lines = BoardHelper.toAnsiString(board1).split("\n");
        String[] board2Lines = BoardHelper.toAnsiString(board2).split("\n");
        for (int i = 0; i < board1Lines.length; i++) {
            result.add(board1Lines[i] + "    " + board2Lines[i]);
        }
        return result;
    }

    /**
     * print a list of strings
     * @param strings
     * @return
     */
    public static String printArrayOfStrings(List<String> strings) {
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    //@TODO read from eothello format
    // F5D6C3D3C4F4F6G5E6C5F3F7E3E7B6B5C6B4D7B3G6C2A5A6A4H5A7E2F2C8E1G4D2C7E8G3H3H4H6D8F8D1F1A3A2G8C1B2A1B7G2H1H2G1A8B8H8H7G7B1
// e.g. https://www.eothello.com/game/2372390


}
