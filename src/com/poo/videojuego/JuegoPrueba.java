package com.poo.videojuego;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.net.*;

public class JuegoPrueba extends Juego
{
    public JuegoPrueba() throws IOException
    {
        super("Prueba");
        URL treeUrl = new URL("https://i.pinimg.com/originals/83/3f/3c/833f3c4931a09a2bc7125c37a94de273.jpg");
        BufferedImage myPicture = ImageIO.read(treeUrl);
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setPreferredSize(new Dimension(140, 140));
        getContentPane().add(picLabel, BorderLayout.WEST);
        createTicTacToe();
        
        setSize(800,600);
        setLocation(
                (GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth() / 2) - (getWidth() / 2), 
                (GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight() / 2) - (getHeight() / 2));
        setVisible(true);
    }
    
    private void createTicTacToe()
    {
        JPanel tictactoePanel = new JPanel(new BorderLayout());
        JButton[] botones = new JButton[9];
        JPanel panelBotones = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < botones.length; i++){
            botones[i] = new JButton();
            botones[i].setPreferredSize(new Dimension(80, 80));
            panelBotones.add(botones[i]);
        }
        tictactoePanel.add(panelBotones, BorderLayout.CENTER);
        getContentPane().add(tictactoePanel, BorderLayout.CENTER);
    }
}
