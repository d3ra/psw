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

    // return next collection id
    public int getNextCollectionId() {
        int id = - 1;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(COLLECTIONS_PATH + "/collections")));
            String line = br.readLine();
            while (line != null) {
                String[] split = line.split(SEPARATOR);
                id = Integer.parseInt(split[0]);
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id + 1;
    }

    // aggiunge una riga a collections
    // QUESTO é SBAGLIATO PER ORAAAAAAAA e quidni anche il metodo subito sopra non funziona bene TODO
    public boolean createNewCollection(String collectionName, int collectionId, String type) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(COLLECTIONS_PATH + "/collections")));
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(COLLECTIONS_PATH + "/tmp")));
            String line = "";
            while (br.readLine() != null) {
                bw.write(line);
                bw.newLine();
            }
            bw.write(collectionId + SEPARATOR + collectionName + SEPARATOR + type);
            bw.newLine();
            br.close();
            bw.close();

            File oldFile = new File(COLLECTIONS_PATH + "/collections");
            oldFile.delete();
            File newFile = new File(COLLECTIONS_PATH + "/tmp");
            newFile.renameTo(oldFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    // non si può modificare titolo e autore
    public boolean modify(LinkedList<Media> medias) {
        if (medias.size() == 0) {
            return false;
        }
        File filmFolder = new File(FILMS_PATH);
        File ebookFolder = new File(EBOOKS_PATH);
        File audioFolder = new File(AUDIO_PATH);
        try {
            // per ogni media modificato modifica il filesystem che lo contiene
            for (Media media : medias) {
                BufferedReader reader;
                // per i film
                for (final File fileEntry : filmFolder.listFiles()) {
                    reader = new BufferedReader(new FileReader(fileEntry));
                    // attributi comuni a ogni media
                    String[] split = reader.readLine().split(SEPARATOR);
                    String titolo = split[1];
                    String autore = split[2];
                    if (titolo.equals(media.getTitolo())) {
                        // magari devo smettere di leggere se voglio scrivere
                        BufferedWriter bw = new BufferedWriter(new FileWriter(fileEntry));
                        bw.write(media.toString());
                        bw.close();
                    }
                }
                // per ebook
                for (final File fileEntry : ebookFolder.listFiles()) {
                    reader = new BufferedReader(new FileReader(fileEntry));
                    // attributi comuni a ogni media
                    String[] split = reader.readLine().split(SEPARATOR);
                    String titolo = split[1];
                    String autore = split[2];
                    if (titolo.equals(media.getTitolo())) {
                        // magari devo smettere di leggere se voglio scrivere
                        BufferedWriter bw = new BufferedWriter(new FileWriter(fileEntry));
                        bw.write(media.toString());
                        bw.close();
                    }
                }
                // per audio
                for (final File fileEntry : audioFolder.listFiles()) {
                    reader = new BufferedReader(new FileReader(fileEntry));
                    // attributi comuni a ogni media
                    String[] split = reader.readLine().split(SEPARATOR);
                    String titolo = split[1];
                    String autore = split[2];
                    if (titolo.equals(media.getTitolo())) {
                        // magari devo smettere di leggere se voglio scrivere
                        BufferedWriter bw = new BufferedWriter(new FileWriter(fileEntry));
                        bw.write(media.toString());
                        bw.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
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

    /*TODO ritorna null se non trova niente, controllare in Main*/
    public LinkedList<Media> search(String title, boolean isFilmChecked, boolean isEbookChecked, boolean isAudioChecked, String genere, String fromYear, String toYear) {
        LinkedList<Media> temp = new LinkedList<Media>();
        // se non c'è ricerca avanzata o l'utente ha selezionato tutti i checkbox -> getAll()
        if (isFilmChecked && isEbookChecked && isAudioChecked) {
            temp = getAll();
        } else {
            if (isFilmChecked) {
                temp.addAll(getMedias("films"));
            }
            if (isEbookChecked) {
                temp.addAll(getMedias("ebooks"));
            }
            if (isAudioChecked) {
                temp.addAll(getMedias("audio"));
            }
        }
        // non ho trovato nessun media -> non ci sono media salvati
        if (temp.size() == 0) {
            return null;
        }
        int from = Integer.parseInt(fromYear);
        int to = Integer.parseInt(toYear);
        // di tutti i media che ho trovato mi interessano solo quelli che soddisfano le condizioni
        LinkedList<Media> support = new LinkedList<Media>();
        // controllo solo il nome
        for (Media media : temp) {
            // preparo support
            if (title.equals("")) {
                // no titolo
                support.add(media);
            } else {
                if (media.getTitolo().equalsIgnoreCase(title)) {
                    support.add(media);
                }
            }
        }
        // controllo il size di support
        if (support.size() == 0) {
            return null;
        }
        clearResult();
        // size > 0 vuol dire che ho un po di media che rispettano il requisito del nome
        for (Media media : support) {
            int anno = -1;
            // controllo il genere e l'anno
            if (genere.equalsIgnoreCase("all")) {
                // va bene qualsiasi genere, controllo l'anno
                String mediaAnno = media.getAnno();
                // vuol dire che nella scheda del media è salvato un anno
                if (!mediaAnno.equals("-")) {
                    anno = Integer.parseInt(mediaAnno);
                } else {
                    // se nella scheda non è presente l'anno e anno richiesto 0 -> 2016 ritorno comunque
                    if (from == 0 && to == 2016) {
                        result.add(media);

                    }
                }
                // verifica condizione anno
                if (anno >= from && anno <= to) {
                    result.add(media);
                }
            } else {
                // controllo il genere, e poi l'anno
                String mediaGenere = media.getGenere();
                // se c'è un genere
                if (!mediaGenere.equals("-")) {
                    // se è uguale a quello richiesto
                    if (genere.equalsIgnoreCase(mediaGenere)) {
                        String mediaAnno = media.getAnno();
                        // vuol dire che nella scheda del media è salvato un anno
                        if (!mediaAnno.equals("-")) {
                            anno = Integer.parseInt(mediaAnno);
                        } else {
                            // se nella scheda non è presente l'anno e anno richiesto 0 -> 2016 ritorno comunque
                            if (to == 0 && from == 2016) {
                                result.add(media);
                            }
                        }
                        // verifica condizione anno
                        if (anno >= from && anno <= to) {
                            result.add(media);
                        }
                    }
                }
            }
        }
        return result;
    }

    public LinkedList<Media> getAll() {
        LinkedList<Media> result = null;
        // scorro tutti i file nelle directory e creo oggetti da mandare al main
        try {
            File folder = new File(MEDIA_PATH);
            clearResult();
            result = listFilesForFolder(folder);
            // se non ci sono media mi ritorna una lista vuota
        } catch (Exception e) {
            // errore nel leggere i file o nel creare oggetti
            e.printStackTrace();
        }
        return result;
    }

    // type deve essere: film, ebook o audio
    public LinkedList<Media> getMedias(String type) {
        LinkedList<Media> result = null;
        // scorro tutti i file nelle directory e creo oggetti da mandare al main
        System.out.println("type = " + type);
        System.out.println("parh = " + MEDIA_PATH + "/" + type);
        try {
            File folder = new File(MEDIA_PATH + "/" + type);
            clearResult();
            result = listFilesForFolder(folder);
            // se non ci sono media mi ritorna una lista vuota
        } catch (Exception e) {
            // errore nel leggere i file o nel creare oggetti
            e.printStackTrace();
        }
        return result;
    }

    // pulisce la variabile comune result prima che venga riutilizzata
    private void clearResult() {
        result = new LinkedList<Media>();
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
