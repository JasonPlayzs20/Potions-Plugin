package com.jasonx.potions;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public final class Main extends JavaPlugin implements CommandExecutor, Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this,this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    ArrayList<Player> playerArrayList = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (playerArrayList.contains(player)) {
            playerArrayList.remove(player);
            player.sendMessage("removed you from the protection");
            return true;
        }playerArrayList.add(player);
        player.sendMessage("added you to the protection.");
        return true;
    }

    @EventHandler
    public void Listenerss(PlayerInteractEvent e){
        if (!playerArrayList.contains(e.getPlayer())) return;
        if (e.getAction() == Action.LEFT_CLICK_AIR){
            Player player = e.getPlayer();
            ItemStack potion;
            potion = new ItemStack(Material.SPLASH_POTION);
            PotionMeta meta = (PotionMeta) potion.getItemMeta();

            for (int i = 0;i <4;i++) {
                int rnd = ThreadLocalRandom.current().nextInt(PotionEffectType.values().length);
                meta.addCustomEffect(new PotionEffect(PotionEffectType.values()[rnd], 100 * 20, 2), true);
            }

            potion.setItemMeta(meta);
            ThrownPotion thrownPotion = player.launchProjectile(ThrownPotion.class);
            thrownPotion.setItem(potion);



        }
    }
    @EventHandler
    public void PotionLis(PlayerMoveEvent e){
        if (playerArrayList.contains(e.getPlayer())){
            Player p = e.getPlayer();
            p.removePotionEffect(PotionEffectType.BLINDNESS);
            p.removePotionEffect(PotionEffectType.BAD_OMEN);
            p.removePotionEffect(PotionEffectType.HUNGER);
            p.removePotionEffect(PotionEffectType.LEVITATION);
            p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
            p.removePotionEffect(PotionEffectType.CONFUSION);
            p.removePotionEffect(PotionEffectType.POISON);
            p.removePotionEffect(PotionEffectType.SLOW);
            p.removePotionEffect(PotionEffectType.WEAKNESS);
            p.removePotionEffect(PotionEffectType.WITHER);
            p.removePotionEffect(PotionEffectType.UNLUCK);
            p.removePotionEffect(PotionEffectType.HARM);

        }
    }
}
