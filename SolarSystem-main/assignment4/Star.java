package assignment4;

import java.util.HashSet;
import java.util.Set;

/**
 * Star Class is also a CelestialBody.
 * Star is in the SolarSystem centre.
 */

public class Star extends CelestialBody {
  public static final long MIN_RADIUS = 16700;

  private Set<String> planetsOrbit = new HashSet<>();

  /**
   * Star Constructor.
   *
   * @param name
   *
   * @param radius
   *
   */

  public Star(String name, long radius) {
    super("Star", name, radius, 0);
  }

  /**
   * Add Planet to the Star method.
   *
   * @param planet
   *
   */

  public void addPlanet(String planet) {
    planetsOrbit.add(planet);
  }
}