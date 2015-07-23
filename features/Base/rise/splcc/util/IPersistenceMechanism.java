package rise.splcc.util;





public interface IPersistenceMechanism {

    public void connect() throws PersistenceMechanismException;
	public void disconnect() throws PersistenceMechanismException;
	public Object getCommunicationChannel() throws PersistenceMechanismException;
	public void releaseCommunicationChannel() throws PersistenceMechanismException;
	public void beginTransaction() throws TransactionException;
    public void commitTransaction() throws TransactionException;
	public void rollbackTransaction() throws TransactionException;
}
