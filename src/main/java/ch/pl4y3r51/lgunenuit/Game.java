package ch.pl4y3r51.lgunenuit;

import ch.pl4y3r51.lgunenuit.bean.IngamePlayers;
import ch.pl4y3r51.lgunenuit.bean.Role;
import ch.pl4y3r51.lgunenuit.commands.CommandRestart;
import ch.pl4y3r51.lgunenuit.commands.CommandRoles;
import ch.pl4y3r51.lgunenuit.commands.CommandVote;
import ch.pl4y3r51.lgunenuit.commands.CommandeIndice;
import ch.pl4y3r51.lgunenuit.listener.ItemListener;
import ch.pl4y3r51.lgunenuit.listener.PlayerListener;
import ch.pl4y3r51.lgunenuit.worker.Worker;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Game extends JavaPlugin {

    private GameState gameState;
    private final MyLoader loader = new MyLoader();
    private final List<IngamePlayers> ingamePlayersList = new ArrayList<>();
    private List<IngamePlayers> votablePlayersList = new ArrayList<>();
    private final List<Role> mysteriousRoles = new ArrayList<>();
    private final List<Role> selectedRoles = new ArrayList<>();
    private List<Role> unselectedRoles = new ArrayList<>();
    private List<Location> spawns = new ArrayList<>();
    private final Worker wrk = new Worker(this);

    @Override
    public void onEnable() {
        getServer().getWorld("world").setPVP(false);
        getServer().getWorld("world").setDifficulty(Difficulty.PEACEFUL);

        unselectedRoles = loader.loadRoles();
        spawns = loader.loadSpawns();

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(this), this);
        pm.registerEvents(new ItemListener(this, wrk), this);

        getCommand("restartonenightlg").setExecutor(new CommandRestart(this));
        getCommand("vote").setExecutor(new CommandVote(this));
        getCommand("indice").setExecutor(new CommandeIndice(this));
        getCommand("roles").setExecutor(new CommandRoles(this));
        setGameState(GameState.NOGAME);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<IngamePlayers> getIngamePlayersList() {
        return ingamePlayersList;
    }

    public List<Role> getMysteriousRoles() {
        return mysteriousRoles;
    }

    public List<Role> getSelectedRoles() {
        return selectedRoles;
    }

    public List<Role> getUnselectedRoles() {
        return unselectedRoles;
    }

    public Worker getWrk() {
        return wrk;
    }

    public List<IngamePlayers> getVotablePlayersList() {
        return votablePlayersList;
    }

    public void setVotablePlayersList(List<IngamePlayers> votablePlayersList) {
        this.votablePlayersList = votablePlayersList;
    }
}
