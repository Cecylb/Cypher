import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Cypher {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        File WarAndPiece = null;
        int menu=0;
        int check=0;
        System.out.println("Welcome. What are you going to do?\n" +
                           "1. Upload a text file (Text)\n" +
                           "2. Encipher a chapter\n" +
                           "3. Decipher a chapter\n" +
                           "4. Letters' frequency check\n" +
                           "5. Pairs' frequency check\n" +
                           "6. Close the programm");
        do{
            System.out.println("Please type a number from 1 to 6: ");
            try {
                menu = sc.nextInt();
            } catch ( Exception e){
                sc.next();
                }
        switch (menu){
            case 1:
                check=1;
                Textloader load = new Textloader();
                WarAndPiece=load.getFile();
                break;
            case 2:
                if(check==0){
                    System.out.println("You must load a text first!");
                    break;
                }else {
                    Encoder code = new Encoder();
                    code.enCode(WarAndPiece);
                    break;
                }
            case 3:
                if(check==0){
                    System.out.println("You must load a text first!");
                    break;
                }else {
                    Decoder decode = new Decoder();
                    decode.deCodeByLetters(WarAndPiece);
                    decode.deCodeByPairs(WarAndPiece);
                    break;
                }
            case 4:
                if(check==0){
                    System.out.println("You must load a text first!");
                    break;
                }else{
                    LetFreqScanner letScan = new LetFreqScanner();
                    letScan.letScanner(WarAndPiece);
                }
                break;
            case 5:
                if(check==0){
                    System.out.println("You must load a text first!");
                    break;
                }else{
                    PairFreqScanner pairScan = new PairFreqScanner();
                    pairScan.pairScanner(WarAndPiece);
                }
                break;
            case 6:
                break;
            default:
                System.out.println("Typing error!");
        }
        }while (menu!= 6);
    }
}
