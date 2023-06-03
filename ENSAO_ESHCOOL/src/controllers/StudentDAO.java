package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import utils.ConnectionDB;
import model.Student;



public class StudentDAO {
	
	
	private static final Logger logger = Logger.getLogger(StudentDAO.class.getName());
	
	private Connection connection;
	
	public StudentDAO(){
		
	try {
		connection =ConnectionDB.getConnection();
     } catch (SQLException e) {
    e.printStackTrace();
      }
	
}
    
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
         
       
            try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM etudiant");//pour executer les donnees liees a la bdd
            ResultSet resultSet = statement.executeQuery();//execute la requette 

            while (resultSet.next()) {//pour parcourir tout les enregistrements 
            	Student student = new Student();
                student.setId(resultSet.getInt("ID"));
                student.setFirstname(resultSet.getString("Firstname"));
                student.setLastname(resultSet.getString("Lastname"));
                student.setEmail(resultSet.getString("Email"));
                student.setLevel(resultSet.getString("Level"));
                studentList.add(student);

         
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentList;
    }
    
    public void addStudent(Student student) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO etudiant (Firstname, Lastname, Email, Level) VALUES (?, ?, ?, ?)");
            
            statement.setString(1, student.getFirstname());//le 1 associe le first name a la 1er occurence de ? 
            statement.setString(2, student.getLastname());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getLevel());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE etudiant SET Firstname = ?, Lastname = ?, Email = ?, Level = ? WHERE ID = ?");
            statement.setString(1, student.getFirstname());
            statement.setString(2, student.getLastname());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getLevel());
            statement.setInt(5, student.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudents(List<Student> students) {
        try {
        	
        	
        	
        	// Log avant la suppression
            logger.info("Avant la suppression : " + students.toString());
            
            PreparedStatement statement = connection.prepareStatement("DELETE FROM etudiant WHERE ID = ?");
            for (Student student : students) {
                statement.setInt(1, student.getId());
                statement.addBatch();
            }

            statement.executeBatch();
            
            
            
            // Effectuer une nouvelle requête de sélection pour obtenir les données mises à jour
            List<Student> updatedStudents = getAllStudents();
            
            
            
         // Log après la suppression
            
            logger.info("Après la suppression : " + updatedStudents.toString());
            
            PreparedStatement resetStatement = connection.prepareStatement("ALTER TABLE etudiant AUTO_INCREMENT = 1");
            resetStatement.executeUpdate();
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
