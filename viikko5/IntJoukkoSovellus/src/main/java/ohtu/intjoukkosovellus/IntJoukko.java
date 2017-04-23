package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, OLETUSKASVATUS = 5;
    private int kasvatuskoko;
    private int[] ljono;
    private int alkioidenLkm;

    public void intJoukkoFactory(int kapasiteetti, int kasvu) {
        if (kapasiteetti < 0 || kasvu < 0) {
            throw new IndexOutOfBoundsException("Ei negatiivisia lukuja");
        }
        ljono = new int[kapasiteetti];
        alkioidenLkm = 0;
        kasvatuskoko = kasvu;
    }

    public IntJoukko() {
        intJoukkoFactory(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        intJoukkoFactory(kapasiteetti, OLETUSKASVATUS);

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        intJoukkoFactory(kapasiteetti, kasvatuskoko);

    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            kasvataJosTaynna();
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }
        return false;
    }

    public void kasvataJosTaynna() {
        if (alkioidenLkm % ljono.length == 0) {
            int[] vanhaTaulukko = new int[ljono.length];
            vanhaTaulukko = ljono;
            kopioiTaulukko(ljono, vanhaTaulukko);
            ljono = new int[alkioidenLkm + kasvatuskoko];
            kopioiTaulukko(vanhaTaulukko, ljono);
        }
    }

    public int palautaIndeksi(int luku) {
        for (int i = 0; i < this.alkioidenLkm; i++) {
            if (luku == this.ljono[i]) {
                return i;
            }
        }
        return -1;
    }

    public boolean poista(int luku) {
        if (palautaIndeksi(luku) != -1) {
            for (int j = palautaIndeksi(luku); j < alkioidenLkm - 1; j++) {
                int apu = ljono[j];
                ljono[j] = ljono[j + 1];
                ljono[j + 1] = apu;
            }
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    public void kopioiTaulukko(int[] vanhaTaulukko, int[] uusi) {
        for (int i = 0; i < vanhaTaulukko.length; i++) {
            uusi[i] = vanhaTaulukko[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + ljono[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += ljono[i];
                tuotos += ", ";
            }
            tuotos += ljono[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulukko = new int[alkioidenLkm];
        for (int i = 0; i < taulukko.length; i++) {
            taulukko[i] = ljono[i];
        }
        return taulukko;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        for (int i = 0; i < a.toIntArray().length; i++) {
            yhdiste.lisaa(a.toIntArray()[i]);
        }
        for (int i = 0; i < b.toIntArray().length; i++) {
            yhdiste.lisaa(b.toIntArray()[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        for (int i = 0; i < a.toIntArray().length; i++) {
            for (int j = 0; j < b.toIntArray().length; j++) {
                if (a.toIntArray()[i] == b.toIntArray()[j]) {
                    leikkaus.lisaa(b.toIntArray()[j]);
                }
            }
        }
        return leikkaus;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        for (int i = 0; i < a.toIntArray().length; i++) {
            erotus.lisaa(a.toIntArray()[i]);
        }
        for (int i = 0; i < b.toIntArray().length; i++) {
            erotus.poista(i);
        }
        return erotus;
    }

}
