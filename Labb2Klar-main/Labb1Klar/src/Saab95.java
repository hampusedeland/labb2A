import java.awt.*;

public class Saab95 extends  Car {

    protected boolean turboOn;
    /** Konstruktor för Saab95
     * @param nrDoors antala dörrar
     * @param color färg
     * @param enginePower hästkrafter */
    public Saab95(Integer nrDoors, Color color, double enginePower, int weight) { //ett problem va att man behövde deklarera flera ggr för resp konstruktor
        super(nrDoors, color, enginePower, weight);
        turboOn = false;
        modelName = "Saab95";
    }

    /**
     * Sätter på turbo om metoden anropas
     */
    public void setTurboOn() {
        turboOn = true;

    }

    /**
     * Stänger av turbon om metoden anropas
     */
    public void setTurboOff() {
        turboOn = false;
    }

    /**
     * Beskriver hur farten ändras med turbon
     * @return enginePower*0.01*turbo
     */
    @Override
    public double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }

}