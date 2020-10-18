package com.company;


/**
 * Class represent card image (hand or suit), also contain images patterns
 */
public class CardImage {

    public static final String TWO = "       ******                 **********              ************            *****    *****           ***       ****            *         ***                      ***                      ***                     ****                     ***                     ****                    ****                    ****                    ****                    ****                   *****                   ****                    ****                    ****                    ***************          ***************          ***************                                 ";
    public static final String THREE = "   *************            *************            **************                    ****                    ****                    ****                    ****                     ***                     ***                     ****                     *******                  ********                      ****                      ****                     ****                      ***                     ****           **        ***           *****     ****            ************              **********                 *******                                     ";
    public static final String FOUR = "             ***                     ****                    *****                   ******                   ******                  *** ***                 ***  ***                ***   ***               ****   ***              ****    ***              ***     ***             ***      ***            ***       ***           ***        ***          ******************        *****************        *****************                   ***                      ***                      ***                      ***                      ***                                  ";
    public static final String FIVE = "    ************             ************             ************             **                      ***                      ***                      ***                      ***                      ***   **                 ***********              ************              **      ****                      ****                      ***                      ***                      ***                      ***           **        ****          *****     ****           **************             **********                 *******                                     ";
    public static final String SIX = "       *******                ***********             *************           ******   ****            ****       *            ****                     ****                     ***                     ****   *****             **** *********           ***************          *******   ******         *****       ****         ****         ****        ****         ****        ****         ****         ***         ****         ****       ****           ****     *****           *************             ***********                *******                                     ";
    public static final String SEVEN = "  ***************          ***************          ***************                     ****                    ****                     ****                    ****                     ****                    ****                     ****                    ****                     ****                    ****                     ****                    ****                     ****                    ****                     ****                    ****                    *****                    ****                    *****                                          ";
    public static final String EIGHT = "       ******                 **********              ************            ****     ****            ***       ****           **         ***           **         ***           ***       ****           ***       ***             ****   ****               *********               ***********             ****     ****           ****       ****          ***         ***          ***         ****         ***         ****         ***         ***          ****       ****           *************             ***********                *******                                     ";
    public static final String NINE = "       *******                **********              ************             ****     ****           ****       ****          ***         ***          ***         ***          ***         ****         ***         ****         ***        *****         ****      ******          ***************           ********* ****             *****   ***                      ***                      ***                     ****                    ****            ***     *****           *************             ***********                *******                                     ";
    public static final String TEN = "   ****        ******    *******      **********  *******     ************ *******    *****    ******  ****   *****      ****   ****   ****        ***   ****  ****          **   ****  ****          **   ****  ****          **   ****  ***            *   ****  ***            *   ****  ***            *   ****  ***            *   ****  ****          **   ****  ****          **   ****  ****          **   ****   ****        ***   ****   *****      ****   ****    *****    *****   ****     ************    ****      **********     ****       ********                            ";
    public static final String ACE = "                               ****                     ****                    *****                    ******                  *** ***                  ***  ***                 **   ***                ***    ***               ***    ***              ***     ****             ***      ***            ***       ***            ***        ***          ***************          ****************         ****************         **           ****        **            ***        *             ***        *              ***       *              ***                      *  *      ";
    public static final String JACK = "          ****                     ****                     ****                     ****                     ****                     ****                     ****                     ****                     ****                     ****                     ****                     ****                     ****                     ****                     ****                     ****             *       ****            ***     *****           *****   *****             ************              **********                *******                                        ";
    public static final String QUEEN = "         *******                ***********             **************         ******     ******        ****         ****       ****           ****     ****             ****    ***              ****    ***               ***    ***               ***   ****               ****  ****               ****   ***               ***    ***         **    ***    ***        ***** ****    ****        *********     ****        *******      *****        ******       ******     *******        ******************         ***********  ***           *******     *                            ";
    public static final String KING = "  ***           ****       ***          ****        ***         ****         ***        ****          ***        ***           ***       ***            ***      ***             ***     ***              ***    ***               ***   ****               ***  *****               ***********              ******  ****             *****    ****            ****     ****            ****      ****           ***        ****          ***         ****         ***         ****         ***          ****        ***           ****       ****           ****                             ";

    public static final String DIAMONDS_STRING_PATTERN = "                                                                **                ****               ****              ******             *******           *********          *********         ***********       ************        **********         **********          ********            *******             *****              *****               ***                 *                                                                   ";
    public static final String CLUBS_STRING_PATTERN = "                                                                ****              ******             ******             ******             ******             ******             ******            ****** *         ************       ************       ************       *************      *************      ************       ************        ***  * ***             **                ****               ****                           ";
    public static final String HEARTS_STRING_PATTERN = "                                                                                                                     ****   ****       *************      *************      *************      *************      *************      *************      *************       ***********         *********           *******            *******             *****               ***                                                                  ";
    public static final String SPADES_STRING_PATTERN = "                                                                  *                 ***                ***               *****             *******           **********         **********        ************      *************      **************     **************     **************     **************     *************      *************       **** * ****             *                 ***                ***                           ";

    /**
     * Name, used for output value
     */
    private String name;

    /**
     * String patter for recognizing cards
     */
    private String pattern;


    public CardImage(String name, String pattern) {
        this.name = name;
        this.pattern = pattern;
    }

    /**
     * Compare two strings and get levenshtein length
     *
     * @param targetStr target string
     * @param sourceStr source string
     * @return length which represent count of operations to transform one string to an other
     */
    public static int getLevenshteinLength(String targetStr, String sourceStr) {
        int m = targetStr.length(), n = sourceStr.length();
        int[][] delta = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++)
            delta[i][0] = i;
        for (int j = 1; j <= n; j++)
            delta[0][j] = j;
        for (int j = 1; j <= n; j++)
            for (int i = 1; i <= m; i++) {
                if (targetStr.charAt(i - 1) == sourceStr.charAt(j - 1))
                    delta[i][j] = delta[i - 1][j - 1];
                else
                    delta[i][j] = Math.min(delta[i - 1][j] + 1,
                            Math.min(delta[i][j - 1] + 1, delta[i - 1][j - 1] + 1));
            }
        return delta[m][n];
    }

    public String getName() {
        return name;
    }

    public String getPattern() {
        return pattern;
    }
}
