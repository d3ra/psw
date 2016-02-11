/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_package;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Interfaccia il main a qualunque cosa mi dia i dati, db o filesystem
 *
 * @author mich
 */
public class Repository {

    // forse così si può risolvere il problema sotto (linux)
    private static final String MEDIA_PATH = "media";
    // funziona solo su windows, testare su linux
    private static final String FILMS_PATH = "media/films";
    private static final String EBOOKS_PATH = "media/ebooks";
    private static final String MUSIC_PATH = "media/music";
    // path per il file di collezioni
    private static final String COLLECTIONS_PATH = "collections";

    // separatore formato
    private static final String SEPARATOR = "\t";
    
    public Repository() {
        try {
            File media = new File(MEDIA_PATH);
            File films = new File(FILMS_PATH);
            File ebooks = new File(EBOOKS_PATH);
            File music = new File(MUSIC_PATH);
            File collectionsDir = new File(COLLECTIONS_PATH);
            File collectionsFile = new File(COLLECTIONS_PATH + "/collections");
            if(!media.exists()) {
                media.mkdir();
            }
            if(!films.exists()) {
                films.mkdir();
            }
            if(!ebooks.exists()) {
                ebooks.mkdir();
            }
            if(!music.exists()) {
                music.mkdir();
            }
            if(!collectionsDir.exists()) {
                collectionsDir.mkdir();
            }
            // inizializza il file collections se non esiste già
            if(!collectionsFile.exists()) {
                collectionsFile.createNewFile();
                BufferedWriter bw = new BufferedWriter(new FileWriter(collectionsFile));
                bw.write("0" + SEPARATOR + "default" + SEPARATOR + "ALL");
                bw.newLine();
                bw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Media> getAll() {
        ArrayList<Media> result = null;
        // scorro tutti i file nelle directory e creo oggetti da mandare al main
        try {
            File folder = new File(MEDIA_PATH);
            result = listFilesForFolder(folder);
            // se non ci sono media mi ritorna una lista vuota
        } catch (Exception e) {
            // errore nel leggere i file o nel creare oggetti
            e.printStackTrace();
        }

        return result;
    }

    private ArrayList<Media> listFilesForFolder(final File folder) throws FileNotFoundException, IOException {
        ArrayList<Media> result = new ArrayList<Media>();
        BufferedReader reader;
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                reader = new BufferedReader(new FileReader(fileEntry));
                // attributi comuni a ogni media
                String[] split = reader.readLine().split(SEPARATOR);
                String url = split[0];
                String autore = split[1];
                String titolo = split[2];
                String genere = split[3];
                String anno = split[4];
                int idCollezione = Integer.parseInt(split[5]);
                // controlla il tipo
                System.out.println("name = " + fileEntry.getParent());
                //creare un oggetto del giusto tipo
                switch (fileEntry.getName()) {
                    case "ebooks": {
                        String casaEditrice = split[6];
                        String formatoFile = split[7];
                        String lingua = split[8];
                        Ebook newEbook = new Ebook(url, autore, titolo, genere, anno, idCollezione, casaEditrice, formatoFile, lingua);
                        result.add(newEbook);
                    }
                    break;
                    case "films": {
                        String regista = split[6];
                        String attori = split[7];
                        String descrizione = split[8];
                        String commenti = split[9];
                        String lingua = split[10];
                        Film newFilm = new Film(url, autore, titolo, genere, anno, idCollezione, regista, attori, descrizione, commenti, lingua);
                        result.add(newFilm);

                    }
                    break;
                    case "music": {
                        String produttore = split[6];
                        String album = split[7];
                        String numeroTraccia = split[8];
                        String durata = split[9];
                        String bitRate = split[10];
                        Music newMusic = new  Music(url, autore, titolo, genere, anno, idCollezione, produttore, album, numeroTraccia, durata, bitRate);
                        result.add(newMusic);
                    }
                }
            }
        }
        return result;
    }

}
