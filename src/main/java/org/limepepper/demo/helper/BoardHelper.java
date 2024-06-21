package org.limepepper.demo.helper;

import org.limepepper.demo.Config;
import org.limepepper.demo.model.*;

import java.util.List;
import java.util.ArrayList;

public class BoardHelper {

    public static List<Move> generateMoves(Board board) {
        List<Move> moves = new ArrayList<>();

        for (int y = 0; y < Config.HEIGHT; y++) {
            for (int x = 0; x < Config.WIDTH; x++) {
                if (isValidMove(board, x, y))
                    moves.add(new Move(x, y));

            }
        }
        return moves;
    }

    public static boolean isValidMove(Board board, int x, int y) {
        if (board.getTile(x,y) != Tile.NONE) {
            return false;
        }
        // check if move has opposite tile adjacent
        boolean isAdjacent = false;
        outerLoop:
        for (int j = y - 1; j <= y + 1; j++) {
            for (int i = x - 1; i <= x + 1; i++) {
                //System.out.println("considering " + " x:" + x + " y:" + y + " i:" + i + " j:" + j +  " board.tiles[j][i]:" + board.tiles[j][i] + " board.toPlay.opposite():" + board.toPlay.opposite());
                if (i == x && j == y) {
                    //System.out.println("ignoring target square");
                    continue;
                }
                if (board.getTile(i,j) == board.getToPlay().opposite()) {
                    isAdjacent = true;
                    break outerLoop;
                }
            }
        }
        if (!isAdjacent) {
            //System.out.println("no adjacent tiles of opposite color");
            return false;
        }
        for (Direction direction : Direction.values()) {
            int dx = direction.getDx();
            int dy = direction.getDy();
            boolean foundOpposite = false;
            boolean foundCurrent = false;

            int count = 1;
            while (true) {
                int i = x + (dx * count);
                int j = y + (dy * count);


                // check if out of bounds
                if (i < 0 || i > Config.WIDTH || j < 0 || j > Config.HEIGHT) {
                    break;
                } else if (board.getTile(i,j) == Tile.NONE) {
                    break;
                } else if (board.getTile(i,j) == board.getToPlay()) {
                    foundCurrent = true;
                    // previously found opposite, and this is end tile
                    if (foundOpposite) {
                        return true;
                    }
                    // otherwise this was a dead end
                    break;
                }

                if (board.getTile(i,j) == board.getToPlay().opposite()) {
                    foundOpposite = true;
                }

                count++;
            }
            //System.out.println(direction + ": (" + direction.getDx() + ", " + direction.getDy() + ")");
        }

        //System.out.println("in isValidMove : false");
        return false;
    }

    // serialization helper functions

    public static String toAnsiString(Board board) {

        //return super.toString();
        String output = "toPlay = " + board.getToPlay() + "\n";

        for (int y = 0; y < Config.HEIGHT + 2; y++) {
            for (int x = 0; x < Config.WIDTH + 2; x++) {
                switch (board.getTile(x,y)) {
                    case NONE:
                        output += Config.ANSI_GREEN_BACKGROUND + "   " + Config.ANSI_RESET;
                        break;
                    case DARK:
                        output += Config.ANSI_BLUE_BACKGROUND + " X " + Config.ANSI_RESET;
                        break;
                    case LIGHT:
                        output += " O ";
                        break;
                    case VOID:
                        output += Config.ANSI_CYAN_BACKGROUND + " # " + Config.ANSI_RESET;
                        break;
                }
                //output += '"' + tiles[y][x].toString() + '"' + " ";
            }
            output += "\n";
        }
        return output;
    }


    /**
     * Returns a pretty printed JSON representation of the board.
     * For comprehensibility custom formatting is used. one line per row
     *
     * @return
     */
    public static String toPrettyJson(Board board) {
        String output = "{\n";
        output += "  \"tiles\": [\n";
        for (int y = 0; y < Config.HEIGHT + 2; y++) {
            output += "    [";
            for (int x = 0; x < Config.WIDTH + 2; x++) {
                output += "\"" + board.getTile(x,y) + "\"";
                if (x < Config.WIDTH + 1) {
                    output += ", ";
                }
            }
            output += "]";
            if (y < Config.HEIGHT + 1) {
                output += ",";
            }
            output += "\n";
        }
        output += "  ],\n";
        output += "  \"toPlay\": \"" + board.getToPlay().name() + "\",\n";
        output += "}";
        return output;
    }

}
