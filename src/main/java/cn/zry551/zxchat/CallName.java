package cn.zry551.zxchat;


import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CallName {
    private FileConfiguration Cfg ;
    private String SavePath;
    private File SaveFile;

    public boolean Init(String SavePath, @NotNull FileConfiguration config){
        try {
            this.Cfg = ZxChat.instance.getConfig();
            this.SavePath = SavePath;
            this.SaveFile = new File(SavePath);
            if(!SaveFile.exists()){
                SaveFile.createNewFile();
            }
            //Cfg.load(SavePath);
            Cfg.load(SaveFile);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    public boolean Add(String PlayerName,String CallName){
        try {
            @NotNull List<String> AR = Cfg.getStringList(PlayerName + ".all");
            AR.add(CallName);
            Cfg.set(PlayerName + ".all",AR);
            Cfg.save(SaveFile);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean SetList(String PlayerName,@NotNull List<String> CallName){
        try {
            Cfg.set(PlayerName + ".all",CallName);
            Cfg.save(SaveFile);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean Remove(String PlayerName,String CallName){
        try {
            @NotNull List<String> AR = Cfg.getStringList(PlayerName + ".all");
            AR.remove(CallName);
            Cfg.set(PlayerName + ".all",AR);
            Cfg.save(SaveFile);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean SetNow(String PlayerName,String CallName){
        try {
            Cfg.set(PlayerName + ".now",CallName);
            Cfg.save(SaveFile);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public String GetNow(String PlayerName){
        String ARG = Cfg.getString(PlayerName + ".now");
        if(ARG == null | Objects.equals(ARG, "#NULL") | Objects.equals(ARG, "")){
            if(Data.UseDefaultCallName){
                return Data.DefaultCallName.replaceAll("%",Str.CF);
            }else{
                return "#NULL";
            }
        }
        return ARG.replaceAll("%",Str.CF);
    }

    public List<String> GetList(String PlayerName) throws CallNameNotFound{
        @NotNull List<String> ARG = Cfg.getStringList(PlayerName + ".all");
        @NotNull List<String> ARG2 = new ArrayList<>();
        if(ARG == null){
            throw new CallNameNotFound();
        }
        for (String L2:ARG) {
            //ARG.remove(L2);
            ARG2.add(L2.replaceAll("%",Str.CF));
        }
        return ARG2;
    }

    public boolean RemoveNow(String PlayerName){
        try {
            Cfg.set(PlayerName + ".now","#NULL");
            Cfg.save(SaveFile);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public void Save(){
        try {
            Cfg.save(SaveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class CallNameNotFound extends Exception {

    }
}
