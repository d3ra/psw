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

    public Ebook(Media media, String casaEditrice, String formatoFile, String lingua) {
        super(media.getUrl(), media.getAutore(), media.getTitolo(), media.getGenere(), media.getAnno(), media.getIdCollezione());
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

    @Override
    public String toString() {
        String casaEditrice = this.casaEditrice;
        String lingua = this.lingua;
        if (casaEditrice.equals("")) {
            casaEditrice = "-";
        }
        if (lingua.equals("")) {
            lingua = "-";
        }
        return super.toString() + SEPARATOR + casaEditrice + SEPARATOR + this.formatoFile + SEPARATOR + lingua;
    }
    
    public String toString(String separator) {
        String casaEditrice = this.casaEditrice;
        String lingua = this.lingua;
        if (casaEditrice.equals("")) {
            casaEditrice = "-";
        }
        if (lingua.equals("")) {
            lingua = "-";
        }
        return super.toString(separator) + separator + casaEditrice + separator + this.formatoFile + separator + lingua;
    }

}
