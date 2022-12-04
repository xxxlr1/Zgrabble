package test;

import java.util.ArrayList;

public class Board {

    private static final int STAR = 7;
    private static final int BOARD_SIZE = 15;
    private static Tile[][] _BoardTiles = new Tile[BOARD_SIZE][BOARD_SIZE];
    private final char[][] _BoardMat = new char[BOARD_SIZE][BOARD_SIZE];
    private final char _TripleWordScr = '!';
    private final char _DoubleWordScr = '@';
    private final char _TripleLetterScr = '#';
    private final char _DoubleLetterScr = '$';
    private final char _Star = '*';
    private static Board _boardInstance = null;
    private static boolean _firstWord = false;

    private Board() {

        _BoardMat[STAR][STAR] = _Star;

        _BoardMat[0][0] = _TripleWordScr;
        _BoardMat[0][7] = _TripleWordScr;
        _BoardMat[0][14] = _TripleWordScr;
        _BoardMat[7][0] = _TripleWordScr;
        _BoardMat[7][14] = _TripleWordScr;
        _BoardMat[14][0] = _TripleWordScr;
        _BoardMat[14][7] = _TripleWordScr;
        _BoardMat[14][14] = _TripleWordScr;

        _BoardMat[1][1] = _DoubleWordScr;
        _BoardMat[2][2] = _DoubleWordScr;
        _BoardMat[3][3] = _DoubleWordScr;
        _BoardMat[4][4] = _DoubleWordScr;
        _BoardMat[1][13] = _DoubleWordScr;
        _BoardMat[2][12] = _DoubleWordScr;
        _BoardMat[3][11] = _DoubleWordScr;
        _BoardMat[4][10] = _DoubleWordScr;
        _BoardMat[13][1] = _DoubleWordScr;
        _BoardMat[12][2] = _DoubleWordScr;
        _BoardMat[11][3] = _DoubleWordScr;
        _BoardMat[10][4] = _DoubleWordScr;
        _BoardMat[13][13] = _DoubleWordScr;
        _BoardMat[12][12] = _DoubleWordScr;
        _BoardMat[12][12] = _DoubleWordScr;
        _BoardMat[12][12] = _DoubleWordScr;

        _BoardMat[1][5] = _TripleLetterScr;
        _BoardMat[1][9] = _TripleLetterScr;
        _BoardMat[5][5] = _TripleLetterScr;
        _BoardMat[5][9] = _TripleLetterScr;
        _BoardMat[5][1] = _TripleLetterScr;
        _BoardMat[5][13] = _TripleLetterScr;
        _BoardMat[9][5] = _TripleLetterScr;
        _BoardMat[9][9] = _TripleLetterScr;
        _BoardMat[9][1] = _TripleLetterScr;
        _BoardMat[9][13] = _TripleLetterScr;
        _BoardMat[13][5] = _TripleLetterScr;
        _BoardMat[13][9] = _TripleLetterScr;

        _BoardMat[0][3] = _DoubleLetterScr;
        _BoardMat[0][11] = _DoubleLetterScr;
        _BoardMat[2][6] = _DoubleLetterScr;
        _BoardMat[2][8] = _DoubleLetterScr;
        _BoardMat[3][0] = _DoubleLetterScr;
        _BoardMat[3][7] = _DoubleLetterScr;
        _BoardMat[3][14] = _DoubleLetterScr;
        _BoardMat[6][2] = _DoubleLetterScr;
        _BoardMat[6][6] = _DoubleLetterScr;
        _BoardMat[6][8] = _DoubleLetterScr;
        _BoardMat[6][12] = _DoubleLetterScr;
        _BoardMat[7][3] = _DoubleLetterScr;
        _BoardMat[7][11] = _DoubleLetterScr;
        _BoardMat[8][2] = _DoubleLetterScr;
        _BoardMat[8][6] = _DoubleLetterScr;
        _BoardMat[8][8] = _DoubleLetterScr;
        _BoardMat[8][12] = _DoubleLetterScr;
        _BoardMat[11][0] = _DoubleLetterScr;
        _BoardMat[11][7] = _DoubleLetterScr;
        _BoardMat[11][14] = _DoubleLetterScr;
        _BoardMat[12][6] = _DoubleLetterScr;
        _BoardMat[12][8] = _DoubleLetterScr;
        _BoardMat[14][3] = _DoubleLetterScr;
        _BoardMat[14][11] = _DoubleLetterScr;

    }

    public static Board getBoard() {
        if (_boardInstance == null)
            _boardInstance = new Board();

        return _boardInstance;
    }

