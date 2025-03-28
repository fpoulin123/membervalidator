package membervalidator;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
	
	
	public void play(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
		
		Clip clip = AudioSystem.getClip();
		
		clip.open(audioInputStream);
		clip.start();
		
	}

}
