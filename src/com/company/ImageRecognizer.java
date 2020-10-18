package com.company;

import java.awt.image.BufferedImage;

/**
 * Main class for recognizing cards images, recognizing based on determination of Levenshtain length
 */
public class ImageRecognizer {

    private static final int COMPARE_IMAGE_HEIGHT = 24;
    private static final int COMPARE_IMAGE_WIDTH = 26;
    private static final int SUIT_WIDTH = 20;
    private static final int SUIT_HEIGHT = 18;

    private static final int FIRST_CARD_INDENT = 150;
    private static final int CARD_INDENT = 71;
    private final CardImage[] hands = new CardImage[13];
    private final CardImage[] suits = new CardImage[4];
    private final int handYRelativeCenter = 8;
    private final int suitYRelativeCenter = 34;

    public ImageRecognizer() {
        suits[0] = new CardImage("h", CardImage.HEARTS_STRING_PATTERN);
        suits[1] = new CardImage("d", CardImage.DIAMONDS_STRING_PATTERN);
        suits[2] = new CardImage("c", CardImage.CLUBS_STRING_PATTERN);
        suits[3] = new CardImage("s", CardImage.SPADES_STRING_PATTERN);

        hands[0] = new CardImage("2", CardImage.TWO);
        hands[1] = new CardImage("3", CardImage.THREE);
        hands[2] = new CardImage("4", CardImage.FOUR);
        hands[3] = new CardImage("5", CardImage.FIVE);
        hands[4] = new CardImage("6", CardImage.SIX);
        hands[5] = new CardImage("7", CardImage.SEVEN);
        hands[6] = new CardImage("8", CardImage.EIGHT);
        hands[7] = new CardImage("9", CardImage.NINE);
        hands[8] = new CardImage("10", CardImage.TEN);
        hands[9] = new CardImage("J", CardImage.JACK);
        hands[10] = new CardImage("Q", CardImage.QUEEN);
        hands[11] = new CardImage("K", CardImage.KING);
        hands[12] = new CardImage("A", CardImage.ACE);
    }

    /**
     * Main method for recognizing cards, first step we cut image of hand and suit from given image and then try to recognized then
     *
     * @param image image for recognizing
     * @return string with names of hands and suits
     */
    public String recognizedCards(BufferedImage image) {
        String result = "";
        int maxCardCount = 6;
        int handY = Math.round(image.getHeight() / 2) + handYRelativeCenter;
        int suitY = Math.round(image.getHeight() / 2) + suitYRelativeCenter;

        for (int i = 0; i < maxCardCount; i++) {
            BufferedImage subimage = image.getSubimage(FIRST_CARD_INDENT + Math.round(CARD_INDENT * i), handY, COMPARE_IMAGE_WIDTH, COMPARE_IMAGE_HEIGHT);
            BufferedImage binaryHand = ImageUtils.getBinaryImage(subimage);

            BufferedImage suitImage = image.getSubimage(FIRST_CARD_INDENT + (CARD_INDENT * i), suitY, SUIT_WIDTH, SUIT_HEIGHT);
            BufferedImage binarySuit = ImageUtils.getBinaryImage(suitImage);

            String hand = recognizeHand(binaryHand);
            if (hand != null) {
         
                hand += recognizeSuit(binarySuit);
                result += hand;
            } else {
                break;
            }
        }
        return result;
    }

    private String recognizeSuit(BufferedImage subimage) {
        return getNameByLevenshtainLength(ImageUtils.getBinaryString(subimage), suits);
    }

    private String recognizeHand(BufferedImage subimage) {
        return getNameByLevenshtainLength(ImageUtils.getBinaryString(subimage), hands);
    }

    private static String getNameByLevenshtainLength(String str, CardImage[] array) {
        if (str.trim().length() == str.length()) {
            return null;
        }
        int min = 1000000;
        String findSymbol = "";
        for (int i = 0; i < array.length; i++) {
            int levenshtein = CardImage.getLevenshteinLength(str, array[i].getPattern());
            if (levenshtein < min) {
                min = levenshtein;
                findSymbol = array[i].getName();
            }
        }

        return findSymbol;
    }
}
