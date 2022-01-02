package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ItemController implements Initializable{

    @FXML
    private Label File_name_label;

    @FXML
    private Label Result_label;

    @FXML
    private Label Instance_label;

    @FXML
    private Label Time_Label;
    
    
    



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		File_name_label.setText("FileName: "+Singleton.rs.FileName);
		Result_label.setText("Result: "+Singleton.rs.Result);
		Instance_label.setText("Instance: "+Singleton.rs.Nb_clause);
		Time_Label.setText("Time: "+Singleton.rs.Time+"ms");
	
	}

}
