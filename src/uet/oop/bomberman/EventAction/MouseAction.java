package uet.oop.bomberman.EventAction;

public class MouseAction  {
    public static double x;
    public static double y;
    public static boolean isClicked;

    public static void setMouseCoordinates(double xM, double yM) {
        x = xM;
        y = yM;
    }

    public static void setIsClicked(boolean isClick) {
        isClicked = isClick;
    }
}
