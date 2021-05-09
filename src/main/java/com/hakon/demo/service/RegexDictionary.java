package com.hakon.demo.service;

import com.hakon.demo.model.WordCandidate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegexDictionary implements IDictionary {

    String dictionary;
    public void load() throws IOException {
        dictionary = new String(Files.readAllBytes(Paths.get(IDictionary.PATH)), StandardCharsets.UTF_8);
    }

    @Override
    public WordCandidate read(String word) {
        WordCandidate wordCandidate = new WordCandidate();
        word = word.replaceAll("@",""); //empty space support
        //wildcard supported with '.' char. Illegal for wildcard at the end of request

        Pattern pattern = Pattern.compile("^"+word+"\\S",Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(dictionary);

        Pattern pattern2 = Pattern.compile("^"+word+"\\s",Pattern.MULTILINE);
        Matcher matcher2 = pattern2.matcher(dictionary);

        wordCandidate.hasWordPotential =  matcher.find();
        wordCandidate.isWord = matcher2.find();

        return wordCandidate;
    }
}
