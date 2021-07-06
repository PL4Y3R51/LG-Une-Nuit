package ch.pl4y3r51.lgunenuit.timer;

import ch.pl4y3r51.lgunenuit.Game;
import ch.pl4y3r51.lgunenuit.GameState;
import ch.pl4y3r51.lgunenuit.bean.IngamePlayers;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class FreezeTimer extends BukkitRunnable {

    private Game game;

    public FreezeTimer(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        if (game.getGameState()== GameState.DAY){
            for (IngamePlayers p: game.getIngamePlayersList()) {
                p.getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);
                p.getPlayer().removePotionEffect(PotionEffectType.SLOW);
            }
            cancel();
        }
    }
}
