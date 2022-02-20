package cn.zry551.zxchat.Commands;

import cn.zry551.zxchat.ClassSave;
import cn.zry551.zxchat.Str;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ZxChatCmd implements CommandExecutor {

    @Override
    public boolean onCommand(@org.jetbrains.annotations.NotNull CommandSender sender, @org.jetbrains.annotations.NotNull Command command, @org.jetbrains.annotations.NotNull String label, @org.jetbrains.annotations.NotNull String[] args)
    {
        if(ClassSave.DebugMode){
            String dif = "";
            for (int i = 0; i < args.length; i++) {
                dif = dif + args[i] + ",";
            }
            sender.sendMessage("[ZxChat] Debug Info : \n=Sender Name : " + sender.getName() + "\n=Command Name : "
                    + command.getName() + "\n=Label Info : " + label.toString() + "\n=Args Info : " + dif);
        }

        sender.sendMessage("gui!");

        return false;
    }


}
