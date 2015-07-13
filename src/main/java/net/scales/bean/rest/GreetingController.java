package net.scales.bean.rest;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import net.scales.model.ScalesData;
import net.scales.service.ScalesDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    private ScalesDataService scalesDataService;

    @RequestMapping("/greeting")
    public List<ScalesData> greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	List<ScalesData> scales = scalesDataService.getScalesInByPeriod(new Date(0), new Date()); 
        return scales;
    }
}