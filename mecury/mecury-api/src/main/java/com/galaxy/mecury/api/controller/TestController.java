package com.galaxy.mecury.api.controller;

import com.galaxy.mecury.api.common.exception.ParamsNotValidException;
import com.galaxy.mecury.api.service.DeadLetterProducerService;
import com.galaxy.mecury.entity.NBAPlayer;
import com.galaxy.mecury.test.starter.service.ExampleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "TEST")
@RestController
@RequestMapping("/test")
public class TestController {
    @ApiOperation("zero division")
    @RequestMapping("/zerodivision")
    public String list() {
        int i = 1 / 0;

        return "zerodivision";
    }

    @RequestMapping("/nbaplayer/add")
    public NBAPlayer add(@RequestBody @Validated NBAPlayer nbaPlayer, BindingResult result) {
        if (result.hasErrors()) {
            throw new ParamsNotValidException(result);
        }

        return nbaPlayer;
    }

    @Autowired
    private ExampleService exampleService;

    @Autowired
    private DeadLetterProducerService deadLetterProducerService;

    @RequestMapping("/starter")
    public String starter() {
        return exampleService.wrap("TEST");
    }

    @RequestMapping("/dead-letter")
    public String deadLetter() {
        deadLetterProducerService.send(10);
        return "deadLetter";
    }
}