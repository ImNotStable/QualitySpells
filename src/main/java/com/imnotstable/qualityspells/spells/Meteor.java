package com.imnotstable.qualityspells.spells;

import com.imnotstable.qualityspells.CastPattern;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.function.Predicate;

@Getter
public class Meteor implements Spell {
  
  NamespacedKey key = new NamespacedKey("qualityspells", "meteor");
  String name = "Meteor";
  Component displayName = Component.text("Meteor", NamedTextColor.GOLD);
  CastPattern castPattern = CastPattern.create('R', 'L', 'L');
  Predicate<PlayerInteractEvent> action = event -> {
    Player player = event.getPlayer();
    Entity target = player.getTargetEntity(25);
    if (target == null)
      return false;
    Location location = target.getLocation();
    location.setY(location.getY() + 50);
    location.setYaw(0);
    location.setPitch(0);
    player.getWorld().spawn(location, Fireball.class, fireball -> {
      fireball.setShooter(player);
      fireball.setVelocity(new Vector(0, -5, 0));
      fireball.setYield(7.5f);
    });
    return true;
  };

}
