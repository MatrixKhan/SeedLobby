package com.theseedmc.lobby.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.theseedmc.lobby.SeedLobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Utils {

    public static String replaceColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public static void teleportSpawn(Player player) {
        World world = Bukkit.getWorld(SeedLobby.get().getConfig().getString("Spawn.world"));
        double x = SeedLobby.get().getConfig().getDouble("Spawn.X");
        double y = SeedLobby.get().getConfig().getDouble("Spawn.Y");
        double z = SeedLobby.get().getConfig().getDouble("Spawn.Z");
        float yaw = (float) SeedLobby.get().getConfig().getDouble("Spawn.yaw");
        float pitch = (float) SeedLobby.get().getConfig().getDouble("Spawn.pitch");
        Location location = new Location(world, x, y, z);
        location.setYaw(yaw);
        location.setPitch(pitch);
        player.setFallDistance(0.0f);
        player.teleport(location);
    }

    public static void sendMOTD(Player player) {
        player.sendMessage(replaceColors("&7&m=------------------------------------------="));
        player.sendMessage(replaceColors("&7&l»                &fWelcome to &a&lThe Seed&f."));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + player.getName() + " [\"\"\"\",{\"\"text\"\":\"\"»          &7[\"\",\"\"color\"\":\"\"gray\"\",\"\"bold\"\":\"\"true\"\"},{\"\"text\"\":\"\"MELON\"\",\"\"color\"\":\"\"green\"\",\"\"bold\"\":true,\"\"clickEvent\"\":{\"\"action\"\":\"\"run_command\"\",\"\"value\"\":\"\"/transfer melon\"\"},\"\"hoverEvent\"\":{\"\"action\"\":\"\"show_text\"\",\"\"value\"\":{\"\"text\"\":\"\"\"\",\"\"extra\"\":[{\"\"text\"\":\"\"Click to connect!\"\",\"\"color\"\":\"\"gray\"\"}]}}},{\"\"text\"\":\"\"]  \"\",\"\"color\"\":\"\"gray\"\",\"\"bold\"\":false},{\"\"text\"\":\"\"[\"\",\"\"color\"\":\"\"gray\"\"},{\"\"text\"\":\"\"PUMPKIN\"\",\"\"color\"\":\"\"gold\"\",\"\"bold\"\":true,\"\"clickEvent\"\":{\"\"action\"\":\"\"run_command\"\",\"\"value\"\":\"\"/transfer pumpkin\"\"},\"\"hoverEvent\"\":{\"\"action\"\":\"\"show_text\"\",\"\"value\"\":{\"\"text\"\":\"\"\"\",\"\"extra\"\":[{\"\"text\"\":\"\"Click to connect!\"\",\"\"color\"\":\"\"gray\"\"}]}}},{\"\"text\"\":\"\"]  \"\",\"\"color\"\":\"\"gray\"\",\"\"bold\"\":false},{\"\"text\"\":\"\"[\"\",\"\"color\"\":\"\"gray\"\"},{\"\"text\"\":\"\"MUSHROOM\"\",\"\"color\"\":\"\"light_purple\"\",\"\"bold\"\":true,\"\"clickEvent\"\":{\"\"action\"\":\"\"run_command\"\",\"\"value\"\":\"\"/transfer mushroom\"\"},\"\"hoverEvent\"\":{\"\"action\"\":\"\"show_text\"\",\"\"value\"\":{\"\"text\"\":\"\"\"\",\"\"extra\"\":[{\"\"text\"\":\"\"Click to connect!\"\",\"\"color\"\":\"\"gray\"\"}]}}},{\"\"text\"\":\"\"]\"\",\"\"color\"\":\"\"gray\"\",\"\"bold\"\":false}]");
        player.sendMessage(replaceColors("&7&m=------------------------------------------="));
    }

    public static void sendPlayer(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        if (server.equalsIgnoreCase("melon")) {
            out.writeUTF("ConnectOther");
            out.writeUTF(player.getName());
            out.writeUTF("melon");
        } else if (server.equalsIgnoreCase("pumpkin")) {
            out.writeUTF("ConnectOther");
            out.writeUTF(player.getName());
            out.writeUTF("pumpkin");
        } else if (server.equalsIgnoreCase("mushroom")) {
            out.writeUTF("ConnectOther");
            out.writeUTF(player.getName());
            out.writeUTF("mushroom");
        } else {
            player.sendMessage(replaceColors("&c&lERROR: &7/transfer <melon|pumpkin|mushroom>"));
        }

        player.sendPluginMessage(SeedLobby.get(), "BungeeCord", out.toByteArray());
    }
}