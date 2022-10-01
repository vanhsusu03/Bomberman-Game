package uet.oop.bomberman.map;

import java.io.FileNotFoundException;

public abstract class LoadLevel {
    protected int width;
    protected int height;
    protected int level;

    public LoadLevel(int level) throws FileNotFoundException{
        inputLevel(level);
    }

    public abstract void inputLevel(int level) throws FileNotFoundException;
    public abstract void createMap();

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLevel() {
        return level;
    }
}
