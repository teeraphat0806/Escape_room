import java.util.Dictionary;
import java.util.Hashtable;
public class record_time {
    private Dictionary<String,Long> time = new Hashtable<>();
    private long start_time = 0;
    private long end_time = 0;
    private boolean is_active = false;
    public void start_time(){
        start_time = System.currentTimeMillis();
        is_active = true;
    }
    public void end_time(){
        end_time = System.currentTimeMillis();
        is_active = false;
        time.put("hour",get_hour());
        time.put("minute",get_minute());
        time.put("second",get_second());
    }
    public void restart_time(){
        start_time = 0;
        end_time = 0;
    }
    public long get_hour(){
        if(!is_active){
            return ((this.end_time - this.start_time)/1000)/3600; }
        else{
            return -1;
        }
    }
    public long get_minute(){
        if(!is_active){
            return ((this.end_time - this.start_time)/1000)/60; }
        else {
            return -1;
        }
    }
    public long get_second(){
        if(!is_active){
            return ((this.end_time - this.start_time)/1000); }
        else {
            return -1;
        }
    }
}
