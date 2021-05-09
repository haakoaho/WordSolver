package com.hakon.demo;

import com.hakon.demo.model.WordCandidate;
import com.hakon.demo.service.CleverDictionary;
import com.hakon.demo.service.RegexDictionary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;


@SpringBootTest

public class DictionaryTest {
    @Autowired
    CleverDictionary cleverDictionary;
    @Autowired
    RegexDictionary regexDictionary;

    @BeforeEach
    public void setup() throws IOException {
        cleverDictionary.createCleverDictionary();
        regexDictionary.load();
    }

    @Test
    public void wordFromCleverDictionaryWithPotential(){
        WordCandidate wordCandidate = cleverDictionary.read("incomprehensible");
        WordCandidate wordCandidate1 = regexDictionary.read("incomprehensible");
        Assertions.assertTrue(wordCandidate.isWord);
        Assertions.assertTrue(wordCandidate.hasWordPotential);
        Assertions.assertTrue(wordCandidate1.isWord);
        Assertions.assertTrue(wordCandidate1.hasWordPotential);
    }
    @Test
    public void wordFromCleverDictionaryWithoutPotential(){
        WordCandidate wordCandidate = cleverDictionary.read("incomprehensibleness");
        WordCandidate wordCandidate1 = regexDictionary.read("incomprehensibleness");
        Assertions.assertTrue(wordCandidate.isWord);
        Assertions.assertFalse(wordCandidate.hasWordPotential);
        Assertions.assertTrue(wordCandidate1.isWord);
        Assertions.assertFalse(wordCandidate1.hasWordPotential);
    }
    @Test
    public void potentialFromCleverDictionary(){
        WordCandidate wordCandidate = cleverDictionary.read("hel");
        WordCandidate wordCandidate1 = regexDictionary.read("hel");
        Assertions.assertFalse(wordCandidate.isWord);
        Assertions.assertTrue(wordCandidate.hasWordPotential);
        Assertions.assertFalse(wordCandidate1.isWord);
        Assertions.assertTrue(wordCandidate1.hasWordPotential);
    }

    @Test
    public void notAWord(){
        WordCandidate wordCandidate = cleverDictionary.read("adsfas");
        WordCandidate wordCandidate1 = regexDictionary.read("adsfas");
        Assertions.assertFalse(wordCandidate.isWord);
        Assertions.assertFalse(wordCandidate.hasWordPotential);
        Assertions.assertFalse(wordCandidate1.isWord);
        Assertions.assertFalse(wordCandidate1.hasWordPotential);
    }
}
