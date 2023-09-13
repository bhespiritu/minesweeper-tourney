package org.example;

public class MineSweeperGame {

    public final int sizeX, sizeY, numBombs;

    private final int maxAttempts;

    int[][] data;
    boolean[][] flagged;
    boolean[][] revealed;

    public MineSweeperGame(int x, int y, int n)
    {
        this(x,y,n,true);
    }

    public MineSweeperGame(int x, int y, int n, int ix, int iy)
    {
        this(x,y,n,false);

        if(checkBounds(ix,iy))
        {
            throw new IllegalArgumentException("Initial reveal out of bounds");
        }

        populate(ix, iy);
    }

    public MineSweeperGame(int x, int y, int n, boolean initialize)
    {
        sizeX = x;
        sizeY = y;
        numBombs = n;

        if(n < 0)
        {
            throw new IllegalArgumentException(
                    String.format("You cannot add negative bombs to a game: %d",n));
        }

        if(n >= x*y)
        {
            throw new IllegalArgumentException(
                    String.format("%d bombs cannot fit in a %d x %d grid (Count: %d)." +
                            "You need at least one free space.",n,x,y,x*y));
        }

        maxAttempts = sizeX*sizeY;
        if(initialize) populate();
    }

    public void populate()
    {
        populate((int)(Math.random()*sizeX),(int)(Math.random()*sizeY));
    }

    private boolean checkBounds(int x, int y)
    {
        return !(x < 0 || x >= sizeX || y < 0 || y >= sizeY);
    }

    public void populate(int ix, int iy)
    {
        data = new int[sizeX][sizeY];
        flagged = new boolean[sizeX][sizeY];
        revealed = new boolean[sizeX][sizeY];
        addBomb:
        for(int i = numBombs; i > 0; i--)
        {
            for(int j = 0; j < maxAttempts; j++)
            {
                int bX = (int)(Math.random()*sizeX);
                int bY = (int)(Math.random()*sizeY);
                System.out.println("attempt " + j + " at " + bX + "," + bY);
                if(data[bX][bY] != -1 && !(bX == ix && bY == iy))
                {
                    data[bX][bY] = -1;
                    continue addBomb;
                }
            }

            throw new RuntimeException("Couldn't find place for bomb");
        }

        for(int x = 0; x < sizeX; x++)
        {
            for(int y = 0; y < sizeY; y++)
            {
                if(data[x][y] != -1)
                {
                    for(int j = -1; j <= 1; j++)
                    {
                        for(int i = -1; i <= 1; i++)
                        {
                            if(checkBounds(x+i, y+j) && !(i==0 && j==0))
                            {
                                if(data[x+i][y+j] == -1) data[x][y]++;
                            }
                        }
                    }
                }
            }
        }

        reveal(ix,iy);
    }

    public void reveal(int x, int y)
    {
        revealed[x][y] = true;
    }

}
