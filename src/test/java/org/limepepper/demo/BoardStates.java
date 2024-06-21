package org.limepepper.demo;

import org.limepepper.demo.model.Tile;

public class BoardStates {


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



    public static final Tile[][] fullLightBoard = new Tile[][]{
            new Tile[]{Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID},
            new Tile[]{Tile.VOID, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.VOID},
            new Tile[]{Tile.VOID, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.VOID},
            new Tile[]{Tile.VOID, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.VOID},

            new Tile[]{Tile.VOID, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.VOID},

            new Tile[]{Tile.VOID, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.VOID},

            new Tile[]{Tile.VOID, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.VOID},

            new Tile[]{Tile.VOID, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.VOID},

            new Tile[]{Tile.VOID, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.LIGHT, Tile.VOID},

            new Tile[]{Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID}
    };

    public static final Tile[][] fullDarkBoard = new Tile[][]{
            new Tile[]{Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID},
            new Tile[]{Tile.VOID, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.VOID},
            new Tile[]{Tile.VOID, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.VOID},
            new Tile[]{Tile.VOID, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.VOID},

            new Tile[]{Tile.VOID, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.VOID},

            new Tile[]{Tile.VOID, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.VOID},

            new Tile[]{Tile.VOID, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.VOID},

            new Tile[]{Tile.VOID, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.VOID},

            new Tile[]{Tile.VOID, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.DARK, Tile.VOID},

            new Tile[]{Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID, Tile.VOID}
    };

}
