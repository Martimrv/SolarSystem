package assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Class with Menu, Loading File.
 */

public class Menu {
  private Scanner input = new Scanner(System.in, "UTF-8");
  private List<SolarSystem> solarSystemList;
  private String orderCriteria = "RADIUS";

  /**
   * Menu constructor.
   */

  public Menu() {
    solarSystemList = new ArrayList<>();
    // load all information from file
  }

  /**
   * Method to Load all data from solarsystem.data.
   */

  private void loadFile() {
    // read from file and insert in solar system list
    File data = new File("data/solarsystems.data");
    // "data\\solarsystem.data"

    try {
      @SuppressWarnings("resource")
      Scanner read = new Scanner(data, "UTF-8");
      while (read.hasNextLine()) {
        String line = read.nextLine();

        if (line.charAt(0) == '-') {
          if (line.contains("--")) {

            String[] elementos = line.replace("--", "").split(":");
            CelestialBody moon = new Moon(elementos[0], Long.parseLong(elementos[1]), Long.parseLong(elementos[2]));
            int idx = solarSystemList.get(SolarSystem.getCounter() - 1).planetsList.size();
            solarSystemList.get(SolarSystem.getCounter() - 1).planetsList.get(idx - 1).addMoonToPlanet((Moon) moon);
          } else {

            String[] elementos = line.replace("-", "").split(":");
            CelestialBody planet = new Planet(elementos[0], Long.parseLong(elementos[1]), Long.parseLong(elementos[2]));
            solarSystemList.get(SolarSystem.getCounter() - 1).addPlanetToSolarSystem((Planet) planet);
          }
        } else {
          // fazer para a star
          String[] elementos = line.split(":");
          Star star = new Star(elementos[0], Long.parseLong(elementos[1]));
          solarSystemList.add(new SolarSystem(star));
        }

      }

    } catch (FileNotFoundException e) {
      System.out.println("Error reading file:  " + e.getMessage());
    }
  }

  /**
   * Printing the Menu.
   */

  private void printMenu() {
    System.out.println("\n\n");
    System.out.println("************************************");
    System.out.println("1 - List all Solar Systems");
    System.out.println("2 - Select a particular Solar System and print it in a pretty way!");
    System.out.println("3 - Create a new Solar System");
    System.out.println("0 - Exit the application");
    System.out.println("************************************\n");
  }

  /**
   * Checking user option method.
   *
   * @param option
   *
   */

  private void checkInputOption(int option) {
    switch (option) {
      case 1:
        // list all solar system
        // System.out.println("Opcao 1");
        printAllSolarSystem();
        break;

      case 2:
        // Select Solar system
        // System.out.println("Opcao 2");
        selectSolarSystem();
        break;

      case 3:
        // Create Solar System
        // System.out.println("Opcao 3");
        createSolarSystem();
        break;

      case 0:
        System.out.println("Option 0 - Saving Solar System in the file");
        persistData();
        break;
      default:
        System.out.println("Please, insert a valid option");
        break;
    }
  }

  /**
   * Prints solarsystem.
   *
   * @param solarsystem
   *
   * @return
   *
   */

  private String solarSystemInfo(SolarSystem solarsystem) {
    String data = solarsystem.star.getName() + ":" + solarsystem.star.getRadius() + "\n";
    for (CelestialBody element : solarsystem.planetsList) {
      String line = null;
      if (element.getClass().equals(Planet.class)) {
        line = "-";
        line = line + element.getName() + ":" + element.getRadius() + ":" + element.getOrbitradius() + "\n";
        data += line;
        Planet planet = (Planet) element;

        for (Moon moon : planet.moonsList) {
          line = "--";
          line = line + moon.getName() + ":" + moon.getRadius() + ":" + moon.getOrbitradius() + "\n";
          data += line;
        }
      }

    }
    System.out.println(data);
    return data;
  }

  /**
   * Storing data again.
   */

