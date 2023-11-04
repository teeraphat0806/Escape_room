import java.lang.Math.*;
public class collison {

    collison(){

    }
    boolean check_collison(int[] player_row,int[] player_column,int[] obj_column,int[] obj_row){
        for(int i=0;i<player_column.length;i++){
            for(int k=0;k<obj_column.length;k++) {
                if (player_column[i] == obj_column[k] && player_row[i] == obj_row[k]) {
                    return true;
                }
            }
        }
        return false;
    }

}
