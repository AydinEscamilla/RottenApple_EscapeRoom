package SystemFiles;
public class Settings {
    private int audioVolume;
    private boolean musicOn;
    private boolean soundEffectsOn;
    private int textSize;

    public Settings() {
        // defaults
        this.audioVolume = 50;
        this.musicOn = true;
        this.soundEffectsOn = true;
        this.textSize = 14;
    }

    public Settings(int audioVolume, boolean musicOn, boolean soundEffectsOn, int textSize) {
        this.audioVolume = audioVolume;
        this.musicOn = musicOn;
        this.soundEffectsOn = soundEffectsOn;
        this.textSize = textSize;
    }

    public int getAudioVolume(){
        return audioVolume;
    }

    public void setAudioVolume(int volume){
        this.audioVolume = volume;
    }

    public boolean isMusicOn(){
        return musicOn;
    }

    public void setMusicOn(boolean enabled){
        this.musicOn = enabled;
    }

    public boolean isSoundEffectsOn(){
        return soundEffectsOn;
    }

    public void setSoundEffectsOn(boolean enabled){
        this.soundEffectsOn = enabled;
    }

    public int getTextSize(){
        return textSize;
    }

    public void setTextSize(int size){
        this.textSize = size;
    }

    public void resetDefaults(){
        this.audioVolume = 50;
        this.musicOn = true;
        this.soundEffectsOn = true;
        this.textSize = 14;
    }
}
