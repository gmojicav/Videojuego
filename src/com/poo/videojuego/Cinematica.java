package com.poo.videojuego;

import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Cinematica extends JFrame
{
    protected Image fondo;
    protected String texto;
    protected Image personaje1;
    protected Image personaje2;
    
    private final JPanel panelFondo;
    private final JTextArea textoHistoria;
    private final JButton botonContinuar;
    
    protected EventoJuego cerrar;
    
    public Cinematica(Image fondo, String texto,Image personaje1, Image personaje2)
    {
        super("La aventura de Adrián");
        
        this.fondo = fondo;
        this.texto = texto;
        this.personaje1 = personaje1;
        this.personaje2 = personaje2;
        
        panelFondo = new JPanel(new GridBagLayout())
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, null);
                g.drawImage(personaje1, 30, 300, null);
                g.drawImage(personaje2,450,300,null);
            }
        };
        
        textoHistoria = new JTextArea(texto);
        textoHistoria.setEditable(false);
        textoHistoria.setLineWrap(true);
        textoHistoria.setWrapStyleWord(true);
        textoHistoria.setPreferredSize(new Dimension(510,100));
        GridBagConstraints textoHistoriaConstraints = new GridBagConstraints();
        textoHistoriaConstraints.gridx = 0;
        textoHistoriaConstraints.gridy = 0;
        textoHistoriaConstraints.anchor = GridBagConstraints.PAGE_START;
        
        botonContinuar = new JButton("Continuar");
        botonContinuar.addActionListener((ActionEvent evento) ->
        {
            terminar();
        });
        GridBagConstraints botonContinuarConstraints = new GridBagConstraints();
        botonContinuarConstraints.gridx = 0;
        botonContinuarConstraints.gridy = 1;
        botonContinuarConstraints.anchor = GridBagConstraints.PAGE_END;
        botonContinuarConstraints.insets = new Insets(600, 0, 0, 0);
        
        panelFondo.add(textoHistoria, textoHistoriaConstraints);
        panelFondo.add(botonContinuar, botonContinuarConstraints);
        
        getContentPane().add(panelFondo);
    }
    
    public void mostrar()
    {
        setSize(850,850);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("assets/icono.png").getImage());
        setVisible(true);
    }
    
    public void guardarEventoCerrar(EventoJuego cerrar)
    {
        this.cerrar = cerrar;
    }
    
    public void terminar()
    {
        cerrar.ejecutar();
        dispose();  
    }
}