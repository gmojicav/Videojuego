package com.poo.videojuego.tresenraya;

import com.poo.videojuego.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class TresEnRaya extends Juego
{
    private final JButton[] botones = new JButton[9];
    
    private final String[] datos = new String[9];
    
    private final String LETRA_JUGADOR = "arbol";
    private final String LETRA_MAQUINA = "gato";
    
    private boolean ganadorJugador = false;
    
    public TresEnRaya(Videojuego videojuego)
    {
        super(videojuego);
        // crear cuadricula 3x3 de botones
        JPanel panelBotones = new JPanel(new GridLayout(3, 3));
        // crear texto de status de partida
        // rellenar cuadricula de botones
        for (int i = 0; i < botones.length; i++)
        {
            botones[i] = new JButton();
            panelBotones.add(botones[i]);
            // accion al presionar un boton
            botones[i].addActionListener((ActionEvent evento) ->
            {
                // convertir fuente de "evento" en JButton
                JButton botonPresionado = (JButton)evento.getSource();
                // presionar boton como el jugador
                presionarBoton(botonPresionado, LETRA_JUGADOR);
                // ejecutar turno de la maquina
                turnoMaquina();
            });
        }
        // agregar cuadricula de botones y el texto de ganador a la ventana
        add(panelBotones, BorderLayout.CENTER);
        //add(textoStatus, BorderLayout.SOUTH);
    }
    
    @Override
    public final void nuevoJuego()
    {
        for (int i = 0; i < datos.length; i++)
        {
            // guarda un valor vacio en una casilla del tablero
            datos[i] = "";
            // activa un boton y le asigna un icono por defecto
            botones[i].setEnabled(true);
            botones[i].setIcon(crearIcono("assets/tresenraya/vacio.png"));
        }
        
        terminado = false;
    }
    
    /**
     * Bloque la posibilidad de seguir jugando y,
     * dependiendo del resultado de la partida,
     * el juego se reiniciará o terminará
     */
    @Override
    public void terminar()
    {
        // desactivar todos los botones
        for (JButton boton : botones)
        {
            boton.setEnabled(false);
        }
        // guardar estado del juego
        terminado = true;
        
        // si el jugador ganó, llamar método "terminar()" original
        if (ganadorJugador)
            super.terminar();
        else // de lo contrario, reiniciar el juego
        {
            nuevoJuego();
        }
    }
    
    /**
     * Ejecuta las acciones necesarias cuando un boton de la cuadricula
     * es presionado por algun jugador (real/maquina)
     * @param boton Boton presionado
     * @param jugador El jugador que presiona el boton
     */
    private void presionarBoton(JButton boton, String jugador)
    {
        // guarda la letra presionada en la lista de datos basandose en el boton presionado
        for (int i = 0; i < datos.length; i++)
        {
            if (botones[i] == boton)
            {
                datos[i] = jugador;
            }
        }
        
        // desactivar boton y asignar el icono del jugador o maquina al boton
        boton.setEnabled(false);
        boton.setIcon(crearIcono("assets/tresenraya/" + jugador +".png"));
        
        // si existe un tres en raya para el jugador o maquina,
        // el juego se considera ganado por el jugador o la maquina
        if (comprobarTresEnRaya(jugador))
        {
            videojuego.notificar(("El " + jugador + " gana"));
            
            ganadorJugador = jugador.equals(LETRA_JUGADOR);
            terminar();
        }
        // sin embargo, si no existen mas botones disponibles para presionar,
        // el juego se considera como empate y termina
        else if (comprobarBloqueo())
        {
            videojuego.notificar("Empate");
            terminar();
        }
    }
    
    /**
     * Elige una celda para que el jugador de la máquina realice su turno,
     * tratando de hacer un tres en raya si es posible.
     */
    private void turnoMaquina()
    {
        // si el juego ya ha terminado, la máquina no jugará
        if (terminado)
            return;

        // berificar si hay una celda donde la máquina pueda hacer un tres en raya
        int celdaGanadora = buscarCeldaGanadora(LETRA_MAQUINA);
        if (celdaGanadora != -1)
        {
            // si se encontró una celda ganadora, presionarla
            presionarBoton(botones[celdaGanadora], LETRA_MAQUINA);
        }
        else
        {
            // si no se encontró una celda ganadora,
            // escoger boton aleatorio para el turno de la maquina
            Random random = new Random();
            int botonPresionado = random.nextInt(botones.length);
            // si el boton escogido ya está presionado,
            // se escogerá un boton nuevo hasta que se encuentre uno disponible
            while(!botones[botonPresionado].isEnabled())
            {
                botonPresionado = random.nextInt(botones.length);
            }

            // presionar el boton escogido como la maquina
            presionarBoton(botones[botonPresionado], LETRA_MAQUINA);
        }
    }
    
    /**
    * Busca una celda donde el jugador especificado pueda hacer un tres en raya.
    * @param jugador El jugador a comprobar si puede hacer un tres en raya.
    * @return El índice de la celda donde el jugador puede hacer un tres en raya, o -1 si no hay ninguna.
    */
    private int buscarCeldaGanadora(String jugador)
    {
        // Comprobar filas
        for (int fila = 0; fila < 3; fila++)
        {
            int celda1 = fila * 3;
            int celda2 = celda1 + 1;
            int celda3 = celda2 + 1;

            // **!
            if (datos[celda1].equals(jugador)
                    && datos[celda2].equals(jugador)
                    && datos[celda3].isEmpty())
            {
                return celda3;
            }
            // *!*
            if (datos[celda1].equals(jugador)
                    && datos[celda3].equals(jugador)
                    && datos[celda2].isEmpty())
            {
                return celda2;
            }
            // !**
            if (datos[celda2].equals(jugador)
                    && datos[celda3].equals(jugador)
                    && datos[celda1].isEmpty())
            {
                return celda1;
            }
        }

        // Comprobar columnas
        for (int columna = 0; columna < 3; columna++)
        {
            int celda1 = columna;
            int celda2 = celda1 + 3;
            int celda3 = celda2 + 3;

            // *
            // *
            // !
            if (datos[celda1].equals(jugador)
                    && datos[celda2].equals(jugador)
                    && datos[celda3].isEmpty())
            {
                return celda3;
            }
            // *
            // !
            // *
            if (datos[celda1].equals(jugador)
                    && datos[celda3].equals(jugador)
                    && datos[celda2].isEmpty())
            {
                return celda2;
            }
            // !
            // *
            // *
            if (datos[celda2].equals(jugador)
                    && datos[celda3].equals(jugador)
                    && datos[celda1].isEmpty())
            {
                return celda1;
            }
        }

        // Comprobar diagonales
        
        // *--
        // -*-
        // --!
        if (datos[0].equals(jugador)
                && datos[4].equals(jugador)
                && datos[8].isEmpty())
        {
            return 8;
        }
        // *--
        // -!-
        // --*
        if (datos[0].equals(jugador)
                && datos[8].equals(jugador)
                && datos[4].isEmpty())
        {
            return 4;
        }
        // !--
        // -*-
        // --*
        if (datos[4].equals(jugador)
                && datos[8].equals(jugador)
                && datos[0].isEmpty())
        {
            return 0;
        }
        // --*
        // -*-
        // !--
        if (datos[2].equals(jugador)
                && datos[4].equals(jugador)
                && datos[6].isEmpty())
        {
            return 6;
        }
        // --*
        // -!-
        // *--
        if (datos[2].equals(jugador)
                && datos[6].equals(jugador)
                && datos[4].isEmpty())
        {
            return 4;
        }
        // --!
        // -*-
        // *--
        if (datos[4].equals(jugador)
                && datos[6].equals(jugador)
                && datos[2].isEmpty())
        {
            return 2;
        }

        return -1; // No se encontró una celda ganadora
    }
    
    /**
     * Comprueba si en la cuadricula aun hay botones disponibles para ser
     * presionados por algun jugador
     * @return true si aun existen botones presionables; de lo contrario, false
     */
    private boolean comprobarBloqueo()
    {
        int botonesDisponibles = 0;
        
        // buscar en todos los botones si existen algun boton sin presionar
        for (JButton boton : botones)
        {
            if (boton.isEnabled())
            {
                botonesDisponibles++;
            }
        }
        // si no existen botones disponibles,
        // se considera que el juego se ha bloqueado
        return botonesDisponibles == 0;
    }
    
    /**
     * Comprueba si en la cuadricula se ha hecho un tres en raya
     * por algun jugador
     * @param value El jugador a comprobar si hizo tres en raya
     * @return true si el jugador hizo un tres en raya; de lo contrario, false
     */
    private boolean comprobarTresEnRaya(String value)
    {
        // XXX
        // ---
        // ---
        if (datos[0].equals(value)&& datos[1].equals(value)
                && datos[2].equals(value))
        {
            return true;
        }
        // ---
        // XXX
        // ---
        else if (datos[3].equals(value) && datos[4].equals(value)
                && datos[5].equals(value))
        {
           return true;
        }
        // ---
        // ---
        // XXX
        else if (datos[6].equals(value) && datos[7].equals(value)
                && datos[8].equals(value))
        {
           return true;
        }
        // X--
        // X--
        // X--
        else if (datos[0].equals(value) && datos[3].equals(value)
                && datos[6].equals(value))
        {
           return true;
        }
        // -X-
        // -X-
        // -X-
        else if (datos[1].equals(value) && datos[4].equals(value)
                && datos[7].equals(value))
        {
           return true;
        }
        // --X
        // --X
        // --X
        else if (datos[2].equals(value) && datos[5].equals(value)
                && datos[8].equals(value))
        {
           return true;
        }
        // X--
        // -X-
        // --X
        else if (datos[0].equals(value) && datos[4].equals(value)
                && datos[8].equals(value))
        {
           return true;
        }
        // --X
        // -X-
        // X--
        else return datos[2].equals(value) && datos[4].equals(value)
                && datos[6].equals(value);
    }
    
    /**
     * Crea una imagen que se puede asignar a un boton
     * @param ruta La ruta de acceso al archivo de imagen a usar
     * @return La imagen creada a partir del archivo dado
     */
    private ImageIcon crearIcono(String ruta)
    {
        // crear imagen a partir del archivo dado
        ImageIcon icono = new ImageIcon(ruta);
        // reescalar la imagen para quedar con el tamaño de los botones
        Image imagenIcono = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenIcono);
    }
}
