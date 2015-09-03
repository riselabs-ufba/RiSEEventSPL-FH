package rise.splcc.ui2;

import javax.swing.JMenuItem;

public class RiSEEventMainScreenP extends JFrame {

	
	//#if ${SubmissionCompleta} == "T" or ${SubmissionParcial} == "T"
	private SubmissionSearchScreenP screenSearchSubmission;
	private SubmissionListAllScreenP screenListAllSubmission;
	private SubmissionRemoveScreenP screenRemoveSubmission;
	//#endif
			
	public void init(){
		
		original();
		
		//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
		SearchSubmissionMenuAction searchSubmissionAction = new SearchSubmissionMenuAction();
		ListAllSubmissionMenuAction listAllSubmissionAction = new ListAllSubmissionMenuAction();
		RemoveSubmissionMenuAction removeSubmissionAction = new RemoveSubmissionMenuAction();
		//#endif
		

		//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
		menuBar.add(mnSubmission);
		
		JMenuItem mntmRemove_8 = new JMenuItem("Remove");
		mnSubmission.add(mntmRemove_8);
		
		JMenuItem mntmSearch_8 = new JMenuItem("Search");
		mnSubmission.add(mntmSearch_8);
		
		JMenuItem mntmListall_8 = new JMenuItem("List All");
		mnSubmission.add(mntmListall_8);
		//#ifdef
		
		//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
		mntmSearch_8.addActionListener(searchSubmissionAction);
		mntmListall_8.addActionListener(listAllSubmissionAction);
		mntmRemove_8.addActionListener(removeSubmissionAction);
		//#endif
	}
				
				//SUBMISSION
			
				//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"	
				private class RemoveSubmissionMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {

						screenRemoveSubmission = SubmissionRemoveScreenP.getInstanceSubmissionRemoveScreenP();
						//desktopPane.add(screenRemoveSubmission);
						if(screenRemoveSubmission.getParent() == null){
							desktopPane.add(screenRemoveSubmission);
						}
						screenRemoveSubmission.setVisible(true);
						desktopPane.moveToFront(screenRemoveSubmission);
						try {
							screenRemoveSubmission.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}  
				}

				private class SearchSubmissionMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {

						screenSearchSubmission = SubmissionSearchScreenP.getInstanceSubmissionSearchScreenP();
						//desktopPane.add(screenSearchSubmission);
						if(screenSearchSubmission.getParent() == null){
							desktopPane.add(screenSearchSubmission);
						}
						screenSearchSubmission.setVisible(true);
						desktopPane.moveToFront(screenSearchSubmission);
						try {
							screenSearchSubmission.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}  
				}

				private class ListAllSubmissionMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {

						screenListAllSubmission = SubmissionListAllScreenP.getInstanceSubmissionListAllScreenP();
					//	desktopPane.add(screenListAllSubmission);
						if(screenListAllSubmission.getParent() == null){
							desktopPane.add(screenListAllSubmission);
						}
						screenListAllSubmission.setVisible(true);
						desktopPane.moveToFront(screenListAllSubmission);
						try {
							screenListAllSubmission.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}  
				}

				//#endif
}
