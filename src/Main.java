import com.zhenya.domain.Clients;
import com.zhenya.domain.Managers;
import com.zhenya.util.QueueGenerator;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;


public class Main {
    public static void main(String[] args) {

        int clients = 10;
        int managers = 3;

        ExecutorService client = Executors.newFixedThreadPool(1);
        ExecutorService manager = Executors.newFixedThreadPool(managers);

        BlockingQueue<Clients> queue = new PriorityBlockingQueue<>(10);
        QueueGenerator queueGenerator = new QueueGenerator(queue, clients);

        System.out.println("Call centre begin working");

        client.submit(new Thread(queueGenerator));
        for (int i = 0; i < managers; i++) {
            manager.submit(new Thread(new Managers(queue)));
        }

        client.shutdown();
        manager.shutdown();

        while (true) {
            if (manager.isTerminated()) {
                System.out.println("Call centre just closed");
                break;
            }
        }
    }
}

