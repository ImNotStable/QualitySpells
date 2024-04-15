package com.imnotstable.qualityspells.spells;

import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SpellManager {
  
  private static final Map<NamespacedKey, Spell> spells = new HashMap<>();
 
  static {
    register(new Empty());
    register(new FireBall());
    register(new ThunderBolt());
    register(new Meteor());
    register(new Graveyard());
  }
  
  public static void register(Spell spell) {
    spells.put(spell.getKey(), spell);
  }
  
  public static HashSet<Spell> getSpells() {
    return new HashSet<>(spells.values());
  }
  
  public static Optional<Spell> getSpell(NamespacedKey key) {
    return Optional.ofNullable(spells.get(key));
  }
  
  public static List<Spell> getSpell(String name) {
    return spells.values().stream().filter(spell -> spell.getName().equals(name)).toList();
  }
  
}
