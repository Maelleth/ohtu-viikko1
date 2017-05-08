package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KPS{

    @Override
    public void ekaHaara() {
        System.out.print("Toisen pelaajan siirto: ");
        tokanSiirto = scanner.nextLine();
    }

    @Override
    public void tokaHaara() {
        ekaHaara();
    }

}