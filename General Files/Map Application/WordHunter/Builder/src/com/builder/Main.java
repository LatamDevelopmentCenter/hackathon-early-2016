package com.builder;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        boolean visible = true;

        int x = 1000;//Vertical
        int y = 1000;//Horizontal

        String time = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(Calendar.getInstance().getTime());
        String filePath1 = "C:\\WordHunter\\File\\Word-" + time + ".txt";
        String filePath2 = "C:\\WordHunter\\File\\WordV-" + time + ".txt";
        String filePath3 = "C:\\WordHunter\\File\\WordL-" + time + ".txt";
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);
        File file3 = new File(filePath3);
        PrintStream fo1 = new PrintStream(file1);
        PrintStream fo2 = new PrintStream(file2);
        PrintStream fo3 = new PrintStream(file3);

        String[] vowels = {"a", "e", "i", "o", "u"};
        String[] consonants = {"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","y","z"};
        String[] simpleDisplayType = {"h","v"};
        String[] complexDisplayType = {"d"};
        String[] complexDisplayTypeVariator = {"l","r"};
        String[] invertedDisplayType = {"i"};
        String[] planets = {"alderaan","hoth","bespin","coruscant","kamino","mustafar","rodia","dantooine","sullust"};

        int sizing = 10;
        int[] vowelTendency = {1,1,1,0,0,0,0,0,0,0}; //Tendency to be a vowel
        int[] simpleTypeTendency = {1,1,1,1,1,1,0,0,0,0}; //Tendency to be horizontal or vertical, which means a simple type
        int[] horizontanlTendency = {1,1,1,1,1,0,0,0,0,0}; //Tendency to be horizontal in a simple type
        int[] invertedTendency = {1,1,1,1,0,0,0,0,0,0}; //Tendency to be inverted
        int[] leftOrientationComplexTypeTendency = {1,1,1,1,1,0,0,0,0,0}; //Tendency to be left oriented for a complex type

        boolean simple = false;
        boolean horizontal = false;
        boolean inverted = false;
        boolean left = false;
        boolean fills = false;
        boolean spaceFree = false;
        int xPos = 0;
        int yPos = 0;
        int xSize = 0;
        int ySize =0;
        String[][] map = new String[x][y];
        String wordHelper = "";

        Random random = new Random();

        System.out.println("Building map");

        for(int i = 0; i < planets.length; i++) {
            simple = false;
            horizontal = false;
            inverted = false;
            left = false;
            fills = false;
            spaceFree = false;
            xPos = 0;
            yPos = 0;
            xSize = 0;
            ySize =0;
            wordHelper = "";

            simple = (simpleTypeTendency[random.nextInt(sizing)] == 1);
            horizontal = (simple ? (horizontanlTendency[random.nextInt(sizing)] == 1) : false);
            inverted = (invertedTendency[random.nextInt(sizing)] == 1);
            left = (!simple ? (leftOrientationComplexTypeTendency[random.nextInt(sizing)] == 1) : false);

            xSize = (simple && !horizontal) ? 1 : planets[i].length();
            ySize = (simple && horizontal) ? 1 : planets[i].length();

            while (!fills || !spaceFree) {
                xPos = random.nextInt(x - xSize);
                yPos = random.nextInt(y - ySize);

                fills = (xPos + xSize < x - 1 && yPos + ySize < y - 1);
                spaceFree = SpaceNotBeenUsed(xSize, ySize, xPos, yPos, map);
            }

            fo3.println((planets[i] + " X:" + (xPos + 1) +
                    " Y:" + ((!simple && !left ?  yPos + ySize  : yPos + 1)) +
                    " Orientation:" + (simple ?
                                (horizontal ? "Horizontal" : "Vetical") :
                                (left ? "Diagonal Left" : "Diagonal Right"))) +
                    " Inverted:" + (inverted ? "Yes" : "No")
            );

            wordHelper = planets[i];
            wordHelper = inverted ? InvertWord(wordHelper) : wordHelper;

            if(simple){
                //Horizontal
                if(horizontal) {
                    for(int j = 0; j < xSize; j++){
                        map[j + xPos][yPos] = String.valueOf(wordHelper.charAt(j));
                    }
                }
                //Vertical
                else{
                    for(int j = 0; j < ySize; j++){
                        map[xPos][j + yPos] = String.valueOf(wordHelper.charAt(j));
                    }
                }
            }
            //Diagonal Left(Top on left)
            else if (left){
                for(int j = 0; j < xSize; j++){
                    map[xPos + j][yPos + j] = String.valueOf(wordHelper.charAt(j));
                }
            }
            //Diagonal Right(Top on right)
            else{
                for(int j = 0; j < xSize; j++){
                    map[xPos + j][yPos + ySize - j - 1] = String.valueOf(wordHelper.charAt(j));
                }
            }
        }

        String stringMapLine = "";
        String stringMapLineV = "";

        System.out.println("Writing map");

        for(int i = 0; i < x; i++){
            stringMapLine = "";
            stringMapLineV = "";

            for(int j = 0; j < y; j++){
                stringMapLine += map[i][j] != null ? map[i][j] :
                        ((vowelTendency[random.nextInt(sizing)]) == 1 ? vowels[random.nextInt(4)]
                                : consonants[random.nextInt(20)]);

                stringMapLineV += map[i][j] != null ? map[i][j] : "_";
            }

            fo1.println(stringMapLine);
            fo2.println(stringMapLineV);
        }

        fo1.close();
        fo2.close();
        fo3.close();

        System.out.println("Done");

    }

    static private boolean SpaceNotBeenUsed(int xSize, int ySize, int xPos, int yPos, String[][] map){
        boolean result = true;
        for(int i = xPos; i < xPos + xSize; i++){
            for(int j = yPos; j < yPos + ySize; j++){
                result &= (map[i][j] == null);
            }
        }
        return  result;
    }
    static private String InvertWord(String word){
        String newWord = "";
        for(int i = word.length() - 1; i >= 0; i--){
            newWord += String.valueOf(word.charAt(i));
        }
        return  newWord;
    }

}
