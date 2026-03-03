package ypractice.multithreading.old;


import java.util.concurrent.atomic.AtomicReference;

public class OrderService {

    public static void main(String[] args) throws Exception {
        ThreadTimer.measure(() -> makeOrder(1L));
        ThreadTimer.measure(() -> makeOrderWithThreads(1L));
    }

    public static Order makeOrderWithThreads(Long clientId) throws InterruptedException {
        var client = getClient(clientId);

        var itemRef = new AtomicReference<Item>();
        var t1 = new Thread(() -> {
            Item item = getItem(client);
            itemRef.set(item);
        });

        var cityRef = new AtomicReference<City>();
        Thread t2 = new Thread(() -> {
            City city = getCityFromYandex(client);
            cityRef.set(city);
        });

        var t3 = new Thread(OrderService::anyWork);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        var order = createOrder(clientId, itemRef.get(), cityRef.get());
        t3.join();

        return order;
    }

    public static Order makeOrder(Long clientId) {
        Client client = getClient(clientId);
        Item item = getItem(client);
        City city = getCityFromYandex(client);
        anyWork();
        return createOrder(clientId, item, city);
    }

    public static Client getClient(Long personId) {
        delay(500);
        return new Client(personId, "Mikhail");
    }

    public static Item getItem(Client person) {
        delay(500);
        return new Item("iphone", 1000);
    }
    public static City getCityFromYandex(Client person) {
        delay(500);
        return new City("SPB", 52);
    }
    public static void anyWork() {
        delay(500);
    }

    public static Order createOrder(Long personId, Item item, City liabilities) {
        delay(500);
        return new Order(personId, item.price(), liabilities.name());
    }

    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
