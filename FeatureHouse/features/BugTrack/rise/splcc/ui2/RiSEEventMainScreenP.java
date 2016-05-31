package rise.splcc.ui2;

public class RiSEEventMainScreenP extends JFrame {
	
	//#if ${Bugs} == "T"
	private BugtrackScreenP screenBugtrack;
	//#endif
		
	public void init(){
		original();
		
		//#if ${Bugs} == "T"
		BugtrackScreenMenuAction bugtrackAction = new BugtrackScreenMenuAction();
		//#endif
		
		//#if ${Bugs} == "T"		
		JMenuItem mntmBugtrack = new JMenuItem("Bugtrack");
		mnArchieve.add(mntmBugtrack);
		//#endif
		
		//#if ${Bugs} == "T"
		mntmBugtrack.addActionListener(bugtrackAction);
		//#endif
	}

	//BUGTRACK
				//#if ${Bugs} == "T"
				private class BugtrackScreenMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {
						screenBugtrack = BugtrackScreenP.getInstanceBugtrackScreenP();
						//desktopPane.add(screenBugtrack);
						if(screenBugtrack.getParent() == null){
							desktopPane.add(screenBugtrack);
						}
						screenBugtrack.setVisible(true);
						desktopPane.moveToFront(screenBugtrack);
						try {
							screenBugtrack.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}  
				}
				//#endif
}
