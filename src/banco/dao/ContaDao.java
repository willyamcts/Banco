package banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import banco.modelo.Cliente;
import banco.modelo.Conta;

public class ContaDao implements Dao<Conta> {
	
	private static final String GET_BY_ID = "SELECT * FROM livro NATURAL JOIN autor WHERE id = ?";
	private static final String GET_ALL = "SELECT * FROM livro NATURAL JOIN autor";
	private static final String INSERT = "INSERT INTO livro (id, titulo, anoPublicacao, editora, autor) "
			+ "VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE livro SET id = ?, titulo = ?, anoPublicacao = ?, "
			+ "editora = ? WHERE autor = ?";
	private static final String DELETE = "DELETE FROM livro WHERE id = ?";
	
	public ContaDao() {
		try {
			createTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void createTable() throws SQLException {
	    final String sqlCreate = "CREATE TABLE IF NOT EXISTS livro"
	            + "  (id           INTEGER,"
	            + "   titulo      VARCHAR(50),"
	            + "   anoPublicacao   INTEGER,"
	            + "   editora	   VARCHAR(50),"
	            + "   FOREIGN KEY (autor_id) REFERENCES autor(id),"
	            + "   PRIMARY KEY (id))";
	    
	    Connection conn = DbConnection.getConnection();

	    Statement stmt = conn.createStatement();
	    stmt.execute(sqlCreate);
	}
	
	
	private Conta getContaFromRS(ResultSet rs) throws SQLException
    {
		Conta conta = new Conta();
			
		conta.setId( rs.getInt("id") );
		conta.setTitulo( rs.getString("titulo") );
		conta.setNumero( rs.getInt("anoPublicacao"));
		conta.setEditora( rs.getString("editora") );
		conta.setCliente( new Cliente(rs.getInt("id"), rs.getString("nome"), 
				rs.getLong("cpf")) );
	
		return conta;
    }
	
	@Override
	public Conta getByKey(int id) {
		Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Conta conta = null;
		
		try {
			stmt = conn.prepareStatement(GET_BY_ID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				conta = getContaFromRS(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, rs);
		}
		
		return conta;
	}

	@Override
	public List<Conta> getAll() {
		Connection conn = DbConnection.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Conta> conta = new ArrayList<>();
		
		try {
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(GET_ALL);
			
			while (rs.next()) {
				conta.add(getContaFromRS(rs));
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, rs);
		}
		
		return conta;
	}

	@Override
	public void insert(Conta conta) {
		Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, conta.getId());
			stmt.setInt(2, conta.getCliente().getId());
			stmt.setInt(3, conta.getNumero());
			
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				conta.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, rs);
		}

	}

	@Override
	public void delete(int id) {
		Connection conn = DbConnection.getConnection();
		
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(DELETE);
			
			stmt.setInt(1, id);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, null);
		}
	}

	@Override
	public void update(Conta conta) {
		Connection conn = DbConnection.getConnection();
		
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, conta.getTitulo());
			stmt.setInt(2, conta.getCliente().getId());
			stmt.setInt(3, conta.getNumero());
			stmt.setInt(5, conta.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close (conn, stmt, null);
		}
	}
	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao fechar recursos.", e);
		}
		
	}

}
