package rise.splcc.ui2;


public class RiSEEventMainScreenP extends JFrame {

	//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
	private CheckingCopyInsertScreenP screenInsertCheckingCopy;
	private CheckingCopyRemoveScreenP screenRemoveCheckingCopy;
	private CheckingCopyUpdateScreenP screenUpdateCheckingCopy;
	private CheckingCopySearchScreenP screenSearchCheckingCopy;
	private CheckingCopyListAllScreenP screenListAllCheckingCopy;
	private CheckingCopyManagementScreenP screenManagementCheckingCopy;
	//#endif
	
	public void init(){
		
		original();
		
		//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
		InsertCheckingCopyMenuAction insertCheckingCopyAction = new InsertCheckingCopyMenuAction();
		RemoveCheckingCopyMenuAction removeCheckingCopyAction = new RemoveCheckingCopyMenuAction();
		UpdateCheckingCopyMenuAction updateCheckingCopyAction = new UpdateCheckingCopyMenuAction();
		SearchCheckingCopyMenuAction searchCheckingCopyAction = new SearchCheckingCopyMenuAction();
		ListAllCheckingCopyMenuAction listAllCheckingCopyAction = new ListAllCheckingCopyMenuAction();
		ManagementCheckingCopyMenuAction managementCheckingCopyAction = new ManagementCheckingCopyMenuAction();
		//#endif
		
		//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
		mnReports.add(menu);
		
		JMenuItem menuItem = new JMenuItem("Insert");
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Remove");
		menu.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Update");
		menu.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("Search");
		menu.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("ListAll");
		menu.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("CheckingCopy Management");
		menu.add(menuItem_5);
		//#endif
		
		//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
		menuItem.addActionListener(insertCheckingCopyAction);
		menuItem_1.addActionListener(removeCheckingCopyAction);
		menuItem_2.addActionListener(updateCheckingCopyAction);
		menuItem_3.addActionListener(searchCheckingCopyAction);
		menuItem_4.addActionListener(listAllCheckingCopyAction);
		menuItem_5.addActionListener(managementCheckingCopyAction);
		//#endif
	}
		//CHECKINGCOPY
				//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
		private class InsertCheckingCopyMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenInsertCheckingCopy = CheckingCopyInsertScreenP.getInstanceCheckingCopyInsertScreenP();
				//desktopPane.add(screenInsertCheckingCopy);
				if(screenInsertCheckingCopy.getParent() == null){
					desktopPane.add(screenInsertCheckingCopy);
				}
				screenInsertCheckingCopy.setVisible(true);
				desktopPane.moveToFront(screenInsertCheckingCopy);
				try {
					screenInsertCheckingCopy.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}  
		}

		private class RemoveCheckingCopyMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenRemoveCheckingCopy = CheckingCopyRemoveScreenP.getInstanceCheckingCopyRemoveScreenP();
				//desktopPane.add(screenRemoveCheckingCopy);
				if(screenRemoveCheckingCopy.getParent() == null){
					desktopPane.add(screenRemoveCheckingCopy);
				}
				screenRemoveCheckingCopy.setVisible(true);
				desktopPane.moveToFront(screenRemoveCheckingCopy);
				try {
					screenRemoveCheckingCopy.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}  
		}

		private class UpdateCheckingCopyMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenUpdateCheckingCopy = CheckingCopyUpdateScreenP.getInstanceCheckingCopyUpdateScreenP();
				//desktopPane.add(screenUpdateCheckingCopy);
				if(screenUpdateCheckingCopy.getParent() == null){
					desktopPane.add(screenUpdateCheckingCopy);
				}
				screenUpdateCheckingCopy.setVisible(true);
				desktopPane.moveToFront(screenUpdateCheckingCopy);
				try {
					screenUpdateCheckingCopy.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}

		private class SearchCheckingCopyMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenSearchCheckingCopy = CheckingCopySearchScreenP.getInstanceCheckingCopySearchScreenP();
				//desktopPane.add(screenSearchCheckingCopy);
				if(screenSearchCheckingCopy.getParent() == null){
					desktopPane.add(screenSearchCheckingCopy);
				}
				screenSearchCheckingCopy.setVisible(true);
				desktopPane.moveToFront(screenSearchCheckingCopy);
				try {
					screenSearchCheckingCopy.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}

		private class ListAllCheckingCopyMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenListAllCheckingCopy = CheckingCopyListAllScreenP.getInstanceCheckingCopyListAllScreenP();
				//desktopPane.add(screenListAllCheckingCopy);
				if(screenListAllCheckingCopy.getParent() == null){
					desktopPane.add(screenListAllCheckingCopy);
				}
				screenListAllCheckingCopy.setVisible(true);
				desktopPane.moveToFront(screenListAllCheckingCopy);
				try {
					screenListAllCheckingCopy.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}

		private class ManagementCheckingCopyMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				screenManagementCheckingCopy = CheckingCopyManagementScreenP.getInstanceCheckingCopyManagementScreenP();
				//desktopPane.add(screenManagementCheckingCopy);
				if(screenManagementCheckingCopy.getParent() == null){
					desktopPane.add(screenManagementCheckingCopy);
				}
				screenManagementCheckingCopy.setVisible(true);
				desktopPane.moveToFront(screenManagementCheckingCopy);
				try {
					screenManagementCheckingCopy.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}
		//#endif
		
}
