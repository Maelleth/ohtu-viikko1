package ohtu.kivipaperisakset;


public class KPSTekoaly extends KPS{

    @Override
    public void ekaHaara() {
        tekoaly = new Tekoaly();
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