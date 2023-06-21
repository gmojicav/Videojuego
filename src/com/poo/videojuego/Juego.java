package com.poo.videojuego;

import java.awt.*;
import javax.swing.*;

public abstract class Juego extends JPanel
{
    protected Videojuego videojuego;
    
    protected boolean terminado = false;
    
    protected EventoJuego juegoTerminado;
    
    public Juego(Videojuego videojuego)
    {
        super();
        setLayout(new BorderLayout());
        
        this.videojuego = videojuego;
    }
    
    /**
     * Establece el juego en su estado inicial
     */
    public void nuevoJuego() {}
    
    /**
     * Guarda el evento ejecutado cuando el juego termina
     * @param terminarJuego
     */
    public void guardarEventos(EventoJuego terminarJuego)
    {
        this.juegoTerminado = terminarJuego;
    }
    
    /**
     * Termina el juego y ejecuta el evento de juego terminado
     */
    public void terminar()
    {
        videojuego.subirPuntuacion(1000);
        juegoTerminado.ejecutar();
    }
    
    /**
     * Cierra y destruye el juego y limpia los recursos usados por este
     */
    public void cerrar()
    {
       removeAll();
       getGraphics().dispose();
    }
}
