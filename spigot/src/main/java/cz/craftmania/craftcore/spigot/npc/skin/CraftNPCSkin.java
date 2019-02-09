package cz.craftmania.craftcore.spigot.npc.skin;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CraftNPCSkin {

    private SkinType type;
    private String identifier;
    private Plugin plugin;

    public CraftNPCSkin(Plugin plugin, SkinType type, String identifier) {
        this.type = type;
        this.identifier = identifier;
        this.plugin = plugin;
    }

    public CraftNPCSkin(Plugin plugin, SkinType type) {
        this.type = type;
        this.plugin = plugin;
    }

    public SkinType getSkinType() {
        return type;
    }

    public void getSkinDataAsync(SkinDataReply skinreply) {
        if (type == SkinType.IDENTIFIER) {
            SkinManager.getSkinFromMojangAsync(plugin, this.identifier, new SkinDataReply() {
                @Override
                public void done(SkinData skinData) {
                    if (skinData != null) {
                        skinreply.done(skinData);
                    } else {
                        SkinManager.getSkinFromMCAPIAsync(plugin, identifier, new SkinDataReply() {
                            @Override
                            public void done(SkinData skinData) {
                                if (skinData != null) {
                                    skinreply.done(skinData);
                                } else skinreply.done(null);
                            }
                        });
                    }
                }
            });
        } else if (type == SkinType.MINESKINID) {
            SkinManager.getSkinFromMineskinAsync(plugin, Integer.valueOf(this.identifier), new SkinDataReply() {
                @Override
                public void done(SkinData skinData) {
                    if (skinData != null) {
                        skinreply.done(skinData);
                    } else skinreply.done(null);
                }
            });
        }
    }

    public void getSkinDataAsync(SkinDataReply skinreply, Player p) {
        if (type == SkinType.PLAYER) {
            SkinManager.getSkinFromMojangAsync(plugin, p.getUniqueId().toString(), new SkinDataReply() {
                @Override
                public void done(SkinData skinData) {
                    if (skinData != null) {
                        skinreply.done(skinData);
                    } else {
                        SkinManager.getSkinFromMCAPIAsync(plugin, p.getName(), new SkinDataReply() {
                            @Override
                            public void done(SkinData skinData) {
                                if (skinData != null) {
                                    skinreply.done(skinData);
                                } else skinreply.done(null);
                            }
                        });
                    }
                }
            });
        }
    }
}
