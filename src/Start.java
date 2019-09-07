

import java.util.Scanner;

import controller.RouteHelper;
import model.Route;

public class Start {
    
    static RouteHelper rh = new RouteHelper();
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

       Route r = new Route("Waste Knot", "5.8", false);
       rh.insertRoute(r);
        
    }

}
