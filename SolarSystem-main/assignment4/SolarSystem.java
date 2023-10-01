package assignment4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SolarSystem class.
 */

public class SolarSystem {
  private static AtomicInteger counter = new AtomicInteger(0);
  CelestialBody star;
  List<Planet> planetsList = new ArrayList<>();

  /**
   * Constructor.
   *
   * @param star
   *
   */

  public SolarSystem(CelestialBody star) {
    counter.incrementAndGet();
    //SolarSystem.counter++;
    this.star = new Star(star.getName(), star.getRadius()); 
  }

  public void addPlanetToSolarSystem(Planet element) {
    planetsList.add(element);
  }

  public void removePlanetFromSolarSystem(Planet element) {
    planetsList.remove(element);
  }

  public static int getCounter() {
    return SolarSystem.counter.get();
  }

  public static void decrementCounter() {
    SolarSystem.counter.decrementAndGet();
    //counter--;
  }

  @Override
  public String toString() {
    return "Star: " + star + "All Planets of this Solar System: \n\n" + planetsList;
  }

  /**
   * Method to Print SolarSystems in a prettier way.
   */

  public void printAllSolarElementsPretty() {
    System.out.println("*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+ ");
    System.out.println(" \t\t * Star: " + star.getName() + " *\n");
    for (Planet planet : planetsList) {
      System.out.println(" \t Planet: " + planet.getName() + " | Radius: " 
                        + planet.getRadius() + " | OrbitRadius: " + planet.getOrbitradius());
      for (Moon moon : planet.moonsList) {
        System.out.print("Moon: " + moon.getName() + " \t");
      }
      System.out.println("\n");
    }
    System.out.println("*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+");

  }

}
