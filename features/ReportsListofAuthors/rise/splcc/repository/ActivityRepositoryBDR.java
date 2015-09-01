package rise.splcc.repository;

public class ActivityRepositoryBDR implements ActivityRepository {
	
	
	//#if ${ReportsListofAuthors} == "T"
	@Override
	public List<String> getListOfAuthorsPerActivity(int idActivity) throws RepositoryException{
		ArrayList<String> list = new ArrayList<String>();
		 try {
	            Statement statement = (Statement) pm.getCommunicationChannel();           
	            ResultSet resultset = statement.executeQuery("select Author.nameAuthor from Author inner join submissionAuthor on Author.idAuthor = submissionAuthor.idAuthor inner join submission on Submission.idSubmission = submissionAuthor.idSubmission inner join Activity on Submission.idActivity = '"+ idActivity+ "'");
	            while (resultset.next()) {

					list.add(resultset.getString("nameAuthor"));
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
