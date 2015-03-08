/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonconverter.createandwrite;

import java.util.ArrayList;
import jsonconverter.Logic.JSONSearchObjectCreator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Usuario
 */
public class JSONObjectCreator {

    JSONObject obj = new JSONObject();
    ArrayList<String[]> arraySearch;
    ArrayList<JSONSearchObjectCreator> arraySearchObjs;
    

    public JSONObjectCreator(ArrayList<String[]> arraySearch) {
        this.arraySearch = arraySearch;
        
        setSearchObjectList();
    }

    private void setSearchObjectList() {
        arraySearchObjs = new ArrayList();

        for (String[] tempArray : arraySearch) {
            JSONSearchObjectCreator newObject = new JSONSearchObjectCreator(tempArray[0], tempArray[1], tempArray[2], tempArray[3]);
            arraySearchObjs.add(newObject);
        }
    }

    public JSONObject objectArranger() {
        
        JSONArray array = new JSONArray();
        for (JSONSearchObjectCreator searchObject : arraySearchObjs) {
            JSONObject tempObj = new JSONObject();
            

            tempObj.put("id", searchObject.getId());
            tempObj.put("path", searchObject.getPath());
            tempObj.put("title", searchObject.getTitle());
            tempObj.put("frag", searchObject.getFragment());
            
            
            array.add(tempObj);
        }
        
        obj.put("Search", array);
        
        return obj;
    }
    
    public void printArray(ArrayList algo){
        
        for(int i=0; i<algo.size(); i++){
            System.out.println();
            System.out.print(" ");
        }
        
    }

}
