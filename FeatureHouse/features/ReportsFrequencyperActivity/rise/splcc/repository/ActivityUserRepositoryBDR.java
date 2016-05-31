package rise.splcc.repository;

public class ActivityUserRepositoryBDR implements ActivityUserRepository{
	
	//#if ${ReportsFrequencyperActivity} == "T"
	@Override
	public List<String> getParticipantsPerActivity(int idActivity) throws RepositoryException{
		ArrayList<String> list = new ArrayList<String>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select nameUser from User inner join activityuser on user.idUser = activityuser.idUser and activityuser.idActivity ='"+ idActivity + "'");
            while (resultset.next()) {
            	String nameUser;
            	nameUser = resultset.getString("nameUser");
				list.add(nameUser);
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
	//#endif
}
