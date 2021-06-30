package ch.pl4y3r51.lgunenuit.commands;

import ch.pl4y3r51.lgunenuit.Game;
import ch.pl4y3r51.lgunenuit.GameState;
import ch.pl4y3r51.lgunenuit.GameTeam;
import ch.pl4y3r51.lgunenuit.bean.IngamePlayers;
import ch.pl4y3r51.lgunenuit.bean.Role;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandeIndice implements CommandExecutor {

    private final Game game;

    public CommandeIndice(Game game) {
        this.game = game;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (game.getGameState() == GameState.DAY) {
                if (args.length == 2) {
                    for (IngamePlayers p : game.getVotablePlayersList()) {
                        if (args[0].equalsIgnoreCase(p.getPlayer().getName())) {
                            for (Role r : game.getSelectedRoles()) {
                                if (args[1].equalsIgnoreCase(r.getNom())) {
                                    if (r.getTeam() == GameTeam.LG) {
                                        Bukkit.broadcastMessage(player.getName() + " indique : §4" + p.getPlayer().getName() + " est " + r.getNom());
                                        return true;
                                    } else if (r.getTeam() == GameTeam.VILLAGE) {
                                        Bukkit.broadcastMessage(player.getName() + " indique : §2" + p.getPlayer().getName() + " est " + r.getNom());
                                        return true;
                                    } else {
                                        Bukkit.broadcastMessage(player.getName() + " indique : §9" + p.getPlayer().getName() + " est " + r.getNom());
                                        return true;
                                    }
                                }
                            }
                            player.sendMessage("Le rôle indiqué n'est pas indicable");
                            return true;
                        }

                    }
                    player.sendMessage("Le joueur indiqué n'est pas indicable");
                    return true;
                } else {
                    player.sendMessage("Syntaxe de la commande : /indice <pseudo du joueur> <rôle>");
                }
            }
            player.sendMessage("Il faut être au vote pour utiliser cette commande");
        }
        return false;
    }
}
