package org.example;

import javax.swing.*;
import java.awt.*;

public class MineSweeperDisplayPane extends JPanel {

    public MineSweeperGame game;

    public MineSweeperDisplayPane(MineSweeperGame game)
    {
        this.game = game;
        setPreferredSize(new Dimension(game.sizeX*20, game.sizeY*20));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int minDim = Math.min(getWidth(), getHeight());
        int ppm = minDim/Math.min(game.sizeX, game.sizeY);

        for(int x = 0; x < game.sizeX; x++)
        {
            for(int y = 0; y < game.sizeY; y++)
            {
                int px = x*ppm;
                int py = y*ppm;

                if(game.data[x][y] == -1)
                {
                    g.setColor(Color.red);
                    g.fillRect(px,py, ppm, ppm);
                }

                g.setColor(Color.black);
                g.drawRect(px, py, ppm, ppm);
                if(game.revealed[x][y] || true)
                {
                    g.drawString(game.data[x][y]+"",px+ppm/4,py+(int)((3/4f)*ppm));
                }
            }
        }

    }
}
