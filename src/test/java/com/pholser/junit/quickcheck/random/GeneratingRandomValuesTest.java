/*
 The MIT License

 Copyright (c) 2010-2012 Paul R. Holser, Jr.

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.pholser.junit.quickcheck.random;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GeneratingRandomValuesTest {
    private SourceOfRandomness source;
    @Mock private Random random;

    @Before
    public void beforeEach() {
        source = new SourceOfRandomness(random);
    }

    @Test
    public void delegatesToJDKForNextBoolean() {
        source.nextBoolean();

        verify(random).nextBoolean();
    }

    @Test
    public void delegatesToJDKForNextBytes() {
        byte[] bytes = new byte[1];

        source.nextBytes(bytes);

        verify(random).nextBytes(bytes);
    }

    @Test
    public void delegatesToJDKForNextDouble() {
        source.nextDouble();

        verify(random).nextDouble();
    }

    @Test
    public void delegatesToJDKForNextFloat() {
        source.nextFloat();

        verify(random).nextFloat();
    }

    @Test
    public void delegatesToJDKForNextGaussian() {
        source.nextGaussian();

        verify(random).nextGaussian();
    }

    @Test
    public void delegatesToJDKForNextInt() {
        source.nextInt();

        verify(random).nextInt();
    }

    @Test
    public void delegatesToJDKForNextIntFromZeroToN() {
        source.nextInt(2);

        verify(random).nextInt(2);
    }

    @Test
    public void delegatesToJDKForNextLong() {
        source.nextLong();

        verify(random).nextLong();
    }

    @Test
    public void delegatesToJDKForSetSeed() {
        source.setSeed(2L);

        verify(random).setSeed(2L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nextByteWithBackwardsRange() {
        source.nextByte((byte) 0, (byte) -1);
    }

    @Test
    public void nextByteWithIdenticalMinAndMax() {
        assertEquals((byte) -2, source.nextByte((byte) -2, (byte) -2));
    }

    @Test
    public void nextByteInRange() {
        when(random.nextDouble()).thenReturn(0.23245);

        byte value = source.nextByte((byte) 3, (byte) 5);

        verify(random).nextDouble();
        assertThat(value, lessThanOrEqualTo((byte) 5));
        assertThat(value, greaterThanOrEqualTo((byte) 3));
    }

    @Test
    public void nextByteAtLowEndOfRange() {
        when(random.nextDouble()).thenReturn(0D);

        byte value = source.nextByte(Byte.MIN_VALUE, Byte.MAX_VALUE);

        verify(random).nextDouble();
        assertEquals(Byte.MIN_VALUE, value);
    }

    @Test
    public void nextByteAtHighEndOfRange() {
        when(random.nextDouble()).thenReturn(0.9999999999999999);

        byte value = source.nextByte(Byte.MIN_VALUE, Byte.MAX_VALUE);

        verify(random).nextDouble();
        assertEquals(Byte.MAX_VALUE, value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nextShortWithBackwardsRange() {
        source.nextShort((short) 0, (short) -1);
    }

    @Test
    public void nextShortWithIdenticalMinAndMax() {
        assertEquals((short) -2, source.nextShort((short) -2, (short) -2));
    }

    @Test
    public void nextShortInRange() {
        when(random.nextDouble()).thenReturn(0.971234);

        short value = source.nextShort((short) 3, (short) 5);

        verify(random).nextDouble();
        assertThat(value, lessThanOrEqualTo((short) 5));
        assertThat(value, greaterThanOrEqualTo((short) 3));
    }

    @Test
    public void nextShortAtLowEndOfRange() {
        when(random.nextDouble()).thenReturn(0D);

        short value = source.nextShort(Short.MIN_VALUE, Short.MAX_VALUE);

        verify(random).nextDouble();
        assertEquals(Short.MIN_VALUE, value);
    }

    @Test
    public void nextShortAtHighEndOfRange() {
        when(random.nextDouble()).thenReturn(0.9999999999999999);

        short value = source.nextShort(Short.MIN_VALUE, Short.MAX_VALUE);

        verify(random).nextDouble();
        assertEquals(Short.MAX_VALUE, value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nextCharWithBackwardsRange() {
        source.nextChar('b', 'a');
    }

    @Test
    public void nextCharWithIdenticalMinAndMax() {
        assertEquals('c', source.nextChar('c', 'c'));
    }

    @Test
    public void nextCharInRange() {
        when(random.nextDouble()).thenReturn(0.002341);

        char value = source.nextChar('d', 'f');

        verify(random).nextDouble();
        assertThat(value, lessThanOrEqualTo('f'));
        assertThat(value, greaterThanOrEqualTo('d'));
    }

    @Test
    public void nextCharAtLowEndOfRange() {
        when(random.nextDouble()).thenReturn(0D);

        char value = source.nextChar(Character.MIN_VALUE, Character.MAX_VALUE);

        verify(random).nextDouble();
        assertEquals(Character.MIN_VALUE, value);
    }

    @Test
    public void nextCharAtHighEndOfRange() {
        when(random.nextDouble()).thenReturn(0.9999999999999999);

        char value = source.nextChar(Character.MIN_VALUE, Character.MAX_VALUE);

        verify(random).nextDouble();
        assertEquals(Character.MAX_VALUE, value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nextIntWithBackwardsRange() {
        source.nextInt(0, -1);
    }

    @Test
    public void nextIntWithIdenticalMinAndMax() {
        assertEquals(-2, source.nextInt(-2, -2));
    }

    @Test
    public void nextIntInRange() {
        when(random.nextDouble()).thenReturn(0.34);

        int value = source.nextInt(3, 5);

        verify(random).nextDouble();
        assertThat(value, lessThanOrEqualTo(5));
        assertThat(value, greaterThanOrEqualTo(3));
    }

    @Test
    public void nextIntAtLowEndOfRange() {
        when(random.nextDouble()).thenReturn(0D);

        int value = source.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);

        verify(random).nextDouble();
        assertEquals(Integer.MIN_VALUE, value);
    }

    @Test
    public void nextIntAtHighEndOfRange() {
        when(random.nextDouble()).thenReturn(0.9999999999999999);

        int value = source.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);

        verify(random).nextDouble();
        assertEquals(Integer.MAX_VALUE, value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nextLongWithBackwardsRange() {
        source.nextLong(0L, -1L);
    }

    @Test
    public void nextLongWithIdenticalMinAndMax() {
        assertEquals(-2L, source.nextLong(-2L, -2L));
    }

    @Test
    public void nextLongInRange() {
        when(random.nextDouble()).thenReturn(0.654);

        long value = source.nextLong(3L, 5L);

        verify(random).nextDouble();
        assertThat(value, lessThanOrEqualTo(5L));
        assertThat(value, greaterThanOrEqualTo(3L));
    }

    @Test
    public void nextLongAtLowEndOfRange() {
        when(random.nextDouble()).thenReturn(0D);

        long value = source.nextLong(Long.MIN_VALUE, Long.MAX_VALUE);

        verify(random).nextDouble();
        assertEquals(Long.MIN_VALUE, value);
    }

    @Test
    public void nextLongAtHighEndOfRange() {
        when(random.nextDouble()).thenReturn(0.9999999999999999);

        long value = source.nextLong(Long.MAX_VALUE - 1, Long.MAX_VALUE);

        verify(random).nextDouble();
        assertEquals(Long.MAX_VALUE, value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nextFloatWithBackwardsRange() {
        source.nextFloat(0.2F, 0.1F);
    }

    @Test
    public void nextFloatWithIdenticalMinAndMax() {
        assertEquals(-2F, source.nextFloat(-2F, -2F), 0F);
    }

    @Test
    public void nextFloatInRange() {
        when(random.nextDouble()).thenReturn(0.3343443);

        float value = source.nextFloat(-4.56F, -1.234234F);

        verify(random).nextDouble();
        assertThat(value, lessThanOrEqualTo(-1.234234F));
        assertThat(value, greaterThanOrEqualTo(-4.56F));
    }

    @Test
    public void nextFloatAtLowEndOfRange() {
        when(random.nextDouble()).thenReturn(0D);

        float value = source.nextFloat(Float.MIN_VALUE, Float.MAX_VALUE);

        verify(random).nextDouble();
        assertEquals(Float.MIN_VALUE, value, 0F);
    }

    @Test
    public void nextFloatAtHighEndOfRange() {
        when(random.nextDouble()).thenReturn(0.9999999999999999);

        float value = source.nextFloat(Float.MIN_VALUE, Float.MAX_VALUE);

        verify(random).nextDouble();
        assertEquals(Float.MAX_VALUE, value, 0F);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nextDoubleWithBackwardsRange() {
        source.nextDouble(0.2, 0.1);
    }

    @Test
    public void nextDoubleWithIdenticalMinAndMax() {
        assertEquals(-2D, source.nextDouble(-2D, -2D), 0D);
    }

    @Test
    public void nextDoubleInRange() {
        when(random.nextDouble()).thenReturn(0.45);

        double value = source.nextDouble(-4.56, -1.234234);

        verify(random).nextDouble();
        assertThat(value, lessThanOrEqualTo(-1.234234));
        assertThat(value, greaterThanOrEqualTo(-4.56));
    }

    @Test
    public void nextDoubleAtLowEndOfRange() {
        when(random.nextDouble()).thenReturn(0D);

        double value = source.nextDouble(Double.MIN_VALUE, Double.MAX_VALUE);

        verify(random).nextDouble();
        assertEquals(Double.MIN_VALUE, value, 0D);
    }

    @Test
    public void nextDoubleAtHighEndOfRange() {
        when(random.nextDouble()).thenReturn(0.9999999999999999);

        double value = source.nextDouble(Double.MAX_VALUE - 1E292, Double.MAX_VALUE);

        verify(random).nextDouble();
        assertEquals(Double.MAX_VALUE, value, 0D);
    }

    @Test
    public void nextBytes() {
        source.nextBytes(1);

        verify(random).nextBytes(new byte[1]);
    }
}
