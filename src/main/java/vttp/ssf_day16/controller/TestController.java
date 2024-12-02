package vttp.ssf_day16.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vttp.ssf_day16.model.Country;
import vttp.ssf_day16.service.StudentService;

@Controller
public class TestController {
    
    @Autowired
    StudentService studentService;

    @GetMapping("/")
    public String showCountries(Model model){

        List<Country> countries = studentService.practice();
        model.addAttribute("countries", countries);
        
        return "countries";
    }
}
