package escaperoom;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import com.model.GameSystemFacade; //  from her example -she made the facade into a singleton so she can acess it

import java.io.IOException;

public class App extends Application {
    
    private static Scene scene;

    @Override
    public void start (Stage stage) throws Exception {
        scene = new Scene(loadFXML("home"), 985, 552); //  set the window to img size
        //  Apply stylesheet universally
        // scene.getStylesheets().add(
        //     App.class.getResource("styles.css").toExternalForm()
        // );
        stage.setScene(scene);
        stage.setTitle("Rotten Apple Escape Room");
        
        
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle (WindowEvent w) {
                GameSystemFacade.getInstance().logout();
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
