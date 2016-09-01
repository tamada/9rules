package com.github.ninerules.utils;

import java.util.Iterator;

public class PairIterator<T, U> implements Iterator<Pair<T, U>> {
    private Iterator<T> iteratorT;
    private Iterator<U> iteratorU;

    public PairIterator(Iterator<T> iteratorT, Iterator<U> iteratorU){
        this.iteratorT = iteratorT;
        this.iteratorU = iteratorU;
    }

    @Override
    public boolean hasNext() {
        return iteratorT.hasNext()
                && iteratorU.hasNext();
    }
    @Override
    public Pair<T, U> next() {
        T objectT = iteratorT.next();
        U objectU = iteratorU.next();
        return Pair.of(objectT, objectU);
    }
}
