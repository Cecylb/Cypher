import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
 class Encoder {
     private String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
     private Map<Character, Character> alphabetP = new TreeMap<>();
     private File WarAndPieceEncrypted = new File("lt3.txt");

     void enCode(File WarAndPieceLetterScan)throws IOException {
        FileOutputStream outputStream = new FileOutputStream(WarAndPieceEncrypted);
         Scanner sc2 = new Scanner(System.in);

        System.out.println("Enter the encryption password. The format is '00' ");
        int password = sc2.nextInt();

        for(int i =0; i<25; i++){
            Character ch=alphabet[i].charAt(0);
            Character ch2;
            if((i+password)<25){
                ch2 = alphabet[i+password].charAt(0);
                alphabetP.put(ch, ch2);
            }else{
                ch2 = alphabet[i+password-25].charAt(0);
                alphabetP.put(ch, ch2);
            }
        }
         try (Scanner sc = new Scanner(WarAndPieceLetterScan)) {
             while (sc.hasNext()) {
                 char[] line = sc.nextLine().toLowerCase().toCharArray();
                 for (int k = 0; (k + 1) < line.length; k++) {
                     Character character = line[k];
                     if (alphabetP.keySet().contains(line[k])) {
                         line[k] = alphabetP.get(character);
                     }
                     String str = String.valueOf(line[k]);
                     byte[] strToBytes = str.getBytes();
                     outputStream.write(strToBytes);
                 }
             }
         }
    }
   /* public static Object getKey(Map alphabet, Object value){
         for(Object o : alphabet.keySet()){
             if(alphabet.get(o).equals(value)){
                 return o;
             }
         }
         return null;
    }
    public File getFile(){
         return WarAndPieceEncrypted;
    }*/
}

