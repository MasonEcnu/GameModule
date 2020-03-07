package com.mason.game.constans;

import java.util.Objects;

/**
 * Created by mwu on 2019/12/23
 */
public class Pair<T> {

    private T first;
    private T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", first, second);
    }

    @Override
    public int hashCode() {
        return first.hashCode() * 13 + (second == null ? 0 : second.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            if (!Objects.equals(first, pair.first)) return false;
            return Objects.equals(second, pair.second);
        }
        return false;
    }
}
