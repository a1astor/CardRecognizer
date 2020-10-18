package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Class performs methods for work with images
 */
public class ImageUtils {

    /**
     * Used for determinate shadow cards
     */
    public static final int[] ARRAY_RGB_OF_SHADOW_CARD_SURFACE = {120, 120, 120};

    /**
     * Load and retern image form file
     *
     * @param imageFile image file
     * @return loaded image
     */
    public static BufferedImage loadImages(File imageFile) {
        try {
            FileInputStream stream = new FileInputStream(imageFile);
            return ImageIO.read(stream);
        } catch (IOException e) {
            System.out.println("Can't load image from " + imageFile.getName());
        }
        return null;
    }

    /**
     * Create and return binary image
     *
     * @param subimage source image
     * @return binary image
     */
    public static BufferedImage getBinaryImage(BufferedImage subimage) {
        changeColor(subimage);
        BufferedImage binaryHand = new BufferedImage(subimage.getWidth(), subimage.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g = binaryHand.createGraphics();
        g.drawImage(subimage, 0, 0, binaryHand.getWidth(), binaryHand.getHeight(), null);
        g.dispose();
        return binaryHand;
    }

    /**
     * Change image color, used for remove shadow
     *
     * @param img  source image
     */
    private static void changeColor(BufferedImage img) {
        int MASK = 0x00ffffff;
        int sourceRgb = ARRAY_RGB_OF_SHADOW_CARD_SURFACE[0] << 16 | ARRAY_RGB_OF_SHADOW_CARD_SURFACE[1] << 8 | ARRAY_RGB_OF_SHADOW_CARD_SURFACE[2];
        int destRgb = -1;
        int w = img.getWidth();
        int h = img.getHeight();
        int[] arrRgb = img.getRGB(0, 0, w, h, null, 0, w);
        for (int i = 0; i < arrRgb.length; i++) {
            if ((arrRgb[i] & MASK) == sourceRgb) {
                arrRgb[i] ^= destRgb;
            }
        }
        img.setRGB(0, 0, w, h, arrRgb, 0, w);
    }

    /**
     * Get binary string for image
     *
     * @param subimage source image
     * @return string which represent image where space - while color, "*" - black
     */
    public static String getBinaryString(BufferedImage subimage) {
        short whiteBg = -1;
        StringBuilder binaryString = new StringBuilder();
        for (short y = 1; y < subimage.getHeight(); y++) {
            for (short x = 1; x < subimage.getWidth(); x++) {
                int rgb = subimage.getRGB(x, y);
                binaryString.append(rgb == whiteBg ? " " : "*");
            }
        }
        return binaryString.toString();
    }
}