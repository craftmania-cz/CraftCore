package cz.craftmania.craftcore.bungee.entity;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import cz.craftmania.craftcore.bungee.Main;
import cz.craftmania.craftcore.bungee.mojang.Skin;
import cz.craftmania.craftcore.bungee.utils.ReflectionFix;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.connection.InitialHandler;
import net.md_5.bungee.connection.LoginResult;

/**
 * A class helps you to manage players
 */
public class PlayerManager {

    private ProxiedPlayer player;

    /**
     * Creates a new PlayerManager instance
     *
     * @param player the player
     */
    public PlayerManager(ProxiedPlayer player) {
        this.player = player;
    }

    /**
     * Gets the ping number of that player
     * @return the player ping
     */
    public int getPing(){
        return this.player.getPing();
    }

    /**
     * Changes the skin of that player.
     * @param skin the skin
     */
    public void changeSkin(Skin skin){
        InitialHandler ih = (InitialHandler) this.player.getPendingConnection();
        LoginResult lr = ih.getLoginProfile();
        if(lr == null) {
            lr = new LoginResult(this.player.getUniqueId().toString().replace("-", ""), this.player.getName(), null);
        }
        lr.setProperties(new LoginResult.Property[]{
                new LoginResult.Property("textures", skin.getValue(), skin.getSignature())
        });
        ReflectionFix.setField("loginProfile", InitialHandler.class, ih, lr);
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("skin");
        out.writeUTF(this.player.getName());
        out.writeUTF(skin.getValue());
        out.writeUTF(skin.getSignature());
        this.player.getServer().sendData(Main.CRAFTCORE_CHANNEL, out.toByteArray());
    }
}
