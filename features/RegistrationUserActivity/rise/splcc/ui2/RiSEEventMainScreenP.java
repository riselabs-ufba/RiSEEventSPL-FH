package rise.splcc.ui2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import rise.splcc.facade.RiSEventFacade;

public class RiSEEventMainScreenP extends JFrame {

	//#if ${RegistrationUserActivity} == "T"
	private ActivityUserManagementScreenP screenActivityUserManagement;
	//#endif
	
	public void init(){
		
		original();
		
		//#if ${RegistrationUserActivity} == "T"
		ActivityUserManagementMenuAction managementActivityUserAction = new ActivityUserManagementMenuAction();
		//#endif
		
		//#if ${RegistrationUserActivity} == "T"
		JMenuItem mntmUserActivity = new JMenuItem("User -> Activity");
		mnRegistration.add(mntmUserActivity);
		//#endif
		
		//#if ${RegistrationUserActivity} == "T"
		mntmUserActivity.addActionListener(managementActivityUserAction);
		//#endif
	}
	
		//ACTIVITY USER/SPEAKER/ORGANIZER
		//#if ${RegistrationUserActivity} == "T"
		private class ActivityUserManagementMenuAction implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				screenActivityUserManagement = ActivityUserManagementScreenP.getInstanceActivityUserManagementScreenP();
				//desktopPane.add(screenActivityUserManagement);
				if(screenActivityUserManagement.getParent() == null){
					desktopPane.add(screenActivityUserManagement);
				}
				screenActivityUserManagement.setVisible(true);
				desktopPane.moveToFront(screenActivityUserManagement);
				try {
					screenActivityUserManagement.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		//#endif
		
}
