package ch.pl4y3r51.lgunenuit;

import java.util.Locale;

public enum GameTeam {
    VILLAGE, LG, TANNEUR;

    @Override
    public String toString() {
        return name().toUpperCase();
    }
}
