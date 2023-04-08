package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener
{
    int WIDTH = 1000;
    int HEIGHT = 750;
    int DELAY = 20;
    double MOVE_PER_TICK = 5;

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;

    Timer timer;

    Color backgroundColor = Color.BLACK;

    Player player;

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
    }

    public void tick()
    {
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
    }
}
