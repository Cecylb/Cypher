import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

 class Decoder {
    private File WarAndPieceChapterEncrypted = new File("lt3.txt");



    void deCodeByLetters(File WarAndPieceChapter) throws IOException {
        System.out.println("Decoding by letters: ");

        File WarAndPieceChapterDecrypted = new File("lt4.txt");
        FileOutputStream outputStream = new FileOutputStream(WarAndPieceChapterDecrypted);
        LetFreqScanner letScan = new LetFreqScanner();
        List chapterLets;
        List cypherLets;
        Map<Character, Character> crossLets = new TreeMap<>();
        Scanner sc;

        System.out.println("Chapter frequency: ");
        chapterLets = letScan.letScanner(WarAndPieceChapter);
        System.out.println("Encrypted chapter frequency: ");
        cypherLets = letScan.letScanner(WarAndPieceChapterEncrypted);
        for(int i=0; i<chapterLets.size(); i++){
            String aa = chapterLets.get(i).toString();
            Character a = aa.charAt(0);
            String bb = cypherLets.get(i).toString();
            Character b = bb.charAt(0);
            crossLets.put(b, a);
        }

for(Map.Entry<Character, Character> enthree : crossLets.entrySet()){
    System.out.println("RES: " + enthree.getKey() + "" + enthree.getValue());
}
        sc = new Scanner(WarAndPieceChapterEncrypted);
        int k=0;
            while (sc.hasNext()) {
                char[] line = sc.nextLine().toLowerCase().toCharArray();

                for (Character character : line) {
                    if (Character.isLetter(character)) {
                        if (crossLets.containsKey(character)) {
                            line[k] = crossLets.get(character);
                        }
                        String str = String.valueOf(line[k]);
                        byte[] strToBytes = str.getBytes();
                        outputStream.write(strToBytes);
                    }
                }

            }
        }
    void deCodeByPairs(File WarAndPieceChapter) throws IOException {
        System.out.println("Decoding by pairs: ");

        File WarAndPieceChapterDecrypted = new File("lt5.txt");
        FileOutputStream outputStream = new FileOutputStream(WarAndPieceChapterDecrypted);
        PairFreqScanner pairScan = new PairFreqScanner();
        List chapterTop;
        List cypherTop;
        Map<String, String> crossTops = new TreeMap<>();
        Scanner sc;
        char ch;
        char ch2;

        System.out.println("Chapter's pair frequency: ");
        chapterTop=pairScan.pairScanner(WarAndPieceChapter);
        System.out.println("Encrypted chapter's pair frequency: ");
        cypherTop=pairScan.pairScanner(WarAndPieceChapterEncrypted);

        for(int i=0; i<chapterTop.size(); i++){
            String a = chapterTop.get(i).toString().substring(0, 2);
            String b = cypherTop.get(i).toString().substring(0, 2);
            System.out.println(b + " :  " + a );
            crossTops.put(b, a);
        }
        for (Map.Entry<String, String> enttry : crossTops.entrySet()) {
            System.out.println(enttry.getKey() + ": " + enttry.getValue());
        }

        sc = new Scanner(WarAndPieceChapterEncrypted);
        while (sc.hasNext()) {
            char[] line = sc.nextLine().toLowerCase().toCharArray();
            for (int k = 0; (k + 1) < line.length; k++) {
                ch = line[k];
                ch2 = line[k + 1];
                if (Character.isLetter(ch) && Character.isLetter(ch2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(ch);
                    sb.append(ch2);
                    if(crossTops.containsKey(sb.toString())){
                     String buf = crossTops.get(sb.toString());
                     line[k]=buf.charAt(0);
                     line[k+1]=buf.charAt(1);
                    }
                }
                String str = String.valueOf(line[k]);
                byte[] strToBytes = str.getBytes();
                outputStream.write(strToBytes);
            }
        }
    }
}