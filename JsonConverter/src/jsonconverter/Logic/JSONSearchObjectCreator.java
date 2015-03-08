/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonconverter.Logic;

/**
 *
 * @author Usuario
 */
public class JSONSearchObjectCreator {
    
    private String id, path, title, fragment;

    public JSONSearchObjectCreator(String id, String path, String title, String fragment) {
        this.id = id;
        this.path = path;
        this.title = title;
        this.fragment = fragment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }
    
    
    
}
