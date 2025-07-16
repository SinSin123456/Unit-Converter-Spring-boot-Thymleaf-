package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ConverterService;

@RestController
@RequestMapping("/api")
public class ConverterController {

    @Autowired
    private ConverterService converterService;

    @PostMapping("/convert")
    public ResponseEntity<Double> convert(@RequestParam String category,
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double value) {
        double result = converterService.convert(category, from, to, value);
        return ResponseEntity.ok(result);
    }
}
