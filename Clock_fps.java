public class Clock_fps {
    final double FPS = 60;
    private double drawInterval = 1000000000/FPS;
    private long lastTime;
    private long current_time;
    private long timer = 0;
    private int drawCount = 0;
    public double delta=0;
    public Clock_fps() {
        lastTime = System.nanoTime();
    }
    public void update(){
        current_time = System.nanoTime();
        delta+= ((double) (current_time - lastTime) /(long)(drawInterval));
        timer+= (current_time - lastTime);
        lastTime = current_time;
    }
    public void show_FPS(){
        if(timer >= 1000000000){
            System.out.println("FPS:" + drawCount);
            drawCount = 0;
            timer = 0;
        }
    }
    public void update_parameter(){



        delta--;
        drawCount++;
    }
}
