package uet.oop.bomberman.LocalAreaNetwork;

import org.json.JSONObject;
import uet.oop.bomberman.KeyAction;
import uet.oop.bomberman.Pvp;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

public abstract class Lan {
    protected static final int PORT = 1234;
    protected Socket socket;
    protected BufferedReader bufferedReader;
    protected BufferedWriter bufferedWriter;

    public void sendData(JSONObject obj) {
        try {
            bufferedWriter.write(obj.toString());
            bufferedWriter.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendKeyData() {
        JSONObject obj = new JSONObject();
        obj.put("role", Pvp.playerType);
        for (int i = 0; i < KeyAction.keys.length; i++) {
            switch (i) {
                case KeyEvent.VK_UP:
                    obj.put("act", "up");
                    obj.put("use", KeyAction.keys[i]);
                    break;
                case KeyEvent.VK_DOWN:
                    obj.put("act", "down");
                    obj.put("use", KeyAction.keys[i]);
                    break;
                case KeyEvent.VK_LEFT:
                    obj.put("act", "left");
                    obj.put("use", KeyAction.keys[i]);
                    break;
                case KeyEvent.VK_RIGHT:
                    obj.put("act", "right");
                    obj.put("use", KeyAction.keys[i]);
                    break;
                case KeyEvent.VK_SPACE:
                    obj.put("act", "putBomb");
                    obj.put("use", KeyAction.keys[i]);
                    break;
            }
        }
        System.out.println(obj.toString());
        sendData(obj);
    }

    public void getData() {
        try {
            String s = bufferedReader.readLine();
//            System.out.println(s);
            if (s == null || s == "{\"none\":\"none\"}") {
                return;
            }
            JSONObject obj = new JSONObject(s);
            if (obj.has("act")) {
                System.out.println(obj.get("act").toString());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
