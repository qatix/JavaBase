package com.qatix.base.lang.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ListSortExample {
    public static void main(String[] args) {
        List<Item> list = new ArrayList<>();
        while (list.size() < 10) {
            list.add(new Item(new Random().nextInt(991)));
        }
        System.out.println(list.toString());
        list.sort((o1, o2) -> {
            return o1.getScore() - o2.getScore();
        });
        System.out.println(list.toString());
        list.sort((o1, o2) -> {
            if (o1.score > o2.score) {
                return -1;
            } else if (o1.score < o2.score) {
                return 1;
            } else {
                return 0;
            }
        });
        System.out.println(list.toString());
    }

    public static class Item {
        private int score;

        public Item(int score) {
            this.score = score;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "score=" + score +
                    '}';
        }
    }
}
