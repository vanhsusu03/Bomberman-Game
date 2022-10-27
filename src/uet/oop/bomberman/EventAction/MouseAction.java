package uet.oop.bomberman.EventAction;

public class MouseAction {
    public static double x;
    public static double y;
    public static boolean isClicked;

    /**
     * Set the mouse's coordinates.
     *
     * @param xM - x coordinates
     * @param yM - y coordinates
     */
    public static void setMouseCoordinates(double xM, double yM) {
        x = xM;
        y = yM;
    }

    /**
     * Set if mouse is clicked.
     *
     * @param isClick - is clicked or not
     */
    public static void setIsClicked(boolean isClick) {
        isClicked = isClick;
    }
}
