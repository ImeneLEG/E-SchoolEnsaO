package home;

import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Student;
import controllers.*;
import controllers.ProfileScene;



public class Main extends Application {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private StudentDAO studentDAO = new StudentDAO();
    private Stage window;
    private TableView<Student> table;
    private TextField firstnameInput, lastnameInput, emailInput, levelInput;

    public static void main(String[] args) {
        launch(args);
        // Configuration du niveau de log
        logger.setLevel(Level.INFO);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        Image icon = new Image(getClass().getResource("/images/I3.jpg").toExternalForm());
        window.getIcons().add(icon);
        window.setTitle("javaFX-Users-Table");

        Label label = new Label("ENSAO-Students");
        label.getStyleClass().add("TitleLabel");
        label.setAlignment(Pos.CENTER);

        TableColumn<Student, String> firstNameCol = new TableColumn<Student, String>("First Name");
        firstNameCol.setMinWidth(150);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstname"));
        firstNameCol.getStyleClass().add("table-cell");
        firstNameCol.getStyleClass().add("table-row-cell");

        TableColumn<Student, String> lastNameCol = new TableColumn<Student, String>("Last Name");
        lastNameCol.setMinWidth(150);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("lastname"));
        lastNameCol.getStyleClass().add("table-cell");
        lastNameCol.getStyleClass().add("table-row-cell");

        TableColumn<Student, String> emailCol = new TableColumn<Student, String>("Email");
        emailCol.setMinWidth(150);
        emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        emailCol.getStyleClass().add("table-cell");
        emailCol.getStyleClass().add("table-row-cell");

        TableColumn<Student, String> levelCol = new TableColumn<Student, String>("Level");
        levelCol.setMinWidth(150);
        levelCol.setCellValueFactory(new PropertyValueFactory<Student, String>("level"));
        levelCol.getStyleClass().add("table-cell");
        levelCol.getStyleClass().add("table-row-cell");

        TableColumn<Student, String> idCol = new TableColumn<Student, String>("Id");
        idCol.setMinWidth(150);
        idCol.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
        idCol.getStyleClass().add("table-cell");
        idCol.getStyleClass().add("table-row-cell");
        
        Label firstlab=new Label("First name :");
        firstlab.getStyleClass().add("inputLabel");
        firstnameInput = new TextField();
        firstnameInput.setPromptText("First Name");
        firstnameInput.setMinWidth(100);
        firstnameInput.getStyleClass().add("text-field");
        
        Label lastlab=new Label("Last name :");
        lastlab.getStyleClass().add("inputLabel");
        lastnameInput = new TextField();
        lastnameInput.setPromptText("Last Name");
        lastnameInput.setMinWidth(100);
        lastnameInput.getStyleClass().add("text-field");
        
