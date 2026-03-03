package ypractice.multithreading.virtual_thread;

import static ypractice.multithreading.old.OrderService.createOrder;
import static ypractice.multithreading.old.OrderService.getCityFromYandex;
import static ypractice.multithreading.old.OrderService.getClient;
import static ypractice.multithreading.old.OrderService.getItem;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import ypractice.multithreading.old.City;
import ypractice.multithreading.old.Client;
import ypractice.multithreading.old.Item;
import ypractice.multithreading.old.Order;
import ypractice.multithreading.old.OrderService;

public class VirtualThreadService {
    public static Order makeOrderWithVirtualThread(Long clientId) throws ExecutionException, InterruptedException {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Client client = getClient(clientId);
            Future<Item> itemFuture = executor.submit(() -> getItem(client));
            Future<City> cityFuture = executor.submit(() -> getCityFromYandex(client));
            executor.submit(OrderService::anyWork);
            return createOrder(clientId, itemFuture.get(), cityFuture.get());
        }
    }
}
