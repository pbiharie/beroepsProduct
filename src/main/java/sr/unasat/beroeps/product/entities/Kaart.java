package sr.unasat.beroeps.product.entities;

import java.time.LocalDate;

public class Kaart {
    private int kaartId;
    private int kaartNummer;
    private int zitPlaatsNummer;
    private LocalDate verkoopDatum;
    private FilmVoorstelling filmVoorstelling;


    public Kaart(int kaartId, int kaartNummer, int zitPlaatsNummer, LocalDate verkoopDatum, FilmVoorstelling filmVoorstelling) {
        this.kaartId = kaartId;
        this.kaartNummer = kaartNummer;
        this.zitPlaatsNummer = zitPlaatsNummer;
        this.verkoopDatum = verkoopDatum;
        this.filmVoorstelling = filmVoorstelling;
    }

    public Kaart(FilmVoorstelling filmVoorstelling) {
        this.filmVoorstelling = filmVoorstelling;
    }

    public int getKaartId() {
        return kaartId;
    }

    public int getKaartNummer() {
        return kaartNummer;
    }

    public int getZitPlaatsNummer() {
        return zitPlaatsNummer;
    }

    public FilmVoorstelling getFilmVoorstelling() {
        return filmVoorstelling;
    }

    @Override
    public String toString() {
        return "Kaart{" +
                "kaartId=" + kaartId +
                ", kaartNummer=" + kaartNummer +
                ", zitPlaatsNummer=" + zitPlaatsNummer +
                ", verkoopDatum=" + verkoopDatum +
                ", filmStartDatum=" + filmVoorstelling.getStartDatum() +
                ", filmStartTijd=" + filmVoorstelling.getStartTijd() +
                ", filmNaam=" + filmVoorstelling.getFilm().getFilmNaam() +
                ", filmdPrijs=" + filmVoorstelling.getFilm().getPrijs() +
                ", zaal=" + filmVoorstelling.getZaal().getZaalNummer() +
                '}';
    }
}
