package com.poo.videojuego;

import java.awt.*;
import javax.swing.*;

public abstract class Juego extends JFrame
{
    protected boolean terminado = false;
    
    protected EventoJuego juegoTerminado;
    
    public Juego(String nombre)
    {
        super(nombre);
        getContentPane().setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Establece el juego en su estado inicial
     */
    protected void nuevoJuego() {}
    
    /**
     * Termina el juego y ejecuta el evento de juego terminado
     */
    public void terminar()
    {
        dispose();
        juegoTerminado.ejecutar();
    }
}
