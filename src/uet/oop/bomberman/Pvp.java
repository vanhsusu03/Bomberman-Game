package uet.oop.bomberman;

import uet.oop.bomberman.LocalAreaNetwork.Client;
import uet.oop.bomberman.LocalAreaNetwork.Server;

public class Pvp {
    public enum PlayerType {
        PLAYER0,
        PLAYER1
    }

    public static PlayerType playerType;
    public static Server server;
    public static Client client;
    public static boolean isRunning = false;

    public Pvp() {
        isRunning = true;
        playerType = PlayerType.PLAYER0;
        server = new Server();
    }

    public Pvp(String IP) {
        isRunning = true;
        this.playerType = PlayerType.PLAYER1;
        client = new Client(IP);
    }
}