package pl.yspar.core.comparator;

import java.util.*;

import pl.yspar.core.basic.User;


public class UserKillsComparator implements Comparator<User>
{
    @Override
    public int compare(final User g0, final User g1) {
        final Integer p0 = g0.getKills();
        final Integer p2 = g1.getKills();
        return p2.compareTo(p0);
    }
}
