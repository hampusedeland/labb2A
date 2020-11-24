import java.awt.*;

public class Scania extends Car{
    /**
     * En konstruktor för Scania lastbil generellt med undernämnda parametrar
     *
     * @param nrDoors
     * @param color
     * @param enginePower
     */
    protected Scania(int nrDoors, Color color, double enginePower, int weight) {
        super(nrDoors, color, enginePower, weight);
        modelName = "Scania340";
    }
    /**default vnikel
     */
    private double angleTrBed=0;

    public double getAngleTrBed() {
        return angleTrBed;
    }

    /** Om vinkeln på flaket ligger i intervallet [0,70] och Scania-bilen är still, sätt vinkeln
     *
     * @param angleTrBed godtycklig vinkel i intervallet
     */
    public void setAngleTrBed(double angleTrBed) {
        if(getAngleTrBed()>=0 && getAngleTrBed()<=70 && getCurrentSpeed()==0) { //maxvinkeln och om lastbilen är still
            this.angleTrBed = angleTrBed;
        }
        else {
            throw new IllegalArgumentException("Make sure the Truck isn't moving, and the designated angle for the truck bed is between 0-70 degrees");
        }
    }

    /** Bilen ska bara kunna röra på sig när flaket har vinkeln 0 grader
     *
     */
    @Override
    public void move(){
        if(getAngleTrBed()==0){
            super.move();
        }
        else{
            throw new IllegalArgumentException("For the truck to move, the truck bed must have a zero angle, the angle (was:"+ getAngleTrBed() + ")");
        }
    }
}