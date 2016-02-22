//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package rise.splcc.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rise.splcc.data.Submission;
import rise.splcc.data.Submission.TypeSubmission;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.SubmissionNotFoundException;
import rise.splcc.util.PersistenceMechanismException;
import rise.splcc.util.PersistenceMechanismRDBMS;

public class SubmissionRepositoryBDR implements SubmissionRepository {
	
	private static SubmissionRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public SubmissionRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static SubmissionRepositoryBDR getInstance(){
		if(instance == null){
			instance = new SubmissionRepositoryBDR();
		}
		return instance;
	}
	
	
	@Override
	public void insert(Submission submission) throws RepositoryException {
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO submission (idActivity, type, abstract, keywords, title) Values('"+submission.getIdActivity()+"', '"+submission.getType()+"', '"+ submission.getAbstractPaper() +"', '" + submission.getKeywords()+ "', '"+submission.getTitle()+"')");
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
	public List<Submission> getSubmissions() throws RepositoryException {
		Submission submission = null;
		ArrayList<Submission> list = new ArrayList<Submission>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from submission");
            while (resultset.next()) {
                submission = new Submission();
                submission.setIdSubmission(resultset.getInt("idSubmission"));
                submission.setIdActivity(resultset.getInt("idActivity"));
                submission.setAbstractPaper(resultset.getString("abstract"));
                submission.setKeywords(resultset.getString("keywords"));
                submission.setType(TypeSubmission.valueOf(resultset.getString("type")));
                submission.setTitle(resultset.getString("title"));
            
				list.add(submission);
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
	public boolean isThere(int idSubmission) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM submission WHERE idSubmission = '" + idSubmission + "'");
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
	public int getSubmissionLastId() throws RepositoryException {
		int answer=-1;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT AUTO_INCREMENT as proximo_valor FROM information_schema.tables WHERE TABLE_SCHEMA= 'EeventDB' AND TABLE_NAME= 'submission'");
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
	
	@Override
	public void remove(int idSubmission) throws SubmissionNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM submission WHERE idSubmission = '"+ idSubmission+"'");
            if (i == 0) {
            	throw new SubmissionNotFoundException(idSubmission);
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
	public void update(Submission submission) throws SubmissionNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();

            statement.executeUpdate("UPDATE submission SET idActivity = '"+submission.getIdActivity()+"', type = '"+submission.getType()+"', abstract = '"+ submission.getAbstractPaper() +"', keywords = '" + submission.getKeywords()+ "' , title = '" + submission.getTitle()+ "' WHERE idSubmission = '"+ submission.getIdSubmission()+"'");
            	
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


	
	public int getSubmissionIdByTitle(String submissionTitle) throws RepositoryException{
		int answer = -1;
		try{
			Statement statement = (Statement) pm.getCommunicationChannel();
			ResultSet resultset = statement.executeQuery("Select idSubmission from Submission where title = '" + submissionTitle + "'");
			resultset.first();
			answer = resultset.getInt("idSubmission");
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
	public Submission search(int idSubmission) throws SubmissionNotFoundException,
			RepositoryException {
		Submission submission = null;
		submission = new Submission();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from submission WHERE idSubmission =" + idSubmission);
            if (resultset.next()) {   
            	
            	submission.setIdSubmission(resultset.getInt("idSubmission"));
                submission.setIdActivity(resultset.getInt("idActivity"));
                submission.setAbstractPaper(resultset.getString("abstract"));
                submission.setKeywords(resultset.getString("keywords"));
                submission.setType(TypeSubmission.valueOf(resultset.getString("type")));
                submission.setTitle(resultset.getString("title"));
            } else {
            	throw new SubmissionNotFoundException(idSubmission);
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
		return submission;
	}
	//#if ${SubmissionCompleta} == "T"
	@Override
	public void insert(File attachment, int idActivity) throws RepositoryException {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;

		FileInputStream input = null;

		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/EeventDB", "root", "password");

			// 2. Prepare statement
			String sql = "INSERT INTO submission" + 
			             "(idActivity, type, abstract, keywords, title, attachment) Values" +
					      "(?,?,?,?,?,?)";
			myStmt = myConn.prepareStatement(sql);

			// 3. Set parameter for resume file name
			input = new FileInputStream(attachment);
			myStmt.setInt(1, idActivity);
			myStmt.setString(2, "");
			myStmt.setString(3, "");
			myStmt.setString(4, "");
			myStmt.setString(5, "");
			myStmt.setBinaryStream(6,input);

			System.out.println("Reading input file: " + attachment.getAbsolutePath());

			// 4. Execute statement
			System.out.println("\nStoring resume in database: " + attachment);
			System.out.println(sql);

			myStmt.executeUpdate();

			System.out.println("\nCompleted successfully!");
			
			if (input != null) {
				input.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		} 
	}
	//#endif
	
	@Override
	public void pdfRecover(int idSubmission) throws RepositoryException{
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		InputStream input = null;
		FileOutputStream output = null;

		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/EeventDB", "root", "password");

			// 2. Execute statement
			myStmt = myConn.createStatement();
			String sql = "select attachment from submission where idSubmission=" + idSubmission;
			myRs = myStmt.executeQuery(sql);
			
			// 3. Set up a handle to the file
			File theFile = new File("attachment_from_DB.pdf");
			output = new FileOutputStream(theFile);

			if (myRs.next()) {

				input = myRs.getBinaryStream("attachment"); 
				System.out.println("Reading resume from database...");
				System.out.println(sql);
				
				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);
				}
				
				System.out.println("\nSaved to file: " + theFile.getAbsolutePath());
				
				System.out.println("\nCompleted successfully!");	
				
				if (input != null) {
					input.close();
				}

				if (output != null) {
					output.close();
				}
				
				if (myStmt != null) {
					myStmt.close();
				}

				if (myConn != null) {
					myConn.close();
				}
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		} 
       
	}
	
	@Override
	public List<Submission> getSubmissionsByUser(int idUser) throws RepositoryException {
		Submission submission = null;
		ArrayList<Submission> list = new ArrayList<Submission>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select Submission.title, Submission.abstract from Submission inner join submissionUser on submissionUser.`idUser` =" + idUser);
            while (resultset.next()) {
            	submission = new Submission();
            	submission.setTitle(resultset.getString("title"));
            	submission.setAbstractPaper(resultset.getString("abstract"));
				list.add(submission);
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

}
//#endif
