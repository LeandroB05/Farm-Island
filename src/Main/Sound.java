package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];  //para almacenar la ruta de los sonidos

    public Sound() {
        soundURL[0] = getClass().getResource("../Sound/InGameMusic.wav");   // musica del juego
        soundURL[1] = getClass().getResource("../Sound/MainMenuMusic.wav"); // musica del menu principal
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);  //agarramos el sonido de la ruta
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void play() {
        clip.start();  //para que empiece a sonar
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);  //para que se repita el sonido
    }
    public void stop() {
        clip.stop();
    }
}
