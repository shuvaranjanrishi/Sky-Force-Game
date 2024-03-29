package computer_master_bullets;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
    private int x;
    private int y;
    private int speed;

    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
        this.speed = 10;
    }
    public void tick(){
        y -= speed;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void render(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(x, y, 6, 10);
    }
}

