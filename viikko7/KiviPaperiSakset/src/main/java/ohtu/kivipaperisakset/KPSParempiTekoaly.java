package ohtu.kivipaperisakset;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KPS{

    @Override
    public void ekaHaara() {
        tekoaly = new TekoalyParannettu(20);
        tokanSiirto = tekoaly.annaSiirto();
        System.out.println();
        System.out.print("Tietokone valitsi: " + tokanSiirto);
    }

    @Override
    public void tokaHaara() {
        tokanSiirto = tekoaly.annaSiirto();
        System.out.print("Tietokone valitsi: " + tokanSiirto);
    }
}
