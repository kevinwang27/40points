package GUI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SuitImages {

    private static String path = "bin/SuitImgs" + File.separator;
    private static String fileType = ".png";

    private static BufferedImage spade;
    private static BufferedImage heart;
    private static BufferedImage diamond;
    private static BufferedImage club;

    public static BufferedImage getDiamond() {
        return diamond;
    }

    public static BufferedImage getHeart() {
        return heart;
    }

    public static BufferedImage getSpade() {
        return spade;
    }

    public static BufferedImage getClub() {
        return club;
    }

    public static void BufferImages() throws IOException {
        spade = ImageIO.read(new File(path + "spade" + fileType));
        heart = ImageIO.read(new File(path + "heart" + fileType));
        diamond = ImageIO.read(new File(path + "diamond" + fileType));
        club = ImageIO.read(new File(path + "club" + fileType));
    }
}
