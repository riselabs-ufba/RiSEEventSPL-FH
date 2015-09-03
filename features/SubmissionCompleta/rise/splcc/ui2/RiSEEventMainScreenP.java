package rise.splcc.ui2;

public class RiSEEventMainScreenP extends JFrame {

	
	//#if ${SubmissionCompleta} == "T"
	private SubmissionCompleteInsertScreenP screenInsertSubmission;
	//#endif
			
	public void init(){
		
		original();
		
		//#if ${SubmissionCompleta} == "T"
		InsertSubmissionMenuAction insertSubmissionAction = new InsertSubmissionMenuAction();
		//#endif

		//#if ${SubmissionCompleta} == "T"
		JMenuItem mntmInsertComplete = new JMenuItem("Insert Complete");
		mnSubmission.add(mntmInsertComplete);
		//#endif
		
		//#if ${SubmissionCompleta} == "T"
		mntmInsertComplete.addActionListener(insertSubmissionAction);
		//#endif
	}
				
				//SUBMISSION
		//#if ${SubmissionCompleta} == "T"
				private class InsertSubmissionMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {

						screenInsertSubmission = SubmissionCompleteInsertScreenP.getInstanceSubmissionInsertScreenP();
						//desktopPane.add(screenInsertSubmission);
						if(screenInsertSubmission.getParent() == null){
							desktopPane.add(screenInsertSubmission);
						}
						screenInsertSubmission.setVisible(true);
						desktopPane.moveToFront(screenInsertSubmission);
						try {
							screenInsertSubmission.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}  
				}
			//#endif	
}
