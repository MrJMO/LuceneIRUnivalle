/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonconverter;

import java.util.ArrayList;
import jsonconverter.createandwrite.JSONFileWriter;
import jsonconverter.createandwrite.JSONObjectCreator;
import searcher.Searcher;
/**
 *
 * @author Usuario
 */
public class JSONConverter {
    /**
     * @param args the command line arguments
     * String consulta
     * String indexPath
     * String JSONPath
     */
    public static void main(String[] args) {
        
        Searcher buscador = new Searcher();
 
        try{
        ArrayList<String[]> resultadosBusqueda = buscador.Buscador(args[0], args[1]);
        String JSONFolderPath = args[2];
        
        JSONObjectCreator creator = new JSONObjectCreator(resultadosBusqueda);
        JSONFileWriter writer = new JSONFileWriter(creator.objectArranger());
        
        writer.createFile("prueba", JSONFolderPath);
        } catch(Exception e){
        }
        
    }
    
}
