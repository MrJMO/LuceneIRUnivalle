/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonconverter.createandwrite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

/**
 *
 * @author Usuario
 */
public class JSONFileWriter {

    JSONObject obj = new JSONObject();

    public JSONFileWriter(JSONObject obj) {
        this.obj = obj;
    }

    public void createFile(String name, String JSONFolderPath) {
        try {
            File file = new File(JSONFolderPath, name + ".txt");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(obj.toString());
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
