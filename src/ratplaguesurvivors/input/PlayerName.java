package ratplaguesurvivors.input;

import com.codeforall.online.simplegraphics.pictures.Picture;
import ratplaguesurvivors.hud.HUDImageText;

public class PlayerName {


    private static final int MAX_NAME_LENGTH = 20;
    private static final String BACKGROUND_PATH = "resources/Menu/Covers/backgroundName.png";
    private static final int backgroundX = 0;
    private static final int backgroundY = 0;

    private static final int nameBoxX = backgroundX + 978;
    private static final int nameBoxY = backgroundY + 550;
    private static final int nameSpacing = 35;

    private static final int startButtonX = backgroundX + 690;
    private static final int startButtonY = backgroundY + 780;
    private static final int startButtonWidth = 570;
    private static final int startButtonHeight = 190;

    private Picture backgroundPicture;
    private HUDImageText nameText;
    private MouseHandler mouseHandler;
    private boolean active;
    private boolean startRequested;
    private final StringBuilder namePlayer;

    public PlayerName() {
        namePlayer = new StringBuilder();
        active = false;
        startRequested = false;
    }

    public void show(){

        clear();

        active = true;
        startRequested = false;
        namePlayer.setLength(0);

        backgroundPicture = new Picture(backgroundX,backgroundY,BACKGROUND_PATH);
        backgroundPicture.draw();

        updateNameText();

        mouseHandler = new MouseHandler(null,null,this);
        mouseHandler.init();
    };

    public void clear(){

        active = false;

        if(mouseHandler != null){
            mouseHandler.disableMouse();
            mouseHandler = null;
        }
        if(nameText  != null){
            nameText.clear();
            nameText = null;
        }
        if(backgroundPicture != null){
            backgroundPicture.delete();
            backgroundPicture = null;
        }
    }

    public void addLetter(char letter){
        if(!active){
            return;
        }
        if(namePlayer.length() >= MAX_NAME_LENGTH){
            return;
        }

        letter = Character.toUpperCase(letter);

        if( letter < 'A' || letter > 'Z'){
            return;
        }
        namePlayer.append(letter);
        updateNameText();
    }

    public void addSpacing(){
        if(!active){
            return;
        }

        if(namePlayer.length() == 0 || namePlayer.length() >= MAX_NAME_LENGTH){
            return;
        }

        namePlayer.append(' ');
        updateNameText();

    }

    public void deleteLastLetter(){
        if(!active){
            return;
        }
        if(namePlayer.length() == 0){
            return;
        }
        namePlayer.deleteCharAt(namePlayer.length() - 1);
        updateNameText();
    }

    private void updateNameText(){
        if(nameText != null){
            nameText.clear();
            nameText = null;
        }

        String name = namePlayer.toString();

        if(name.isEmpty()){
            return;
        }

        int textX = calculateNameTextX();

        nameText = new HUDImageText(textX,nameBoxY,nameSpacing);
        nameText.setText(name);
        nameText.drawText();
    }

    private int calculateNameTextX(){
        int textWidth = namePlayer.length() * nameSpacing;
        return nameBoxX - textWidth / 2;
    }

    public boolean isOnStartButton(int mouseX, int mouseY){
        return mouseX >= startButtonX
                && mouseX <= startButtonX + startButtonWidth
                && mouseY >= startButtonY
                && mouseY <= startButtonY + startButtonHeight;
    }

    public void requestStart(){
        if(!active){
            return;
        }

        startRequested = true;

    }

    public boolean isStartRequested() {
        return startRequested;
    }

    public String getName(){
        String name = namePlayer.toString().trim();

        if(name.isBlank()){
            return "Gato Das Botas";
        }

        return name;
    }

    public boolean isActive(){
        return active;
    }
}
