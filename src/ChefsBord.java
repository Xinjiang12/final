import java.util.ArrayList;

public class ChefsBord extends Bord {
    String specialMessage;


    //Constructor
    public ChefsBord(ArrayList<Integer> time, int size, String specialMessage) {
        this.time = time;
        this.size = size;
        this.specialMessage = specialMessage;
    }

    //Setter
    public void setSpecialMessage(String specialMessage) {
        this.specialMessage = specialMessage;
    }
}

