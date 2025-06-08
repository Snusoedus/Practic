package org.example.automaster;

public class Client {
    private int id;
    private String name;
    private String carModel;
    private String phone;
    private String email;

    public Client(int id, String name, String carModel, String phone, String email) {
        this.id = id;
        this.name = name;
        this.carModel = carModel;
        this.phone = phone;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCarModel() { return carModel; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setCarModel(String carModel) { this.carModel = carModel; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
}