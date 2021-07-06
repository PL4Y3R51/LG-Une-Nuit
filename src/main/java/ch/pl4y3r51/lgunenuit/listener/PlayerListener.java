package ch.pl4y3r51.lgunenuit.listener;

import ch.pl4y3r51.lgunenuit.Game;
import ch.pl4y3r51.lgunenuit.GameState;
import ch.pl4y3r51.lgunenuit.bean.IngamePlayers;
import ch.pl4y3r51.lgunenuit.worker.Worker;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

public class PlayerListener implements Listener {

    private final Game game;

    public PlayerListener(Game game) {
        this.game = game;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ItemStack chooseCompass = new ItemStack(Material.COMPASS);
        ItemMeta chooseCompassMeta = chooseCompass.getItemMeta();
        chooseCompassMeta.setDisplayName("§cConfiguration des rôles");
        chooseCompass.setItemMeta(chooseCompassMeta);
        if (game.getGameState() != GameState.NOGAME) {
            player.kickPlayer("Une partie est déjà en court !");
        } else {
            game.getIngamePlayersList().add(new IngamePlayers(player));
            player.setHealth(20);
            player.setSaturation(20);
            player.setGameMode(GameMode.ADVENTURE);
            player.getInventory().clear();
            player.setAbsorptionAmount(0);
            for (PotionEffect effet : player.getActivePotionEffects()) {
                player.removePotionEffect(effet.getType());
            }
            if (player.isOp()) {
                player.setGameMode(GameMode.CREATIVE);
                player.getInventory().setItem(4, chooseCompass);
            }
            event.setJoinMessage(null);
            event.setJoinMessage("[§5One Night LG§r] §6" + player.getName() + "§a a rejoint la partie");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (game.getGameState() != GameState.NOGAME){
            for (int i = 0; i < game.getIngamePlayersList().size(); i++) {
                if (player.getName().equalsIgnoreCase(game.getIngamePlayersList().get(i).getPlayer().getName())) {
                    game.getIngamePlayersList().remove(i);
                }
            }
        }
        event.setQuitMessage(null);
        event.setQuitMessage("[§5One Night LG§r] §6" + player.getName() + "§c a quitté la partie");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (game.getGameState()==GameState.NIGHT) {
            e.setCancelled(true);
        }
    }
}
