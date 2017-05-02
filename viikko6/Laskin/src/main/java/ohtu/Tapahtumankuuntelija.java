package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.HashMap;
import java.util.Map;

public class Tapahtumankuuntelija implements ActionListener {

    private JButton plus;
    private JButton miinus;
    private JButton nollaa;
    private JButton undo;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private Map<JButton, Komento> komennot;
    private Komento edellinen;
    private Sovelluslogiikka sovellus;

    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        komennot = new HashMap<>();
        komennot.put(plus, new Summa(sovellus, tuloskentta, syotekentta));
        komennot.put(miinus, new Erotus(sovellus, tuloskentta, syotekentta));
        komennot.put(nollaa, new Nollaa(sovellus, tuloskentta, syotekentta));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Komento komento = komennot.get(ae.getSource());
        if (komento != null) {
            komento.suorita();
            edellinen = komento;
        } else {
            edellinen.peru();
            edellinen = null;
        }
        nollaa.setEnabled(sovellus.tulos() != 0);
        undo.setEnabled(edellinen != null);
    }

    public interface Komento {
        void suorita();
        void peru();
    }

    public class Summa implements Komento {
        private Sovelluslogiikka sovellus;
        private JTextField tuloskentta;
        JTextField syotekentta;
        private int edellinenTulos;

        public Summa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
            this.sovellus = sovellus;
            this.syotekentta = syotekentta;
            this.tuloskentta = tuloskentta;
        }

        @Override
        public void suorita() {
            this.edellinenTulos = sovellus.tulos();
            int arvo = 0;
            try {
                arvo = Integer.parseInt(syotekentta.getText());
            } catch (Exception e) {
            }
            sovellus.plus(arvo);
            this.tuloskentta.setText("" + sovellus.tulos());
            this.syotekentta.setText("");
        }

        @Override
        public void peru() {
            this.tuloskentta.setText("" + this.edellinenTulos);
        }
    }

    public class Erotus implements Komento {

        private Sovelluslogiikka sovellus;
        private JTextField tuloskentta;
        private int edellinenTulos;
        JTextField syotekentta;
        
        public Erotus(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
            this.sovellus = sovellus;
            this.syotekentta = syotekentta;
            this.tuloskentta = tuloskentta;
        }

        @Override
        public void suorita() {
            this.edellinenTulos = sovellus.tulos();
            int arvo = 0;
            try {
                arvo = Integer.parseInt(syotekentta.getText());
            } catch (Exception e) {
            }
            sovellus.miinus(arvo);
            this.tuloskentta.setText("" + sovellus.tulos());
            this.syotekentta.setText("");
        }

        @Override
        public void peru() {
            this.tuloskentta.setText("" + this.edellinenTulos);
        }
    }

    public class Nollaa implements Komento {

        private Sovelluslogiikka sovellus;
        private JTextField tuloskentta;
        private int edellinenTulos;
        JTextField syotekentta;

        public Nollaa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
            this.sovellus = sovellus;
            this.syotekentta = syotekentta;
            this.tuloskentta = tuloskentta;
        }

        @Override
        public void suorita() {
            this.edellinenTulos = sovellus.tulos();
            sovellus.nollaa();
            this.tuloskentta.setText("" + sovellus.tulos());
            this.syotekentta.setText("");
        }

        @Override
        public void peru() {
            this.tuloskentta.setText("" + this.edellinenTulos);
        }
    }

}
