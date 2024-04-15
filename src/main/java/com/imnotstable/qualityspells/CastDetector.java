package com.imnotstable.qualityspells;

import com.imnotstable.qualityspells.items.WandItem;
import com.imnotstable.qualityspells.spells.Spell;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CastDetector implements Listener {
  
  private final Map<UUID, CastPattern> playerCastPatterns;
  
  public CastDetector(QualitySpells plugin) {
    this.playerCastPatterns = new HashMap<>();
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }
  
  @EventHandler
  public void on(PlayerInteractEvent event) {
    if (event.getItem() == null || !WandItem.isWand(event.getItem()))
      return;
    
    char patternKey;
    if (event.getAction().isLeftClick()) patternKey = 'L';
    else if (event.getAction().isRightClick()) patternKey = 'R';
    else return;
    
    Player player = event.getPlayer();
    
    CastPattern pattern;
    if (!playerCastPatterns.containsKey(player.getUniqueId())) {
      pattern = CastPattern.create(patternKey);
      playerCastPatterns.put(player.getUniqueId(), pattern);
    } else
      pattern = playerCastPatterns.get(player.getUniqueId()).then(patternKey);
    
    player.sendActionBar(buildActionBar(pattern));
    
    if (!pattern.isComplete()) {
      return; //Add something that removes the pattern if the player doesn't complete it in time
    }
    
    for (Spell spell : WandItem.getSpells(event.getItem()))
      if (pattern.matches(spell.getCastPattern())) {
        if (spell.cast(event))
          player.sendActionBar(
            Component.text().append(Component.text("You casted ", NamedTextColor.GREEN),
              spell.getDisplayName(),
              Component.text("!", NamedTextColor.GREEN))
              .build()
          );
        else
          player.sendActionBar(Component.text("Your spell fizzled!", NamedTextColor.RED));
        playerCastPatterns.remove(player.getUniqueId());
      }
    
    if (playerCastPatterns.containsKey(player.getUniqueId())) {
      player.sendActionBar(Component.text("Your spell fizzled!", NamedTextColor.RED));
      playerCastPatterns.remove(player.getUniqueId());
    }
    
  }
  
  private Component buildActionBar(CastPattern pattern) {
    char[] patternChars = pattern.getPattern();
    Component plusSign = Component.text(" + ", NamedTextColor.AQUA);
    return Component.text().append(
      getDisplayableKey(patternChars[0]), plusSign,
      getDisplayableKey(patternChars[1]), plusSign,
      getDisplayableKey(patternChars[2])
    ).build();
  }
  
  private Component getDisplayableKey(char key) {
    if (key == 'R' || key == 'r')
      return Component.text(" R ", NamedTextColor.DARK_AQUA);
    else if (key == 'L' || key == 'l')
      return Component.text(" L ", NamedTextColor.DARK_AQUA);
    return Component.text(" _ ", NamedTextColor.DARK_AQUA);
  }
  
}
