package com.company;

import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        File directoryPath = new File(args.length != 0 ? args[0] : System.getProperty("user.dir"));
        File filesList[] = directoryPath.listFiles();
        if (filesList == null) {
            System.out.println("Cant find image files");
            return;
        }

        for (File file : filesList) {
            BufferedImage bufferedImage = ImageUtils.loadImages(file);
            if (bufferedImage != null) {
                ImageRecognizer imageRecognizer = new ImageRecognizer();
                String name = imageRecognizer.recognizedCards(bufferedImage);
                System.out.println(file.getName() + " " + name);
            }
        }
    }
}


