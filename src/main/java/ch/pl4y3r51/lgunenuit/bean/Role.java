package ch.pl4y3r51.lgunenuit.bean;

import ch.pl4y3r51.lgunenuit.GameTeam;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Role implements Comparable{
    private int passage;
    private String nom;
    private String description;
    private List<ItemStack> items;
    private ItemStack roleItemPreview;
    private GameTeam team;
    private int difficulty;
    private boolean isPassed;

    public Role(int passage, ItemStack roleItemPreview, String nom, String description, List<ItemStack> items, GameTeam team, int difficulty) {
        this.passage = passage;
        this.roleItemPreview = roleItemPreview;
        this.nom = nom;
        this.description = description;
        this.items = items;
        this.team = team;
        this.difficulty = difficulty;
        this.isPassed = false;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public ItemStack getRoleItemPreview() {
        return roleItemPreview;
    }

    public GameTeam getTeam() {
        return team;
    }

    public int getPassage() {
        return passage;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    @Override
    public int compareTo(Object o) {
        Role r = (Role) o;
        int retour;
        if (this.passage < r.getPassage()) {
            retour = -1;
        } else if (this.passage == r.getPassage()) {
            retour = 0;
        } else {
            retour = 1;
        }
        return retour;
    }
}
