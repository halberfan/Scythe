package fts.sense.listeners;

import fts.sense.Sense;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class blockBreakListener implements Listener {
    private final List<Material> crops = new ArrayList<Material>(Arrays.asList(
            Material.WHEAT,
            Material.POTATOES,
            Material.CARROTS,
            Material.BEETROOTS,
            Material.COCOA_BEANS
    ));
    @EventHandler
    private void farmBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.hasItemMeta() &&
            item.getItemMeta().getCustomModelData() == 999 &&
            crops.contains(event.getBlock().getType()) &&
            checkInventory(event.getBlock().getType(), event.getPlayer())) {

            if(item.getItemMeta() instanceof Damageable) {
                int enchDurabilityLevel = item.getItemMeta().getEnchantLevel(Enchantment.DURABILITY);
                ItemMeta itemMeta = item.getItemMeta();
                Damageable damage = (Damageable) itemMeta;
                if (enchDurabilityLevel == 0 || damageChance(enchDurabilityLevel)) {
                    damage.setDamage(damage.getDamage() + 1);
                    item.setItemMeta(itemMeta);

                    if (damage.getDamage() > item.getType().getMaxDurability()) {
                        player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1F, 1F);
                    }
                }
            }
            Material updatedMaterial = updateMaterial(event.getBlock().getType());
            for (int i = 0; i < 35; i++){

                if (player.getInventory().getItem(i) != null &&
                    player.getInventory().getItem(i).getType() == updatedMaterial) {
                        player.getInventory().getItem(i).setAmount(player.getInventory().getItem(i).getAmount() - 1);
                        break;
                }
            }
            placeBlock(event.getBlock(), event.getBlock().getType());

        }
    }
    private void placeBlock(Block block, Material material) {
        Bukkit.getScheduler().runTaskLater(Sense.getPlugin(), () -> {
            block.setType(material);
        }, 3L);  //delay in ticks
    }
    private boolean damageChance(int level) {
        int formula = (100 / level + 1);

        int chance = (int) (Math.random() * 100);

        return chance < formula;
    }
    private boolean checkInventory(Material material, Player player) {
        switch (material) {
            case WHEAT:
                return player.getInventory().contains(Material.WHEAT_SEEDS);

            case CARROTS:
                return player.getInventory().contains(Material.CARROT);

            case POTATOES:
                return player.getInventory().contains(Material.POTATO);

            case BEETROOTS:
                return player.getInventory().contains(Material.BEETROOT_SEEDS);

            case COCOA_BEANS:
                return player.getInventory().contains(Material.COCOA_BEANS);

        }
        return false;
    }
    private Material updateMaterial(Material material) {
        switch (material) {
            case WHEAT:
                return Material.WHEAT_SEEDS;

            case CARROTS:
                return Material.CARROT;

            case POTATOES:
                return Material.POTATO;

            case BEETROOTS:
                return Material.BEETROOT_SEEDS;

            case COCOA_BEANS:
                return Material.COCOA_BEANS;

        }
        return Material.VOID_AIR;
    }
}
