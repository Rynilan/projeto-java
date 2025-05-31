package biblioteca_de_jogos;

import biblioteca_de_jogos.classes.Classe;
import biblioteca_de_jogos.assets.Asset;

/** A classe estática que representa o sistema em funcionalidade,
 * faz as classes conversarem e interage com o usuário. */
public class Main {
	public static void main(String args[]) {

		Asset asset = new Asset();
		Classe classe = new Classe();

		asset.metodo();
		classe.metodo();

		System.out.println("Tá jóia.");

	}
}
