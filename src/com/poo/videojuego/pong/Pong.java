package com.poo.videojuego.pong;

import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

import com.poo.videojuego.*;

public class Pong extends Juego implements ActionListener
{
    private final Timer timer;
    
    private Pelota pelota;
    private Raqueta jugador1;
    private Raqueta jugador2;
    
    private final EventosTeclado eventosTeclado;
    
    public Pong(Videojuego videojuego)
    {
        super(videojuego);
        timer = new Timer(10, this);
        
        eventosTeclado = new EventosTeclado();
        videojuego.addKeyListener(eventosTeclado);
    }
    
    @Override
    public final void nuevoJuego()
    {
        videojuego.notificar("Pong con Teco");
        
        pelota = new Pelota();
        jugador1 = new Raqueta(1);
        jugador2 = new Raqueta(2);
        timer.stop();
        
        terminado = false;
    }
    
    @Override
    public final void cerrar()
    {
        timer.stop();
        videojuego.removeKeyListener(eventosTeclado);
        
        super.cerrar();
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.drawRect(35, 0, 230, 300);
        pelota.dibujar(g);
        jugador1.dibujar(g);
        jugador2.dibujar(g);
        g.setColor(Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        pelota.mover();
        actualizarFisicasPelota();
        detectarGanador();
        
        repaint();
    }

    private void detectarGanador()
    {
        // si la pelota cae del lado del jugador 1, el juego vuelve a empezar
        if (pelota.ubicarHorizontalmente() < 30)
        {
            nuevoJuego();
            videojuego.bajarPuntuacion(100);
        }
        // si la pelota cae del lado el jugador 2, el juego termina
        if (pelota.ubicarHorizontalmente() > 270)
        {
            // se darán 2500 puntos en total al ganar,
            // ya que por defecto siempre se dan 1000 puntos
            videojuego.subirPuntuacion(1500);
            terminar();
        }
    }

    private void actualizarFisicasPelota()
    {
        if (pelota.ubicarHorizontalmente() < 45
                && (jugador1.ubicar() < pelota.ubicarVerticalmente())
                && (jugador1.ubicar()+ 80 > pelota.ubicarVerticalmente()))
        {
            pelota.rebotarHorizontalmente();
        }
        if (pelota.ubicarHorizontalmente() > 255)
        {
            if ((jugador2.ubicar() < pelota.ubicarVerticalmente())
                    && (jugador2.ubicar() + 80 > pelota.ubicarVerticalmente()))
            {
                pelota.rebotarHorizontalmente();
            }
        }
    }
    
    private class EventosTeclado extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            // si el juego no está actualizandose, 
            // lo empezará a hacer al presionar cualquier tecla
            if (!timer.isRunning())
                timer.start();
            
            // jugador 1
            if (e.getKeyCode() == KeyEvent.VK_W)
            {
                jugador1.mover(-1);
            }

            if (e.getKeyCode() == KeyEvent.VK_S)
            {
                jugador1.mover(1);
            }

            // jugador 2
            if (e.getKeyCode() == KeyEvent.VK_UP)
            {
                jugador2.mover(-1);
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN)
            {
                jugador2.mover(1);
            }
        }
    }
}
