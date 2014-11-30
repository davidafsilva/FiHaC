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
import com.saphir2.sphlib.MD5;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for the {@link Md5HashCalculator} class.
 *
 * @author david
 */
public class Md5HashCalculatorTest {

  @Test
  public void test_algorithmCreation() {
    final Md5HashCalculator md5HashCalculator = new Md5HashCalculator();
    final Digest digest = md5HashCalculator.createHashAlgorithmInstance();
    assertNotNull(digest);
    assertEquals(MD5.class, digest.getClass());
  }

  @Test
  public void test_hash_computation() throws IOException {
    final Path file = Files.createTempFile(Md5HashCalculatorTest.class.getSimpleName(), ".tmp");
    try (final Writer w = new FileWriter(file.toFile())) {
      w.write("The quick brown fox jumps over the lazy dog");
      w.flush();
    }
    assertEquals("9e107d9d372bb6826bd81d3542a419d6",
                 new Md5HashCalculator().calculate(file.toFile()));
  }
}
