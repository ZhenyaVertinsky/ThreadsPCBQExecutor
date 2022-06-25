package com.zhenya.domain;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Managers implements Runnable {

    BlockingQueue<Clients> queue;
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private int id;

    public Managers(BlockingQueue<Clients> queue) {
        this.queue = queue;
        this.id = COUNTER.getAndIncrement();
    }

    public BlockingQueue<Clients> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Clients> queue) {
        this.queue = queue;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Clients clients = queue.poll(1, TimeUnit.SECONDS);
                if (clients == null) {
                    System.out.println("Manager " + id + " ended a work");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    System.out.println("Manager " + id + " have spoken with a client " + clients.getId());
                } catch (InterruptedException e) {
                    System.out.println("INTERRUPTED");
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Manager INTERRUPTED");
        }
    }
}
