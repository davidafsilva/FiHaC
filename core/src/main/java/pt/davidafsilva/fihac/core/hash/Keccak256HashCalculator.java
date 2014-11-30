package pt.davidafsilva.fihac.core.hash;

import com.saphir2.sphlib.Digest;
import com.saphir2.sphlib.Keccak256;

/**
 * The Keccak-256 hash algorithm calculator implementation.
 *
 * @author David Silva
 */

public class Keccak256HashCalculator extends BaseHashCalculator {

  @Override
  Digest createHashAlgorithmInstance() {
    return new Keccak256();
  }
}
