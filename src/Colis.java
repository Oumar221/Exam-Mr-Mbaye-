public abstract class Colis implements Comparable<Colis> {
    private String code;
    private int poids;
    private boolean horsDakar;

    public Colis(String code, int poids, boolean horsDakar) {
        if (poids <= 0) throw new IllegalArgumentException("poids invalide");
        this.code = code;
        this.poids = poids;
        this.horsDakar = horsDakar;
    }

    public String getCode() { return code; }
    public int getPoids() { return poids; }
    public boolean isHorsDakar() { return horsDakar; }

    public long tarif() {
        long t = poids * Parametres.TARIF_KG;
        if (horsDakar) t += Parametres.SURCHARGE_HORS_DAKAR;
        return t;
    }

    public abstract String sigle();
    public abstract int priorite();

    @Override
    public String toString() {
        return "[" + sigle() + "] " + code + " " + poids + "kg "
                + (horsDakar ? "hors-Dakar" : "Dakar") + " " + tarif() + " FCFA";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return code.equals(((Colis) o).code);
    }

    @Override
    public int hashCode() {
        return 31 * getClass().hashCode() + code.hashCode();
    }

    @Override
    public int compareTo(Colis o) {
        if (priorite() != o.priorite()) return Integer.compare(o.priorite(), priorite());
        if (tarif() != o.tarif()) return Long.compare(o.tarif(), tarif());
        return code.compareTo(o.code);
    }
}
