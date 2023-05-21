import java.util.ArrayList;

public class Bord {
    //Lista för de bokade tiderna.
    ArrayList<Integer> time = new ArrayList<>();
    int size; //Mängden människor som får plats vid 1 bord.

    //Generisk constructor
    public Bord() {}

    //Constructor
    public Bord(ArrayList<Integer> time, int size) {
        this.time = time;
        this.size = size;
    }

    //Getter
    public ArrayList<Integer> getTime() {
        return time;
    }

    //Setter
    public void setTime(ArrayList<Integer> time) {
        this.time = time;
    }
}


