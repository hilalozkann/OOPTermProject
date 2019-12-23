package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.MySqlConnection;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SampleController implements Initializable{

    @FXML
    private TextField userText;

    @FXML
    private   PasswordField passText;
    @FXML 
    private Button loginButton;
   @FXML 
   private Button signUp;
   @FXML 
   private Label lbl_close;
   @FXML 
   private Label lblErrors;
   @FXML 
   private ImageView photo1;
   @FXML 
   private ImageView photo2;
  
   
   Connection con= null;
   PreparedStatement preparedStatement = null;
   ResultSet resultSet = null;
   
   @FXML
   void signUp(ActionEvent event) {
	   try {
		   Node node =(Node) event.getSource();
		   Stage stage = (Stage) node.getScene().getWindow();
		   stage.close();
		   Scene scene = new Scene(FXMLLoader.load(getClass().getResource("signUp.fxml")));
		   stage.setScene(scene);
		   stage.show();
	   }catch(IOException ex) {
		   System.err.println(ex.getMessage());
	   }
   }
   public void handleButtonAction(MouseEvent event) {
	   if(event.getSource()== loginButton) {
		   if(login(event).equals("Success")) {
			   try {
				   Node node =(Node) event.getSource();
				   Stage stage = (Stage) node.getScene().getWindow();
				   stage.close();
				   Scene scene = new Scene(FXMLLoader.load(getClass().getResource("login.fxml")));
				   stage.setScene(scene);
				   stage.show();
			   }catch(IOException ex) {
				   System.err.println(ex.getMessage());
			   }
		   }
	   }
   }
   
   public void initialize(URL url,ResourceBundle rb) {
	   if(con == null) {
		   lblErrors.setTextFill(Color.TOMATO);
		   lblErrors.setText("Server error");
	   } else {
		   lblErrors.setTextFill(Color.GREEN);
		   lblErrors.setText("Server is working");
	   }
   }
   public SampleController() {
	   con = MySqlConnection.conDB();
   
   }
   public int userType = 0;
   private String login(MouseEvent event) {
	   String status = "Success";
	   String username = userText.getText().trim();
	   String password = passText.getText().trim();
	   if(username.isEmpty() || password.isEmpty()) {
		   setlblError(Color.TOMATO,"Do not leave username section empty");
		   status = "Error";
	    } else {
		   String sql = "SELECT * FROM logininfo WHERE username = ? AND password = ?";
		   try {
			   preparedStatement = con.prepareStatement(sql);
			   preparedStatement.setString(1, username);
			   preparedStatement.setString(2,password);
			   resultSet = preparedStatement.executeQuery();
			   if(!resultSet.next()) {
				   setlblError(Color.TOMATO, "Enter correct username or password");
				   status = "Error";
			   }
		   else{
			   
			   userType = resultSet.getInt(4);
			   switch(userType) {
			   case 1:
			   
			    	try {
						   Node node =(Node) event.getSource();
						   Stage stage = (Stage) node.getScene().getWindow();
						   stage.close();
						   Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Manager.fxml")));
						   stage.setScene(scene);
						   stage.show();
					   }catch(IOException ex) {
						   System.err.println(ex.getMessage());
					   }
		break;
			   }
			   setlblError(Color.GREEN,"Login successful");
		   }
			  
		   }catch(SQLException ex) {
				   System.err.println(ex.getMessage());
				   status = "Exception";
			   }  
		   }
   
   return status;
}
   
private void setlblError(Color color,String text) {
	lblErrors.setTextFill(color);
	lblErrors.setText(text);
	System.out.println(text);

		   	
		   
	   }
   }
			  
		   
	   
   
   	