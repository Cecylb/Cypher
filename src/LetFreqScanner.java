import java.io.File;
import java.io.IOException;
import java.util.*;

class LetFreqScanner {
    public List letScanner(File WarAndPieceLetterScan) throws IOException {
        Map<Character, Double> characters = new HashMap<>();
        Map<Character, Double> characterZ = new TreeMap<>();
        long counter=0;
        try (Scanner sc = new Scanner(WarAndPieceLetterScan)) {
            while (sc.hasNext()) {
                char[] line = sc.nextLine().toLowerCase().toCharArray();
                for (Character character : line) {
                    if (Character.isLetter(character)) {
                        if (characters.containsKey(character)) {
                            characters.put(character, characters.get(character) + 1);
                        } else {
                            characters.put(character, 1d);
                        }
                        counter++;
                    }
                }
            }
        }

        for(Map.Entry<Character, Double> loop : characters.entrySet()){
            characterZ.put(loop.getKey(), loop.getValue()/counter);
        }

        return (sortByValue(characterZ));
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
