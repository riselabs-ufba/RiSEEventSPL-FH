package rise.splcc.repository;

public interface SubmissionRepository {

	//#if ${SubmissionCompleta} == "T"
	public void insert (File attachment, int idActivity) throws RepositoryException;
	//#endif
	
}
