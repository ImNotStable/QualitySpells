package com.imnotstable.qualityspells.spells;

import com.imnotstable.qualityspells.CastPattern;
import com.imnotstable.qualityspells.QualitySpells;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

@Getter
public class Graveyard implements Spell {
  
  NamespacedKey key = new NamespacedKey("qualityspells", "graveyard");
  String name = "Graveyard";
  Component displayName = Component.text("Graveyard", NamedTextColor.DARK_GRAY);
  CastPattern castPattern = CastPattern.create('R', 'R', 'R');
  Predicate<PlayerInteractEvent> action = event -> {
    Player player = event.getPlayer();
    Entity rawTarget = player.getTargetEntity(20);
    if (!(rawTarget instanceof LivingEntity target))
      return false;
    Set<Zombie> zombies = new HashSet<>();
    for (int i = 0; i < 4; i++) {
      target.getWorld().spawn(target.getLocation(), Zombie.class, entity -> {
        entity.setTarget(target);
        entity.setCustomNameVisible(true);
        entity.customName(Component.text(player.getName() + "'s Zombie", NamedTextColor.DARK_GRAY));
        entity.setHealth(7.5);
        entity.setAdult();
        entity.setLootTable(null);
        zombies.add(entity);
      });
    }
    Bukkit.getScheduler().runTaskLater(QualitySpells.getInstance(), () -> {
      for (Zombie zombie : zombies) {
        if (!zombie.isDead())
          zombie.remove();
      }
    },  200);
    return true;
  };
  
}
