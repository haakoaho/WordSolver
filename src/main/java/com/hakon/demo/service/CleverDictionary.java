package com.hakon.demo.service;


import com.hakon.demo.model.WordCandidate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


@Service
public class CleverDictionary implements IDictionary {

    private final HashMap<String, WordCandidate> cleverDictionary = new HashMap<>();

    public void createCleverDictionary() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(IDictionary.PATH));
        String  word;
        while((word = reader.readLine()) != null){
            if(cleverDictionary.containsKey(word)){
                cleverDictionary.get(word).isWord = true;
            }
            else{
                WordCandidate wordCandidate = new WordCandidate();
                wordCandidate.isWord = true;
                cleverDictionary.put(word,wordCandidate);
            }
            word = word.substring(0, word.length() - 1);
            while(!word.isEmpty()){
                if(!cleverDictionary.containsKey(word)){
                    WordCandidate wordCandidate = new WordCandidate();
                    wordCandidate.hasWordPotential = true;
                    cleverDictionary.put(word,wordCandidate);
                }
                else{
                    cleverDictionary.get(word).hasWordPotential = true;
                }
                word = word.substring(0, word.length() - 1);
            }
        }
    }
    public WordCandidate read(String word){
        if(!cleverDictionary.containsKey(word)){
            return new WordCandidate();
        }
        return cleverDictionary.get(word);
    }
}
