package pvs;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.net.URL;

public class ViewLoader {
    private Pane view;

    public Pane loadView(String fileName) {
        try {                                                           // fxml-Verzeichnis
            URL fileUrl = DashboardController.class.getResource("/pvs/" + fileName + ".fxml");

            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("FXML-Datei konnte nicht unter dem Pfad gefunden werden.");
            }

            FXMLLoader loader = new FXMLLoader(fileUrl);
            view = loader.load();

        } catch (Exception e) {
            System.out.println("No page " + fileName + ". Please check FXMLLoader. Error: " + e.getMessage());
            e.printStackTrace();
        }
        return view;
    }
}