/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversortxt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 *
 * @author Juan Olaya O
 */
public class ConversorTXT {

    public static void main(String args[]) {
        System.out.println("Convirtiendo TXT");
        if(args.length == 2){
        String docsPath = args[0];
        String docTextPath = args[1];
        lectorDocs(docsPath, docTextPath);
        } else{
            System.out.println("Requiere dos valores para poder ejecutarse");
        }
        
        //String docsPath = "/home/olaya/Dropbox/TG/Buscador/docs";
        //String docsTxtPath = ""
        //String docsPath = "/home/juan/Dropbox/TG/Buscador/DocsPDF";
        //String docTextPath = "/home/juan/Dropbox/TG/Buscador/DocsConvertidos";
        //String docsPath = "C:\\Users\\Juan Olaya O\\Dropbox\\TG\\Buscador\\DocsPDF";
        //String docTextPath = "C:\\Users\\Juan Olaya O\\Dropbox\\TG\\Buscador\\DocsConvertidos";
        

        
    }

    public static void lectorDocs(String docsPath, String docTextPath) {
        //Directorio donde se encuentran los documentos a ser indexados.
        File docDir = new File(docsPath);

        try {

            //Verifica si el directorio de docuimentos a convertir se puede leer y
            //si es directorio.
            if (docDir.canRead() && docDir.isDirectory()) {

                //Si listan todos los documentos del directorio.
                File[] docFiles = docDir.listFiles();

                //Revisa que la lista de documentos no sea nula.
                if (docFiles != null) {

                    //Genera un id para el documento y recorre cada archivo.
                    int docID = 0;
                    for (int i = 0; i < docFiles.length; i++) {

                        //Si uno de los elementos del directorio es un Directorio
                        //se procede a realizar el procedimeinto addDocuments
                        //a este.
                        if (docFiles[i].isDirectory()) {
                            String docDirNew = docFiles[i].getCanonicalPath();
                            lectorDocs(docDirNew, docTextPath);
                        } else {
                            //Sino es directorio se procede a leer la informacion
                            //contenida dentro del archivo. Se utiliza el lector
                            //de archivos apache tika.
                            String texto = TikaDocReader(docFiles[i]);
                            if (texto != null) {
                                passToTXT(docFiles[i], texto, docTextPath);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error de tipo " + e.getClass() + "\n dice: " + e.getMessage());
        }

    }

    //TikaDocReader se utiliza para poder indexar diferentes tipos de documentos
    //(pdf, doc, ppt, etc) y pasarlos a texto plano
    private static String TikaDocReader(File docFile) {

        try {

            Metadata metadata = new Metadata();
            metadata.set(Metadata.RESOURCE_NAME_KEY, docFile.getName());

            InputStream is = new FileInputStream(docFile);      // 5
            Parser parser = new AutoDetectParser();       // 6
            ContentHandler handler = new BodyContentHandler(1000000); // Se puede agregar un entero grande para aumentar la capacidad   Ejemplo new BodyContentHandler(1000000)

            ParseContext context = new ParseContext();   // 8
            context.set(Parser.class, parser);           // 8

            try {
                parser.parse(is, handler, metadata, // 9
                        new ParseContext());        // 9
            } catch (SAXException ex) {
                
            } catch (TikaException ex) {
                
            } finally {
                is.close();
            }
            
            String texto = handler.toString();

            return texto;
        } catch (IOException e) {
            System.out.println("Error de tipo " + e.getClass() + "\n dice: " + e.getMessage());
        }
        return null;
    }

    public static void passToTXT(File doc, String contenido, String destino) throws IOException {

        String nombre = doc.getName();
        String tipoDoc4 = nombre.substring(nombre.length() - 4);
        String tipoDoc5 = nombre.substring(nombre.length() - 5);
        
        System.out.println("Nombre: " + nombre + " Tipo4: " + tipoDoc4);
        System.out.println("Nombre: " + nombre + " Tipo5: " + tipoDoc5);

        if (tipoDoc4.contains(".")) {
            String name = nombre.split(tipoDoc4)[0];
            

            File file = new File(destino, name + ".txt");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.flush();
            bw.close();
        } else if (tipoDoc5.contains(".")) {
            String name = nombre.split(tipoDoc5)[0];
            System.out.println("Nuevo nombre: " + name);

            File file = new File(destino, name + ".txt");
            file.createNewFile();
            
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.flush();
            bw.close();
        }else{
            System.out.println("Revise el documento: " + nombre);
        }

    }

}
