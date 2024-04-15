package com.imnotstable.qualityspells;

import com.imnotstable.qualityspells.items.WandItem;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.jorel.commandapi.CommandAPICommand;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class QualitySpells extends JavaPlugin {
    
    @Getter
    private static QualitySpells instance;
    
    public QualitySpells() {
        instance = this;
    }
    
    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this));
    }
    
    @Override
    public void onEnable() {
        CommandAPI.onEnable();
        new CastDetector(this);
        new CommandAPICommand("wand")
            .executesPlayer((player, args) -> {
                
                player.getInventory().addItem(WandItem.create(player));
            })
            .register();
    }

    @Override
    public void onDisable() {
        CommandAPI.onDisable();
    }
}
