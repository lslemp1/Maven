package org.ssa.ironyard.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TrieNode
{

    char letter;
    final Map<Character, TrieNode> alphabet = new HashMap<>();
    boolean endOfWord = false;

    public TrieNode(char letter)
    {
        this.letter = letter;
    }

    public char getLetter()
    {
        return letter;
    }

    public void add(String word)
    {
        if (word.length() > 0)
        {
            TrieNode firstChar = new TrieNode(word.charAt(0));

            if (alphabet.get(word.charAt(0)) == null)
                alphabet.put(word.charAt(0), firstChar);

            alphabet.get(word.charAt(0)).add(word.substring(1));
        } else
            endOfWord = true;
    }

    public String getWordCheckString(String word)
    {
        if (word.length() > 0)
        {
            if (alphabet.get(word.charAt(0)) != null)
            {
                String wordCheck = "" + alphabet.get(word.charAt(0)).letter;
                return wordCheck += alphabet.get(word.charAt(0)).getWordCheckString(word.substring(1));
            }
        } else if (endOfWord)
            return "|";
        return "";
    }

    public void remove(String word)
    {
        if (word.length() == 0)
            endOfWord = false;
        else
            alphabet.get(word.charAt(0)).remove(word.substring(1));

    }

    public int nextLettersSize()
    {
        return alphabet.size();
    }

    public List<String> suggest(String digits, String word)
    {
        String[] digitArray =
        { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        List<String> list = new ArrayList<>();
        String currentWord = word;
        word += letter;

        if (digits.length() > 0)
        {
            int nextDigit = Character.getNumericValue(digits.charAt(0));
            String digitLetters = digitArray[nextDigit];

            for (int i = 0; i < digitLetters.length(); i++)
            {
                word = currentWord;
                char nextChar = digitLetters.charAt(i);
                if (digits.length() != 1)
                    word += nextChar;

                if (alphabet.get(nextChar) != null)
                    list.addAll(alphabet.get(nextChar).suggest(digits.substring(1), word));

            }
        } else
        {
            if (endOfWord)
            {
                System.out.println(word);
                list.add(word);
            }
            for (Entry<Character, TrieNode> e : alphabet.entrySet())
                list.addAll(e.getValue().suggest("", word));

        }

        return list;
    }
}
