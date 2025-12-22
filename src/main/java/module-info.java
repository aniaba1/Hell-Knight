module io.github.aniaba1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens io.github.aniaba1 to javafx.fxml;
    exports io.github.aniaba1;
}
