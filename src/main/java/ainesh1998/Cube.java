package ainesh1998;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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

    void resetCube() {
        up = "WWWWWWWWW".toCharArray();
        down = "YYYYYYYYY".toCharArray();
        left = "OOOOOOOOO".toCharArray();
        right = "RRRRRRRRR".toCharArray();
        front = "GGGGGGGGG".toCharArray();
        back = "BBBBBBBBB".toCharArray();
    }

    ArrayList<char[]> getState() {
        return new ArrayList<>(Arrays.asList(up, left, front, right, down, back));
    }

    void upCw() {
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

    void upCcw() {
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

    void downCw() {
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

    void downCcw() {
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

    void leftCw() {
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

    void leftCcw() {
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

    void rightCw() {
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

    void rightCcw() {
        right = rotateFaceCcw(right);

        // the other faces
        for (int i = 2; i <= 8; i += 3) {
            char temp = up[i];
            up[i] = back[8 - i];
            back[8 - i] = down[i];
            down[i] = front[i];
            front[i] = temp;
        }
    }

    void frontCw() {
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

    void frontCcw() {
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

    void backCw() {
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

    void backCcw() {
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

    void xCw() {
        char[] temp = front;
        front = down;
        down = rotateFace180(back);
        back = rotateFace180(up);
        up = temp;

        // rotate faces to keep the labelling consistent
        left = rotateFaceCcw(left);
        right = rotateFaceCw(right);
    }

    void xCcw() {
        char[] temp = front;
        front = up;
        up = rotateFace180(back);
        back = rotateFace180(down);
        down = temp;

        // rotate faces to keep the labelling consistent
        left = rotateFaceCw(left);
        right = rotateFaceCcw(right);
    }

    void yCw() {
        char[] temp = front;
        front = right;
        right = back;
        back = left;
        left = temp;

        // rotate faces to keep the labelling consistent
        up = rotateFaceCw(up);
        down = rotateFaceCcw(down);
    }

    void yCcw() {
        char[] temp = front;
        front = left;
        left = back;
        back = right;
        right = temp;

        // rotate faces to keep the labelling consistent
        up = rotateFaceCcw(up);
        down = rotateFaceCw(down);
    }

    void zCw() {
        char[] temp = up;
        up = rotateFaceCw(left);
        left = rotateFaceCw(down);
        down = rotateFaceCw(right);
        right = rotateFaceCw(temp);

        // rotate faces to keep the labelling consistent
        front = rotateFaceCw(front);
        back = rotateFaceCcw(back);
    }

    void zCcw() {
        char[] temp = up;
        up = rotateFaceCcw(right);
        right = rotateFaceCcw(down);
        down = rotateFaceCcw(left);
        left = rotateFaceCcw(temp);

        // rotate faces to keep the labelling consistent
        front = rotateFaceCcw(front);
        back = rotateFaceCw(back);
    }

    void randomMovesScramble() {
        /*
        Just do 25 random moves and hope it's not too easy
         */
        Runnable[] moves = {this::upCw, this::upCcw, this::up180, this::downCw, this::downCcw, this::down180,
                            this::leftCw, this::leftCcw, this::left180, this::rightCw, this::rightCcw, this::right180,
                            this::frontCw, this::frontCcw, this::front180, this::backCw, this::backCcw, this::back180};

        Random rand = new Random();
        int count = 0;
        int prev = -1;

        while (count < 25) {
            int chosenOne = rand.nextInt(18);

            if (chosenOne != prev) {
                moves[chosenOne].run();
                prev = chosenOne;
                count++;
            }

        }


    }

    void randomStateScramble() {
        /*
        Perform some swaps, twists and flips on the cube, set its state, use Chen Shuang's solver to obtain an optimal
        solution and invert it to obtain the scramble.
        */

        String[] edges = {"WB", "WR", "WG", "WO", "BO", "BR", "GR", "GO", "YB", "YR", "YG", "YO"};
        String[] corners = {"WOB", "WBR", "WRG", "WGO", "YBO", "YRB", "YGR", "YOG"};
        Random rand = new Random();

        int edgeSwaps = rand.nextInt(5)*2 + 8;
        for (int i = 0; i < edgeSwaps; i++) {
            int edge1 = rand.nextInt(12);
            int edge2 = rand.nextInt(12);
            String temp = edges[edge1];
            edges[edge1] = edges[edge2];
            edges[edge2] = temp;
        }

        int cornerSwaps = rand.nextInt(5)*2 + 4;
        for (int i = 0; i < cornerSwaps; i++) {
            int corner1 = rand.nextInt(8);
            int corner2 = rand.nextInt(8);
            String temp = corners[corner1];
            corners[corner1] = corners[corner2];
            corners[corner2] = temp;
        }

        int flips = rand.nextInt(7)*2;
        for (int i = 0; i < flips; i++) {
            int flip1 = rand.nextInt(12);
            int flip2 = rand.nextInt(12);
            edges[flip1] = flipEdge(edges[flip1]);
            edges[flip2] = flipEdge(edges[flip2]);
        }

        setScrambledState(edges, corners);
    }

    boolean isSolved() {
        boolean result = true;
        return result;
    }

    /*
         Private methods
     */

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

    private char[] rotateFace180(char[] faceToTurn) {
        // 8, 7, 6, 5, 4, 3, 2, 1, 0
        for (int i = 0; i < 4; i++) {
            char temp = faceToTurn[i];
            faceToTurn[i] = faceToTurn[8 - i];
            faceToTurn[8 - i] = temp;
        }
        return faceToTurn;
    }

    private String flipEdge(String edge) {
        return edge.substring(1, 2) + edge.substring(0, 1);
    }

    private void setScrambledState(String[] edges, String[] corners) {

        int[] topAndBottomIndices = {0, 1, 3, 2, 7, 6, 4, 5, 0, 3, 1, 2, 10, 11, 9, 8};
        ArrayList<char[]> faces = new ArrayList<>(Arrays.asList(left, back, right, front));

        for (int i = 0; i < 4; i++) {
            // U and D faces

            int cornerIndex = i < 2 ? 2*i : 2*i + 2;
            int edgeIndex = 2*i + 1;

            up[cornerIndex] = corners[topAndBottomIndices[i]].charAt(0);
            down[cornerIndex] = corners[topAndBottomIndices[i + 4]].charAt(0);
            up[edgeIndex] = edges[topAndBottomIndices[i + 8]].charAt(0);
            down[edgeIndex] = edges[topAndBottomIndices[i + 12]].charAt(0);

            // L, B, R, F faces

            // top layer corners
            faces.get(i)[0] = corners[i].charAt(1);
            faces.get((i+1)%4)[2] = corners[i].charAt(2);

            // top layer edges
            faces.get((i+1)%4)[1] = edges[i].charAt(1);

            // bottom layer corners
            faces.get(i)[6] = corners[i+4].charAt(2);
            faces.get((i+1)%4)[8] = corners[i+4].charAt(1);

            // bottom layer edges
            faces.get((i+1)%4)[7] = edges[i+8].charAt(1);

            // E layer edges
            int firstSticker = i%2 == 0 ? 0 : 1;
            faces.get((i+1)%4)[5] = edges[i+4].charAt(firstSticker);
            faces.get(i)[3] = edges[i+4].charAt(1 - firstSticker);
        }

        left = faces.get(0); back = faces.get(1); right = faces.get(2); front = faces.get(3);
    }

    /*
        Double moves - private cause they are not a valid moves the user can do
     */

    private void up180() {
        upCw();
        upCw();
    }

    private void down180() {
        downCw();
        downCw();
    }

    private void left180() {
        leftCw();
        leftCw();
    }

    private void right180() {
        rightCw();
        rightCw();
    }

    private void front180() {
        frontCw();
        frontCw();
    }

    private void back180() {
        backCw();
        backCw();
    }
}
