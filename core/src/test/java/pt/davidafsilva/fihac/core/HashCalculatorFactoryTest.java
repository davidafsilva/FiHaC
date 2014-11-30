package pt.davidafsilva.fihac.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the {@link HashCalculatorFactory} class.
 *
 * @author david
 */
public class HashCalculatorFactoryTest {

  @Test
  public void test_getInstance() {
    assertNotNull(HashCalculatorFactory.getInstance());
  }

  @Test
  public void test_singleton() {
    final HashCalculatorFactory factory = HashCalculatorFactory.getInstance();
    assertEquals(factory, HashCalculatorFactory.getInstance());
  }

  @Test
  public void test_algorithmCreation() {
    for (final HashAlgorithm algorithm : HashAlgorithm.values()) {
      final HashCalculator calculator = HashCalculatorFactory.getInstance().create(algorithm);
      assertNotNull(calculator);
      assertTrue(calculator.getClass().getSimpleName().toUpperCase().startsWith(algorithm.name()));
    }
  }
}
