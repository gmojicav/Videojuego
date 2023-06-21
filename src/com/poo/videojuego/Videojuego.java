package com.poo.videojuego;

import java.awt.*;
import javax.swing.*;

public class Videojuego extends JFrame
{
    private Juego juegoActual;
    
    private final JPanel fondo;
    private JPanel panelJuego;
    
    private final JLabel imagenJugador;
    private final JLabel imagenEnemigo;
    
    private final JLabel textoPuntuacion;
    private final JLabel textoStatus;
    
    private int puntuacion;
    
    public Videojuego()
    {
        super();
        
        // crear panel con la imagen de fondo
        getContentPane().setLayout(new BorderLayout());
        fondo = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibujar la imagen de fondo
                Image imagenFondo = new ImageIcon("assets/fondos/bosque.png").getImage();
                g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        fondo.setOpaque(false);
        getContentPane().add(fondo, BorderLayout.CENTER);
        
        // crear imagen del personaje jugador
        ImageIcon iconoJugador = new ImageIcon("assets/personajes/adrian.png");
        Image iconoJugadorAdrian = iconoJugador.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        imagenJugador = new JLabel(new ImageIcon(iconoJugadorAdrian));
        imagenJugador.setPreferredSize(new Dimension(400, 400));
        GridBagConstraints imagenJugadorContraints = new GridBagConstraints();
        imagenJugadorContraints.gridx = 0;
        imagenJugadorContraints.gridy = 1;
        imagenJugadorContraints.fill = GridBagConstraints.WEST;
        
        // crear imagen del enemigo
        imagenEnemigo = new JLabel();
        imagenEnemigo.setPreferredSize(new Dimension(400, 400));
        GridBagConstraints imagenEnemigoContraints = new GridBagConstraints();
        imagenEnemigoContraints.gridx = 2;
        imagenEnemigoContraints.gridy = 1;
        imagenEnemigoContraints.fill = GridBagConstraints.EAST;
        
        // crear texto de información para el jugador
        textoStatus = new JLabel();
        textoStatus.setPreferredSize(new Dimension(300, 20));
        textoStatus.setHorizontalAlignment(JLabel.CENTER);
        textoStatus.setBackground(Color.WHITE);
        GridBagConstraints textoStatusContraints = new GridBagConstraints();
        textoStatusContraints.gridx = 1;
        textoStatusContraints.gridy = 2;
        textoStatusContraints.fill = GridBagConstraints.NONE;
        
        // crear texto de textoPuntuacion para el jugador
        textoPuntuacion = new JLabel();
        textoPuntuacion.setPreferredSize(new Dimension(300, 20));
        textoPuntuacion.setHorizontalAlignment(JLabel.CENTER);
        textoPuntuacion.setBackground(Color.WHITE);
        textoPuntuacion.setOpaque(true);
        GridBagConstraints textoPuntuacionContraints = new GridBagConstraints();
        textoPuntuacionContraints.gridx = 1;
        textoPuntuacionContraints.gridy = 0;
        textoPuntuacionContraints.fill = GridBagConstraints.NONE;
        
        // agregar imagenes de personajes y texto de informacion
        fondo.add(imagenJugador, imagenJugadorContraints);
        fondo.add(imagenEnemigo, imagenEnemigoContraints);
        fondo.add(textoStatus, textoStatusContraints);
        fondo.add(textoPuntuacion, textoPuntuacionContraints);
        
        // configurar ventana
        setTitle("La aventura de Adrián");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("assets/icono.png").getImage());
        
        puntuacion = 0;
        mostrarPuntuacion();
    }
    
    public void jugar(Juego nuevoJuego, ImageIcon enemigo)
    {
        if (juegoActual != null)
        {
            cerrarPanelJuego();
        }
        
        panelJuego = juegoActual = nuevoJuego;
        panelJuego.setPreferredSize(new Dimension(300, 300));
        panelJuego.setBackground(Color.GRAY);
        GridBagConstraints panelJuegoContraints = new GridBagConstraints();
        panelJuegoContraints.gridx = 1;
        panelJuegoContraints.gridy = 1;
        panelJuegoContraints.weightx = 2;
        panelJuegoContraints.fill = GridBagConstraints.CENTER;
        
        fondo.add(panelJuego, panelJuegoContraints);
        
        nuevoJuego.nuevoJuego();
        
        mostrarEnemigo(enemigo);
        
        setVisible(true);
        this.requestFocus();
    }
    
    public void cerrarPanelJuego()
    {
        juegoActual.cerrar();
        fondo.remove(panelJuego);
    }
    
    public void mostrarEnemigo(ImageIcon enemigo)
    {
        ImageIcon iconoEnemigo = enemigo;
        Image imagenIconoEnemigo = iconoEnemigo.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        imagenEnemigo.setIcon(new ImageIcon(imagenIconoEnemigo));
    }
    
    public void notificar(String texto)
    {
        textoStatus.setText(texto);
        textoStatus.setOpaque(true);
    }
    
    public void subirPuntuacion(int puntos)
    {
        puntuacion += puntos;
        mostrarPuntuacion();
    }
    
    public void bajarPuntuacion(int puntos)
    {
        // limitar a 0 la cantidad minima de puntos
        puntuacion = Math.max(0, puntuacion - puntos);
        mostrarPuntuacion();
    }
    
    private void mostrarPuntuacion()
    {
        textoPuntuacion.setText("Puntuacion: " + Integer.toString(puntuacion));
    }
}
