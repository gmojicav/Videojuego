package com.poo.videojuego;

import java.awt.*;
import javax.swing.*;

class Evento1 extends Historia {
    private int hist;

    public Evento1(){
        super(null,"",null,null);
    }
    
    public void fond(int hist){
        this.hist = hist;
        
        switch(hist){
            case 1:
                sf(new ImageIcon("assets/fondos/talado.png").getImage());
                st("En el pleno apojeo de la urbanizacion de la ciudad de Mexico.\nLos gobernadores decidieron talar todo el bosque de Chapultepec para construir edificios.");
                mostrarfondo();
            break;
            
            case 2:
                sf(new ImageIcon("assets/fondos/ardillla.png").getImage());
                st("La tala masiva de arboles provoco la exitncion no solo de vegeracion, sino \ntambien de fauna, felipita la ultima ardilla qu quedaba con vida salio corriendo \nde miedo");
                mostrarfondo();
            break;    
            
            case 3:
                sf(new ImageIcon("assets/fondos/talado.png").getImage());
                st("Esta ardilla triste, sola y si ganas de vivir sguio camiando por Chapultepec o lo que quedaba de el y cuando estaba a punto de ya no seguir mas con su vida un cedreo le hablo, era el ultimo arbol que quedaba y le dijo que aun debia vivir, tenia un proposito en la vida y por eso seguia con vida.");
                sp1(new ImageIcon("assets/personajes/adrian.png").getImage());
                sp2(new ImageIcon("assets/personajes/felipita.png").getImage());
                mostrarfondo();
            break;
            
            case 4:
                sf(new ImageIcon("assets/fondos/talado.png").getImage());
                st("Adrian y Felipita se hicieron grandes amigos y eran las unicas dos especies que vivian en el bosque, hasta que un dia Felipita desaparecio sin dejar rastro y \nadrian se preocupo mucho y recordo que las personas que pasabana por ahi \nhabian comentado que un tejon se habia estado rondando la zona en busca de \ncomida.");
                sp1(new ImageIcon("assets/personajes/adrian.png").getImage());
                mostrarfondo();
            break;
            
            case 5:
                sf(new ImageIcon("assets/fondos/bosque.png").getImage());
                st("Adrian se le ocurrio ir a preguntar  al gato con sandalias sobre ese tejon porque era quien podia saber en donde econtralo, pero el gato le dijo que primero tenia que ganarle en su juego para darle informacion.");
                sp1(new ImageIcon("assets/personajes/adrian.png").getImage());
                sp2(new ImageIcon("assets/personajes/gato.png").getImage());
                mostrarfondo();
                //Juego 1
            break;
            
            case 6:
                sf(new ImageIcon("assets/fondos/bosque.png").getImage());
                st("El que lo podria encontrar es Tomi, cerca de su casa de Tomi viven los tejones y el los conoce a la perfeccion, ve a Xochimilco a encotrate con Toni.");
                sp1(new ImageIcon("assets/personajes/adrian.png").getImage());
                sp2(new ImageIcon("assets/personajes/gato.png").getImage());
                mostrarfondo();
            break;
            case 7:
                sf(new ImageIcon("assets/fondos/bosque.png").getImage());
                st("Adrian emprendio su aventura hacia la locacion de Tomi hasta que llego con el y a lo lejos se dio cuenta que estaba a punto de colgarse asi que corrio y le grito...");
                sp1(new ImageIcon("assets/personajes/adrian.png").getImage());
                mostrarfondo();
                //Juego 2
            break;
            
            case 8:
                sf(new ImageIcon("assets/fondos/bosque.png").getImage());
                st("Tomi muy confuncido por lo que habia dicho Adrian se bajo y le pregunto que \nque habia dicho, Adrian contesto que le pedia ayuda porque su unica amiga se la habia robado un tejon. Tomi muy agradecido por evitar que se colgara le dijo \nque los tejones se habian mudado y hace mucho no los veia pero podia ir a \npreguntarle a Vicente el zorro, los tejones trabajaban en su mina.");
                sp1(new ImageIcon("assets/personajes/adrian.png").getImage());
                sp2(new ImageIcon("assets/personajes/tomi.png").getImage());
                mostrarfondo();
            break;
            case 9:
                sf(new ImageIcon("assets/fondos/bosque.png").getImage());
                st("Adrian siguio su camino hacia las minas a buscar a Vicente. Ya casi llegando a \nlas minas descubrio que el camino estaba lleno de minas.");
                sp1(new ImageIcon("assets/personajes/adrian.png").getImage());
                mostrarfondo();
                //Juego3
            break;
            case 10:
                sf(new ImageIcon("assets/fondos/bosque.png").getImage());
                st("-Lo siento por eso, es que los mapaches vienen a robarse mis diamantes, ¿Que \nnecesita amigo?. dijo Vicente \nAdrian le contesto que estaba buscando a un tejon que habia estado por \nChapultepec, a lo que Vicente respondio que lo conocia, que se llamaba Teco y \nque podia encontrarlo en las mesas de pinpong.");
                sp1(new ImageIcon("assets/personajes/adrian.png").getImage());
                sp2(new ImageIcon("assets/personajes/vicente.png").getImage());
                mostrarfondo();
            break;
            
            case 11:
                sf(new ImageIcon("assets/fondos/bosque.png").getImage());
                st("Por fin encontro al tejon y le dijo que donde tenia a su amiga, el tejon le \ncontesto que no diria nada hasta que le ganara en un juego de pingpong...");
                sp1(new ImageIcon("assets/personajes/adrian.png").getImage());
                sp2(new ImageIcon("assets/personajes/Teco.png").getImage());
                mostrarfondo();
                //Juego 4
            break;
            
            case 12:
                sf(new ImageIcon("assets/fondos/bosque.png").getImage());
                st("Si eres muy bueno arbol, ahora si te dire lo que quieras, dijo Teco. El arbol le dijo que le devolviera a Felipita pero Teco muy confundido no sabia de que hablaba, se disculparon por el malentendido y Adrian trsite volvio a Chaputepec.");
                sp1(new ImageIcon("assets/personajes/adrian.png").getImage());
                sp2(new ImageIcon("assets/personajes/teco.png").getImage());
                mostrarfondo();
            break;
            case 13:
                sf(new ImageIcon("assets/fondos/talado.png").getImage());
                st("Llegando a chapultepec Adria vio a Felipita, muy contento le pregunto que donde estaba y esta le dijo que habia ido por nueces para su inveracion y ya juntos \nvivieron felices para siempre.");
                sp1(new ImageIcon("assets/personajes/adrian.png").getImage());
                sp2(new ImageIcon("assets/personajes/ardilla.png").getImage());
                mostrarfondo();
            break;
            
        }
        
    }
    
    

}