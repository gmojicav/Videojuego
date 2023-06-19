package com.poo.videojuego.buscaminas;

import com.poo.videojuego.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Random;

public class Buscaminas extends Juego
{
    private final int NUMERO_IMAGENES = 13;
    private final int TAMANO_CELDA = 15;

    private final int ID_CELDA_VACIA = 0;
    private final int ID_CELDA_MINADA = 9;
    private final int ID_CELDA_CUBIERTA = 10;
    private final int ID_CELDA_MARCADA = 11;
    private final int ID_CELDA_MAL_MARCADA = 12;
    
    private final int CELDA_MINADA_CUBIERTA = ID_CELDA_MINADA + ID_CELDA_CUBIERTA;
    private final int CELDA_MINADA_MARCADA = CELDA_MINADA_CUBIERTA + ID_CELDA_CUBIERTA;

    private final int TOTAL_MINAS = 4;
    private final int TOTAL_FILAS = 20;
    private final int TOTAL_COLUMNAS = 20;

    private final int ANCHO = TOTAL_COLUMNAS * TAMANO_CELDA + 1;
    private final int ALTO = TOTAL_FILAS * TAMANO_CELDA + 1;

    private int[] campo;
    private int minasFaltantes;
    private final Image[] imagenes;

    private int totalCeldas;
    
    private final EventosMouse eventosMouse;

    public Buscaminas(Videojuego videojuego)
    {
        super(videojuego);
        
        // asignar el tamaño objetivo del tablero
        setPreferredSize(new Dimension(ANCHO, ALTO));

        // crear la lista de imagenes
        imagenes = new Image[NUMERO_IMAGENES];

        // buscar y guardar los sprites del buscaminas
        for (int i = 0; i < NUMERO_IMAGENES; i++)
        {
            String path = "assets/buscaminas/" + i + ".png";
            imagenes[i] = (new ImageIcon(path)).getImage();
        }

        // crear y asignar el listener de clicks de raton para las casillas del tablero
        eventosMouse = new EventosMouse();
        addMouseListener(eventosMouse);
    }

    @Override
    public final void nuevoJuego()
    {
        Random random = new Random();
        
        // asumimos que al ser un nuevo juego, el numero de minas por encontrar es el mismo que de minas totales
        minasFaltantes = TOTAL_MINAS;

        // calculamos la cantidad total de celdas
        totalCeldas = TOTAL_FILAS * TOTAL_COLUMNAS;
        // creamos un nuevo arreglo de datos para el campo de minas
        campo = new int[totalCeldas];
        // asignamos un valor de celda cubierta vacia a todas las posiciones del campo de minas
        for (int i = 0; i < totalCeldas; i++)
        {
            campo[i] = ID_CELDA_CUBIERTA;
        }

        // mostramos la cantidad de minas restantes/totales
        videojuego.notificar("Minas restantes: " + Integer.toString(minasFaltantes));

        // bucle para posicionar todas las minas
        int i = 0;
        while (i < TOTAL_MINAS)
        {
            // calcular posicion aleatoria para la mina
            int posicion = (int)(totalCeldas * random.nextDouble());
            
            // el bucle se saltará la agregación de la mina si por alguna razon
            // la posicion anterior esta fuera del tablero
            // o si en la misma posición ya existe una mina en el campo
            if (posicion >= totalCeldas || campo[posicion] == CELDA_MINADA_CUBIERTA)
                continue;
            
            // obtenemos la columna en la que la mina se encontrará
            int columnaActual = posicion % TOTAL_COLUMNAS;
            // se coloca la mina en la posición
            campo[posicion] = CELDA_MINADA_CUBIERTA;
            i++;

            // el siguiente codigo se encarga de "avisar" a las casillas vecinas
            // a la mina que hay una mina en sus alrededores
            // (SE VIENE CODIGO REPETITIVO)
            int celda;

            // avisar a celdas vecinas del lado izquierdo-----------------------
            // (esto solo aplica para minas no colocadas en el borde izquierdo del tablero)
            if (columnaActual > 0)
            {
                // celda superior izquierda
                celda = posicion - 1 - TOTAL_COLUMNAS;
                if (celda >= 0) // solo aplica si la celda es mayor o igual a la 0
                {
                    // no hacer nada si la celda en cuestion contiene una mina
                    if (campo[celda] != CELDA_MINADA_CUBIERTA)
                    {
                        // aumentar la cantidad de minas detectadas por la celda en cuestion
                        campo[celda] += 1;
                    }
                }
                // celda de la izquierda
                celda = posicion - 1;
                if (celda >= 0)
                {
                    if (campo[celda] != CELDA_MINADA_CUBIERTA)
                    {
                        campo[celda] += 1;
                    }
                }
                // celda inferior izquierda
                celda = posicion + TOTAL_COLUMNAS - 1;
                if (celda < totalCeldas) // solo aplica si la celda es menor a la cantidad total de celdas
                {
                    if (campo[celda] != CELDA_MINADA_CUBIERTA)
                    {
                        campo[celda] += 1;
                    }
                }
            }
            // -----------------------------------------------------------------

            // avisar a celdas vecinas del lado superior e inferior-------------
            // celda superior
            celda = posicion - TOTAL_COLUMNAS;
            if (celda >= 0)
            {
                if (campo[celda] != CELDA_MINADA_CUBIERTA)
                {
                    campo[celda] += 1;
                }
            }
            // celda inferior
            celda = posicion + TOTAL_COLUMNAS;
            if (celda < totalCeldas)
            {
                if (campo[celda] != CELDA_MINADA_CUBIERTA)
                {
                    campo[celda] += 1;
                }
            }
            // -----------------------------------------------------------------

            // avisar a celdas vecinas del lado derecho-------------------------
            // (esto solo aplica para minas no colocadas en el borde derecho del tablero)
            if (columnaActual < (TOTAL_COLUMNAS - 1))
            {
                // celda superior derecha
                celda = posicion - TOTAL_COLUMNAS + 1;
                if (celda >= 0)
                {
                    if (campo[celda] != CELDA_MINADA_CUBIERTA)
                    {
                        campo[celda] += 1;
                    }
                }
                // celda del lado derecho
                celda = posicion + TOTAL_COLUMNAS + 1;
                if (celda < totalCeldas)
                {
                    if (campo[celda] != CELDA_MINADA_CUBIERTA)
                    {
                        campo[celda] += 1;
                    }
                }
                // celda inferior derecha
                celda = posicion + 1;
                if (celda < totalCeldas)
                {
                    if (campo[celda] != CELDA_MINADA_CUBIERTA)
                    {
                        campo[celda] += 1;
                    }
                }
            }
            // -----------------------------------------------------------------
        }
        
        terminado = false;
    }
    
