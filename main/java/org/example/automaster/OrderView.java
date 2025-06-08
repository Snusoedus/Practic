package org.example.automaster;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import com.example.autoservice.storage.DataStore;
import com.example.autoservice.model.*;
import java.time.LocalDate;
import java.util.*;

public class OrderView {
    public Pane getView() {
        VBox box = new VBox(10);
        box.setStyle("-fx-padding: 20;");
        Label title = new Label("Заказы");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        box.getChildren().add(title);

        for (Order o : DataStore.orders) {
            VBox card = new VBox(2);
            card.setStyle("-fx-background-color: #eee; -fx-padding: 10; -fx-border-radius: 5; -fx-background-radius: 5;");
            StringBuilder servicesList = new StringBuilder();
            for (Service s : o.getServices()) {
                servicesList.append(s.getName()).append(", ");
            }
            card.getChildren().addAll(
                    new Label("Клиент: " + o.getClient().getName()),
                    new Label("Авто: " + o.getClient().getCarModel()),
                    new Label("Услуги: " + servicesList),
                    new Label("Дата: " + o.getDate()),
                    new Label("Статус: " + o.getStatus()),
                    new Label("ID: " + o.getId())
            );
            box.getChildren().add(card);
        }

        // Форма добавления заказа
        ComboBox<Client> clientBox = new ComboBox<>();
        clientBox.getItems().addAll(DataStore.clients);
        clientBox.setPromptText("Клиент");

        ListView<Service> serviceList = new ListView<>();
        serviceList.getItems().addAll(DataStore.services);
        serviceList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TextField statusField = new TextField(); statusField.setPromptText("Статус");
        Button addBtn = new Button("Добавить заказ");
        Label msg = new Label();

        addBtn.setOnAction(e -> {
            Client client = clientBox.getValue();
            List<Service> selectedServices = serviceList.getSelectionModel().getSelectedItems();
            if (client != null && !selectedServices.isEmpty()) {
                int newId = DataStore.orders.size() + 1;
                DataStore.orders.add(new Order(newId, client, new ArrayList<>(selectedServices), LocalDate.now(), statusField.getText()));
                msg.setText("Заказ добавлен!");
                clientBox.setValue(null); serviceList.getSelectionModel().clearSelection(); statusField.clear();
            } else {
                msg.setText("Выберите клиента и услуги");
            }
        });

        VBox form = new VBox(5, clientBox, serviceList, statusField, addBtn, msg);
        form.setStyle("-fx-padding: 10; -fx-background-color: #f9f9f9; -fx-border-radius: 5; -fx-background-radius: 5;");
        box.getChildren().add(form);

        return new ScrollPane(box);
    }
}