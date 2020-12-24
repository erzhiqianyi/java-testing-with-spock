package top.erzhiqian.spock.sample.file;

import java.util.*;

/**
 * A sample class that takes a block of text and counts the occurences of words
 */
public class WordDetector {

    private Map<String, Integer> countPerWord = new HashMap<>();

    public void parseText(String text) {
        countPerWord.clear();
        feedText(text);
    }

    public WordDetector feedText(String text) {
        String[] words = text.split("[\\s,:]"); //Code used only for illustration purposes
        for (String word : words) {
            recordCount(word);
        }
        return this;
    }


    private void recordCount(String word) {
        if (!countPerWord.containsKey(word)) {
            countPerWord.put(word, 0);
        }
        int now = countPerWord.get(word);
        countPerWord.put(word, now + 1);
    }

    public int wordsFound() {
        return countPerWord.size();
    }

    public Collection<String> duplicatesFound() {
        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : countPerWord.entrySet()) {
            int count = entry.getValue();
            if (count > 1) {
                result.add(entry.getKey());
            }
        }
        return result;
    }
}
