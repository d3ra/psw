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
import java.util.LinkedList;

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
    private static final String AUDIO_PATH = "media/audio";
    // path per il file di collezioni
    private static final String COLLECTIONS_PATH = "collections";
    // path per i file di controllo
    private static final String CONTROL_FILE_PATH = "control";

    // separatore formato
    public static final String SEPARATOR = "\t";

    private static int filmCounter;
    private static int ebookCounter;
    private static int audioCounter;
    
    private LinkedList<Media> result = new LinkedList<Media>();

    public Repository() {
        try {
            File media = new File(MEDIA_PATH);
            File films = new File(FILMS_PATH);
            File ebooks = new File(EBOOKS_PATH);
            File audio = new File(AUDIO_PATH);
            File collectionsDir = new File(COLLECTIONS_PATH);
            File collectionsFile = new File(COLLECTIONS_PATH + "/collections");
            File controlDir = new File(CONTROL_FILE_PATH);
            File controlFile = new File(CONTROL_FILE_PATH + "/control_file");
            if (!controlDir.exists()) {
                controlDir.mkdir();
            }
            if (!controlFile.exists()) {
                controlFile.createNewFile();
                BufferedWriter bw = new BufferedWriter(new FileWriter(controlFile));
                // rappresentano il numero di film, ebook e audio presenti nel sistema
                bw.write("0" + SEPARATOR + "0" + SEPARATOR + "0");
                bw.newLine();
                bw.close();
                // siccome se entro qui vuol dire che non esiste file di controllo, se esistono film, canzoni o libri li cancello
                if (films.exists() || ebooks.exists() || audio.exists()) {
                    // cancella tutti i film
                    String[] entries = films.list();
                    for (String s : entries) {
                        File currentFile = new File(films.getPath(), s);
                        currentFile.delete();
                    }
                    // cancella tutti gli ebook
                    entries = ebooks.list();
                    for (String s : entries) {
                        File currentFile = new File(ebooks.getPath(), s);
                        currentFile.delete();
                    }
                    // cancella tutte le canzoni
                    entries = audio.list();
                    for (String s : entries) {
                        File currentFile = new File(audio.getPath(), s);
                        currentFile.delete();
                    }
                }
            }
            if (!media.exists()) {
                media.mkdir();
            }
            if (!films.exists()) {
                films.mkdir();
            }
            if (!ebooks.exists()) {
                ebooks.mkdir();
            }
            if (!audio.exists()) {
                audio.mkdir();
            }
            if (!collectionsDir.exists()) {
                collectionsDir.mkdir();
            }
            // inizializza il file collections se non esiste già
            if (!collectionsFile.exists()) {
                collectionsFile.createNewFile();
                BufferedWriter bw = new BufferedWriter(new FileWriter(collectionsFile));
                bw.write("0" + SEPARATOR + "default" + SEPARATOR + "ALL");
                bw.newLine();
                bw.close();
            }
            // setto i valori di controllo
            BufferedReader br = new BufferedReader(new FileReader(controlFile));
            String[] split = br.readLine().split(SEPARATOR);
            filmCounter = Integer.parseInt(split[0]);
            ebookCounter = Integer.parseInt(split[1]);
            audioCounter = Integer.parseInt(split[2]);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean addMedia(Media media) {
        if (media == null) {
            return false;
        }
        try {
            if (media instanceof Film) {
                media = (Film) media;
                File newFile = new File(FILMS_PATH + "/film" + filmCounter);
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
                bw.write(media.toString());
                bw.close();
                filmCounter++;
                return true;
            }
            if (media instanceof Ebook) {
                media = (Ebook) media;
                File newFile = new File(EBOOKS_PATH + "/ebook" + ebookCounter);
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
                bw.write(media.toString());
                bw.close();
                ebookCounter++;
                return true;
            }
            if (media instanceof Audio) {
                media = (Audio) media;
                File newFile = new File(AUDIO_PATH + "/audio" + audioCounter);
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
                bw.write(media.toString());
                bw.close();
                audioCounter++;
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public LinkedList<Media> getAll() {
        LinkedList<Media> result = null;
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
    
    private LinkedList<Media> listFilesForFolder(final File folder) throws FileNotFoundException, IOException {
        BufferedReader reader;
        for (final File fileEntry : folder.listFiles()) {
            LinkedList<Media> temp = new LinkedList<Media>();
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                reader = new BufferedReader(new FileReader(fileEntry));
                // attributi comuni a ogni media
                String[] split = reader.readLine().split(SEPARATOR);
                String url = split[0];
                String titolo = split[1];
                String autore = split[2];
                String genere = split[3];
                String anno = split[4];
                int idCollezione = Integer.parseInt(split[5]);
                // controlla il tipo
                //creare un oggetto del giusto tipo
                String type = "";
                if (fileEntry.getName().contains("ebook")) {
                    type = "ebooks";
                }
                if (fileEntry.getName().contains("film")) {
                    type = "films";
                }
                if (fileEntry.getName().contains("audio")) {
                    type = "audio";
                }
                switch (type) {
                    case "ebooks": {
                        String casaEditrice = split[6];
                        String formatoFile = split[7];
                        String lingua = split[8];
                        Ebook newEbook = new Ebook(url, autore, titolo, genere, anno, idCollezione, casaEditrice, formatoFile, lingua);
                        temp.add(newEbook);
                    }
                    break;
                    case "films": {
                        String regista = split[6];
                        String attori = split[7];
                        String descrizione = split[8];
                        String commenti = split[9];
                        String lingua = split[10];
                        Film newFilm = new Film(url, autore, titolo, genere, anno, idCollezione, regista, attori, descrizione, commenti, lingua);
                        temp.add(newFilm);

                    }
                    break;
                    case "audio": {
                        String produttore = split[6];
                        String album = split[7];
                        String numeroTraccia = split[8];
                        String durata = split[9];
                        String bitRate = split[10];
                        Audio newAudio = new Audio(url, autore, titolo, genere, anno, idCollezione, produttore, album, numeroTraccia, durata, bitRate);
                        temp.add(newAudio);
                    }
                    break;
                }
            }
            result.addAll(temp);
        }
        return result;
    }

    public void onClose() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(CONTROL_FILE_PATH + "/control_file")));
            bw.write(filmCounter + SEPARATOR + ebookCounter + SEPARATOR + audioCounter);
            bw.close();
        } catch (Exception e) {
        }
    }

}
