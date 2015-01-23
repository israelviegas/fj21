import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class TestaInsere {

	public static void main(String[] args) throws SQLException {
		
		// Criando um contato
		Contato contato = new Contato();
		contato.setNome("Israel");
		contato.setEmail("israel.viegas@gmail.com");
		contato.setEndereco("R. Andorinha da Mata, 239, apto. 52A");
		contato.setDataNascimento(Calendar.getInstance());

		//Adicionando contato
		ContatoDao contatoDao = new ContatoDao();
		contatoDao.adiciona(contato);
		
		// Criando um contato
		Contato contato2 = new Contato();
		contato2.setNome("Vera");
		contato2.setEmail("veravleal@gmail.com");
		contato2.setEndereco("R. Andorinha da Mata, 239, apto. 52A");
		contato2.setDataNascimento(Calendar.getInstance());

		//Adicionando contato
		contatoDao.adiciona(contato2);
		
		
		// Removendo contato
		//contatoDao.remove(contato);
		//System.out.println("Contato removido");
		
		// Recuperando lista de Contatos
		List<Contato> lista = new ArrayList<Contato>();
		
		lista = contatoDao.getLista();
		
		for (Contato cadaContato : lista) {
			System.out.println("Nome: "+ cadaContato.getNome());
			System.out.println("Email: " + cadaContato.getEmail());
			System.out.println("Endereço: " + cadaContato.getEndereco());
			System.out.println("Data de nascimento: " + new Date(cadaContato.getDataNascimento().getTimeInMillis()));
			
		}
		
		contatoDao.FecharConexao();
		
	}

}
