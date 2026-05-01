package ratplaguesurvivors.hud;

import com.codeforall.online.simplegraphics.pictures.Picture;


public class HUDImageText {

    private int x;
    private int y;
    private int spacing;

    private Picture[] pictures;
    private int pictureCounter;
    private String text;

    public HUDImageText(int x, int y, int spacing) {
        this.x = x;
        this.y = y;
        this.spacing = spacing;

        pictures = new Picture[40];
        pictureCounter = 0;
    }

    public void drawText() {
        clear();

        if (text == null) {
            return;
        }

        String upperCase = text.toUpperCase();
        int currentX = x;

        for (int i = 0; i <= upperCase.length() - 1; i++) {
            char c = upperCase.charAt(i);
            if(c == ' '){
                currentX += spacing;
                continue;
            }

            String path = resolvePath(c);
            if (path == null || path.isEmpty()) {
                currentX += spacing;
                continue;
            }

            Picture picture = new Picture(currentX,y,path);
            picture.draw();

            pictures[pictureCounter] = picture;
            pictureCounter++;
            currentX += spacing;
        }
    }

    public void clear(){
        for(int i = 0; i < pictureCounter; i++){
            if (pictures[i] != null) {
                pictures[i].delete();
                pictures[i] = null;
            }
        }
        pictureCounter= 0;
    }

    private String resolvePath(char c){
        String path = HUDLetters.getPathForLetter(c);
        if(!path.isEmpty()){
            return path;
        }
        return HUDLetters.getPathForLetter(c);
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
