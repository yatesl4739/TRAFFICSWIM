/**
 * Interface for colliding objects
 *
 * used for all cars
 * single boolean
 * @param <T>
 * @version 5/22/24
 */
public interface Collideable<T>
{
  boolean didCollide(Player other);
}
