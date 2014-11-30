package pt.davidafsilva.fihac.core.hash;

import com.saphir2.sphlib.Digest;
import com.saphir2.sphlib.SHA256;

/**
 * The SHA-256 hash algorithm calculator implementation.
 *
 * @author David Silva
 */

public class Sha256HashCalculator extends BaseHashCalculator {

  @Override
  Digest createHashAlgorithmInstance() {
    return new SHA256();
  }
}
