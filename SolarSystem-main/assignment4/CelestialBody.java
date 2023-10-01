package assignment4;

/**
 * Abstract class CelestialBody.
 * Star, Planet and Moon will extend this class.
 */

public abstract class CelestialBody {
  private String type;
  private String name;
  private long radius;
  private long orbitradius;

  /**
   * CelestialBody constructor.
   *
   * @param type
   *
   * @param name
   *
   * @param radius
   *
   * @param orbitradius
   *
   */

  public CelestialBody(String type, String name, long radius, long orbitradius) {
    super();
    this.type = type;
    this.name = name;
    this.radius = radius;
    this.orbitradius = orbitradius;
  }

  /**
   * Getter for the type of Body.
   *
   * @return
   *
   */

  public String getType() {
    return type;
  }

  /**
   * Setter for the type.
   *
   * @param type
   *
   */

  public void setType(String type) {
    this.type = type;
  }

  /**
   * Get name.
   *
   * @return
   *
   */

  public String getName() {
    return name;
  }

  /**
   * Setter for the Name.
   *
   * @param name
   *
   */

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the Radius.
   *
   * @return
   *
   */

  public long getRadius() {
    return radius;
  }

  /**
   * Setter for the Radius.
   *
   * @param radius
   *
   */

  public void setRadius(long radius) {
    this.radius = radius;
  }

  /**
   * Get OrbitRadius.
   *
   * @return
   *
   */

  public long getOrbitradius() {
    return orbitradius;
  }

  /**
   * SetOrbitRadius.
   *
   * @param orbitradius
   *
   */

  public void setOrbitradius(long orbitradius) {
    this.orbitradius = orbitradius;
  }

  /**
   * CelestialBody toString Method.
   */

  @Override
  public String toString() {
    return "Type: " + type + " || Name: " + name + " || Radius: " 
            + radius + " || Orbitradius: " + orbitradius + " \n\n ";
  }

  /**
   * CelestialBody clone Method.
   */

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
