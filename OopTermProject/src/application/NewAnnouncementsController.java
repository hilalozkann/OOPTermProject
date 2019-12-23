package application;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;



public class NewAnnouncementsController implements Initializable{
	
    @FXML
    private Label labelI;
    @FXML
    private TextArea NewA;
    PreparedStatement prepareadStatement4;
    Connection conn;
    public NewAnnouncementsController(){
    	conn = (Connection) MySqlConnectionSingleton.getConnection();
    }





    @FXML
    void SAnn(ActionEvent event) {
    setMessage();
    }
      private String setMessage() {
    	  try {
    		  String message = "INSERT INTO announcements(theAnnouncement) VALUES (?)";
    		  prepareadStatement4 = (PreparedStatement) conn.clientPrepareStatement(message);
    		  prepareadStatement4.setString(1,NewA.getText());
    		  prepareadStatement4.executeUpdate();
    		  
    	      labelI.setTextFill(Color.GREEN);
    		  labelI.setText("The announcement is successfully shared.");
    		  return "Success";
    	  }catch(SQLException ex) {
    		  System.out.println(ex.getMessage());
    	      labelI.setTextFill(Color.RED);
    		  labelI.setText(ex.getMessage());
    		  return "Exception";
    		  
    	  }
      }
    
      public void initialize(URL url,ResourceBundle rb) {
      }
      }
    

