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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class SignUpController implements Initializable, SignUp{
    @FXML
    private TextField NUName;

    @FXML
    private TextField NUUsername;

    @FXML
    private PasswordField NUPassword;

    @FXML
    private TextField NUEmail;

    @FXML
    private Button signUpButton;
    
    @FXML
    private TextField userType;
    @FXML
    private Label labelS;
    
    @FXML 
    private Button goLogin;
    @FXML
    private TextField loginID;
    PreparedStatement preparedStatement1;
    Connection conn;
    @FXML
    void goLogin(ActionEvent event) {
    	try {
			   Node node =(Node) event.getSource();
			   Stage stage = (Stage) node.getScene().getWindow();
			   stage.close();
			   Scene scene = new Scene(FXMLLoader.load(getClass().getResource("sample.fxml")));
			   stage.setScene(scene);
			   stage.show();
		   }catch(IOException ex) {
			   System.err.println(ex.getMessage());
		   }
    }
    public SignUpController() {
    	conn = (Connection) MySqlConnectionSingleton.getConnection();
    	
    }
	public void initialize(URL url, ResourceBundle reBu) {
		
		
	}
	@FXML
	public void signUpButton(ActionEvent event) {
		if(NUUsername.getText().isEmpty() || NUPassword.getText().isEmpty() || NUName.getText().isEmpty() || NUEmail.getText().isEmpty()) {
			labelS.setTextFill(Color.TOMATO);
			labelS.setText("Please dont fill any information empty.");
		}else {
		     saveInfo();
		}
	}
	@Override
	public String saveInfo() {
		try {
			String info = "INSERT INTO logininfo(loginID,username,password,userType,Name,UserEmail) VALUES (?,?,?,?,?,?)";
			preparedStatement1 = (PreparedStatement) conn.prepareStatement(info);
			preparedStatement1.setString(1, loginID.getText());
			preparedStatement1.setString(2, NUUsername.getText());
			preparedStatement1.setString(3, NUPassword.getText());
			preparedStatement1.setString(4, userType.getText());
			preparedStatement1.setString(5, NUName.getText());
			preparedStatement1.setString(6, NUEmail.getText());
			preparedStatement1.executeUpdate();
			
			labelS.setTextFill(Color.GREEN);
			labelS.setText("Your informations has been added successfully");
			return "Success";
		}  catch(SQLException ex) {
			System.out.println(ex.getMessage());
			labelS.setTextFill(Color.TOMATO);
			labelS.setText(ex.getMessage());
			return "Exception";
		}
	}

	
}

