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

package com.pholser.junit.quickcheck.generator;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.pholser.junit.quickcheck.internal.generator.GeneratingUniformRandomValuesForTheoryParameterTest;

import static com.pholser.junit.quickcheck.Strings.*;
import static java.util.Arrays.*;
import static org.mockito.Mockito.*;

public class BigDecimalTest extends GeneratingUniformRandomValuesForTheoryParameterTest {
    @Override
    protected void primeSourceOfRandomness() {
        when(randomForParameterGenerator.nextBytes(0)).thenReturn(new byte[0]);
        when(randomForParameterGenerator.nextBytes(1)).thenReturn(bytesOf("a"));
        when(randomForParameterGenerator.nextBytes(2)).thenReturn(bytesOf("bc"));
    }

    @Override
    protected Type parameterType() {
        return BigDecimal.class;
    }

    @Override
    protected int sampleSize() {
        return 3;
    }

    @Override
    protected List<?> randomValues() {
        return asList(new BigDecimal(BigInteger.ZERO, 0),
            new BigDecimal(new BigInteger(bytesOf("a")), 1),
            new BigDecimal(new BigInteger(bytesOf("bc")), 2));
    }

    @Override
    public void verifyInteractionWithRandomness() {
        verify(randomForParameterGenerator, times(1)).nextBytes(0);
        verify(randomForParameterGenerator, times(1)).nextBytes(1);
        verify(randomForParameterGenerator, times(1)).nextBytes(2);
    }
}
