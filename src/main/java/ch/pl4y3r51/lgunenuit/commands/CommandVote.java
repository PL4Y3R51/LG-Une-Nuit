package ch.pl4y3r51.lgunenuit.commands;

import ch.pl4y3r51.lgunenuit.Game;
import ch.pl4y3r51.lgunenuit.GameState;
import ch.pl4y3r51.lgunenuit.bean.IngamePlayers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandVote implements CommandExecutor {

    private final Game game;

    public CommandVote(Game game) {
        this.game = game;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (game.getGameState() == GameState.DAY) {
                IngamePlayers player1 = null;
                for (IngamePlayers p : game.getIngamePlayersList()) {
                    if (p.getPlayer().getName().equals(player.getName())) {
                        player1 = p;
                    }
                }

                if (args.length == 1) {
                    if (!player1.isAsVoted()) {
                        if (args[0].equalsIgnoreCase("personne")) {
                            game.getWrk().setVotePersonne(game.getWrk().getVotePersonne() + 1);
                        } else {
                            for (IngamePlayers p : game.getVotablePlayersList()) {
                                if (args[0].equalsIgnoreCase(p.getPlayer().getName())) {
                                    if (args[0].equalsIgnoreCase(player.getName())) {
                                        player.sendMessage("Vous ne pouvez pas vous voter vous-même");
                                        return true;
                                    } else {
                                        player.sendMessage("Votre vote a bien été comptabilisé");
                                        player1.setAsVoted(true);
                                        p.setVote(p.getVote() + 1);
                                        player1.setVotedPlayer(p);
                                        return true;
                                    }
                                }
                            }
                        }
                    } else {
                        player.sendMessage("Vous avez déjà voté !");
                    }
                    player.sendMessage("Le joueur indiqué n'est pas votable");
                } else {
                    player.sendMessage("Syntaxe de la commande : /vote <pseudo du joueur>");
                }

            } else {
                player.sendMessage("Il faut être au vote pour utiliser cette commande");

            }
        }
        return false;
    }
}
