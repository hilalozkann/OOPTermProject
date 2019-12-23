package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SeeComplaintController implements Initializable{

    @FXML
    private Button BackTo;

    @FXML
    private TableView<ClaimtTable>ClaimtTable1;

    

    @FXML
    private TableColumn<ClaimtTable,String> TheClaimt;
    Connection conn;
    public SeeComplaintController() {
    	conn = (Connection) MySqlConnectionSingleton.getConnection();
    
    }
    @FXML
    void GoB(ActionEvent event){
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
    }
    ObservableList <ClaimtTable> cList = FXCollections.observableArrayList();
    public void initialize(URL url, ResourceBundle reBu) {
    	try {
    		ResultSet rs2 = conn.createStatement().executeQuery("select * from claimts");
    		while(rs2.next()) {
    			cList.add(new ClaimtTable(rs2.getString("claimt")));
    			cList.add(new ClaimtTable(rs2.getString("claimtID")));
    		}
    	}catch(SQLException ex) {
    		Logger.getLogger(SeeComplaintController.class.getName()).log(Level.SEVERE,null,ex);
    	}
    	TheClaimt.setCellValueFactory(new PropertyValueFactory<ClaimtTable,String>("claimt"));
    
    	ClaimtTable1.setItems(cList);
    		
    			
    		}
    	}

    
	
	
		
	

