package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RepoFile implements Repository {
    private final String PATH = "src/main/java/org/example/stat.txt";

    @Override
    public Map<Integer, String> load() {
        Map<Integer, String> map = new HashMap<>();
        try {
            File file = new File(PATH);
            FileInputStream fis = new FileInputStream(file);
//            System.out.println(file.length());
            byte[] byteArray = new byte[(int) file.length()];
            fis.read(byteArray);
            String data = new String(byteArray);
            String[] stringArray = data.split("\n");
            for (int i = 0; i < stringArray.length; i++) {
                String[] some_result = stringArray[i].split(":");
                map.put(i + 1, some_result[1]);
            }
            fis.close();
            return map;
        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public int loadCount() {
        try {
            File file = new File(PATH);
            FileInputStream fis = new FileInputStream(file);
//            System.out.println(file.length());
            byte [] byteArray = new byte[(int) file.length()];
            fis.read(byteArray);
            String data = new String(byteArray);
            String[] stringArray = data.split("\n");
            if (stringArray[0].isEmpty()) {
                return 1;
            }
            fis.close();
            return stringArray.length+1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void save(Integer i, String str) {
        try (FileWriter fileWriter = new FileWriter(PATH, true)) {
            String total = i + ":" + str + "\n";
            fileWriter.write(total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
