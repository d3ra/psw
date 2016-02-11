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
public class Music extends Media {
    
    private String produttore;
    private String album;
    private String numeroTraccia;
    private String durata;
    private String bitRate;
    
    public Music(String url, String autore, String titolo, String genere, String anno, int idCollezione, String produttore, String album, String numeroTraccia, String durata, String bitrate) {
        super(url, autore, titolo, genere, anno, idCollezione);
        this.produttore = produttore;
        this.album = album;
        this.numeroTraccia = numeroTraccia;
        this.durata = durata;
        this.bitRate = bitrate;
    }
    
    public Music(Media media, String produttore, String album, String numeroTraccia, String durata, String bitRate) {
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
    
}
