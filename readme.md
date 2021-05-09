# Word Solver API 

API to solve word solver puzzles. Find words in a 2D matrix of characters by solving vertically and horizontally. 

**Endpoints** : 
```
POST {domain}/clever
POST {domain}/regex
```
**Request Param** : upload file
```
file : inputFile.txt
```

The clever endpoint uses a hashMap of all words in the English language, and is the fastest method.  Regex method uses a list of all words to find a matching word. 

Supports wildcards : . 

Supports spaces : @

