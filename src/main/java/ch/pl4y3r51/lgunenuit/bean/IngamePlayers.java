package ch.pl4y3r51.lgunenuit.bean;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class IngamePlayers implements Comparable {

    private final Player player;
    private Role startRole;
    private Role endRole;
    private int vote = 0;
    private boolean asVoted = false;
    private IngamePlayers votedPlayer;
    private Location spawn;

    public IngamePlayers(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Role getStartRole() {
        return startRole;
    }

    public void setStartRole(Role startRole) {
        this.startRole = startRole;
    }

    public Role getEndRole() {
        return endRole;
    }

    public void setEndRole(Role endRole) {
        this.endRole = endRole;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public boolean isAsVoted() {
        return asVoted;
    }

    public void setAsVoted(boolean asVoted) {
        this.asVoted = asVoted;
    }

    public IngamePlayers getVotedPlayer() {
        return votedPlayer;
    }

    public void setVotedPlayer(IngamePlayers votedPlayer) {
        this.votedPlayer = votedPlayer;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    @Override
    public int compareTo(Object o) {

        IngamePlayers p = (IngamePlayers) o;
        Role r = p.getStartRole();
        return this.startRole.compareTo(r);
    }


}
