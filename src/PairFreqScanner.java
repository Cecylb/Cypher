import java.io.File;
import java.io.IOException;
import java.util.*;

class PairFreqScanner {
    List pairScanner(File WarAndPieceLetterScan) throws IOException {
        char ch;  //first bigram symbol
        char ch2; //second bigram symbol
        String pair;
        long counter=0;
        Map<String, Double> top = new HashMap<>();
        Map<String, Double> topZ = new TreeMap<>();
        Map<String, Double> pairs = new HashMap<>();

        try (Scanner sc = new Scanner(WarAndPieceLetterScan)) {
            while (sc.hasNext()) {
                char[] line = sc.nextLine().toLowerCase().toCharArray();
                for (int k = 0; (k + 1) < line.length; k++) {
                    ch = line[k];
                    ch2 = line[k + 1];
                    if (Character.isLetter(ch) && Character.isLetter(ch2)) {
                        pair = String.valueOf(ch) + ch2;
                        if (pairs.containsKey(pair)) {
                            pairs.put(pair, pairs.get(pair) + 1);
                        } else {
                            pairs.put(pair, 1d);
                        }
                        counter++;
                    }
                }
            }
        }
        if(!pairs.isEmpty()){
            top = getFreqTop(pairs);
        }

        for(Map.Entry<String, Double> loop : top.entrySet()){
            topZ.put(loop.getKey(), loop.getValue()/counter);
        }

        return(sortByValue(topZ));
    }

    private Map getFreqTop(Map<String, Double> pairs){
        Map<String, Double> top = new TreeMap<>();
        double bufDig=0d;
        String bufLet="";
        int top10 = 10;
         do{
            for (Map.Entry<String, Double> entry : pairs.entrySet()) {
                if(entry.getValue()>=bufDig){
                if(!top.containsKey(entry.getKey())) {
                    bufDig=entry.getValue();
                    bufLet=entry.getKey();
                    }
                }
            }
            top.put(bufLet, bufDig);
            bufLet="";
            bufDig=0;
            top10--;
        }while (top10!=0);
        return(top);
    }

    public static <K, V extends Comparable<? super V>> List<Map.Entry<K,V>> sortByValue(Map<K, V> unsortMap) {

        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i));
        }
        return list;
    }
}
