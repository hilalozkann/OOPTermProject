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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SeeReservationsController {


    @FXML
    private TableView<?> SeeR;

    @FXML
    private TableColumn<ResTable,String> Sday;

    @FXML
    private TableColumn<ResTable,String> Sbed;

    @FXML
    private TableColumn<ResTable,String> SView;

    @FXML
    private Button BackTo;
    Connection conn;
    public SeeReservationsController() {
    conn = (Connection) MySqlConnectionSingleton.getConnection();
    }
    @FXML
    void BackTo(ActionEvent event) {
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
    ObservableList <ResTable> nRList = FXCollections.observableArrayList();
    public void initialize(URL url, ResourceBundle reBu) {
    	try {
    		ResultSet rsR = conn.createStatement().executeQuery("select * from reservations");
    		while (rsR.next()) {
    			nRList.add(new ResTable(rsR.getString("day"), null, null));
    			nRList.add(new ResTable(rsR.getString("bed"), null, null));
    			nRList.add(new ResTable(rsR.getString("view"), null, null));
    		}
    	}catch(SQLException ex) {
    		Logger.getLogger(SeeReservationsController.class.getName()).log(Level.SEVERE,null,ex);
    	}
    	Sday.setCellValueFactory(new PropertyValueFactory<>("day"));
    	Sbed.setCellValueFactory(new PropertyValueFactory<>("bed"));
    	SView.setCellValueFactory(new PropertyValueFactory<>("view"));
    	SeeR.setItems(nRList);
    		}
    	
    }

