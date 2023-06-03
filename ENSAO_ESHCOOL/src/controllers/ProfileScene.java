package controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import com.itextpdf.*;
import com.itextpdf.text.*;
//import com.itextpdf.text.Chunk;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.print.PrinterJob;
import model.Student;
import controllers.*;


public class ProfileScene {

private Student student;

public ProfileScene(Student student) {
    this.student = student;
}

public void show() {
    // Créez une nouvelle fenêtre ou remplacez la scène actuelle de la fenêtre principale
    // avec la scène du profil de l'étudiant
    
    // Par example :
    Stage profileWindow = new Stage();
    Image icon = new Image(getClass().getResource("/images/I3.jpg").toExternalForm());
    profileWindow.getIcons().add(icon);
    
    Image image = new Image(getClass().getResource("/images/I1.jpg").toExternalForm());
    
    ImageView imageView = new ImageView(image);
    imageView.setFitWidth(200); // Adjust the desired width of the image
    imageView.setPreserveRatio(true); // Maintain the original aspect ratio of the image
    imageView.setStyle("-fx-border-color: red; -fx-border-width: 2px;"); // Set the desired CSS styles
 
    
    
     


    profileWindow.setTitle("Student Profile");

    // Créez les éléments de l'interface utilisateur pour afficher les détails du profil de l'étudiant
    // Utilisez les attributs de l'objet Student pour afficher les informations appropriées
    
    // Par exemple :
    
    
    Label nameLabel = new Label("FirstName : " + student.getFirstname()+"\n");
    nameLabel.setId("nameLabel");
    Label lastnameLabel = new Label("LastName : " + " " + student.getLastname()+"\n");
    lastnameLabel.setId("nameLabel");

    
    nameLabel.setId("nameLabel");
    Label emailLabel = new Label("Academic Email : " + student.getEmail());
    emailLabel.setId("emailLabel");
    Label levelLabel = new Label("Level: " + student.getLevel());
    levelLabel.setId("levelLabel");
    
    
    //print button 
    
    
    Button print = new Button("Print") ;
    print.setId("Printbutton");
    print.setOnAction(e -> printButtonClicked());
    

    VBox profileBox = new VBox(10);
    profileBox.setSpacing(40);
    
    profileBox.setAlignment(Pos.CENTER);
    profileBox.getChildren().addAll(imageView,nameLabel,lastnameLabel, emailLabel, levelLabel,print);

    Scene profileScene = new Scene(profileBox, 500, 600);
    profileScene.getRoot().getStyleClass().add("background");
    profileScene.getStylesheets().add(getClass().getResource("../fxmll/style.css").toExternalForm());
    profileWindow.setScene(profileScene);
    profileWindow.show();
}

public void printButtonClicked() {
    // Générer le rapport en PDF
    generatePDFReport();
}
public void generatePDFReport() {
	
    // Utilisez une bibliothèque de création de PDF telle que iText, Apache PDFBox, etc.
    // pour générer le fichier PDF récapitulatif
    
    // Par exemple, en utilisant iText :
    try {
        String fileName = "Student_Profile.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();
        
        // Ajoutez le contenu du rapport au document PDF
        addContentToPDF(document);
        
        document.close();
        
        // Affichez un message de confirmation à l'utilisateur
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Print Report");
        alert.setHeaderText("Print Report");
        alert.setContentText("PDF report generated successfully!");
        alert.showAndWait();
        
        // Ouvrez le fichier PDF généré
        Desktop.getDesktop().open(new File(fileName));
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public void addContentToPDF(Document document) throws DocumentException {
    // Ajoutez les détails du profil de l'étudiant au document PDF
    
    // Par exemple :
    Paragraph profileDetails = new Paragraph();
    profileDetails.add(new Phrase("FirstName : " + student.getFirstname()+"\n"));
    profileDetails.add(Chunk.NEWLINE);
    profileDetails.add(new Phrase("LastName : " + " " + student.getLastname()+"\n"));
    profileDetails.add(Chunk.NEWLINE);
    profileDetails.add(new Phrase("Email: " + student.getEmail()+"\n"));
    profileDetails.add(Chunk.NEWLINE);
    profileDetails.add(new Phrase("Level: " + student.getLevel()));
    
    
    document.add(profileDetails);
}
}
