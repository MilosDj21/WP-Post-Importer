package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KeywordPicker {
    public List<String> getKeywords(){
        List<String> keywordsList = new ArrayList<>();
        String s = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("lista_varijabli.txt"));
            while((s = reader.readLine()) != null){
                keywordsList.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keywordsList;
    }
}
