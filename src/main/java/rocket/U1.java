package rocket;

import java.util.Random;

public class U1 extends Rocket {

    private final float launchExplosionChance = 0.05f;
    private final float landingCrashChance = 0.01f;


    public U1() {
        super(100000000, 10000, 18000);
    }

    @Override
    public boolean launch(){
        Random random = new Random();
        return random.nextInt(100)>(launchExplosionChance*100);
    }

    @Override
    public boolean land(){
        Random random = new Random();
        return random.nextInt(100)>(landingCrashChance*100);
    }
}
