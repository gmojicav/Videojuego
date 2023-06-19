package com.poo.videojuego;

import com.poo.videojuego.tresenraya.TresEnRaya;
import com.poo.videojuego.buscaminas.*;
import com.poo.videojuego.ahorcado.Ahorcado;

import javax.swing.ImageIcon;

public class Main
{
    public static void main(String[] args)
    {
        // crear ventana de juego
        Videojuego videojuego = new Videojuego();
        
        // crear minijuegos
        TresEnRaya tresEnRaya = new TresEnRaya(videojuego);
        Ahorcado ahorcado = new Ahorcado(videojuego);
        Buscaminas buscaminas = new Buscaminas(videojuego);
        
        // configurar las acciones ejecutadas al terminar cada juego
        tresEnRaya.guardarEventos(()->
        {
            videojuego.jugar(ahorcado, new ImageIcon("assets/personajes/tomi.png"));
        });
        ahorcado.guardarEventos(()-> 
        {
            videojuego.jugar(buscaminas, new ImageIcon("assets/personajes/vicente.png"));
        });
        buscaminas.guardarEventos(()-> 
        {
            //videojuego.jugar(ahorcado, new ImageIcon("assets/tresenraya/o.png"));
            javax.swing.JOptionPane.showMessageDialog(videojuego, "La aventura de Adrian ha terminado");
            videojuego.dispose();
        });
        
        // iniciar con el primer juego
        videojuego.jugar(tresEnRaya, new ImageIcon("assets/personajes/gato.png"));
    }
}
