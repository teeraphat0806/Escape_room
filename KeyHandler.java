import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyHandler implements KeyListener{
    public boolean w_k,a_k,s_k,d_k,e_k,q_k,k_1,k_2,k_3,k_4,k_5,k_6,k_7,k_8,k_9,k_0,back_space,space,up,down;
    @Override
    public void keyTyped(KeyEvent e) {

    }
    public int chars;

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case (KeyEvent.VK_UP) -> up = true;
            case (KeyEvent.VK_DOWN) -> down = true;
            case (KeyEvent.VK_W) -> w_k = true;
            case (KeyEvent.VK_S) -> s_k = true;
            case (KeyEvent.VK_A) -> a_k = true;
            case (KeyEvent.VK_D) -> d_k = true;
            case (KeyEvent.VK_E) -> e_k = true;
            case (KeyEvent.VK_Q) -> q_k = true;
            case (KeyEvent.VK_BACK_SPACE) ->back_space = true;
            case (KeyEvent.VK_SPACE) ->space = true;
            case (KeyEvent.VK_1), (KeyEvent.VK_NUMPAD1) -> {
                k_1 = true;
                chars = 1;
            }
            case (KeyEvent.VK_2), (KeyEvent.VK_NUMPAD2) -> {
                k_2 = true;
                chars = 2;
            }
            case (KeyEvent.VK_3), (KeyEvent.VK_NUMPAD3) -> {
                k_3 = true;
                chars = 3;
            }
            case (KeyEvent.VK_4), (KeyEvent.VK_NUMPAD4) -> {
                k_4 = true;
                chars = 4;
            }
            case (KeyEvent.VK_5), (KeyEvent.VK_NUMPAD5) -> {
                k_5 = true;
                chars = 5;
            }
            case (KeyEvent.VK_6), (KeyEvent.VK_NUMPAD6) -> {
                k_6 = true;
                chars = 6;
            }
            case (KeyEvent.VK_7), (KeyEvent.VK_NUMPAD7) -> {
                k_7 = true;
                chars = 7;
            }
            case (KeyEvent.VK_8), (KeyEvent.VK_NUMPAD8) -> {
                k_8 = true;
                chars = 8;
            }
            case (KeyEvent.VK_9), (KeyEvent.VK_NUMPAD9) -> {
                k_9 = true;
                chars = 9;
            }
            case (KeyEvent.VK_0), (KeyEvent.VK_NUMPAD0) -> {
                k_0 = true;
                chars = 0;
            }

        }
    }
    public void show(){
        System.out.println("w: " + w_k +" s: "+s_k+" a: " + a_k+" d: "+d_k);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP){
            up = false;
        }
        if(code == KeyEvent.VK_DOWN){
            down = false;
        }
        if(code == KeyEvent.VK_W){
            w_k = false;
        }
        if(code == KeyEvent.VK_S){
            s_k = false;
        }
        if(code == KeyEvent.VK_A){
            a_k = false;
        }
        if(code == KeyEvent.VK_D){
            d_k = false;
        }
        if(code == KeyEvent.VK_E){
            e_k = false;
        }
        if(code == KeyEvent.VK_Q){
            q_k = false;
        }
        if(code == KeyEvent.VK_BACK_SPACE){
            back_space = false;
        }
        if(code == KeyEvent.VK_SPACE){
            space = false;
        }
        if(code == KeyEvent.VK_0 || code == KeyEvent.VK_NUMPAD0){
            k_0 = false;
        }
        if(code == KeyEvent.VK_1 || code == KeyEvent.VK_NUMPAD1){
            k_1 = false;
        }
        if(code == KeyEvent.VK_2 || code == KeyEvent.VK_NUMPAD2){
            k_2 = false;
        }
        if(code == KeyEvent.VK_3 || code == KeyEvent.VK_NUMPAD3){
            k_3 = false;
        }
        if(code == KeyEvent.VK_4 || code == KeyEvent.VK_NUMPAD4){
            k_4 = false;
        }
        if(code == KeyEvent.VK_5 || code == KeyEvent.VK_NUMPAD5){
            k_5 = false;
        }if(code == KeyEvent.VK_6 || code == KeyEvent.VK_NUMPAD6){
            k_6 = false;
        }if(code == KeyEvent.VK_7 || code == KeyEvent.VK_NUMPAD7){
            k_7 = false;
        }if(code == KeyEvent.VK_8 || code == KeyEvent.VK_NUMPAD8){
            k_8 = false;
        }if(code == KeyEvent.VK_9 || code == KeyEvent.VK_NUMPAD9){
            k_9 = false;
        }
        chars = -1;
    }

}
