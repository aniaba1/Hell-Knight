module io.github.aniaba1 {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;

    opens io.github.aniaba1 to javafx.fxml, javafx.graphics, javafx.controls;
    exports io.github.aniaba1;
}
