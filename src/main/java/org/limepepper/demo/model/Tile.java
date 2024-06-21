package org.limepepper.demo.model;

public enum Tile {
    NONE('.'),
    DARK('X'),
    LIGHT('O'),
    VOID(' '); // for out of bounds

    private char shortName;

    Tile(char shortName) {
        this.shortName = shortName;
    }

    public Tile opposite() {
        switch (this) {
            case DARK:
                return LIGHT;
            case LIGHT:
                return DARK;
            default:
                throw new IllegalStateException("This tile has no opposite");
        }
    }

    /**
     * handle various permutations that are present in serialized form
     * @param c
     * @return
     */
    public static Tile fromChar(char c) {
        switch (c) {
            case 'b':
            case 'B':
            case 'x':
            case 'X':
            case '*':
                return DARK;
            case 'o':
            case 'O':
            case 'w':
            case 'W':
                return LIGHT;
            case '.':
            case '-':
            case '_':
                return NONE;
            case ' ':
                return VOID;
            default:
                throw new IllegalArgumentException("Invalid character for tile: " + c);
        }
    }

    @Override
    public String toString() {
        return this.shortName + "";
    }

    public String toAnsiString() {
        switch (this) {
            case DARK:
                return "\u001B[31m" + "●" + "\u001B[0m";
            case LIGHT:
                return "\u001B[33m" + "●" + "\u001B[0m";
            case VOID:
                return " ";
            default:
                return "○";
        }
    }

    public char getShortName() {
        return shortName;
    }
}
