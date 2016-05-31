//#if ${InsertAuthors} == "T"
package rise.splcc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rise.splcc.data.Author;
import rise.splcc.exception.AuthorNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.util.PersistenceMechanismException;
import rise.splcc.util.PersistenceMechanismRDBMS;

public class AuthorRepositoryBDR implements AuthorRepository {
	
	private static AuthorRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public AuthorRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static AuthorRepositoryBDR getInstance(){
		if(instance == null){
			instance = new AuthorRepositoryBDR();
		}
		return instance;
	}

	
	@Override
	public void insert(Author author) throws RepositoryException{
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO author ( nameAuthor, filiation, email) "
					+ "Values('"+author.getName()+"', '"
					            +author.getEmail()+"', '"
					            +author.getFiliation()+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		
	}

	@Override
	public void remove(int idAuthor) throws AuthorNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM author WHERE idAuthor = '"+ idAuthor+"'");
            if (i == 0) {
            	throw new AuthorNotFoundException(idAuthor);
            }
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
		} catch(SQLException e){
            throw new RepositoryException(e);            
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		
	}

	@Override
	public Author search(int idAuthor) throws AuthorNotFoundException,
			RepositoryException {
		Author author = null;
		author = new Author();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from author WHERE idAuthor =" + idAuthor);
            if (resultset.next()) {   
            	author.setIdAuthor(resultset.getInt("idAuthor"));
            	author.setName(resultset.getString("nameAuthor"));
            	author.setEmail(resultset.getString("email"));
            	author.setFiliation(resultset.getString("filiation"));
            	
            } else {
            	throw new AuthorNotFoundException(idAuthor);
            }
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		return author;
	}

	@Override
	public List<Author> getAuthors() throws RepositoryException {
		Author author=null;
		ArrayList<Author> list = new ArrayList<Author>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from author");
            while (resultset.next()) {
            	author = new Author();
            	author.setIdAuthor(resultset.getInt("idAuthor"));
            	author.setName(resultset.getString("nameAuthor"));
            	author.setEmail(resultset.getString("email"));
            	author.setFiliation(resultset.getString("filiation"));
				list.add(author);
            } 
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		return list;
	}

	@Override
	public void update(Author author) throws AuthorNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();

    	    statement.executeUpdate("UPDATE author SET nameAuthor = '"+ author.getName() +
    	    		                                 "', filiation = '" + author.getFiliation() +
    	    		                                 "', email = '"+ author.getEmail() +
    	    		                                 "' WHERE idAuthor = '"+ author.getIdAuthor()+"'");

		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
		} catch (SQLException e) {
		    throw new RepositoryException(e);
	    } finally {
	    	try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		
	}

	@Override
	public boolean isThere(int idAuthor) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM author WHERE idAuthor = '" + idAuthor + "'");
            answer = resultset.next();
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
        return answer;
	}

	@Override
	public int getAuthorLastId() throws RepositoryException {
		int answer=-1;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT AUTO_INCREMENT as proximo_valor FROM information_schema.tables WHERE TABLE_SCHEMA= 'EEventDB' AND TABLE_NAME= 'author'");
            resultset.first();
            answer = resultset.getInt("proximo_valor");
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
        return answer;
	}

}
//#endif