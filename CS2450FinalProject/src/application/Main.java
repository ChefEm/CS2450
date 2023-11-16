package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    private Stage primaryStage;
    private Cart cart = new Cart();
    private Label cartItemCountLabel = new Label("Cart Items: 0");
    private String currentUsername;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("viviennewestwood.com");

        // Create the initial layout with logoView
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(createMenuBar());
        VBox mainContent = createMainPageView(); // This creates the view with logoView
        mainContent.setAlignment(Pos.CENTER);
        mainLayout.setCenter(mainContent);

        ScrollPane scrollPane = new ScrollPane(mainLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        
        // Set the scene to the primary stage
        primaryStage.setScene(new Scene(scrollPane, 1200, 800));
        primaryStage.show();
    }

    
    private VBox createMainPageView() {
        VBox mainPageView = new VBox(10); // Spacing between elements
        mainPageView.setAlignment(Pos.CENTER); // Center alignment

        ImageView logoView = createLogoView();
        VBox sectionView = createSectionView("Home");

        mainPageView.getChildren().addAll(logoView, sectionView);
        return mainPageView;
    }

    
    private ImageView createLogoView() {
        Image logo = new Image("logo.jpg"); 
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(250);
        logoView.setFitHeight(150);

        logoView.setOnMouseClicked(event -> {
            BorderPane mainLayout = new BorderPane();
            mainLayout.setTop(createMenuBar());
            VBox mainContent = createMainPageView();
            mainContent.setAlignment(Pos.CENTER); // Center the main content
            mainLayout.setCenter(mainContent);

            ScrollPane scrollPane = new ScrollPane(mainLayout);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            primaryStage.setScene(new Scene(scrollPane, 1200, 800));
            
            TextField searchField = new TextField();
            Label searchLabel = new Label("Search:");
            HBox searchBox = new HBox(searchLabel, searchField);
            searchBox.setPadding(new Insets(5));
            searchBox.setAlignment(Pos.CENTER_RIGHT);

            // Create the top bar with the menu bar and the search box
            HBox topBar = new HBox(createMenuBar(), searchBox);
            topBar.setAlignment(Pos.CENTER);
            HBox.setHgrow(searchBox, Priority.ALWAYS);
            
            
        });

        return logoView;
    }
    

    private Scene createLoginScene() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        // Add a title at the top
        Label titleLabel = new Label("Login Page");
        titleLabel.setFont(new Font("Arial", 24));
        gridPane.add(titleLabel, 1, 0);

        // Load and add an image to the left side
        Image image = new Image("logo.jpg"); // Update the path
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        gridPane.add(imageView, 0, 1, 1, 3);

        Label userNameLabel = new Label("Username:");
        gridPane.add(userNameLabel, 0, 4);

        TextField userTextField = new TextField();
        gridPane.add(userTextField, 1, 4);

        Label pwLabel = new Label("Password:");
        gridPane.add(pwLabel, 0, 5);

        PasswordField pwBox = new PasswordField();
        gridPane.add(pwBox, 1, 5);

        Button loginButton = new Button("Sign in");
        loginButton.setOnAction(event -> {
            currentUsername = userTextField.getText();
            // Update here to show the logoView scene instead of dashboard
            BorderPane mainLayout = new BorderPane();
            mainLayout.setTop(createMenuBar());
            VBox mainContent = createMainPageView(); // This creates the view with logoView
            mainContent.setAlignment(Pos.CENTER);
            mainLayout.setCenter(mainContent);

            ScrollPane scrollPane = new ScrollPane(mainLayout);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            primaryStage.setScene(new Scene(scrollPane, 1200, 800));
        });
        gridPane.add(loginButton, 1, 6);

        return new Scene(gridPane, 500, 500);
    }
    
    private Scene createAccountCreationScene() {
    	GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        // Add a title at the top
        Label titleLabel = new Label("Account Creation Page");
        titleLabel.setFont(new Font("Arial", 24));
        gridPane.add(titleLabel, 1, 0);

        // Load and add an image to the left side
        Image image = new Image("logo.jpg"); // Update the path
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        gridPane.add(imageView, 0, 1, 1, 3);

        Label userNameLabel = new Label("Username:");
        gridPane.add(userNameLabel, 0, 4);

        TextField userTextField = new TextField();
        gridPane.add(userTextField, 1, 4);

        Label pwLabel = new Label("Password:");
        gridPane.add(pwLabel, 0, 5);

        PasswordField pwBox = new PasswordField();
        gridPane.add(pwBox, 1, 5);

        Button loginButton = new Button("Create Account");
        loginButton.setOnAction(event -> {
            currentUsername = userTextField.getText();
            // Update here to show the logoView scene instead of dashboard
            BorderPane mainLayout = new BorderPane();
            mainLayout.setTop(createMenuBar());
            VBox mainContent = createMainPageView(); // This creates the view with logoView
            mainContent.setAlignment(Pos.CENTER);
            mainLayout.setCenter(mainContent);

            ScrollPane scrollPane = new ScrollPane(mainLayout);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            primaryStage.setScene(new Scene(scrollPane, 1200, 800));
        });
        gridPane.add(loginButton, 1, 6);


        return new Scene(gridPane, 500, 500);
    }
    


    private void showDashboard() {
    	
      TextField searchField = new TextField();
    	Label searchLabel = new Label("Search:");
        searchLabel.setPadding(new Insets(5));
         
    	HBox searchBox = new HBox(searchLabel,searchField);
    	VBox searhAndCartBox = new VBox(searchBox, cartItemCountLabel);
    	VBox homeSectionView = new VBox(createLogoView(), createSectionView("Home"));
        
    	Label userGreeting = new Label("Hello, " + currentUsername); 
    	
        BorderPane borderPaneBar = new BorderPane();
        MenuBar menuBar = createMenuBar();
        borderPaneBar.setTop(menuBar);
        borderPaneBar.setRight(searhAndCartBox);
        
        
        BorderPane mainPane= new BorderPane();
        mainPane.setTop(borderPaneBar);
        mainPane.setCenter(homeSectionView);
        
        mainPane.setStyle("-fx-background-color: white;");

     // Create the ScrollPane and set its content
        ScrollPane scrollMaiPane = new ScrollPane();
        scrollMaiPane.setContent(mainPane);
        scrollMaiPane.setFitToWidth(true);
        scrollMaiPane.setFitToHeight(true);
        Scene scene = new Scene(scrollMaiPane, 1200, 800);
        primaryStage.setScene(scene);
        
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu menMenu = new Menu("Men");
        MenuItem menItem = new MenuItem("Men's Clothing");
        menItem.setOnAction(e -> switchToSection("Men's Clothing"));
        menMenu.getItems().add(menItem);

        Menu womenMenu = new Menu("Women");
        MenuItem womenItem = new MenuItem("Women's Clothing");
        womenItem.setOnAction(e -> switchToSection("Women's Clothing"));
        womenMenu.getItems().add(womenItem);

        Menu bridalMenu = new Menu("Bridal");
        MenuItem bridalItem = new MenuItem("Bridal' Clothing");
        bridalMenu.setOnAction(e -> switchToSection("Bridal' Clothing"));
        bridalMenu.getItems().add(bridalItem);

        Menu cartMenu = new Menu("Cart");
        MenuItem cartItem = new MenuItem("Shopping Cart");
        cartItem.setOnAction(e -> switchToSection("Shopping Cart"));
        cartMenu.getItems().add(cartItem);


        Menu accountMenu = new Menu("Account");
        MenuItem loginItem = new MenuItem("Login");
        loginItem.setOnAction(e -> primaryStage.setScene(createLoginScene()));

        MenuItem createAccountItem = new MenuItem("Create Account");
        createAccountItem.setOnAction(e -> primaryStage.setScene(createAccountCreationScene()));

        accountMenu.getItems().addAll(loginItem, createAccountItem);

        menuBar.getMenus().addAll(menMenu, womenMenu, bridalMenu, cartMenu, accountMenu);
        menuBar.setStyle("-fx-background-color: #4a5759; -fx-font-size: 16px; fx-text-color: white;");

        return menuBar;
    }

    private void switchToSection(String section) {
        BorderPane borderPane = new BorderPane();
        MenuBar menuBar = createMenuBar();
        borderPane.setTop(menuBar);

        // Handle different sections
        if ("Home".equals(section)) {
            borderPane.setCenter(createSectionView("Home"));
        } else if ("Shopping Cart".equals(section)) {
            borderPane.setCenter(createCartView());
        } else if ("Bridal".equals(section)) {
            borderPane.setCenter(maternityPane());
        } else if ("Special".equals(section)) {
            borderPane.setCenter(specialPane());
        } else {
            // Default case for other sections, if any
            borderPane.setCenter(createSectionView(section));
        }

        primaryStage.setScene(new Scene(borderPane, 1200, 800));
    }

    private VBox createSectionView(String sectionTitle) {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        
        if (!"Home".equals(sectionTitle)) {
            ImageView logoView = createLogoView();
            vBox.getChildren().add(logoView);
        }
        
    

       // Label sectionLabel = new Label(sectionTitle);
        //sectionLabel.setStyle("-fx-font-size: 24px;");
       // FileInputStream inputStream = new FileInputStream("Logo.png");


        if ("Home".equals(sectionTitle)) {
        
        Image special = new Image("mainImage.jpg");
        ImageView specialView = new ImageView(special);
        specialView.setFitWidth(1000);
        specialView.setFitHeight(500);
        vBox.getChildren().add(specialView);
        specialView.setOnMouseClicked(event -> {
        	switchToSection("Special");
           
        });
    	
        	
        HBox hbox= new HBox();
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(10));
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(
        		sectionImageButton("temp.jpg","Maternity"),
        		sectionImageButton("temp.jpg","Kid"),
        		sectionImageButton("temp.jpg","Baby")
        		);
        
        ;
        vBox.getChildren().addAll(hbox);
           
        } else {
            vBox.getChildren().addAll(
                createItemButton(sectionTitle + " Item 1"),
                createItemButton(sectionTitle + " Item 2")
            );
        }

        return vBox;
    }

    private ImageView importImage(String imageName)
    {
    	ImageView imageView = new ImageView(imageName);
    	imageView.setFitWidth(100);
        imageView.setFitHeight(150);
        
        return  imageView;
    }
    
    private VBox imageAndButtonBox(String name, String buttonName) 
 
    {
    	
    	VBox vbox= new VBox(importImage(name),createItemButton(buttonName));
    	
    	return vbox;
    }
    private VBox sectionImageButton(String name, String buttonName) 
    
    {
    	
    	VBox vbox= new VBox(importImage(name),sectionName(buttonName));
    	vbox.setAlignment(Pos.CENTER);
    	
    	return vbox;
    }
    
    private Button sectionName(String sectionName) {
    	String section = sectionName;
    	Button name = new Button (sectionName);
    	name.setOnAction(event ->{
    		switchToSection(section);
    		
    	});
    	
    	return name;
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
        
        ImageView logoView = createLogoView();
        vBox.getChildren().add(logoView);

        Label cartLabel = new Label("Shopping Cart");
        cartLabel.setStyle("-fx-font-size: 24px;");
        vBox.getChildren().add(cartLabel);

        for (Map.Entry<String, Integer> entry : cart.getItems().entrySet()) {
            String itemName = entry.getKey();
            Integer itemCount = entry.getValue();

            Label itemLabel = new Label(itemName + " - Quantity: " + itemCount);
            vBox.getChildren().add(itemLabel);
        }
        
        Button checkoutButton = new Button("Checkout");
        checkoutButton.setOnAction(event -> primaryStage.setScene(createCheckoutScene()));
        vBox.getChildren().add(checkoutButton);

        return vBox;
    }
    
    private Scene createCheckoutScene() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        // Add fields for name, number, email, address, city, state, zip, and card number
        addFieldToGrid(gridPane, "Name:", 0);
        addFieldToGrid(gridPane, "Phone Number:", 1);
        addFieldToGrid(gridPane, "Email Address:", 2);
        addFieldToGrid(gridPane, "Address:", 3);
        addFieldToGrid(gridPane, "City:", 4);
        addFieldToGrid(gridPane, "State:", 5);
        addFieldToGrid(gridPane, "Zip Code:", 6);
        addCardNumberField(gridPane, "Card Number:", 7);

        // Add a submit button
        Button submitButton = new Button("Submit");
        gridPane.add(submitButton, 1, 8);
        submitButton.setOnAction(event -> handleCheckout()); // Implement this method to handle checkout logic

        return new Scene(gridPane, 400, 500);
    }

    private void addFieldToGrid(GridPane grid, String label, int row) {
        grid.add(new Label(label), 0, row);
        grid.add(new TextField(), 1, row);
    }

    private void addCardNumberField(GridPane grid, String label, int row) {
        TextField cardField = new TextField();
        cardField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                cardField.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (cardField.getText().length() > 16) {
                cardField.setText(cardField.getText().substring(0, 16));
            }
        });
        grid.add(new Label(label), 0, row);
        grid.add(cardField, 1, row);
    }

    
    private void handleCheckout() {

    	String orderNumber = generateRandomOrderNumber();

        // Show success popup
        showOrderSuccessPopup(orderNumber);
    }

    private String generateRandomOrderNumber() {
        // Generate a random number for the order
        
        int randomNum = 100000 + (int) (Math.random() * 900000); // Generates a 6-digit number
        return "Order #" + randomNum;
    }

    private void showOrderSuccessPopup(String orderNumber) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(primaryStage);

        VBox popupContent = new VBox(10);
        popupContent.setAlignment(Pos.CENTER);
        popupContent.setPadding(new Insets(10));
        popupContent.setStyle("-fx-background-color: lightblue; -fx-border-color: black; -fx-border-width: 2;");

        Label successLabel = new Label("Order Successful!");
        Label orderNumberLabel = new Label(orderNumber);

        // Get the total number of items in the cart
        int totalItems = cart.getTotalItemCount();
        Label itemCountLabel = new Label("Total items in cart: " + totalItems);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> popupStage.close());

        popupContent.getChildren().addAll(successLabel, orderNumberLabel, itemCountLabel, closeButton);

        Scene popupScene = new Scene(popupContent, 300, 150);
        popupStage.setScene(popupScene);

        popupStage.showAndWait();
    }

    private GridPane maternityPane()
    {
    	GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        //gridPane.getChildren().addAll(importImage("logo.png"));
        return gridPane;
    }
    private GridPane specialPane() // modify to specials
    {
    	GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        //gridPane.getChildren().addAll(importImage("logo.png"));
        return gridPane;
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
