package ru.job4j.garbage;

import java.util.ArrayList;
import java.util.List;

public class GCExample {

    public static class User {

        private final String name;
        private final Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println(String.format("%s is finalize", this));

        }

        @Override
        public String toString() {
            return String.format("User: name = %s | age = %d", this.name, this.age);
        }
    }

    public static void info() {
        int mb = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();
        System.out.println("##### Heap utilization statistics [MB] #####");
        System.out.println("Used memory: " + (runtime.totalMemory() - runtime.freeMemory()) / mb + "Mb");
        System.out.println("Free memory: " + (runtime.freeMemory()) / mb + "Mb");
        System.out.println("Total memory: " + (runtime.totalMemory()) / mb + "Mb");
        System.out.println("Max memory: " + (runtime.maxMemory()) / mb + "Mb");
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        info();
//        List<User> list = new ArrayList<>();
        for (int index = 0; index < 100000; index++) {
            System.out.println(new User(String.valueOf(index), index));
//            list.add(new User(String.valueOf(index),index));
        }
        info();

        System.out.println(System.currentTimeMillis() - start);

    }
}
