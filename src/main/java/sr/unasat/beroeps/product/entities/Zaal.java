package sr.unasat.beroeps.product.entities;

public class Zaal {
    private int zaalId;
    private int zaalNummer;
    private int aantalZitPlaatsen;

    public Zaal(int zaalId, int zaalNummer, int aantalZitPlaatsen) {
        this.zaalId = zaalId;
        this.zaalNummer = zaalNummer;
        this.aantalZitPlaatsen = aantalZitPlaatsen;
    }

    public Zaal(int zaalNummer, int aantalZitPlaatsen) {
        this.zaalNummer = zaalNummer;
        this.aantalZitPlaatsen = aantalZitPlaatsen;
    }

    public void setZaalNummer(int zaalNummer) {
        this.zaalNummer = zaalNummer;
    }

    public void setAantalZitPlaatsen(int aantalZitPlaatsen) {
        this.aantalZitPlaatsen = aantalZitPlaatsen;
    }

    public int getZaalId() {
        return zaalId;
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
                "id=" + zaalId +
                ", zaalNummer=" + zaalNummer +
                ", aantalZitPlaatsen=" + aantalZitPlaatsen +
                '}';
    }
}
