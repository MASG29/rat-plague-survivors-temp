package ratplaguesurvivors.input;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;

public class SoundHandler {

    private final Map<String , Clip> clips;
    private boolean muted;

    public SoundHandler(){

        this.clips = new HashMap<>();
        this.muted = false;

    }

    public void loadSound(String key, String resourcePath) {

        try {
            unload(key);
            InputStream rawStream = getClass().getClassLoader().getResourceAsStream(resourcePath);

            if(rawStream == null){
                File file = new File(resourcePath);

                if (!file.exists()){
                    file = new File("resources",resourcePath);
                }
                if (!file.exists()){
                    System.out.println("sound resource not found" + resourcePath);
                    System.out.println("working directory" + System.getProperty("user.dir"));
                    return;
                }

                rawStream = new FileInputStream(file);
            }

            BufferedInputStream bis = new BufferedInputStream(rawStream);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bis);

            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clips.put(key, clip);

        } catch (Exception e) {

            System.out.println("Failed to load sound:" + resourcePath);
            e.printStackTrace();

        }
    }

    public void play(String key){

        if(muted) {
            return;
        }

        Clip clip = clips.get(key);

        if(clip == null) {
            System.out.println("Sound resource found: " + key);
            return;
        }

        if(clip.isRunning()) {
            clip.stop();
        }

        clip.setFramePosition(0);
        clip.start();

    }

    public void loop(String key){

        if (muted) {
            return;
        }

        Clip clip = clips.get(key);

        if(clip == null) {
            System.out.println("Sound  not loaded : " + key);
        }

        if(clip.isRunning()) {
            clip.stop();
        }

        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(String key){
        Clip clip = clips.get(key);

        if(clip == null) {
            return;
        }

        clip.stop();
        clip.setFramePosition(0);
    }

    public void pause(String key){

        Clip clip = clips.get(key);

        if(clip == null) {
            return;
        }

        if(clip.isRunning()) {

            clip.stop();
        }
    }

    public void resume(String key){

        if(muted) {
            return;
        }
        Clip clip = clips.get(key);

        if(clip == null) {
            return;
        }

        clip.start();
    }

    public void stopAll(){

        for(Clip clip : clips.values()){

            if(clip != null) {

                clip.stop();
                clip.setFramePosition(0);

            }
        }
    }

    public void unload(String key) {

        for(Clip clip : clips.values()) {

            if(clip != null){

                clip.stop();
                clip.close();

            }
        }

        clips.clear();

    }

    public void mute(){
        muted = true;

        for(Clip clip : clips.values()){
            if(clip != null && clip.isRunning()) {
                clip.stop();
            }
        }
    }

    public void unmute(){
        muted = false;
    }

    public void isMuted(){
        if(muted) {
            unmute();
        }
        else {
            mute();
        }
    }

    public boolean isMuted(String key){
        return muted;
    }
}

