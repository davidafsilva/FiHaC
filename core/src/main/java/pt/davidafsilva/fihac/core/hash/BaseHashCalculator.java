package pt.davidafsilva.fihac.core.hash;

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

import com.saphir2.sphlib.Digest;

import org.apache.commons.codec.binary.Hex;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Objects;

import pt.davidafsilva.fihac.core.HashCalculator;

/**
 * The base hash calculator for all of the hash calculator concrete implementations.
 *
 * This abstract class provides a shared way of reading an arbitrary file contents.
 *
 * @author David Silva
 */
abstract class BaseHashCalculator implements HashCalculator {

  // the buffer size
  static final int BUFFER_SIZE = 1024 * 4;

  @Override
  public String calculate(final File file) throws IOException {
    Objects.requireNonNull(file, "invalid file");
    if (!file.exists() || !file.isFile()) {
      throw new IllegalArgumentException(String.format("no such file: %s", file.getAbsolutePath()));
    }
    if (!file.canRead()) {
      throw new IllegalArgumentException(
          String.format("no read access for %s", file.getAbsolutePath()));
    }

    // get the channel
    try (final FileChannel channel = openFileChannel(file)) {
      // allocate a buffer of #BUFFER_SIZE
      final ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

      // create the hash algorithm instance
      final Digest hash = createHashAlgorithmInstance();

      // iterate through the file and calculate the hash
      int len;
      while ((len = channel.read(buffer)) > 0) {
        hash.update(0, len, ((ByteBuffer) buffer.flip()).array());
      }

      // digest all of the byte data
      return Hex.encodeHexString(hash.digest());
    }
  }

  /**
   * Opens a channel for the specified file
   *
   * @param file the file path
   * @return the file channel for the specified file
   * @throws IOException if an I/O error occurs
   */
  FileChannel openFileChannel(final File file) throws IOException {
    return FileChannel.open(file.toPath());
  }

  /**
   * Creates an instance of the concrete hash algorithm to be used in the hash computation.
   *
   * @return the hash algorithm instance
   */
  abstract Digest createHashAlgorithmInstance();
}
