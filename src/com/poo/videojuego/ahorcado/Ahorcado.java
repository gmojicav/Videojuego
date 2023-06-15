package com.poo.videojuego.ahorcado;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;

import java.util.*;
import java.util.List;

import com.poo.videojuego.*;

public class Ahorcado extends Juego
{
    private final JLabel textoTitulo;
    private final JTextArea registroPalabras;

    private final JPanel panelInferior;
    private final JLabel etiquetaIngresaLetra;
    private final JTextField entradaLetra;
    private final JButton botonIngresarLetra;

    private String palabra;
    private int tamanyoPalabra;
    private char[] palabraAdivinada = new char[50];
    private int tamanyoPalabraAdivinada;
    private int intentosRestantes;
    private char letra;
    
    public Ahorcado(EventoJuego terminarJuego)
    {
        super("Ahorcado");
        
        textoTitulo = new JLabel("Ahorcado [Prototipo]",JLabel.CENTER);
        textoTitulo.setFont(new Font("Arial",Font.BOLD,40));
        
        registroPalabras = new JTextArea();
        registroPalabras.setFont(new Font("Arial",Font.BOLD,20));
        registroPalabras.setEditable(false);
        
        etiquetaIngresaLetra = new JLabel("Ingresa una letra");
        etiquetaIngresaLetra.setFont(new Font("Arial",Font.PLAIN,20));
        
        entradaLetra = new JTextField(10);
        entradaLetra.setFont(new Font("Arial",Font.PLAIN,20));
        
        botonIngresarLetra = new JButton("Probar");
        botonIngresarLetra.setFont(new Font("Arial",Font.PLAIN,20));
        botonIngresarLetra.addActionListener((ActionEvent evento) ->
        {
            letra = entradaLetra.getText().charAt(0);
            int pos = palabra.indexOf(letra);
            entradaLetra.setText("");

            if(pos >= 0)
            {
                palabraAdivinada = agregarLetra();
                mostrarPalabraAdivinada();
            }
            else
            {
                registroPalabras.append("\n\"" + letra + "\" no esta en la palabra\nPierdes un intento");
                intentosRestantes -= 1;
                mostrarPalabraAdivinada();
            }

            if(tamanyoPalabraAdivinada == tamanyoPalabra)
            {
                registroPalabras.append("\nFelicidades! Adivinaste la palabra\n" + palabra);
                terminar();
            }

            if(intentosRestantes == 0)
            {
                registroPalabras.append("\nOops!!! Perdiste");
                nuevoJuego();
            }
            
            registroPalabras.setCaretPosition(registroPalabras.getDocument().getLength() - 1);
        });
        
        panelInferior = new JPanel();
        panelInferior.add(etiquetaIngresaLetra);
        panelInferior.add(entradaLetra);
        panelInferior.add(botonIngresarLetra);
        
        JPanel cpanel = new JPanel();
        cpanel.setLayout(new BorderLayout(0,0));
        cpanel.add(registroPalabras,BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(
            registroPalabras,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cpanel.add(scrollPane);

        add(textoTitulo, BorderLayout.NORTH);
        add(cpanel, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
        
        setSize(800,500);
        setLocationRelativeTo(null);
        setVisible(true);
        
        nuevoJuego();
        
        // asignar evento de fin de juego
        this.juegoTerminado = terminarJuego;
    }
    
    @Override
    protected final void nuevoJuego()
    {
        intentosRestantes = 5;
        tamanyoPalabraAdivinada = 0;
        
        palabra = elegirPalabra();
        registroPalabras.setText("");
        registroPalabras.append("Intenta adivinar la palabra\nIntentos restantes: " + intentosRestantes + "\n");
        
        for(int i = 0; i < tamanyoPalabra; i++)
        {
            registroPalabras.append("_ ");
            palabraAdivinada[i] = '_';
        }

        registroPalabras.append("\nIngresa una letra...");

//        if(intentosRestantes > 0 && tamanyoPalabraAdivinada < tamanyoPalabra)
//        {
//            probarLetra();
//        }

        setVisible(true);
    }
    
    private String elegirPalabra()
    {
        try
        {
            List<String> palabras = 
                    Files.readAllLines(Paths.get("assets/ahorcado/palabras.txt"), Charset.defaultCharset());
            Random random = new Random(System.currentTimeMillis());
            String palabraElegida = palabras.get(random.nextInt(palabras.size()));
            palabraElegida = palabraElegida.toLowerCase();
            tamanyoPalabra = palabraElegida.length();
            System.out.print(palabraElegida);
            
            return palabraElegida;
        }
        catch(IOException ex)
        {
            return "";
        }
    }
    
    private void probarLetra()
    {
        
    }
    
    private char[] agregarLetra()
    {
        char[] secretWord = new char[tamanyoPalabra];
        for(int i = 0; i < tamanyoPalabra; i++)
        {
            secretWord[i] = palabra.charAt(i);
        }
        
        int elem = palabra.indexOf(letra);
        if( letra == palabraAdivinada[elem])
        {
            registroPalabras.append("\nLa letra ya se encuentra en la palabra\nPrueba una letra diferente");
        }
        else
        {
            registroPalabras.append("\n\"" + letra + "\" está en la palabra");
            for(int i = elem; i < tamanyoPalabra; i++)
            {
                if(secretWord[i] == letra)
                {
                    palabraAdivinada[i] = letra;
                    tamanyoPalabraAdivinada += 1;
                }
            }
        }
        return palabraAdivinada;
    }
    
    private void mostrarPalabraAdivinada()
    {
        registroPalabras.append("\n");
        for(int i = 0; i < tamanyoPalabra; i++)
        {
            registroPalabras.append(palabraAdivinada[i] + " ");
        }
        registroPalabras.append("\nIntentos restantes: " + intentosRestantes);
    }
}
