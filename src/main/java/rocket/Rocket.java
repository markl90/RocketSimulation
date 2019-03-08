package rocket;

import payload.Item;
import rocket.SpaceShip;

public abstract class Rocket implements SpaceShip {

    protected long cost;
    protected int weight;
    protected int maxWeight;


    public Rocket(long cost, int weight, int maxWeight) {
        this.cost = cost;
        this.weight = weight;
        this.maxWeight = maxWeight;

    }

    public Rocket(){}

    public boolean launch() {
        return true;
    }

    public boolean land() {
        return true;
    }

    public boolean canCarry(Item item) {
        if(item.getWeight() + weight < maxWeight){
            return true;
        }
        return false;
    }

    public void carry(Item item) {
            weight += item.getWeight();
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }
}
