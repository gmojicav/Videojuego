package com.poo.videojuego.buscaminas;

import com.poo.videojuego.Juego;

import java.awt.*;
import javax.swing.*;

public class Buscaminas extends Juego
{
    private JLabel statusbar;
    
    public Buscaminas()
    {
        super("Buscaminas");
        statusbar = new JLabel("");
        add(statusbar, BorderLayout.SOUTH);

        add(new Tablero(statusbar));

        setResizable(false);
        pack();

        setVisible(true);
    }
}
