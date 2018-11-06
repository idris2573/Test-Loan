package com.landbay.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    private List<String> lines = new ArrayList<>();

    public boolean readFileUsingFilepath(String filepath){
        lines.clear();
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(filepath));

            String line;
            while ((line = br.readLine()) != null) {
                this.lines.add(line);
            }

            br.close();

        } catch (Exception e) {
            System.err.println("Error reading the file");
            return false;
        }
        return true;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public List<String> getLines() {
        return lines;
    }
}
