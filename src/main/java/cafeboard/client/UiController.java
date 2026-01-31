package cafeboard.client;

import cafeboard.entities.Item;
import cafeboard.storage.FileLogger;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

public class UiController {
    @FXML
    private ImageView jelly;

    @FXML
    private ImageView milkshake;

    @FXML
    private ImageView cake;

    @FXML
    private ImageView coffee;

    @FXML
    private ImageView pancake;

    @FXML
    private VBox menuItems;

    @FXML
    private Button faveButton;

    private NetworkListener networkListener;
    private boolean onlyFavourites = false;
    private List<Item> menu = new ArrayList<>();

    public void setNetworkListener(NetworkListener listener) {
        this.networkListener = listener;
    }

    public void changeGui(List<Item> menulist) {
        this.menu = menulist;
        menuItems.getChildren().clear();
        Region spacer = new Region();
        spacer.setMinHeight(70);
        menuItems.getChildren().add(spacer);

        List <Item> toShow = new ArrayList<>();
        if (onlyFavourites) {
            for (Item item : menulist) {
                if (item.isFavourite()) {
                    toShow.add(item);
                }
            }
        } else {
            toShow = menulist;
        }

        for (Item item : toShow) {
            Label nameLabel = new Label(item.getName() + (item.isFavourite() ? "\u2764" : "") + (item.getType().equals("special") ? "*" : ""));
            nameLabel.setFont(Font.font("Segoe UI Symbol"));
            nameLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20px;");

            Label priceLabel = new Label(String.format("%.2f €", item.getPrice()));
            priceLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20px;");

            Region space = new Region();
            HBox.setHgrow(space, Priority.ALWAYS);

            HBox line = new HBox(10, nameLabel, space, priceLabel);
            line.setAlignment(Pos.CENTER_LEFT);
            menuItems.getChildren().add(line);

            line.setOnMouseClicked(event -> {
                networkListener.sendFavouriteToggle(item.getName());
            });
        }
        FileLogger.log("GUI updated with " + menulist.size() + " items");
    }

    @FXML
    public void initialize() {
        ImageView[] images = {milkshake, jelly, cake, coffee, pancake};

        for (ImageView iv : images) {
            RotateTransition rotate = new RotateTransition(Duration.seconds(1 + Math.random()), iv);
            rotate.setByAngle(10);
            rotate.setAutoReverse(true);
            rotate.setCycleCount(RotateTransition.INDEFINITE);
            rotate.play();

            TranslateTransition translate = new TranslateTransition(Duration.seconds(2 + Math.random()), iv);
            translate.setByX(3);
            translate.setAutoReverse(true);
            translate.setCycleCount(TranslateTransition.INDEFINITE);
            translate.play();
        }
        faveButton.setOnAction(e -> {
            onlyFavourites = !onlyFavourites;
            changeGui(menu);
        });
    }
}
