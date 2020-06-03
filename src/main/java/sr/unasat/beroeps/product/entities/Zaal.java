package sr.unasat.beroeps.product.entities;

public class Zaal {
    private int id;
    private int zaalNummer;
    private int aantalZitPlaatsen;

    public Zaal(int id, int zaalNummer, int aantalZitPlaatsen) {
        this.id = id;
        this.zaalNummer = zaalNummer;
        this.aantalZitPlaatsen = aantalZitPlaatsen;
    }

    public Zaal(int zaalNummer, int aantalZitPlaatsen) {
        this.zaalNummer = zaalNummer;
        this.aantalZitPlaatsen = aantalZitPlaatsen;
    }

    public int getZaalNummer() {
        return zaalNummer;
    }

    public int getAantalZitPlaatsen() {
        return aantalZitPlaatsen;
    }

    @Override
    public String toString() {
        return "Zaal{" +
                "id=" + id +
                ", zaalNummer=" + zaalNummer +
                ", aantalZitPlaatsen=" + aantalZitPlaatsen +
                '}';
    }
}
