package isom3320.project.game.utiliy;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
	
}
