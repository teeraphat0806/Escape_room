import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class sound {
    private AudioInputStream ais;
    private Clip clip;
    private URL soundurl;

    public sound(String sound_file){
        soundurl = getClass().getResource(sound_file);
        setFile();

    }
    public void setFile(){
        try {
            ais = AudioSystem.getAudioInputStream(soundurl);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void set_soundurl(String sound_file) {
        close(); // Close existing resources
        soundurl = getClass().getResource(sound_file);
        setFile();
    }
    public void play() {
        clip.stop();
        clip.setFramePosition(0); // Reset the clip to the beginning
        clip.start();
    }

    public void loop() {
        clip.stop();
        clip.setFramePosition(0); // Reset the clip to the beginning
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
    public void close() {
        if (clip != null) {
            clip.close();
        }
        if (ais != null) {
            try {
                ais.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void play_loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }


}
