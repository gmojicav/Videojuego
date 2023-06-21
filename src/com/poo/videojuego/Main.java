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
         //Crear historia
        Evento1 evento1 = new Evento1();
        
        //Inicia historia
        int histo=0,i;
        for(i=1;i<18;i++){
            switch(i){
                case 1: 
                    histo=1;
                    evento1.fond(histo);
                break;
                case 2: 
                    histo=2;
                    evento1.fond(histo);
                break;
                case 3: 
                    histo=3;
                    evento1.fond(histo);
                break;
                case 4: 
                    histo=4;
                    evento1.fond(histo);
                break;
                case 5: 
                    histo=5;
                    evento1.fond(histo);
                break;
                case 6: 
                    videojuego.jugar(tresEnRaya, new ImageIcon("assets/personajes/gato.png"));
                     tresEnRaya.guardarEventos(()->
                    {
                        videojuego.jugar(ahorcado, new ImageIcon("assets/personajes/tomi.png"));
                    });
                break;
                case 7: 
                    histo=6;
                    evento1.fond(histo);
                break;
                case 8: 
                    histo=7;
                    evento1.fond(histo);
                break;
                case 9: 
                    ahorcado.guardarEventos(()-> 
                    {
                        videojuego.jugar(buscaminas, new ImageIcon("assets/personajes/vicente.png"));
                    });
                break;
                case 10: 
                    histo=8;
                    evento1.fond(histo);
                break;
                case 11: 
                    histo=9;
                    evento1.fond(histo);
                break;
                case 12: 
                    buscaminas.guardarEventos(()-> 
                    {
                        videojuego.jugar(pong, new ImageIcon("assets/personajes/teco.png"));
                    });
                break;
                case 13: 
                    histo=10;
                    evento1.fond(histo);
                break;
                case 14: 
                    histo=11;
                    evento1.fond(histo);
                break;
                case 15: 
                    pong.guardarEventos(()-> 
                    {
                        videojuego.cerrarPanelJuego();
                    });
                break;
                case 16: 
                    histo=12;
                    evento1.fond(histo);
                break;
                case 17: 
                    histo=13;
                    evento1.fond(histo);
                break;
            }

    
         // configurar las acciones ejecutadas al terminar cada juego
           
            
            
            
            // iniciar con el primer juego
            
        }
    }
}
