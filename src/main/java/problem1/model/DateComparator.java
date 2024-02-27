package problem1.model;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

/**
 * The class Date comparator implements Comparator,
 * using to compare todo items with different dates.
 */
public class DateComparator implements Comparator<ToDo> {

  private Map<Integer, ToDo> map;

  /**
   * Instantiates a new Date comparator.
   *
   * @param map the map
   */
  public DateComparator(Map<Integer, ToDo> map) {
    this.map = map;
  }

  /**
   * compare in interface Comparator, using to compare date of 2 objects.
   *
   * @param t1 – the first object to be compared.
   * @param t2 – the second object to be compared.
   */
  @Override
  public int compare(ToDo t1, ToDo t2) {
    if (t1.getDue() == null && t2.getDue() == null) {
      return 0;
    } else if(t1.getDue() == null) {
      return 1;
    } else if(t2.getDue() == null) {
      return -1;
    } else {
      return t1.getDue().compareTo(t2.getDue());
    }
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
    DateComparator that = (DateComparator) o;
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
    return "DateComparator{" +
        "map=" + map +
        '}';
  }
}
