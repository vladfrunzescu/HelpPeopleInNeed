package socialnetwork;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import socialnetwork.controller.InregistrareController;
import socialnetwork.domain.Nevoie;
import socialnetwork.domain.Persoana;
import socialnetwork.domain.validators.NevoieValidator;
import socialnetwork.domain.validators.PersoanaValidator;
import socialnetwork.repository.Repository;
import socialnetwork.repository.file.NevoieFile;
import socialnetwork.repository.file.PersoanaFile;
import socialnetwork.service.Service;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private static List<String> argumente = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Repository<Long, Persoana> persoanaRepo = new PersoanaFile("data/persoana.txt", new PersoanaValidator());
        Repository<Long, Nevoie> nevoieRepo = new NevoieFile("data/nevoie.txt", new NevoieValidator());
        Service service = new Service(persoanaRepo, nevoieRepo);
        Stage stage = new Stage();
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/inregistrareView.fxml"));
            AnchorPane root = loader.load();
            InregistrareController controller = loader.getController();
            service.addObserver(controller);
            controller.setPage(service);
            stage.setTitle("Inregistrare!");
            stage.setScene(new Scene(root, 632, 405));
            stage.show();

        } catch(Exception ex){
            ex.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}



