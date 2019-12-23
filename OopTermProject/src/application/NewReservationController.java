package application;

import java.io.IOException;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class NewReservationController implements SignUp{
   @FXML
   private Label labelInfo;
   
    @FXML
    private TextField DaySelector;

    @FXML
    private TextField BedSelector;

    @FXML
    private TextField ViewSelector;
    @FXML
    private Button menu;
    @FXML
    private Button SaveResButton;
   
    PreparedStatement preparedStatement3;
    Connection conn;
    public NewReservationController(){
    	conn = (Connection) MySqlConnectionSingleton.getConnection();
    }
    @FXML
    void goM(ActionEvent event) {
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
   
    @FXML
    public void SaveReservation(ActionEvent event) {
    	if(DaySelector.getText().isEmpty() || BedSelector.getText().isEmpty() || ViewSelector.getText().isEmpty()) {
    
		labelInfo.setTextFill(Color.TOMATO);
		labelInfo.setText("Please dont fill any information empty.");
    }else { 
	     saveInfo();
	}
    }
	@Override
	public String saveInfo() {
		try {
			String info = "INSERT INTO reservations(day,size,view) VALUES (?,?,?)";
			preparedStatement3 = (PreparedStatement) conn.prepareStatement(info);
			preparedStatement3.setString(1, DaySelector.getText());
			preparedStatement3.setString(2, BedSelector.getText());
			preparedStatement3.setString(3, ViewSelector.getText());
			preparedStatement3.executeUpdate();
			
			labelInfo.setTextFill(Color.GREEN);
			labelInfo.setText("Your informations has been added successfully");
			return "Success";
		}  catch(SQLException ex) {
			System.out.println(ex.getMessage());
			labelInfo.setTextFill(Color.TOMATO);
			labelInfo.setText(ex.getMessage());
			return "Exception";
	}


    	
}



}



