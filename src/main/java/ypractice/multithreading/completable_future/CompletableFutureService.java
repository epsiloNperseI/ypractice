package ypractice.multithreading.completable_future;

import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.concurrent.CompletableFuture.runAsync;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static ypractice.multithreading.old.OrderService.createOrder;
import static ypractice.multithreading.old.OrderService.getClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import ypractice.multithreading.old.City;
import ypractice.multithreading.old.Client;
import ypractice.multithreading.old.Item;
import ypractice.multithreading.old.Order;
import ypractice.multithreading.old.OrderService;

public class CompletableFutureService {
    public static Order makeOrderWithCompletableFuture(Long clientId) throws InterruptedException, ExecutionException {
        CompletableFuture<Client> clientFuture = supplyAsync(() -> getClient(clientId));
        CompletableFuture<Item> itemFuture = clientFuture.thenApplyAsync(OrderService::getItem);
        CompletableFuture<City> cityFuture = clientFuture.thenApplyAsync(OrderService::getCityFromYandex);
        CompletableFuture<Void> anyWorkFuture = runAsync(OrderService::anyWork);

        return allOf(clientFuture, itemFuture, cityFuture, anyWorkFuture)
            .thenApply(v -> {
                try {
                    Client client = clientFuture.get();
                    Item item = itemFuture.get();
                    City city = cityFuture.get();
                    return createOrder(client.id(), item, city);
                } catch (InterruptedException | ExecutionException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            })
            .get();
    }
}