    /**
     * Busca de forma concurrente las celdas vacias vecinas de la celda presionada
     * @param j 
     */
    private void encontrarCeldasVacias(int j)
    {
        int columnaActual = j % TOTAL_COLUMNAS;
        int celda;

        if (columnaActual > 0)
        {
            celda = j - TOTAL_COLUMNAS - 1;
            if (celda >= 0)
            {
                if (campo[celda] > ID_CELDA_MINADA)
                {
                    campo[celda] -= ID_CELDA_CUBIERTA;
                    if (campo[celda] == ID_CELDA_VACIA)
                    {
                        encontrarCeldasVacias(celda);
                    }
                }
            }

            celda = j - 1;
            if (celda >= 0)
            {
                if (campo[celda] > ID_CELDA_MINADA)
                {
                    campo[celda] -= ID_CELDA_CUBIERTA;
                    if (campo[celda] == ID_CELDA_VACIA)
                    {
                        encontrarCeldasVacias(celda);
                    }
                }
            }

            celda = j + TOTAL_COLUMNAS - 1;
            if (celda < totalCeldas)
            {
                if (campo[celda] > ID_CELDA_MINADA)
                {
                    campo[celda] -= ID_CELDA_CUBIERTA;
                    if (campo[celda] == ID_CELDA_VACIA)
                    {
                        encontrarCeldasVacias(celda);
                    }
                }
            }
        }

        celda = j - TOTAL_COLUMNAS;
        if (celda >= 0)
        {
            if (campo[celda] > ID_CELDA_MINADA)
            {
                campo[celda] -= ID_CELDA_CUBIERTA;
                if (campo[celda] == ID_CELDA_VACIA)
                {
                    encontrarCeldasVacias(celda);
                }
            }
        }

        celda = j + TOTAL_COLUMNAS;
        if (celda < totalCeldas)
        {
            if (campo[celda] > ID_CELDA_MINADA)
            {
                campo[celda] -= ID_CELDA_CUBIERTA;
                if (campo[celda] == ID_CELDA_VACIA)
                {
                    encontrarCeldasVacias(celda);
                }
            }
        }

        if (columnaActual < (TOTAL_COLUMNAS - 1))
        {
            celda = j - TOTAL_COLUMNAS + 1;
            if (celda >= 0)
            {
                if (campo[celda] > ID_CELDA_MINADA)
                {
                    campo[celda] -= ID_CELDA_CUBIERTA;
                    if (campo[celda] == ID_CELDA_VACIA)
                    {
                        encontrarCeldasVacias(celda);
                    }
                }
            }

            celda = j + TOTAL_COLUMNAS + 1;
            if (celda < totalCeldas)
            {
                if (campo[celda] > ID_CELDA_MINADA)
                {
                    campo[celda] -= ID_CELDA_CUBIERTA;
                    if (campo[celda] == ID_CELDA_VACIA)
                    {
                        encontrarCeldasVacias(celda);
                    }
                }
            }

            celda = j + 1;
            if (celda < totalCeldas)
            {
                if (campo[celda] > ID_CELDA_MINADA)
                {
                    campo[celda] -= ID_CELDA_CUBIERTA;
                    if (campo[celda] == ID_CELDA_VACIA)
                    {
                        encontrarCeldasVacias(celda);
                    }
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        int minasDescubiertas = 0;

        for (int i = 0; i < TOTAL_FILAS; i++)
        {
            for (int j = 0; j < TOTAL_COLUMNAS; j++)
            {
                int celda = campo[(i * TOTAL_COLUMNAS) + j];

                if (!terminado && celda == ID_CELDA_MINADA)
                    terminado = true;

                if (terminado)
                {
                    if (celda == CELDA_MINADA_CUBIERTA)
                    {
                        celda = ID_CELDA_MINADA;
                    }
                    else if (celda == CELDA_MINADA_MARCADA)
                    {
                        celda = ID_CELDA_MARCADA;
                    }
                    else if (celda > CELDA_MINADA_CUBIERTA)
                    {
                        celda = ID_CELDA_MAL_MARCADA;
                    }
                    else if (celda > ID_CELDA_MINADA)
                    {
                        celda = ID_CELDA_CUBIERTA;
                    }
                }
                else
                {
                    if (celda > CELDA_MINADA_CUBIERTA)
                    {
                        celda = ID_CELDA_MARCADA;
                    }
                    else if (celda > ID_CELDA_MINADA)
                    {
                        celda = ID_CELDA_CUBIERTA;
                        minasDescubiertas++;
                    }
                }

                g.drawImage(imagenes[celda], (j * TAMANO_CELDA), (i * TAMANO_CELDA), this);
            }
        }

        if (minasDescubiertas == 0 && !terminado)
        {
            terminado = true;
            videojuego.notificar("Ganaste");
            terminar();
        }
        else if (terminado)
        {
            videojuego.notificar("Perdiste");
        }
    }
    
    @Override
    public void cerrar()
    {
        removeMouseListener(eventosMouse);
        super.cerrar();
    }

    private class EventosMouse extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e)
        {
            int x = e.getX();
            int y = e.getY();

            int columna = x / TAMANO_CELDA;
            int fila = y / TAMANO_CELDA;

            boolean redibujarTablero = false;

            if (terminado)
            {
                nuevoJuego();
                repaint();
            }

            if ((x < TOTAL_COLUMNAS * TAMANO_CELDA) && (y < TOTAL_FILAS * TAMANO_CELDA))
            {
                if (e.getButton() == MouseEvent.BUTTON3)
                {
                    if (campo[(fila * TOTAL_COLUMNAS) + columna] > ID_CELDA_MINADA)
                    {
                        redibujarTablero = true;

                        if (campo[(fila * TOTAL_COLUMNAS) + columna] <= CELDA_MINADA_CUBIERTA)
                        {
                            if (minasFaltantes > 0)
                            {
                                campo[(fila * TOTAL_COLUMNAS) + columna] += ID_CELDA_CUBIERTA;
                                minasFaltantes--;
                                videojuego.notificar("Minas restantes: " + Integer.toString(minasFaltantes));
                            }
                            else
                            {
                                videojuego.notificar("No es posible marcar mas casillas!");
                            }
                        }
                        else
                        {
                            campo[(fila * TOTAL_COLUMNAS) + columna] -= ID_CELDA_CUBIERTA;
                            minasFaltantes++;
                            videojuego.notificar("Minas restantes: " + Integer.toString(minasFaltantes));
                        }
                    }
                }
                else
                {
                    if (campo[(fila * TOTAL_COLUMNAS) + columna] > CELDA_MINADA_CUBIERTA)
                        return;

                    if ((campo[(fila * TOTAL_COLUMNAS) + columna] > ID_CELDA_MINADA)
                            && (campo[(fila * TOTAL_COLUMNAS) + columna] < CELDA_MINADA_MARCADA))
                    {
                        campo[(fila * TOTAL_COLUMNAS) + columna] -= ID_CELDA_CUBIERTA;
                        redibujarTablero = true;

                        if (campo[(fila * TOTAL_COLUMNAS) + columna] == ID_CELDA_MINADA)
                        {
                            terminado = true;
                        }

                        if (campo[(fila * TOTAL_COLUMNAS) + columna] == ID_CELDA_VACIA)
                        {
                            encontrarCeldasVacias((fila * TOTAL_COLUMNAS) + columna);
                        }
                    }
                }

                if (redibujarTablero)
                {
                    repaint();
                }
            }
        }
    }
}
