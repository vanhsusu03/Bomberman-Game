package uet.oop.bomberman.map;

import java.io.FileNotFoundException;

public abstract class LoadLevel {
    protected int level;

    public LoadLevel(int level) throws FileNotFoundException {

        inputLevel(level);
    }

    public abstract void inputLevel(int level) throws FileNotFoundException;

    public abstract void createMap();
}