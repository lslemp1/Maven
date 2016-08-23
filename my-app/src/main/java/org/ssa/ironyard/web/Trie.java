package org.ssa.ironyard.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Trie implements Text9Trie {

    final Map<Character, TrieNode> alphabet = new HashMap<>();
    
    static {

    }

    @Override
    public void addWord(String word) {
        if (word.length() > 0) {
            TrieNode firstChar = new TrieNode(word.charAt(0));

            if (alphabet.get(word.charAt(0)) == null)
                alphabet.put(word.charAt(0), firstChar);

            alphabet.get(word.charAt(0)).add(word.substring(1, word.length()));
        }
    }

    @Override
    public boolean contains(String word) {
        String wordCheck = "";

        if (alphabet.get(word.charAt(0)) != null)
            wordCheck += alphabet.get(word.charAt(0)).letter
                    + alphabet.get(word.charAt(0)).getWordCheckString(word.substring(1));

        if (wordCheck.endsWith("|") && word.equals(wordCheck.substring(0, wordCheck.length() - 1)))
            return true;
        return false;
    }

    @Override
    public boolean remove(String word) {
        if (contains(word)) {
            alphabet.get(word.charAt(0)).remove(word.substring(1));

            if (!contains(word))
                return true;

        }
        return false;
    }

    @Override
    public void clear() {
        alphabet.clear();
    }

    @Override
    public List<String> suggest(String digits) // 234
    {
        // 2-> abc 3 -> def 4 -> ghi 5 -> jkl 6 -> mno 7 -> pqrs 8 -> tuv 9 ->
        // wxyz
        String[] digitArray = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        ArrayList<String> list = new ArrayList<>();

        if (digits.length() > 0) {
            int nextDigit = Character.getNumericValue(digits.charAt(0));
            String digitLetters = digitArray[nextDigit];

            for (int i = 0; i < digitLetters.length(); i++) {
                char nextChar = digitLetters.charAt(i);
                String words = "" + nextChar;
                if (digits.length() == 1)
                    words = "";
                list.addAll(alphabet.get(nextChar).suggest(digits.substring(1), words));
            }
        }

        return list;
    }

}
