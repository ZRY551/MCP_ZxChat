package cn.zry551.zxchat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;


public class ChatEvent implements Listener {
    /*@EventHandler
    public void ChatRebuild(PlayerChatEvent e){
        e.setMessage("SB -> " + e.getMessage());
    }*/
    //%1$s %2$s
    @EventHandler
    public void ChatRebuild(AsyncPlayerChatEvent e){
        e.setFormat("\u00a7l[\u00a76%1$s\u00a7r\u00a7l]\u00a7r \u00a7a%2$s");
        String MSG = e.getMessage();
        String MSGR = MSG;
        try{
            MSG = MSG.replaceAll("%",Str.CF);
        }catch (Exception ex){
            MSG = MSGR;
        }
        e.setMessage(MSG);
        if(ClassSave.DebugMode){
            e.setMessage("\nMsg : " + e.getMessage() + "\nFormat : " + e.getFormat());
        }
    }
}
