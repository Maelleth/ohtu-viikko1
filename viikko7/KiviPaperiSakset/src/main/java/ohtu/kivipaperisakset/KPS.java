/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.Scanner;

/**
 *
 * @author Sara
 */
public class KPS {

    static final Scanner scanner = new Scanner(System.in);
    static String ekanSiirto;
    static String tokanSiirto;
    static Tuomari tuomari;
    static Tekoaly tekoaly;

    private void ennenPelia() {
        tuomari = new Tuomari();
        System.out.print("Ensimmäisen pelaajan siirto: ");
        ekanSiirto = scanner.nextLine();
        ekaHaara();
    }

    private void looppi() {
        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();
            System.out.print("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = scanner.nextLine();
            tokaHaara();
        }
    }

    private void pelinJalkeen() {
        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }

    public void ekaHaara() {
    }

    public void tokaHaara() {
    }

    public void pelaa() {
        ennenPelia();
        ekaHaara();
        looppi();
        pelinJalkeen();
    }

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}
