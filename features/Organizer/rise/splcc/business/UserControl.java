
package rise.splcc.business;

//#if ${Organizer} == "T"
import rise.splcc.repository.OrganizerRepository;
//#endif

public class UserControl {

	//#if ${Organizer} == "T"
	private OrganizerRepository organizers;
	//#endif
}
