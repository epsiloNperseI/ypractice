package ypractice.multithreading.executor;

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
import ypractice.multithreading.old.ThreadTimer;

public class Executor {

    public static void main(String[] args) throws Exception {
        ThreadTimer.measure(() -> makeOrderWithExecutor(1L));
    }
    public static Order makeOrderWithExecutor(Long clientId) throws ExecutionException, InterruptedException {
        try (ExecutorService executor = Executors.newFixedThreadPool(5)) {
            Client client = getClient(clientId);
            Future<Item> itemFuture = executor.submit(() -> getItem(client));
            Future<City> cityFuture = executor.submit(() -> getCityFromYandex(client));
            executor.submit(OrderService::anyWork);
            return createOrder(clientId, itemFuture.get(), cityFuture.get());
        }
    }
}
