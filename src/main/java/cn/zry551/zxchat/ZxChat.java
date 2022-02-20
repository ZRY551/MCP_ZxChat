package cn.zry551.zxchat;

import cn.zry551.zxchat.Commands.ZxChatCmd;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ZxChat extends JavaPlugin {
    public static JavaPlugin instance;

    @Override
    public void onLoad() {
        saveDefaultConfig();
        // 如果配置文件不存在，Bukkit 会保存默认的配置
    }

    @Override
    public void onEnable() {
        instance = this;
        ClassSave.DebugMode = false;
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);
        Objects.requireNonNull(Bukkit.getPluginCommand("zxchat")).setExecutor(new ZxChatCmd());
        // 注册事件处理器，也要实例化，requireNonNull 是不必要的，但是万一插件损坏了或者 Bukkit 出错了，我们还能知道是这里出问题

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
