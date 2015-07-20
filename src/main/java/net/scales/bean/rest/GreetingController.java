package net.scales.bean.rest;

import java.util.Date;
import java.util.List;

import net.scales.model.Scales;
import net.scales.service.ScaleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    private ScaleService scaleService;

    @RequestMapping("/greeting")
    public List<Scales> greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	List<Scales> scales = scaleService.getScalesInByPeriod(new Date(0), new Date(), 39L); 
        return scales;
    }
}