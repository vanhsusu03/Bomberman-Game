package uet.oop.bomberman.LocalAreaNetwork;

import uet.oop.bomberman.Pvp;

import java.net.ServerSocket;

public class Server extends Lan {
    private ServerSocket serverSocket;

    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
            Pvp.client = new Client();
            socket = serverSocket.accept();
            Pvp.playerType = Pvp.PlayerType.PLAYER0;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
