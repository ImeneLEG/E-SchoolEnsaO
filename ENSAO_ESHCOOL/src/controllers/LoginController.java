package controllers;



//import home.Main;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.scene.Parent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.ConnectionDB;

/**
 *
 * @author oXCToo
 */
public class LoginController implements Initializable {
	
	
	
	
	

    @FXML
    private Label lblErrors;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnSignin;

    /// -- 
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    public void handleButtonAction(MouseEvent event) throws IOException {

        if (event.getSource() == btnSignin) {
            //login here
            if (logIn().equals("Success")) {
                Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				//stage.setMaximized(true);
			
               
				
				 // Load the main.fxml file
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmll/main.fxml"));
	            Parent root = loader.load();
	            
	            // Create a new scene with the loaded fxml file
	            Scene scene = new Scene(root);
	            scene.getRoot().getStyleClass().add("scene-background");
	            scene.getStylesheets().add(getClass().getResource("../fxmll/style.css").toExternalForm());
	            // Set the scene to the stage and show the stage
	            stage.setScene(scene);
	            stage.show();
				
				
				

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (connection == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Check");
        } else {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server is up : Good to go");
        }
    }

    public LoginController() throws SQLException {
    	connection =ConnectionDB.getConnection();
        
        
       
    }

    //we gonna use string to check for status
    private String logIn() {
        String status = "Success";
        String email = txtUsername.getText();
        String password = txtPassword.getText();
        if(email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            //query
            String sql = "SELECT * FROM admins Where email = ? and password = ?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    status = "Error";
                } else {
                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
                    status = "Success";
                   
                    
                   
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }
        
        return status;
    }
    
    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }
}