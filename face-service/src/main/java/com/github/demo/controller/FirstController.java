package com.github.demo.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wo
 */
@RestController
@Tag(name = "测试 Knife4j")
@RequestMapping("/demo")
public class FirstController {

    @Operation(summary = "测试方法")
    @GetMapping("/get")
    public String get() {
        return "hello World";
    }
}