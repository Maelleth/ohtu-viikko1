package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varastoT;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void liikaaTavaraa() {
        varasto.lisaaVarastoon(12);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otetaanLiikaa() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(8);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void annetaanKelvollinenTilavuus() {
        varastoT = new Varasto(-3);
        
        assertEquals(0, varastoT.getTilavuus(), vertailuTarkkuus);
    }
    
     
    @Test
    public void varastolleTilavuusJaAlkuSaldo1() {
        varastoT = new Varasto(10, 5);
        
        assertEquals(10, varastoT.getTilavuus(), vertailuTarkkuus);
        assertEquals(5, varastoT.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastolleTilavuusJaAlkuSaldo2() {
        varastoT = new Varasto(-1, -1);
        
        assertEquals(0, varastoT.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varastoT.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastolleTilavuusJaAlkuSaldo3() {
        varastoT = new Varasto(4, 6);
        
        assertEquals(4, varastoT.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisataanNegatiivinen() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(-2);
        
        assertEquals(5, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void otetaanNegatiivinen() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(-2);
        
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringOikein() {
        varasto.lisaaVarastoon(4);
        
        assertEquals("saldo = 4.0, vielä tilaa 6.0", varasto.toString());
    }
    
}
