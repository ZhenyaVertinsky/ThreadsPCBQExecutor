package com.zhenya.util;

import com.zhenya.domain.Clients;

import java.util.concurrent.BlockingQueue;

public class QueueGenerator implements Runnable {
    BlockingQueue<Clients> queue;
    private int sizeOfClients;

    public QueueGenerator(BlockingQueue<Clients> queue, int sizeOfClients) {
        this.queue = queue;
        this.sizeOfClients = sizeOfClients;
    }

    public BlockingQueue<Clients> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Clients> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < sizeOfClients; i++) {
                Clients clients = Generators.generateClient();
                queue.put(clients);
                System.out.println("Client " + clients.getId() + " waiting an answer");
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("INTERRUPTED");
            }
        } catch (InterruptedException e) {
            System.out.println("CLIENT INTERRUPTED");
        }
    }
}
