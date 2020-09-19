package ru.phoenixit.expert.fp.demo.ct.java;

import ru.phoenixit.expert.fp.demo.ct.java.result.Result;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.function.Function;

public class UsingIO {
    public static <B> IO<Result<Path>> save(String content, String path) {
        return () -> {
            try {
                return Result.success(Files.write(Paths.get(path), Collections.singleton(content)));
            } catch (Throwable ex) {
                return Result.fail(ex);
            }
        };
    }

    public static IO<Void> debug(String msg) {
        return IO.effect(() -> {
            System.out.println(msg);
            return true;
        }).andThen(IO.zero());
    }

    public static IO<Void> programm() {
        Function<String, IO<String>> stringIO = IO::success;
        IO<Void> writeFile = stringIO.apply("My text")
                .flatMap(text ->
                        save(text, "my_text.txt")
                                .flatMap(res ->
                                        debug("Successful? " + res.isSuccess())
                                ));
        IO<Void> greet = debug("Programm launched");

        return IO.foreach(greet, writeFile);
    }

    public static void main(String[] args) {
        programm().eval();
    }
}
