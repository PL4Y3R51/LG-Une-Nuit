package ch.pl4y3r51.lgunenuit.timer;

import ch.pl4y3r51.lgunenuit.Game;

import ch.pl4y3r51.lgunenuit.bean.IngamePlayers;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class SbireTimer extends BukkitRunnable {

    private final Game game;
    private int timer = 10;

    public SbireTimer(Game game) {
        this.game = game;
    }

    @Override
    public void run() {

        if (timer == 0) {
            for (IngamePlayers p : game.getIngamePlayersList()) {
                p.getPlayer().closeInventory();
                p.getPlayer().getInventory().clear();
            }
            Bukkit.broadcastMessage("[§5One Night LG§r] Le sbire se rendort");
            game.getWrk().playNightRole(game.getWrk().whoIsNextTurn(game.getSelectedRoles()));
            cancel();
        }

        timer--;
    }
}
