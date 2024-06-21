package org.limepepper.demo.helper;

import org.limepepper.demo.Config;
import org.limepepper.demo.model.Board;
import org.limepepper.demo.model.Move;

import java.util.List;

public class MinimaxHelper {

    public static int evaluate(Board board) {

        int current = board.currentCount();
        int opposite = board.oppositeCount();

        long lightBits = board.getLightBits();
        long darkBits = board.getDarkBits();

        int lightCount = StringHelper.countBits(lightBits);
        int darkCount = StringHelper.countBits(darkBits);

//        System.out.println("lightBits: " + lightCount);
//        System.out.println("darkBits: " + darkCount);
//
//        System.out.println(StringHelper.formatBits(lightBits));
//        System.out.println(StringHelper.formatBits(darkBits));

        List<Move> moves = BoardHelper.generateMoves(board);

        double score = 0;

        if (board.getTile(1, 1) == board.getToPlay()) {
            score += 20;
        }

        return 0;
    }

    /**
     * Evaluate the board based on the tile values
     */
    public static int evaluateTileValues(Board board) {
        int lightScore = (Config.SCORE_CORNER * Long.bitCount(board.getLightBits() & Config.cornerSquares)) + (Config.SCORE_A * Long.bitCount(board.getLightBits() & Config.aSquares)) + (Config.SCORE_B * Long.bitCount(board.getLightBits() & Config.bSquares)) + (Config.SCORE_C * Long.bitCount(board.getLightBits() & Config.cSquares)) + (Config.SCORE_X * Long.bitCount(board.getLightBits() & Config.xSquares)) + calcSquareTypeScore(board.getLightBits(), Config.otherSquares, Config.SCORE_OTHER);
        System.out.println("lightScore: " + lightScore);
        int darkScore = (Config.SCORE_CORNER * Long.bitCount(board.getDarkBits() & Config.cornerSquares)) + (Config.SCORE_A * Long.bitCount(board.getDarkBits() & Config.aSquares)) + (Config.SCORE_B * Long.bitCount(board.getDarkBits() & Config.bSquares)) + (Config.SCORE_C * Long.bitCount(board.getDarkBits() & Config.cSquares)) + (Config.SCORE_X * Long.bitCount(board.getDarkBits() & Config.xSquares)) + calcSquareTypeScore(board.getDarkBits(), Config.otherSquares, Config.SCORE_OTHER);
        System.out.println("darkScore: " + darkScore);
        return lightScore - darkScore;
    }

    public static int calcSquareTypeScore(long bits, long squares, int score) {
        return score * Long.bitCount(bits & squares);
    }

    public static Move minimax(Board board, int depth) {


        List<Move> moves = BoardHelper.generateMoves(board);

        return null;
    }
}
