package com.hakon.demo.service;

import com.hakon.demo.model.WordCandidate;

public interface IDictionary {
    String PATH = "src/main/resources/words.txt";
    WordCandidate read(String word);
}
