package uet.oop.bomberman.LocalAreaNetwork;

import uet.oop.bomberman.Pvp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Lan {
    public ServerSocket serverSocket;

    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
            socket = serverSocket.accept();
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Pvp.playerType = Pvp.PlayerType.PLAYER0;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
