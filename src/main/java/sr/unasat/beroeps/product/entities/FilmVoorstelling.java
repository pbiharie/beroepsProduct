package sr.unasat.beroeps.product.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class FilmVoorstelling {
    private int id;
    private LocalDate startDatum;
    private LocalTime startTijd;
    private Film film;
    private Zaal zaal;


    public FilmVoorstelling(LocalDate startDatum, LocalTime startTijd, Film film, Zaal zaal) {
        this.startDatum = startDatum;
        this.startTijd = startTijd;
        this.film = film;
        this.zaal = zaal;
    }

    public FilmVoorstelling(int id, LocalDate startDatum, LocalTime startTijd, Film film, Zaal zaal) {
        this.id = id;
        this.startDatum = startDatum;
        this.startTijd = startTijd;
        this.film = film;
        this.zaal = zaal;
    }

    public int getId() {
        return id;
    }


    public LocalDate getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(LocalDate startDatum) {
        this.startDatum = startDatum;
    }

    public LocalTime getStartTijd() {
        return startTijd;
    }

    public void setStartTijd(LocalTime startTijd) {
        this.startTijd = startTijd;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Zaal getZaal() {
        return zaal;
    }

    public void setZaal(Zaal zaal) {
        this.zaal = zaal;
    }

    @Override
    public String toString() {
        return "FilmVoorstelling{" +
                "id=" + id +
                ", startDatum=" + startDatum +
                ", startTijd=" + startTijd +
                ", film=" + film +
                ", zaal=" + zaal +
                '}';
    }
}
