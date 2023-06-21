package com.poo.videojuego.ahorcado;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

import java.io.*;
import java.nio.file.*;

import java.util.*;
import java.util.List;

import com.poo.videojuego.*;

public class Ahorcado extends Juego
{
    private final JLabel ahorcado;
    
    private final JLabel palabraOculta;
    
    private final JPanel panelEntradaTexto;
    private final JTextField entradaLetra;
    private final JButton botonIngresarLetra;

    private String palabra;
    private String palabraAdivinada;
    //private char[] palabraAdivinada = new char[50];
    //private int tamanyoPalabraAdivinada;
    private int intentosRestantes;
    //private char letra;
    
    private final int INTENTOS_MAXIMOS = 5;
    // advertencia: las palabras en el archivo "palabras.txt" nunca deberian incluir este caracter
    private final String CARACTER_VACIO = "~";
    
    public Ahorcado(Videojuego videojuego)
    {
        super(videojuego);
        
        setLayout(new GridBagLayout());
        
        ahorcado = new JLabel();
        ahorcado.setPreferredSize(new Dimension(200, 200));
        ahorcado.setHorizontalAlignment(JTextField.CENTER);
        GridBagConstraints ahorcadoContraints = new GridBagConstraints();
        ahorcadoContraints.gridx = 0;
        ahorcadoContraints.gridy = 0;
        
        palabraOculta = new JLabel();
        palabraOculta.setPreferredSize(new Dimension(300, 20));
        palabraOculta.setHorizontalAlignment(JTextField.CENTER);
        GridBagConstraints palabraOcultaContraints = new GridBagConstraints();
        palabraOcultaContraints.gridx = 0;
        palabraOcultaContraints.gridy = 1;
        palabraOcultaContraints.fill = GridBagConstraints.HORIZONTAL;
        
        panelEntradaTexto = new JPanel(new GridBagLayout());
        GridBagConstraints panelEntradaTextoContraints = new GridBagConstraints();
        panelEntradaTextoContraints.gridx = 0;
        panelEntradaTextoContraints.gridy = 2;
        panelEntradaTextoContraints.weighty = 1.1;
        
        entradaLetra = new JTextField();
        entradaLetra.setPreferredSize(new Dimension(20, 20));
        entradaLetra.setHorizontalAlignment(JTextField.CENTER);
        PlainDocument documentoEntradaLetra = new PlainDocument();
        DocumentFilter filtroEntradaLetra = new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attrs) throws BadLocationException
            {
                if (fb.getDocument().getLength() + text.length() <= 1) {
                    super.insertString(fb, offset, text, attrs);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException
            {
                if (fb.getDocument().getLength() + text.length() - length <= 1) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        };
        documentoEntradaLetra.setDocumentFilter(filtroEntradaLetra);
        entradaLetra.setDocument(documentoEntradaLetra);
        GridBagConstraints entradaLetraContraints = new GridBagConstraints();
        entradaLetraContraints.gridx = 0;
        entradaLetraContraints.gridy = 0;
        
        botonIngresarLetra = new JButton();
        botonIngresarLetra.addActionListener((ActionEvent evento) ->
        {
            probar();
        });
        GridBagConstraints botonIngresarLetraContraints = new GridBagConstraints();
        botonIngresarLetraContraints.gridx = 1;
        botonIngresarLetraContraints.gridy = 0;
        
        panelEntradaTexto.add(entradaLetra, entradaLetraContraints);
        panelEntradaTexto.add(botonIngresarLetra, botonIngresarLetraContraints);
        
        add(ahorcado, ahorcadoContraints);
        add(palabraOculta, palabraOcultaContraints);
        add(panelEntradaTexto, panelEntradaTextoContraints);
    }
    
    @Override
    public final void nuevoJuego()
    {
        setBackground(Color.WHITE);
        
        intentosRestantes = INTENTOS_MAXIMOS;
        
        palabra = elegirPalabra();
        palabraAdivinada = new String();
        videojuego.notificar("Ahorcado");
        actualizarAhorcado();
        
        for(int i = 0; i < palabra.length(); i++)
        {
            palabraAdivinada = palabraAdivinada.concat(CARACTER_VACIO);
        }
        
        reconstruirPalabraOculta();
        botonIngresarLetra.setText("Probar");
        
        terminado = false;
    }
    
    private void reconstruirPalabraOculta()
    {
        palabraOculta.setText("");
        
        for(int i = 0; i < palabra.length(); i++)
        {
            if (palabraAdivinada.charAt(i) == CARACTER_VACIO.charAt(0))
                palabraOculta.setText(palabraOculta.getText().concat("_ "));
            else
            {
                palabraOculta.setText(
                        palabraOculta.getText().concat(new String(new char[]{palabraAdivinada.charAt(i)})));
            }
        }
    }
    
    private String elegirPalabra()
    {
        try
        {
            List<String> palabras = Files.readAllLines(Paths.get("assets/ahorcado/palabras.txt"));
            Random random = new Random(System.currentTimeMillis());
            String palabraElegida = palabras.get(random.nextInt(palabras.size()));
            palabraElegida = palabraElegida.toUpperCase();
            System.out.println(palabraElegida);
            
            return palabraElegida;
        }
        catch(IOException ex)
        {
            return "";
        }
    }
    
    private void probar()
    {
        if (intentosRestantes > 0)
            probarLetra();
        else
            nuevoJuego();
    }
    
    private void probarLetra()
    {
        // parar aqui si no hay texto en el campo de entrada
        // o si se intentó ingresar un caracter invalido
        if (entradaLetra.getText().isEmpty()
                || entradaLetra.getText().equals(" ")
                || entradaLetra.getText().contains(CARACTER_VACIO))
        {
            videojuego.notificar("No puedes ingresar espacios ni \"" + CARACTER_VACIO + "\"");
            entradaLetra.setText("");
            return;
        }
        
        // obtener la primera (y unica) letra del campo de entrada
        char letra = entradaLetra.getText().toUpperCase().charAt(0);
        // intenta encontrar la letra en la palabra
        int posicionLetra = palabra.indexOf(letra);
        
        entradaLetra.setText("");
        
        // si la letra no existe en la palabra...
        if (posicionLetra == -1)
        {
            // se notifica al jugador
            videojuego.notificar("\"" + letra + "\" no esta en la palabra");
            // se quita un intento
            intentosRestantes -= 1;
            // se quitan 10 puntos al jugador
            videojuego.bajarPuntuacion(10);
            // se actualiza el estado del ahorcado
            actualizarAhorcado();
        }
        else // de lo contrario...
        {
            // se agrega la letra en la palabra
            agregarLetra(letra);
        }
        
        // si la palabra adivinada coincide con la palabra, el juego termina
        if (palabra.equals(palabraAdivinada))
        {
            videojuego.notificar("Adivinaste la palabra!");
            terminar();
        }
        
        // si los intentos se han acabado, el juego vuelve a empezar
        if(intentosRestantes == 0)
        {
            videojuego.bajarPuntuacion(100);
            videojuego.notificar("Perdiste");
            botonIngresarLetra.setText("Volver a intentar");
        }
    }
    
    private void agregarLetra(char letra)
    {
        if (palabraAdivinada.contains(new String(new char[]{letra})))
        {
            videojuego.notificar("\"" + letra + "\" ya fue adivinada");
            return;
        }
        
        char[] pa = palabraAdivinada.toCharArray();
        
        for (int i = 0; i < palabra.length(); i++)
        {
            if (palabra.charAt(i) == letra)
            {
                pa[i] = letra;
            }
        }
        
        palabraAdivinada = new String(pa);
        reconstruirPalabraOculta();
        
        videojuego.notificar("");
    }
    
    private void actualizarAhorcado()
    {
        // crear imagen a partir del archivo dado
        ImageIcon icono = new ImageIcon("assets/ahorcado/" + intentosRestantes + ".png");
        // reescalar la imagen para quedar con el tamaño de los botones
        Image imagenIcono = icono.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ahorcado.setIcon(new ImageIcon(imagenIcono));
    }
}
