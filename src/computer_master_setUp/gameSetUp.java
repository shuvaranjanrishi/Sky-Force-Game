package computer_master_setUp;

import computer_master_display.Display;
import computer_master_graphics.loadImage;
import computer_master_manager.gameManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public class gameSetUp implements Runnable {
    private String title;
    private int width;
    private int height;
    private Thread thread;
    private boolean running;
    private BufferStrategy buffer;
    private Graphics g;
    private boolean start;
    private gameManager manager;
    
    private Display display;
    public static final int gameWidth = 400;
    public static final int gameHeight = 400;
    
    public gameSetUp(String title,int width,int height){
        this.title = title;
        this.width = width;
        this.height = height;
    }
    public void init(){
        display = new Display(title,width,height);
        loadImage.init();
        manager = new gameManager();
        manager.init();
        start = true;
    }
    public synchronized void start(){
        if(running)
            return;
        running = true;
        if(thread == null){
        thread = new Thread(this);
        thread.start();
        }
    }
    public synchronized void stop(){
        if(!(running))
            return;
        running = false;
        try{
            thread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public void tick(){
        manager.tick();
    }
    public void render(){
        buffer = display.getCanvas().getBufferStrategy();
        if(buffer == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = buffer.getDrawGraphics();
        g.clearRect(0, 0, width, height);
        
        //draw block
        
       // g.drawImage(loadImage.image, 50, 50, gameWidth, gameHeight,null);
       g.setColor(Color.white);
       g.fillRect(50, 50, gameWidth, gameHeight);
        manager.render(g);
        
        //end of draw
        buffer.show();
        g.dispose();
    }
    public void run() {
        init();
        
        int fps = 50;
        double timePerTick = 1000000000/fps;
        double delta = 0;
        long current = System.nanoTime();
        
        while(running){
            delta = delta + (System.nanoTime()-current)/timePerTick;
            current = System.nanoTime();
            if(delta >= 1){
                tick();
                render();
                delta--;
            }
        }
    }
}
