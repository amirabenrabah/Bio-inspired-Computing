package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;

import ACS.ACS;
import Aetoile.A_etoile;
import Algo_Genetique.AG;
//import Algo_Genetique.main_AG;
import Profondeur.Cls;
import Profondeur.Dfs;
import Profondeur.ReadData;
import Profondeur.Satisfiabilite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class VueController implements Initializable{

    @FXML
    private JFXButton Uploadbutton;
    @FXML
    private JFXButton StartButton;
    private FileChooser fc;
    @FXML
    private Label FileNameLabel;
    @FXML
    private Label PathLabel;
    @FXML
    private JFXRadioButton a_etoile_radio;
    @FXML
    private JFXRadioButton dfs_radio;
    @FXML
    private JFXRadioButton genetique_radio;
    @FXML
    private JFXRadioButton fourmisacs_radio;
    @FXML
    private VBox Vbox_item;
    private ArrayList<Node> node;
    private List<File> f;
    
    public void clear(){
		 if(Vbox_item.getChildren().size()>0){
			 Vbox_item.getChildren().clear();
		 }
		 if(node.size()!=0)node.clear();
    }

@FXML   
void Start(ActionEvent event) throws IOException{
		ArrayList<Cls> Clause = new ArrayList<>();
		 int cpt=0;
		 clear();
		 if(f.size()!=0){
    		for(File ftmp:f){
    			final int j=cpt;
    			Clause = ReadData.Read(ftmp.getAbsolutePath());
    			Satisfiabilite p = new Satisfiabilite(Clause, Clause.size());
    			boolean start=false;
    				if(a_etoile_radio.isSelected()) {
    	    			Singleton.rs=A_etoile.solution(p,cpt);
    	    			Singleton.rs.FileName=ftmp.getName();
    	    			start=true;
    				}
    				if(genetique_radio.isSelected()) {
    					Singleton.rs=new AG().solution(p);
    					Singleton.rs.FileName=ftmp.getName();
    	    			start=true;
    				}
    				if(dfs_radio.isSelected()) {
    					Dfs dfs=new Dfs();
    					Singleton.rs=dfs.Dfs_Sol(p);
    					Singleton.rs.FileName=ftmp.getName();
    	    			start=true;
    				}
    				if(fourmisacs_radio.isSelected()) {
    					Singleton.rs=ACS.solution(p);
    					Singleton.rs.FileName=ftmp.getName();
    	    			start=true;
    				}
    				
    				node.add(FXMLLoader.load(getClass().getResource("item.fxml")));
    				node.get(cpt).setOnMouseEntered(Event->{
    					node.get(j).setStyle("-fx-background-color:#C9C9C9");
    				});
    				node.get(cpt).setOnMouseEntered(Event->{
    					node.get(j).setStyle("-fx-background-color:#f4f4f4");
    				});	
        			Vbox_item.getChildren().add(node.get(cpt));
        			cpt++;

    		
    		}
    	}
    }

    
  @FXML
  void Upload(ActionEvent event) {
	  		
    		fc = new FileChooser();
    		fc.getExtensionFilters().addAll(new ExtensionFilter("CNF Files", "*.cnf"));
        	List<File> tmp=fc.showOpenMultipleDialog(null);
        	f=new ArrayList<>(tmp);
        
        if(f.size()!=0) {
        	a_etoile_radio.setDisable(false);
        	dfs_radio.setDisable(false);
        	genetique_radio.setDisable(false);
        	fourmisacs_radio.setDisable(false);
        	
        }
    }
    
    @FXML
    void a_etoile(ActionEvent event) {
    	if(a_etoile_radio.isSelected()) {
    		dfs_radio.setDisable(true);
    		genetique_radio.setDisable(true);
    		fourmisacs_radio.setDisable(true);
            StartButton.setDisable(false);

    	}else {
    		dfs_radio.setDisable(false);
    		genetique_radio.setDisable(false);
    		fourmisacs_radio.setDisable(false);
            StartButton.setDisable(true);

    	}
    }
    @FXML
    void dfs(ActionEvent event) {
    	if(dfs_radio.isSelected()) {
    	a_etoile_radio.setDisable(true);
    	genetique_radio.setDisable(true);
    	fourmisacs_radio.setDisable(true);
        StartButton.setDisable(false);
    	}else {
        	a_etoile_radio.setDisable(false);
        	genetique_radio.setDisable(false);
        	fourmisacs_radio.setDisable(false);
            StartButton.setDisable(true);

    	}
    }
    @FXML
    void fourmisacs(ActionEvent event) {
    	if(fourmisacs_radio.isSelected()) {
    		a_etoile_radio.setDisable(true);
    		dfs_radio.setDisable(true);
    		genetique_radio.setDisable(true);
            StartButton.setDisable(false);
    	}else {
    		a_etoile_radio.setDisable(false);
    		dfs_radio.setDisable(false);
    		genetique_radio.setDisable(false);
            StartButton.setDisable(true);


    	}

    }

    @FXML
    void genetique(ActionEvent event) {
    	if(genetique_radio.isSelected()) {
    		a_etoile_radio.setDisable(true);
    		dfs_radio.setDisable(true);
    		fourmisacs_radio.setDisable(true);
            StartButton.setDisable(false);
    	}else {
    		a_etoile_radio.setDisable(false);
    		dfs_radio.setDisable(false);
    		fourmisacs_radio.setDisable(false);
            StartButton.setDisable(true);


    	}
    	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		node=new ArrayList<Node>();
		f=new ArrayList<File>();
		
	}
}
