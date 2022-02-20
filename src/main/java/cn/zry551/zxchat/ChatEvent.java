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
    /*@EventHandler
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
    }*/
    private static CallName CN = ZxChat.CN;
    @EventHandler
    public void ChatRebuild(AsyncPlayerChatEvent e){
        //e.setFormat("\u00a7l[\u00a76%1$s\u00a7r\u00a7l]\u00a7r \u00a7a%2$s");
        if(!Data.UseCallName){
            e.setFormat(Data.Format);//.replaceAll("#","%")
        }else{
            /*try {*/
                String NP = CN.GetNow(e.getPlayer().getName());
                if(NP == "" | NP == null | NP == "#NULL"){
                    e.setFormat(Data.Format);//.replaceAll("#","%")
                }else{
                    e.setFormat(Data.CallNameFormat.replaceAll("#CN",NP));//.replaceAll("#","%")
                }
            /*} catch (CallName.CallNameNotFound ex) {
                //ex.printStackTrace();
                e.setFormat(Data.Format);//.replaceAll("#","%")
            }*/
            //e.setFormat(Data.CallNameFormat);//.replaceAll("#","%")
        }
        String MSG = e.getMessage();
        String MSGR = MSG;
        try{
            if(Data.UseColor) {
                MSG = MSG.replaceAll("%", "\u00a7");
            }else{
                MSG = MSG.replaceAll("%", "%");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            MSG = MSGR;
        }
        e.setMessage(MSG);
        if(ClassSave.DebugMode){
            e.setMessage("\n|- Debug Info -|\n=Sender : " + e.getPlayer().getName() + "\n=Msg : " + e.getMessage() + "\n=Format : " + e.getFormat());
        }
    }
}
