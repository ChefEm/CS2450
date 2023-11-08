package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("JavaFX Shopping App");
        primaryStage.setScene(createLoginScene());
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
        BorderPane borderPane = new BorderPane();
        MenuBar menuBar = createMenuBar();
        borderPane.setTop(menuBar);
        borderPane.setCenter(createSectionView("Home"));

        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        // Create "Home" menu
        Menu homeMenu = new Menu("Home");
        MenuItem homeItem = new MenuItem("Home");
        homeItem.setOnAction(e -> switchToSection("Home"));

        // Create Menus
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

        // Add MenuItems to Menus
        homeMenu.getItems().add(homeItem);
        menMenu.getItems().add(menItem);
        womenMenu.getItems().add(womenItem);
        kidsMenu.getItems().add(kidsItem);
        cartMenu.getItems().add(cartItem);

        // Add Menus to MenuBar
        menuBar.getMenus().addAll(homeMenu, menMenu, womenMenu, kidsMenu, cartMenu);

        return menuBar;
    }


    private void switchToSection(String section) {
        BorderPane borderPane = new BorderPane();
        MenuBar menuBar = createMenuBar();
        borderPane.setTop(menuBar);
        borderPane.setCenter(createSectionView(section));
        primaryStage.setScene(new Scene(borderPane, 800, 600));
    }

    private VBox createSectionView(String sectionTitle) {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        Label sectionLabel = new Label(sectionTitle);
        sectionLabel.setStyle("-fx-font-size: 24px;");

        vBox.getChildren().add(sectionLabel);


        if ("Home".equals(sectionTitle)) {

            vBox.getChildren().addAll(
                createItemButton("Men's Item 1"),
                createItemButton("Women's Item 1"),
                createItemButton("Kids' Item 1")
            );
        } else {
            // Add two placeholder items for other sections
            vBox.getChildren().addAll(
                createItemButton(sectionTitle + " Item 1"),
                createItemButton(sectionTitle + " Item 2")
            );
        }

        return vBox;
    }

    // Helper method to create a button for an item
    private Button createItemButton(String itemName) {
        Button itemButton = new Button("Add " + itemName + " to Cart");

        itemButton.setOnAction(event -> System.out.println(itemName + " added to cart!"));
        return itemButton;
    }
    
    


    public static void main(String[] args) {
        launch(args);
    }
}
