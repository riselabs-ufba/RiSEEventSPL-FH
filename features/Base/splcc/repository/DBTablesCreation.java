package rise.splcc.repository;

import java.sql.SQLException;
import java.sql.Statement;

import rise.splcc.util.PersistenceMechanismException;
import rise.splcc.util.PersistenceMechanismRDBMS;



public class DBTablesCreation {

	private static DBTablesCreation instance;
	private PersistenceMechanismRDBMS pm;
	
	
	public DBTablesCreation(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static DBTablesCreation getInstance(){
		if(instance == null){
			instance = new DBTablesCreation();
		}
		return instance;
	}
	
	public synchronized void createTablesDB() {
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("Create TABLE participante ( idParticipante int NOT NULL AUTO_INCREMENT, "
                    + "nomeParticipante varchar(255) NOT NULL, "
                    + "tipoAssociado varchar(255) NOT NULL, "
                    + "CONSTRAINT participante_pk PRIMARY KEY (idParticipante) );");
		} catch (SQLException e) {
			System.out.println("Could not create the database table " + e.getMessage());
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
