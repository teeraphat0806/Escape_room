import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.ArrayList;
import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.util.Dictionary;
import java.util.Hashtable;

public class GamePanel extends JPanel implements Runnable {
    // display setup
    final int original_size = 16;
    final int scale = 3;
    final int tilesize = original_size*scale; // 48*48 pixels
    final int maxScreencol = 16; //768 pixel
    final int maxScreenrow = 14; //672 pixel
    final int screenwidth = tilesize*maxScreencol;
    final int screenheight = tilesize*maxScreenrow;

    // key input class
    KeyHandler keyH = new KeyHandler();
    // Thread
    Thread gameThread;
    // Clock include FPS
    Clock_fps ck = new Clock_fps();
    // character
    Player players = new Player(screenwidth/2,screenheight/2,tilesize,0.09);

    // scene
    scene room = new scene(0,0,screenheight,screenwidth);
    scene room3 = new scene(0,0,screenheight,screenwidth);

    // object
    door doors = new door();
    pillar_button pillar_buttons = new pillar_button(screenwidth/2 -47,screenheight/2 +50,tilesize);
    pin_door pin_doored = new pin_door(screenwidth/2 -65,130,tilesize,0.01);
    int[][] pillar_invicible_position = {{336},{336},{7},{7}};
    image_show chess_tablee = new image_show(screenwidth/2 -280,screenheight/2 -280,tilesize*12,tilesize * 12,"/room2/chess_puzzles.png",new Color(237,28,36));
    image_show sudoku_code = new image_show(screenwidth/2 -240,screenheight/2 -240,tilesize*10,tilesize * 10,"/room2/sudoku_puzzle.png",null);
    image_show oven_fire = new image_show(533,240, tilesize,tilesize,new String[]{"/room2/fire1.png","/room2/fire2.png","/room2/fire3.png","/room2/fire4.png"},null);
    image_show oven_fire2 = new image_show(590,240, tilesize,tilesize,new String[]{"/room2/fire1.png","/room2/fire2.png","/room2/fire3.png","/room2/fire4.png"},null);


    image_show book_shelts = new image_show(screenwidth/2 -240,screenheight/2 -240,tilesize*10,tilesize * 10,"/room2/book_shelt.png",null);
    object_invicible pillar_buttonn = new object_invicible(pillar_invicible_position[0],pillar_invicible_position[1],pillar_invicible_position[2],pillar_invicible_position[3]);
    object_invicible fridge = new object_invicible(new int[]{672,672},new int[]{384,432},new int[]{8,9},new int[]{14,14});
    object_invicible fire = new object_invicible(new int[]{528,528,576,576},new int[]{384,432,384,432},new int[]{8,9,8,9},new int[]{11,11,12,12});
    object_invicible book_shelt = new object_invicible(new int[]{0,0,48,48,96,96,144,144},new int[]{384,432,384,432,384,432,384,432},new int[]{8,9,8,9,8,9,8,9},new int[]{0,0,1,1,2,2,3,3});
    object_invicible chess_table = new object_invicible(new int[]{0,0,48,48,96,96,144,144},new int[]{144,192,240,144,192,240,144,192,240,144,192,240},new int[]{3,4,5,3,4,5,3,4,5,3,4,5},new int[]{0,0,1,1,2,2,3,3});
    light lights = new light(tilesize*5,tilesize,0.01,"1010100010101010101",tilesize);
    light lights2 = new light(tilesize*9,tilesize,0.01,"0001010101",tilesize);

    light_door light_doorr = new light_door(tilesize*6.5,22,tilesize);
    light_effects light_ovenfire = new light_effects(560,240,200,new Color(250,140,10,100),new Color(250,0,0,0));
    password_pin password_pinn = new password_pin(6751,screenwidth,screenheight,tilesize);
    entity en = new entity(keyH,3);


    draw_title q_toexit = new draw_title(screenwidth-200,screenheight-40,"Q  TO EXIT",new Font("SansSerif", Font.BOLD, 25),new Color(255,255,255,200));
    draw_title numpad_backspace = new draw_title(100,screenheight-40,"NUMPAD                                  BACKSPACE",new Font("SansSerif", Font.BOLD, 18),new Color(255,255,255,200));
    draw_title e = new draw_title(screenwidth/2-27,screenheight/2-20,"E",new Font("SansSerif", Font.BOLD, 20),new Color(255,255,255,200));
    draw_title arrow_down = new draw_title(screenwidth/2-25,screenheight/2+10,"↓",new Font("SansSerif", Font.BOLD, 20),new Color(255,255,255,200));
    draw_title arrow_down2 = new draw_title(screenwidth/2-25,screenheight/2+10,"↓",new Font("SansSerif", Font.BOLD, 20),new Color(255,255,255,200));
    draw_title q = new draw_title(screenwidth/2-27,screenheight/2-20,"Q",new Font("SansSerif", Font.BOLD, 20),new Color(255,255,255,200));
    draw_title intro_dialog = new draw_title(screenwidth/2 -130,screenheight -80,new String[]{"OH","WHERE AM I","HOW CAN I ARRIVE THIS ROOM","LAST TIME I WAS WALKING CROSS STREET","THEN I WAKE UP","OH NO LENA","I HAVE TO PICK HER UP AT SCHOOL","LENA WAIT FOR ME","DAD IS COMING","I WILL FIND THE WAY OUT"," "},new Font("SansSerif", Font.BOLD, 20),Color.WHITE);
    draw_title last_dialog1 = new draw_title(screenwidth/2 -110,screenheight -80,new String[]{"LENA","IS THAT YOU","I MISS YOU SO MUCH","I'M GLAD YOU ARE SAVE"},new Font("SansSerif", Font.BOLD, 20),Color.WHITE);
    draw_title last_dialog2 = new draw_title(screenwidth/2 -90,screenheight/2,new String[]{" ","DAD","WE ARE IN HEAVEN"," "},new Font("SansSerif", Font.BOLD, 20),Color.CYAN);
    draw_title exit = new draw_title(600,550,"EXIT",new Font("SansSerif", Font.BOLD, 40),Color.white);
    draw_title room1_time = new draw_title(70,200,"",new Font("SansSerif", Font.BOLD, 50),Color.white);
    draw_title room2_time = new draw_title(70,300,"",new Font("SansSerif", Font.BOLD, 50),Color.white);
    draw_title room3_time = new draw_title(70,400,"",new Font("SansSerif", Font.BOLD, 50),Color.white);
    draw_title play = new draw_title(100,250,"PLAY",new Font("SansSerif", Font.BOLD, 50),Color.white);
    draw_title credit = new draw_title(100,350,"CREDIT",new Font("SansSerif", Font.BOLD, 50),Color.white);
    // draw object_draw

