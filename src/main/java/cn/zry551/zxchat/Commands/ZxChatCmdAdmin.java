package cn.zry551.zxchat.Commands;

import cn.zry551.zxchat.CallName;
import cn.zry551.zxchat.ClassSave;
import cn.zry551.zxchat.ZxChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZxChatCmdAdmin implements CommandExecutor {
    public static CallName CN = ZxChat.CN;
    public static String RegEx="[`${}<>/|{}]";
    public static Pattern P = Pattern.compile(RegEx);
    @Override
    public boolean onCommand(@org.jetbrains.annotations.NotNull CommandSender sender, @org.jetbrains.annotations.NotNull Command command, @org.jetbrains.annotations.NotNull String label, @org.jetbrains.annotations.NotNull String[] args)
    {
        if(ClassSave.DebugMode){
            String dif = "";
            for (int i = 0; i < args.length; i++) {
                dif = dif + args[i] + ",";
            }
            sender.sendMessage("\u00a7l[\u00a76ZxChat\u00a7r\u00a7l]\u00a7r\u00a7d Debug Info : \n=Sender Name : " + args[2] + "\n=Command Name : "
                    + command.getName() + "\n=Label Info : " + label.toString() + "\n=Args Info : " + dif);
        }

        //sender.sendMessage("Gui!");
        try{
            if(args[0].equals("add")){
                Matcher M = P.matcher(args[1]);
                String NSD = M.replaceAll("").trim();
                CN.Add(args[2],NSD);
                sender.sendMessage("\u00a7l[\u00a76ZxChat\u00a7r\u00a7l]\u00a7r\u00a7d Add Name '" + NSD + "' for player '" + args[2] + "' ! ");
                CN.Save();
                return true;
            }
            if(args[0].equals("remove")){
                Matcher M = P.matcher(args[1]);
                String NSD = M.replaceAll("").trim();
                CN.Add(args[2],NSD);
                sender.sendMessage("\u00a7l[\u00a76ZxChat\u00a7r\u00a7l]\u00a7r\u00a7d Remove Name '" + NSD + "' for player '" + args[2] + "' ! ");
                CN.Save();
                return true;
            }
            if(args[0].equals("list")){
                List<String> LS = CN.GetList(args[1]);
                String NR = "";
                for (String SR:LS) {
                    NR = "\n\u00a7d - \u00a7r" + SR + NR;
                }
                sender.sendMessage("\u00a7l[\u00a76ZxChat\u00a7r\u00a7l]\u00a7r\u00a7d Name List : " + NR);
                CN.Save();
                return true;
            }
            if(args[0].equals("set")){
                List<String> LS = CN.GetList(args[2]);
                /*if(!LS.equals(args[1])){
                    sender.sendMessage("\u00a7l[\u00a76ZxChat\u00a7r\u00a7l]\u00a7r\u00a7d Name Not Found ! ");
                }*/
                //CN.SetNow(args[2],args[1]);
                //CN.Add(args[2],args[1]);
                try {
                    CN.SetNow(args[2], LS.get(Integer.parseInt(args[1])));
                    sender.sendMessage("\u00a7l[\u00a76ZxChat\u00a7r\u00a7l]\u00a7r\u00a7d Set Name : " + args[2]);
                    CN.Save();
                    return true;
                }catch (Exception ex){
                    ex.printStackTrace();
                    sender.sendMessage("\u00a7l[\u00a76ZxChat\u00a7r\u00a7l]\u00a7r\u00a7d Error ! ");
                    CN.Save();
                    return false;
                }

            }
            if(args[0].equals("removenow")){

                List<String> LS = CN.GetList(args[1]);
                CN.SetNow(args[1],"#NULL");
                sender.sendMessage("\u00a7l[\u00a76ZxChat\u00a7r\u00a7l]\u00a7r\u00a7d Remove Name ! ");
                CN.Save();
                return true;
            }


        }catch (Exception ex){
            ex.printStackTrace();
            sender.sendMessage("\u00a7l[\u00a76ZxChat\u00a7r\u00a7l]\u00a7r\u00a7d Error ! ");
        }

        return false;
    }


}
