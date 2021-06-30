package ch.pl4y3r51.lgunenuit.listener;

import ch.pl4y3r51.lgunenuit.Game;
import ch.pl4y3r51.lgunenuit.GameState;
import ch.pl4y3r51.lgunenuit.GameTeam;
import ch.pl4y3r51.lgunenuit.bean.IngamePlayers;
import ch.pl4y3r51.lgunenuit.bean.Role;
import ch.pl4y3r51.lgunenuit.worker.Worker;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Objects;

public class ItemListener implements Listener {

    private final Game game;
    private final ItemStack KeySelected;
    private final ItemStack KeyUnselected;
    private final Worker wrk;
    private IngamePlayers choixUnNoiseuse;

    public ItemListener(Game game, Worker wrk) {
        this.game = game;
        this.wrk = wrk;
        KeySelected = itemWithName(Material.ALLIUM, "KEY_SELECTED");
        KeyUnselected = itemWithName(Material.ALLIUM, "KEY_UNSELECTED");
    }

    @EventHandler
    public void onItemInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        Action action = event.getAction();

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {

            if (item == null) {

            } else if (isItem(item, Material.COMPASS, "§cConfiguration des rôles")) {

                Inventory inv = Bukkit.createInventory(null, 9, "§3Menu Principal");
                inv.setItem(1, itemWithName(Material.GREEN_CONCRETE, "§aRoles sélectionnés"));
                inv.setItem(4, itemWithName(Material.NETHER_STAR, "§1Lancer la partie"));
                inv.setItem(7, itemWithName(Material.RED_CONCRETE, "§cRoles non-sélectionnés"));
                player.openInventory(inv);

            } else if (isItem(item, Material.NETHER_STAR, "ITEM LOUP-GAROU")) {
                //Checker la recherche
                Inventory inv = Bukkit.createInventory(null, 9, "§4Menu Mystèrieux");
                inv.setItem(1, itemWithName(Material.BLACK_CONCRETE, "§4Role Mystèrieux 1"));
                inv.setItem(4, itemWithName(Material.BLACK_CONCRETE, "§4Role Mystèrieux 2"));
                inv.setItem(7, itemWithName(Material.BLACK_CONCRETE, "§4Role Mystèrieux 3"));
                player.openInventory(inv);

            } else if (isItem(item, Material.NETHER_STAR, "ITEM VOYANTE - 1")) {

                Inventory inv = Bukkit.createInventory(null, 27, "§5Menu Mystèrieux");
                for (int i = 0; i < game.getIngamePlayersList().size(); i++) {
                    if (!(game.getIngamePlayersList().get(i).getPlayer().getName().equals(player.getName()))) {
                        inv.setItem(i, playerHead(game.getIngamePlayersList().get(i).getPlayer(), "§5"));
                    }
                }
                inv.setItem(inv.getSize() - 1, itemWithName(Material.BLACK_CONCRETE, "§5Role Mystèrieux 1"));
                inv.setItem(inv.getSize() - 2, itemWithName(Material.BLACK_CONCRETE, "§5Role Mystèrieux 2"));
                inv.setItem(inv.getSize() - 3, itemWithName(Material.BLACK_CONCRETE, "§5Role Mystèrieux 3"));
                player.openInventory(inv);

            } else if (isItem(item, Material.NETHER_STAR, "ITEM VOYANTE - 2")) {
                //Checker la recherche
                Inventory inv = Bukkit.createInventory(null, 9, "§5Menu Mystèrieux");
                inv.setItem(1, itemWithName(Material.BLACK_CONCRETE, "§4Role Mystèrieux 1"));
                inv.setItem(4, itemWithName(Material.BLACK_CONCRETE, "§4Role Mystèrieux 2"));
                inv.setItem(7, itemWithName(Material.BLACK_CONCRETE, "§4Role Mystèrieux 3"));
                player.openInventory(inv);

            } else if (isItem(item, Material.NETHER_STAR, "ITEM VOLEUR")) {
                Inventory inv = Bukkit.createInventory(null, 27, "§2Menu Mystèrieux");
                for (int i = 0; i < game.getIngamePlayersList().size(); i++) {
                    if (!(game.getIngamePlayersList().get(i).getPlayer().getName().equals(player.getName()))) {
                        inv.setItem(i, playerHead(game.getIngamePlayersList().get(i).getPlayer(), "§2"));
                    }
                }
                player.openInventory(inv);

            } else if (isItem(item, Material.NETHER_STAR, "ITEM NOISEUSE - 1")) {

                Inventory inv = Bukkit.createInventory(null, 27, "§6Menu Mystèrieux");
                for (int i = 0; i < game.getIngamePlayersList().size(); i++) {
                    if (!(game.getIngamePlayersList().get(i).getPlayer().getName().equals(player.getName()))) {
                        inv.setItem(i, playerHead(game.getIngamePlayersList().get(i).getPlayer(), "§6"));
                    }
                }
                player.openInventory(inv);

            } else if (isItem(item, Material.NETHER_STAR, "ITEM NOISEUSE - 2")) {

                Inventory inv = Bukkit.createInventory(null, 27, "§6Menu Mystèrieux");
                for (int i = 0; i < game.getIngamePlayersList().size(); i++) {
                    if ((!(game.getIngamePlayersList().get(i).getPlayer().getName().equals(player.getName()))) && (game.getIngamePlayersList().get(i) != choixUnNoiseuse)) {
                        inv.setItem(i, playerHead(game.getIngamePlayersList().get(i).getPlayer(), "§c"));
                    }
                }
                player.openInventory(inv);

            } else if (isItem(item, Material.NETHER_STAR, "ITEM SOÛLARD")) {
                //Checker la recherche
                Inventory inv = Bukkit.createInventory(null, 9, "§3Menu Mystèrieux");
                inv.setItem(1, itemWithName(Material.BLACK_CONCRETE, "§3Role Mystèrieux 1"));
                inv.setItem(4, itemWithName(Material.BLACK_CONCRETE, "§3Role Mystèrieux 2"));
                inv.setItem(7, itemWithName(Material.BLACK_CONCRETE, "§3Role Mystèrieux 3"));
                player.openInventory(inv);

            }
        }
    }

    @EventHandler
    public void onItemClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        Inventory selectedRolesInv = Bukkit.createInventory(null, 45, "§a Roles selectionnés");
        Inventory unselectedRolesInv = Bukkit.createInventory(null, 45, "§c Roles non-selectionnés");
        Player player = (Player) event.getWhoClicked();
        ItemStack itemClicked = event.getCurrentItem();
        if (itemClicked == null) return;

        if (isItem(itemClicked, Material.GREEN_CONCRETE, "§aRoles sélectionnés")) {
            event.setCancelled(true);
            player.closeInventory();
            for (int i = 0; i < game.getSelectedRoles().size(); i++) {
                String color;
                if (game.getSelectedRoles().get(i).getTeam() == GameTeam.LG) {
                    color = "§4";
                } else if (game.getSelectedRoles().get(i).getTeam() == GameTeam.VILLAGE) {
                    color = "§2";
                } else {
                    color = "§9";
                }
                selectedRolesInv.setItem(i, itemWithName(game.getSelectedRoles().get(i).getRoleItemPreview().getType(), (color + game.getSelectedRoles().get(i).getNom() + " [" + game.getSelectedRoles().get(i).getDifficulty() + "]")));
            }
            selectedRolesInv.setItem(44, KeySelected);
            player.openInventory(selectedRolesInv);
        }

        if (isItem(itemClicked, Material.NETHER_STAR, "§1Lancer la partie")) {
            event.setCancelled(true);
            if (game.getIngamePlayersList().size() + 3 == game.getSelectedRoles().size()) {
                Bukkit.broadcastMessage("[§5One Night LG§r] §bLe jeu va commencer");
                game.setGameState(GameState.NIGHT);
                wrk.startGame();
            } else {
                Bukkit.broadcastMessage("[§5One Night LG§r] §cLe jeu ne peux pas commencer car il y a " + game.getSelectedRoles().size() + " roles pour " + game.getIngamePlayersList().size() + " joueurs");
            }
            player.closeInventory();
        }

        if (isItem(itemClicked, Material.RED_CONCRETE, "§cRoles non-sélectionnés")) {
            event.setCancelled(true);
            player.closeInventory();
            for (int i = 0; i < game.getUnselectedRoles().size(); i++) {
                String color;
                if (game.getUnselectedRoles().get(i).getTeam() == GameTeam.LG) {
                    color = "§4";
                } else if (game.getUnselectedRoles().get(i).getTeam() == GameTeam.VILLAGE) {
                    color = "§2";
                } else {
                    color = "§9";
                }
                unselectedRolesInv.setItem(i, itemWithName(game.getUnselectedRoles().get(i).getRoleItemPreview().getType(), (color + game.getUnselectedRoles().get(i).getNom() + " [" + game.getUnselectedRoles().get(i).getDifficulty() + "]")));
            }
            unselectedRolesInv.setItem(44, KeyUnselected);
            player.openInventory(unselectedRolesInv);
        }

        //Répondre à la recherche sur role mystère en une fois par voyante -2 ou loup garou
        if (isItem(itemClicked, Material.BLACK_CONCRETE, "§4Role Mystèrieux 1")) {
            event.setCancelled(true);
            player.closeInventory();
            player.sendMessage("Le rôle mytèrieux N° 1 est " + game.getMysteriousRoles().get(0).getNom());
            player.getInventory().clear();
        }
        if (isItem(itemClicked, Material.BLACK_CONCRETE, "§4Role Mystèrieux 2")) {
            event.setCancelled(true);
            player.closeInventory();
            player.sendMessage("Le rôle mytèrieux N° 2 est " + game.getMysteriousRoles().get(1).getNom());
            player.getInventory().clear();
        }
        if (isItem(itemClicked, Material.BLACK_CONCRETE, "§4Role Mystèrieux 3")) {
            event.setCancelled(true);
            player.closeInventory();
            player.sendMessage("Le rôle mytèrieux N° 3 est " + game.getMysteriousRoles().get(2).getNom());
            player.getInventory().clear();
        }

        //Repondre a la recherche 1 de la voyante (Rôle Mystère ou Rôle principal) et voleur et noiseuse 1
        if (isItem(itemClicked, Material.BLACK_CONCRETE, "§5Role Mystèrieux 1")) {
            event.setCancelled(true);
            player.closeInventory();
            player.sendMessage("Le rôle mytèrieux N° 1 est " + game.getMysteriousRoles().get(0).getNom());
            player.getInventory().clear();
            player.getInventory().setItem(4, itemWithName(Material.NETHER_STAR, "ITEM VOYANTE - 2"));
        }
        if (isItem(itemClicked, Material.BLACK_CONCRETE, "§5Role Mystèrieux 2")) {
            event.setCancelled(true);
            player.closeInventory();
            player.sendMessage("Le rôle mytèrieux N° 2 est " + game.getMysteriousRoles().get(1).getNom());
            player.getInventory().clear();
            player.getInventory().setItem(4, itemWithName(Material.NETHER_STAR, "ITEM VOYANTE - 2"));
        }
        if (isItem(itemClicked, Material.BLACK_CONCRETE, "§5Role Mystèrieux 3")) {
            event.setCancelled(true);
            player.closeInventory();
            player.sendMessage("Le rôle mytèrieux N° 3 est " + game.getMysteriousRoles().get(2).getNom());
            player.getInventory().clear();
            player.getInventory().setItem(4, itemWithName(Material.NETHER_STAR, "ITEM VOYANTE - 2"));
        }
        for (IngamePlayers p : game.getIngamePlayersList()) {
            //Voyante
            if (isItem(itemClicked, Material.PLAYER_HEAD, "§5" + p.getPlayer().getName())) {
                event.setCancelled(true);
                player.closeInventory();
                player.sendMessage("Le rôle de " + p.getPlayer().getName() + " est " + p.getEndRole().getNom());
                player.getInventory().clear();
                break;
            }
            //Voleur
            if (isItem(itemClicked, Material.PLAYER_HEAD, "§2" + p.getPlayer().getName())) {
                event.setCancelled(true);
                player.closeInventory();
                //Changer le rôle
                IngamePlayers startVoleur = null;
                for (IngamePlayers voleur : game.getIngamePlayersList()) {
                    if (voleur.getPlayer().getName().equals(player.getName())) {
                        startVoleur = voleur;
                        break;
                    }
                }
                Role transition = startVoleur.getEndRole();
                startVoleur.setEndRole(p.getEndRole());
                p.setEndRole(transition);

                //Afficher le nouveau rôle
                player.sendMessage("Votre nouveau rôle est " + startVoleur.getEndRole().getNom());
                player.getInventory().clear();
                break;
            }
            //Noiseuse 1
            if (isItem(itemClicked, Material.PLAYER_HEAD, "§6" + p.getPlayer().getName())) {
                event.setCancelled(true);
                player.closeInventory();
                //Enregistrer la première personne séléctionnée par la noiseuse
                choixUnNoiseuse = p;
                player.getInventory().clear();
                player.getInventory().setItem(4, itemWithName(Material.NETHER_STAR, "ITEM NOISEUSE - 2"));
                break;
            }
            //Noiseuse 2
            if (isItem(itemClicked, Material.PLAYER_HEAD, "§c" + p.getPlayer().getName())) {
                event.setCancelled(true);
                player.closeInventory();
                //Enregistrer la première personne séléctionnée par la noiseuse
                Role transition = p.getEndRole();
                p.setEndRole(choixUnNoiseuse.getEndRole());
                choixUnNoiseuse.setEndRole(transition);
                player.getInventory().clear();
                break;
            }
        }
        //Répondre à la recherche du soulard
        if (isItem(itemClicked, Material.BLACK_CONCRETE, "§3Role Mystèrieux 1")) {
            event.setCancelled(true);
            player.closeInventory();

            IngamePlayers startSoulard = null;
            for (IngamePlayers soulard : game.getIngamePlayersList()) {
                if (soulard.getPlayer().getName().equals(player.getName())) {
                    startSoulard = soulard;
                    break;
                }
            }
            Role transition = startSoulard.getEndRole();

            startSoulard.setEndRole(game.getMysteriousRoles().get(0));
            game.getMysteriousRoles().set(0, transition);

            player.getInventory().clear();
        }
        if (isItem(itemClicked, Material.BLACK_CONCRETE, "§3Role Mystèrieux 2")) {
            event.setCancelled(true);
            player.closeInventory();

            IngamePlayers startSoulard = null;
            for (IngamePlayers soulard : game.getIngamePlayersList()) {
                if (soulard.getPlayer().getName().equals(player.getName())) {
                    startSoulard = soulard;
                    break;
                }
            }
            Role transition = startSoulard.getEndRole();

            startSoulard.setEndRole(game.getMysteriousRoles().get(1));
            game.getMysteriousRoles().set(1, transition);

            player.getInventory().clear();
        }
        if (isItem(itemClicked, Material.BLACK_CONCRETE, "§3Role Mystèrieux 3")) {
            event.setCancelled(true);
            player.closeInventory();

            IngamePlayers startSoulard = null;
            for (IngamePlayers soulard : game.getIngamePlayersList()) {
                if (soulard.getPlayer().getName().equals(player.getName())) {
                    startSoulard = soulard;
                    break;
                }
            }
            Role transition = startSoulard.getEndRole();

            startSoulard.setEndRole(game.getMysteriousRoles().get(2));
            game.getMysteriousRoles().set(2, transition);

            player.getInventory().clear();
        }

        if (inv.contains(KeySelected)) {
            event.setCancelled(true);
            for (int i = 0; i < game.getSelectedRoles().size(); i++) {
                if (itemClicked.getItemMeta() != null) {
                    String color;
                    if (game.getSelectedRoles().get(i).getTeam() == GameTeam.LG) {
                        color = "§4";
                    } else if (game.getSelectedRoles().get(i).getTeam() == GameTeam.VILLAGE) {
                        color = "§2";
                    } else {
                        color = "§9";
                    }
                    if (itemClicked.getItemMeta().getDisplayName().equalsIgnoreCase((color + game.getSelectedRoles().get(i).getNom() + " [" + game.getSelectedRoles().get(i).getDifficulty() + "]"))) {
                        game.getUnselectedRoles().add(game.getSelectedRoles().get(i));
                        unselectedRolesInv.addItem(itemWithName(game.getSelectedRoles().get(i).getRoleItemPreview().getType(), game.getSelectedRoles().get(i).getNom()));
                        selectedRolesInv.remove(game.getSelectedRoles().get(i).getRoleItemPreview());
                        Bukkit.broadcastMessage("[§5One Night LG§r] §cLe role " + game.getSelectedRoles().get(i).getNom() + " à été retiré de la partie");
                        game.getSelectedRoles().remove(i);
                        player.closeInventory();
                        break;
                    }
                }
            }
        }

        if (inv.contains(KeyUnselected)) {
            event.setCancelled(true);
            for (int i = 0; i < game.getUnselectedRoles().size(); i++) {
                if (itemClicked.getItemMeta() != null) {
                    String color;
                    if (game.getUnselectedRoles().get(i).getTeam() == GameTeam.LG) {
                        color = "§4";
                    } else if (game.getUnselectedRoles().get(i).getTeam() == GameTeam.VILLAGE) {
                        color = "§2";
                    } else {
                        color = "§9";
                    }
                    if (itemClicked.getItemMeta().getDisplayName().equalsIgnoreCase((color + game.getUnselectedRoles().get(i).getNom() + " [" + game.getUnselectedRoles().get(i).getDifficulty() + "]"))) {
                        game.getSelectedRoles().add(game.getUnselectedRoles().get(i));
                        selectedRolesInv.addItem(itemWithName(game.getUnselectedRoles().get(i).getRoleItemPreview().getType(), game.getUnselectedRoles().get(i).getNom()));
                        unselectedRolesInv.remove(game.getUnselectedRoles().get(i).getRoleItemPreview());
                        Bukkit.broadcastMessage("[§5One Night LG§r] §aLe role " + game.getUnselectedRoles().get(i).getNom() + " à été ajouté à la partie");
                        game.getUnselectedRoles().remove(i);
                        player.closeInventory();
                        break;
                    }
                }
            }
        }

    }

    private ItemStack itemWithName(Material material, String displayName) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta != null) {
            itemMeta.setDisplayName(displayName);
        }
        item.setItemMeta(itemMeta);
        return item;
    }

    private ItemStack playerHead(Player p, String color) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
        if (itemMeta != null) {
            itemMeta.setOwningPlayer(p);
            itemMeta.setDisplayName(color + p.getName());
        }
        item.setItemMeta(itemMeta);
        return item;
    }

    private boolean isItem(ItemStack item, Material material, String displayName) {
        if (item.getType() == material) {
            if (item.hasItemMeta()) {
                if (Objects.requireNonNull(item.getItemMeta()).hasDisplayName()) {
                    return item.getItemMeta().getDisplayName().equalsIgnoreCase(displayName);
                }
            }
        }
        return false;
    }
}



