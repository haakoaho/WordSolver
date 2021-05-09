package com.hakon.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hakon.demo.controller.DemoController;
import com.hakon.demo.service.CleverDictionary;
import com.hakon.demo.service.DemoService;
import com.hakon.demo.service.RegexDictionary;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DemoController.class)
public class DemoControllerIntegrationTest {

    @SpyBean
    CleverDictionary cleverDictionary;
    @SpyBean
    RegexDictionary regexDictionary;
    @SpyBean
    DemoController demoController;
    @SpyBean
    DemoService demoService;
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    MultipartFile multipartFile;

    @Test
    public void test10SquareGridClever() throws Exception {
        //Junit struggles to solve larger grid
        File task = new File("src/main/resources/grid10x10.txt");
        File solution = new File("src/main/resources/solution10x10.txt");
        MvcResult result = mvc.perform(multipart("/clever")
                .file("file",FileUtils.readFileToByteArray(task))
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        List<String> response = objectMapper.readValue(contentAsString, List.class);
        Iterator<String> resultIterator = response.iterator();
        Scanner sc = new Scanner(solution);
        while (sc.hasNext()){
            Assertions.assertEquals(sc.next(),resultIterator.next());
        }
    }

    @Test
    public void test10SquareGridRegex() throws Exception {
        //Junit struggles to solve larger grid
        File task = new File("src/main/resources/grid10x10.txt");
        File solution = new File("src/main/resources/solution10x10.txt");
        MvcResult result = mvc.perform(multipart("/regex")
                .file("file",FileUtils.readFileToByteArray(task))
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        List<String> response = objectMapper.readValue(contentAsString, List.class);
        Iterator<String> resultIterator = response.iterator();
        Scanner sc = new Scanner(solution);
        while (sc.hasNext()){
            Assertions.assertEquals(sc.next(),resultIterator.next());
        }
    }
}
