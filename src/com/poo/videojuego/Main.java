package com.poo.videojuego;

import com.poo.videojuego.tresenraya.TresEnRaya;
import com.poo.videojuego.buscaminas.Buscaminas;
import com.poo.videojuego.ahorcado.Ahorcado;
import com.poo.videojuego.pong.Pong;

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
        Pong pong = new Pong(videojuego);
        
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
            videojuego.jugar(pong, new ImageIcon("assets/personajes/teco.png"));
        });
        pong.guardarEventos(()-> 
        {
            videojuego.cerrarPanelJuego();
            videojuego.mostrarEnemigo(new ImageIcon("assets/personajes/ardilla.png"));
            videojuego.notificar("La aventura de Adrian ha terminado");
        });
        
        // iniciar con el primer juego
        videojuego.jugar(tresEnRaya, new ImageIcon("assets/personajes/gato.png"));
    }
}
