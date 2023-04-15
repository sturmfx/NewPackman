package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener
{
    int WIDTH = 1000;
    int HEIGHT = 750;
    int DELAY = 20;
    double MOVE_PER_TICK = 5;
    int MAX_BOTS_ON_ANY_MOMENT = 3;

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;

    Timer timer;

    Color backgroundColor = Color.BLACK;

    Player player;

    ArrayList<Bot> bots = new ArrayList<Bot>();

    int botsEatenByPlayer = 0;
    double secondsPerBot = 1;
    Instant startOfGame;
    Instant currentTime;

    public GamePanel()
    {
        player = new Player(WIDTH/2, HEIGHT/2);
        addKeyListener(new PlayerKeyboardAdapter());
        setBackground(backgroundColor);
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.setTitle("PACKMAN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel.timer = new Timer(panel.DELAY, panel);
        panel.timer.start();
        panel.startOfGame = Instant.now();
    }

    public void tick()
    {
        spawnBot();
        checkPlayerBotCollision();
        limitPlayerOnMap();
        if(up)
        {
            player.setY(player.getY() - MOVE_PER_TICK);
        }
        if(down)
        {
            player.setY(player.getY() + MOVE_PER_TICK);
        }
        if(right)
        {
            player.setX(player.getX() + MOVE_PER_TICK);
        }
        if(left)
        {
            player.setX(player.getX() - MOVE_PER_TICK);
        }
    }

    public void spawnBot()
    {
        if(bots.size() < 3)
        {
            Bot b1 = Bot.createBot(WIDTH, HEIGHT, 10);
            bots.add(b1);
        }
    }

    public void checkPlayerBotCollision()
    {
        for(Bot b : bots)
        {
            double c = Math.sqrt((player.getX() - b.getX())*(player.getX() - b.getX()) + (player.getY() - b.getY())*(player.getY() - b.getY()));
            double radiusSum = player.getWidth()/2 + b.getRadius();
            if(c < radiusSum)
            {
                b.setEaten(true);
                botsEatenByPlayer++;
                currentTime = Instant.now();
                Duration d = Duration.between(startOfGame, currentTime);
                secondsPerBot = d.getSeconds()/botsEatenByPlayer;
            }
        }
        bots.removeIf(b -> b.isEaten());
    }

    public void limitPlayerOnMap()
    {
        if(player.getX() > WIDTH)
        {
            player.setX(WIDTH);
        }
        if(player.getX() < 0)
        {
            player.setX(0);
        }
        if(player.getY() > HEIGHT)
        {
            player.setY(HEIGHT);
        }
        if(player.getY() < 0)
        {
            player.setY(0);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        tick();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(player.color);
        int realX = (int) (player.getX() - player.getWidth()/2);
        int realY = (int) (player.getY() - player.getHeight()/2);
        g.fillOval(realX, realY, player.getWidth(), player.getHeight());

        for(Bot b : bots)
        {
            g.setColor(b.color);
            realX = (int) (b.getX() - b.getRadius());
            realY = (int) (b.getY() - b.getRadius());
            g.fillOval(realX, realY, (int) (b.getRadius() * 2), (int) (b.getRadius() * 2));
        }
        g.setColor(Color.YELLOW);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString("BOTS EATEN: " + String.valueOf(botsEatenByPlayer), WIDTH - 250, 20);
        g.drawString("SECOND PER BOT: " + String.valueOf(secondsPerBot), WIDTH - 250, 40);
    }

    private class PlayerKeyboardAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            int key_code = e.getKeyCode();

            if(key_code == KeyEvent.VK_W)
            {
                up = true;
                down = false;
            }

            if(key_code == KeyEvent.VK_S)
            {
                down = true;
                up = false;
            }

            if(key_code == KeyEvent.VK_A)
            {
                left = true;
                right = false;
            }

            if(key_code == KeyEvent.VK_D)
            {
                right = true;
                left = false;
            }
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            int key_code = e.getKeyCode();
            if(key_code == KeyEvent.VK_W)
            {
                up = false;
            }

            if(key_code == KeyEvent.VK_S)
            {
                down = false;
            }

            if(key_code == KeyEvent.VK_A)
            {
                left = false;
            }

            if(key_code == KeyEvent.VK_D)
            {
                right = false;
            }
        }
    }
}
