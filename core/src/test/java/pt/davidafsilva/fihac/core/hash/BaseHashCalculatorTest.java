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

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

/**
 * Unit tests for the {@link BaseHashCalculator} class.
 *
 * @author david
 */
public class BaseHashCalculatorTest {

  // the hash calculator
  private static BaseHashCalculator calculator;

  @BeforeClass
  public static void setup() {
    calculator = new BaseHashCalculator() {
      @Override
      Digest createHashAlgorithmInstance() {
        return new MD5();
      }
    };
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_invalidFile() throws IOException {
    final Path file = Files.createTempDirectory(BaseHashCalculatorTest.class.getSimpleName());
    calculator.calculate(file.toFile());
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_noReadPermissions() throws IOException {
    final Path file = Files.createTempFile(BaseHashCalculatorTest.class.getSimpleName(), ".tmp");
    assertTrue(file.toFile().setReadable(false));
    calculator.calculate(file.toFile());
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_noSuchFile() throws IOException {
    final Path file = Files.createTempFile(BaseHashCalculatorTest.class.getSimpleName(), ".tmp");
    assertTrue(file.toFile().delete());
    calculator.calculate(file.toFile());
  }

  @Test(expected = IOException.class)
  public void test_throwableFileChannel() throws IOException {
    final Path file = Files.createTempFile(BaseHashCalculatorTest.class.getSimpleName(), ".tmp");
    final BaseHashCalculator hashCalculator = mock(BaseHashCalculator.class, CALLS_REAL_METHODS);
    doThrow(IOException.class).when(hashCalculator).openFileChannel(any());
    doReturn(new MD5()).when(hashCalculator).createHashAlgorithmInstance();
    hashCalculator.calculate(file.toFile());
  }

  @Test(expected = IOException.class)
  public void test_throwableFileChannelClose() throws IOException {
    final Path file = Files.createTempFile(BaseHashCalculatorTest.class.getSimpleName(), ".tmp");
    final FileChannel fc = spy(FileChannel.open(file));
    doThrow(IOException.class).when(fc).close();
    final BaseHashCalculator hashCalculator = mock(BaseHashCalculator.class, CALLS_REAL_METHODS);
    doReturn(fc).when(hashCalculator).openFileChannel(any());
    doReturn(new MD5()).when(hashCalculator).createHashAlgorithmInstance();
    hashCalculator.calculate(file.toFile());
  }

  @Test(expected = IOException.class)
  public void test_ioErrorFileChannel() throws IOException {
    final Path file = Files.createTempFile(BaseHashCalculatorTest.class.getSimpleName(), ".tmp");
    final BaseHashCalculator hashCalculator = mock(BaseHashCalculator.class, CALLS_REAL_METHODS);
    doThrow(IOException.class).when(hashCalculator).openFileChannel(any());
    doReturn(new MD5()).when(hashCalculator).createHashAlgorithmInstance();
    hashCalculator.calculate(file.toFile());
  }

  @Test(expected = IOException.class)
  public void test_ioErrorFileChannelRead() throws IOException {
    final Path file = Files.createTempFile(BaseHashCalculatorTest.class.getSimpleName(), ".tmp");
    final FileChannel fc = spy(FileChannel.open(file));
    doThrow(IOException.class).when(fc).read(any(ByteBuffer.class));
    final BaseHashCalculator hashCalculator = mock(BaseHashCalculator.class, CALLS_REAL_METHODS);
    doReturn(fc).when(hashCalculator).openFileChannel(any());
    doReturn(new MD5()).when(hashCalculator).createHashAlgorithmInstance();
    hashCalculator.calculate(file.toFile());
  }

  @Test(expected = IOException.class)
  public void test_ioErrorFileChannelReadClose() throws IOException {
    final Path file = Files.createTempFile(BaseHashCalculatorTest.class.getSimpleName(), ".tmp");
    final FileChannel fc = spy(FileChannel.open(file));
    doThrow(IOException.class).when(fc).read(any(ByteBuffer.class));
    doThrow(Throwable.class).when(fc).close();
    final BaseHashCalculator hashCalculator = mock(BaseHashCalculator.class, CALLS_REAL_METHODS);
    doReturn(fc).when(hashCalculator).openFileChannel(any());
    doReturn(new MD5()).when(hashCalculator).createHashAlgorithmInstance();
    hashCalculator.calculate(file.toFile());
  }

  @Test
  public void test_hash_computation() throws IOException {
    final Path file = Files.createTempFile(BaseHashCalculatorTest.class.getSimpleName(), ".tmp");
    try (final Writer w = new FileWriter(file.toFile())) {
      w.write("The quick brown fox jumps over the lazy dog");
      w.flush();
    }
    assertEquals("9e107d9d372bb6826bd81d3542a419d6", calculator.calculate(file.toFile()));
  }
}
