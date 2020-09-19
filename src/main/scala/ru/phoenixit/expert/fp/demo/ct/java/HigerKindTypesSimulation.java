package ru.phoenixit.expert.fp.demo.ct.java;

import java.util.List;
import java.util.function.Function;

public class HigerKindTypesSimulation {

    public interface Functor<A> {
        <B> Functor<B> map(A a, Function<? super A, ? extends B> f);
    }

    Functor<List<Integer>> listFunctor = new Functor<List<Integer>>() {
        @Override
        public <B> Functor<B> map(List<Integer> integers, Function<? super List<Integer>, ? extends B> f) {
            return null;
        }
    };
}
