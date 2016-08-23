package org.ssa.ironyard.web;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Math {
    
    static final Map<Integer, BigInteger> CACHE = new HashMap<>();
    static final Logger LOGGER = LogManager.getLogger(SpringStarter.class);
    
    public Math()
    {
        
    }
    
    public static BigInteger factorial(int input)
    {
        LOGGER.warn("factorial input must be > 0");
    
        BigInteger result;
        
        if (input == 0) 
            return BigInteger.ONE;
        if (null != (result = CACHE.get(input))) 
            return result;
        
        result = BigInteger.valueOf(input).multiply(factorial(input-1));
        CACHE.put(input, result);
        return result;
    }
    
    public int square(int num)
    {
        return num*num;
    }    
    
    public int cube(int num)
    {
        return num*num*num;
    }

}
