package com.zhenya.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class Clients implements Comparable<Clients> {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private int id;
    private int priority;

    public Clients(int priority) {
        id = COUNTER.getAndIncrement();
        this.priority = priority;
    }

    public static AtomicInteger getCOUNTER () {
        return COUNTER;
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Clients clients) {
        return Integer.valueOf(clients.getPriority()).compareTo(this.getPriority());
    }
}
