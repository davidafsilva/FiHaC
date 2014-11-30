package pt.davidafsilva.fihac.core.hash;

import com.saphir2.sphlib.Digest;
import com.saphir2.sphlib.Keccak384;

/**
 * The Keccak-384 hash algorithm calculator implementation.
 *
 * @author David Silva
 */

public class Keccak384HashCalculator extends BaseHashCalculator {

  @Override
  Digest createHashAlgorithmInstance() {
    return new Keccak384();
  }
}
