package isom3320.project.game.utiliy;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javafx.scene.media.Media;

import javax.imageio.ImageIO;

public class Multimedia {
	final public static String resourcesDir = "Resources/";
	
	public static BufferedImage getImageByName(String imageName) {
		BufferedImage image = null;
		
		try {
			String imagePath = resourcesDir + imageName;
			image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			e.printStackTrace();
		}
		
		return image;
	}
	
	public static Media getMusicByName(String musicName) {
		Media music = null;
		String musicUri = new File(resourcesDir + musicName).toURI().toString();
		music = new Media(musicUri);
		return music;
	}
	
}
