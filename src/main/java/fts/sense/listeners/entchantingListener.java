package fts.sense.listeners;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class entchantingListener implements Listener {
    @EventHandler
    private void onAnvil(InventoryClickEvent event) {
        if (event.getInventory().getType() == InventoryType.ANVIL) {
            if (event.getCurrentItem() != null &&
                event.getCurrentItem().hasItemMeta() &&
                    (event.getCurrentItem().getItemMeta().hasEnchant(Enchantment.MENDING) ||
                     event.getCurrentItem().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) &&
                event.getCurrentItem().getItemMeta().hasCustomModelData() &&
                event.getCurrentItem().getItemMeta().getCustomModelData() == 999) {
                    event.setCancelled(true);
            }
        }
    }
}
