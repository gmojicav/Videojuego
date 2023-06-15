package com.poo.videojuego.buscaminas;

import com.poo.videojuego.*;

import java.awt.*;
import javax.swing.*;

public class Buscaminas extends Juego
{
    private final JLabel statusbar;
    
    public Buscaminas(EventoJuego terminarJuego)
    {
        super("Buscaminas");
        // crear y agregar el texto de estado de juego a la ventana
        statusbar = new JLabel("");
        add(statusbar, BorderLayout.SOUTH);

        // crear y agregar el tablero a la ventana
        add(new Tablero(this, statusbar));

        // bloquear redimensionamiento de la ventana
        setResizable(false);
        // compactar ventana (hacer que la ventana se ajuste a sus tamaños minimos posibles)
        pack();

        // hacer visible la ventana del juego
        setVisible(true);
        
        // asignar evento de fin de juego
        this.juegoTerminado = terminarJuego;
    }
}
