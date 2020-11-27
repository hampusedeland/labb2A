import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel<image> extends JPanel{

    // Just a single image, TODO: Generalize
    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage scaniaImage;
    // To keep track of a singel cars position
    Point volvoPoint = new Point();
    Point saabPoint  = new Point();
    Point scaniaPoint = new Point();
    Point carPoint = new Point();
    HashMap<String , BufferedImage > carimages = new HashMap();

    private CarController cc;
    private Car carRepresentation;
    // TODO: Make this genereal for all cars
    void moveit(int x, int y,Car car){   ///Kanske får hämta in en bil, för att represetera bilens position.
        volvoPoint.x = x;
        volvoPoint.y = y;

        //carPoint.x = (int) car.getX();
        saabPoint.x=x;
        saabPoint.y=y;

        scaniaPoint.x=x;
        scaniaPoint.y=y;

    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.black);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        //HashMap carimages = new HashMap();
        carimages.put("Volvo240", volvoImage);
        carimages.put("Saab95", saabImage);
        carimages.put("Scania340", scaniaImage);

    }
    public void paintCar(Car c) {

        this.carRepresentation = c;
        this.repaint();
    }
    public void makingHash() {

    }
    //göra map map.get(modelname)
    private ArrayList<Car> cars= new ArrayList();
    public void getArrayList(ArrayList<Car> carsFromAnothermama){
        cars= carsFromAnothermama;
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
            for(Car car : cars){
                g.drawImage(carimages.get(car.modelName),(int)car.getX(),(int)car.getY(),null);
            }
           /* if(carRepresentation.modelName.contains("Volvo240")) {
                g.drawImage(volvoImage, (int) carRepresentation.getX(), (int) carRepresentation.getY(), null);
            }
            if(carRepresentation.modelName.contains("Saab95")) {
                g.drawImage(saabImage,  (int)carRepresentation.getX(), (int) carRepresentation.getY() + 100, null);
            }
            if(carRepresentation.modelName.contains("Scania340")) {
                g.drawImage(scaniaImage,  (int) carRepresentation.getX(), (int) carRepresentation.getY() + 200, null);// see javadoc for more info on the parameters
            }
            */
    }
}
