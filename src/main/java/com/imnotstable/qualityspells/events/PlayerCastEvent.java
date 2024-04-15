package com.imnotstable.qualityspells.events;

import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerCastEvent extends PlayerInteractEvent {
  
  public PlayerCastEvent(PlayerInteractEvent event) {
    super(event.getPlayer(), event.getAction(), event.getItem(), event.getClickedBlock(), event.getBlockFace(), event.getHand(), event.getClickedPosition());
  }
  
}
