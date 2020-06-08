package sr.unasat.beroeps.product.entities;

public class Film {
    private int filmId;
    private String filmNaam;
    private String genre;
    private String dimensionType;
    private double prijs;

    public Film(int filmId, String filmNaam, String genre, String dimensionType, double prijs) {
        this.filmId = filmId;
        this.filmNaam = filmNaam;
        this.genre = genre;
        this.dimensionType = dimensionType;
        this.prijs = prijs;
    }

    public Film(String filmNaam, String genre, String dimensionType, double prijs) {
        this.filmNaam = filmNaam;
        this.genre = genre;
        this.dimensionType = dimensionType;
        this.prijs = prijs;

    }

    public Film(int filmId) {
        this.filmId = filmId;
    }

    public int getFilmId() {
        return filmId;
    }

    public double getPrijs() {
        return prijs;
    }

    public String getFilmNaam() {
        return filmNaam;
    }

    public String getGenre() {
        return genre;
    }

    public String getDimensionType() {
        return dimensionType;
    }

    public void setFilmNaam(String filmNaam) {
        this.filmNaam = filmNaam;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDimensionType(String dimensionType) {
        this.dimensionType = dimensionType;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", filmNaam='" + filmNaam + '\'' +
                ", genre='" + genre + '\'' +
                ", dimensionType='" + dimensionType + '\'' +
                ", prijs=SRD" + prijs +
                '}';
    }
}
