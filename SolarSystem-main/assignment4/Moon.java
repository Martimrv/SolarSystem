package assignment4;

/**
 * Moon class which extends CelestialBody (Moon is a CelestialBody).
 */

public class Moon extends CelestialBody {
  public static final long MIN_RADIUS = 60;

  /**
   * Moon Constructor.
   *
   * @param name
   *
   * @param radius
   *
   * @param orbitradius
   *
   */

  public Moon(String name, long radius, long orbitradius) {
    super("Moon", name, radius, orbitradius);
  }

  /**
   * toString Method.
   */

  @Override
  public String toString() {
    return "Moon: " + super.toString();
  }
}