  private void persistData() {
    FileWriter myWriter = null;
    try {
      String path = "data/solarsystems.data";
      // "data\\solarystem.data"
      myWriter = new FileWriter(path, StandardCharsets.UTF_8);
      if (!solarSystemList.isEmpty()) {
        for (SolarSystem element : solarSystemList) {
          myWriter.write(solarSystemInfo(element));
        }
      }
      System.out.println("Solar System saved in the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
    } finally {
      if (myWriter != null) {
        try {
          myWriter.flush();
        } catch (IOException e) {
          e.printStackTrace();
        }
        try {
          myWriter.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

    }
  }

  private void printAllSolarSystem() {

    int tot = 0;
    for (SolarSystem element : solarSystemList) {
      tot++;
      System.out.println("Solar System number " + tot + ": --> " + element.toString() + "\n\n");
    }

  }

  /**
   * Method to add a moon to a planet.
   * Works as a ´sub-menu´.
   *
   * @param planet
   *
   */

  private void addMoonToPlanet(Planet planet) {
    System.out.println("Please, insert the name of the Moon, a minimun Radius of 60"
        + " and an Orbit radius:\t");

    String moonName = input.next();
    long moonRadius;
    long moonOrbitradius;

    do {
      System.out.println("Radius has a minimun value of: " + Moon.MIN_RADIUS);
      moonRadius = input.nextLong();
      moonOrbitradius = input.nextLong();
    } while (!(moonRadius > Moon.MIN_RADIUS));

    Moon moon = new Moon(moonName, moonRadius, moonOrbitradius);
    planet.addMoonToPlanet(moon);
    System.out.println("Moon name: " + moonName + " , was added to Planet: " + planet.getName());

  }

  /**
   * Option to add a planet.
   *
   * @param solarSystem
   *
   */

  private void addPlanetsToSolarSystem(SolarSystem solarSystem) {
    System.out.println("Provide a Planet name, with a Radius between"
        + " 2000 and 200000 and  a minimum Orbit Radius of 18000");

    String planetName = input.next();
    long planetRadius;
    long planetOrbitradius;

    do {
      System.out
          .println("Please make sure the Radius is between " + Planet.MIN_RADIUS
              + "  and " + Planet.MAX_RADIUS + ""
              + " and Orbit Radius minimun of " + Planet.MIN_RADIUS_ORBIT);

      planetRadius = input.nextLong();
      planetOrbitradius = input.nextLong();

    } while (!(planetRadius > Planet.MIN_RADIUS && planetRadius < Planet.MAX_RADIUS
        && planetOrbitradius > Planet.MIN_RADIUS_ORBIT));

    Planet planet = new Planet(planetName, planetRadius, planetOrbitradius);

    System.out.println("You can choose how many Moons "
        + "you want to add to the Planet " + planetName);
    int option = input.nextInt();
    solarSystem.addPlanetToSolarSystem(planet);

    if (option > 0) {
      for (int i = 0; i < option; i++) {
        System.out.println("Number of Moon(s): " + (i + 1));
        addMoonToPlanet(planet);
      }
    }

    System.out.println("All elements were added :)");
  }

  /**
   * Creates a solarsystem.
   */

  private void createSolarSystem() {

    System.out.println("To create a Solar System you must provide"
        + " a Star name and a minimun Radius of 16700: ");

    String name = input.next();
    long radius = input.nextLong();
    Star star = new Star(name, radius);
    SolarSystem solarSystem = new SolarSystem(star);

    System.out.println("You can choose the number"
        + " of Planets you want to add: ");
    int option = input.nextInt();

    if (option > 0) {
      for (int i = 0; i < option; i++) {
        System.out.println("The number of Planet(s) and Moon(s) added: " + (i + 1));
        addPlanetsToSolarSystem(solarSystem);
      }
      solarSystemList.add(solarSystem);

      System.out.println("Number of Planets added : " + option);

    } else {
      System.out.println("Invalid number of Planets");
    }

  }

  /**
   * Method to select a particular solarsystem.
   */

  private void selectSolarSystem() {

    int option;
    while (true) {
      printAllSolarSystem();
      if (SolarSystem.getCounter() == 0) {
        System.out.println("\nThere are " + SolarSystem.getCounter()
            + " Solar Systems in the file.\t"
            + "Please, press  0 to Exit to the main menu and choose option 3 to create a new Solar System");
      } else {
        System.out.println("\nThere are " + SolarSystem.getCounter()
            + " Solar Systems in the file.\t"
            + "Please, select the number of the Solar System you want to see"
            + " in detail or 0 to Exit to the main menu");
      }
      option = input.nextInt();

      if (option == 0) {
        break;
      }

      if (option < 0 || option > SolarSystem.getCounter()) {
        System.out.println("Solar System does not exist.");
      } else {
        solarSystemSelected(option - 1);
        // System.out.println("test to find problem");
      }

    }

  }

  private void checkInputOptionForSelectedSolarSystem(int option, int solarsystemIndex) {
    switch (option) {
      case 1:

        addElementToSolarSystem(solarSystemList.get(solarsystemIndex));
        break;

      case 2:
        removeElementFromSolarSystem(solarSystemList.get(solarsystemIndex));
        break;

      case 3:
        solarSystemList.remove(solarsystemIndex);
        System.out.println("Solar System removed :(");
        SolarSystem.decrementCounter();
        break;

      case 4:
        if (orderCriteria.equals("RADIUS")) {
          orderCriteria = "ORBITRADIUS";
        } else {
          orderCriteria = "RADIUS";
        }

        System.out.println("Order criteria changed to " + orderCriteria);
        break;

      default:
        System.out.println("Exit Menu");
        break;
    }
  }

  /**
   * Method to add a planet or moon.
   *
   * @param solar
   *
   */

  private void addElementToSolarSystem(SolarSystem solar) {

    while (true) {
      System.out.println("\n\n");
      System.out.println("************************************");
      System.out.println("1 - Add a Planet");
      System.out.println("2 - Add a Moon");
      System.out.println("0 - Back to previous menu");
      System.out.println("************************************\n");

      int option = input.nextInt();

      if (option < 0 || option > 2) {
        System.out.println("Invalid option, please try again");
        continue;
      }
      checkInputOptionForAddingPlanetOrMoon(option, solar);

      // exit as zero
      if (option == 0) {
        break;
      }

    }

  }

  private void checkInputOptionForAddingPlanetOrMoon(int option, SolarSystem solar) {

    switch (option) {
      case 1:

        addPlanetsToSolarSystem(solar);
        break;

      case 2:
        System.out.println("Please, provide the name of the Planet that you want to add a Moon");
        String name = input.next();
        boolean foundPlanet = false;
        for (Planet planet : solar.planetsList) {
          if (planet.getName().equals(name)) {
            System.out.println("System found the Planet! Name: " + name);
            addMoonToPlanet(planet);
            foundPlanet = true;
            break;
          }
        }
        if (!foundPlanet) {
          System.out.println("The Planet " + name + " does not exist");
        }
        break;

      default:
        System.out.println("Exit menu");
        break;
    }

  }

  /**
   * Method to remove a SolarSystem element.
   *
   * @param solar
   *
   */

  private void removeElementFromSolarSystem(SolarSystem solar) {
    String elementName = null;
    System.out.println("Choose a Celestial Body name that you want to remove: ");
    elementName = input.next();

    int idxPlanet = 0;
    int idxMoon = 0;
    boolean found = false;
    for (Planet planet : solar.planetsList) {

      idxMoon = 0;
      if (planet.getName().equals(elementName)) {
        System.out.println("Found Planet with name: " + planet.getName());
        solar.planetsList.remove(idxPlanet);
        System.out.println("Planet removed and all respective moons!");
        found = true;
        break;
      }

      for (Moon moon : planet.moonsList) {
        if (moon.getName().equals(elementName)) {
          System.out.println("Found Moon with name: " + moon.getName() + " from Planet: " + planet.getName());
          planet.moonsList.remove(idxMoon);
          System.out.println("Moon removed!");
          found = true;
          break;

        }
        idxMoon++;
      }

      idxPlanet++;
    }

    if (!found) {
      System.out.println("Element with name: " + elementName + ", was not found!");
    }

  }

  /**
   * Select a SolarSystem.
   *
   * @param solarsystemIndex
   *
   */

  private void solarSystemSelected(int solarsystemIndex) {
    System.out.println("Solar System selected information: \n\n");

    sortSolarSystem(solarsystemIndex);

    // apart from the way that was asked on the assignment, added a "Pretty
    // printing"
    solarSystemList.get(solarsystemIndex).printAllSolarElementsPretty();

    System.out.println("Note: The Solar System is ordered by this criteria: " + orderCriteria);

    int option = 0;
    while (true) {
      System.out.println("\n\n");
      System.out.println("************************************");
      System.out.println("1 - Add an element");
      System.out.println("2 - Remove an element");
      System.out.println("3 - Delete a Star (and full SolarSystem)");
      System.out.println("4 - Change the order criteria");
      System.out.println("0 - Back to previous menu");
      System.out.println("************************************\n");

      option = input.nextInt();

      if (option < 0 || option > 4) {
        System.out.println("Option not available. Please choose available option");
        continue;
      }
      checkInputOptionForSelectedSolarSystem(option, solarsystemIndex);

      // exit as zero as always. or if all solarsystem goes bye bye
      if (option == 0 || option == 3) {
        break;
      }

    }
  }

  /**
   * Sorting SolarSystem Method.
   *
   * @param solarsystemIndex
   *
   */

  private void sortSolarSystem(int solarsystemIndex) {

    if (orderCriteria.equals("RADIUS")) {
      // ordenar
      List<Planet> celestialBodyList = solarSystemList.get(solarsystemIndex).planetsList;
      // usando Collections
      Collections.sort(celestialBodyList, Comparator.comparing(Planet::getRadius));
      // ver se os planetes t�m luas. se sim ordenar
      for (Planet planet : celestialBodyList) {
        Collections.sort(planet.moonsList, Comparator.comparing(Moon::getRadius));
      }

    } else {
      // ordenar
      List<Planet> celestialBodyList = solarSystemList.get(solarsystemIndex).planetsList;
      // usando Collections
      Collections.sort(celestialBodyList, Comparator.comparing(Planet::getOrbitradius));
      // ver se os planetes t�m luas. se sim ordenar
      for (Planet planet : celestialBodyList) {
        Collections.sort(planet.moonsList, Comparator.comparing(Moon::getOrbitradius));
      }
    }
  }

  /**
   * Run method.
   */

  public void run() {

    // if file exist. Load all solar systems
    loadFile();
    if (solarSystemList.isEmpty()) {
      System.out.println("There is no previous Solar System data");
    } else {
      System.out.println("Solar System data loaded ");
    }

    int option;
    // Scanner input = new Scanner(System.in);

    do {
      printMenu();

      option = input.nextInt();
      checkInputOption(option);

    } while (option != 0);

    input.close();
  }

}
