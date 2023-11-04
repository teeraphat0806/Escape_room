import java.lang.Math.*;
public class door {
    public int[] x={288,336,384}; // column
    public int[] y={264,264,264}; // row
    public int[] row={6,6,6};
    public int[] column={6,7,8};


    door(){

    }
    public void update(entity en){
        int[] x = new int[3];
        int[] y = new int[3];

        x[0] = this.x[0] - (en.x);
        x[1] = this.x[1] - (en.x);
        x[2] = this.x[2] - (en.x);
        y[0] = (int) (this.y[0] + (en.y));
        y[1] = (int) (this.y[1] + (en.y));
        y[2] = (int) (this.y[2] + (en.y));
        for(int i=0;i<3;i++){
            column[i] = (int)Math.floor((double) (x[i] + 24) /48);
            row[i] = (int)Math.floor((double) (y[i] + 24) /48);
        }
    }
}
