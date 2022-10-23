package pl.yspar.core.manager;

import java.util.HashMap;
import java.util.Map;

public class TpaManager
{
    private static Map<String, Long> time;
    private static Map<String, String> tpa;
    private static TpaManager inst;
    
    public void send(final String sender, final String reciever) {
        TpaManager.tpa.put(reciever.toLowerCase(), sender.toLowerCase());
        TpaManager.time.put(reciever.toLowerCase(), System.currentTimeMillis() + 60000L);
    }
    
    public void removeRequest(final String reciever) {
        TpaManager.tpa.remove(reciever.toLowerCase());
        TpaManager.time.remove(reciever.toLowerCase());
    }
    
    public String getRequest(final String reciever) {
        if (!TpaManager.tpa.containsKey(reciever.toLowerCase())) {
            return null;
        }
        if (TpaManager.time.get(reciever.toLowerCase()) < System.currentTimeMillis()) {
            TpaManager.tpa.remove(reciever.toLowerCase());
            TpaManager.time.remove(reciever.toLowerCase());
            return null;
        }
        return TpaManager.tpa.get(reciever.toLowerCase());
    }
    
    public boolean isSend(final String sender, final String reciever) {
        if (!TpaManager.tpa.containsKey(reciever.toLowerCase())) {
            return false;
        }
        if (TpaManager.time.get(reciever.toLowerCase()) < System.currentTimeMillis()) {
            TpaManager.tpa.remove(reciever.toLowerCase());
            TpaManager.time.remove(reciever.toLowerCase());
            return false;
        }
        return TpaManager.tpa.get(reciever.toLowerCase()).equalsIgnoreCase(sender);
    }
    
    public static TpaManager getInst() {
        if (TpaManager.inst == null) {
            new TpaManager();
        }
        return TpaManager.inst;
    }
    
    public TpaManager() {
        TpaManager.inst = this;
    }
    
    static {
        TpaManager.tpa = new HashMap<String, String>();
        TpaManager.time = new HashMap<String, Long>();
    }
}
