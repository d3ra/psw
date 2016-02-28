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
public class Media {

    //magari sta roba è meglio metterla in una classe comune siccome la usa anche repository
    public static final String SEPARATOR = "\t";

    private String url;
    private String autore;
    private String titolo;
    private String genere;
    private String anno;
    private int idCollezione;
    
    public Media() {}

    public Media(String url, String autore, String titolo, String genere, String anno, int idCollezione) {
        this.url = url;
        this.autore = autore;
        this.titolo = titolo;
        this.genere = genere;
        this.anno = anno;
        this.idCollezione = idCollezione;
    }

    // GETTER
    public String getUrl() {
        return url;
    }

    public String getAutore() {
        return autore;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getGenere() {
        return genere;
    }

    public String getAnno() {
        return anno;
    }

    public int getIdCollezione() {
        return this.idCollezione;
    }

    //SETTER
    public void setUrl(String url) {
        this.url = url;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public void setIdCollezione(int idCollezione) {
        this.idCollezione = idCollezione;
    }

    @Override
    public String toString() {
        String genere = this.genere;
        String anno = this.anno;
        if (genere.equals("")) {
            genere = "-";
        }
        if (anno.equals("")) {
            anno = "-";
        }
        return this.url + SEPARATOR + this.titolo + SEPARATOR + this.autore + SEPARATOR + genere + SEPARATOR + anno + SEPARATOR + this.idCollezione;
    }
    
    public String toString(String separator) {
        String genere = this.genere;
        String anno = this.anno;
        if (genere.equals("")) {
            genere = "-";
        }
        if (anno.equals("")) {
            anno = "-";
        }
        return this.url + separator + this.titolo + separator + this.autore + separator + genere + separator + anno + separator + this.idCollezione;
    }
}
