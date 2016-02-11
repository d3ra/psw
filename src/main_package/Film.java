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
public class Film extends Media {
    
    private String regista;
    private String attori;
    private String descrizione;
    private String commenti;
    private String lingua;
    
    public Film(String url, String autore, String titolo, String genere, String anno, int idCollezione, String regista, String attori, String descrizione, String commenti, String lingua) {
        super(url, autore, titolo, genere, anno, idCollezione);
        this.regista = regista;
        this.attori = attori;
        this.descrizione = descrizione;
        this.commenti = commenti;
        this.lingua = lingua;
    }
    
    // GETTER

    public String getRegista() {
        return regista;
    }

    public String getAttori() {
        return attori;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getCommenti() {
        return commenti;
    }

    public String getLingua() {
        return lingua;
    }
    
    // SETTER

    public void setRegista(String regista) {
        this.regista = regista;
    }

    public void setAttori(String attori) {
        this.attori = attori;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setCommenti(String commenti) {
        this.commenti = commenti;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }
    
}
