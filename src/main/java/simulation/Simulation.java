package simulation;

import payload.Item;
import rocket.Rocket;
import rocket.U1;
import rocket.U2;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Simulation {

    private File file;
    private Scanner scanner;
    private ArrayList items;

    public Simulation(){

    }

    public ArrayList<Item> loadItems(String materials){
        items = new ArrayList<Item>();
        file = new File(ClassLoader.getSystemResource(materials).getFile());
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        addItemsToList();
        return items;
    }

    public void addItemsToList(){
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] nameWeight = line.split("=");
            Item itemToAdd = new Item(nameWeight[0], Integer.valueOf(nameWeight[1]));
            System.out.println("adding" +itemToAdd.toString());
            items.add(itemToAdd);
        }
    }

    public ArrayList<Rocket> loadU1(ArrayList<Item> items){
        int capacity = 0;
        int itemWeight = 0;
        ArrayList<Rocket> U1Rockets = new ArrayList<>();

        for(Item item : items) {
            itemWeight += item.getWeight();
        //    System.out.println("unloading: "+ item.getName()+ ".....");
         //   System.out.println("total cargo now: " + itemWeight + "Kg");

            while (capacity < itemWeight) {
                Rocket U1 = new U1();
                capacity += U1.getMaxWeight() - U1.getWeight();
                U1Rockets.add((U1) U1);
             //   System.out.println("capacity: " + capacity +"Kg... " + "added "+ U1Rockets.size() + " rockets" );
            }

        }
        System.out.println(U1Rockets.size()+ " total "+ U1.class + " rockets to carry payload.");
        return U1Rockets;
    }

    public ArrayList<Rocket> loadU2(ArrayList<Item> items){
        int capacity = 0;
        int itemWeight = 0;
        ArrayList<Rocket> U2Rockets = new ArrayList<>();

        for(Item item : items) {
            itemWeight += item.getWeight();
          //  System.out.println("unloading: "+ item.getName()+ ".....");
         //   System.out.println("total cargo now: " + itemWeight + "Kg");

            while (capacity < itemWeight) {
                Rocket U2 = new U2();
                capacity += U2.getMaxWeight() - U2.getWeight();
                U2Rockets.add((U2) U2);
               // System.out.println("capacity: " + capacity +"Kg... " + "added "+ U2Rockets.size() + " rockets" );
            }

        }
        System.out.println(U2Rockets.size()+ " total "+ U2.class + " rockets to carry payload.");
        return U2Rockets;
    }

    private long cost;
    private int crashes;
    private int totalLaunches;
    public void runSimulation(ArrayList<Rocket> rockets, Rocket type){
        ArrayList<Rocket> relaunches = new ArrayList<>();
        for(Rocket rocket: rockets){
            cost += rocket.getCost();
            totalLaunches++;

            if (!rocket.launch() || !rocket.land()){
                crashes++;
                relaunches.add(type);
            }
        }
        if(relaunches.size()>0) {
            runSimulation(relaunches, type);
        }
        else {
            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

            System.out.println("rocket.Rocket " + type.getClass());
            System.out.println("crashes:" + crashes);
            System.out.println("Total launches " + totalLaunches);
            System.out.println("Cost " + formatter.format(cost) + "\n\n");
            cost=0;
            crashes=0;
            totalLaunches=0;
        }
    }


    public static void main(String[] args) {
        Simulation simulation = new Simulation();

        simulation.runSimulation((simulation.loadU1(simulation.loadItems("phase1.txt"))),new U1());
        simulation.runSimulation((simulation.loadU2(simulation.loadItems("phase1.txt"))),new U2());

        simulation.runSimulation((simulation.loadU1(simulation.loadItems("phase2.txt"))),new U1());
        simulation.runSimulation((simulation.loadU2(simulation.loadItems("phase2.txt"))),new U2());
    }


}
