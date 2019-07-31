package ainesh1998;

public class Cube {

    private char[] up;
    private char[] down;
    private char[] left;
    private char[] right;
    private char[] front;
    private char[] back;

    public Cube() {
        resetCube();
    }

    public void resetCube() {
        up = "WWWWWWWWW".toCharArray();
        down = "YYYYYYYYY".toCharArray();
        left = "OOOOOOOOO".toCharArray();
        right = "RRRRRRRRR".toCharArray();
        front = "GGGGGGGGG".toCharArray();
        back = "BBBBBBBBB".toCharArray();
    }

    public void upCw() {
        up = rotateFaceCw(up);

        // the other faces
        for (int i = 0; i < 3; i++) {
            char temp = front[i];
            front[i] = right[i];
            right[i] = back[i];
            back[i] = left[i];
            left[i] = temp;
        }
    }

    public void upCcw() {
        up = rotateFaceCcw(up);

        // the other faces
        for (int i = 0; i < 3; i++) {
            char temp = front[i];
            front[i] = left[i];
            left[i] = back[i];
            back[i] = right[i];
            right[i] = temp;
        }
    }

    public void downCw() {
        down = rotateFaceCw(down);

        // the other faces
        for (int i = 6; i < 9; i++) {
            char temp = front[i];
            front[i] = left[i];
            left[i] = back[i];
            back[i] = right[i];
            right[i] = temp;
        }
    }

    public void downCcw() {
        down = rotateFaceCcw(down);

        // the other faces
        for (int i = 6; i < 9; i++) {
            char temp = front[i];
            front[i] = right[i];
            right[i] = back[i];
            back[i] = left[i];
            left[i] = temp;
        }
    }

    public void leftCw() {
        left = rotateFaceCw(left);

        // the other faces
        for (int i = 0; i <= 6; i += 3) {
            char temp = up[i];
            up[i] = back[8 - i];
            back[8 - i] = down[i];
            down[i] = front[i];
            front[i] = temp;
        }
    }

    public void leftCcw() {
        left = rotateFaceCcw(left);

        // the other faces
        for (int i = 0; i <= 6; i += 3) {
            char temp = up[i];
            up[i] = front[i];
            front[i] = down[i];
            down[i] = back[8 - i];
            back[8 - i] = temp;
        }
    }

    public void rightCw() {
        right = rotateFaceCw(right);

        // the other faces
        for (int i = 2; i <= 8; i += 3) {
            char temp = up[i];
            up[i] = front[i];
            front[i] = down[i];
            down[i] = back[8 - i];
            back[8 - i] = temp;
        }
    }

    public void rightCcw() {
        right = rotateFaceCcw(right);

        // the other faces
        for (int i = 2; i <= 6; i += 3) {
            char temp = up[i];
            up[i] = back[8 - i];
            back[8 - i] = down[i];
            down[i] = front[i];
            front[i] = temp;
        }
    }

    public void frontCw() {
        front = rotateFaceCw(front);

        // the other faces
        int LRcount = 8;

        for (int i = 2; i >= 0; i--) {
            char temp = up[8 - i];
            up[8 - i] = left[LRcount];
            left[LRcount] = down[i];
            down[i] = right[8 - LRcount];
            right[8 - LRcount] = temp;

            LRcount -= 3;
        }
    }

    public void frontCcw() {
        front = rotateFaceCcw(front);

        // the other faces
        int LRcount = 8;

        for (int i = 2; i >= 0; i--) {
            char temp = up[8 - i];
            up[8 - i] = right[8 - LRcount];
            right[8 - LRcount] = down[i];
            down[i] = left[LRcount];
            left[LRcount] = temp;

            LRcount -= 3;
        }
    }

    public void backCw() {
        back = rotateFaceCw(back);

        // the other faces
        int LRcount = 6;

        for (int i = 8; i >= 6; i--) {
            char temp = up[8 - i];
            up[8 - i] = right[8 - LRcount];
            right[8 - LRcount] = down[i];
            down[i] = left[LRcount];
            left[LRcount] = temp;

            LRcount -= 3;
        }
    }

    public void backCcw() {
        back = rotateFaceCcw(back);

        // the other faces
        int LRcount = 6;

        for (int i = 8; i >= 6; i--) {
            char temp = up[8 - i];
            up[8 - i] = left[LRcount];
            left[LRcount] = down[i];
            down[i] = right[8 - LRcount];
            right[8 - LRcount] = temp;

            LRcount -= 3;
        }
    }

    public void xCw() {
//        char[] temp = front;
//        front = up;
//        up = back;
//        back = down;
//        down = temp;
//
//        // rotate faces to keep the labelling consistent
//        left = rotateFaceCcw(left);
//        right = rotateFaceCw(right);
//
    }

    public void xCcw() {

    }

    public void yCw() {

    }

    public void yCcw() {

    }

    public void zCw() {

    }

    public void zCcw() {

    }

    private char[] rotateFaceCw(char[] faceToTurn) {
        char[] result = new char[9];
        // 6, 3, 0, 7, 4, 1, 8, 5, 2
        for (int i = 0; i < 3; i++) {
            result[3 * i] = faceToTurn[6 + i];
            result[3 * i + 1] = faceToTurn[3 + i];
            result[3 * i + 2] = faceToTurn[i];
        }
        return result;
    }

    private char[] rotateFaceCcw(char[] faceToTurn) {
        char[] result = new char[9];
        // 2, 5, 8, 1, 4, 7, 0, 3, 6
        for (int i = 0; i < 3; i++) {
            result[3 * i] = faceToTurn[2 - i];
            result[3 * i + 1] = faceToTurn[5 - i];
            result[3 * i + 2] = faceToTurn[8 - i];
        }
        return result;
    }
}