    ArrayList<object_draw> obj_draw = new ArrayList<object_draw>();
    // function
    collison collisons = new collison();
    // room 3
    entity en3 = new entity(keyH,3);
    entity en_endroom = new entity(keyH,3);

    password_pin password_pinn3 = new password_pin(12,screenwidth,screenheight,tilesize);
    image_show book_shelt3 = new image_show(screenwidth/2 -240,screenheight/2 -240,tilesize*10,tilesize * 10,"/room3/book_shelt.png",null);
    image_show shelt3 = new image_show(screenwidth -190,screenheight -190,tilesize*4,tilesize * 4,new String[]{"/room3/shelts.png","/room3/shelts2.png"},null);
     // light fireoven
    light_effects light_ovenfire1 = new light_effects(10,240,200,new Color(250,140,10,80),new Color(250,0,0,0));
    light_effects light_ovenfire2 = new light_effects(120,240,200,new Color(250,140,10,80),new Color(250,0,0,0));
    light_effects light_ovenfire3 = new light_effects(485,240,200,new Color(250,140,10,80),new Color(250,0,0,0));
    light_effects light_ovenfire4 = new light_effects(585,240,200,new Color(250,140,10,80),new Color(250,0,0,0));
    light_effects light_floor1 = new light_effects(435,420,70,new Color(140,200,80,70),new Color(0,250,0,0));
    light_effects light_floor2 = new light_effects(340,520,70,new Color(140,200,80,70),new Color(0,250,0,0));
    light_effects lena = new light_effects(screenwidth/2,screenheight/2-100,150,Color.white,new Color(140,255,251,0));
    object_invicible oven1 = new object_invicible(new int[]{0,0,48,48},new int[]{384,432,384,432},new int[]{8,9,8,9},new int[]{0,0,1,1});
    // oven fire
    image_show oven_fir1 = new image_show(5,240, tilesize,tilesize,new String[]{"/room2/fire1.png","/room2/fire2.png","/room2/fire3.png","/room2/fire4.png"},null);
    image_show oven_fir2 = new image_show(50,240, tilesize,tilesize,new String[]{"/room2/fire1.png","/room2/fire2.png","/room2/fire3.png","/room2/fire4.png"},null);
    image_show oven_fir3 = new image_show(100,240, tilesize,tilesize,new String[]{"/room2/fire1.png","/room2/fire2.png","/room2/fire3.png","/room2/fire4.png"},null);
    image_show oven_fir4 = new image_show(155,240, tilesize,tilesize,new String[]{"/room2/fire1.png","/room2/fire2.png","/room2/fire3.png","/room2/fire4.png"},null);
    image_show oven_fir5 = new image_show(460,240, tilesize,tilesize,new String[]{"/room2/fire1.png","/room2/fire2.png","/room2/fire3.png","/room2/fire4.png"},null);
    image_show oven_fir6 = new image_show(515,240, tilesize,tilesize,new String[]{"/room2/fire1.png","/room2/fire2.png","/room2/fire3.png","/room2/fire4.png"},null);
    image_show oven_fir7 = new image_show(570,240, tilesize,tilesize,new String[]{"/room2/fire1.png","/room2/fire2.png","/room2/fire3.png","/room2/fire4.png"},null);
    image_show oven_fir8 = new image_show(615,240, tilesize,tilesize,new String[]{"/room2/fire1.png","/room2/fire2.png","/room2/fire3.png","/room2/fire4.png"},null);
    image_show shelts_3 = new image_show(screenwidth/2 -240,screenheight/2 -240,tilesize*10,tilesize * 10,"/room3/book_shelt.png",null);
    image_show intro_player = new image_show(screenwidth/2,screenheight/2,tilesize*2,tilesize*2,new String[]{"/player/sleep3.png","/player/sleep2.png","/player/sleep1.png","/player/front1.png"},Color.WHITE);
    image_show wood_background = new image_show(screenwidth/2 -390,screenheight/2 -235,tilesize*16,tilesize * 8,"/last_scene/wood_background.png",Color.white);
    object_invicible oven2 = new object_invicible(new int[]{96,96,144,144},new int[]{384,432,384,432},new int[]{8,9,8,9},new int[]{2,2,3,3});
    object_invicible oven3 = new object_invicible(new int[]{480,480,528,528},new int[]{384,432,384,432},new int[]{8,9,8,9},new int[]{10,10,11,11});
    object_invicible oven4 = new object_invicible(new int[]{576,576,624,624},new int[]{384,432,384,432},new int[]{8,9,8,9},new int[]{12,12,13,13});
    object_invicible bookshelt3 = new object_invicible(new int[]{576,576,576,624,624,624,672,672,720,720,720},new int[]{144,192,240,144,192,240,144,192,240,144,192,240},new int[]{3,4,5,3,4,5,3,4,5,3,4,5},new int[]{12,12,12,13,13,13,14,14,14,15,15,15});
    light_door light_doorr3 = new light_door(tilesize*6.5,22,tilesize);

