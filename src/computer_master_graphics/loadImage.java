package computer_master_graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class loadImage {
    public static BufferedImage image;
    public static BufferedImage entities;
    public static BufferedImage player,enemy;
    
    public static void init(){
        image = imageLoader("/sky_back.png");
        entities = imageLoader("/players.png");
        crop();
    }
    
    public static BufferedImage imageLoader(String path){
    try{
        return ImageIO.read(loadImage.class.getResource(path));
        
    }catch(IOException e){
        e.printStackTrace();
        System.exit(1);
    }
    return null;
    }
    public static void crop(){
        enemy = entities.getSubimage(0, 5, 102, 120);
        player = entities.getSubimage(104, 5, 104, 120);
        
    }
}
