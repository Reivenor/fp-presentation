package ru.phoenixit.expert.fp.demo.ct.java.result;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Result<A> {
    private Result() { }

    public abstract A get() throws Throwable;
    public abstract Result<A> orElse(Supplier<A> f);

    public abstract Boolean isSuccess();
    public abstract Boolean isFailure();

    public abstract <B> Result<B> map(Function<? super A, ? extends B> f);
    public abstract <B> Result<B> flatMap(Function<? super A, Result<B>> f);

    public static <A> Result<A> success(A value) {
        Objects.requireNonNull(value);
        return new Success<>(value);
    }

    public static <A> Result<A> fail(Throwable th) {
        Objects.requireNonNull(th);
        return new Fail<>(th);
    }


    public static <A> Result<A> failable(Supplier<A> f) {
        Objects.requireNonNull(f);
        try {
            return Result.success(f.get());
        } catch (Throwable th) {
            return Result.fail(th);
        }
    }

  //  public static <A> Result<A> manageable(Supplier<A> f, res)

    private static class Success<A> extends Result<A> {
        private final A value;

        public Success(A value) {
            super();
            this.value = value;
        }

        @Override
        public A get() {
            return value;
        }

        @Override
        public Result<A> orElse(Supplier<A> f) {
            return Result.success(value);
        }

        @Override
        public Boolean isSuccess() {
            return true;
        }

        @Override
        public Boolean isFailure() {
            return false;
        }

        @Override
        public <B> Result<B> map(Function<? super A, ? extends B> f) {
            Objects.requireNonNull(f);
            return Result.failable(() -> f.apply(value));
        }

        @Override
        public <B> Result<B> flatMap(Function<? super A, Result<B>> f) {
            Objects.requireNonNull(f);
            try {
             return f.apply(value);
            } catch (Throwable th) {
                return Result.fail(th);
            }
        }
    }

    private static class Unit extends Success<Void> {
        public Unit() {
            super(null);
        }
        public Unit(Void value) {
            super(value);
        }

        @Override
        public Void get() {
            return null;
        }
    }

    private static class Fail<A> extends  Result<A> {
        private final Throwable ex;

        public Fail(Throwable ex) {
            super();
            this.ex = ex;
        }

        @Override
        public A get() throws Throwable {
            throw ex;
        }

        @Override
        public Result<A> orElse(Supplier<A> f) {
            return Result.failable(f);
        }

        @Override
        public Boolean isSuccess() {
            return false;
        }

        @Override
        public Boolean isFailure() {
            return true;
        }

        @Override
        public <B> Result<B> map(Function<? super A, ? extends B> f) {
            return Result.fail(ex);
        }

        @Override
        public <B> Result<B> flatMap(Function<? super A, Result<B>> f) {
            return Result.fail(ex);
        }
    }
}
