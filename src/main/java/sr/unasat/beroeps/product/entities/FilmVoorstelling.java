package sr.unasat.beroeps.product.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FilmVoorstelling {
    private int id;
    private LocalDateTime startDatumTijd;
    private Film film;
    private Zaal zaal;

    public FilmVoorstelling(LocalDateTime startDatumTijd, Film film, Zaal zaal) {
        this.startDatumTijd = startDatumTijd;
        this.film = film;
        this.zaal = zaal;
    }

    public FilmVoorstelling(int id, LocalDateTime startDatumTijd, Film film, Zaal zaal) {
        this.id = id;
        this.startDatumTijd = startDatumTijd;
        this.film = film;
        this.zaal = zaal;
    }


    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm");
        return "FilmVoorstelling{" +
                "id=" + id +
                ", startDatumTijd=" + startDatumTijd.format(format) +
                ", film=" + film +
                ", zaal=" + zaal +
                '}';
    }
}
