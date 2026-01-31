package cafeboard.launcher;

import cafeboard.server.CafeBoardServer;

public class ServerLauncher {
    public static void main(String[] args) {
        new CafeBoardServer(5000).start();
    }
}
