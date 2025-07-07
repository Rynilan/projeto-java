package biblioteca_de_jogos.view.ui;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.HashMap;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

public class Loader extends Application {

	private static Deque<String> navigationCache = new ArrayDeque<String>();
	private static AnchorPane content = null;
	private static Map<String, AnchorPane> cache = new HashMap<String, AnchorPane>();

	private AnchorPane template(Stage janela) {
		AnchorPane content = new AnchorPane();
		// Carrega o Template.fxml
		return content;
	}

	private void addToHistory(String fxml) {
		Loader.navigationCache.push(fxml);
		if (Loader.navigationCache.size() > 5) {
			Loader.navigationCache.removeFirst();
		}
	}

	private String popFromHistory() {
		return Loader.navigationCache.pop();
	}

	private void loadContent(String fxml) {
		if (cache.containsKey(fxml)) {
			// Desconecta (esconde) o AnchorPane que está dentro do content.
			// Reconect (exibe) o AnchorPane já carregado que estava ocultado.
		} else {
			AnchorPane innerContent = new AnchorPane();
			// Carrega o FXML dentro do innerContent e coloca dentro do content.
			Loader.cache.put(fxml, innerContent);
			this.addToHistory(fxml);;
		}
	}

	/* Implementar goBackStatus, homeStatus, exit, home, goBack. */

	@Override
	public void start(Stage janela) throws Exception {
		String fxml = "path fxml"; // Arrumar forma de pegar como parâmetro.
		AnchorPane content = this.template(janela);
		Loader.content = content;
		this.loadContent(fxml);
	}

}
