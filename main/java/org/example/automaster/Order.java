package org.example.automaster;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private int id;
    private Client client;
    private List<Service> services;
    private LocalDate date;
    private String status;

    public Order(int id, Client client, List<Service> services, LocalDate date, String status) {
        this.id = id;
        this.client = client;
        this.services = services;
        this.date = date;
        this.status = status;
    }

    public int getId() { return id; }
    public Client getClient() { return client; }
    public List<Service> getServices() { return services; }
    public LocalDate getDate() { return date; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
