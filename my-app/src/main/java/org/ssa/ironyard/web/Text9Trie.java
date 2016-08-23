package org.ssa.ironyard.web;

import java.util.List;

public interface Text9Trie {
    
     /**
     *  adds a word to this tries "dictionary"
     */
     void addWord(String word);

     boolean contains(String word);

     boolean remove(String word);

     void clear();
     
     /**
     * @ return a 'logical' set of words in ascending lexical order
     */
     List<String> suggest(String digits);
}
