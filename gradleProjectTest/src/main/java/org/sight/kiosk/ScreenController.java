package org.sight.kiosk;

import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.HashMap;

public class ScreenController {
    private HashMap<String, Parent> screens = new HashMap<>();
    private Scene main;

    public ScreenController(Scene main) {
        this.main = main;
    }

    public void addScreen(String name, Parent pane) {
        screens.put(name, pane);
    }

    public void activate(String name) {
        main.setRoot(screens.get(name));
    }
}