    // icon
    image_show backspace_icon = new image_show(280,screenheight-60,tilesize/2 +20,tilesize/2,"/scene/backspace_icon.png",Color.BLACK);
    image_show numpad_icon = new image_show(55,screenheight-60,tilesize/2,tilesize/2,"/scene/numpad_icon.png",Color.BLACK);
    // time playing
    record_time record_playtime = new record_time();
    private Dictionary<String,Long> all_time = new Hashtable<>();
    object_draw pillar_button = pillar_buttons.draw(en);
    dark_effect darkEffect = new dark_effect(0,0,screenwidth,screenheight,200,0.5);
    dark_effect darkEffect_room3 = new dark_effect(0,0,screenwidth,screenheight,200,0.5);
    dark_effect lightEffect = new dark_effect(0,0,screenwidth,screenheight,200,1);

    menu_text menu_texts = new menu_text(2,0);

    // sound
    sound pass_sound = new sound("/sound/door_true.wav");
    sound false_sound = new sound("/sound/door_false.wav");
    sound fire_sound = new sound("/sound/fire_sound.wav");
    sound walk_sound = new sound("/sound/walk_sound.wav");
    sound button_sound = new sound("/sound/button.wav");
    sound door_close = new sound("/sound/door_close.wav");
    sound bept = new sound("/sound/bept.wav");
    sound beat = new sound("/sound/beat_sound.wav");

