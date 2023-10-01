package assignment4;

import java.util.ArrayList;
import java.util.List;

/**
 * Planet class extends CelestialBody (Planet is a Celestial Body).
 */

public class Planet extends CelestialBody {
  public static final long MIN_RADIUS = 2000;
  public static final long MAX_RADIUS = 200000;

  public static final long MIN_RADIUS_ORBIT = 18000;

  /**
   * Planet Constructor.
   *
   * @param name
   *
   * @param radius
   *
   * @param orbitradius
   *
   */

  public Planet(String name, long radius, long orbitradius) {
    super("Planet", name, radius, orbitradius);
  }

  List<Moon> moonsList = new ArrayList<>();

  /**
   * Add a Moon to a Planet method.
   *
   * @param element
   *
   */

  public void addMoonToPlanet(Moon element) {
    moonsList.add(element);
  }

  /**
   * Remove a moon method.
   *
   * @param element
   *
   */

  public void removeMoon(Moon element) {
    moonsList.remove(element);
  }

  /**
   * Getter for the Moon.
   *
   * @return
   *
   */

  @Override
  public String toString() {
    return "Planet name: \n" + super.toString() + "\tMoons of this Planet: \n" + moonsList;
  }
}