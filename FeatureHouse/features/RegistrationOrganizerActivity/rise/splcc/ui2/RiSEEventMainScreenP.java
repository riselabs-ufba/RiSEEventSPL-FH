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

	//#if ${RegistrationOrganizerActivity} == "T"
	private ActivityOrganizerManagementScreenP screenActivityOrganizerManagement;
	//#endif
	
	
	
	public void init(){
		
		original();

		//#if ${RegistrationOrganizerActivity} == "T"
		ActivityOrganizerManagementMenuAction managementActivityOrganizerAction = new ActivityOrganizerManagementMenuAction();
		//#endif

		//#if ${RegistrationOrganizerActivity} == "T"
		JMenuItem mntmOrganizerActivity = new JMenuItem("Organizer -> Activity");
		mnRegistration.add(mntmOrganizerActivity);
		//#endif
		
		//#if ${RegistrationOrganizerActivity} == "T"
		mntmOrganizerActivity.addActionListener(managementActivityOrganizerAction);
		//#endif
	}
	

		//#if ${RegistrationOrganizerActivity} == "T"
		private class ActivityOrganizerManagementMenuAction implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				screenActivityOrganizerManagement = ActivityOrganizerManagementScreenP.getInstanceActivityOrganizerManagementScreenP();
				//desktopPane.add(screenActivityOrganizerManagement);
				if(screenActivityOrganizerManagement.getParent() == null){
					desktopPane.add(screenActivityOrganizerManagement);
				}
				screenActivityOrganizerManagement.setVisible(true);
				desktopPane.moveToFront(screenActivityOrganizerManagement);
				try {
					screenActivityOrganizerManagement.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		//#endif
}
