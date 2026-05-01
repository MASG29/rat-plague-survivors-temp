package ratplaguesurvivors.hud;

import java.util.ArrayList;

import com.codeforall.online.simplegraphics.graphics.Color;
import com.codeforall.online.simplegraphics.graphics.Rectangle;
import com.codeforall.online.simplegraphics.pictures.Picture;

import ratplaguesurvivors.interfaces.Collidable;
import ratplaguesurvivors.utils.CollisionDetector;
import ratplaguesurvivors.utils.Position;
import ratplaguesurvivors.entity.npc.Enemy;

public class HUDComponent implements Collidable{

    private Position pos;
    private Picture body;
    private Picture bg;
    private ArrayList<HUDImageText> imageTxt;
    private ArrayList<HUDNumberText> numberTxt;
    private Rectangle bar;
    private int barResource;
    private int maxBarResource;
    private int maxBarWidth;

    public HUDComponent(int x, int y, String path){
        body = new Picture(0, 0, path);
        pos = new Position(x, y, body.getWidth(), body.getHeight());
        body.translate(x, y);
        imageTxt = new ArrayList<>();
        numberTxt = new ArrayList<>();
    }

    public void draw(){
        if (bg != null){
            bg.draw();
        }

        if (bar != null){
            bar.fill();
        }

        body.draw();
        if (imageTxt.size() > 0){
            for (HUDImageText comps : imageTxt){
                comps.drawText();
            }
        }
        if (numberTxt.size() > 0){
            for (HUDNumberText comps : numberTxt){
                comps.drawNumber();
            }
        }
    }

    public void clear(){
        if (bg != null){
            bg.delete();
        }
        body.delete();
        if (imageTxt.size() > 0){
            for (HUDImageText comps : imageTxt){
                comps.clear();
            }
        }
        if (numberTxt.size() > 0){
            for (HUDNumberText comps : numberTxt){
                comps.clear();
            }
        }
        if (bar != null){
            bar.delete();
        }
    }

    public void updateNumberTxt(int[] number) {
        if (number.length > numberTxt.size() || numberTxt.size() == 0){
            return;
        }
        for (int i = 0; i < number.length; i++){
            if (numberTxt.get(i).getNumber() == 0){
                numberTxt.get(i).setNumber(number[i]);
                continue;
            }
            if (numberTxt.get(i).getNumber() != number[i]){
                numberTxt.get(i).setNumber(number[i]);
                numberTxt.get(i).drawNumber();
            }
        }
    }

    public void updateNumberTxt(int number) {
        if (numberTxt.size() < 1){
            return;
        }
        updateNumberTxt(new int[]{number});
    }

    public void updateImageTxt(String[] txt) {
        if (txt.length > imageTxt.size() || imageTxt.size() == 0){
            return;
        }
        for (int i = 0; i < txt.length; i++){
            if (imageTxt.get(i).getText() == null){
                imageTxt.get(i).setText(txt[i]);
                continue;
            }
            if (!imageTxt.get(i).getText().equals(txt[i])){
                imageTxt.get(i).setText(txt[i]);
                imageTxt.get(i).drawText();
            }
        }
    }

    public void updateImageTxt(String txt) {
        if (imageTxt.size() < 1){
            return;
        }
        updateImageTxt(new String[]{txt});
    }

    public void updateBar(int entityResource) {
        if (entityResource == barResource){
            return;
        }

        barResource = entityResource;
        if (barResource < 0) {
            barResource = 0;
        }

        if (barResource > maxBarResource) {
            return;
        }

        if (maxBarResource <= 0){
            return;
        }

        double percent = (double) barResource / maxBarResource;
        int newWidth = (int) (percent * maxBarWidth);

        if (percent > 0.6) {
            updateBarGraphics(newWidth, Color.GREEN);
        } else if (percent > 0.3) {
            updateBarGraphics(newWidth, Color.YELLOW);
        } else {
            updateBarGraphics(newWidth, Color.RED);
        }
    }

    private void updateBarGraphics(int width, Color color) {
        bar.delete();
        bar = new Rectangle(bar.getX(), bar.getY(), width, bar.getHeight());
        bar.setColor(color);
        draw();
    }

    public void setBg(int offsetX, int offsetY, String path) {
        bg = new Picture(pos.getX() + offsetX, pos.getY() + offsetY, path);
    }

    public void setImageTxt(HUDImageText[] imageTxt) {
        for (HUDImageText i : imageTxt){
            this.imageTxt.add(i);
        }
    }

    public void setImageTxt(HUDImageText imageTxt) {
        setImageTxt(new HUDImageText[]{imageTxt});
    }

    public void setNumberTxt(HUDNumberText[] numberTxt) {
        for (HUDNumberText i : numberTxt){
            this.numberTxt.add(i);
        }
    }

    public void setNumberTxt(HUDNumberText numberTxt) {
        setNumberTxt(new HUDNumberText[]{numberTxt});
    }

    public void setBar(Rectangle bar, int barResource) {
        this.bar = bar;
        maxBarWidth = bar.getWidth();
        this.bar.setColor(Color.GREEN);
        maxBarResource = barResource;
        this.barResource = barResource;
    }

    public Rectangle getBar() {
        return bar;
    }

    @Override
    public Position getPos() {
        return pos;
    }

    @Override
    public Position getHitbox() {
        return getPos();
    }

    @Override
    public boolean hasCollided(Collidable obj2) {
        return CollisionDetector.hasCollided(pos, obj2.getHitbox());
    }

    @Override
    public void collided(Collidable obj2) {
        if (obj2 instanceof Enemy) {
            //clear();
            //draw();
        }
    }

    @Override
    public String toString(){
        return "HUD Component: " + pos.toString();
    }

}
