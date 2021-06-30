package ch.pl4y3r51.lgunenuit;

import ch.pl4y3r51.lgunenuit.bean.Role;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MyLoader {



    public MyLoader() {

    }

    public List<Role> loadRoles(){
        List<Role> roles = new ArrayList<>();
        List<ItemStack> items = new ArrayList<>();

        roles.add(new Role(-1, new ItemStack(Material.LEATHER), "TANNEUR", "Le tanneur est un pauvre bougre : il déteste tellement son métier qu’il veut mourir. Il ne peut gagner que s’il meurt. À la fin de la partie, … \n" +
                "1. si le tanneur meurt mais aucun Loup-Garou, il remporte seul la partie.\n" +
                "2. si le tanneur et au moins 1 Loup-Garou meurent, le tanneur et le village l’emportent.\n", new ArrayList<>(items), GameTeam.TANNEUR,2));
        roles.add(new Role(-1, new ItemStack(Material.COOKED_BEEF), "CHASSEUR", "Le chasseur entraîne avec lui un autre personnage dans la tombe : s’il meurt, le joueur qu’il a voté meurt aussi.\n" +
                "Conseil : Le chasseur peut mourir mais tout de même l’emporter s’il pointe vers un Loup-Garou. S’il pense que ses partenaires ne soupçonnent pas le bon joueur, pourquoi ne pas essayer de se faire tuer en entraînant un Loup-Garou avec soi… ?\n", new ArrayList<>(items), GameTeam.VILLAGE, 2));
        roles.add(new Role(-1, new ItemStack(Material.PLAYER_HEAD), "VILLAGEOIS", "Le villageois ne possède aucune capacité spéciale.\n" +
                "Conseil : Les Loups-Garous prétendront souvent être des villageois. À l’inverse, à vous de faire attention que vos coéquipiers ne pensent pas que vous êtes des Loups-Garous !\n", new ArrayList<>(items), GameTeam.VILLAGE, 1));
        roles.add(new Role(-1, new ItemStack(Material.PLAYER_HEAD), "VILLAGEOIS", "Le villageois ne possède aucune capacité spéciale.\n" +
                "Conseil : Les Loups-Garous prétendront souvent être des villageois. À l’inverse, à vous de faire attention que vos coéquipiers ne pensent pas que vous êtes des Loups-Garous !\n", new ArrayList<>(items), GameTeam.VILLAGE, 1));
        roles.add(new Role(-1, new ItemStack(Material.PLAYER_HEAD), "VILLAGEOIS", "Le villageois ne possède aucune capacité spéciale.\n" +
                "Conseil : Les Loups-Garous prétendront souvent être des villageois. À l’inverse, à vous de faire attention que vos coéquipiers ne pensent pas que vous êtes des Loups-Garous !\n", new ArrayList<>(items), GameTeam.VILLAGE, 1));
        items.add(itemWithName(Material.NETHER_STAR, "ITEM LOUP-GAROU"));
        roles.add(new Role(2, new ItemStack(Material.BONE), "LOUP-GAROU", "Pendant la nuit, les Loups-Garous se reconnaissent.\n" +
                "Si un seul joueur est Loup-Garou, parce que l’autre est un rôle mystère, par exemple, il peut regarder un des rôles mystère.\n" +
                "Conseil : Pour le joueur qui est Loup-Garou, il est primordial d’entraîner les autres joueurs sur de fausses pistes. Il faut donc faire semblant d’incarner un autre personnage, de préférence avant que les autres aient donné trop d’indications sur le leur. Il peut, par exemple, prétendre être un villageois. Lorsqu’il n’y a qu’un Loup-Garou, il est facile de jouer le rôle de la carte que l’on a regardé au milieu de la table.\n", new ArrayList<>(items), GameTeam.LG, 1));
        roles.add(new Role(2, new ItemStack(Material.BONE), "LOUP-GAROU", "Pendant la nuit, les Loups-Garous se reconnaissent.\n" +
                "Si un seul joueur est Loup-Garou, parce que l’autre est un rôle mystère, par exemple, il peut regarder un des rôles mystère.\n" +
                "Conseil : Pour le joueur qui est Loup-Garou, il est primordial d’entraîner les autres joueurs sur de fausses pistes. Il faut donc faire semblant d’incarner un autre personnage, de préférence avant que les autres aient donné trop d’indications sur le leur. Il peut, par exemple, prétendre être un villageois. Lorsqu’il n’y a qu’un Loup-Garou, il est facile de jouer le rôle de la carte que l’on a regardé au milieu de la table.\n", new ArrayList<>(items), GameTeam.LG, 1));
        items.clear();
        roles.add(new Role(3, new ItemStack(Material.ROTTEN_FLESH), "SBIRE", "Le sbire connait les Loups-Garous, mais eux ne savent pas qui est le sbire. À la fin de la partie …\n" +
                "1. s’il y a des Loups-Garous en jeu, le sbire peut mourir et malgré tout remporter la partie ! Si le sbire meurt, mais aucun Loup-Garou, la meute de Loups-Garous l’emporte (et donc le sbire avec eux).\n" +
                "2. si aucun Loup-Garou n’est en jeu, le sbire ne gagne que s’il survit et qu’au moins un autre personnage meurt. Le sbire peut être un associé redoutable pour les Loups-Garous.\n" +
                "Conseil : Le sbire peut détourner les soupçons des Loups-Garous pour les attirer sur lui car, s’il meurt, il gagne quand même. Mais attention que ce ne soit pas trop flagrant…\n", new ArrayList<>(items), GameTeam.LG, 2));
        roles.add(new Role(4, new ItemStack(Material.BRICK), "FRANC-MACON", "Les cartes Franc-maçon entrent toujours en jeu ensemble. La nuit, ils ouvrent les yeux et se regardent. Si un seul franc-maçon ouvre les yeux, c’est que l’autre franc-maçon est un rôle mystère. Il n’a cependant pas le droit de regarder un des rôles mystère si il est seul.\n" +
                "Conseil : Ensemble, les francs-maçons sont très forts car ils peuvent se fournir mutuellement un alibi. Les Loups-Garous courageux peuvent également prétendre être des francs-maçons. Les vrais francs-maçons deviennent alors suspects. Utilisez-les de préférence à partir de 7 joueurs, sinon ils sont trop forts !\n", new ArrayList<>(items), GameTeam.VILLAGE, 3));
        roles.add(new Role(4, new ItemStack(Material.BRICK), "FRANC-MACON", "Les cartes Franc-maçon entrent toujours en jeu ensemble. La nuit, ils ouvrent les yeux et se regardent. Si un seul franc-maçon ouvre les yeux, c’est que l’autre franc-maçon est un rôle mystère. Il n’a cependant pas le droit de regarder un des rôles mystère si il est seul.\n" +
                "Conseil : Ensemble, les francs-maçons sont très forts car ils peuvent se fournir mutuellement un alibi. Les Loups-Garous courageux peuvent également prétendre être des francs-maçons. Les vrais francs-maçons deviennent alors suspects. Utilisez-les de préférence à partir de 7 joueurs, sinon ils sont trop forts !\n", new ArrayList<>(items), GameTeam.VILLAGE, 3));
        items.add(itemWithName(Material.NETHER_STAR, "ITEM VOYANTE"));
        roles.add(new Role(5, new ItemStack(Material.END_CRYSTAL), "VOYANTE", "Pendant la nuit, la voyante a le choix entre regarder le rôle d’un autre joueur ou deux rôles mystères. Elle ne doit échanger aucune carte.\n" +
                "Conseil : La force de la voyante est de démasquer les menteurs. Elle possède des informations sur les joueurs ou sur les cartes retournées au milieu. Lorsqu’un joueur contredit ces informations, elle peut facilement le confondre. En tant que voyante, il peut être intéressant d’attendre qu’un joueur dévoile un détail car il se peut qu’un Loup-Garou se trahisse rapidement.\n", new ArrayList<>(items), GameTeam.VILLAGE, 1));
        items.clear();
        items.add(itemWithName(Material.NETHER_STAR, "ITEM VOLEUR"));
        roles.add(new Role(6, new ItemStack(Material.LEATHER_BOOTS), "VOLEUR", "Pendant la nuit, le voleur échange son rôle avec celui d’un autre joueur. Il prend ensuite connaissance de son nouveau rôle volé. Attention ! Le voleur peut éventuellement changer d’équipe : S’il a échangé sa carte avec un Loup-Garou, par exemple, il rejoint alors la meute de Loups-Garous. Cependant, il n’effectue pas l’action de son nouveau personnage. S’il récupère l’insomniaque devant lui, il ne se réveille pas lorsque celle-ci est appelée. Le joueur qui a récupéré le voleur appartient désormais à l’équipe du village.", new ArrayList<>(items), GameTeam.VILLAGE, 1));
        items.clear();
        items.add(itemWithName(Material.NETHER_STAR, "ITEM NOISEUSE"));
        roles.add(new Role(7, new ItemStack(Material.SPIDER_EYE), "NOISEUSE", "La noiseuse intervertit rôles de deux autres joueurs. Mais elle n’a pas le droit de les regarder. Les deux joueurs auxquels appartenaient les rôles, rejoignent l’équipe correspondant au nouveau rôle.", new ArrayList<>(items), GameTeam.VILLAGE, 1));
        items.clear();
        items.add(itemWithName(Material.NETHER_STAR, "ITEM SOÛLARD"));
        roles.add(new Role(8, itemWithName(Material.POTION, "SOÛLARD (2)"), "SOÛLARD", "Le soûlard ne se souvient plus de son rôle : durant la nuit, il échange son rôle avec un des rôles mystère sans le regarder, il ne peut pas connaitre son nouveau rôle. Il endosse ce nouveau rôle et appartient à l’équipe correspondante… sans savoir laquelle !", new ArrayList<>(items), GameTeam.VILLAGE, 2));
        items.clear();
        roles.add(new Role(9, new ItemStack(Material.RED_BED), "INSOMNIAQUE", "L’insomniaque se réveille la dernière et vérifie si elle possède toujours le même rôle. Si son rôle a changé, elle rejoint alors l’équipe correspondant à son nouveau personnage.", new ArrayList<>(items), GameTeam.VILLAGE, 1));

        return  roles;
    }

    public List<Location> loadSpawns(){
        List<Location> spawns = new ArrayList<>();
            //1
            spawns.add(new Location(Bukkit.getWorld("world"), -234.500, 21.600, -67.500, -58, 10));
            //2
            spawns.add(new Location(Bukkit.getWorld("world"), -236.500, 21.600, -61.500, -90, 10));
            //3
            spawns.add(new Location(Bukkit.getWorld("world"), -234.500, 21.600, -55.500, -121, 10));
            //4
            spawns.add(new Location(Bukkit.getWorld("world"), -230.500, 21.600, -51.500, -150, 10));
            //5
            spawns.add(new Location(Bukkit.getWorld("world"), -224.500, 21.600, -50.500, -180, 10));
            //6
            spawns.add(new Location(Bukkit.getWorld("world"), -218.500, 21.600, -51.500, -150, 10));
            //7
            spawns.add(new Location(Bukkit.getWorld("world"), -214.500, 21.600, -55.500, -121, 10));
            //8
            spawns.add(new Location(Bukkit.getWorld("world"), -212.500, 21.600, -61.500, -90, 10));
            //9
            spawns.add(new Location(Bukkit.getWorld("world"), -214.500, 21.600, -67.500, -58, 10));
            //10
            spawns.add(new Location(Bukkit.getWorld("world"), -218.500, 21.600, -71.500, -32, 10));
            //11
            spawns.add(new Location(Bukkit.getWorld("world"), -224.500, 21.600, -73.500, -0, 10));
            //12
            spawns.add(new Location(Bukkit.getWorld("world"), -230.500, 21.600, -71.500, -32, 10));
        return spawns;
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
