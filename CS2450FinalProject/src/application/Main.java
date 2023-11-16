package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    private Stage primaryStage;
    private Cart cart = new Cart();
    private Label cartItemCountLabel = new Label("Cart Items: 0");

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("kiskissing.com");

        Scene scene = createLoginScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Scene createLoginScene() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Label userNameLabel = new Label("Username:");
        gridPane.add(userNameLabel, 0, 1);

        TextField userTextField = new TextField();
        gridPane.add(userTextField, 1, 1);

        Label pwLabel = new Label("Password:");
        gridPane.add(pwLabel, 0, 2);

        PasswordField pwBox = new PasswordField();
        gridPane.add(pwBox, 1, 2);

        Button loginButton = new Button("Sign in");
        loginButton.setOnAction(event -> showDashboard());

        gridPane.add(loginButton, 1, 4);

        return new Scene(gridPane, 300, 275);
    }

    private void showDashboard() {
      TextField searchField = new TextField();
    	Label searchLabel = new Label("Search:");
        searchLabel.setPadding(new Insets(5));
         
    	HBox searchBox = new HBox(searchLabel,searchField);
    	VBox searhAndCartBox = new VBox(searchBox, cartItemCountLabel);
    	VBox homeSectionView= new VBox( createSectionView("Home"));
        
    	 
        BorderPane borderPaneBar = new BorderPane();
        MenuBar menuBar = createMenuBar();
        borderPaneBar.setTop(menuBar);
        borderPaneBar.setRight(searhAndCartBox);
        
        
        BorderPane mainPane= new BorderPane();
        mainPane.setTop(borderPaneBar);
        mainPane.setCenter(homeSectionView);
        
 

        Scene scene = new Scene(mainPane, 800, 600);
        primaryStage.setScene(scene);
        
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu homeMenu = new Menu("Home");
        MenuItem homeItem = new MenuItem("Home");
        homeItem.setOnAction(e -> switchToSection("Home"));

        Menu menMenu = new Menu("Men");
        MenuItem menItem = new MenuItem("Men's Clothing");
        menItem.setOnAction(e -> switchToSection("Men's Clothing"));

        Menu womenMenu = new Menu("Women");
        MenuItem womenItem = new MenuItem("Women's Clothing");
        womenItem.setOnAction(e -> switchToSection("Women's Clothing"));

        Menu kidsMenu = new Menu("Kids");
        MenuItem kidsItem = new MenuItem("Kids' Clothing");
        kidsItem.setOnAction(e -> switchToSection("Kids' Clothing"));

        Menu cartMenu = new Menu("Cart");
        MenuItem cartItem = new MenuItem("Shopping Cart");
        cartItem.setOnAction(e -> switchToSection("Shopping Cart"));

        homeMenu.getItems().add(homeItem);
        menMenu.getItems().add(menItem);
        womenMenu.getItems().add(womenItem);
        kidsMenu.getItems().add(kidsItem);
        cartMenu.getItems().add(cartItem);

        menuBar.getMenus().addAll(homeMenu, menMenu, womenMenu, kidsMenu, cartMenu);

        return menuBar;
    }

    private void switchToSection(String section) {
        BorderPane borderPane = new BorderPane();
        MenuBar menuBar = createMenuBar();
        borderPane.setTop(menuBar);

        if ("Shopping Cart".equals(section)) {
            borderPane.setCenter(createCartView());
        } else {
            borderPane.setCenter(createSectionView(section));
        }

        primaryStage.setScene(new Scene(borderPane, 800, 600));
    }

    private VBox createSectionView(String sectionTitle) {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

       // Label sectionLabel = new Label(sectionTitle);
        //sectionLabel.setStyle("-fx-font-size: 24px;");
       // FileInputStream inputStream = new FileInputStream("Logo.png");
        Image logo = new Image("Logo.png");
        ImageView logoView = new ImageView(logo);
     
        
       vBox.getChildren().add(logoView);

        if ("Home".equals(sectionTitle)) {
            vBox.getChildren().addAll(
                createItemButton("Men's Item 1"),
                createItemButton("Women's Item 1"),
                createItemButton("Kids' Item 1")
            );
        } else {
            vBox.getChildren().addAll(
                createItemButton(sectionTitle + " Item 1"),
                createItemButton(sectionTitle + " Item 2")
            );
        }

        return vBox;
    }

    private Button createItemButton(String itemName) {
        Button itemButton = new Button("Add " + itemName + " to Cart");
        itemButton.setOnAction(event -> {
            cart.addItem(itemName);
            updateCartItemCount();
            showAddedToCartPopup(itemName);
        });
        return itemButton;
    }

    private VBox createCartView() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        Label cartLabel = new Label("Shopping Cart");
        cartLabel.setStyle("-fx-font-size: 24px;");
        vBox.getChildren().add(cartLabel);

        for (Map.Entry<String, Integer> entry : cart.getItems().entrySet()) {
            String itemName = entry.getKey();
            Integer itemCount = entry.getValue();

            Label itemLabel = new Label(itemName + " - Quantity: " + itemCount);
            vBox.getChildren().add(itemLabel);
        }

        return vBox;
    }

    private void showAddedToCartPopup(String itemName) {
        Stage popupStage = new Stage();
        popupStage.initOwner(primaryStage);
        popupStage.initModality(Modality.NONE);
        popupStage.initStyle(StageStyle.UNDECORATED);

        VBox popupContent = new VBox(10);
        popupContent.setAlignment(Pos.CENTER);
        popupContent.setStyle("-fx-padding: 10; -fx-background-color: lightgreen; -fx-border-color: black; -fx-border-width: 2;");

        Label addedLabel = new Label(itemName + " added to cart!");
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> popupStage.close());

        popupContent.getChildren().addAll(addedLabel, closeButton);

        Scene popupScene = new Scene(popupContent, 200, 100);
        popupStage.setScene(popupScene);

        popupStage.setX(primaryStage.getX() + primaryStage.getWidth());
        popupStage.setY(primaryStage.getY());

        popupStage.show();
    }

    private void updateCartItemCount() {
        cartItemCountLabel.setText("Cart Items: " + cart.getTotalItemCount());
    }

    public static void main(String[] args) {
        launch(args);
    }

    public class Cart {
        private Map<String, Integer> items;

        public Cart() {
            items = new HashMap<>();
        }

        public void addItem(String item) {
            items.put(item, items.getOrDefault(item, 0) + 1);
        }

        public int getTotalItemCount() {
            return items.values().stream().mapToInt(Integer::intValue).sum();
        }

        public Map<String, Integer> getItems() {
            return items;
        }
    }
}
