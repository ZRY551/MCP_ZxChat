package cn.zry551.zxchat;

import cn.zry551.zxchat.Commands.ZxChatCmd;
import cn.zry551.zxchat.Commands.ZxChatCmdAdmin;
import cn.zry551.zxchat.Commands.ZxChatSmall;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.Objects;

public final class ZxChat extends JavaPlugin {
    public static JavaPlugin instance;
    public FileConfiguration config = getConfig();
    public File DataDir = this.getDataFolder();
    public static CallName CN = new CallName();
    @Override
    public void onLoad() {
        //saveDefaultConfig();;
        getLogger().info("Data Dir : " + DataDir.getPath());
        ClassLoader CL = this.getClassLoader();
        try{
            //String Path = DataDir.getPath();
            if(!DataDir.exists()){
                DataDir.mkdir();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            config.load(DataDir.getPath() + "/Config.yml");
        } catch (Exception e) {
            //config.save();
            File F = new File(DataDir.getPath() + "/Config.yml");
            try {
                BufferedWriter W = new BufferedWriter(new FileWriter(F,true));
                //File DC = new File(CL.getResource("/res/default.yml").toURI());
                //BufferedReader DCR = new BufferedReader(new FileReader(DC));
                //W.write(DCR.read());
                BufferedReader RIN = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("res/default.yml")));
                StringBuilder Builder = new StringBuilder();
                String Lines = "";
                while ((Lines = RIN.readLine()) != null){
                    Builder.append(Lines + "\n");
                }
                W.write(Builder.toString());
                W.close();
                //getLogger().info(Builder.toString());
                config.load(DataDir.getPath() + "/Config.yml");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try{
            Data.Format = config.getString("Format");
            Data.CallNameFormat = config.getString("CallNameFormat");
            Data.UseColor = config.getBoolean("UseColor");
            Data.UseCallName = config.getBoolean("UseCallName");

            Data.UseDefaultCallName = config.getBoolean("UseDefaultCallName");
            Data.DefaultCallName = config.getString("DefaultCallName");
        }catch (Exception e){
            e.printStackTrace();
        }

        // 如果配置文件不存在，Bukkit 会保存默认的配置
    }

    @Override
    public void onEnable() {
        instance = this;
        ClassSave.DebugMode = false;
        // Plugin startup logic
        CN.Init(DataDir.getPath() + "/CallNameData.yml",this.getConfig());
        Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);
        Objects.requireNonNull(Bukkit.getPluginCommand("zxchat")).setExecutor(new ZxChatCmd());
        Objects.requireNonNull(Bukkit.getPluginCommand("zxchatadmin")).setExecutor(new ZxChatCmdAdmin());
        Objects.requireNonNull(Bukkit.getPluginCommand("zxchatsmall")).setExecutor(new ZxChatSmall());
        // 注册事件处理器，也要实例化，requireNonNull 是不必要的，但是万一插件损坏了或者 Bukkit 出错了，我们还能知道是这里出问题

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
