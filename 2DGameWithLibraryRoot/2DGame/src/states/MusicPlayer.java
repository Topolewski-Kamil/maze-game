package states;

import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Class that adds and lets to play different music in the background
 */
public class MusicPlayer implements Runnable {

	private ArrayList<String> musicFiles;
	private int currentSongIndex;
	public static Boolean check = true;

	/**
	 * Loads music files
	 * @param files
	 */
	public MusicPlayer(String... files) {
		musicFiles = new ArrayList<String>();
		for (String file : files)
			musicFiles.add("res1/sounds/" + file + ".wav");
	}

	/**
	 * Loads the music from the file and sets its volume
	 * @param fileName
	 */
	private void playSound(String fileName) {
		try {
			File soundFile = new File(fileName);
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-30); // set volume of the sound
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Starts the music
	 */
	@Override
	public void run() {
		playSound(musicFiles.get(currentSongIndex));
	}

}
