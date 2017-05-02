package statistics.matcher;

import java.util.Arrays;
import statistics.Player;

/**
 *
 * @author Sara
 */
public class Or implements Matcher {

    private final Matcher[] matchers;

    public Or(Matcher... matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Player p) {
        return Arrays.stream(this.matchers)
                .anyMatch((matcher) -> matcher.matches(p));
    }
}
