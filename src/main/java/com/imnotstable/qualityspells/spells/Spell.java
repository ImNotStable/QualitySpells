package com.imnotstable.qualityspells.spells;

import com.imnotstable.qualityspells.CastPattern;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.function.Predicate;

public interface Spell {
  
  NamespacedKey getKey();
  
  String getName();
  
  Component getDisplayName();
  
  CastPattern getCastPattern();
  
  Predicate<PlayerInteractEvent> getAction();
  
  default boolean cast(PlayerInteractEvent event) {
    return getAction().test(event);
  }
  
}
