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

public class AnnouncementsController implements Initializable{


    @FXML
    private Button MeBu;
	@FXML
	private TableView<ModelTable>TableA;
	@FXML
	private TableColumn<ModelTable, String>TheA;
	Connection conn;
	public AnnouncementsController() {
		conn=(Connection) MySqlConnectionSingleton.getConnection();
	}
  
    @FXML
    void MeBu(ActionEvent event) {
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
    ObservableList <ModelTable> obsList = FXCollections.observableArrayList();
	@Override
	public void initialize(URL url, ResourceBundle reBu) {
try {
	  ResultSet rs = conn.createStatement().executeQuery("select * from announcements");
	  while(rs.next()) {
		  obsList.add(new ModelTable(rs.getString("TheAnnouncement")));
	  }
}catch(SQLException ex) {
	Logger.getLogger(AnnouncementsController.class.getName()).log(Level.SEVERE,null,ex);
	
}
	TheA.setCellValueFactory(new PropertyValueFactory<>("TheAnnouncement"));
	TableA.setItems(obsList);
	
		
	}



}