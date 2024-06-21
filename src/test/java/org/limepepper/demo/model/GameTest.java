package org.limepepper.demo.model;

import org.junit.Test;
import org.limepepper.demo.Config;
import org.limepepper.demo.helper.BoardHelper;
import org.limepepper.demo.helper.IOHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GameTest {


    @Test
    public void makeMove() {
        Board newBoard = new Board(new Tile[][]{
                new Tile[]{Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID},
                new Tile[]{Tile.VOID, Tile.DARK, Tile.DARK, Tile.DARK, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.VOID},
                new Tile[]{Tile.VOID, Tile.DARK, Tile.DARK, Tile.DARK, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.VOID},
                new Tile[]{Tile.VOID, Tile.DARK, Tile.DARK, Tile.DARK, Tile.LIGHT, Tile.NONE, Tile.NONE, Tile.NONE, Tile.NONE, Tile.VOID},

                new Tile[]{Tile.VOID, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.LIGHT, Tile.LIGHT, Tile.NONE, Tile.NONE, Tile.VOID},

                new Tile[]{Tile.VOID, Tile.DARK, Tile.DARK, Tile.DARK, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.NONE, Tile.NONE, Tile.VOID},

                new Tile[]{Tile.VOID, Tile.DARK, Tile.DARK, Tile.LIGHT, Tile.DARK, Tile.LIGHT, Tile.LIGHT, Tile.NONE, Tile.NONE, Tile.VOID},

                new Tile[]{Tile.VOID, Tile.DARK, Tile.DARK, Tile.DARK, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.NONE, Tile.NONE, Tile.VOID},

                new Tile[]{Tile.VOID, Tile.LIGHT, Tile.DARK, Tile.LIGHT, Tile.DARK, Tile.LIGHT, Tile.DARK, Tile.NONE, Tile.NONE, Tile.VOID},

                new Tile[]{Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID}
        });
        Game game = Game.fromBoard(newBoard);
        game.start();
        Game oldGame = Game.copy(game);
        assertEquals(game.getCurrentPlayer(), Tile.DARK);
        assertEquals(oldGame.getCurrentPlayer(), Tile.DARK);

        game.board.setToPlay(Tile.LIGHT);
        System.out.println(game.board.toJson());
        System.out.println(BoardHelper.toAnsiString(game.board));
        System.out.println("valid: " + BoardHelper.isValidMove(game.board, 7, 8));
        game.makeMove(7, 8, Tile.LIGHT);
        System.out.println(BoardHelper.toAnsiString(oldGame.board));
        System.out.println(BoardHelper.toAnsiString(game.board));

        System.out.print(IOHelper.printArrayOfStrings(IOHelper.print2boards(oldGame.board, game.board)));
        assertEquals(game.status, Game.Status.ONGOING);
        assertEquals(game.getCurrentPlayer(), Tile.DARK);
    }

    @Test
    public void makeMove2() {
        Board newBoard = new Board();
        newBoard.setToPlay(Tile.LIGHT);
        for (int i = 1; i <= Config.WIDTH; i++) {
            for (int j = 1; j <= Config.HEIGHT; j++) {
                newBoard.setTile(i, j, Tile.DARK);
            }
        }
        newBoard.setTile(1, 1, Tile.LIGHT);
        newBoard.setTile(1, 8, Tile.LIGHT);
        newBoard.setTile(8, 1, Tile.LIGHT);
        newBoard.setTile(8, 8, Tile.NONE);
        Game game = Game.fromBoard(newBoard);
        game.start();
        System.out.println(BoardHelper.toAnsiString(newBoard));
        System.out.println("valid: " + BoardHelper.isValidMove(newBoard, 8, 8));
        game.makeMove(8, 8, Tile.LIGHT);
        System.out.println(BoardHelper.toAnsiString(newBoard));
    }

    @Test
    public void testToString() {
        Game game = new Game();
        String gameString = game.toString();
        System.out.println(gameString);
        assertEquals(game.status, Game.Status.PREGAME);
        assertEquals(game.getCurrentPlayer(), Tile.DARK);
    }

    @Test
    public void testToJson() {
        Game game = new Game();
        String json = game.toJson();
        System.out.println(json);
    }

    @Test
    public void testFromJson() {
        Game game = new Game();
        game.start();
        game.board.setTile(1,1, Tile.DARK);
        System.out.print(BoardHelper.toAnsiString(game.board));
        String json = game.toJson();
        System.out.println(json);
        Game newGame = Game.fromJson(json);
        assertEquals(game.toJson(), newGame.toJson());
    }

    @Test
    public void testOldBoards1() {
        Game game = new Game();
        game.start();
        assertEquals(game.getCurrentPlayer(), Tile.DARK);
        System.out.print(BoardHelper.toAnsiString(game.board));
        game.makeMove(4,3, Tile.DARK);
        assertEquals(game.getOldBoards().size(), 1);
        assertEquals(game.getCurrentPlayer(), Tile.LIGHT);
        System.out.print(BoardHelper.toAnsiString(game.board));
        game.makeMove(5,3, Tile.LIGHT);
        assertEquals(game.getOldBoards().size(), 2);
        assertEquals(game.getCurrentPlayer(), Tile.DARK);
        System.out.print(BoardHelper.toAnsiString(game.board));
        String json = game.toJson();
        //System.out.println(json);
        Game newGame = Game.fromJson(json);
        System.out.println(newGame.toJson());
        System.out.println(game.toJson());
        assertEquals(game.toJson(), newGame.toJson());
    }
    @Test
    public void testOldBoards2() {
        Game game = new Game();
        game.start();
        assertEquals(game.getCurrentPlayer(), Tile.DARK);
        System.out.print(BoardHelper.toAnsiString(game.board));
        game.makeMove(4,3, Tile.DARK);
        assertEquals(game.getOldBoards().size(), 1);
        assertEquals(game.getCurrentPlayer(), Tile.LIGHT);
        System.out.print(BoardHelper.toAnsiString(game.board));
        game.makeMove(5,3, Tile.LIGHT);
        assertEquals(game.getOldBoards().size(), 2);
        assertEquals(game.getCurrentPlayer(), Tile.DARK);
        System.out.print(BoardHelper.toAnsiString(game.board));

        game.undo();
        assertEquals(game.getOldBoards().size(), 1);
        assertEquals(game.getCurrentPlayer(), Tile.LIGHT);
        System.out.print(BoardHelper.toAnsiString(game.board));

        String json = game.toJson();
        //System.out.println(json);
        Game newGame = Game.fromJson(json);
        System.out.println(newGame.toJson());
        System.out.println(game.toJson());
        assertEquals(game.toJson(), newGame.toJson());
    }

    @Test
    public void testCopyGame1() {
        Game game = new Game();
        game.start();
        assertEquals(game.getCurrentPlayer(), Tile.DARK);
        System.out.print(BoardHelper.toAnsiString(game.board));
        game.makeMove(4,3, Tile.DARK);
        assertEquals(game.getOldBoards().size(), 1);
        assertEquals(game.getCurrentPlayer(), Tile.LIGHT);
        System.out.print(BoardHelper.toAnsiString(game.board));
        game.makeMove(5,3, Tile.LIGHT);
        assertEquals(game.getOldBoards().size(), 2);
        assertEquals(game.getCurrentPlayer(), Tile.DARK);
        System.out.print(BoardHelper.toAnsiString(game.board));

        Game newGame = Game.copy(game);
        assertEquals(newGame.getOldBoards().size(), 2);
        assertEquals(newGame.getCurrentPlayer(), Tile.DARK);
        System.out.print(BoardHelper.toAnsiString(newGame.board));
        assertEquals(game.toJson(), newGame.toJson());
        assertNotEquals(game.hashCode(), newGame.hashCode());
    }
}
