package rise.splcc.ui2;


public class RiSEEventMainScreenP extends JFrame {

	//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
	private ActivityInsertScreenP screenInsertActivity;
	private ActivityRemoveScreenP screenRemoveActivity;
	private ActivityUpdateScreenP screenUpdateActivity;
	private ActivitySearchScreenP screenSearchActivity;
	private ActivityListAllScreenP screenListAllActivity;
	private ActivityManagementScreenP screenManagementActivity;
	//#endif

	public void init(){
		
		original();
		
		//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
		InsertActivityMenuAction insertActivityAction = new InsertActivityMenuAction();
		RemoveActivityMenuAction removeActivityAction = new RemoveActivityMenuAction();
		UpdateActivityMenuAction updateActivityAction = new UpdateActivityMenuAction();
		SearchActivityMenuAction searchActivityAction = new SearchActivityMenuAction();
		ListAllActivityMenuAction listAllActivityAction = new ListAllActivityMenuAction();
		ManagementActivityMenuAction managementActivityAction = new ManagementActivityMenuAction();
		//#endif
		
		//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
		JMenu mnActivity = new JMenu("Activity");
		menuBar.add(mnActivity);
		
		JMenuItem mntmInsert_5 = new JMenuItem("Insert");
		mnActivity.add(mntmInsert_5);
		
		JMenuItem mntmRemove_5 = new JMenuItem("Remove");
		mnActivity.add(mntmRemove_5);
		
		JMenuItem mntmUpdate_5 = new JMenuItem("Update");
		mnActivity.add(mntmUpdate_5);
		
		JMenuItem mntmSearch_5 = new JMenuItem("Search");
		mnActivity.add(mntmSearch_5);
		
		JMenuItem mntmListall_5 = new JMenuItem("ListAll");
		mnActivity.add(mntmListall_5);
		
		JMenuItem mntmActivityManagement = new JMenuItem("Activity Management");
		mnActivity.add(mntmActivityManagement);
		//#endif
		
		
		//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
		mntmInsert_5.addActionListener(insertActivityAction);
		mntmRemove_5.addActionListener(removeActivityAction);
		mntmUpdate_5.addActionListener(updateActivityAction);
		mntmSearch_5.addActionListener(searchActivityAction);
		mntmListall_5.addActionListener(listAllActivityAction);
		mntmActivityManagement.addActionListener(managementActivityAction);
		//#endif
	}
	

		//ACTIVITY
		//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
		private class InsertActivityMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenInsertActivity = ActivityInsertScreenP.getInstanceActivityInsertScreenP();
				//desktopPane.add(screenInsertActivity);
				if(screenInsertActivity.getParent() == null){
					desktopPane.add(screenInsertActivity);
				}
				screenInsertActivity.setVisible(true);
				desktopPane.moveToFront(screenInsertActivity);
				try {
					screenInsertActivity.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}  
		}

		private class RemoveActivityMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenRemoveActivity = ActivityRemoveScreenP.getInstanceActivityRemoveScreenP();
				//desktopPane.add(screenRemoveActivity);
				if(screenRemoveActivity.getParent() == null){
					desktopPane.add(screenRemoveActivity);
				}
				screenRemoveActivity.setVisible(true);
				desktopPane.moveToFront(screenRemoveActivity);
				try {
					screenRemoveActivity.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}  
		}

		private class UpdateActivityMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenUpdateActivity = ActivityUpdateScreenP.getInstanceActivityUpdateScreenP();
				//desktopPane.add(screenUpdateActivity);
				if(screenUpdateActivity.getParent() == null){
					desktopPane.add(screenUpdateActivity);
				}
				screenUpdateActivity.setVisible(true);
				desktopPane.moveToFront(screenUpdateActivity);
				try {
					screenUpdateActivity.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}

		private class SearchActivityMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenSearchActivity = ActivitySearchScreenP.getInstanceActivitySearchScreenP();
			//	desktopPane.add(screenSearchActivity);
				if(screenSearchActivity.getParent() == null){
					desktopPane.add(screenSearchActivity);
				}
				screenSearchActivity.setVisible(true);
				desktopPane.moveToFront(screenSearchActivity);
				try {
					screenSearchActivity.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}

		private class ListAllActivityMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenListAllActivity = ActivityListAllScreenP.getInstanceActivityListAllScreenP();
				//desktopPane.add(screenListAllActivity);
				if(screenListAllActivity.getParent() == null){
					desktopPane.add(screenListAllActivity);
				}
				screenListAllActivity.setVisible(true);
				desktopPane.moveToFront(screenListAllActivity);
				try {
					screenListAllActivity.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}

		private class ManagementActivityMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				screenManagementActivity = ActivityManagementScreenP.getInstanceActivityManagementScreenP();
				//desktopPane.add(screenManagementActivity);
				if(screenManagementActivity.getParent() == null){
					desktopPane.add(screenManagementActivity);
				}
				screenManagementActivity.setVisible(true);
				desktopPane.moveToFront(screenManagementActivity);
				try {
					screenManagementActivity.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}
		//#endif
		
}
