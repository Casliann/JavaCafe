# JavaCafe

**JavaCafe** is a GUI-based client-server application for displaying and managing menus with prices. It allows users to view menu items in a graphical interface while enabling server-side management of the data through JSON files.


## Features

- Display menus and product prices in a clean GUI.
- Manage menu items and prices through server-side commands.
- TCP-based communication between client and server.
- Mark favorite items in the GUI for quick access.
- Menu data is stored in JSON format for easy editing and persistence.


## Getting Started

### Server

1. Start the server program.
2. Menu data is loaded from JSON files.
3. Manage menu items through console commands.

**Command format:**

```
Enter [add/delete] [product-name] (for add: [price] [menu/special])
```

### Client

1. Start the client program.
2. Connect to the server via TCP.
3. The menu is displayed in a graphical interface.

**GUI Usage:**

- All products and prices are clearly displayed.
- Click on product names to mark items (e.g., as favorites).


## About

JavaCafe is designed to provide a simple and intuitive interface for menu management and viewing. It demonstrates practical use of JavaFX for GUI applications combined with server-client communication via TCP and JSON-based data persistence.

