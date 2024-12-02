package com.learn.abdevs29.blueproductservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class SampleController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello world!";
    }

    @GetMapping("/hello/{name}")
    public String sayHelloWithName(@PathVariable String name) {
        return "Hello " + name;
    }
}
