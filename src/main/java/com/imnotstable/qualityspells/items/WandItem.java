package com.imnotstable.qualityspells.items;

import com.imnotstable.qualityspells.spells.Spell;
import com.imnotstable.qualityspells.spells.SpellManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WandItem{
  
  public static ItemStack create(Player owner) {
    ItemStack wand = new ItemStack(Material.STICK);
    ItemMeta meta = wand.getItemMeta();
    meta.displayName(Component.text("Wand", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
    meta.lore(List.of(Component.empty(), Component.text("Wand of " + owner.getName(), NamedTextColor.GRAY)));
    PersistentDataContainer container = meta.getPersistentDataContainer();
    container.set(new NamespacedKey("qualitywands", "wand"), PersistentDataType.BOOLEAN, true);
    container.set(new NamespacedKey("qualitywands", "owner"), PersistentDataType.STRING, owner.getUniqueId().toString());
    container.set(new NamespacedKey("qualitywands", "spellone"), PersistentDataType.STRING, "qualityspells:fireball");
    container.set(new NamespacedKey("qualitywands", "spelltwo"), PersistentDataType.STRING, "qualityspells:thunderbolt");
    container.set(new NamespacedKey("qualitywands", "spellthree"), PersistentDataType.STRING, "qualityspells:meteor");
    container.set(new NamespacedKey("qualitywands", "spellfour"), PersistentDataType.STRING, "qualityspells:graveyard");
    wand.setItemMeta(meta);
    return wand;
  }
  
  public static boolean isWand(ItemStack item) {
    if (item == null || item.getItemMeta() == null)
      return false;
    return item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey("qualitywands", "wand"), PersistentDataType.BOOLEAN);
  }
  
  public static Set<Spell> getSpells(ItemStack wand) {
    if (!isWand(wand))
      return new HashSet<>();
    Set<Spell> spells = new HashSet<>();
    for (NamespacedKey key : getKeys(wand))
      SpellManager.getSpell(key).ifPresent(spells::add);
    return spells;
  }
  
  private static Set<NamespacedKey> getKeys(ItemStack wand) {
    Set<NamespacedKey> keys = new HashSet<>();
    PersistentDataContainer container = wand.getItemMeta().getPersistentDataContainer();
    for (String key : List.of("spellone", "spelltwo", "spellthree", "spellfour")) {
      String rawNamespacedKey = container.get(new NamespacedKey("qualitywands", key), PersistentDataType.STRING);
      if (rawNamespacedKey != null) {
        NamespacedKey namespacedKey = NamespacedKey.fromString(rawNamespacedKey);
        if (namespacedKey != null && !namespacedKey.getKey().equals("empty"))
          keys.add(namespacedKey);
      }
    }
    return keys;
  }
  
}
