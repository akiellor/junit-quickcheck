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
import java.util.List;

import com.pholser.junit.quickcheck.internal.generator.GeneratingUniformRandomValuesForTheoryParameterTest;

import static java.lang.Integer.*;
import static java.util.Arrays.*;
import static org.mockito.Mockito.*;

public class WrapperIntegerTest extends GeneratingUniformRandomValuesForTheoryParameterTest {
    @Override
    protected void primeSourceOfRandomness() {
        when(randomForParameterGenerator.nextInt(MIN_VALUE, MAX_VALUE)).thenReturn(0).thenReturn(1).thenReturn(-1);
    }

    @Override
    protected Type parameterType() {
        return Integer.class;
    }

    @Override
    protected int sampleSize() {
        return 3;
    }

    @Override
    protected List<?> randomValues() {
        return asList(0, 1, -1);
    }

    @Override
    public void verifyInteractionWithRandomness() {
        verify(randomForParameterGenerator, times(3)).nextInt(MIN_VALUE, MAX_VALUE);
    }
}
