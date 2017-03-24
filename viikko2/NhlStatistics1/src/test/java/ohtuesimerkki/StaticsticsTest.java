/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sara
 */
public class StaticsticsTest {

    Statistics stats;
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    @Before
    public void setUp() {
        // luodaan Staatistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchFindsCorrectPlayer() {
        Player player = stats.search("Kurri");
        assertEquals("Kurri", player.getName());
        assertEquals("EDM", player.getTeam());
        assertEquals(37, player.getGoals());
        assertEquals(53, player.getAssists());
    }

    @Test
    public void searchWorksWhenNothingIsFound() {
        assertEquals(null, stats.search("-"));
    }

    @Test
    public void teamReturnsRight() {
        Player player = stats.team("PIT").get(0);

        assertEquals("Lemieux", player.getName());
        assertEquals("PIT", player.getTeam());
        assertEquals(45, player.getGoals());
        assertEquals(54, player.getAssists());
    }

    @Test
    public void topScorersWorks() {
        Player player = stats.topScorers(1).get(0);

        assertEquals("Gretzky", player.getName());
        assertEquals("EDM", player.getTeam());
        assertEquals(35, player.getGoals());
        assertEquals(89, player.getAssists());
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
