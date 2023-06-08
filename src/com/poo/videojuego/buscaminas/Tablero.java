package com.poo.videojuego.buscaminas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Random;

public class Tablero extends JPanel
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

    private final int TOTAL_MINAS = 10;
    private final int TOTAL_FILAS = 8;
    private final int TOTAL_COLUMNAS = 8;

    private final int ANCHO = TOTAL_COLUMNAS * TAMANO_CELDA + 1;
    private final int ALTO = TOTAL_FILAS * TAMANO_CELDA + 1;

    private int[] campo;
    private boolean jugando;
    private int minasFaltantes;
    private final Image[] imagenes;

    private int totalCeldas;
    private final JLabel barraEstado;

    public Tablero(JLabel barraEstado)
    {
        this.barraEstado = barraEstado;
        
        setPreferredSize(new Dimension(ANCHO, ALTO));

        imagenes = new Image[NUMERO_IMAGENES];

        for (int i = 0; i < NUMERO_IMAGENES; i++)
        {
            String path = "assets/buscaminas/" + i + ".png";
            imagenes[i] = (new ImageIcon(path)).getImage();
        }

        addMouseListener(new MinesAdapter());
        nuevoJuego();
    }

    /**
     * Establece el juego en su estado inicial
     */
    private void nuevoJuego()
    {
        int celda;

        Random random = new Random();
        jugando = true;
        minasFaltantes = TOTAL_MINAS;

        totalCeldas = TOTAL_FILAS * TOTAL_COLUMNAS;
        campo = new int[totalCeldas];

        for (int i = 0; i < totalCeldas; i++)
        {
            campo[i] = ID_CELDA_CUBIERTA;
        }

        barraEstado.setText(Integer.toString(minasFaltantes));

        int i = 0;
        while (i < TOTAL_MINAS)
        {
            int position = (int) (totalCeldas * random.nextDouble());

            if ((position < totalCeldas) && (campo[position] != CELDA_MINADA_CUBIERTA))
            {
                int columnaActual = position % TOTAL_COLUMNAS;
                campo[position] = CELDA_MINADA_CUBIERTA;
                i++;

                if (columnaActual > 0)
                {
                    celda = position - 1 - TOTAL_COLUMNAS;
                    if (celda >= 0)
                    {
                        if (campo[celda] != CELDA_MINADA_CUBIERTA)
                        {
                            campo[celda] += 1;
                        }
                    }
                    celda = position - 1;
                    if (celda >= 0)
                    {
                        if (campo[celda] != CELDA_MINADA_CUBIERTA)
                        {
                            campo[celda] += 1;
                        }
                    }

                    celda = position + TOTAL_COLUMNAS - 1;
                    if (celda < totalCeldas)
                    {
                        if (campo[celda] != CELDA_MINADA_CUBIERTA)
                        {
                            campo[celda] += 1;
                        }
                    }
                }

                celda = position - TOTAL_COLUMNAS;
                if (celda >= 0)
                {
                    if (campo[celda] != CELDA_MINADA_CUBIERTA)
                    {
                        campo[celda] += 1;
                    }
                }

                celda = position + TOTAL_COLUMNAS;
                if (celda < totalCeldas)
                {
                    if (campo[celda] != CELDA_MINADA_CUBIERTA)
                    {
                        campo[celda] += 1;
                    }
                }

                if (columnaActual < (TOTAL_COLUMNAS - 1))
                {
                    celda = position - TOTAL_COLUMNAS + 1;
                    if (celda >= 0)
                    {
                        if (campo[celda] != CELDA_MINADA_CUBIERTA)
                        {
                            campo[celda] += 1;
                        }
                    }
                    celda = position + TOTAL_COLUMNAS + 1;
                    if (celda < totalCeldas)
                    {
                        if (campo[celda] != CELDA_MINADA_CUBIERTA)
                        {
                            campo[celda] += 1;
                        }
                    }
                    celda = position + 1;
                    if (celda < totalCeldas)
                    {
                        if (campo[celda] != CELDA_MINADA_CUBIERTA)
                        {
                            campo[celda] += 1;
                        }
                    }
                }
            }
        }
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
    public void paintComponent(Graphics g) {

        int minasDescubiertas = 0;

        for (int i = 0; i < TOTAL_FILAS; i++)
        {
            for (int j = 0; j < TOTAL_COLUMNAS; j++)
            {
                int celda = campo[(i * TOTAL_COLUMNAS) + j];

                if (jugando && celda == ID_CELDA_MINADA)
                    jugando = false;

                if (!jugando)
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

        if (minasDescubiertas == 0 && jugando)
        {
            jugando = false;
            barraEstado.setText("Ganaste");
        }
        else if (!jugando)
        {
            barraEstado.setText("Perdiste");
        }
    }

    private class MinesAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e)
        {
            int x = e.getX();
            int y = e.getY();

            int columna = x / TAMANO_CELDA;
            int fila = y / TAMANO_CELDA;

            boolean redibujarTablero = false;

            if (!jugando)
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
                                String msg = Integer.toString(minasFaltantes);
                                barraEstado.setText(msg);
                            }
                            else
                            {
                                barraEstado.setText("No es posible marcar mas casillas!");
                            }
                        }
                        else
                        {
                            campo[(fila * TOTAL_COLUMNAS) + columna] -= ID_CELDA_CUBIERTA;
                            minasFaltantes++;
                            String msg = Integer.toString(minasFaltantes);
                            barraEstado.setText(msg);
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
                            jugando = false;
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
