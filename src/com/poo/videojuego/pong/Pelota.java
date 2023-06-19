package com.poo.videojuego.pong;

import java.awt.Color;
import java.awt.Graphics;

public class Pelota
{
    private int xVel;   // velocidad
    private int yVel;
    private int x;      // posicion
    private int y;
    
    private final int TAMANYO = 20;
    private final int MITAD_TAMANYO = TAMANYO / 2;

    public Pelota()
    {
        // colocar en el centro de la pantalla
        x = 150;
        y = 150;
        // lanzar pelota
        xVel = -2;
        yVel = 2;
    }

    public void dibujar(Graphics g)
    {
        g.setColor(Color.black);
        // nota: se resta la mitad del tamaño de la pelota a la posicion de la misma debido a que
        // cuando dibujas un circulo este se dibuja desde su esquina superior izquierda
        g.fillOval(x - MITAD_TAMANYO, y - MITAD_TAMANYO, TAMANYO, TAMANYO);
    }

    public void mover()
    {
        // se mueve la pelota usando las velocidades
        x += xVel;
        y += yVel;

        // si la pelota alcanza el tope o el fondo de la pantalla,
        // esta rebota en el sentido contrario
        if (y < 10 || y > 290)
            rebotarVerticalmente();
    }
    
    public void rebotarVerticalmente()
    {
        yVel = -yVel;
    }
    
    public void rebotarHorizontalmente()
    {
        xVel = -xVel;
    }
    
    public int ubicarHorizontalmente()
    {
        return x;
    }
    
    public int ubicarVerticalmente()
    {
        return y;
    }
}