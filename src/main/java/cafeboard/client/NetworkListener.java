package cafeboard.client;

import cafeboard.entities.Item;
import cafeboard.storage.JsonStorage;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.application.Platform;
import java.io.*;
import java.net.Socket;
import java.util.List;

public class NetworkListener implements Runnable {

    private final String host;
    private final int port;
    private Socket socket;
    private List<Item> receivedItems;
    private UiController guiController;
    private BufferedWriter out;

    public NetworkListener(String host, int port, UiController guiController) {
        this.host = host;
        this.port = port;
        this.guiController = guiController;
        try {
            this.socket = new Socket(host, port);
            this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        guiController.setNetworkListener(this);
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            out.write("GET_MENU");
            out.newLine();
            out.flush();

            String json;

            while ((json = in.readLine()) != null)  {
                receivedItems = JsonStorage.deserialize(
                        json,
                        new TypeReference<List<Item>>() {
                        });

                if (receivedItems != null) {
                    System.out.println("Client received " + receivedItems.size() + " items");
                }
                Platform.runLater(() -> {
                        guiController.changeGui(receivedItems);
                    });
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFavouriteToggle(String itemName) {
        try {
            out.write("FAVOURITE " + itemName);
            out.newLine();
            out.flush();

        } catch (IOException e) {
            System.err.println("Error sending message to server: FAVOURITE" + itemName);
        }
    }
}
