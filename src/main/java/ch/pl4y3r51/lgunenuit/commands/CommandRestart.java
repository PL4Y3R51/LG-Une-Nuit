package ch.pl4y3r51.lgunenuit.commands;

import ch.pl4y3r51.lgunenuit.Game;
import ch.pl4y3r51.lgunenuit.GameState;
import ch.pl4y3r51.lgunenuit.bean.IngamePlayers;
import ch.pl4y3r51.lgunenuit.bean.Role;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandRestart implements CommandExecutor {

    private final Game game;

    public CommandRestart(Game game) {
        this.game = game;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (game.getGameState() == GameState.FINISHED) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                for (Role r : game.getSelectedRoles()) {
                    r.setPassed(false);
                    game.getUnselectedRoles().add(r);
                }
                for (IngamePlayers p : game.getIngamePlayersList()) {
                    p.getPlayer().getInventory().clear();
                    p.setVote(0);
                    p.setAsVoted(false);
                }

                ItemStack chooseCompass = new ItemStack(Material.COMPASS);
                ItemMeta chooseCompassMeta = chooseCompass.getItemMeta();
                chooseCompassMeta.setDisplayName("§cConfiguration des rôles");
                chooseCompass.setItemMeta(chooseCompassMeta);
                player.getInventory().setItem(4, chooseCompass);

                game.getSelectedRoles().clear();
                game.setGameState(GameState.NOGAME);
            }
        }
        return false;
    }
}
