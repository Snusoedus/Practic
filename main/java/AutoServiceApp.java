import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

class Client {
    int id;
    String name, email, phone, carModel;
    Client(int id, String name, String email, String phone, String carModel) {
        this.id = id; this.name = name; this.email = email; this.phone = phone; this.carModel = carModel;
    }
    public String toString() { return id + ": " + name + " (" + carModel + ")"; }
}

class Service {
    int id, price;
    String name;
    Service(int id, String name, int price) {
        this.id = id; this.name = name; this.price = price;
    }
    public String toString() { return name + " - " + price + "р"; }
}

class Order {
    int id;
    Client client;
    String description, date;
    Order(int id, Client client, String description, String date) {
        this.id = id; this.client = client; this.description = description; this.date = date;
    }
    public String toString() { return "Заказ " + id + ": " + client.name + " (" + date + ")"; }
}

public class AutoServiceApp extends Application {
    ObservableList<Client> clients = FXCollections.observableArrayList();
    ObservableList<Service> services = FXCollections.observableArrayList();
    ObservableList<Order> orders = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        clients.add(new Client(1, "Иванов И.И.", "ivanov@mail.ru", "123456", "Lada"));
        services.add(new Service(1, "Замена масла", 1000));
        orders.add(new Order(1, clients.get(0), "Шум в двигателе", "2024-06-01"));

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(
            new Tab("Клиенты", clientsPane()),
            new Tab("Заказы", ordersPane()),
            new Tab("Услуги", servicesPane())
        );
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(tabPane, 600, 400);
        stage.setTitle("Автосервис");
        stage.setScene(scene);
        stage.show();
    }

    private Pane clientsPane() {
        ListView<Client> list = new ListView<>(clients);

        TextField tfName = new TextField(), tfEmail = new TextField(), tfPhone = new TextField(), tfCar = new TextField();
        tfName.setPromptText("Имя"); tfEmail.setPromptText("Почта");
        tfPhone.setPromptText("Телефон"); tfCar.setPromptText("Модель авто");
        Button btnAdd = new Button("Добавить");

        btnAdd.setOnAction(e -> {
            int id = clients.size() + 1;
            clients.add(new Client(id, tfName.getText(), tfEmail.getText(), tfPhone.getText(), tfCar.getText()));
            tfName.clear(); tfEmail.clear(); tfPhone.clear(); tfCar.clear();
        });

        VBox form = new VBox(5, tfName, tfEmail, tfPhone, tfCar, btnAdd);
        form.setPadding(new Insets(10));
        form.setAlignment(Pos.CENTER_LEFT);

        BorderPane pane = new BorderPane();
        pane.setCenter(list);
        pane.setRight(form);
        return pane;
    }

    private Pane ordersPane() {
        ListView<Order> list = new ListView<>(orders);

        ComboBox<Client> cbClients = new ComboBox<>(clients);
        cbClients.setPromptText("Клиент");
        TextField tfDesc = new TextField(), tfDate = new TextField();
        tfDesc.setPromptText("Описание"); tfDate.setPromptText("Дата");
        Button btnAdd = new Button("Добавить");

        btnAdd.setOnAction(e -> {
            if (cbClients.getValue() != null) {
                int id = orders.size() + 1;
                orders.add(new Order(id, cbClients.getValue(), tfDesc.getText(), tfDate.getText()));
                tfDesc.clear(); tfDate.clear();
            }
        });

        VBox form = new VBox(5, cbClients, tfDesc, tfDate, btnAdd);
        form.setPadding(new Insets(10));
        form.setAlignment(Pos.CENTER_LEFT);

        BorderPane pane = new BorderPane();
        pane.setCenter(list);
        pane.setRight(form);
        return pane;
    }

    private Pane servicesPane() {
        ListView<Service> list = new ListView<>(services);

        TextField tfName = new TextField(), tfPrice = new TextField();
        tfName.setPromptText("Название"); tfPrice.setPromptText("Цена");
        Button btnAdd = new Button("Добавить");

        btnAdd.setOnAction(e -> {
            try {
                int id = services.size() + 1;
                int price = Integer.parseInt(tfPrice.getText());
                services.add(new Service(id, tfName.getText(), price));
                tfName.clear(); tfPrice.clear();
            } catch (NumberFormatException ex) {
                // Можно добавить сообщение об ошибке
            }
        });

        VBox form = new VBox(5, tfName, tfPrice, btnAdd);
        form.setPadding(new Insets(10));
        form.setAlignment(Pos.CENTER_LEFT);

        BorderPane pane = new BorderPane();
        pane.setCenter(list);
        pane.setRight(form);
        return pane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}