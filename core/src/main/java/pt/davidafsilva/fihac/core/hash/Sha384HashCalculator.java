package pt.davidafsilva.fihac.core.hash;

import com.saphir2.sphlib.Digest;
import com.saphir2.sphlib.SHA384;

/**
 * The SHA-384 hash algorithm calculator implementation.
 *
 * @author David Silva
 */

public class Sha384HashCalculator extends BaseHashCalculator {

  @Override
  Digest createHashAlgorithmInstance() {
    return new SHA384();
  }
}
