import java.util.ArrayList;
import java.util.Scanner;

public class Bokning {

    //Listor för de olika typerna av bord, används för att boka.
    ArrayList<Bord> tables = new ArrayList<>();
    ArrayList<ChefsBord> chefsTables = new ArrayList<>();

    public void CreateResturant() {
        //Skapar borden som finns. for loop
        for (int i = 0; i < 5; i++) {
            ArrayList<Integer> time = new ArrayList<>();
            //Bokningsbara tider är 17-21.
            for (int a = 17; a < 22; a++) {
                time.add(a);
            }
            //skapar ett nytt bord med plats för fyra och bestämmer vilka tider som finns
            Bord table = new Bord(time, 4);
            //lägger till borden i arrayn
            tables.add(table);
        }
        //skapar chefs table.
        ArrayList<Integer> time = new ArrayList<>();
        //Chefs table startar 17 eller 19.
        time.add(17);
        time.add(19);
        ChefsBord chefsBord = new ChefsBord(time, 8, "");
        chefsTables.add(chefsBord);
        System();
    }
    //Meny bokningssystem.
    public void System() {
        Scanner scanner = new Scanner(System.in);
        Boolean isRunning = true;
        while (isRunning) {
            System.out.println("Welcome to our booking site!");
            while (true) {
                try {
                    boolean isChefsTable;
                    //Meny för val av sak att göra.
                    System.out.println("Select an action:\n1. Book a table.\n2. Book the chefs table.\n3. Exit.");
                    int answer = scanner.nextInt();
                    if (answer == 1) {
                        isChefsTable = false;
                        BookTable(isChefsTable);
                    } else if (answer == 2) {
                        isChefsTable = true;
                        BookTable(isChefsTable);
                    } else if (answer == 3) {
                        isRunning = false;
                        break;
                    } else {
                        System.out.println("Answer should be either 1, 2 or 3.");
                    }
                } catch (Exception e) {
                    System.out.println("Error.");
                    scanner.next();

                }
            }
        }
    }

    //Metod för att boka bordet.
    public void BookTable(boolean isChefsTable) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter the amount of people: ");
                int people = scanner.nextInt();
                System.out.println("Enter time (17-21 for normal tables, 17 or 19 for chefs): ");
                int time = scanner.nextInt();
                //X är ett nummer som kollar hur många bord som är lediga, bord kan flyttas.
                int x = 0;
                if (!isChefsTable) {
                    //Vid ett vanligt bord får det bara plats 4, du vill ju inte sitta med några andra.
                    int neededTables = people/4 + 1;
                    //For-each loop som kollar hur många lediga bord finns vid den valda lediga tiden.
                    for (Bord a : tables) {
                        ArrayList<Integer> availableTimes = a.getTime();
                        //Funktion som kollar om tiden du valt är ledig vid ett bord, "x < neededTables" ser till att inte fler bord än vad som behövs bokas.
                        if (availableTimes.contains(Integer.valueOf(time)) && x < neededTables) {
                            x++;
                        }
                    }
                    //Den nestående finns för att ett misslyckat försök att boka inte ska ta bort tider.
                    if (neededTables <= x) {
                        //Y funkar likt hur en variabel i en for-loop brukar funka, den ser till att bara mängden som ska tas bort faktiskt tas bort.
                        int y = x;
                        for (Bord a : tables) {
                            ArrayList<Integer> availableTimes = a.getTime();
                            //Funktion som kollar om tiden du valt är ledig vid ett bord, "x < neededTables" ser till att inte fler bord än vad som behövs bokas.
                            if (availableTimes.contains(Integer.valueOf(time)) && y > 0) {
                                y--;
                                //För att inga dubbelbokningar ska ske tas denna tid bort från listan med lediga tider hos bordet a.
                                availableTimes.remove(Integer.valueOf(time));
                                a.setTime(availableTimes);
                            }
                        }
                    }
                    //Kollar först om den inskrivna tiden stämmer eller inte, om allt stämmer bokas tiden.
                    if (!(time < 22 && time > 16)) {
                        System.out.println("Time selected does not exist.");
                    } else if (neededTables <= x && (time < 22 && time > 16)) {
                        System.out.println("You have booked a table at " + time + ":00 for " + people + ".");
                        System();
                    } else {
                        System.out.println("Not enough tables at the chosen time.");
                    }
                } else {
                    //Exakt likadan som den tidigare men för chefstable.
                    int neededTables = people/8 + 1; //Chefs table är 2x så stora.
                    //Du får lägga in ett eget meddelande.
                    System.out.println("Enter a special message: ");
                    String message = scanner.next();
                    for (ChefsBord a : chefsTables) {
                        ArrayList<Integer> availableTimes = a.getTime();
                        if (availableTimes.contains(Integer.valueOf(time)) && x < neededTables) {
                            x++;
                        }
                    }
                    if (neededTables <= x) {
                        int y = x;
                        for (ChefsBord a : chefsTables) {
                            ArrayList<Integer> availableTimes = a.getTime();
                            if (availableTimes.contains(Integer.valueOf(time)) && y > 0) {
                                y--;
                                availableTimes.remove(Integer.valueOf(time));
                                a.setSpecialMessage(message);
                                a.setTime(availableTimes);
                            }
                        }
                    }
                    if (!(time == 17 || time == 19)) {
                        System.out.println("Time selected does not exist.");
                    } else if (neededTables <= x && (time == 17 || time == 19)) {
                        System.out.println("You have booked a table at " + time + ":00 for " + people + ". Your special message is: " + message + ".");
                        System();
                    } else {
                        System.out.println("Not enough tables at the chosen time.");
                    }
                    System();
                }
            } catch (Exception e) {
                System.out.println("Error.");
            }
        }
    }

}

