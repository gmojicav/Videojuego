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
        
        // crear historia
        Cinematica cinematicaIntro1
                = new Cinematica(new ImageIcon("assets/fondos/talado.png").getImage(),
                "En el pleno apojeo de la urbanizacion de la Ciudad de Mexico. Los gobernadores decidieron talar todo el bosque de Chapultepec para construir más edificios.",
                null,
                null);
        Cinematica cinematicaIntro2 = new Cinematica(new ImageIcon("assets/fondos/ardillla.png").getImage(),
                "La tala masiva de arboles provocó la extincion masiva no solo de flora, sino tambien de fauna. Y Felipita, la ultima ardilla que quedaba con vida, salio corriendo de miedo",
                null,
                null);
        Cinematica cinematicaIntro3 = new Cinematica(new ImageIcon("assets/fondos/talado.png").getImage(),
                "Esta ardilla triste, sola y sin ganas de vivir siguio caminando por Chapultepec, o lo que quedaba de el, y cuando estaba a punto de ya no querer seguir mas con su vida, un cedreo le habló, era el ultimo arbol que quedaba y le dijo que aun debia vivir, que seguro tenia un proposito en la vida y por eso seguia con vida.",
                new ImageIcon("assets/personajes/adrian.png").getImage(),
                new ImageIcon("assets/personajes/felipita.png").getImage());
        Cinematica cinematicaIntro4 = new Cinematica(new ImageIcon("assets/fondos/talado.png").getImage(),
                "Adrian y Felipita se hicieron grandes amigos y eran los unicos de su especie que aún vivian en el bosque. Pero un dia Felipita desaparecio sin dejar rastro, Adrian se preocupo mucho pero recordó que las personas que pasabana por ahí habían comentado que un tejón habia estado rondando la zona en busca de comida.",
                new ImageIcon("assets/personajes/adrian.png").getImage(),
                null);
        Cinematica cinematicaIntro5 = new Cinematica(new ImageIcon("assets/fondos/bosque.png").getImage(),
                "A Adrian se le ocurrio ir a preguntar al gato con sandalias sobre ese tejón, porque era quien podia saber en donde encontralo, pero el gato le dijo que primero tenia que ganarle en su juego para darle información.",
                new ImageIcon("assets/personajes/adrian.png").getImage(),
                new ImageIcon("assets/personajes/gato.png").getImage());
        Cinematica cinematicaIntro6 = new Cinematica(new ImageIcon("assets/fondos/bosque.png").getImage(),
                "\"El que lo podria encontrar es Tomi\", dijo el gato, \"cerca de la casa de Tomi viven los tejones y el los conoce a la perfección, ve a Xochimilco a encotrarte con Tomi\".",
                new ImageIcon("assets/personajes/adrian.png").getImage(),
                new ImageIcon("assets/personajes/gato.png").getImage());
        Cinematica cinematicaIntro7 = new Cinematica(new ImageIcon("assets/fondos/bosque.png").getImage(),
                "Adrian emprendio su aventura hacia la locacion de Tomi. Pero cuando llego, vio a lo lejos y se dio cuenta que estaba a punto de colgarse asi que corrió y le gritó...",
                new ImageIcon("assets/personajes/adrian.png").getImage(),
                null);
        Cinematica cinematicaIntro8 = new Cinematica(new ImageIcon("assets/fondos/bosque.png").getImage(),
                "Tomi muy confuncido por lo que habia dicho Adrian se bajó y le preguntó que que habia dicho, Adrian contestó que quería ayuda porque su unica amiga se la habia robado un tejon. Tomi muy agradecido por evitar que se colgara le dijo que los tejones se habian mudado y hace mucho no los veia pero podia ir a preguntarle a Vicente el zorro, ya que los tejones trabajaban en su mina.",
                new ImageIcon("assets/personajes/adrian.png").getImage(),
                new ImageIcon("assets/personajes/tomi.png").getImage());
        Cinematica cinematicaIntro9 = new Cinematica(new ImageIcon("assets/fondos/bosque.png").getImage(),
                "Adrian siguio su camino hacia las minas a buscar a Vicente. Pero cuando estaba por llegar a las minas descubrió que el camino estaba lleno de minas. Ironico, ¿a poco no?.",
                new ImageIcon("assets/personajes/adrian.png").getImage(),
                null);
        Cinematica cinematicaIntro10 = new Cinematica(new ImageIcon("assets/fondos/bosque.png").getImage(),
                "\"Lo siento por eso, los mapaches vienen seguido a querer robar mis diamantes y necesitaba una forma de mantenerlos a raya, ¿Que necesita amigo?\", dijo Vicente. Adrian le contestó que estaba buscando a un tejon que habia estado por Chapultepec, a lo que Vicente respondió que lo conocia, que se llamaba Teco y que podia encontrarlo en las mesas de ping pong.",
                new ImageIcon("assets/personajes/adrian.png").getImage(),
                new ImageIcon("assets/personajes/vicente.png").getImage());
        Cinematica cinematicaIntro11 = new Cinematica(new ImageIcon("assets/fondos/bosque.png").getImage(),
                "Cuando por fin encontró al tejon le dijo que donde tenia a su amiga, el tejón le contestó que no diría nada hasta que le ganara en un juego de ping pong...",
                new ImageIcon("assets/personajes/adrian.png").getImage(),
                new ImageIcon("assets/personajes/Teco.png").getImage());
        Cinematica cinematicaIntro12 = new Cinematica(new ImageIcon("assets/fondos/bosque.png").getImage(),
                "\"Si, eres muy bueno arbol, te dire lo que necesites\", dijo Teco. El arbol le dijo que le devolviera a Felipita pero Teco muy confundido no sabia de que hablaba, así que se disculparon por el malentendido y Adrian triste volvio a Chapultepec.",
                new ImageIcon("assets/personajes/adrian.png").getImage(),
                new ImageIcon("assets/personajes/Teco.png").getImage());
        Cinematica cinematicaIntro13 = new Cinematica(new ImageIcon("assets/fondos/talado.png").getImage(),
                "Llegando a Chapultepec, Adrián vio a Felipita, y muy contento le pregunto que donde habia estado y esta le dijo que habia ido por nueces para su hibernación. Después de eso, ellos vivieron felices para siempre.",
                new ImageIcon("assets/personajes/adrian.png").getImage(),
                new ImageIcon("assets/personajes/ardilla.png").getImage());
        
        
        // configurar las acciones ejecutadas al terminar cada cinematica y juego
        cinematicaIntro1.guardarEventoCerrar(() ->
        {
            cinematicaIntro2.mostrar();
        });
        cinematicaIntro2.guardarEventoCerrar(() ->
        {
            cinematicaIntro3.mostrar();
        });
        cinematicaIntro3.guardarEventoCerrar(() ->
        {
            cinematicaIntro4.mostrar();
        });
        cinematicaIntro4.guardarEventoCerrar(() ->
        {
            cinematicaIntro5.mostrar();
        });
        cinematicaIntro5.guardarEventoCerrar(() ->
        {
            // iniciar con el primer juego
            videojuego.jugar(tresEnRaya, new ImageIcon("assets/personajes/gato.png"));
        });
        tresEnRaya.guardarEventos(()->
        {
            cinematicaIntro6.mostrar();
        });
        cinematicaIntro6.guardarEventoCerrar(() ->
        {
            cinematicaIntro7.mostrar();
        });
        cinematicaIntro7.guardarEventoCerrar(() ->
        {
            videojuego.jugar(ahorcado, new ImageIcon("assets/personajes/tomi.png"));
        });
        ahorcado.guardarEventos(()-> 
        {
            cinematicaIntro8.mostrar();
        });
        cinematicaIntro8.guardarEventoCerrar(() ->
        {
            cinematicaIntro9.mostrar();
        });
        cinematicaIntro9.guardarEventoCerrar(() ->
        {
            videojuego.jugar(buscaminas, new ImageIcon("assets/personajes/vicente.png"));
        });
        buscaminas.guardarEventos(()-> 
        {
            cinematicaIntro10.mostrar();
        });
        cinematicaIntro10.guardarEventoCerrar(() ->
        {
            cinematicaIntro11.mostrar();
        });
        cinematicaIntro11.guardarEventoCerrar(() ->
        {
            videojuego.jugar(pong, new ImageIcon("assets/personajes/teco.png"));
        });
        pong.guardarEventos(()-> 
        {
            videojuego.cerrarPanelJuego();
            cinematicaIntro12.mostrar();
        });
        cinematicaIntro12.guardarEventoCerrar(() ->
        {
            cinematicaIntro13.mostrar();
        });
        cinematicaIntro13.guardarEventoCerrar(() ->
        {
            videojuego.mostrarEnemigo(new ImageIcon("assets/personajes/ardilla.png"));
            videojuego.notificar("¡Gracias por jugar!");
        });
        
        // iniciar juego
        cinematicaIntro1.mostrar();
    }
}
