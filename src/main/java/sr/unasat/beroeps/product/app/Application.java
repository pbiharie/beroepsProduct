package sr.unasat.beroeps.product.app;

import sr.unasat.beroeps.product.entities.Film;
import sr.unasat.beroeps.product.entities.FilmVoorstelling;
import sr.unasat.beroeps.product.entities.Kaart;
import sr.unasat.beroeps.product.entities.Zaal;
import sr.unasat.beroeps.product.repositories.FilmRepository;
import sr.unasat.beroeps.product.repositories.FilmVoorstellingRepository;
import sr.unasat.beroeps.product.repositories.KaartRepository;
import sr.unasat.beroeps.product.repositories.ZaalRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        FilmRepository filmRepo = new FilmRepository();
        ZaalRepository zaalRepo = new ZaalRepository();
        FilmVoorstellingRepository filmVoorstellingRepo = new FilmVoorstellingRepository();
        KaartRepository kaartRepo = new KaartRepository();


        //-----Inserting films
        Film film1 = new Film("The Conjuring", "Horror", "2D", 15.5);
        Film film2 = new Film("Bad Boys", "Comedy", "3D", 25);
        Film film3 = new Film("The Nun", "Horror", "3D", 35);
        filmRepo.insertFilm(film1);
        filmRepo.insertFilm(film2);
        filmRepo.insertFilm(film3);


        //-----Inserting the halls
        Zaal zaal1 = new Zaal(1, 30);
        Zaal zaal2 = new Zaal(2, 5);
        zaalRepo.insertZaal(zaal1);
        zaalRepo.insertZaal(zaal2);


        //------Inserting records in the filmVoorstelling(T3) table
        Film foundFilmRecord = filmRepo.findFilm("Bad boys");
        Zaal foundZaalRecord = zaalRepo.findZaal(1);
        FilmVoorstelling filmVoorstelling1 = new FilmVoorstelling(LocalDate.of(2020, 6, 10),
                LocalTime.of(15, 30), foundFilmRecord, foundZaalRecord);
        filmVoorstellingRepo.insertFilmVoorstelling(filmVoorstelling1);

        foundFilmRecord = filmRepo.findFilm("The Nun");
        foundZaalRecord = zaalRepo.findZaal(1);
        FilmVoorstelling filmVoorstelling2 = new FilmVoorstelling(LocalDate.of(2021, 6, 10),
                LocalTime.of(15, 30), foundFilmRecord, foundZaalRecord);
        filmVoorstellingRepo.insertFilmVoorstelling(filmVoorstelling2);


        //------Updating the filmVoorstelling record that's planned on 2020-06-10
        FilmVoorstelling foundFilmVoorstelling = filmVoorstellingRepo.findFilmVoorstelling(LocalDate.of(2020, 6, 10));
        foundFilmVoorstelling.setStartDatum(LocalDate.of(2030, 6, 13));
        foundFilmVoorstelling.setFilm(filmRepo.findFilm("The Conjuring"));
        foundFilmVoorstelling.setZaal(zaalRepo.findZaal(2));
        filmVoorstellingRepo.updateFilmVoorstelling(foundFilmVoorstelling);

        System.out.println();

        //------Displaying all the records of the filmVoorstelling table
        List<FilmVoorstelling> filmVoorstellingList = filmVoorstellingRepo.getAllFilmVoorstellingen();
        for (FilmVoorstelling filmVoorstelling : filmVoorstellingList) {
            System.out.println(filmVoorstelling);
        }

        System.out.println();

        //------Deleting the filmVoorstelling record that's planned on 2021-6-10
        foundFilmVoorstelling = filmVoorstellingRepo.findFilmVoorstelling(LocalDate.of(2021, 6, 10));
        filmVoorstellingRepo.deleteFilmVoorstelling(foundFilmVoorstelling);


        //------Selling a film ticket
        Kaart kaart = new Kaart(filmVoorstellingRepo.findFilmVoorstelling(LocalDate.of(2030,6,13)));
        kaartRepo.sellKaart(kaart);


    }

}
