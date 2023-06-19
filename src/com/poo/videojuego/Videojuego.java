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
    
    private final JLabel textoStatus;
    
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
                Image imagenFondo = new ImageIcon("assets/bosque.png").getImage();
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
        imagenJugadorContraints.gridy = 0;
        imagenJugadorContraints.fill = GridBagConstraints.WEST;
        
        // crear imagen del enemigo
        imagenEnemigo = new JLabel();
        imagenEnemigo.setPreferredSize(new Dimension(400, 400));
        GridBagConstraints imagenEnemigoContraints = new GridBagConstraints();
        imagenEnemigoContraints.gridx = 2;
        imagenEnemigoContraints.gridy = 0;
        imagenEnemigoContraints.fill = GridBagConstraints.EAST;
        
        // crear texto de información para el jugador
        textoStatus = new JLabel();
        textoStatus.setPreferredSize(new Dimension(300, 20));
        textoStatus.setHorizontalAlignment(JLabel.CENTER);
        textoStatus.setBackground(Color.WHITE);
        GridBagConstraints textoStatusContraints = new GridBagConstraints();
        textoStatusContraints.gridx = 1;
        textoStatusContraints.gridy = 1;
        textoStatusContraints.fill = GridBagConstraints.NONE;
        
        // agregar imagenes de personajes y texto de informacion
        fondo.add(imagenJugador, imagenJugadorContraints);
        fondo.add(imagenEnemigo, imagenEnemigoContraints);
        fondo.add(textoStatus, textoStatusContraints);
        
        // configurar ventana
        setTitle("La busqueda de Adrian/Adrian's bizarre adventure");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void jugar(Juego nuevoJuego, ImageIcon enemigo)
    {
        if (juegoActual != null)
        {
            juegoActual.cerrar();
            getContentPane().remove(panelJuego);
        }
        
        panelJuego = juegoActual = nuevoJuego;
        panelJuego.setPreferredSize(new Dimension(300, 300));
        panelJuego.setBackground(Color.GRAY);
        GridBagConstraints panelJuegoContraints = new GridBagConstraints();
        panelJuegoContraints.gridx = 1;
        panelJuegoContraints.gridy = 0;
        panelJuegoContraints.weightx = 2;
        panelJuegoContraints.fill = GridBagConstraints.CENTER;
        
        fondo.add(panelJuego, panelJuegoContraints);
        
        nuevoJuego.nuevoJuego();
        
        ImageIcon iconoEnemigo = enemigo;
        Image imagenIconoEnemigo = iconoEnemigo.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        imagenEnemigo.setIcon(new ImageIcon(imagenIconoEnemigo));
        
        setVisible(true);
    }
    
    public void notificar(String texto)
    {
        textoStatus.setText(texto);
        textoStatus.setOpaque(true);
    }
}
