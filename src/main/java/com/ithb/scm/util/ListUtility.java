package com.ithb.scm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListUtility<T> {
  public List<T> immutableRandom(List<T> list) {
    List<T> clone = new ArrayList<>(list);

    for (var index = 0; index < clone.size(); index++) {
      var randomIndexToSwap = new Random().nextInt(clone.size());
      var temp = clone.get(randomIndexToSwap);
      clone.set(randomIndexToSwap, clone.get(index));
      clone.set(index, temp);
    }

    return clone;
  }
}
