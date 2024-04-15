package com.imnotstable.qualityspells.spells;

import com.imnotstable.qualityspells.CastPattern;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.function.Predicate;

@Getter
public class Empty implements Spell {
  
  NamespacedKey key = new NamespacedKey("qualityspells", "empty");
  String name = "Empty";
  Component displayName = Component.empty();
  CastPattern castPattern = null;
  Predicate<PlayerInteractEvent> action = event -> false;
  
}
