package vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RespuestaBox {
	
	private static boolean respuesta;
	
	public static boolean display (String titulo, String mensaje) {
		
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(titulo);
		stage.setMinWidth(400);
		
		Label mensajeLabel = new Label (mensaje);
		
		Button salirBoton = new Button("Salir");
		Button quedarseBoton = new Button("Quedarse");
		
		salirBoton.setOnAction(e -> {
			respuesta = true;
			stage.close();
		});
		
		quedarseBoton.setOnAction(e -> {
			respuesta = false;
			stage.close();
		});
		
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(10));
		HBox layoutBotones = new HBox(10);
		layoutBotones.setAlignment(Pos.CENTER);
		layoutBotones.getChildren().addAll(salirBoton,quedarseBoton);
		layout.getChildren().addAll(mensajeLabel, layoutBotones);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		
		stage.setScene(scene);
		stage.showAndWait();
		
		return respuesta;		
		
	}

}
