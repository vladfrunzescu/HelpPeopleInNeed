package socialnetwork.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import socialnetwork.domain.Persoana;
import socialnetwork.service.Service;
import socialnetwork.utils.Type;
import socialnetwork.utils.observer.Observer;

import java.util.Arrays;
import java.util.List;

public class InregistrareController implements Observer {

    private Service service;

    private ObservableList<Persoana> persoanaModel = FXCollections.observableArrayList();
    
    @FXML
    private TextField textFieldNume;
    @FXML
    private TextField textFieldPrenume;
    @FXML
    private ComboBox<Type> comboBoxOras;
    @FXML
    private TextField textFieldParola;
    @FXML
    private TextField textFieldUsername;
    @FXML
    private TextField textFieldStrada;
    @FXML
    private TextField textFieldNumarStrada;
    @FXML
    private TextField textFieldTelefon;
    @FXML
    private ListView<Persoana> listView;
    @FXML
    private TextField textFieldRepetaParola;


    @Override
    public void update() {
        this.initModel();
    }

    public void setPage( Service service) {

        this.service = service;
        service.addObserver(this);
        initModel();
    }

    @FXML
    public void initialize() {
        List<Type> options = Arrays.asList(Type.values());
        ObservableList<Type> observableTypeList = FXCollections.observableArrayList(options);
        comboBoxOras.setItems(observableTypeList);
    }

    private void initModel() {

        listView.setCellFactory(lv -> new ListCell<Persoana>() {
            @Override
            public void updateItem(Persoana entity, boolean empty) {
                super.updateItem(entity, empty) ;
                setText(empty ? null : "Username: " + entity.getUsername() );
            }
        });

        persoanaModel = FXCollections.observableArrayList(service.getAllPersoana());
        listView.setItems(persoanaModel);
    }

    @FXML
    public void handleInregistreazaButton(){
        try {
            Type first_myenum = comboBoxOras.getValue();
            String nume = textFieldNume.getText();
            String prenume = textFieldPrenume.getText();
            String username = textFieldUsername.getText();
            String parola = textFieldParola.getText();
            String strada = textFieldStrada.getText();
            String numarStrada = textFieldNumarStrada.getText();
            String telefon = textFieldTelefon.getText();
            String rep = textFieldRepetaParola.getText();

            if (first_myenum != null && nume != null && prenume != null && username != null && parola != null && strada != null && numarStrada != null && telefon != null && rep!= null && rep.equals(parola)) {
                Persoana entity = new Persoana(nume, prenume, username, parola, first_myenum, strada, telefon, numarStrada);
                service.addPersoana(entity);
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Persoana adaugata", "S-a adaugat persoana cu succes!");
                openPage(entity);
            } else {
                MessageAlert.showErrorMessage(null, "Nu s-a facut adaugarea!");
            }
        }catch (Exception ex){
            MessageAlert.showErrorMessage(null, ex.getMessage());
        }
    }

    @FXML
    public void handleApasa(){
        Persoana p = listView.getSelectionModel().getSelectedItem();
        if(p!=null){
            openPage(p);
        }
    }

    private void openPage(Persoana p){
        Stage stage = new Stage();
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/profilView.fxml"));
            AnchorPane root = loader.load();
            ProfilController controller = loader.getController();
            service.addObserver(controller);
            controller.setPage(p, service);
            stage.setTitle("Profil!");
            stage.setScene(new Scene(root, 632, 405));
            stage.show();

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    /*
    @FXML
    public void handleTrimiteButton() {
        try {
            String text_message = textField.getText();
            List<Long> all_entities = new ArrayList<>();
            for(Inregistrare e : service.getAllInregistrare()){
                if(!e.equals(unu)){
                    all_entities.add(e.getId());
                }
            }

            //service.addEntity();
            //MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Mesaj", "Mesajul a fost trimis cu succes!");
            textField.setText("");
        }
        catch (Exception ex) {
            MessageAlert.showErrorMessage(null, ex.getMessage());
        }
    }

    @FXML
    public void handleRetragButton(){
        try{
            //if(service.action() == null){
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "", "!");
            //}
        } catch(Exception ex){
            MessageAlert.showErrorMessage(null, ex.getMessage());
        }
    }

    @FXML
    public void handleRevinButton(){
        try{
            //if(service.action() == null){
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "", "!");
            //}
        } catch(Exception ex){
            MessageAlert.showErrorMessage(null, ex.getMessage());
        }
    }

     */
}
