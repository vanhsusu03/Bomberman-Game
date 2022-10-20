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
    public Client(String host) {
        try {
            socket = new Socket(host, PORT);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            socket.setTcpNoDelay(true);
            Pvp.playerType = Pvp.PlayerType.PLAYER1;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}