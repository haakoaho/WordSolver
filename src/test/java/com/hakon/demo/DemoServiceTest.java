package com.hakon.demo;

import com.hakon.demo.model.WordCandidate;
import com.hakon.demo.service.DemoService;
import com.hakon.demo.service.IDictionary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

@SpringBootTest
public class DemoServiceTest {
    @Mock
    IDictionary dictionary;
    @Autowired
    DemoService demoService;
    private final WordCandidate A = new WordCandidate();
    private final WordCandidate B = new WordCandidate();
    private final WordCandidate C = new WordCandidate();
    private final WordCandidate D = new WordCandidate();
    MultipartFile multipartFile;

    @BeforeEach
    public void setup(){
        A.isWord = true;
        A.hasWordPotential = true;
        B.isWord = true;
        C.hasWordPotential = true;
        Mockito.when(dictionary.read("a")).thenReturn(C);
        Mockito.when(dictionary.read("ab")).thenReturn(B);
        Mockito.when(dictionary.read("b")).thenReturn(D);
        multipartFile = new MockMultipartFile("ab","ab".getBytes(StandardCharsets.UTF_8));
    }
    //Unit test requires a lot of mocking. Not really valuable
    @Test
    public void demoTest() throws IOException {
        Set<String> set = demoService.findWords(multipartFile,dictionary);
        Assertions.assertTrue(set.contains("ab"));
    }
}
