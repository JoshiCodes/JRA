package de.joshizockt.jra.rest;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

public class RestAction<T> {

    private final Function<Void, T> action;

    public RestAction(Function<Void, T> action) {
        this.action = action;
    }

    /**
     * Executes the action and returns the result
     * @return The result T of the action
     */
    public T complete() {
        try {
            return action.apply(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void queue() {
        queue(null);
    }

    /**
     * Executes the action and calls the consumer with the result
     * @param then the consumer to call with the result, can be null
     */
    public void queue(Consumer<T> then) {
        queue(then, null);
    }

    /**
     * Executes the action and returns nothing.
     * If finished successfully, the then consumer will be executed. If an error occurs, the error consumer will be executed.
     * @param then The consumer to execute if the action was successful, can be null
     * @param error The consumer to execute if an error occurs, can be null
     */
    public void queue(@Nullable Consumer<T> then, @Nullable Consumer<Exception> error) {
        new Thread(() -> {
            try {
                T t = complete();
                if(then != null) {
                    then.accept(t);
                }
            } catch (Exception e) {
                if(error != null) {
                    error.accept(e);
                } else throw new RuntimeException(e);
            }
        }).start();
    }

    public CompletableFuture<T> submit() {
        return CompletableFuture.supplyAsync(this::complete);
    }

}
