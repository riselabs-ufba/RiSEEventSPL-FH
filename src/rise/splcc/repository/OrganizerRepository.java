//#if ${Organizer} == "T"
package rise.splcc.repository;

import java.util.List;

import rise.splcc.data.Organizer;
import rise.splcc.exception.OrganizerNotFoundException;
import rise.splcc.exception.RepositoryException;

public interface OrganizerRepository {

	public void insert(Organizer organizer) throws RepositoryException;

	// retirado pq o delete on cascade ja sera usado. motivo: carantia da consistencia do banco
	public void remove(int idUser) throws OrganizerNotFoundException, RepositoryException;

	public Organizer search(int idUser) throws OrganizerNotFoundException, RepositoryException;
	
	public List<Organizer> getOrganizers() throws RepositoryException;

	public void update(Organizer organizer) throws OrganizerNotFoundException, RepositoryException;

	public boolean isThere(int idUser) throws RepositoryException;
}
//#endif