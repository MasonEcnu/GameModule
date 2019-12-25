package com.mason.game.constans;

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
}
