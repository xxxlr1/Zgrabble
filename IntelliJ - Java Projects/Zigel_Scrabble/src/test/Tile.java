package test;
import java.util.Objects;
import java.util.Random;


public class Tile {

    public final char letter;
    public final int _score;

    private Tile(char ltr, int scr)
    {
        this.letter = ltr;
        this._score = scr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return letter == tile.letter && _score == tile._score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, _score);
    }

    public char getLetter() {
        return letter;
    }

    public int get_score() {
        return _score;
    }

    public static class Bag
    {
        private static Bag bag=null;
        private int _currAmount[] =            {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
        private final int _maxAmount[] =              {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
        private final int _scoreArr[] =               {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
        private  Tile _tiles[] = new Tile[26];
        private Bag() {
            int i;
            char x;
            for (i = 0, x = 'A'; i < _tiles.length; i++, x++) {
                this._tiles[i] = new Tile(x, _scoreArr[i]);
            }
        }

        // get random tile with srand library, if not found, check if the next letter is availble
        public Tile getRand()
        {
            Random rand = new Random();
            int i=0,j=0, rand_int = rand.nextInt(26);

        while (!isTileExist(rand_int+i))
        {
            i++;
            j++;
            if (i+rand_int > 26) {
                rand_int = 0;
                i=1;
            }
            if (j == 26) // not found any available tile
                return null;
        }
            this._currAmount[rand_int+i]--;
            return _tiles[rand_int+i];
        }

        // check if tile exist by checking it amount from the specific index
        public boolean isTileExist(int num)
        {
            if(this._currAmount[num] == 0)
                return false; // not found

            return true;
        }

        // search through the tiles to compare the input letter, if found return it
        public Tile getTile(char ltr)
        {
            for (int i = 0; i< _tiles.length; i++)
                if(ltr == _tiles[i].getLetter() && isTileExist(i))
                {
                    this._currAmount[i]--;
                    return _tiles[i];
                }
            return null;
        }

        // run through to find the letter then check if the maximum amount is there
        public void put(Tile tile) //maybe need to get char only instead of Tile object
        {
            Bag b = getBag();

            int tileIndex = tile.getLetter()-'A';
            if(b._currAmount[tileIndex] >= b._maxAmount[tileIndex])
                return;
            else
            {
                b._currAmount[tileIndex]++;
                return;
            }
        }

        // maybe try to change the method so that it will return the size in O(1)
        // by holding another data member for this
        public int size()
        {
            int sum=0;
            for (int i = 0; i< _tiles.length; i++)
                sum += _currAmount[i];

            return sum;
        }


        public int[] getQuantities()
        {
            return getBag()._currAmount.clone();
        }

        public static Bag getBag()
        {
             if (bag==null)
                 bag = new Bag();

             return bag;
        }



    }

}
