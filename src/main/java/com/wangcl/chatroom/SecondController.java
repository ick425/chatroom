package com.wangcl.chatroom;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wo
 */
@RestController
@Tag(name = "聊天室")
@RequestMapping("/chat")
public class SecondController {

    @Operation(summary = "返回字符")
    @GetMapping()
    public String get() {
        return "开始聊天";
    }
}
