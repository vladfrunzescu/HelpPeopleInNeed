package socialnetwork.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import socialnetwork.domain.Nevoie;
import socialnetwork.domain.Persoana;
import socialnetwork.service.Service;
import socialnetwork.utils.observer.Observer;

import java.time.LocalDateTime;

public class ProfilController implements Observer {

    private Service service;
    private Persoana persoana;


    private ObservableList<Nevoie> nevoieModel = FXCollections.observableArrayList();
    private ObservableList<Nevoie> nevoieModel1 = FXCollections.observableArrayList();

    @FXML
    private TableView<Nevoie> tableView;
    @FXML
    private TableColumn<Nevoie,String> tableColumnID;
    @FXML
    private TableColumn<Nevoie,String> tableColumnTitlu;
    @FXML
    private TableColumn<Nevoie,String> tableColumnDescriere;
    @FXML
    private TableColumn<Nevoie,String> tableColumnDeadline;
    @FXML
    private TableColumn<Nevoie,String> tableColumnNevoie;
    @FXML
    private TableColumn<Nevoie,String> tableColumnSalvator;
    @FXML
    private TableColumn<Nevoie,String> tableColumnStatus;

    @FXML
    private TableView<Nevoie> tableView1;
    @FXML
    private TableColumn<Nevoie,String> tableColumnID1;
    @FXML
    private TableColumn<Nevoie,String> tableColumnTitlu1;
    @FXML
    private TableColumn<Nevoie,String> tableColumnDescriere1;
    @FXML
    private TableColumn<Nevoie,String> tableColumnDeadline1;
    @FXML
    private TableColumn<Nevoie,String> tableColumnNevoie1;
    @FXML
    private TableColumn<Nevoie,String> tableColumnSalvator1;
    @FXML
    private TableColumn<Nevoie,String> tableColumnStatus1;

    @FXML
    private TextField textFieldTitlu;
    @FXML
    private TextField textFieldDescriere;
    @FXML
    private DatePicker datePicker;


    @Override
    public void update() {
        this.initModel();
    }



    public void setPage( Persoana p, Service service) {

        this.persoana = p;
        this.service = service;
        service.addObserver(this);
        initModel();
    }

    @FXML
    public void initialize() {
        tableColumnID.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("Id"));
        tableColumnTitlu.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("Titlu"));
        tableColumnDescriere.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("Descriere"));
        tableColumnDeadline.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("Deadline"));
        tableColumnNevoie.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("OmInNevoie"));
        tableColumnSalvator.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("OmSalvator"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("Status"));

        tableView.setItems(nevoieModel);

        tableColumnID1.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("Id"));
        tableColumnTitlu1.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("Titlu"));
        tableColumnDescriere1.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("Descriere"));
        tableColumnDeadline1.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("Deadline"));
        tableColumnNevoie1.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("OmInNevoie"));
        tableColumnSalvator1.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("OmSalvator"));
        tableColumnStatus1.setCellValueFactory(new PropertyValueFactory<Nevoie, String>("Status"));

        tableView1.setItems(nevoieModel1);


    }

    private void initModel() {
        nevoieModel.setAll(service.getAllNevoieByOras(persoana));
        nevoieModel1.setAll(service.getAllNevoieByPersoana(persoana));
    }

    @FXML
    public void handleAlege(){
        try {
            Nevoie n = tableView.getSelectionModel().getSelectedItem();

            if (n != null && n.getStatus().equals("Caut erou!")) {
                Nevoie actualizata = new Nevoie(n.getId(), n.getTitlu(), n.getDescriere(), n.getDeadline(), n.getOmInNevoie(), persoana.getId(), "Erou gasit!");
                service.updateNevoie(actualizata);
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Acestei nevoi i s-a gasit erou!", "Erou gasit!");
            } else {
                MessageAlert.showErrorMessage(null, "Nu s-a putut actualiza nevoia!");
            }
        }catch (Exception ex){
            MessageAlert.showErrorMessage(null, "Nu s-a selectat nimic");
        }
    }

    @FXML
    public void handleAdaugaNevoie(){
        try {
            if (textFieldTitlu.getText() != null && textFieldDescriere.getText() != null && datePicker.getValue() != null) {
                String titlu = textFieldTitlu.getText();
                String descriere = textFieldDescriere.getText();
                LocalDateTime data = datePicker.getValue().atTime(00, 00, 00);

                Nevoie n = new Nevoie(titlu, descriere, data, persoana.getId(), null, "Caut erou!");
                service.addNevoie(n);
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Nevoie adaugata", "Nevoia s-a adaugat cu succes!");
            } else {
                MessageAlert.showErrorMessage(null, "Nu s-a adaugat nevoia!");
            }
        }catch (Exception ex){
            MessageAlert.showErrorMessage(null, ex.getMessage());
        }


    }

    /*

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

            if (first_myenum != null && nume != null && prenume != null && username != null && parola != null && strada != null && numarStrada != null && telefon != null) {
                Persoana entity = new Persoana(nume, prenume, username, parola, first_myenum, strada, telefon, numarStrada);
                service.addPersoana(entity);
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Persoana adaugata", "S-a adaugat persoana cu succes!");
            } else {
                MessageAlert.showErrorMessage(null, "Nu s-a facut adaugarea!");
            }
        }catch (Exception ex){
            MessageAlert.showErrorMessage(null, ex.getMessage());
        }
    }

    @FXML
    public void handleApasa(){

    }

    private void openPage(Persoana p){
        Stage stage = new Stage();
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/profilView.fxml"));
            AnchorPane root = loader.load();
            ProfilController controller = loader.getController();
            // service.addObserver(controller);
            // controller.setPage(persoana, service);
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
            for(Profil e : service.getAllProfil()){
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
