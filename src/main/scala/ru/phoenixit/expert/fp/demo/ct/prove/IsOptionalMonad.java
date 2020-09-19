package ru.phoenixit.expert.fp.demo.ct.prove;

import scala.Int;

import java.util.Optional;
import java.util.function.Function;

public class IsOptionalMonad {
    public static void main(String[] args) {
        Function<Integer, Optional<Integer>> leftIdentLaw = value -> {
            if(value < 0) {
                value = null;
            }
            return Optional.ofNullable(value);
        };

        System.out.println( "Left Identity Law: " +
                Optional.of(5).flatMap(leftIdentLaw).equals(leftIdentLaw.apply(5))
        );
        System.out.println( "Left Identity Law: " +
                Optional.of(-1).flatMap(leftIdentLaw).equals(leftIdentLaw.apply(-1))
        );

        Function<Integer, Optional<String>> fOp = value -> Optional.ofNullable((value == null) ? "Null" : value.toString());

        Optional<String> string = fOp.apply(null);
        Integer nullValue = null;
        Optional<String> empty = Optional.ofNullable(nullValue).flatMap(fOp);



        Function<Integer, Integer> f = value -> (value > 0) ? value : null;
        Function<Integer, String> g = value -> (value == null) ? "null value" : value.toString();

        Optional<Integer> ok = Optional.of(1);
        Optional<Integer> nulled = Optional.of(0);

        System.out.println("Associativity Law");
        System.out.println(ok.map(f).map(g));
        System.out.println(ok.map(f.andThen(g)));

        System.out.println(nulled.map(f).map(g));
        System.out.println(nulled.map(f.andThen(g)));

    }
}
