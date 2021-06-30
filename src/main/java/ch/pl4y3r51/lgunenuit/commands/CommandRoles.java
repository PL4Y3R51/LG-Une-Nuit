package ch.pl4y3r51.lgunenuit.commands;

import ch.pl4y3r51.lgunenuit.Game;
import ch.pl4y3r51.lgunenuit.GameTeam;
import ch.pl4y3r51.lgunenuit.bean.Role;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRoles implements CommandExecutor {

    private Game game;

    public CommandRoles(Game game) {
        this.game = game;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("[§551-UHC§r] §6Les roles suivant sont dans la partie :");
            for (Role role : game.getSelectedRoles()) {
                if (role.getTeam() == GameTeam.LG) {
                    player.sendMessage("§4" + role.getNom());
                } else if (role.getTeam() == GameTeam.VILLAGE) {
                    player.sendMessage("§2" + role.getNom());
                }else{
                    player.sendMessage("§9" + role.getNom());
                }
            }
        }
        return false;
    }
}
