package com.poo.videojuego;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JuegoPrueba extends Juego
{
    public JuegoPrueba(Videojuego videojuego)
    {
        super(videojuego);
        
        try {
            URL treeUrl = new URL("https://i.pinimg.com/originals/83/3f/3c/833f3c4931a09a2bc7125c37a94de273.jpg");
            BufferedImage myPicture = ImageIO.read(treeUrl);
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setPreferredSize(new Dimension(140, 140));
            add(picLabel, BorderLayout.WEST);
            
            setSize(800,600);
            setLocation(
                    (GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth() / 2) - (getWidth() / 2),
                    (GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight() / 2) - (getHeight() / 2));
            setVisible(true);
        } catch (HeadlessException | IOException ex) {
            Logger.getLogger(JuegoPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
