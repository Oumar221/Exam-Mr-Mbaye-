public class Tournee {

    public static void trierFusion(Colis[] t) {
        if (t.length < 2) return;
        Colis[] tmp = new Colis[t.length];
        trier(t, tmp, 0, t.length - 1);
    }

    private static void trier(Colis[] t, Colis[] tmp, int g, int d) {
        if (g >= d) return;
        int m = (g + d) / 2;
        trier(t, tmp, g, m);
        trier(t, tmp, m + 1, d);
        fusionner(t, tmp, g, m, d);
    }

    private static void fusionner(Colis[] t, Colis[] tmp, int g, int m, int d) {
        int i = g, j = m + 1, k = g;
        while (i <= m && j <= d)
            tmp[k++] = (t[i].compareTo(t[j]) <= 0) ? t[i++] : t[j++];
        while (i <= m) tmp[k++] = t[i++];
        while (j <= d) tmp[k++] = t[j++];
        for (k = g; k <= d; k++) t[k] = tmp[k];
    }

    public static Colis[] chargementOptimal(Colis[] t, int capacite) {
        int n = t.length;
        long[][] meilleur = new long[n + 1][capacite + 1];
        for (int i = 1; i <= n; i++) {
            int p = t[i - 1].getPoids();
            long v = t[i - 1].tarif();
            for (int c = 0; c <= capacite; c++) {
                meilleur[i][c] = meilleur[i - 1][c];
                if (p <= c && meilleur[i - 1][c - p] + v > meilleur[i][c])
                    meilleur[i][c] = meilleur[i - 1][c - p] + v;
            }
        }
        int nb = 0, c = capacite;
        for (int i = n; i >= 1; i--)
            if (meilleur[i][c] != meilleur[i - 1][c]) {
                nb++;
                c -= t[i - 1].getPoids();
            }
        Colis[] res = new Colis[nb];
        int k = nb - 1;
        c = capacite;
        for (int i = n; i >= 1; i--)
            if (meilleur[i][c] != meilleur[i - 1][c]) {
                res[k--] = t[i - 1];
                c -= t[i - 1].getPoids();
            }
        return res;
    }

    public static void main(String[] args) {
        Colis[] lot = {
            new ColisStandard("C01", 4, false),
            new ColisExpress("C02", 3, true),
            new ColisFragile("C03", 5, false, 120_000),
            new ColisStandard("C04", 7, true),
            new ColisExpress("C05", 2, false),
            new ColisFragile("C06", 6, true, 45_000),
            new ColisStandard("C07", 1, true),
            new ColisExpress("C08", 8, false),
            new ColisFragile("C09", 3, false, 300_000),
            new ColisStandard("C10", 5, false),
        };
        trierFusion(lot);
        for (Colis x : lot) System.out.println(x);
        Colis[] choix = chargementOptimal(lot, Parametres.CAPACITE);
        long somme = 0;
        for (Colis x : choix) somme += x.tarif();
        System.out.println("Recette optimale : " + somme + " FCFA (capacite " + Parametres.CAPACITE + " kg)");
    }
}
