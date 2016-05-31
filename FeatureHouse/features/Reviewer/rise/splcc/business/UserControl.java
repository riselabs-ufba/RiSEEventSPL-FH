
package rise.splcc.business;

import java.util.List;

//#if ${Reviewer} == "T"
import rise.splcc.repository.ReviewerRepository;
//#endif

public class UserControl {


	//#if ${Reviewer} == "T"
	private ReviewerRepository reviewers;
	//#endif
}
