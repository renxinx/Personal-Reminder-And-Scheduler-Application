package problem1.model;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

/**
 * The class Priority comparator implements Comparator,
 * using to compare todo items with different priorities.
 */
public class PriorityComparator implements Comparator<ToDo> {

  private Map<Integer, ToDo> map;

  /**
   * Instantiates a new Priority comparator.
   *
   * @param map the map
   */
  public PriorityComparator(Map<Integer, ToDo> map) {
    this.map = map;
  }

  /**
   * compare in interface Comparator, using to compare priority of 2 objects.
   *
   * @param t1 – the first object to be compared.
   * @param t2 – the second object to be compared.
   */
  @Override
  public int compare(ToDo t1, ToDo t2) {
    return t1.getPriority().compareTo(t2.getPriority());
  }

  /**
   * Return true if Object o equals to the object.
   *
   * @param o the object to compare with the current object.
   * @return true if the field is same for both objects, otherwise, return false.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PriorityComparator that = (PriorityComparator) o;
    return Objects.equals(map, that.map);
  }

  /**
   * Return the hashCode of this object.
   *
   * @return the hashCode of this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(map);
  }

  /**
   * Return the string representation of the object.
   *
   * @return the string representation of the object.
   */
  @Override
  public String toString() {
    return "PriorityComparator{" +
        "map=" + map +
        '}';
  }
}
