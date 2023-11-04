
public class menu_text {
    public int chose = -1;
    public int select = -1;
    private int multi_choice;
    menu_text(int choice, int default_chose){
        this.multi_choice = choice;
        this.chose = default_chose;
    }
    public void update(KeyHandler keyH){
        if(keyH.up && chose-1 >= 0){
            chose -= 1;
        }else if(keyH.up && chose-1 <0){
            chose = multi_choice-1;
        }
        if(keyH.down && chose+1 <= multi_choice-1){
            chose += 1;
        }else if(keyH.down && chose+1 > multi_choice-1){
            chose = 0;
        }
        if(keyH.space){
            select = chose;
        }
        keyH.space = false;
        keyH.up = false;
        keyH.down = false;
    }
}
