package pt.davidafsilva.fihac.core.hash;

import com.saphir2.sphlib.Digest;
import com.saphir2.sphlib.Keccak512;

/**
 * The Keccak-512 hash algorithm calculator implementation.
 *
 * @author David Silva
 */

public class Keccak512HashCalculator extends BaseHashCalculator {

  @Override
  Digest createHashAlgorithmInstance() {
    return new Keccak512();
  }
}