        Label emaillab=new Label("Academic Email :");
        emaillab.getStyleClass().add("inputLabel");
        emailInput = new TextField();
        emailInput.setPromptText("Academic Email");
        emailInput.setMinWidth(100);
        emailInput.getStyleClass().add("text-field");
        
        
        Label levellab=new Label("Level :");
        levellab.getStyleClass().add("inputLabel");
        levelInput = new TextField();
        levelInput.setPromptText("Level");
        levelInput.setMinWidth(100);
        levelInput.getStyleClass().add("text-field");

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addButtonClicked());
        addButton.getStyleClass().add("addButton");

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());
        deleteButton.getStyleClass().add("deleteButton");
        
        VBox boox=new VBox();
        boox.setSpacing(15);
        boox.getChildren().addAll(addButton, deleteButton);

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> updateButtonClicked());
        updateButton.getStyleClass().add("updateButton");
        
        Button viewbutton = new Button("View profil ");
        viewbutton.setOnAction(e -> viewButtonClicked());
        viewbutton.getStyleClass().add("viewbutton");
        
        VBox boox2=new VBox();
        boox2.setSpacing(15);
        boox2.getChildren().addAll(updateButton, viewbutton);
        
        HBox hboox=new HBox();
        hboox.setSpacing(10);
        hboox.getChildren().addAll(boox , boox2 );
        
        VBox hbox = new VBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPadding(new Insets(70, 150, 10, 100)); // top right bottom left
        hbox.setSpacing(10);
        hbox.getChildren().addAll(firstlab,firstnameInput,lastlab, lastnameInput,emaillab, emailInput,levellab,levelInput, hboox);

        table = new TableView<>();
        table.getStyleClass().add("table-view");
        //table.setItems(getUsers());
        table.getColumns().addAll(idCol, firstNameCol, lastNameCol, emailCol, levelCol);
        table.getItems().addAll(studentDAO.getAllStudents());

        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);
        box.getChildren().addAll(label, table);
        

        HBox vbox = new HBox();
        vbox.getChildren().addAll(hbox, box);
        vbox.setStyle("-fx-background-color:  #082d5d;");

        Scene scene = new Scene(vbox);
        scene.getRoot().getStyleClass().add("scene-background");
        scene.getStylesheets().add(getClass().getResource("../fxmll/style.css").toExternalForm());
        window.setScene(scene);
        window.show();
        scene.getRoot().getStyleClass().add("scene-background");
        scene.getStylesheets().add(getClass().getResource("../fxmll/style.css").toExternalForm());
    }

    public ObservableList<Student> getUsers() {
        ObservableList<Student> users = FXCollections.observableArrayList();
        List<Student> students = studentDAO.getAllStudents();
        users.addAll(students);
        return users;
    }

    public void addButtonClicked() {
        Student student = new Student();
        student.setFirstname(firstnameInput.getText());
        student.setLastname(lastnameInput.getText());
        student.setEmail(emailInput.getText());
        student.setLevel(levelInput.getText());
        studentDAO.addStudent(student);
        //refreshTable();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Student Registration");
        alert.setHeaderText("Student Registration");
        alert.setContentText("Record Added!");
        alert.showAndWait();
        refreshTable();
        clearInputs();
    }

    public void deleteButtonClicked() {
        ObservableList<Student> studentSelected = table.getSelectionModel().getSelectedItems();
        if (!studentSelected.isEmpty()) {
            studentDAO.deleteStudents(studentSelected);
            //refreshTable();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Student Deletion");
            alert.setHeaderText("Student Deletion");
            alert.setContentText("Record Deleted!");
            alert.showAndWait();
            refreshTable();
        } else {
            // Show an error message or notification to the user
        }
    }

    public void updateButtonClicked() {
        Student selectedStudent = table.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            selectedStudent.setFirstname(firstnameInput.getText());
            selectedStudent.setLastname(lastnameInput.getText());
            selectedStudent.setEmail(emailInput.getText());
            selectedStudent.setLevel(levelInput.getText());
            studentDAO.updateStudent(selectedStudent);
            //refreshTable();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Student Update");
            alert.setHeaderText("Student Update");
            alert.setContentText("Record Updated!");
            alert.showAndWait();
            refreshTable();
        } else {
            // Show an error message or notification to the user
        }
        clearInputs();
    }

    public void refreshTable() {
        table.getItems().clear();
        table.getItems().addAll(studentDAO.getAllStudents());
    }

    public void clearInputs() {
        firstnameInput.clear();
        lastnameInput.clear();
        emailInput.clear();
        levelInput.clear();
    }
    public void viewButtonClicked() {
        // Récupérer l'étudiant sélectionné dans la table
        Student selectedStudent = table.getSelectionModel().getSelectedItem();
        
        if (selectedStudent != null) {
            // Créer une nouvelle scène pour afficher le profil de l'étudiant
            // Vous pouvez créer une classe distincte pour la scène du profil de l'étudiant
            
            // Par exemple :
            ProfileScene profileScene = new ProfileScene(selectedStudent);
            profileScene.show(); // Méthode pour afficher la scène du profil
        } else {
           System.out.println("profil not found !");
        }
    }
    
}