    // get the tiles that are currently on the board
    public Tile[][] getTiles() {
        Tile[][] curTiles = new Tile[15][15];
        Board board = getBoard();

        for (int i = 0; i < board._BoardMat.length; i++)
            for (int j = 0; j < board._BoardMat.length; j++)
                if (board._BoardTiles[i][j] != null)//.get_letter()>= 'A' && board._BoardTiles[i][j].get_letter() <= 'Z')
                    curTiles[i][j] = board._BoardTiles[i][j];

        return curTiles;
    }

    public boolean dictionaryLegal(Word w) {
        return true;
    }

    // check if the word can fit within the board's borders
    public boolean WordInBoardsRange(Word w) {
        int row = w.get_row(),
                col = w.get_col(),
                wordSize = w.get_tiles().length;
        boolean w_is_horizontal = !w.is_vertical();

        if (col < 0 || row < 0)
            return false;

        if (w_is_horizontal && col + wordSize > BOARD_SIZE - 1)
            return false;
        else if (w.is_vertical() && row + wordSize > BOARD_SIZE - 1)
            return false;

        return true;
    }

    // check if the first word is on the star
    public boolean FirstWordOnStar(Word w) {
        int row = w.get_row(),
                col = w.get_col(),
                wordSize = w.get_tiles().length;
        boolean w_is_horizontal = !w.is_vertical();

        for (int i = 0; i < wordSize; i++) {
            if (w_is_horizontal && row == STAR && col + wordSize > STAR)
                return true;
            else if (w.is_vertical() && col == STAR && row + wordSize > STAR)
                return true;
        }
        return false;
    }

    // if we need to complete a tile that have a _ and it צמוד/חופך another word, return that original tile
    public boolean WordsAlign(Word w) {
        int row = w.get_row(),
                col = w.get_col(),
                wordSize = w.get_tiles().length;
        boolean w_is_horizontal = !w.is_vertical();

        for (int i = 0; i < wordSize; i++) {
            if (w_is_horizontal && w.get_tiles()[i] == null &&
                    (_BoardTiles[row - 1][col + i] != null || _BoardTiles[row + 1][col + i] != null)) {
                w.get_tiles()[i] = _BoardTiles[row][col + i];
                return true;
            } else if (w.is_vertical() && w.get_tiles()[i] == null &&
                    (_BoardTiles[row + i][col - 1] != null || _BoardTiles[row + i][col + 1] != null)) {
                w.get_tiles()[i] = _BoardTiles[row + i][col];
                return true;
            }
        }
        return false;
    }

    // check if there's a tile around the first word's tile
    public boolean checkAroundWord(Word w) {
               int     row = w.get_row(),
                col = w.get_col(),
                wordSize = w.get_tiles().length;
        boolean w_is_horizontal = !w.is_vertical();

        for (int i = 0; i < wordSize; i++) {
            if (w_is_horizontal)
                  {
                if (i == 0 && col - 1 > 0 && _BoardTiles[row][col - 1] != null)                             // check up
                    return true;
                if (row - 1 > 0 && _BoardTiles[row - 1][col + i] != null)                                  // check left
                    return true;
                if (row + 1 < BOARD_SIZE && _BoardTiles[row + 1][col + i] != null)                        // check right
                    return true;
                if (i == wordSize - 1 && col + 1 < BOARD_SIZE && _BoardTiles[row + i + 1][col + 1] != null) //check down
                    return true;
                   }

                else if (w.is_vertical())
                   {
                if (i == 0 && row - 1 > 0 && _BoardTiles[row - 1][col] != null)                              // check up
                    return true;
                if (col - 1 > 0 && _BoardTiles[row + i][col - 1] != null)                                  // check left
                    return true;
                if (col + 1 < BOARD_SIZE && _BoardTiles[row + i][col + 1] != null)                        // check right
                    return true;
                if (i == wordSize - 1 && row + 1 < BOARD_SIZE && _BoardTiles[row + 1][col + i + 1] != null) //check down
                    return true;
                  }
        }
        return false;
    }

    // check if the word is legal using different situational methods
    public boolean boardLegal(Word w) {

        // check if the word is in the dictionary
        if (!dictionaryLegal(w))
            return false;

        if (!WordInBoardsRange(w))
            return false;

        if (_firstWord == false)
            return FirstWordOnStar(w);

        if (WordsAlign(w))
            return true;

        if (checkAroundWord(w))
            return true;

        return false;
    }

