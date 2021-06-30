package ch.pl4y3r51.lgunenuit.timer;

import ch.pl4y3r51.lgunenuit.Game;
import ch.pl4y3r51.lgunenuit.bean.IngamePlayers;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class FirstStart extends BukkitRunnable {

    Game game;
    int timer = 20;

    public FirstStart(Game game) {
        this.game = game;
    }

    @Override
    public void run() {

        if (timer == 3 || timer == 2 || timer == 1){
            Bukkit.broadcastMessage("[§5One Night LG§r] Le jeu commence dans " + timer);
        }

        if (timer == 0) {
            game.getWrk().playNightRole(game.getWrk().whoIsNextTurn(game.getSelectedRoles()));
        }

        timer--;

    }
}
