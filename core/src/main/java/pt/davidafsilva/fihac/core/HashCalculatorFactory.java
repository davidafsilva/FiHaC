package pt.davidafsilva.fihac.core;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import pt.davidafsilva.fihac.core.hash.Md5HashCalculator;

/**
 * The factory for the creation of {@link HashCalculator} instances.
 *
 * This factory is a singleton, also, thread-safe.
 *
 * @author David Silva
 */
public final class HashCalculatorFactory {

  // the singleton object holder
  private static final class Holder {

    // the factory instance
    private static final HashCalculatorFactory instance = new HashCalculatorFactory();
  }

  // the default supplier when an unsupported algorithm is provided
  private static final Supplier<HashCalculator> INVALID_SUPPLIER = () -> {
    throw new IllegalArgumentException("Algorithm not supported");
  };

  // the algorithm mapping
  private final Map<HashAlgorithm, Supplier<HashCalculator>> algorithmSupplier;

  // private constructor
  private HashCalculatorFactory() {
    algorithmSupplier = new HashMap<>();
    initializeSuppliers();
  }

  /**
   * Initializes the hash algorithm suppliers
   */
  private void initializeSuppliers() {
    algorithmSupplier.put(HashAlgorithm.MD5, Md5HashCalculator::new);
  }

  /**
   * Returns the hash calculator factory instance.
   *
   * @return the singleton instance of the factory
   */
  public static HashCalculatorFactory getInstance() {
    return Holder.instance;
  }

  /**
   * Creates a new instance of the hash calculator for the specified algorithm.
   *
   * @param algorithm the hash algorithm
   * @return the instance of the specified hash algorithm
   */
  public HashCalculator create(final HashAlgorithm algorithm) {
    return algorithmSupplier.getOrDefault(algorithm, INVALID_SUPPLIER).get();
  }
}
