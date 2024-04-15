package com.imnotstable.qualityspells.spells;

import com.imnotstable.qualityspells.CastPattern;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.function.Predicate;

@Getter
public class ThunderBolt implements Spell {
  
  NamespacedKey key = new NamespacedKey("qualityspells", "thunderbolt");
  String name = "Thunder Bolt";
  Component displayName = Component.text("Thunder Bolt", NamedTextColor.AQUA);
  CastPattern castPattern = CastPattern.create('L', 'L', 'R');
  Predicate<PlayerInteractEvent> action = event -> {
    Player player = event.getPlayer();
    Entity target = player.getTargetEntity(25);
    if (target == null)
      return false;
    player.getWorld().strikeLightning(target.getLocation());
    return true;
  };
  
}
