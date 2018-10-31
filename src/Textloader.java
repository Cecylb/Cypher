import java.io.File;
import java.util.Scanner;

class Textloader {
     File getFile(){
         File WarAndPiece;
         Scanner sc = new Scanner(System.in);
         String location;
         System.out.println("Enter the location of your file:");
         location = sc.nextLine();
         WarAndPiece = new File(location);
         while (!WarAndPiece.exists()) {
                System.out.println("Error: file not found! Please type again: ");
                location = sc.nextLine();
                WarAndPiece = new File(location);
         }
         System.out.println("File was successfully loaded");
         return(WarAndPiece);
     }
}

