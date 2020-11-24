import java.awt.*;

/** Gemensamma variabler för Car
 * nrDoors Number of doors on the car
 * enginePower Engine power of the car
 * currentSpeed The current speed of the car
 * color Color of the car
 * modelName The car model name
 * Car implements interface, klass Car förljer mallen som för en Moveble klass
 * initierar startpunktne för X och Y kordinaterna i origo, default position
 * x -kordinat
 * y -kordinat
 * currentdirection variable som initierar kurrent direction
 * SIDENOTE: allt är protected vilket är anledningen till att vi måste göra getter och setter för alla
 */
public class Car implements Movable {
    private int nrDoors;


    public void setEnginePower(double enginePower) {
        if(enginePower>=0) {
            this.enginePower = enginePower;
        }
    }

    private double enginePower;

    public void setCurrentSpeed(double currentSpeed) {
        if(currentSpeed<0) {
            throw new IllegalArgumentException("Currentspeed must be non negative (was:"+ currentSpeed + ")");
        }
        this.currentSpeed = currentSpeed;
        //throw new exception
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    private boolean isLoaded;
    private int weight;
    private double currentSpeed; // - samma här
    private Color color;
    protected String modelName;
    private double x = 0;
    private double y = 0;
    private String currentdirection; //nuvarande riktning, gjorde till protected pga getter och setter
    //ublic double x=x0; //ska vi ha x som public
    // public double y=y0;
    private String currentpos; //gjorde till protected

    /**
     * En konstruktor för bilar generellt med undernämnda parametrar
     * @param nrDoors
     * @param color
     * @param enginePower
     * + stop engine som är en gemensam för bilarna
     */
    protected Car(int nrDoors, Color color, double enginePower, int weight) { //throw om nrdoors är negativ
        if(nrDoors>=0) {
            this.nrDoors = nrDoors;
        }
        this.color = color;
        if(enginePower>=0) {
            this.enginePower = enginePower;
        }
        stopEngine();
        this.weight=weight;
        this.isLoaded=false;
    }



    /**
     * Denna är tillför testet
     * Behöver veta vilken modell för att kunna testa turbon
     * @return String
     */

    /**
     *
     */
    public void setTurboOn() {
    }
    /**
     *
     */
    /**
     *Funktion för x (getter för x)
     * @return X kordinaten
     */
    public double getX() {

        return x;
    }

    /**
     *Funktion för y (getter för y)
     * @return Y kordinaten
     */

    public double getY() {

        return y;
    }

    /**
     *skicka in argumentet x för att sätta bilens x-kordinat
     * @param x
     */
    public void setX(double x) {

        this.x = x;
    }

    /**
     * skicka in argumentet y för att sätta bilens y-kordinat
     * @param y
     */
    public void setY(double y) {

        this.y = y;
    }

    /**
     *String = nord/ost osv.. retunerar nuvarande string
     * @return String
     */
    public String getCurrentdirection() {

        return currentdirection;
    }

    /**
     * Settern för currentdirektion
     * skicka in argumentet currentdirection för att sätta bilens riktning i form av String
     * @param currentdirection
     */

    public void setCurrentdirection(String currentdirection) {

        this.currentdirection = currentdirection;
    }

    /**
     * Använder variablen nrDoors i metoden getNrDoors för att få antalet dörrar
     * @return int
     */
    public int getNrDoors(){

        return nrDoors;
    }

    /**
     *Använder variablen enginePower i metoden getEnginePower för att få kraften på motorn
     * @return double
     */
    public double getEnginePower(){

        return enginePower;
    }

    /**
     *Använder variablen currentSpeed i metoden getCurrentSpeed för att få hastigheten på bilen
     * @return double
     */
    public final double getCurrentSpeed(){
        return currentSpeed;
    }

    /**
     *Använder variablen color i metoden getColor för att få färgen på bilen
     * @return Color
     */
    public Color getColor(){
        return color;
    }

    /**
     *Settern för color
     * kicka in argumentet color för att sätta bilens färg i form av Color
     * @param clr
     */
    public void setColor(Color clr){
        color = clr;
    }

    /**
     *en sort utav flagga? För att visa att motorn har startat
     * förslagvis boolean?
     */

    public void startEngine(){
        currentSpeed = 0.1;
    }

    /**
     *en sort utav flagga? För att visa att motorn har stannat
     * förslagvis boolean?
     */
    public void stopEngine(){
        currentSpeed = 0;
    }


    /**
     *Om man är påväg norr så ökar Y med en positiv getCurrentSpeed så rör den sig i den positiv y-axelns riktning
     *Om man är påväg söder så ökar Y med en negativ getCurrentSpeed så rör den sig i den negativ y-axelns riktning
     *Om man är påväg söder så ökar X med en negativ getCurrentSpeed så rör den sig i den negativ x-axelns riktning
     *Om man är påväg norr så ökar X med en positiv getCurrentSpeed så rör den sig i den positiv x-axelns riktning
     */
    @Override
    public void move() {
        if(getCurrentdirection().equals("north")){
            setY(getY()+getCurrentSpeed());
        }
        if(getCurrentdirection().equals("south")){
            setY(getY()-getCurrentSpeed());
        }
        if(getCurrentdirection().equals("west")){
            setX(getX()-getCurrentSpeed());
        }
        if(getCurrentdirection().equals("east")){
            setX(getX()+getCurrentSpeed());
        }

    }

    /**
     *Om riktningen är norr så settar den riktningen åt väst för att få den att svänga vänster
     * osv....
     */
    @Override
    public void turnLeft() {
        if(getCurrentdirection().equals("north")){
            setCurrentdirection("west");
        }
        else if(getCurrentdirection().equals("west")){
            setCurrentdirection("south");
        }
        else if(getCurrentdirection().equals("south")){
            setCurrentdirection("east");
        }
        else if(getCurrentdirection().equals("east")){
            setCurrentdirection("north");
        }
    }

    /**
     * Om riktningen är norr så settar den riktningen åt öst för att få den att svänga höger
     */
    @Override
    public void turnRight() {
        if(getCurrentdirection().equals("north")){
            setCurrentdirection("east");
        }
        else if(getCurrentdirection().equals("east")){
            setCurrentdirection("south");
        }
        else if(getCurrentdirection().equals("south")){
            setCurrentdirection("west");
        }
        else if(getCurrentdirection().equals("west")){
            setCurrentdirection("north");
        }

    }

    /**
     * NY. "tom" speedFactor som subklasserna overridear
     * @return
     */
    public double speedFactor(){
        return 1;
    }

    /**
     * metoden anropas av Gas senare, och uppdaterar hastigheten.
     *Ser till så att hastigheten är realistiskt
     */


    public void incrementSpeed(double amount){
        if(getCurrentSpeed()>=0 && getCurrentSpeed()+ amount<=getEnginePower()) {
            currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
        }
    }
    /**
     * används för att uppdatera hastigheten vid en minskning, anropas av Break
     */

    public void decrementSpeed(double amount){
        if(getCurrentSpeed() - amount >=0 && getCurrentSpeed() <=getEnginePower()) {
            currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
        }
    }

    /**
     * Om amounten är mindre än eller lika med 1 och större än/lika med 0 så gasa
     */
    public void gas(double amount){
        if(amount<=1 && amount>=0){
            incrementSpeed(amount);
        }
    }

    /**
     * Om amounten är mindre än eller lika med 1 och större än/lika med 0 så bromsa med en faktor mellan 0 och 1
     */
    public void brake(double amount){
        if(amount<=1 && amount>=0) {
            decrementSpeed(amount);

        }
    }

    /**
     * Stänger av turbon
     *
     */
    public void setTurboOff() {

    }
}