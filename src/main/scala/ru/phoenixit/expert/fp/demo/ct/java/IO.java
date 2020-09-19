package ru.phoenixit.expert.fp.demo.ct.java;

import ru.phoenixit.expert.fp.demo.ct.java.result.Result;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

@FunctionalInterface
public interface IO<A> {
    A eval();

    default <B> IO<B> flatMap(Function<A, IO<B>> f) {
        return () -> f.apply(this.eval()).eval();
    }

    default <B> IO<B> map(Function<A, B> f) {
        return () -> this.flatMap(x -> IO.success(f.apply(x))).eval();
    }

    static <A> IO<Result<A>> effect(Supplier<A> f) {
        return () -> Result.failable(f);
    }

    static <A> IO<Void> zero() {
        return () -> null;
    }

    static <A> IO<Result<A>> fail(Throwable ex) {
        return () -> Result.fail(ex);
    }

    static <A> IO<A> success(A value) {
        return () -> value;
    }

    //Combines two io's. Ignores first io result
    default <A> IO<A> andThen(IO<A> io) {
        return flatMap(result -> io);
    }

    static IO<Void> foreach(IO<?>... ios) {
        return Arrays.stream(ios)
                .reduce(IO.zero(), IO::andThen)
                .andThen(IO.zero());
    }


}
