import java.awt.*;

public class Main {
    public static void main (String [] args){
        OrdinaryWorkshop<Scania> HampusTwerkshop = new OrdinaryWorkshop<>(15);
        System.out.println(HampusTwerkshop.removeFromLoadedCars());
        HampusTwerkshop.addToloadedCars(new Scania(2, Color.BLUE,100,14000));
        System.out.println("Number loaded trucks " + HampusTwerkshop.getNumberOfLoadedCars());
        System.out.println(HampusTwerkshop.removeFromLoadedCars());


    }
}