    // check if there are tiles above or below the word, if so try to locate a new created word
    public Word getWordUpToDown(int row, int col, Word w, int t) {

        int tempSize = 0,
                tempCol = 0,
                tempRow = 0,
                i = 0,
                rowUp = 0,
                rowDown = 0;
        Word tempWord = null;
        ArrayList<Tile> tileArr = null;

        // if there's a tile above and/or below, we will create a tile vector from that size
        while (_BoardTiles[row - 1 - rowUp][col] != null || _BoardTiles[row + 1 + rowDown][col] != null) {
            if (_BoardTiles[row - 1 - rowUp][col] != null && _BoardTiles[row + 1 + rowDown][col] != null) {
                i += 2;
                rowDown++;
                rowUp++;
            } else if (_BoardTiles[row - 1 - rowUp][col] != null) {
                i++;
                rowUp++;
            } else if (_BoardTiles[row + 1 + rowDown][col] != null) {
                i++;
                rowDown++;
            }
        }

        tempCol = col;
        tempRow = row - rowUp;
        tempSize = i + 1;
        tileArr = new ArrayList<>(tempSize);

        // if the board in this coordinates isnt null, theres a tile there, add it to the arr
        // if not, add the new word's tile to the arr
        for (i = 0; i < tempSize; i++) {
            if (_BoardTiles[tempRow + i][col] != null)
                tileArr.add(_BoardTiles[tempRow + i][tempCol]);
            else
                tileArr.add(w.get_tiles()[t]);
        }

        tempWord = new Word(tileArr.toArray(new Tile[tempSize]), tempRow, tempCol, true);
        return tempWord;
    }

    // same like above but from left to right
    public Word getWordLeftToRight(int row, int col, Word w, int t) {
        int tempSize = 0,
                tempCol = 0,
                tempRow = 0, i = 0,
                colLeft = 0,
                colRight = 0;
        Word tempWord = null;
        ArrayList<Tile> tileArr = null;

        while (_BoardTiles[row][col - 1 - colLeft] != null || _BoardTiles[row][col + 1 + colRight] != null) {
            if (_BoardTiles[row][col - 1 - colLeft] != null && _BoardTiles[row][col + 1 + colRight] != null) {
                i += 2;
                colLeft++;
                colRight++;
            } else if (_BoardTiles[row][col - 1 - colLeft] != null) {
                i++;
                colLeft++;
            } else if (_BoardTiles[row][col + 1 + colRight] != null) {
                i++;
                colRight++;
            }
        }

        tempCol = col - colLeft;
        tempRow = row;
        tempSize = i + 1;
        tileArr = new ArrayList<>(tempSize);

        for (i = 0; i < tempSize; i++) {
            if (_BoardTiles[row][tempCol + i] != null)
                tileArr.add(_BoardTiles[tempRow][tempCol + i]);
            else
                tileArr.add(w.get_tiles()[t]);
        }

        tempWord = new Word(tileArr.toArray(new Tile[tempSize]), tempRow, tempCol, false);
        return tempWord;
    }

    // check for new words created from putting the current word
    public ArrayList<Word> getWords(Word w) {

        int     row = w.get_row(),
                col = w.get_col(),
                wordSize = w.get_tiles().length;
        boolean w_is_horizontal = !w.is_vertical();
        ArrayList<Word> WordsArray = new ArrayList<>();
        Word tempWord = null;

        WordsArray.add(w);
        for (int i = 0; i < wordSize; i++) {
            if (w_is_horizontal && _BoardTiles[row][col + i] == null) {
                if (_BoardTiles[row - 1][col + i] != null || _BoardTiles[row + 1][col + i] != null) {
                    //  check the board for words that can be created from top to bottom, also send the index
                    //  where the new word is supposed to land (i)
                    tempWord = getWordUpToDown(row, col + i, w, i);
                    if (boardLegal(tempWord))  // if (dictionarylegal(tempWord))
                        WordsArray.add(tempWord);
                }
            } else if (w.is_vertical() && _BoardTiles[row + i][col] == null) {
                if (_BoardTiles[row + i][col - 1] != null || _BoardTiles[row + i][col + 1] != null) {
                    tempWord = getWordLeftToRight(row + i, col, w, i);
                    if (boardLegal(tempWord))
                        WordsArray.add(tempWord);
                }
            }

        }

        //  WordsArray = checkOldWords(WordsArray); maybe doesnt need because the board legal check on temp words
        return WordsArray;
    }

