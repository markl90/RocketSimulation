package rocket;

public class U2 extends Rocket {

    private final float launchExplosionChance = 0.04f;
    private final float landingCrashChance = 0.08f;

    public U2() {

        super(120000000, 18000, 29000);
    }

    @Override
    public boolean launch(){
        return (launchExplosionChance * weight / maxWeight) < 1 ? true: false;
    }

    @Override
    public boolean land(){
        return (landingCrashChance * weight /maxWeight) < 1 ? true : false;
    }


}
