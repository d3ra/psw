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
public class Audio extends Media {

    private String produttore;
    private String album;
    private String numeroTraccia;
    private String durata;
    private String bitRate;

    public Audio(String url, String autore, String titolo, String genere, String anno, int idCollezione, String produttore, String album, String numeroTraccia, String durata, String bitrate) {
        super(url, autore, titolo, genere, anno, idCollezione);
        this.produttore = produttore;
        this.album = album;
        this.numeroTraccia = numeroTraccia;
        this.durata = durata;
        this.bitRate = bitrate;
    }

    public Audio(Media media, String produttore, String album, String numeroTraccia, String durata, String bitRate) {
        super(media.getUrl(), media.getAutore(), media.getTitolo(), media.getGenere(), media.getAnno(), media.getIdCollezione());
        this.produttore = produttore;
        this.album = album;
        this.numeroTraccia = numeroTraccia;
        this.durata = durata;
        this.bitRate = bitRate;
    }

    // GETTER
    public String getProduttore() {
        return produttore;
    }

    public String getAlbum() {
        return album;
    }

    public String getNumeroTraccia() {
        return numeroTraccia;
    }

    public String getDurata() {
        return durata;
    }

    public String getBitRate() {
        return bitRate;
    }

    // SETTER
    public void setProduttore(String produttore) {
        this.produttore = produttore;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setNumeroTraccia(String numeroTraccia) {
        this.numeroTraccia = numeroTraccia;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public void setBitRate(String bitRate) {
        this.bitRate = bitRate;
    }

    @Override
    public String toString() {
        String produttore = this.produttore;
        String album = this.album;
        String numeroTraccia = this.numeroTraccia;
        String durata = this.durata;
        String bitRate = this.bitRate;
        if (produttore.equals("")) {
            produttore = "-";
        }
        if (album.equals("")) {
            album = "-";
        }
        if (numeroTraccia.equals("")) {
            numeroTraccia = "-";
        }
        if (durata.equals("")) {
            durata = "-";
        }
        if (bitRate.equals("")) {
            bitRate = "-";
        }
        return super.toString() + SEPARATOR + produttore + SEPARATOR + album + SEPARATOR + numeroTraccia + SEPARATOR + durata + SEPARATOR + bitRate;
    }
    
    public String toString(String separator) {
        String produttore = this.produttore;
        String album = this.album;
        String numeroTraccia = this.numeroTraccia;
        String durata = this.durata;
        String bitRate = this.bitRate;
        if (produttore.equals("")) {
            produttore = "-";
        }
        if (album.equals("")) {
            album = "-";
        }
        if (numeroTraccia.equals("")) {
            numeroTraccia = "-";
        }
        if (durata.equals("")) {
            durata = "-";
        }
        if (bitRate.equals("")) {
            bitRate = "-";
        }
        return super.toString(separator) + separator + produttore + separator + album + separator + numeroTraccia + separator + durata + separator + bitRate;
    }

}
