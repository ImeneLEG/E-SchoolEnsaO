package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Student;

public class MainController implements Initializable {

    @FXML
    private TableView<Student> table;
    @FXML
    private TextField firstnameInput;
    @FXML
    private TextField lastnameInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField levelInput;

    private StudentDAO studentDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentDAO = new StudentDAO();
        table.setItems(getUsers());
    }

    public ObservableList<Student> getUsers() {
        ObservableList<Student> users = FXCollections.observableArrayList();
        users.addAll(studentDAO.getAllStudents());
        return users;
    }

    @FXML
    public void addButtonClicked() {
        Student student = new Student();
        student.setFirstname(firstnameInput.getText());
        student.setLastname(lastnameInput.getText());
        student.setEmail(emailInput.getText());
        student.setLevel(levelInput.getText());
        studentDAO.addStudent(student);
        showAlert("Student Registration", "Record Added!");
        refreshTable();
        clearInputs();
    }

    @FXML
    public void deleteButtonClicked() {
        ObservableList<Student> studentSelected = table.getSelectionModel().getSelectedItems();
        if (!studentSelected.isEmpty()) {
            studentDAO.deleteStudents(studentSelected);
            showAlert("Student Deletion", "Record Deleted!");
            refreshTable();
        } else {
            // Show an error message or notification to the user
        }
    }

    @FXML
    public void updateButtonClicked() {
        Student selectedStudent = table.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            selectedStudent.setFirstname(firstnameInput.getText());
            selectedStudent.setLastname(lastnameInput.getText());
            selectedStudent.setEmail(emailInput.getText());
            selectedStudent.setLevel(levelInput.getText());
            studentDAO.updateStudent(selectedStudent);
            showAlert("Student Update", "Record Updated!");
            refreshTable();
        } else {
            // Show an error message or notification to the user
        }
        clearInputs();
    }

    @FXML
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

    private void refreshTable() {
        table.getItems().clear();
        table.getItems().addAll(studentDAO.getAllStudents());
    }

    private void clearInputs() {
        firstnameInput.clear();
        lastnameInput.clear();
        emailInput.clear();
        levelInput.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
