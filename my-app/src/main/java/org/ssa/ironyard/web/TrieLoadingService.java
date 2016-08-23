package org.ssa.ironyard.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrieLoadingService {
    
    static final Logger LOGGER = LogManager.getLogger(TrieLoadingService.class);

    @Autowired
    Text9Trie trie;

    public TrieLoadingService(Text9Trie trie) throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("corncob_lowercase.txt");
        File file = new File(resource.toURI());
        LOGGER.debug("file {}, exists?{}", file, file.exists());

        this.trie = trie;
        LOGGER.debug("file {}, exists?{}", file, file.exists());
        try (BufferedReader reader = Files.newBufferedReader(file.toPath(), Charset.defaultCharset())) 
        {
            String line;
            
            while (null != (line = reader.readLine())) 
            {
                trie.addWord(line.trim());
            }
        } catch (IOException iex) {
            System.err.println(iex);
        }
    }

}
