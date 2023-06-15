package com.poo.videojuego;

import com.poo.videojuego.tresenraya.TresEnRaya;
import com.poo.videojuego.buscaminas.Buscaminas;
import com.poo.videojuego.ahorcado.Ahorcado;

public class Main
{
    public static void main(String[] args)
    {
        // crear juego TresEnRaya primero
        TresEnRaya tresenraya = new TresEnRaya(() ->
        {
            // crear juego Buscaminas al terminar TresEnRaya
            Buscaminas buscaminas = new Buscaminas(()->
            {
                // ---[ "A H O R C A D O"   E S   U N   P R O T O T I P O ]---
                // crear juego Ahorcado al terminar Buscaminas
                Ahorcado ahorcado = new Ahorcado(()->
                {
                    javax.swing.JOptionPane.showMessageDialog(null, "La aventura de Adrian ha terminado");
                });
            });
        });
    }
}
