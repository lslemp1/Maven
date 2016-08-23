package org.ssa.ironyard.web;

import java.math.BigInteger;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssa.ironyard.web.Olympics.Country;
import org.ssa.ironyard.web.Vegetable.Color;

@Controller
public class GreetingController {
    static final Logger LOGGER = LogManager.getLogger(SpringStarter.class);
    
    @Autowired
    WeatherService weather;
        

    @RequestMapping("/")
    @ResponseBody
    public String greet() {
        return "Our first web app";
    }

    @RequestMapping("/foo")
    @ResponseBody
    public Map<String, LocalDate> now() {
        Map<String, LocalDate> model = new HashMap<>();
        model.put("now", LocalDate.now());
        return model;
    }

    @RequestMapping("/zucchini")
    @ResponseBody
    public Vegetable zucchini() {
        return new Vegetable(Color.YELLOW, "zucchinni");
    }

    @RequestMapping("/usaOlympics")
    @ResponseBody
    public Olympics usa() 
    {
        LOGGER.warn("USA medals - 2016 Rio Olympics");
        return new Olympics(Country.USA, 121);
    }

    @RequestMapping("/chinaOlympics")
    @ResponseBody
    public Olympics china() {
        return new Olympics(Country.CHINA, 70);
    }

    @RequestMapping("/britainOlympics")
    @ResponseBody
    public Olympics britain()
    {
    return new Olympics(Country.BRITAIN, 67);
    }

    @RequestMapping("/factorial")
    @ResponseBody
    public BigInteger factorial() {
        BigInteger b = Math.factorial(5);
        return b;
    }
    
    @RequestMapping("/temp")
    @ResponseBody
    public float currentTemp()
    {
        return this.weather.temperature();
    }
    
    @RequestMapping("/param")
    @ResponseBody
    public String paramReturn(HttpServletRequest request) throws URISyntaxException
    {
        String param = request.getParameter("type");
        return param;
    }
    

}
