package ratplaguesurvivors.hud;

import com.codeforall.online.simplegraphics.pictures.Picture;


public class HUDNumberText {

    private int x;
    private int y;
    private int spacing;

    private Picture[] pictures;
    private int pictureCounter;
    private int number;


    public HUDNumberText(int x, int y, int spacing) {
        this.x = x;
        this.y = y;
        this.spacing = spacing;

        pictures = new Picture[12];
        pictureCounter = 0;
    }

    public void drawNumber() {
        clear();

        if (number == 0) {
            drawDigit('0', x);
            return;
        }

        if (number < 0) {
            number = Math.abs(number);
        }

        int[] digit = new int[12];
        int digitCounter = 0;

        while (number > 0) {
            digit[digitCounter] = number % 10;
            digitCounter++;
            number /= 10;
        }

        int currentX = x;

        for (int i = digitCounter - 1; i >= 0; i--) {
            char c = (char) ('0' + digit[i]);
            drawDigit(c, currentX);
            currentX += spacing;
        }
    }


    private void drawDigit(char c, int x) {
        String path = resolvePath(c);

        if (path == null || path.isEmpty()) {
            return;
        }
        Picture picture = new Picture(x, y, path);
        picture.draw();

        pictures[pictureCounter] = picture;
        pictureCounter++;
    }

    public void clear() {
        for (int i = 0; i < pictureCounter; i++) {
            if (pictures[i] != null) {
                pictures[i].delete();
                pictures[i] = null;
            }
        }
        pictureCounter = 0;
    }


    private String resolvePath(char c) {
        String path = HUDDigits.getPathFor(c);
        if (!path.isEmpty()) {
            return path;
        }
        return HUDDigits.getPathFor(c);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

}


