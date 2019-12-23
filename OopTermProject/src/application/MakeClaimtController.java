package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MakeClaimtController {

    @FXML
    private Button submit;

    @FXML
    private Button Menu;
    @FXML
    private TextArea claimtArea;
    @FXML 
    private Label labelC;
    @FXML
    private TextField claimtID;
    
    PreparedStatement preparedStatement2;
    Connection conn;
    @FXML
    void menu(ActionEvent event) {
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


	
	 public MakeClaimtController() {
	    	conn = (Connection) MySqlConnectionSingleton.getConnection();
	    }
		public void initialize(URL url, ResourceBundle reBu) {
			
			
		}

    @FXML
    void submit(ActionEvent event) {
    			     saveInfo();
    			}
    		
    		private String saveInfo() {
    			try {
    			String info = "INSERT INTO claimts(claimtID,claimt) VALUES (?,?)";
    			preparedStatement2 = (PreparedStatement) conn.prepareStatement(info);
    			preparedStatement2.setString(1, claimtID.getText());
    			preparedStatement2.setString(2, claimtArea.getText());
    			preparedStatement2.executeUpdate();
    			
    			labelC.setTextFill(Color.GREEN);
    			labelC.setText("We recieved your claimts/proposals.Thank you.");
    			return "Success";
      }catch(SQLException ex) {
    	  System.out.println(ex.getMessage());
    	  labelC.setTextFill(Color.RED);
    	  labelC.setText(ex.getMessage());
    	  return "Exception";
    	  
    }
    		}
}
