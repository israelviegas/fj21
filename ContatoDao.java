import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ContatoDao {
	
	// a conexão com o banco de dados teste
	private Connection connection;
	
	public ContatoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Contato contato) {
		String sql = "insert into contatos " +
		"(nome,email,endereco,dataNascimento)" +
		" values (?,?,?,?)";
		try {
		// prepared statement para inserção
		PreparedStatement stmt = connection.prepareStatement(sql);
		// seta os valores
		stmt.setString(1,contato.getNome());
		stmt.setString(2,contato.getEmail());
		stmt.setString(3,contato.getEndereco());
		stmt.setDate(4, new Date(
		contato.getDataNascimento().getTimeInMillis()));
		// executa
		stmt.execute();
		stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void remove(Contato contato) {
		String sql = "delete from contatos " +
		"where nome = ?";
		try {
		// prepared statement para inserção
		PreparedStatement stmt = connection.prepareStatement(sql);
		// seta os valores
		stmt.setString(1,contato.getNome());
		// executa
		stmt.execute();
		stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Contato> getLista() {
		
		String sql = "select * from contatos";
		List<Contato> lista = null;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			lista = new ArrayList<Contato>();
			
			while (rs.next()) {
				Contato contato = new Contato();
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				Date data = rs.getDate("dataNascimento");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(data);
				contato.setDataNascimento(calendar);
				lista.add(contato);
			}
			
		stmt.close();	
		rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return lista;
	}
	
	public void FecharConexao() throws SQLException {
		
		connection.close();
		
	}


}
