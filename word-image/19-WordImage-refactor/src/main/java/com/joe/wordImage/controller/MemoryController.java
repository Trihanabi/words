package com.joe.wordImage.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/memory")
@Api(value = "记忆控制器")
public class MemoryController {

    @GetMapping("/{userId}&{bookId}")
    @ApiOperation(value = "生成记忆所用图片名集合并跳转到记忆界面")
    public String startMemory(Model theModel,
                              @PathVariable int userId,
                              @PathVariable int bookId ) {

        return "memory/word_memory";
    }

}
