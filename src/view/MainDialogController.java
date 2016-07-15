package view;

import amazon.Bestellabruf;
import amazon.data.Artikel;
import evntHandler.KeyEventHandlerTableview;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class MainDialogController {
	private Bestellabruf task = null;
	
    @FXML
    private TextField user;
    
    @FXML
    private PasswordField pwd;
    
    @FXML
    private TableView tableview;
    
    @FXML
    private Label statuslabel;
    
    private SimpleStringProperty status = new SimpleStringProperty("");
    
    @FXML
    private void runPressed(ActionEvent event) {
    	if (task != null) {
    		return;
    	}
    	task = new Bestellabruf(user.getText(), pwd.getText(), status);
    	tableview.setItems(task.getListe());
    	 (new Thread(task)).start();
    }
    
    public MainDialogController() {
    }
    
    public void set() {
    	statuslabel.textProperty().bind(status);
    	tableview.getColumns().clear();
    	tableview.setOnKeyPressed(new KeyEventHandlerTableview());
    	tableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	
    	TableColumn<Artikel, String> b1 = new TableColumn<>("Bestelldatum");
    	b1.setCellValueFactory(new Callback<CellDataFeatures<Artikel,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Artikel, String> param) {
    	        return new ReadOnlyStringWrapper(param.getValue().bestellung.datum);
    	    }
    	});
    	b1.setPrefWidth(100);

    	TableColumn<Artikel, String> b2 = new TableColumn<>("Bestellwert");
    	b2.setCellValueFactory(new Callback<CellDataFeatures<Artikel,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Artikel, String> param) {
    	        return new ReadOnlyStringWrapper(param.getValue().bestellung.wert);
    	    }
    	});
    	b2.setPrefWidth(100);
    	
    	TableColumn<Artikel, String> z1 = new TableColumn<>("Status");
    	z1.setCellValueFactory(new Callback<CellDataFeatures<Artikel,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Artikel, String> param) {
    	        return new ReadOnlyStringWrapper(param.getValue().zustellung.status);
    	    }
    	});
    	z1.setPrefWidth(100);

    	
    	TableColumn<Artikel, String> a1 = new TableColumn<>("Name");
    	a1.setCellValueFactory(new Callback<CellDataFeatures<Artikel,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Artikel, String> param) {
    	        return new ReadOnlyStringWrapper(param.getValue().name);
    	    }
    	});
    	a1.setPrefWidth(200);
    	
    	TableColumn<Artikel, String> a2 = new TableColumn<>("Preis");
    	a2.setCellValueFactory(new Callback<CellDataFeatures<Artikel,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Artikel, String> param) {
    	        return new ReadOnlyStringWrapper(param.getValue().preis);
    	    }
    	});
    	a1.setPrefWidth(100);
    	
    	TableColumn<Artikel, String> a3 = new TableColumn<>("Url");
    	a3.setCellValueFactory(new Callback<CellDataFeatures<Artikel,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Artikel, String> param) {
    	        return new ReadOnlyStringWrapper(param.getValue().url);
    	    }
    	});
    	a3.setPrefWidth(100);
    	tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);    	
    	tableview.getColumns().addAll(b1,b2, z1, a1, a2, a3);
    }
    

}
