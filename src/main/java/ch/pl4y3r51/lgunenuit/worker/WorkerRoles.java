package ch.pl4y3r51.lgunenuit.worker;

import ch.pl4y3r51.lgunenuit.Game;
import ch.pl4y3r51.lgunenuit.bean.IngamePlayers;
import ch.pl4y3r51.lgunenuit.bean.Role;
import ch.pl4y3r51.lgunenuit.timer.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class WorkerRoles {

    private Game game;

    public WorkerRoles(Game game) {
        this.game = game;
    }

    public void playDoppelganger(IngamePlayers p) {

    }

    public void playLoupGarou(List<IngamePlayers> players) {
        for (IngamePlayers p : game.getIngamePlayersList()){
            p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999999, 10));
            p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999999, 10));
        }
        for (IngamePlayers p: players) {
            for (PotionEffect pe: p.getPlayer().getActivePotionEffects()) {
                p.getPlayer().removePotionEffect(pe.getType());
            }
        }

        players.remove(null);
        Bukkit.broadcastMessage("[§5One Night LG§r] Les loups garous se réveillent");

        for (int i = 0; i < game.getSelectedRoles().size(); i++) {
            if (game.getSelectedRoles().get(i).getPassage() == 2) {
                game.getSelectedRoles().get(i).setPassed(true);
            }
        }

        switch (players.size()) {
            case 0:
                //il ne se passe rien, le timer se passe sans actions
                break;
            case 1:
                players.get(0).getPlayer().sendMessage("Vous êtes le seul loup-garou, utilisez l'étoile pour regarder un des rôles mytèrieux");
                //Donner Item de recherche
                players.get(0).getPlayer().getInventory().setItem(4, itemWithName(Material.NETHER_STAR, "ITEM LOUP-GAROU"));
                break;
            case 2:
                //Envoyer à chaque loup le nom de l'autre.
                for (IngamePlayers messageReceiver : players) {
                    for (IngamePlayers messageContent : players) {
                        messageReceiver.getPlayer().sendMessage("Le joueur " + messageContent.getPlayer().getName() + " est un Loup-Garou");
                    }
                }
                break;
            default:
                Bukkit.broadcastMessage("Error playLG");
        }
        LoupGarouTimer loupGarouTimer = new LoupGarouTimer(game);
        loupGarouTimer.runTaskTimer(game, 0, 20);
    }

    public void playSbire(IngamePlayers p) {
        for (IngamePlayers ingamePlayers : game.getIngamePlayersList()){
            ingamePlayers.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999999, 10));
            ingamePlayers.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999999, 10));
        }
        Bukkit.broadcastMessage("[§5One Night LG§r] Le sbire se réveille");

        for (int i = 0; i < game.getSelectedRoles().size(); i++) {
            if (game.getSelectedRoles().get(i).getPassage() == 3) {
                game.getSelectedRoles().get(i).setPassed(true);
            }
        }

        if (p != null) {
            for (PotionEffect pe:p.getPlayer().getActivePotionEffects()) {
                p.getPlayer().removePotionEffect(pe.getType());
            }
            for (IngamePlayers possibleLoupGarou : game.getIngamePlayersList()) {
                if (possibleLoupGarou.getEndRole().getPassage() == 2) {
                    p.getPlayer().sendMessage("Le joueur " + possibleLoupGarou.getPlayer().getName() + " est un Loup-Garou");
                }
            }
        }
        SbireTimer sbireTimer = new SbireTimer(game);
        sbireTimer.runTaskTimer(game, 0, 20);
    }

    public void playFrancMacon(List<IngamePlayers> players) {
        for (IngamePlayers p : game.getIngamePlayersList()){
            p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999999, 10));
            p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999999, 10));
        }


        for (IngamePlayers p: players) {
            for (PotionEffect pe: p.getPlayer().getActivePotionEffects()) {
                p.getPlayer().removePotionEffect(pe.getType());
            }
        }

        players.remove(null);
        Bukkit.broadcastMessage("[§5One Night LG§r] Les francs-maçons se réveillent");

        for (int i = 0; i < game.getSelectedRoles().size(); i++) {
            if (game.getSelectedRoles().get(i).getPassage() == 4) {
                game.getSelectedRoles().get(i).setPassed(true);
            }
        }

        switch (players.size()) {
            case 0:
                //il ne se passe rien, le timer se passe sans actions
                break;
            case 1:
                players.get(0).getPlayer().sendMessage("Vous êtes le seul franc-maçon, le deuxième franc-maçon est surement un rôle mystère");
                break;
            case 2:
                //Envoyer à chaque franc maçon le nom de l'autre.
                for (IngamePlayers messageReceiver : players) {
                    for (IngamePlayers messageContent : players) {
                        messageReceiver.getPlayer().sendMessage("Le joueur " + messageContent.getPlayer().getName() + " est un Franc-Maçon");
                    }
                }
                break;
            default:
                Bukkit.broadcastMessage("Error playFrancMacon");
        }

        FrancMaconsTimer francMaconsTimer = new FrancMaconsTimer(game);
        francMaconsTimer.runTaskTimer(game, 0, 20);

    }

    public void playVoyante(IngamePlayers p) {

        for (IngamePlayers ingamePlayers : game.getIngamePlayersList()){
            ingamePlayers.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999999, 10));
            ingamePlayers.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999999, 10));
        }

        Bukkit.broadcastMessage("[§5One Night LG§r] La voyante se réveille");

        for (int i = 0; i < game.getSelectedRoles().size(); i++) {
            if (game.getSelectedRoles().get(i).getPassage() == 5) {
                game.getSelectedRoles().get(i).setPassed(true);
            }
        }

        if (p != null) {
            for (PotionEffect pe:p.getPlayer().getActivePotionEffects()) {
                p.getPlayer().removePotionEffect(pe.getType());
            }
            //Donne l'item de voyante 1
            p.getPlayer().getInventory().setItem(4, itemWithName(Material.NETHER_STAR, "ITEM VOYANTE - 1"));
        }

        VoyanteTimer voyanteTimer = new VoyanteTimer(game);
        voyanteTimer.runTaskTimer(game, 0, 20);
    }

    public void playVoleur(IngamePlayers p) {

        for (IngamePlayers ingamePlayers : game.getIngamePlayersList()){
            ingamePlayers.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999999, 10));
            ingamePlayers.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999999, 10));
        }

        Bukkit.broadcastMessage("[§5One Night LG§r] Le voleur se réveille");

        for (int i = 0; i < game.getSelectedRoles().size(); i++) {
            if (game.getSelectedRoles().get(i).getPassage() == 6) {
                game.getSelectedRoles().get(i).setPassed(true);
            }
        }

        if (p != null) {
            for (PotionEffect pe:p.getPlayer().getActivePotionEffects()) {
                p.getPlayer().removePotionEffect(pe.getType());
            }
            //Donner l'item de voleur pour changer son rôle
            p.getPlayer().getInventory().setItem(4, itemWithName(Material.NETHER_STAR, "ITEM VOLEUR"));
        }

        VoleurTimer voleurTimer = new VoleurTimer(game);
        voleurTimer.runTaskTimer(game, 0, 20);

    }

    public void playNoiseuse(IngamePlayers p) {

        for (IngamePlayers ingamePlayers : game.getIngamePlayersList()){
            ingamePlayers.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999999, 10));
            ingamePlayers.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999999, 10));
        }

        Bukkit.broadcastMessage("[§5One Night LG§r] La noiseuse se réveille");

        for (int i = 0; i < game.getSelectedRoles().size(); i++) {
            if (game.getSelectedRoles().get(i).getPassage() == 7) {
                game.getSelectedRoles().get(i).setPassed(true);
            }
        }

        if (p != null) {
            for (PotionEffect pe:p.getPlayer().getActivePotionEffects()) {
                p.getPlayer().removePotionEffect(pe.getType());
            }
            //Donner l'item de noiseuse pour changer son rôle
            p.getPlayer().getInventory().setItem(4, itemWithName(Material.NETHER_STAR, "ITEM NOISEUSE - 1"));
        }

        NoiseuseTimer noiseuseTimer = new NoiseuseTimer(game);
        noiseuseTimer.runTaskTimer(game, 0, 20);
    }

    public void playSoulard(IngamePlayers p) {

        for (IngamePlayers ingamePlayers : game.getIngamePlayersList()){
            ingamePlayers.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999999, 10));
            ingamePlayers.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999999, 10));
        }

        Bukkit.broadcastMessage("[§5One Night LG§r] Le soulard se réveille");

        for (int i = 0; i < game.getSelectedRoles().size(); i++) {
            if (game.getSelectedRoles().get(i).getPassage() == 8) {
                game.getSelectedRoles().get(i).setPassed(true);
            }
        }

        if (p != null) {
            for (PotionEffect pe:p.getPlayer().getActivePotionEffects()) {
                p.getPlayer().removePotionEffect(pe.getType());
            }
            //Donner l'item de soulard pour changer son rôle
            p.getPlayer().getInventory().setItem(4, itemWithName(Material.NETHER_STAR, "ITEM SOÛLARD"));
        }

        SoulardTimer soulardTimer = new SoulardTimer(game);
        soulardTimer.runTaskTimer(game, 0, 20);

    }

    public void playInsomniaque(IngamePlayers p) {

        for (IngamePlayers ingamePlayers : game.getIngamePlayersList()){
            ingamePlayers.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999999, 10));
            ingamePlayers.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999999, 10));
        }

        Bukkit.broadcastMessage("[§5One Night LG§r] L'insomniaque se réveille");

        for (int i = 0; i < game.getSelectedRoles().size(); i++) {
            if (game.getSelectedRoles().get(i).getPassage() == 9) {
                game.getSelectedRoles().get(i).setPassed(true);
            }
        }

        if (p != null) {
            for (PotionEffect pe:p.getPlayer().getActivePotionEffects()) {
                p.getPlayer().removePotionEffect(pe.getType());
            }
            p.getPlayer().sendMessage("Votre role est maintenant " + p.getEndRole().getNom());
        }

        InsomniaqueTimer insomniaqueTimer = new InsomniaqueTimer(game);
        insomniaqueTimer.runTaskTimer(game, 0, 20);

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
}
