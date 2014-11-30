package pt.davidafsilva.fihac.core.hash;

import com.saphir2.sphlib.Digest;
import com.saphir2.sphlib.Whirlpool;

/**
 * The Whirlpool hash algorithm calculator implementation.
 *
 * @author David Silva
 */

public class WhirlpoolHashCalculator extends BaseHashCalculator {

  @Override
  Digest createHashAlgorithmInstance() {
    return new Whirlpool();
  }
}
