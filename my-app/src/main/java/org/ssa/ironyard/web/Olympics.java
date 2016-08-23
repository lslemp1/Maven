package org.ssa.ironyard.web;

public class Olympics {
    
    private final Country country;
    private final int medals;
    
    public Olympics(Country country, int medals)
    {
        this.country = country;
        this.medals = medals;            
    }
    
    public Country getCountry()
    {
        return this.country;
    }
    
    public int getMedals()
    {
        return this.medals;
    }
    
    public enum Country
    {
        USA, BRITAIN, CHINA;
    }

}
