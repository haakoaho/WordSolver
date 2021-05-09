package com.hakon.demo.controller;

import com.hakon.demo.service.CleverDictionary;
import com.hakon.demo.service.DemoService;
import com.hakon.demo.service.RegexDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.SortedSet;

@RestController
public class DemoController {
    @Autowired
    DemoService demoService;
    @Autowired
    CleverDictionary cleverDictionary;
    @Autowired
    RegexDictionary regexDictionary;

    @PostMapping(path = "clever")
    public SortedSet<String> getWordsWithCleverDictionary(@RequestParam MultipartFile file) throws IOException {
        return demoService.findWords(file,cleverDictionary);
    }
    @PostMapping(path = "regex")
    public SortedSet<String> getWords(@RequestParam MultipartFile file) throws IOException {
        return demoService.findWords(file,regexDictionary);
    }
}
