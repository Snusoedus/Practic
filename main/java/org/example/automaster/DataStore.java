package org.example.automaster;

import com.example.autoservice.model.*;
import java.util.*;

public class DataStore {
    public static List<Client> clients = new ArrayList<>();
    public static List<Service> services = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();

    // Пример заполнения тестовыми данными
    static {
        clients.add(new Client(1, "Иван Иванов", "Lada Granta", "+79991234567", "ivan@mail.ru"));
        clients.add(new Client(2, "Петр Петров", "Toyota Camry", "+79997654321", "petr@mail.ru"));

        services.add(new Service(1, "Замена масла", 1000));
        services.add(new Service(2, "Диагностика двигателя", 1200));
    }
}
