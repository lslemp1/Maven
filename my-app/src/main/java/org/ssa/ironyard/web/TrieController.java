package org.ssa.ironyard.web;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TrieController {

    static final Logger LOGGER = LogManager.getLogger(TrieController.class);
    
    @Autowired
    Trie trie;
    
    @ResponseBody
    @RequestMapping("/trie")
    public List<String> suggestion(HttpServletRequest request) throws URISyntaxException
    {
        LOGGER.debug("will attempt to load corncob.txt");
        URL resource = getClass().getClassLoader().getResource("corncob_lowercase.txt");
        File file = new File(resource.toURI());
        
        LOGGER.debug("file {}, exists? {}", file, file.exists());
        if(null != request.getParameter("digits"))
            return Arrays.asList(request.getParameter("digits"));
        
        //LOGGER.debug("loaded resource {}", resource);
        
        return Arrays.asList("not", "implemented", "yet");
        
    }
    
    @RequestMapping("/t9Word")
    @ResponseBody
    public List<String> suggestions(HttpServletRequest request) throws URISyntaxException
    {
        String parameter = request.getParameter("digits");        
        return trie.suggest(parameter);
    }
    
}
