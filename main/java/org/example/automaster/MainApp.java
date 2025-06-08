package org.example.automaster;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.example.autoservice.view.*;

public class MainApp extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        showLoginScreen();
    }

    private void showLoginScreen() {
        Label title = new Label("Авторизация");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Имя пользователя");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Пароль");
        Button loginButton = new Button("Войти");
        Label messageLabel = new Label();

        loginButton.setOnAction(e -> {
            // Простейшая авторизация (логин: admin, пароль: admin)
            if ("admin".equals(usernameField.getText()) && "admin".equals(passwordField.getText())) {
                showMainWindow();
            } else {
                messageLabel.setText("Неверные данные");
            }
        });

        VBox loginLayout = new VBox(10, title, usernameField, passwordField, loginButton, messageLabel);
        loginLayout.setStyle("-fx-padding: 40; -fx-alignment: center;");
        Scene loginScene = new Scene(loginLayout, 400, 300);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Вход");
        primaryStage.show();
    }

    private void showMainWindow() {
        VBox menu = new VBox(10);
        Button clientsBtn = new Button("Клиенты");
        Button ordersBtn = new Button("Заказы");
        Button servicesBtn = new Button("Услуги");

        BorderPane root = new BorderPane();
        root.setLeft(menu);

        menu.getChildren().addAll(clientsBtn, ordersBtn, servicesBtn);
        menu.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");

        // По умолчанию показываем клиентов
        root.setCenter(new ClientView().getView());

        clientsBtn.setOnAction(e -> root.setCenter(new ClientView().getView()));
        ordersBtn.setOnAction(e -> root.setCenter(new OrderView().getView()));
        servicesBtn.setOnAction(e -> root.setCenter(new ServiceView().getView()));

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Автомастерская");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}