package org.limepepper.demo.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.limepepper.demo.Config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Game implements Serializable {

    public Board board;
    private Stack<Board> oldBoards = new Stack<>();
    protected Status status;


    public Game() {
        board = new Board();
        status = Status.PREGAME;
    }

    public static Game fromBoard(Board board) {
        Game game = new Game();
        game.board = board;
        return game;
    }

    public static Game copy(Game game) {
        Game newGame = new Game();
        newGame.board = Board.copy(game.board);
        newGame.oldBoards = new Stack<>();
        for (int i = 0; i < game.oldBoards.size(); i++) {
            newGame.oldBoards.push(Board.copy(game.oldBoards.get(i)));
        }
        newGame.status = game.status;
        return newGame;
    }

    enum Status {
        DARK_WIN,
        LIGHT_WIN,
        DRAW,
        ONGOING,
        PREGAME;
    }

    /**
     * Make a move on the board. this doesn't properly validate the move
     * due to the cost of checking all possible moves
     *
     * @param x
     * @param y
     * @param tile
     */
    public Board makeMove(int x, int y, Tile tile) {
        if (status != Status.ONGOING) {
            throw new IllegalStateException("Game not ongoing");
        }
        if (board.getTile(x, y) != Tile.NONE) {
            throw new IllegalStateException("Tile is occupied");
        }
        if (board.getToPlay() != tile) {
            throw new IllegalStateException("Not player's turn");
        }
        oldBoards.push(Board.copy(board));

        Board newBoard = Board.copy(board);

        newBoard.setTile(x, y, tile);
        for (Direction direction : Direction.values()) {
            int dx = direction.getDx();
            int dy = direction.getDy();
            boolean foundOpposite = false;
            boolean foundCurrent = false;
            List<Move> toFlip = new ArrayList<>();

            int count = 1;
            while (true) {
                int i = x + (dx * count);
                int j = y + (dy * count);
                //System.out.println("makeMove checking i:" + i + " j:" + j + " count:" + count + " dx:" + dx + " dy:" + dy + "direction: " + direction);

                // check if out of bounds
                if (i < 0 || i > Config.WIDTH + 1 || j < 0 || j > Config.HEIGHT + 1) {
                    System.out.println("out of bounds");
                    break;
                } else if (newBoard.getTile(i,j) == Tile.NONE) {
                    //System.out.println("none square");
                    break;
                } else if (newBoard.getTile(i,j) == newBoard.getToPlay()) {
                    foundCurrent = true;
                    // previously found opposite, and this is end tile
                    if (foundOpposite) {
                        // System.out.println("found opposite - setting tiles");
                        for (int k = 1; k < count; k++) {
                            newBoard.setTile(x + (dx * k),y + (dy * k),newBoard.getToPlay());
                        }
                        break;
                    }
                    // otherwise this was a dead end
                    break;
                } else if (newBoard.getTile(i,j) == newBoard.getToPlay().opposite()) {
                    foundOpposite = true;
                }

                count++;
            }
        }
        newBoard.setToPlay(board.getToPlay().opposite());
        board = newBoard;
        return board;
    }

    public void start() {
        status = Status.ONGOING;
    }

    public void restart() {
        System.out.println("restarting in game class");
        board = new Board();
        oldBoards.clear();
        status = Status.ONGOING;
    }

    //TODO: implement handling the various cases of undoing skipped
    // move due to no valid moves.
    public void undo() {
        this.board = this.oldBoards.pop();
    }

    public Board getBoard() {
        return board;
    }

    /**
     * direct set board, don't update status or oldBoards
     *
     * @param newBoard
     */
    public void setBoard(Board newBoard) {
        this.board = newBoard;
    }

    public Tile getCurrentPlayer() {
        return board.getToPlay();
    }

    public void setCurrentPlayer(Tile currentPlayer) {
        board.setToPlay(currentPlayer);
    }

    public Board getOldBoard() {
        return oldBoards.peek();
    }

    public Stack<Board> getOldBoards() {
        return oldBoards;
    }

    public Status getStatus() {
        return status;
    }

    public String toJson() {
        JSONObject jo = new JSONObject();
        jo.put("board", new JSONObject(board.toJson()));
        //jo.put("oldBoard", new JSONObject(oldBoard.toJson()));
        var jaOldBoards = new JSONArray();
        for (int i = 0; i < oldBoards.size(); i++) {
            jaOldBoards.put(new JSONObject(oldBoards.get(i).toJson()));
        }
        jo.put("oldBoards", jaOldBoards);
        jo.put("status", status);
        return jo.toString();
    }

    public static Game fromJson(String json) {
        Game game = new Game();
        JSONObject jo = new JSONObject(json);
        game.board = Board.fromJson(jo.getJSONObject("board").toString());
        JSONArray jsonOldBoards = jo.getJSONArray("oldBoards");
        for (int i = 0; i < jsonOldBoards.length(); i++) {
            game.oldBoards.push(Board.fromJson(jsonOldBoards.getJSONObject(i).toString()));
        }
        game.status = Status.valueOf(jo.getString("status"));
        return game;
    }

    @Override
    public String toString() {
        //return super.toString();
        String output = "";
        output += "Current player: " + getCurrentPlayer() + "\n";
        output += "Status: " + status + "\n";
        output += board.toString();
        if (!oldBoards.isEmpty()) {
            output += "Old board: \n";
            output += getOldBoard().toString();
        }
        return output;
    }
}
