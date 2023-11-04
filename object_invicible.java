public class object_invicible {
    public int[] x;// column
    public int[] y;// row
    public int[] row;
    public int[] column;

    object_invicible(int[]x,int[]y,int[] row,int[] column){
        this.x = new int[x.length];
        this.y = new int[y.length];
        this.row = new int[x.length];
        this.column = new int[y.length];
        for(int i=0;i < x.length;i++){
            this.x[i] = x[i];
            this.y[i] = y[i];
            this.row[i] = row[i];
            this.column[i] = column[i];
        }

    }

    public void update(entity en){
        int[] x = new int[this.x.length];
        int[] y = new int[this.y.length];
        for(int i=0;i < x.length;i++){
            x[i] = this.x[i] - (en.x);
            y[i] = this.y[i] - (en.y);
        }

        for(int i=0;i<x.length;i++){
            column[i] = (int)Math.floor((double) (x[i] + 24) /48);
            row[i] = (int)Math.floor((double) (y[i] + 24) /48);
        }

    }
}
