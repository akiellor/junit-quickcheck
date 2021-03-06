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

import java.util.ArrayList;
import java.util.List;

/**
 * Produces values for theory parameters of types that have components, such as arrays and lists.
 *
 * @param <T> type of theory parameter to apply this generator's values to
 */
public abstract class ComponentizedGenerator<T> extends Generator<T> {
    protected final List<Generator<?>> componentGenerators = new ArrayList<Generator<?>>();

    /**
     * @param type class token for type of theory parameter this generator is applicable to
     */
    protected ComponentizedGenerator(Class<T> type) {
        super(type);
    }

    @Override
    public boolean hasComponents() {
        return true;
    }

    @Override
    public void addComponentGenerators(List<Generator<?>> generators) {
        componentGenerators.clear();
        componentGenerators.addAll(generators);
    }
}
