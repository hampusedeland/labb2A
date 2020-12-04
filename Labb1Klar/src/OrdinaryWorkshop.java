import java.util.ArrayList;

//när man skapar ArrayListen så anger vad typvariabeln är och det påverkar hela klassen
//C

/** Öppnar en verkstad och objekten i verkstaden ärver från Car
 *
 * @param <C> den generiska typen C ärver från Car
 */
public class OrdinaryWorkshop<C extends Car>{
    private C carType; /*typparameter som extendar Car, bara bilar kan läggas till
        kan även ha en workshop som tar emot lastbil --> upperbound*/

    /** Gör en lista med inlämnade bilar i verkstan som är av typen C
     *
     */
    private ArrayList<C> loadedCars = new ArrayList<C>();
    //arraylist av typen C, defaultvärde
    private Integer maxCars;

    //konstruktorn ett argument, max cars

    /** En metod för att ange högst antal tillåtna bilar som får plats.
     *
     * @param maxCars max antal bilar
     */
    public OrdinaryWorkshop(int maxCars) {
        this.maxCars = maxCars;
    }

    /** Hämtar info om vart en specifik bil är
     * @param plats
     * @return
     */

    private String plats;
    public String getInfoSpecificCar(int plats){
        return "The car at spot: "+ plats + " is a: " + loadedCars.get(plats).modelName;
    }
    public void removeSpecificCar(int plats){
        Car removed = loadedCars.remove(plats);
        removed.setLoaded(false);

    }

    /** Lägger till bilar enligt angivelse: Om det finns plats och om det inte redan är i verkstan
     * @param bil
     */
    public void addToloadedCars(C bil){
        if( !bil.isLoaded()) {
            if (loadedCars.size() < maxCars) {
                loadedCars.add(bil);
                bil.setLoaded(true);
            }
        }
        else {
            throw new IllegalArgumentException("The car is already in another workshop, or this workshop");
        }
    }

    /**
     *
     * @return
     */
    public String removeFromLoadedCars(){
        if (loadedCars.size()>0){
            C furthestOutCar = loadedCars.get(loadedCars.size()-1);
            loadedCars.remove(loadedCars.size()-1);
            return (furthestOutCar.getClass().getName());
        }
        return "no cars";
    }

    /** Antal lastade bilar
     *
     */
    public Integer getNumberOfLoadedCars(){
        return loadedCars.size();
    }

}