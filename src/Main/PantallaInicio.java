package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PantallaInicio extends JPanel {

    public BufferedImage casaImagen;
    private Sound sound; // Instancia para manejar el sonido

    public PantallaInicio(ActionListener startListener) {
        this.setLayout(null); // Usamos layout nulo para posicionar elementos manualmente
        this.setBackground(new Color(96, 120, 143)); // Mismo fondo que tu juego

        // Iniciar música del menú
        sound = new Sound();
        sound.setFile(1); // 1 = MainMenuMusic.wav
        sound.play();
        sound.loop();

        //Cargar la img de la casa
        cargarImagen();
        //Posicion de la casa
        if (casaImagen != null) {
            JLabel casaLabel = new JLabel(new ImageIcon(casaImagen));
            int casaX = (1536 - casaImagen.getWidth()) / 2;
            int casaY = 200;
            casaLabel.setBounds(casaX, casaY, casaImagen.getWidth(), casaImagen.getHeight());
            this.add(casaLabel); // Añadir casa PRIMERO (queda atrás)
        }


        // Etiqueta para el título del juego
            JLabel titulo = new JLabel("Farm Island", SwingConstants.CENTER);
            titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
            titulo.setForeground(Color.GREEN);
            titulo.setBounds(0, 130, 1536, 100); // (x, y, ancho, alto) centrado horizontalmente
            titulo.setOpaque(false);
            this.add(titulo);

            JLabel tituloSombra = new JLabel("Farm Island", SwingConstants.CENTER);
            tituloSombra.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
            tituloSombra.setForeground(Color.BLACK);
            tituloSombra.setBounds(5, 135, 1536, 100); // (x, y, ancho, alto) centrado horizontalmente
            tituloSombra.setOpaque(true);
            this.add(tituloSombra);

            // Botón para iniciar el juego
            JButton botonInicio = new JButton("Iniciar Juego");
            botonInicio.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            botonInicio.setBounds(618, 500, 300, 80); // Centrado debajo del título
            botonInicio.setOpaque(true);
            botonInicio.setBackground(Color.ORANGE);
            botonInicio.setBorderPainted(BorderFactory.createRaisedSoftBevelBorder().isBorderOpaque());
            botonInicio.setFocusPainted(false);
            botonInicio.addActionListener(startListener); // Le asignamos el listener que recibimos
            this.add(botonInicio);

            JButton botonSalir = new JButton("Salir");
            botonSalir.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            botonSalir.setBounds(618, 600, 300, 80); // Centrado debajo del título
            botonSalir.setOpaque(true);
            botonSalir.setBackground(Color.ORANGE);
            botonSalir.setBorderPainted(BorderFactory.createRaisedSoftBevelBorder().isBorderOpaque());
            botonSalir.setFocusPainted(false);
            //Acción para el botón de salir (se está usando una expresión lambda para simplificar
            botonSalir.addActionListener(e -> {
                System.exit(0); // Cierra el juego
            });
            this.add(botonSalir);
    }

    public void cargarImagen() {
        try {
            casaImagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/PantallaInicioCasa/casa (1).png"));

        } catch (IOException | IllegalArgumentException e) {
            casaImagen = null;
        }
    }
    public void detenerMusica() {
        if (sound != null) {
            sound.stop();
        }
    }
}


