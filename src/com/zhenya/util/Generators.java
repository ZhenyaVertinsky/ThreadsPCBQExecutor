package com.zhenya.util;

import com.zhenya.domain.Clients;

public class Generators {
    public static Clients generateClient() {
        int min_priority = 1;
        int max_priority = 5;
        int currentPriority = (int) (min_priority + Math.random() * max_priority);
        return new Clients(currentPriority);
    }
}
