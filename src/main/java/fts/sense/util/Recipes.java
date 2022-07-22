package fts.sense.util;

import fts.sense.Sense;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Recipes {
    public void registerRecipies() {
        ItemStack sense = new ItemStack(Material.IRON_HOE);
        ItemMeta senseMeta = sense.getItemMeta();
        senseMeta.setDisplayName("§aSense");
        senseMeta.setCustomModelData(999);
        sense.setItemMeta(senseMeta);
        NamespacedKey senseNKey = new NamespacedKey(Sense.getPlugin(), "senseNKey");
        ShapedRecipe senseRecipe = new ShapedRecipe(senseNKey, sense);
        senseRecipe.shape(new String[] { "AEE", "ESA", "ASA" });
        senseRecipe.setIngredient('E', Material.EMERALD);
        senseRecipe.setIngredient('S', Material.STICK);
        senseRecipe.setIngredient('A', Material.AIR);
        Bukkit.addRecipe(senseRecipe);

    }
}
