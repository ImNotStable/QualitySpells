package com.imnotstable.qualityspells.spells;

import com.imnotstable.qualityspells.CastPattern;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.function.Predicate;

@Getter
public class FireBall implements Spell {
  
  NamespacedKey key = new NamespacedKey("qualityspells", "fireball");
  String name = "Fire Ball";
  Component displayName = Component.text("Fire Ball", NamedTextColor.RED);
  CastPattern castPattern = CastPattern.create('R', 'L', 'R');
  Predicate<PlayerInteractEvent> action = event -> {
    Player player = event.getPlayer();
    Location location = player.getEyeLocation();
    player.getWorld().spawn(location, Fireball.class, fireball -> {
      fireball.setShooter(player);
      fireball.setVelocity(location.getDirection().multiply(2));
    });
    return true;
  };
  
}
