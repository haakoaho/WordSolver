package com.hakon.demo.service;


import com.hakon.demo.model.CustomStringComparator;
import com.hakon.demo.model.Position;
import com.hakon.demo.model.WordCandidate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

@Service
public class DemoService {


    public SortedSet<String> findWords(MultipartFile file, IDictionary dictionary) throws IOException {
        SortedSet<String> words = new TreeSet<>(new CustomStringComparator());
        char[][] charArray =  multipartFileTo2dCharArray(file);
        for (int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < charArray[i].length; j++) {
                backtracking(new Position(i,j) , charArray, words, dictionary);
            }
        }
        return words;
    }

    private void backtracking(Position root, char[][] charArray, SortedSet<String> words, IDictionary dictionary) {
        StringBuilder sb = new StringBuilder();
        Stack<Position> positions = new Stack<>();
        positions.add(root);
        Position currentPosition;
        while(!positions.isEmpty()){
            currentPosition = positions.peek();
            char c;
            try {
                 c = charArray[currentPosition.getX()][currentPosition.getY()];
            }
            catch (IndexOutOfBoundsException ex){
                positions.pop();
                continue;
            }
            if(!currentPosition.isBackTrack) {
                sb.append(c);
                currentPosition.isBackTrack = true;
            }
            String word = sb.toString();
            WordCandidate wordCandidate = dictionary.read(word);
            if(wordCandidate.isWord){
                words.add(word);
            }
            if(!wordCandidate.hasWordPotential){
                positions.pop();
                sb.deleteCharAt(sb.length()-1);
                continue;
            }
            if (!currentPosition.triedEast){
                currentPosition.triedEast = true;
                Position positionEast = new Position(currentPosition.getX()+ 1 , currentPosition.getY());
                if(positions.stream().noneMatch(positionEast::equals)) {
                    positions.push(positionEast);
                    continue;
                }
            }
            if(!currentPosition.tiredSouth){
                currentPosition.tiredSouth = true;
                Position positionSouth = new Position(currentPosition.getX(),currentPosition.getY() -1);
                if(positions.stream().noneMatch(positionSouth::equals)){
                    positions.push(positionSouth);
                    continue;
                }
            }
            if(!currentPosition.triedWest){
                currentPosition.triedWest = true;
                Position positionWest = new Position(currentPosition.getX() - 1, currentPosition.getY());
                if(positions.stream().noneMatch(positionWest::equals)){
                    positions.push(positionWest);
                    continue;
                }
            }
            if(!currentPosition.triedNorth) {
                currentPosition.triedNorth = true;
                Position positionNorth = new Position(currentPosition.getX(), currentPosition.getY() + 1);
                if (positions.stream().noneMatch(positionNorth::equals)) {
                    positions.push(positionNorth);
                    continue;
                }
            }
            positions.pop();
            sb.deleteCharAt(sb.length()-1);
        }

    }

    private char[][] multipartFileTo2dCharArray(MultipartFile file) throws IOException {
        String[] content = new String(file.getBytes()).split("\\n");
        char[][] charArray = new char[content.length][];
        for (int i = 0; i < content.length; i++) {
            charArray[i] = content[i].toCharArray();
        }
        return charArray;
    }
}
