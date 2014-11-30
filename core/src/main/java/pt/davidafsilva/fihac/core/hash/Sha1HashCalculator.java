package pt.davidafsilva.fihac.core.hash;

import com.saphir2.sphlib.Digest;
import com.saphir2.sphlib.SHA1;

/**
 * The SHA-1 hash algorithm calculator implementation.
 *
 * @author David Silva
 */

public class Sha1HashCalculator extends BaseHashCalculator {

  @Override
  Digest createHashAlgorithmInstance() {
    return new SHA1();
  }
}
