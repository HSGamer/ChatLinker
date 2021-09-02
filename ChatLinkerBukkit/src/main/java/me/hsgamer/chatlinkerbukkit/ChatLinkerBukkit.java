package me.hsgamer.chatlinkerbukkit;

import me.hsgamer.chatlinkerbukkit.channel.GlobalChat;
import me.hsgamer.chatlinkerbukkit.channel.GlobalPlayerList;
import me.hsgamer.chatlinkerbukkit.channel.PrivateChat;
import me.hsgamer.chatlinkerbukkit.command.GlobalChatCommand;
import me.hsgamer.chatlinkerbukkit.command.PrivateChatCommand;
import me.hsgamer.hscore.bukkit.baseplugin.BasePlugin;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;

public final class ChatLinkerBukkit extends BasePlugin {
    private final MainConfig mainConfig = new MainConfig(this);
    private final GlobalChat globalChat = new GlobalChat(this);
    private final PrivateChat privateChat = new PrivateChat(this);
    private final GlobalPlayerList globalPlayerList = new GlobalPlayerList(this);

    @Override
    public void load() {
        mainConfig.setup();
        MessageUtils.setPrefix(MainConfig.PREFIX::getValue);
    }

    @Override
    public void enable() {
        globalChat.register();
        privateChat.register();
        globalPlayerList.register();
        registerCommand(new GlobalChatCommand(this));
        registerCommand(new PrivateChatCommand(this));
        getServer().getScheduler().runTaskTimer(this, globalPlayerList, 20, 200);
    }

    @Override
    public void disable() {
        globalChat.unregister();
        privateChat.unregister();
    }

    @Override
    public void postDisable() {
        globalPlayerList.unregister();
    }

    public GlobalChat getGlobalChat() {
        return globalChat;
    }

    public PrivateChat getPrivateChat() {
        return privateChat;
    }

    public GlobalPlayerList getGlobalPlayerList() {
        return globalPlayerList;
    }
}
