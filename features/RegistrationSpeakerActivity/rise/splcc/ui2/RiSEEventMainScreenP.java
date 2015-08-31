package rise.splcc.ui2;

public class RiSEEventMainScreenP extends JFrame {

	
	//#if ${RegistrationSpeakerActivity} == "T"
	private ActivitySpeakerManagementScreenP screenActivitySpeakerManagement;
	//#endif

	public void init(){
		
		original();
	
		//#if ${RegistrationSpeakerActivity} == "T"
		ActivitySpeakerManagementMenuAction managementActivitySpeakerAction = new ActivitySpeakerManagementMenuAction();
		//#endif
		
		//#if ${RegistrationSpeakerActivity} == "T"
		JMenuItem mntmSpeakerActivity = new JMenuItem("Speaker -> Activity");
		mnRegistration.add(mntmSpeakerActivity);
		//#endif
		
		//#if ${RegistrationSpeakerActivity} == "T"
		mntmSpeakerActivity.addActionListener(managementActivitySpeakerAction);
		//#endif
		
	}
	
		//#if ${RegistrationSpeakerActivity} == "T"
		private class ActivitySpeakerManagementMenuAction implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				screenActivitySpeakerManagement = ActivitySpeakerManagementScreenP.getInstanceActivitySpeakerManagementScreenP();
				//desktopPane.add(screenActivitySpeakerManagement);
				if(screenActivitySpeakerManagement.getParent() == null){
					desktopPane.add(screenActivitySpeakerManagement);
				}
				screenActivitySpeakerManagement.setVisible(true);
				desktopPane.moveToFront(screenActivitySpeakerManagement);
				try {
					screenActivitySpeakerManagement.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		//#endif
		
}
