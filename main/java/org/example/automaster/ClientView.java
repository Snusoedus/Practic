package org.example.automaster;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import com.example.autoservice.storage.DataStore;
import com.example.autoservice.model.Client;

public class ClientView {
    public Pane getView() {
        VBox box = new VBox(10);
        box.setStyle("-fx-padding: 20;");
        Label title = new Label("Клиенты");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        box.getChildren().add(title);

        for (Client c : DataStore.clients) {
            VBox card = new VBox(2);
            card.setStyle("-fx-background-color: #eee; -fx-padding: 10; -fx-border-radius: 5; -fx-background-radius: 5;");
            card.getChildren().addAll(
                    new Label("Имя: " + c.getName()),
                    new Label("Почта: " + c.getEmail()),
                    new Label("Телефон: " + c.getPhone()),
                    new Label("Модель авто: " + c.getCarModel()),
                    new Label("ID: " + c.getId())
            );
            box.getChildren().add(card);
        }

        // Форма добавления клиента
        TextField nameField = new TextField(); nameField.setPromptText("Имя");
        TextField carField = new TextField(); carField.setPromptText("Модель авто");
        TextField phoneField = new TextField(); phoneField.setPromptText("Телефон");
        TextField emailField = new TextField(); emailField.setPromptText("Почта");
        Button addBtn = new Button("Добавить клиента");
        Label msg = new Label();

        addBtn.setOnAction(e -> {
            int newId = DataStore.clients.size() + 1;
            DataStore.clients.add(new Client(newId, nameField.getText(), carField.getText(), phoneField.getText(), emailField.getText()));
            msg.setText("Клиент добавлен!");
            nameField.clear(); carField.clear(); phoneField.clear(); emailField.clear();
        });

        VBox form = new VBox(5, nameField, carField, phoneField, emailField, addBtn, msg);
        form.setStyle("-fx-padding: 10; -fx-background-color: #f9f9f9; -fx-border-radius: 5; -fx-background-radius: 5;");
        box.getChildren().add(form);

        return new ScrollPane(box);
    }
}
