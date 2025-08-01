package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PantallaInicio extends JPanel {

    public BufferedImage casaImagen;
    public BufferedImage utpImagen;
    public BufferedImage fiscImagen;
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
        //Posicion utp
        if (utpImagen != null) {
            JLabel casaLabel = new JLabel(new ImageIcon(utpImagen));
            int casaX = (320 - utpImagen.getWidth()) / 2;
            int casaY = 35;
            casaLabel.setBounds(casaX, casaY, utpImagen.getWidth(), utpImagen.getHeight());
            this.add(casaLabel);
        }
        //Posicion fisc
        if (fiscImagen != null) {
            JLabel casaLabel = new JLabel(new ImageIcon(fiscImagen));
            int casaX = (2750 - fiscImagen.getWidth()) / 2;
            int casaY = 30;
            casaLabel.setBounds(casaX, casaY, fiscImagen.getWidth(), fiscImagen.getHeight());
            this.add(casaLabel);
        }


        // Etiqueta para el título del juego
            JLabel titulo = new JLabel("Farm Island", SwingConstants.CENTER);
            titulo.setFont(new Font("Pixelify Sans", Font.BOLD, 60));
            titulo.setForeground(Color.GREEN);
            titulo.setBounds(0, 130, 1536, 100); // (x, y, ancho, alto) centrado horizontalmente
            titulo.setOpaque(false);
            this.add(titulo);

            JLabel tituloSombra = new JLabel("Farm Island", SwingConstants.CENTER);
            tituloSombra.setFont(new Font("Pixelify Sans", Font.BOLD, 60));
            tituloSombra.setForeground(Color.BLACK);
            tituloSombra.setBounds(5, 135, 1536, 100); // (x, y, ancho, alto) centrado horizontalmente
            tituloSombra.setOpaque(true);
            this.add(tituloSombra);

            //Informacion del grupo
            JLabel tituloUtp = new JLabel("Universidad Tecnologica de Panamá", SwingConstants.CENTER);
            tituloUtp.setFont(new Font("Pixelify Sans", Font.BOLD, 60));
            tituloUtp.setForeground(Color.GREEN);
            tituloUtp.setBounds(0, 0, 1536, 100); // (x, y, ancho, alto) centrado horizontalmente
            tituloUtp.setOpaque(false);
            this.add(tituloUtp);

            JLabel tituloCarrera = new JLabel("Lic. Ingeniería en software", SwingConstants.CENTER);
            tituloCarrera.setFont(new Font("Pixelify Sans", Font.BOLD, 40));
            tituloCarrera.setForeground(Color.GREEN);
            tituloCarrera.setBounds(0, 60, 1536, 100); // (x, y, ancho, alto) centrado horizontalmente
            tituloCarrera.setOpaque(false);
            this.add(tituloCarrera);

            JLabel tituloProfesor = new JLabel("Profesor: Rodrigo Yángüez", SwingConstants.CENTER);
            tituloProfesor.setFont(new Font("Pixelify Sans", Font.BOLD, 20));
            tituloProfesor.setForeground(Color.WHITE);
            tituloProfesor.setBounds(600, 770, 1536, 100); // (x, y, ancho, alto) centrado horizontalmente
            tituloProfesor.setOpaque(false);
            this.add(tituloProfesor);

            JLabel tituloGrupo = new JLabel("Grupo: 1SF122", SwingConstants.CENTER);
            tituloGrupo.setFont(new Font("Pixelify Sans", Font.BOLD, 20));
            tituloGrupo.setForeground(Color.WHITE);
            tituloGrupo.setBounds(600, 740, 1536, 100); // (x, y, ancho, alto) centrado horizontalmente
            tituloGrupo.setOpaque(false);
            this.add(tituloGrupo);

            JLabel tituloEntrega = new JLabel("Fecha de entrega: 1/8/2025", SwingConstants.CENTER);
            tituloEntrega.setFont(new Font("Pixelify Sans", Font.BOLD, 20));
            tituloEntrega.setForeground(Color.WHITE);
            tituloEntrega.setBounds(600, 800, 1536, 100); // (x, y, ancho, alto) centrado horizontalmente
            tituloEntrega.setOpaque(false);
            this.add(tituloEntrega);

            JLabel tituloIntegrantes = new JLabel("Nombres: John Barahona 8-1027-1259 | Leandro Barrios 20-16-8080 | Jorge Li 3-759-2256 | Iker Martinez 8-1024-1366", SwingConstants.CENTER);
            tituloIntegrantes.setFont(new Font("Pixelify Sans", Font.BOLD, 20));
            tituloIntegrantes.setForeground(Color.WHITE);
            tituloIntegrantes.setBounds(-200, 800, 1536, 100); // (x, y, ancho, alto) centrado horizontalmente
            tituloIntegrantes.setOpaque(false);
            this.add(tituloIntegrantes);

            // Botón para iniciar el juego
            JButton botonInicio = new JButton("Iniciar Juego");
            botonInicio.setFont(new Font("Pixelify Sans", Font.PLAIN, 30));
            botonInicio.setBounds(618, 500, 300, 80); // Centrado debajo del título
            botonInicio.setOpaque(true);
            botonInicio.setBackground(Color.ORANGE);
            botonInicio.setBorderPainted(BorderFactory.createRaisedSoftBevelBorder().isBorderOpaque());
            botonInicio.setFocusPainted(false);
            botonInicio.addActionListener(startListener); // Le asignamos el listener que recibimos
            this.add(botonInicio);

            JButton botonSalir = new JButton("Salir");
            botonSalir.setFont(new Font("Pixelify Sans", Font.PLAIN, 30));
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
            utpImagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/PantallaInicioCasa/LogoUTP.png"));
            fiscImagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/PantallaInicioCasa/LogoFISC.png"));

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


