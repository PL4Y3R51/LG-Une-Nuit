package ch.pl4y3r51.lgunenuit.timer;

import ch.pl4y3r51.lgunenuit.Game;
import ch.pl4y3r51.lgunenuit.bean.IngamePlayers;
import org.bukkit.scheduler.BukkitRunnable;

public class VoteTimer extends BukkitRunnable {

    private final Game game;

    public VoteTimer(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        //vote is finished
        int count = 0;
        for (IngamePlayers p : game.getIngamePlayersList()) {
            if (p.isAsVoted()) {
                count++;
            }
        }
        if (count == game.getIngamePlayersList().size()) {
            game.getWrk().checkVote();
            cancel();
        }
    }
}


