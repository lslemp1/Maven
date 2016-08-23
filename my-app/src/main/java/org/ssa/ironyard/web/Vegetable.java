package org.ssa.ironyard.web;

public class Vegetable {
    
    private final Color color;
    private final String name;
    
    public Vegetable(Color color, String name)
    {
        this.color = color;
        this.name = name;            
    }
    
    public Color getColor()
    {
        return this.color;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public enum Color
    {
        RED, GREEN, YELLOW;
    }

}
