package cafeboard.server;

import cafeboard.entities.BoardItem;
import cafeboard.entities.Item;
import cafeboard.storage.FileStorage;
import cafeboard.storage.JsonStorage;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class CafeBoardServer {

    private final int port;
    private List<Item> items;
    private BoardItem menu;
    private ClientHandler handler;
    File file = new File("demo/src/main/resources/data/menu.json");

    public CafeBoardServer(int port) {
        this.port = port;
    }

    public void start() {
        if (!file.exists()) {
            DataInitializer dataInitializer = new DataInitializer();
            menu = dataInitializer.getData();
            saveMenu(menu.getMenuItems(), file);
        } else {
            String json = FileStorage.load(file.getPath());
            items = JsonStorage.deserialize(
                json,
                new TypeReference<List<Item>>() {}
            );
            menu = new BoardItem("Cat Cafe", items);
        }

        System.out.println("Server loaded " + menu.getMenuItems().size() + " items");

        // MenuAdmin Thread
        serverAdminStarter();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket client = serverSocket.accept();
                handler = new ClientHandler(client, menu.getMenuItems());
                handler.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serverAdminStarter() {
        Thread adminThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            MenuService menuService = new MenuService(menu);
            while (true) {
                System.out.println("Enter [add/delete] [product-name] (just for add:[price] [menu/special])\n");
                String line = scanner.nextLine();
                menuService.handleCommand(line);
                if (handler != null && handler.isAlive() && handler.getSocket().isConnected()) {
                    handler.sendMenu(menu.getMenuItems());
                    saveMenu(menu.getMenuItems(), file);
                } else {
                    System.out.println("No client connected!");
                }
            }
        });
        adminThread.start();
    }

    public void saveMenu(List<Item> menulist, File file) {
        String json = JsonStorage.serialize(menulist);
        FileStorage.save(file.getPath(), json);
    }
}
