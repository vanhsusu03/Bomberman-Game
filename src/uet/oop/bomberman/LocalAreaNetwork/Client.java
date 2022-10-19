package uet.oop.bomberman.LocalAreaNetwork;

import org.json.JSONObject;
import uet.oop.bomberman.KeyAction;
import uet.oop.bomberman.Pvp;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends Lan {
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Client() {
        try {
            socket = new Socket(InetAddress.getLocalHost().getHostName(), PORT);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            socket.setTcpNoDelay(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Client(String host) {
        try {
            socket = new Socket(InetAddress.getByName(host).getHostName(), PORT);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            socket.setTcpNoDelay(true);
            Pvp.playerType = Pvp.PlayerType.PLAYER1;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void sendData(JSONObject obj) {
        try {
            bufferedWriter.write(obj.toString());
            bufferedWriter.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendKeyData() {
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
        sendData(obj);
    }
}