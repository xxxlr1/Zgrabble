package test;

import java.util.Arrays;

// need to do: equals
public class Word {
    private Tile[] _tiles;
    private int _row, _col;
    private boolean _vertical;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return _row == word._row && _col == word._col && _vertical == word._vertical && Arrays.equals(_tiles, word._tiles);
    }

    public Word(Tile[] tiles, int row, int col, boolean vertical) {
        this._tiles = tiles;
        this._row =row;
        this._col = col;
        this._vertical = vertical;
    }

    public Tile[] get_tiles() {
        return _tiles;
    }

    public int get_row() {
        return _row;
    }

    public int get_col() {
        return _col;
    }

    public boolean is_vertical() {
        return _vertical;
    }
}