    // boolean game pass
    public boolean menu = false;
    public boolean intro = false;
    public boolean room_1 = false;
    public boolean room_2 = false;
    public boolean room_3 = false;
    public boolean end_room = false;
    public boolean run_thread = true;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenwidth,screenheight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }
    public void startGameThread(){
        gameThread = new Thread(this); // initialize thread
        gameThread.start(); // start game loop
    }
    public void stopGameThread(){
        gameThread.interrupt();
    }
    public void close_game(){
        run_thread = false;
        System.exit(1);
    }
    public void run(){
        initial_menu();
        while(gameThread != null){
            ck.update(); // calculate fps
            if (ck.delta >= 1 && !menu) { // if delta = 1 second
                update_menu();
                repaint();
                // clock parameter
                ck.update_parameter();
            }
            if(menu){
                play.setDraw(false);
                credit.setDraw(false);
                break;
            }
        }
        if(menu && run_thread){
            initial_intro();
            while(gameThread != null){
                ck.update(); // calculate fps
                if (ck.delta >= 1 && !intro) { // if delta = 1 second
                    update_intro();
                    repaint();
                    // clock parameter
                    ck.update_parameter();
                }
                if(intro){
                    break;
                }
            }
        }
        if(intro && run_thread){
            initial_room1();
            record_playtime.start_time();
            while (gameThread != null) {
                ck.update(); // calculate fps
                if (ck.delta >= 1 && !room_1) { // if delta = 1 second
                    update_room1();
                    repaint();
                    // clock parameter
                    ck.update_parameter();
                }
                if(room_1){
                    break;
                }
            }
        }
        record_playtime.end_time();
        all_time.put("hour1", record_playtime.get_hour());
        all_time.put("minute1", record_playtime.get_minute());
        all_time.put("second1", record_playtime.get_second());
        record_playtime.restart_time();
        if(room_1 && run_thread) {
            initial_room2();
            record_playtime.start_time();
            while (gameThread != null) {
                ck.update(); // calculate fps
                if (ck.delta >= 1 && !room_2) {
                    update_room2();
                    repaint();
                    // clock parameter
                    ck.update_parameter();
                }
                if (room_2) {
                    break;
                }
            }
        }
        record_playtime.end_time();
        all_time.put("hour2", record_playtime.get_hour());
        all_time.put("minute2", record_playtime.get_minute());
        all_time.put("second2", record_playtime.get_second());
        record_playtime.restart_time();
        if(room_1 && room_2 && run_thread){

            initial_room3();
            record_playtime.start_time();
            while (gameThread != null){
                ck.update(); // calculate fps
                if(ck.delta >= 1 && !room_3){
                    update_room3();
                    repaint();
                    // clock parameter
                    ck.update_parameter();
                }
                if(room_3){
                    break;

                }
            }
        }
        record_playtime.end_time();
        all_time.put("hour3", record_playtime.get_hour());
        all_time.put("minute3", record_playtime.get_minute());
        all_time.put("second3", record_playtime.get_second());
        record_playtime.restart_time();
        if(room_3 && run_thread){
            initial_end();
            while (!end_room) {
                ck.update(); // calculate fps
                if(ck.delta >= 1 && !end_room){
                    update_end();
                    repaint();
                    // clock parameter
                    ck.update_parameter();
                }
            }
        }


    }
    public void update_menu(){
        menu_texts.update(keyH);
        switch (menu_texts.chose){
            case (0):
                play.animation_filpflop(200,1);
                credit.set_alphafront(230);
                break;
            case (1):
                credit.animation_filpflop(200,1);
                play.set_alphafront(230);
                break;
        }
        switch (menu_texts.select){
            case (0):
                menu = true;
                bept.play();
                bept.setFile();
                break;
            case (1):
                bept.play();
                bept.setFile();
                try
            {
                Desktop desk = Desktop.getDesktop();

                // now we enter our URL that we want to open in our
                // default browser
                desk.browse(new URI("https://itch.io/"));
            }catch (IOException e){
                    e.printStackTrace();
                }catch (URISyntaxException excp){
                    excp.printStackTrace();
                }
                menu_texts.select = -1;
                break;
        }
    }
   public void update_intro(){

        pin_doored.update();
        darkEffect.update_light(0);
        if(!intro_player.loop){
            intro_player.update(0.02,false);
        }
        else if(intro_player.loop) {
            intro_dialog.setDraw(true);
            intro_player.setSprite(intro_player.images.length-1);
            if (!intro_dialog.dialog) {
                intro_dialog.setDraw(true);
                intro_dialog.animation_dialog(0.01);

            } else {
                intro_dialog.setDraw(false);
                intro = true;
            }
        }
   }
    public void update_room1(){

        darkEffect.update_light(0);
        if(!password_pinn.draws){
            en.update();
        }
        doors.update(en);
        pillar_buttonn.update(en);
        pin_doored.update();
        e.animation_update(1,screenheight/2-10,"y");
        arrow_down.animation_update(1,screenheight/2+20,"y");
        if(password_pinn.draws){
            password_pinn.update(keyH);
            keyH.back_space = false;
            keyH.chars = -1;
        }
        if(collisons.check_collison(players.row,players.column,doors.column,doors.row) && keyH.e_k ){
            numpad_backspace.setDraw(true);
            numpad_icon.setDraw(true);
            backspace_icon.setDraw(true);
            if(!password_pinn.pass){
                password_pinn.setDraws(true);
            }else{
                room_1 = true;
            }
        }
        if(collisons.check_collison(players.row,players.column,pillar_buttonn.column, pillar_buttonn.row) && keyH.e_k){
            lights.setLight_activate(true);
            lights2.setLight_activate(true);
            pillar_buttons.setClick(true);
            button_sound.play();
            e.setDraw(false);
            arrow_down.setDraw(false);
        }
        if(lights.light_activate == false && lights2.light_activate == false){
            pillar_buttons.setClick(false);
            button_sound.setFile();
        }
        if(keyH.q_k){
            password_pinn.setDraws(false);
            numpad_backspace.setDraw(false);
            numpad_icon.setDraw(false);
            backspace_icon.setDraw(false);

        }
        if(password_pinn.pass){
            pass_sound.play();
            light_doorr.setPass(true);
            q_toexit.setDraw(false);
            numpad_backspace.setDraw(false);
            numpad_icon.setDraw(false);
            numpad_backspace.setDraw(false);
            backspace_icon.setDraw(false);
            password_pinn.setDraws(false);
        }
        if(password_pinn.passworded != 0 && !password_pinn.pass){
            false_sound.play();
            false_sound.setFile();
        }
        if(keyH.w_k||keyH.a_k||keyH.d_k||keyH.s_k){
            walk_sound.loop();
        }else{
            walk_sound.stop();
        }

    }
    public void update_room2(){
        darkEffect.update_light(0);
        door_close.play();
        if(!chess_tablee.draw && !password_pinn.draws && !sudoku_code.draw && !book_shelts.draw){
            en.update();
        }
        doors.update(en);
        fridge.update(en);
        fire.update(en);
        book_shelt.update(en);
        chess_table.update(en);
        oven_fire.update(0.05,true);
        oven_fire2.update(0.05,true);
        pin_doored.update();
        e.animation_update(1,screenheight/2 -80,"y");
        q.animation_update(1,screenheight/2 -80,"y");
        arrow_down.animation_update(1,screenheight/2 -60,"y");
        arrow_down2.animation_update(1,screenheight/2 -60,"y");


        if(password_pinn.draws){
            password_pinn.update(keyH);
            keyH.back_space = false;
            keyH.chars = -1;
        }
        if(collisons.check_collison(players.row,players.column,doors.column,doors.row) && keyH.e_k ){
            numpad_backspace.setDraw(true);
            q_toexit.setDraw(true);
            numpad_icon.setDraw(true);
            backspace_icon.setDraw(true);
            if(!password_pinn.pass){
                password_pinn.setDraws(true);
            }else{
                room_2 = true;
                door_close.setFile();
                q_toexit.setDraw(false);
                numpad_backspace.setDraw(false);
                backspace_icon.setDraw(false);
                numpad_icon.setDraw(false);
            }
        }
        if(collisons.check_collison(players.row,players.column, fridge.column, fridge.row )&& keyH.e_k){
            sudoku_code.setDraw(true);
            q_toexit.setDraw(true);
        }
        if(collisons.check_collison(players.row,players.column, fire.column, fire.row) && keyH.e_k){
            e.setDraw(false);
            if(!light_ovenfire.draws){
                light_ovenfire.setDraws(true);
                fire_sound.loop();
                arrow_down.setDraw(false);
            }if(oven_fire2.draw){
                oven_fire2.setDraw(false);
            }else if(!oven_fire2.draw){
                oven_fire2.setDraw(true);
            }if(!oven_fire2.draw && !oven_fire.draw){
                light_ovenfire.setDraws(false);
                fire_sound.stop();
                fire_sound.setFile();
            }
        }
        if(collisons.check_collison(players.row,players.column, fire.column, fire.row) && keyH.q_k){
            q.setDraw(false);
            if(!light_ovenfire.draws){
                light_ovenfire.setDraws(true);
                fire_sound.loop();
                arrow_down2.setDraw(false);
            }
            if(oven_fire.draw){
                oven_fire.setDraw(false);
            }else if(!oven_fire.draw){
                oven_fire.setDraw(true);
            }
            if(!oven_fire2.draw && !oven_fire.draw){
                light_ovenfire.setDraws(false);
                fire_sound.stop();
                fire_sound.setFile();
            }
        }
        if(collisons.check_collison(players.row,players.column, book_shelt.column, book_shelt.row) && keyH.e_k){
            book_shelts.setDraw(true);
            q_toexit.setDraw(true);
        }
        if(collisons.check_collison(players.row,players.column, chess_table.column, chess_table.row) && keyH.e_k){
            chess_tablee.setDraw(true);
            q_toexit.setDraw(true);
        }
        if(keyH.q_k){
            password_pinn.setDraws(false);
            chess_tablee.setDraw(false);
            sudoku_code.setDraw(false);
            book_shelts.setDraw(false);
            q_toexit.setDraw(false);
            numpad_backspace.setDraw(false);
            numpad_icon.setDraw(false);
            backspace_icon.setDraw(false);
        }
        if(password_pinn.pass){
            light_doorr.setPass(true);
            password_pinn.setDraws(false);
            pass_sound.play();
            q_toexit.setDraw(false);
            numpad_backspace.setDraw(false);
            backspace_icon.setDraw(false);
            numpad_icon.setDraw(false);
            numpad_backspace.setDraw(false);
            password_pinn.setDraws(false);
        }

        if(password_pinn.passworded != 0 && !password_pinn.pass){
            false_sound.play();
            false_sound.setFile();
        }
        if(keyH.w_k||keyH.a_k||keyH.d_k||keyH.s_k){
            walk_sound.loop();
        }else{
            walk_sound.stop();
        }
    }
    public void update_room3(){
        darkEffect_room3.update_light(0);
        door_close.play();
        if(!shelts_3.draw && !password_pinn3.draws){
            en3.update();
        }
        doors.update(en3);
        pin_doored.update();
        oven1.update(en3);
        oven2.update(en3);
        oven3.update(en3);
        oven4.update(en3);
        oven_fir1.update(0.05,true);
        oven_fir2.update(0.05,true);
        oven_fir3.update(0.05,true);
        oven_fir4.update(0.05,true);
        oven_fir5.update(0.05,true);
        oven_fir6.update(0.05,true);
        oven_fir7.update(0.05,true);
        oven_fir8.update(0.05,true);
        bookshelt3.update(en3);
        if(oven_fir1.draw && oven_fir2.draw && oven_fir3.draw && oven_fir4.draw && oven_fir5.draw && !oven_fir6.draw && !oven_fir7.draw && oven_fir8.draw){
            light_doorr3.setPass(true);
            pass_sound.play();
        }
        if(password_pinn3.draws){
            password_pinn3.update(keyH);
            keyH.back_space = false;
            keyH.chars = -1;
        }
        if(collisons.check_collison(players.row,players.column,doors.column,doors.row) && keyH.e_k ){
            password_pinn3.setDraws(true);
            numpad_backspace.setDraw(true);
            numpad_icon.setDraw(true);
            backspace_icon.setDraw(true);
            pass_sound.setFile();
            if(light_doorr3.pass){
                room_3 = true;
            }
        }
        if(password_pinn.pass){
            light_floor1.setDraws(true);
            light_floor2.setDraws(true);
            password_pinn.setDraws(false);
            q_toexit.setDraw(false);
            backspace_icon.setDraw(false);
            numpad_backspace.setDraw(false);
            numpad_icon.setDraw(false);
            numpad_backspace.setDraw(false);
            pass_sound.play();
        }
        if(collisons.check_collison(players.row,players.column,oven1.column,oven1.row)){
            if(keyH.q_k){
                if(!light_ovenfire1.draws){
                    light_ovenfire1.setDraws(true);

                }if(oven_fir1.draw){
                    oven_fir1.setDraw(false);
                }else if(!oven_fir1.draw){
                    oven_fir1.setDraw(true);
                }
            }
            if(keyH.e_k) {
                if (!light_ovenfire1.draws) {
                    light_ovenfire1.setDraws(true);

                }
                if (oven_fir2.draw) {
                    oven_fir2.setDraw(false);
                } else if (!oven_fir2.draw) {
                    oven_fir2.setDraw(true);
                }
            }if (!oven_fir1.draw && !oven_fir2.draw) {
                light_ovenfire1.setDraws(false);
            }
        }

        if(collisons.check_collison(players.row,players.column,oven2.column,oven2.row)){
            if(keyH.q_k){
                if(!light_ovenfire2.draws){
                    light_ovenfire2.setDraws(true);

                }if(oven_fir3.draw){
                    oven_fir3.setDraw(false);
                }else if(!oven_fir3.draw){
                    oven_fir3.setDraw(true);
                }
            }
            if(keyH.e_k) {
                if (!light_ovenfire2.draws) {
                    light_ovenfire2.setDraws(true);

                }
                if (oven_fir4.draw) {
                    oven_fir4.setDraw(false);
                } else if (!oven_fir4.draw) {
                    oven_fir4.setDraw(true);
                }
            }if (!oven_fir3.draw && !oven_fir4.draw) {
                light_ovenfire2.setDraws(false);
            }
        }
        if(collisons.check_collison(players.row,players.column,oven3.column,oven3.row)){
            if(keyH.q_k){
                if(!light_ovenfire3.draws){
                    light_ovenfire3.setDraws(true);

                }if(oven_fir5.draw){
                    oven_fir5.setDraw(false);
                }else if(!oven_fir5.draw){
                    oven_fir5.setDraw(true);
                }
            }
            if(keyH.e_k) {
                if (!light_ovenfire3.draws) {
                    light_ovenfire3.setDraws(true);

                }
                if (oven_fir6.draw) {
                    oven_fir6.setDraw(false);
                } else if (!oven_fir6.draw) {
                    oven_fir6.setDraw(true);
                }
            }if (!oven_fir5.draw && !oven_fir6.draw) {
                light_ovenfire3.setDraws(false);
            }
        }
        if(collisons.check_collison(players.row,players.column,oven4.column,oven4.row)){
            if(keyH.q_k){
                if(!light_ovenfire4.draws){
                    light_ovenfire4.setDraws(true);

                }if(oven_fir7.draw){
                    oven_fir7.setDraw(false);
                }else if(!oven_fir7.draw){
                    oven_fir7.setDraw(true);
                }
            }
            if(keyH.e_k) {
                if (!light_ovenfire4.draws) {
                    light_ovenfire4.setDraws(true);

                }
                if (oven_fir8.draw) {
                    oven_fir8.setDraw(false);
                } else if (!oven_fir8.draw) {
                    oven_fir8.setDraw(true);
                }
            }if (!oven_fir7.draw && !oven_fir8.draw) {
                light_ovenfire4.setDraws(false);
            }
        }
        if(oven_fir1.draw || oven_fir2.draw || oven_fir3.draw || oven_fir4.draw || oven_fir5.draw || oven_fir6.draw || oven_fir7.draw ||oven_fir8.draw){
            fire_sound.loop();
        }
        if(!oven_fir1.draw&& !oven_fir2.draw&&!oven_fir3.draw&&!oven_fir4.draw&&!oven_fir5.draw&&!oven_fir6.draw&&!oven_fir7.draw&&!oven_fir8.draw){
            fire_sound.stop();
            fire_sound.setFile();
        }
        if(collisons.check_collison(players.row,players.column,bookshelt3.column,bookshelt3.row) && keyH.e_k){
            shelts_3.setDraw(true);
            q_toexit.setDraw(true);
        }
        if(keyH.q_k){
            password_pinn3.setDraws(false);
            shelts_3.setDraw(false);
            q_toexit.setDraw(false);
            numpad_backspace.setDraw(false);
            numpad_icon.setDraw(false);
            backspace_icon.setDraw(false);
        }
        if(password_pinn3.passworded != 0 && !password_pinn3.pass){
            false_sound.play();
            false_sound.setFile();
        }
        if(keyH.w_k||keyH.a_k||keyH.d_k||keyH.s_k){
            walk_sound.loop();
        }else{
            walk_sound.stop();
        }
    }
    public void update_end(){
        lightEffect.update_light(0);
        if(lightEffect.alpha == 0) {
            last_dialog1.animation_dialog(0.01);
            exit.animation_filpflop(210,1);

            if (last_dialog1.word == last_dialog1.words.get(last_dialog1.words.size() - 1)) {
                last_dialog2.setDraw(true);
                last_dialog2.animation_dialog(0.01);
            }
            if (last_dialog2.word == last_dialog2.words.get(1)) {
                last_dialog1.setDraw(false);
            }
            if (last_dialog2.word == last_dialog2.words.get(last_dialog2.words.size() - 1)) {
                exit.setDraw(true);
                room1_time.setDraw(true);
                room2_time.setDraw(true);
                room3_time.setDraw(true);
                wood_background.setDraw(true);
                if (keyH.space) {
                    bept.play();
                    bept.setFile();
                    end_room = true;
                    stopGameThread();
                    close_game();
                }
            }
        }

    }
    public void initial_menu(){
        play.setDraw(true);
        credit.setDraw(true);
    }
    public void initial_intro(){
        beat.loop();
        intro_player.setDraw(true);
        darkEffect.setDraw(true);
        room.setScene("/scene/room1.png");
    }
    public void initial_room1(){

        e.setDraw(true);
        arrow_down.setDraw(true);
        obj_draw.add(pillar_buttons.draw(en));
        obj_draw.add(players.draw(en));
        room.setScene("/scene/room1.png");
    }
    public void initial_room2(){
        darkEffect.setAlpha(250);
        darkEffect.setSpeed_effect(0.9);
        door_close.setFile();
        password_pinn.setPass(false);
        password_pinn.passworded=0;
        password_pinn.clear();
        password_pinn.setPassword(200);
        password_pinn.setText_color(Color.WHITE);
        room.setScene("/scene/room2.png");
        light_doorr.setPass(false);
        en.setY(-240);
        en.setDirection("back");
        obj_draw.clear();
        obj_draw.add(players.draw(en));
        oven_fire2.setSprite(2);
        oven_fire.setX(530);
        oven_fire2.setX(590);
        oven_fire.setY(240);
        oven_fire2.setY(240);
        light_ovenfire.update_animate((float) 0.05);
        // tutorial movement
        e.setDraw(true);
        q.setDraw(true);
        numpad_backspace.setDraw(false);
        arrow_down.setDraw(true);
        arrow_down2.setDraw(true);
        numpad_icon.setDraw(false);
        backspace_icon.setDraw(false);
        // setup text
        e.setX(screenwidth-160);
        e.setXX(screenwidth-160);
        e.setY(screenheight/2 -100);
        e.setYY(screenheight/2 -100);
        q.setX(screenwidth-220);
        q.setXX(screenwidth-220);
        q.setY(screenheight/2 -100);
        q.setYY(screenheight/2 -100);
        arrow_down.setX(screenwidth-160);
        arrow_down.setXX(screenwidth-160);
        arrow_down.setY(screenheight/2-80);
        arrow_down.setYY(screenheight/2-80);
        arrow_down2.setX(screenwidth-220);
        arrow_down2.setXX(screenwidth-220);
        arrow_down2.setY(screenheight/2-80);
        arrow_down2.setYY(screenheight/2-80);

        door_close.setFile();
        door_close.play();
        pass_sound.setFile();
        false_sound.setFile();
    }
    public void initial_room3(){}{
        darkEffect_room3.setAlpha(250);
        darkEffect_room3.setSpeed_effect(0.9);
        darkEffect_room3.setDraw(true);

        light_doorr.setPass(false);
        en3.setY(-240);
        en3.setDirection("back");
        obj_draw.clear();
        obj_draw.add(players.draw(en));

        room3.setScene("/room3/room3_1.png");


        oven_fire.setX(screenwidth/2-80);
        oven_fire.setY(screenheight/2);
        oven_fire2.setX(screenwidth/2);
        oven_fire2.setY(screenheight/2);
        oven_fir1.setDraw(true);
        oven_fir2.setDraw(true);
        oven_fir4.setDraw(true);
        oven_fir7.setDraw(true);

        light_ovenfire1.setDraws(true);
        light_ovenfire2.setDraws(true);
        light_ovenfire4.setDraws(true);

        numpad_icon.setDraw(false);
        backspace_icon.setDraw(false);

        light_ovenfire.update_animate((float) 0.05);
        password_pinn3.setPassword(210);
        password_pinn.passworded=0;
        shelt3.setDraw(true);
        backspace_icon.setDraw(false);
        numpad_icon.setDraw(false);

        door_close.setFile();
        door_close.play();
        pass_sound.setFile();
        false_sound.setFile();
        fire_sound.setFile();
        fire_sound.play();
    }
    public void initial_end(){
        door_close.setFile();
        door_close.play();
        room1_time.setWord(String.join("","Room 1            ",Long.toString(all_time.get("hour1")),".",Long.toString(all_time.get("minute1")),".",Long.toString(all_time.get("second1")),"   sec"));
        room2_time.setWord(String.join("","Room 2            ",Long.toString(all_time.get("hour2")),".",Long.toString(all_time.get("minute2")),".",Long.toString(all_time.get("second2")),"   sec"));
        room3_time.setWord(String.join("","Room 3            ",Long.toString(all_time.get("hour3")),".",Long.toString(all_time.get("minute3")),".",Long.toString(all_time.get("second3")),"   sec"));
        last_dialog1.setDraw(true);
        room.setScene("/last_scene/last_scene.png");
        en.post = "back";
        lightEffect.setDraw(true);
        lena.setDraws(true);
        fire_sound.stop();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if(!menu){
            play.draw(g2);
            credit.draw(g2);
            g2.setColor(new Color(255,255,255,30));
            g2.fillRect(0,play.y-35+(menu_texts.chose*100),screenwidth,35);
        }
        else if(!intro && menu){
            room.draw(en,g2);
            pin_doored.draw(en,g2);
            intro_player.draw_sprite(g2);
            g2.drawImage(pillar_button.image,pillar_button.x,pillar_button.y,pillar_button.tile_size1,pillar_button.tile_size2,null);
            g2.setColor(new Color(0,0,0,100));
            g2.fillRect(0,0,900,900);
            light_doorr.draw(en,g2);
            lights.Light_flip(en,g2);
            lights2.Light_flip(en,g2);
            light_doorr.light_effect(en,g2);

            draw_black_background(g2);
            intro_dialog.draw(g2);
            darkEffect.draw_dark(g2);

        }
        else if(!room_1&& intro){

        room.draw(en,g2);
        room.collide_room(en);
        pin_doored.draw(en,g2);

        if(light_doorr.pass){
            g2.setColor(new Color(0,0,0,255));
            g2.fillRect(310-en.x,60+en.y,tilesize*2+10,tilesize*4+37);
        }
        obj_draw.set(1,players.draw(en));
        obj_draw.set(0,pillar_buttons.draw(en));
        sort_obj();

        for(int i=0;i < obj_draw.size()-1;i++){
            g2.drawImage(obj_draw.get(i).image,obj_draw.get(i).x,obj_draw.get(i).y,obj_draw.get(i).tile_size1,obj_draw.get(i).tile_size2,null);
        }

        light_doorr.draw(en,g2);
        lights.Light_flip(en,g2);
        lights2.Light_flip(en,g2);
        g2.setColor(new Color(0,0,0,100));
        g2.fillRect(0,0,900,900);
        light_doorr.light_effect(en,g2);
        arrow_down.draw_movement(g2,en);
        e.draw_movement(g2,en);
        // draw black background override light
        draw_black_background(g2);
        if(password_pinn.draws){
            g2.setColor(new Color(20,20,20));
            g2.fillRect(0,screenheight-(tilesize+30),screenwidth,tilesize*3);
        }

        backspace_icon.draw(g2);
        numpad_icon.draw(g2);
        password_pinn.draw(g2);
        numpad_backspace.draw(g2);


        } else if (!room_2 && room_1) {

            room.draw(en,g2);
            room.collide_room2(en);
            pin_doored.draw(en,g2);

            if(password_pinn.pass){
                g2.setColor(new Color(0,0,0,255));
                g2.fillRect(310-en.x,60+en.y,tilesize*2+10,tilesize*4+37);
            }
            obj_draw.set(0,players.draw(en));

            sort_obj();

            for(int i=0;i < obj_draw.size();i++){
                g2.drawImage(obj_draw.get(i).image,obj_draw.get(i).x,obj_draw.get(i).y,obj_draw.get(i).tile_size1,obj_draw.get(i).tile_size2,null);
            }

            light_doorr.draw(en,g2);
            g2.setColor(new Color(0,0,0,100));
            g2.fillRect(0,0,900,900);
            light_doorr.light_effect(en,g2);
            oven_fire.draw_sprite(g2,en);
            oven_fire2.draw_sprite(g2,en);
            arrow_down2.draw_movement(g2,en);
            arrow_down.draw_movement(g2,en);
            chess_tablee.draw(g2);
            sudoku_code.draw(g2);
            book_shelts.draw(g2);
            q.draw_movement(g2,en);
            e.draw_movement(g2,en);
            light_ovenfire.draw(g2,en);

            // draw black background override light
            // draw_black_background(g2);
            password_pinn.draw(g2);
            darkEffect.draw_dark(g2);
            if(password_pinn.draws){
                g2.setColor(new Color(20,20,20));
                g2.fillRect(0,screenheight-(tilesize+30),screenwidth,tilesize*3);
            }
            backspace_icon.draw(g2);
            numpad_icon.draw(g2);
            q_toexit.draw(g2);
            numpad_backspace.draw(g2);
        }else if(!room_3 && room_2){

            room3.draw(en3,g2);
            pin_doored.draw(en3,g2);
            room3.collide_room3(en3);

            if(password_pinn3.pass){
                room3.setScene("/room3/room3_2.png");
                shelt3.setSprite(1);
            }
            if(light_doorr3.pass){
                g2.setColor(new Color(0,0,0,255));
                g2.fillRect(310-en3.x,60+en3.y,tilesize*2+10,tilesize*4+37);
            }
            obj_draw.set(0,players.draw(en3));
            sort_obj();
            for(int i=0;i < obj_draw.size();i++){
                g2.drawImage(obj_draw.get(i).image,obj_draw.get(i).x,obj_draw.get(i).y,obj_draw.get(i).tile_size1,obj_draw.get(i).tile_size2,null);
            }
            shelt3.draw_sprite(g2,en3);
            light_doorr3.draw(en3,g2);
            g2.setColor(new Color(0,0,0,100));
            g2.fillRect(0,0,900,900);

            oven_fir1.draw_sprite(g2,en3);
            oven_fir2.draw_sprite(g2,en3);
            oven_fir3.draw_sprite(g2,en3);
            oven_fir4.draw_sprite(g2,en3);
            oven_fir5.draw_sprite(g2,en3);
            oven_fir6.draw_sprite(g2,en3);
            oven_fir7.draw_sprite(g2,en3);
            oven_fir8.draw_sprite(g2,en3);
            shelts_3.draw(g2);
            q_toexit.draw(g2);
            light_ovenfire1.draw(g2,en3);
            light_ovenfire2.draw(g2,en3);
            light_ovenfire3.draw(g2,en3);
            light_ovenfire4.draw(g2,en3);

            light_doorr3.light_effect(en3,g2);
            light_floor1.draw(g2,en3);
            light_floor2.draw(g2,en3);
            if(password_pinn3.draws){
                g2.setColor(new Color(20,20,20));
                g2.fillRect(0,screenheight-(tilesize+30),screenwidth,tilesize*3);
            }
            backspace_icon.draw(g2);
            numpad_icon.draw(g2);
            password_pinn3.draw(g2);
            numpad_backspace.draw(g2);
            darkEffect_room3.draw_dark(g2);
        }else if(!end_room && room_3){

            beat.stop();
            object_draw player_last = players.draw(en_endroom);
            room.draw(en_endroom,g2);
            g2.drawImage(player_last.image,player_last.x -50,player_last.y + 140,player_last.tile_size1,player_last.tile_size2,null);
            lena.draw(g2);
            wood_background.draw(g2);
            room1_time.draw(g2);
            room2_time.draw(g2);
            room3_time.draw(g2);
            last_dialog1.draw(g2);
            last_dialog2.draw(g2);

            lightEffect.draw_bright(g2);
            exit.draw(g2);
        }
        g2.dispose();
    }
    public void draw_black_background(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.fillRect(-400-en.x,-500+en.y,600,2000);
        g2.fillRect(550-en.x,-500+en.y,600,2000);
        g2.fillRect(100-en.x,-500+en.y,600,500);
        g2.fillRect(100-en.x,670+en.y,600,500);
    }

    public void sort_obj() {
        object_draw swap;
        // sort
        for(int i=1;i < obj_draw.size();i++){
            for(int k=i-1;k > -1;k--){
                if(obj_draw.get(i-(i-(k+1))).y < obj_draw.get(k).y){
                    swap = obj_draw.get(i-(i-(k+1)));
                    obj_draw.set(i-(i-(k+1)),obj_draw.get(k));
                    obj_draw.set(k,swap);
                }
            }
        }

    }
}
