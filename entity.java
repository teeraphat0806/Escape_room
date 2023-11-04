public class entity {
    public int x=0,y=0;
    private int speed;
    KeyHandler keyH;
    String direction = "front";
    String post = "lde";
    entity(KeyHandler keyH,int speed){
        this.keyH = keyH;
        this.speed = speed;
    }
    public void update(){
        if(keyH.w_k){ y += speed;direction = "back"; post = "walk";}
        if(keyH.s_k){ y -= speed;direction = "front"; post = "walk";}
        if(keyH.a_k){ x -= speed;direction = "left"; post = "walk";}
        if(keyH.d_k){ x += speed;direction = "right"; post = "walk";}
        if(!keyH.w_k && !keyH.s_k && !keyH.a_k && !keyH.d_k){
           post = "lde";
        }
    }
    public void setX(int x){



        this.x = x;
    }
    public void setY(int y){
        this.y =y;
    }
    public void setDirection(String direction){
        this.direction =direction;
    }
}
