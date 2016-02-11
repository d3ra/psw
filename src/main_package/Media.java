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
public abstract class Media {

    private String url;
    private String autore;
    private String titolo;
    private String genere;
    private String anno;
    private int idCollezione;

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

}
