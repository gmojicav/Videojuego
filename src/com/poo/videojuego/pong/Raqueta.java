package com.poo.videojuego.pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class Raqueta
{
    private int x;
    private int y;
    private int ancho = 20;
    private int alto = 80;
    private int jugador;
    private final Random rand; 
    
    public Raqueta(int jugador)
    {
        this.jugador = jugador;
        rand = new Random();
        
        // se coloca la raqueta en el lado izquierdo o derecho dependiendo del jugador
        x = jugador == 1 ? 15 : 265;
        y = 130;
    }

    public void dibujar(Graphics g)
    {
        g.setColor(Color.pink);
        g.fillRect(x, y, ancho, alto);
        g.setColor(Color.white);
    }

    public void mover(int direccion)
    {
        int direccionFinal = direccion * 10;
        
        if (y + direccionFinal > 220) // limitar movimiento al fondo de la pantalla
        {
            y = 220;
        }
        else if (y + direccionFinal < 10) // limitar movimiento al tope de la pantalla
        {
            y = 0;
        }
        else
        {
            y += direccionFinal;
        }
    }
    
    public void moverIA(Pelota pelota)
    {
        y = pelota.ubicarVerticalmente() - rand.nextInt(100);
    }
    
    public int ubicar()
    {
        return y;
    }
}
