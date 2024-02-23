package com.github.face.demo.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

/**
 * @author wo
 */
@RestController
@Tag(name = "测试 Knife4j", description = "/demo/????")
@RequestMapping("/demo")
public class FirstController {

    @Operation(summary = "分页", parameters = {@Parameter( name = "user", description = "查询参数")})
    @GetMapping()
    public String get(User user) {
        return "hello World";
    }

    @Operation(summary = "根据id查询信息", parameters = {@Parameter(name = "id", description = "人员id"),
            @Parameter(name = "name", description = "nam姓名")})
    @GetMapping("/getById")
    public String getById(String id, String name) {
        return "hello World";
    }

    @PostMapping()
    @Operation(summary = "新增", parameters = {@Parameter(name = "user", description = "用户实体类")})
    public String doPost(@RequestBody User user) {
        return "hello World";
    }

    @DeleteMapping()
    @Operation(summary = "删除", parameters = {@Parameter(name = "id", description = "idxxx")})
    public String doPost(@RequestParam String id) {
        return "hello World";
    }

    @Data
    @Tag(name = "用户实体类")
    public static class User {
        @Schema(name = "id")
        private String id;
        @Schema(name = "姓名")
        private String name;
        @Schema(name = "年龄")
        private int age;
        @Schema(name = "手机" )
        private String phone;
    }
}