
package rise.splcc.business;


//#if ${Speaker} == "T"
import rise.splcc.repository.SpeakerRepository;
//#endif


public class UserControl {

	//#if ${Speaker} == "T"
	private SpeakerRepository speakers;
	//#endif

}
