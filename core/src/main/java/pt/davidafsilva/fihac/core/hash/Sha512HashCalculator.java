package pt.davidafsilva.fihac.core.hash;

import com.saphir2.sphlib.Digest;
import com.saphir2.sphlib.SHA512;

/**
 * The SHA-512 hash algorithm calculator implementation.
 *
 * @author David Silva
 */

public class Sha512HashCalculator extends BaseHashCalculator {

  @Override
  Digest createHashAlgorithmInstance() {
    return new SHA512();
  }
}