    // remove all words that repeat the word's vector (didnt need this method so far)
    public ArrayList<Word> checkOldWords(ArrayList<Word> WordsArr) {
        Board board = getBoard();
        for (int i = 0; i < board._BoardTiles.length; i++) {
            for (int j = 0; j < board._BoardTiles.length; j++) {
                if (board._BoardTiles[i][j] != null)
                    for (int k = 0; k < WordsArr.size(); k++) {
                        if (WordsArr.get(k).equals(board._BoardTiles[i][j])) {
                            WordsArr.remove(k);
                        }
                    }
            }
        }
        return WordsArr;
    }

    // get the word's score according to the board's bonuses
    public int getScore(Word w) {
        int i,  k = 0,
          wordScoreWithoutBonus = 0,
                  bonuses = 0,
              tripleWordCounter = 0,
                doubleWordCounter = 0,
                  row = w.get_row(),
                col = w.get_col(),
                wordSize = w.get_tiles().length;
        boolean w_is_horizontal = !w.is_vertical();

        for (i = 0; i < wordSize; i++)
            wordScoreWithoutBonus += w.get_tiles()[i].get_score();


        if (w_is_horizontal) {
            for (i = 0; i < wordSize; i++) {

                switch (_BoardMat[row][col + k]) {
                    case '!': //triple word
                        tripleWordCounter++;
                        k++;
                        break;
                    case '@': //double wrd
                        doubleWordCounter++;
                        k++;
                        break;
                    case '*': // star
                        if (_BoardTiles[row][col + k] == null)
                            doubleWordCounter++;
                        k++;
                        break;
                    case '#': //triple ltr
                        bonuses = bonuses + w.get_tiles()[k].get_score() * 3;
                        wordScoreWithoutBonus -= w.get_tiles()[k].get_score();
                        k++;
                        break;
                    case '$': //double ltr
                        bonuses = bonuses + w.get_tiles()[k].get_score() * 2;
                        wordScoreWithoutBonus -= w.get_tiles()[k].get_score();
                        k++;
                        break;
                    default:
                        k++;
                }
            }
        } else if (w.is_vertical()) {
            for (i = 0; i < wordSize; i++) {
                switch (_BoardMat[row + k][col]) {
                    case '!': //triple word
                        tripleWordCounter++;
                        k++;
                        break;
                    case '@': //double wrd
                        doubleWordCounter++;
                        k++;
                        break;
                    case '*': // star
                        if (_BoardTiles[row + k][col] == null)
                            doubleWordCounter++;
                        k++;
                        break;
                    case '#': //triple ltr
                        bonuses = bonuses + w.get_tiles()[k].get_score() * 3;
                        wordScoreWithoutBonus -= w.get_tiles()[k].get_score();
                        k++;
                        break;
                    case '$': //double ltr
                        bonuses = bonuses + w.get_tiles()[k].get_score() * 2;
                        wordScoreWithoutBonus -= w.get_tiles()[k].get_score();
                        k++;
                        break;
                    default:
                        k++;
                }
            }
        }

        bonuses += wordScoreWithoutBonus;

        for (i = 0; i < tripleWordCounter; i++)
            bonuses *= 3;

        for (i = 0; i < doubleWordCounter; i++)
            bonuses *= 2;

        return bonuses;
    }

    // try placing a word by checking all the needed criterias, then returning the word's score
    public int tryPlaceWord(Word w) {

        int  sumScore = 0;
        boolean wordFlag = false;
        Word tempWord = null;
        ArrayList<Word> WordsArray = null;
        if (!_firstWord && boardLegal(w))
        {
            _firstWord = true;
            wordFlag = true;
        }
        else wordFlag = boardLegal(w);

        if (wordFlag) {
            WordsArray = getWords(w);
            for (int i = 0; i < WordsArray.size(); i++)
            {
                tempWord = WordsArray.get(i);
                if (tempWord != null)
                {
                    int tempWordSize = tempWord.get_tiles().length,
                            tempCol = tempWord.get_col(),
                            tempRow = tempWord.get_row();
                    boolean tempWord_is_horizontal = !tempWord.is_vertical();

                    sumScore += getScore(tempWord);
                    for (int j = 0; j < tempWordSize; j++)
                    {
                        if (tempWord_is_horizontal && _BoardTiles[tempRow][tempCol + j] == null)
                            _BoardTiles[tempRow][tempCol + j] = w.get_tiles()[j];
                        else if (tempWord.is_vertical() && _BoardTiles[tempRow + j][tempCol] == null)
                            _BoardTiles[tempRow + j][tempCol] = w.get_tiles()[j];
                    }
                }
            }
        }

        return sumScore;
    }

}

