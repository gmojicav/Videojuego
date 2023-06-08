package com.poo.videojuego;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Gato extends Juego
{
    private final JLabel ganadorTxt;
    private final JButton[] botones = new JButton[9];
    
    private final String LETRA_JUGADOR = "X";
    private final String LETRA_MAQUINA = "O";
    
    private boolean ganadorJugador = false;
    
    public Gato(EventoJuego terminarJuego)
    {
        // crear ventana del juego
        super("Gato");
        // crear cuadricula 3x3 de botones
        JPanel panelBotones = new JPanel(new GridLayout(3, 3));
        // crear texto de ganador
        ganadorTxt = new JLabel();
        ganadorTxt.setAlignmentX(CENTER_ALIGNMENT);
        ganadorTxt.setVisible(false);
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
        getContentPane().add(panelBotones, BorderLayout.CENTER);
        getContentPane().add(ganadorTxt, BorderLayout.SOUTH);
        
        // asignar tamaño de ventana
        setSize(240,240);
        // hacer visible la ventana
        setVisible(true);
        
        nuevoJuego();
        
        // asignar evento de fin de juego
        this.juegoTerminado = terminarJuego;
    }
    
    @Override
    protected final void nuevoJuego()
    {
        for (int i = 0; i < botones.length; i++)
        {
            botones[i].setEnabled(true);
            botones[i].setText("");
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
        for (int i = 0; i < botones.length; i++)
        {
            botones[i].setEnabled(false);
        }
        // guardar estado del juego
        terminado = true;
        
        // llamar metodo "terminar()" original
        if (ganadorJugador)
            super.terminar();
        else
            nuevoJuego();
    }
    
    /**
     * Ejecuta las acciones necesarias cuando un boton de la cuadricula
     * es presionado por algun jugador (real/maquina)
     * @param boton Boton presionado
     * @param jugador El jugador que presiona el boton
     */
    private void presionarBoton(JButton boton, String jugador)
    {
        // desactivar boton y asignar la letra del jugador o maquina al boton
        boton.setEnabled(false);
        boton.setText(jugador);
        
        // si existe un tres en raya para el jugador o maquina,
        // el juego se considera ganado por el jugador o la maquina
        if (comprobarTresEnRaya(jugador))
        {
            ganadorTxt.setVisible(true);
            ganadorTxt.setText("La " + jugador + " gana");
            
            ganadorJugador = jugador.equals(LETRA_JUGADOR);
            terminar();
        }
        // sin embargo, si no existen mas botones disponibles para presionar,
        // el juego se considera como empate y termina
        else if (comprobarBloqueo())
        {
            ganadorTxt.setVisible(true);
            ganadorTxt.setText("Empate");
            terminar();
        }
    }
    
    /**
     * Elije aleatoriamente un boton para que el jugador maquina lo presione
     */
    private void turnoMaquina()
    {
        // si el juego ya ha terminado, la maquina no jugará
        if (terminado)
            return;
        
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
    
    /**
     * Comprueba si en la cuadricula aun hay botones disponibles para ser
     * presionados por algun jugador
     * @return true si aun existen botones presionables; de lo contrario, false
     */
    private boolean comprobarBloqueo()
    {
        int botonesDisponibles = 0;
        
        // buscar en todos los botones si existen algun boton sin presionar
        for (int i = 0; i < botones.length; i++)
        {
            if (botones[i].isEnabled())
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
        if (botones[0].getText().equals(value)&& botones[1].getText().equals(value)
                && botones[2].getText().equals(value))
        {
            return true;
        }
        // ---
        // XXX
        // ---
        else if (botones[3].getText().equals(value) && botones[4].getText().equals(value)
                && botones[5].getText().equals(value))
        {
           return true;
        }
        // ---
        // ---
        // XXX
        else if (botones[6].getText().equals(value) && botones[7].getText().equals(value)
                && botones[8].getText().equals(value))
        {
           return true;
        }
        // X--
        // X--
        // X--
        else if (botones[0].getText().equals(value) && botones[3].getText().equals(value)
                && botones[6].getText().equals(value))
        {
           return true;
        }
        // -X-
        // -X-
        // -X-
        else if (botones[1].getText().equals(value) && botones[4].getText().equals(value)
                && botones[7].getText().equals(value))
        {
           return true;
        }
        // --X
        // --X
        // --X
        else if (botones[2].getText().equals(value) && botones[5].getText().equals(value)
                && botones[8].getText().equals(value))
        {
           return true;
        }
        // X--
        // -X-
        // --X
        else if (botones[0].getText().equals(value) && botones[4].getText().equals(value)
                && botones[8].getText().equals(value))
        {
           return true;
        }
        // --X
        // -X-
        // X--
        else return botones[2].getText().equals(value) && botones[4].getText().equals(value)
                && botones[6].getText().equals(value);
    }
}
