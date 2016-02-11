/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_package;

/**
 *
 * @author mich
 */
public class Ebook extends Media {
    
    private String casaEditrice;
    private String formatoFile;
    private String lingua;
    
    public Ebook(String url, String autore, String titolo, String genere, String anno, int idCollezione, String casaEditrice, String formatoFile, String lingua) {
        super(url, autore, titolo, genere, anno, idCollezione);
        this.casaEditrice = casaEditrice;
        this.formatoFile = formatoFile;
        this.lingua = lingua;
    }
    
    // GETTER

    public String getCasaEditrice() {
        return casaEditrice;
    }

    public String getFormatoFile() {
        return formatoFile;
    }

    public String getLingua() {
        return lingua;
    }
    
    // SETTER

    public void setCasaEditrice(String casaEditrice) {
        this.casaEditrice = casaEditrice;
    }

    public void setFormatoFile(String formatoFile) {
        this.formatoFile = formatoFile;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }
    
}
