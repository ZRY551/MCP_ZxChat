package cn.zry551.zxchat.Commands;

import cn.zry551.zxchat.CallName;
import cn.zry551.zxchat.ClassSave;
import cn.zry551.zxchat.Str;
import cn.zry551.zxchat.ZxChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ZxChatCmd implements CommandExecutor {
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
            sender.sendMessage("\u00a7l[\u00a73ZxChat\u00a7r\u00a7l]\u00a7r\u00a7d Debug Info : \n=Sender Name : " + sender.getName() + "\n=Command Name : "
                    + command.getName() + "\n=Label Info : " + label.toString() + "\n=Args Info : " + dif);
        }

        //sender.sendMessage("Gui!");
        try{
            if(args[0].equals("add")){
                Matcher M = P.matcher(args[1]);
                String NSD = M.replaceAll("").trim();
                CN.Add(sender.getName(),NSD);
                sender.sendMessage("\u00a7l[\u00a73ZxChat\u00a7r\u00a7l]\u00a7r\u00a7d Add Name '\u00a7r" + NSD + "\u00a7r\u00a7d' for player '" + sender.getName() + "' ! ");
                CN.Save();
                return true;
            }
            if(args[0].equals("remove")){
                Matcher M = P.matcher(args[1]);
                String NSD = M.replaceAll("").trim();
                CN.Add(sender.getName(),NSD);
                sender.sendMessage("\u00a7l[\u00a73ZxChat\u00a7r\u00a7l]\u00a7r\u00a7d Remove Name '\u00a7r" + NSD + "\u00a7r' for player '" + sender.getName() + "' ! ");
                CN.Save();
                return true;
            }
            if(args[0].equals("list")){
                List<String> LS = CN.GetList(sender.getName());
                String NR = "";
                for (String SR:LS) {
                    NR = "\n\u00a7d - \u00a7r" + SR + NR;
                }
                sender.sendMessage("\u00a7l[\u00a73ZxChat\u00a7r\u00a7l]\u00a7r\u00a7d Name List for Player '" + sender.getName() + "': \u00a7r" + NR);
                CN.Save();
                return true;
            }
            if(args[0].equals("set")){
                List<String> LS = CN.GetList(sender.getName());
                /*if(!LS.equals(args[1])){
                    sender.sendMessage("\u00a7l[\u00a76ZxChat\u00a7r\u00a7l]\u00a7r\u00a7d Name Not Found ! ");
                }*/
                //CN.SetNow(sender.getName(),args[1]);
                //CN.Add(sender.getName(),args[1]);
                try {
                    CN.SetNow(sender.getName(), LS.get(Integer.parseInt(args[1])));
                    sender.sendMessage("\u00a7l[\u00a73ZxChat\u00a7r\u00a7l]\u00a7r\u00a7d Set Name for Player '" + sender.getName() + "' : \u00a7r" + LS.get(Integer.parseInt(args[1])));
                    CN.Save();
                    return true;
                }catch (Exception ex){
                    ex.printStackTrace();
                    sender.sendMessage("\u00a7l[\u00a73ZxChat\u00a7r\u00a7l]\u00a7r\u00a7d Error ! ");
                    CN.Save();
                    return false;
                }

            }
            if(args[0].equals("removenow")){

                List<String> LS = CN.GetList(sender.getName());
                CN.SetNow(sender.getName(),"#NULL");
                sender.sendMessage("\u00a7l[\u00a73ZxChat\u00a7r\u00a7l]\u00a7r\u00a7d Remove Name for Player : '" + sender.getName() + "' ! ");
                CN.Save();
                return true;
            }


        }catch (Exception ex){
            ex.printStackTrace();
            sender.sendMessage("\u00a7l[\u00a73ZxChat\u00a7r\u00a7l]\u00a7r\u00a7d Error ! ");
        }

        return false;
    }


}
