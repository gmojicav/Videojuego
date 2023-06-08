package com.poo.videojuego;

import com.poo.videojuego.buscaminas.Buscaminas;

public class Main
{
    public static void main(String[] args)
    {
        // crear juego Gato primero
        Gato gato = new Gato(() ->
        {
            // crear juego Buscaminas al terminar Gato
            Buscaminas buscaminas = new Buscaminas();
        });
    }
}
