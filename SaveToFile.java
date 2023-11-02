package Homeworks.HW3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveToFile{


    public SaveToFile() {
    }

    public void save(String path, String text) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true));
        bufferedWriter.write(text + "\n");
        bufferedWriter.close();
    }

}
