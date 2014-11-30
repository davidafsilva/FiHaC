package pt.davidafsilva.fihac.core;

/*
 * #%L
 * FiHaC
 * %%
 * Copyright (C) 2014 David Silva
 * %%
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the David Silva nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import pt.davidafsilva.fihac.core.hash.Md5HashCalculator;
import pt.davidafsilva.fihac.core.hash.Sha1HashCalculator;
import pt.davidafsilva.fihac.core.hash.Sha256HashCalculator;
import pt.davidafsilva.fihac.core.hash.WhirlpoolHashCalculator;

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
    algorithmSupplier.put(HashAlgorithm.WHIRLPOOL, WhirlpoolHashCalculator::new);
    algorithmSupplier.put(HashAlgorithm.SHA1, Sha1HashCalculator::new);
    algorithmSupplier.put(HashAlgorithm.SHA256, Sha256HashCalculator::new);
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
