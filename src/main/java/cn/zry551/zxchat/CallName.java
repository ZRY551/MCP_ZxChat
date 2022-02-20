package cn.zry551.zxchat;

import jdk.vm.ci.code.site.Call;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public class CallName {
    private FileConfiguration Cfg ;
    private String SavePath;
    private File SaveFile;
    public boolean Init(String SavePath,FileConfiguration Config){
        try {
            this.Cfg = Config;
            this.SavePath = SavePath;
            this.SaveFile = new File(SavePath);
            if(!SaveFile.exists()){
                SaveFile.createNewFile();
            }
            Cfg.load(SavePath);
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
            Cfg.save(SavePath);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean SetList(String PlayerName,@NotNull List<String> CallName){
        try {
            Cfg.set(PlayerName + ".all",CallName);
            Cfg.save(SavePath);
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
            Cfg.save(SavePath);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean SetNow(String PlayerName,String CallName){
        try {
            Cfg.set(PlayerName + ".now",CallName);
            Cfg.save(SavePath);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public String GetNow(String PlayerName) throws CallNameNotFound{
        String ARG = Cfg.getString(PlayerName + ".now");
        if(ARG == null){
            throw new CallNameNotFound();
        }
        return ARG;
    }

    public List<String> GetList(String PlayerName) throws CallNameNotFound{
        @NotNull List<String> ARG = Cfg.getStringList(PlayerName + ".all");
        if(ARG == null){
            throw new CallNameNotFound();
        }
        return ARG;
    }

    public class CallNameNotFound extends Exception {

    }
}
