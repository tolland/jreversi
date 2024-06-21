package org.limepepper.demo.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.limepepper.demo.Config;
import org.limepepper.demo.helper.BoardHelper;
import org.limepepper.demo.helper.IOHelper;
import org.limepepper.demo.helper.StringHelper;

import java.io.Serializable;

public class Board implements Serializable {

    private final Tile[][] tiles = new Tile[Config.HEIGHT_PADDED][Config.WIDTH_PADDED];

    private Tile toPlay = Tile.DARK;
    private Boolean complete; // null means not yet calculated

    private long lightBits = 0;
    private long darkBits = 0;


    public Board() {
        for (int y = 0; y < Config.HEIGHT_PADDED; y++) {
            for (int x = 0; x < Config.WIDTH_PADDED; x++) {
                if (x == 0 || y == 0 || x == Config.WIDTH + 1 || y == Config.HEIGHT + 1) {
                    //tiles[y][x] = Tile.VOID;
                    setTile(x, y, Tile.VOID);
                } else {
                    setTile(x, y, Tile.NONE);
                }
            }
        }

        setTile(4, 4, Tile.LIGHT);
        setTile(5, 5, Tile.LIGHT);
        setTile(4, 5, Tile.DARK);
        setTile(5, 4, Tile.DARK);
    }

    public Board(Tile[][] tiles) {
        setTiles(tiles);
    }

    public static Board copy(Board board) {
        Board newBoard = new Board();
        newBoard.setToPlay(board.getToPlay());
        newBoard.setTiles(board.getTiles());
        newBoard.complete = board.complete;
        return newBoard;
    }



    public Board setTile(int x, int y, Tile tile) {
        tiles[y][x] = tile;
        if (tile == Tile.LIGHT) {
            //System.out.println(StringHelper.formatBits(lightBits));
            lightBits |= 1L << ((y-1) * (Config.WIDTH) + x-1);
            //System.out.println(StringHelper.formatBits(lightBits));
        } else if (tile == Tile.DARK) {
            //System.out.println(StringHelper.formatBits(darkBits));
            darkBits |= 1L << ((y-1) * (Config.WIDTH) + x-1);
            //System.out.println(StringHelper.formatBits(darkBits));
        }
        complete = null;
        return this;
    }

    public Tile getTile(int x, int y) {
        return tiles[y][x];
    }

    public Tile getToPlay() {
        return toPlay;
    }

    public Board setToPlay(Tile toPlay) {
        this.toPlay = toPlay;
        // @TODO don't think setting the player should affect the board state
        complete = null;
        return this;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        for (int y = 0; y < Config.HEIGHT_PADDED; y++) {
            for (int x = 0; x < Config.WIDTH_PADDED; x++) {
                //this.tiles[y][x] = tiles[y][x];
                setTile(x, y, tiles[y][x]);
            }
        }
        complete = null;
    }

    public boolean currentCanPlay() {
        return !BoardHelper.generateMoves(this).isEmpty();
    }

    public int currentCount() {

        int count = 0;
        for (int y = 1; y <= Config.HEIGHT; y++) {
            for (int x = 1; x <= Config.WIDTH; x++) {
                if (tiles[y][x] == toPlay) {
                    count++;
                }
            }
        }
        return count;
    }

    public int oppositeCount() {

        int count = 0;
        for (int y = 1; y <= Config.HEIGHT; y++) {
            for (int x = 1; x <= Config.WIDTH; x++) {
                if (tiles[y][x] == toPlay.opposite()) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean oppositeCanPlay() {
        return !BoardHelper.generateMoves(Board.copy(this).setToPlay(toPlay.opposite())).isEmpty();
    }

    /**
     * do either of the players have any moves left?
     *
     * @return
     */
    public boolean isComplete() {
        if (complete == null) {
            complete = !(currentCanPlay() || oppositeCanPlay());
        }
        return complete;
    }

    @Override
    public String toString() {
        //return super.toString();
        String output = "";

        for (int y = 0; y < Config.HEIGHT + 2; y++) {
            for (int x = 0; x < Config.WIDTH + 2; x++) {
                output += " " + tiles[y][x].toString() + " " + "";
            }
            output += "\n";
        }
        return output;
    }

    public String toJson() {
        JSONObject jo = new JSONObject();
        jo.put("tiles", new JSONArray(IOHelper.tilesToJson(tiles)));
        jo.put("toPlay", toPlay);
        jo.put("complete", complete);
        return jo.toString();
    }

    public static Board fromJson(String json) {
        Board board = new Board();
        JSONObject jo = new JSONObject(json);
        //Tile toPlay = Tile.fromChar(jo.getString("toPlay").charAt(0));
        Tile toPlay = Tile.valueOf(jo.getString("toPlay"));
        JSONArray jaTileRows = jo.getJSONArray("tiles");
        for (int i = 0; i < jaTileRows.length(); i++) {
            JSONArray jaTileCol = (JSONArray) jaTileRows.get(i);
            for (int j = 0; j < jaTileCol.length(); j++) {
                board.setTile(j, i, Tile.fromChar(jaTileCol.getString(j).charAt(0)));
            }
        }
        board.setToPlay(toPlay);
        return board;
    }


    public long getLightBits() {
        return lightBits;
    }

    public long getDarkBits() {
        return darkBits;
    }
}
