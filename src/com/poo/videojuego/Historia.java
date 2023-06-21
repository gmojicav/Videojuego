package com.poo.videojuego;

import java.awt.*;
import javax.swing.*;
import java.awt.*;

class Historia{
    protected Image fondo;
    protected String texto;
    protected Image personaje1;
    protected Image personaje2;
    
    public Historia(Image fondo, String texto, Image personaje1, Image personaje2){
        this.fondo = fondo;
        this.texto = texto;
        this.personaje1 = personaje1;
        this.personaje2 = personaje2;
    }
    
    public void sf(Image fondo){
        this.fondo = fondo;
    }
    
        
    public void st(String texto){
        this.texto = texto;
    }
    
    public void sp1(Image personaje1){
        this.personaje1 = personaje1;
    }
    
    
    public void sp2(Image personaje2){
    this.personaje2 = personaje2;
    }
    
    public void mostrarfondo(){
        JFrame frame = new JFrame();
        frame.setSize(850,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, null);
                g.drawImage(personaje1, 30, 300, null);
                g.drawImage(personaje2,450,300,null);
            }
        };
        
        JTextArea textArea = new JTextArea(texto);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setPreferredSize(new Dimension(510,100));
        panel.add(textArea);
        
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        
        int timc = 10 * 1000;
        Timer timer = new Timer(timc, e -> {
            frame.dispose();  
        });
        timer.setRepeats(false);
        timer.start();
    }

}