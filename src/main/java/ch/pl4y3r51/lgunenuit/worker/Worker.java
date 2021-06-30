package ch.pl4y3r51.lgunenuit.worker;

import ch.pl4y3r51.lgunenuit.Game;
import ch.pl4y3r51.lgunenuit.GameState;
import ch.pl4y3r51.lgunenuit.GameTeam;
import ch.pl4y3r51.lgunenuit.bean.IngamePlayers;
import ch.pl4y3r51.lgunenuit.bean.Role;
import ch.pl4y3r51.lgunenuit.timer.FirstStart;
import ch.pl4y3r51.lgunenuit.timer.FreezeTimer;
import ch.pl4y3r51.lgunenuit.timer.VoteTimer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class Worker {

    private final Game game;
    private final WorkerRoles wrkRoles;
    private int votePersonne = 0;

    public Worker(Game game) {
        this.game = game;
        this.wrkRoles = new WorkerRoles(game);
    }

    public void startGame() {
        Random random = new Random();
        //Charger les spawns de chacuns
        for (int i = 0; i < random.nextInt(100); i++) {
            Collections.shuffle(game.getSelectedRoles());
            Collections.shuffle(game.getSpawns());
        }

        for (int i = 0; i < game.getIngamePlayersList().size(); i++) {
            game.getIngamePlayersList().get(i).setSpawn(game.getSpawns().get(i));
        }

        //DONNER LES EFFETS + clear inv
        for (IngamePlayers p : game.getIngamePlayersList()) {
            p.getPlayer().getInventory().clear();
            p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999999, 10));
            p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999999, 10));
            p.getPlayer().teleport(p.getSpawn());
        }

        //Donner les rôles ( + infos au joueur)
        for (int i = 0; i < game.getIngamePlayersList().size(); i++) {
            Role selectedRole = game.getSelectedRoles().get(i);

            String color = "";
            if (selectedRole.getTeam() == GameTeam.VILLAGE) {
                color = "§2";
            } else if (selectedRole.getTeam() == GameTeam.LG) {
                color = "§4";
            } else if (selectedRole.getTeam() == GameTeam.TANNEUR) {
                color = "§9";
            }

            game.getIngamePlayersList().get(i).setStartRole(selectedRole);
            game.getIngamePlayersList().get(i).setEndRole(selectedRole);

            Player p = game.getIngamePlayersList().get(i).getPlayer();
            p.sendMessage("§6------------------------");
            p.sendMessage("Vous possèdez le rôle :");
            p.sendMessage(color + selectedRole.getNom() + " (" + selectedRole.getDifficulty() + ")");
            p.sendMessage("§6------------------------");
            p.sendMessage(selectedRole.getDescription());
            p.sendMessage("§6------------------------");
            if (selectedRole.getPassage() > 0) {
                p.sendMessage("Ordre de Passage : " + selectedRole.getPassage());
            } else {
                p.sendMessage("Votre personnage ne fait aucune action durant la nuit ");
            }
            p.sendMessage("§6------------------------");
        }
        game.getMysteriousRoles().add(game.getSelectedRoles().get(game.getSelectedRoles().size() - 1));
        game.getMysteriousRoles().add(game.getSelectedRoles().get(game.getSelectedRoles().size() - 2));
        game.getMysteriousRoles().add(game.getSelectedRoles().get(game.getSelectedRoles().size() - 3));

        Collections.sort(game.getSelectedRoles());

        FirstStart firstStart = new FirstStart(game);
        firstStart.runTaskTimer(game, 0, 20);

        FreezeTimer freezeTimer = new FreezeTimer(game);
        freezeTimer.runTaskTimer(game, 0, 10);
        //Passage tour par tour de la nuit.

    }

    public int whoIsNextTurn(List<Role> roles) {
        int retour = -1;
        if ((roles != null) && (roles.size() > 0)) {
            for (Role r : roles) {
                if (!r.isPassed()) {
                    switch (r.getPassage()) {
                        case 1: //action DOPPELGANGER
                            retour = 1;
                            break;
                        case 2: // action LG
                            retour = 2;
                            break;
                        case 3: //action SBIRE
                            retour = 3;
                            break;
                        case 4: //action FRANC-MACONS
                            retour = 4;
                            break;
                        case 5: //action VOYANTE
                            retour = 5;
                            break;
                        case 6: //action VOLEUR
                            retour = 6;
                            break;
                        case 7: //action NOISEUSE
                            retour = 7;
                            break;
                        case 8: //action SOULARD
                            retour = 8;
                            break;
                        case 9: //action INSOMNIAQUE
                            retour = 9;
                            break;
                        default:
                            retour = -1;
                            break;
                    }
                    if (r.getPassage() > 0) {
                        break;
                    }
                }
            }
        }
        return retour;
    }

    public void playNightRole(int passage) {
        List<IngamePlayers> players = new ArrayList<>();
        for (IngamePlayers p : game.getIngamePlayersList()) {
            if (p.getStartRole().getPassage() == passage) {
                players.add(p);
            }
        }
        players.add(null);
        switch (passage) {
            case 1: //action DOPPELGANGER
                wrkRoles.playDoppelganger(players.get(0));
                break;
            case 2: // action LG
                wrkRoles.playLoupGarou(players);
                break;
            case 3: //action SBIRE
                wrkRoles.playSbire(players.get(0));
                break;
            case 4: //action FRANC-MACONS
                wrkRoles.playFrancMacon(players);
                break;
            case 5: //action VOYANTE
                wrkRoles.playVoyante(players.get(0));
                break;
            case 6: //action VOLEUR
                wrkRoles.playVoleur(players.get(0));
                break;
            case 7: //action NOISEUSE
                wrkRoles.playNoiseuse(players.get(0));
                break;
            case 8: //action SOULARD
                wrkRoles.playSoulard(players.get(0));
                break;
            case 9: //action INSOMNIAQUE
                wrkRoles.playInsomniaque(players.get(0));
                break;
            case -1:
                game.setGameState(GameState.DAY);
                Bukkit.broadcastMessage("Le jour se lève !");
                playDay();
                break;
        }
    }

    private void playDay() {
        //Enlever les effets
        //Infochat comment participer aux votes

        for (IngamePlayers p : game.getIngamePlayersList()) {
            game.getVotablePlayersList().add(p);
        }

        Bukkit.broadcastMessage("§6------------------------");
        Bukkit.broadcastMessage("C’est l’heure du vote !");
        Bukkit.broadcastMessage("§6------------------------");
        Bukkit.broadcastMessage("Afin de montrer qui est méchant et qui est gentil, vous pouvez utiliser la commande /indice < joueur> <rôle>.\n" +
                " Une fois que vous êtes sur de votre choix, vous pouvez voter définitivement pour le joueur que vous pensez loup garou à l’aide de la commande /vote <joueur> ");
        Bukkit.broadcastMessage("§6------------------------");

        //Temps du vote
        VoteTimer voteTimer = new VoteTimer(game);
        voteTimer.runTaskTimer(game, 0, 20);
    }

    public void checkVote() {
        ArrayList<Integer> votes = new ArrayList<>();
        for (IngamePlayers p : game.getIngamePlayersList()) {
            votes.add(p.getVote());
        }
        votes.add(votePersonne);
        Collections.sort(votes);

        int maxVotes = votes.get(votes.size() - 1);

        List<IngamePlayers> players = new ArrayList<>();
        for (IngamePlayers p : game.getIngamePlayersList()) {
            if (p.getVote() == maxVotes) {
                players.add(p);
            }
        }


        if (players.size() == 0) {
            List<IngamePlayers> winners = new ArrayList<>();
            boolean lgWin = false;
            for (IngamePlayers p : game.getIngamePlayersList()) {
                if (p.getEndRole().getPassage() == 2) {
                    lgWin = true;
                    winners.add(p);
                }
            }

            if (lgWin) {
                for (IngamePlayers p : game.getIngamePlayersList()) {
                    if (p.getEndRole().getPassage() == 3) {
                        winners.add(p);
                        break;
                    }
                }
                Bukkit.broadcastMessage("§6------------------------");
                Bukkit.broadcastMessage("§4L'équipe des Loups Garous ont remportés la partie !");
                Bukkit.broadcastMessage("§6------------------------");
                Bukkit.broadcastMessage("Gagnants :");
                for (IngamePlayers winner : winners) {
                    Bukkit.broadcastMessage(winner.getPlayer().getName() + " (" + winner.getEndRole().getNom() + ")");
                }
                Bukkit.broadcastMessage("§6------------------------");
            } else {
                for (IngamePlayers p : game.getIngamePlayersList()) {
                    if (p.getEndRole().getPassage() == 3) {
                        winners.add(p);
                        break;
                    }
                }
                //sbire win ?
                if (winners.size() == 1) {
                    Bukkit.broadcastMessage("§6------------------------");
                    Bukkit.broadcastMessage("§4Le sbire a remporté la partie !");
                    Bukkit.broadcastMessage("§6------------------------");
                    Bukkit.broadcastMessage("Gagnants :");
                    for (IngamePlayers winner : winners) {
                        Bukkit.broadcastMessage(winner.getPlayer().getName() + " (" + winner.getEndRole().getNom() + ")");
                    }
                    Bukkit.broadcastMessage("§6------------------------");
                } else {
                    //Vilage Win
                    Bukkit.broadcastMessage("§6------------------------");
                    Bukkit.broadcastMessage("§2L'équipe des Villageois ont remportés la partie !");
                    Bukkit.broadcastMessage("§6------------------------");
                    Bukkit.broadcastMessage("Les villageois ont décrété qu'il n'y avait aucune menace dans le village, et ils avaient raison !");
                    Bukkit.broadcastMessage("§6------------------------");
                }
            }


        } else if (players.size() == 1) {
            eliminate(players.get(0));
        } else {
            for (IngamePlayers p : game.getIngamePlayersList()) {
                p.setAsVoted(false);
                p.setVote(0);
            }
            game.setVotablePlayersList(players);
            Bukkit.broadcastMessage("Le vote a terminé sur une égalité parfaite, votez à nouveau parmis les joueurs les plus votés de la dernière partie.");

            VoteTimer voteTimer = new VoteTimer(game);
            voteTimer.runTaskTimer(game, 0, 20);
        }
    }

    private boolean eliminate(IngamePlayers p) {
        game.setGameState(GameState.FINISHED);
        Bukkit.broadcastMessage("Le joueur " + p.getPlayer().getName() + " (" + p.getEndRole().getNom() + ") a été éliminé avec " + p.getVote() + " votes");

        if (p.getEndRole().getNom().equalsIgnoreCase("CHASSEUR")) {
            eliminate(p.getVotedPlayer());
            return true;
        } else {
            if (p.getEndRole().getTeam() == GameTeam.TANNEUR) {
                //Win Tanneur
                Bukkit.broadcastMessage("§6------------------------");
                Bukkit.broadcastMessage("§9L'équipe du Tanneur a remporté la partie !");
                Bukkit.broadcastMessage("§6------------------------");
                Bukkit.broadcastMessage("Gagnants :");
                Bukkit.broadcastMessage(p.getPlayer().getName() + " (" + p.getEndRole().getNom() + ")");
                Bukkit.broadcastMessage("§6------------------------");
            } else if (p.getEndRole().getTeam() == GameTeam.LG) {
                List<IngamePlayers> winners = new ArrayList<>();

                //Check sbire
                if (p.getEndRole().getPassage() == 3) {
                    boolean lgWin = false;
                    for (IngamePlayers player : game.getIngamePlayersList()) {
                        if (player.getEndRole().getPassage() == 2) {
                            winners.add(player);
                            lgWin = true;
                        }
                    }
                    if (lgWin) {
                        winners.add(p);
                        Bukkit.broadcastMessage("§6------------------------");
                        Bukkit.broadcastMessage("§4L'équipe des Loups Garous ont remportés la partie !");
                        Bukkit.broadcastMessage("§6------------------------");
                        Bukkit.broadcastMessage("Gagnants :");
                        for (IngamePlayers winner : winners) {
                            Bukkit.broadcastMessage(winner.getPlayer().getName() + " (" + winner.getEndRole().getNom() + ")");
                        }
                        Bukkit.broadcastMessage("§6------------------------");
                    } else {
                        Bukkit.broadcastMessage("§6------------------------");
                        Bukkit.broadcastMessage("§2L'équipe des Villageois ont remportés la partie !");
                        Bukkit.broadcastMessage("§6------------------------");
                        Bukkit.broadcastMessage("Gagnants :");
                        for (IngamePlayers winner : game.getIngamePlayersList()) {
                            if (winner.getEndRole().getTeam() == GameTeam.VILLAGE) {
                                Bukkit.broadcastMessage(winner.getPlayer().getName() + " (" + winner.getEndRole().getNom() + ")");
                            }
                        }
                        Bukkit.broadcastMessage("§6------------------------");
                    }
                } else {
                    Bukkit.broadcastMessage("§6------------------------");
                    Bukkit.broadcastMessage("§2L'équipe des Villageois ont remportés la partie !");
                    Bukkit.broadcastMessage("§6------------------------");
                    Bukkit.broadcastMessage("Gagnants :");
                    for (IngamePlayers winner : game.getIngamePlayersList()) {
                        if (winner.getEndRole().getTeam() == GameTeam.VILLAGE) {
                            Bukkit.broadcastMessage(winner.getPlayer().getName() + " (" + winner.getEndRole().getNom() + ")");
                        }
                    }
                    Bukkit.broadcastMessage("§6------------------------");
                }
            } else if (p.getEndRole().getTeam() == GameTeam.VILLAGE) {
                List<IngamePlayers> winners = new ArrayList<>();

                boolean lgWin = false;
                for (IngamePlayers player : game.getIngamePlayersList()) {
                    if (player.getEndRole().getPassage() == 2) {
                        winners.add(player);
                        lgWin = true;
                    }
                }

                if (lgWin) {
                    for (IngamePlayers player : game.getIngamePlayersList()) {
                        if (player.getEndRole().getPassage() == 3) {
                            winners.add(player);
                        }
                    }
                    Bukkit.broadcastMessage("§6------------------------");
                    Bukkit.broadcastMessage("§4L'équipe des Loups Garous ont remportés la partie !");
                    Bukkit.broadcastMessage("§6------------------------");
                    Bukkit.broadcastMessage("Gagnants :");
                    for (IngamePlayers winner : winners) {
                        Bukkit.broadcastMessage(winner.getPlayer().getName() + " (" + winner.getEndRole().getNom() + ")");
                    }
                    Bukkit.broadcastMessage("§6------------------------");
                } else {
                    boolean sbireWin = false;
                    for (IngamePlayers player : game.getIngamePlayersList()) {
                        if (player.getEndRole().getPassage() == 3) {
                            sbireWin = true;
                            winners.add(player);
                        }
                    }
                    if (sbireWin) {
                        Bukkit.broadcastMessage("§6------------------------");
                        Bukkit.broadcastMessage("§4Le sbire a remporté la partie !");
                        Bukkit.broadcastMessage("§6------------------------");
                        Bukkit.broadcastMessage("Gagnants :");
                        for (IngamePlayers winner : winners) {
                            Bukkit.broadcastMessage(winner.getPlayer().getName() + " (" + winner.getEndRole().getNom() + ")");
                        }
                        Bukkit.broadcastMessage("§6------------------------");
                    } else {
                        Bukkit.broadcastMessage("§6------------------------");
                        Bukkit.broadcastMessage("§2L'équipe des Villageois ont remportés la partie !");
                        Bukkit.broadcastMessage("§6------------------------");
                        Bukkit.broadcastMessage("Les villageois semblait avoir des soupçons infondés... aucun d'eu ne se revele être méchant !");
                        Bukkit.broadcastMessage("(Paix a l'âme de " + p.getPlayer().getName() + ")");
                        Bukkit.broadcastMessage("§6------------------------");
                    }
                }
            }
        }
        Bukkit.broadcastMessage("§6------------------------");
        for (IngamePlayers allPlayer : game.getIngamePlayersList()) {
            String color1;
            String color2;
            if (allPlayer.getStartRole().getTeam() == GameTeam.LG) {
                color1 = "§4";
            } else if (allPlayer.getStartRole().getTeam() == GameTeam.VILLAGE) {
                color1 = "§2";
            } else {
                color1 = "§9";
            }
            if (allPlayer.getEndRole().getTeam() == GameTeam.LG) {
                color2 = "§4";
            } else if (allPlayer.getEndRole().getTeam() == GameTeam.VILLAGE) {
                color2 = "§2";
            } else {
                color2 = "§9";
            }
            Bukkit.broadcastMessage(allPlayer.getPlayer().getName() + " : " + color1 + allPlayer.getStartRole().getNom() + "§r - " + color2 + allPlayer.getEndRole().getNom());
        }
        return false;
    }


    public int getVotePersonne() {
        return votePersonne;
    }

    public void setVotePersonne(int votePersonne) {
        this.votePersonne = votePersonne;
    }
}
