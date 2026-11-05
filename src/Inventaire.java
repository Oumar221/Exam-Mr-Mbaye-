public class Inventaire {
    public static long valeurAssuree(Object[] objets) {
        long s = 0;
        for (Object o : objets)
            if (o instanceof ColisFragile)
                s += ((ColisFragile) o).getValeurDeclaree();
        return s;
    }

    public static int[] compterParType(Object[] objets) {
        int[] r = new int[4];
        for (Object o : objets) {
            if (o == null) continue;
            if (o instanceof ColisFragile) r[2]++;
            else if (o instanceof ColisStandard) r[0]++;
            else if (o instanceof ColisExpress) r[1]++;
            else r[3]++;
        }
        return r;
    }
}
