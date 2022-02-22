package cn.zry551.zxchat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.Objects;


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
            String FN = Data.Format;
            FN = FN.replaceAll("&&","#FG");
            FN = FN.replaceAll("&",Str.CF);
            FN = FN.replaceAll("#FG","&");
            e.setFormat(FN);//.replaceAll("#","%")
        }else{
            /*try {*/
                String NP = CN.GetNow(e.getPlayer().getName());
                if(Objects.equals(NP, "") | NP == null | Objects.equals(NP, "#NULL")){
                    String FN = Data.Format;
                    FN = FN.replaceAll("&&","#FG");
                    FN = FN.replaceAll("&",Str.CF);
                    FN = FN.replaceAll("#FG","&");
                    e.setFormat(FN);//.replaceAll("#","%")
                }else{
                    String FN = Data.CallNameFormat;
                    FN = FN.replaceAll("&&","#FG");
                    FN = FN.replaceAll("&",Str.CF);
                    FN = FN.replaceAll("#FG","&");
                    FN = FN.replaceAll("#CN",NP);
                    e.setFormat(FN);//.replaceAll("#","%")
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
                MSG = MSG.replaceAll("%%", "#BF");
                MSG = MSG.replaceAll("%", "\u00a7");
                MSG = MSG.replaceAll("#BF", "%");
            }else{
                MSG = MSG.replaceAll("%%", "#BF");
                MSG = MSG.replaceAll("%", "%");
                MSG = MSG.replaceAll("#BF", "%");
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
