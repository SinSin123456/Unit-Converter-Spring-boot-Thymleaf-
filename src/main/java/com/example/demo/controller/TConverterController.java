package com.example.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.annotation.WebController;
import com.example.demo.helper.View;

@WebController
@RequestMapping("/converter")
public class TConverterController {
    @GetMapping()
    public String index(Model model) {
        return View.CONVERTER;
    }
}
