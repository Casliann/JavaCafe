package cafeboard.server;

import cafeboard.entities.Item;
import cafeboard.storage.JsonStorage;
import java.io.*;
import java.net.Socket;
import java.util.List;

public class  ClientHandler extends Thread {

    private final Socket socket;
    private final List<Item> items;
    private BufferedWriter out;

    public ClientHandler(Socket socket, List<Item> items) {
        this.socket = socket;
        this.items = items;
        try {
            this.out = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));

        } catch (IOException e) {
            System.err.println("Failed to create output stream");
        }
    }
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            String request;
            while ((request = in.readLine()) != null) {
                if ("GET_MENU".equals(request)) {
                    sendMenu(items);
                }
                else if (request.startsWith("FAVOURITE ")) {
                    String itemName = request.substring("FAVOURITE ".length());
                    for (Item item : items) {
                        if (itemName.equals(item.getName())) {
                            item.toggleFavourite();
                        }
                    }
                    sendMenu(items);

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMenu(List<Item> items) {
        try {
            String json = JsonStorage.serialize(items);
            if (json != null) {
                out.write(json);
            }
            out.newLine();
            out.flush();

            System.out.println("Menu sent to client");

        } catch (IOException e) {
            System.err.println("Error sending menu to client");
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }
}