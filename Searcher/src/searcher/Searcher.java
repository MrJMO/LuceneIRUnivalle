/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.*;
import org.apache.lucene.util.Version;

/**
 *
 * @author olaya
 */
public class Searcher {

    //String indexPath = "/home/olaya/NetBeansProjects/Indices";
    public ArrayList<String[]> Buscador(String busqueda, String indexPath) {
        //ArrayList de respuesta
        ArrayList<String[]> respuestas = new ArrayList();
        try {
            Directory dir = FSDirectory.open(new File(indexPath));

            IndexReader reader = DirectoryReader.open(dir);
            IndexSearcher searcher = new IndexSearcher(reader);
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_9);

            String toSearch = busqueda;

            QueryParser parser = new QueryParser(Version.LUCENE_4_9, "contents", analyzer);
            Query consulta = parser.parse(toSearch);

            TopDocs results = searcher.search(consulta, 10);
            ScoreDoc[] hits = results.scoreDocs;

            int contador = 0;
            if (hits.length == 0) {
                System.out.println("No se han encontrado coincidencias.");
            } else {
                for (int i = 0; i < hits.length; i++) {

                    //Array de elementos de 1 respuesta
                    String[] elementosRespuesta = new String[4];

                    //Recoger Documento del buscador
                    Document doc = searcher.doc(hits[i].doc);

                    //recuperar ID
                    int id = Integer.parseInt(doc.get("id"));
                    System.out.println("id: " + id);

                    //recuperar PATH
                    String path = doc.get("path");

                    if (path != null) {
                        System.out.println((i + 1) + ". " + path);

                        //Recuperar TITULO
                        String title = doc.get("title");
                        if (title != null) {
                            System.out.println("   Title: " + doc.get("title"));

                            //recuperar CONTENIDO DE DOC
                            String texto = doc.get("contents");

                            //recuperar FRAGMENTOS
                            TokenStream tokenStream = TokenSources.getAnyTokenStream(searcher.getIndexReader(), id, "contents", new StandardAnalyzer(Version.LUCENE_4_9));
                            QueryScorer scorer = new QueryScorer(consulta);
                            Highlighter highlighter = new Highlighter(scorer);
                            try {
                                String fragment = highlighter.getBestFragments(tokenStream, texto, 3, "...........");

                                System.out.println(fragment);

                                //Añadir ELEMENTOS
                                elementosRespuesta[0] = id + "";
                                elementosRespuesta[1] = path;
                                elementosRespuesta[2] = title;
                                elementosRespuesta[3] = fragment;
                            } catch (InvalidTokenOffsetsException invalidTokenOffsetsException) {
                                System.out.println("Error de tipo invalidTokenOffsetException dice: " + invalidTokenOffsetsException.getMessage());
                            }
                        } else {
                            System.out.println("Titulo No disponible");
                        }
                    } else {
                        System.out.println((i + 1) + ". " + "No path for this document");
                    }
                    respuestas.add(elementosRespuesta);
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error de tipo " + e.getClass() + "\n dice: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Error de tipo " + e.getClass() + "\n dice: " + e.getMessage());
        }
        return respuestas;
    }

}
