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

	//#if ${Organizer} == "T"
	private OrganizerInsertScreenP screenInsertOrganizer;
	private OrganizerRemoveScreenP screenRemoveOrganizer;
	private OrganizerUpdateScreenP screenUpdateOrganizer;
	private OrganizerSearchScreenP screenSearchOrganizer;
	private OrganizerListAllScreenP screenListAllOrganizer;
	//#endif
	
	
	public void init(){
		
		original();
		
		//#if ${Organizer} == "T"
		InsertOrganizerMenuAction insertOrganizerAction = new InsertOrganizerMenuAction();
		RemoveOrganizerMenuAction removeOrganizerAction = new RemoveOrganizerMenuAction();
     	UpdateOrganizerMenuAction updateOrganizerAction = new UpdateOrganizerMenuAction();
		SearchOrganizerMenuAction searchOrganizerAction = new SearchOrganizerMenuAction();
		ListAllOrganizerMenuAction listAllOrganizerAction = new ListAllOrganizerMenuAction();
		//#endif
		
		//#if ${Organizer} == "T"
		JMenu mnOrganizer = new JMenu("Organizer");
		menuBar.add(mnOrganizer);
		
		JMenuItem mntmInsert_3 = new JMenuItem("Insert");
		mnOrganizer.add(mntmInsert_3);
		
		JMenuItem mntmRemove_3 = new JMenuItem("Remove");
		mnOrganizer.add(mntmRemove_3);
		
		JMenuItem mntmUpdate_3 = new JMenuItem("Update");
		mnOrganizer.add(mntmUpdate_3);
		
		JMenuItem mntmSearch_3 = new JMenuItem("Search");
		mnOrganizer.add(mntmSearch_3);
		
		JMenuItem mntmListall_3 = new JMenuItem("Listall");
		mnOrganizer.add(mntmListall_3);
		//#endif
		
		
		//#if ${Organizer} == "T"
		mntmInsert_3.addActionListener(insertOrganizerAction);
		mntmRemove_3.addActionListener(removeOrganizerAction);
		mntmUpdate_3.addActionListener(updateOrganizerAction);
		mntmSearch_3.addActionListener(searchOrganizerAction);
		mntmListall_3.addActionListener(listAllOrganizerAction);
		//#endif
	}
	
	
	//Organizer
	//#if ${Organizer} == "T"
		private class InsertOrganizerMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				
				screenInsertOrganizer = OrganizerInsertScreenP.getInstanceOrganizerInsertScreenP();
			//	desktopPane.add(screenInsertOrganizer);
				if(screenInsertOrganizer.getParent() == null){
					desktopPane.add(screenInsertOrganizer);
				}
				screenInsertOrganizer.setVisible(true);
				desktopPane.moveToFront(screenInsertOrganizer);
				try {
					screenInsertOrganizer.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}  
		}
		
		private class RemoveOrganizerMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				
				screenRemoveOrganizer = OrganizerRemoveScreenP.getInstanceOrganizerRemoveScreenP();
				//desktopPane.add(screenRemoveOrganizer);
				if(screenRemoveOrganizer.getParent() == null){
					desktopPane.add(screenRemoveOrganizer);
				}
				screenRemoveOrganizer.setVisible(true);
				desktopPane.moveToFront(screenRemoveOrganizer);
				try {
					screenRemoveOrganizer.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}  
		}
		
		private class UpdateOrganizerMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				
				screenUpdateOrganizer = OrganizerUpdateScreenP.getInstanceOrganizerUpdateScreenP();
				//desktopPane.add(screenUpdateOrganizer);
				if(screenUpdateOrganizer.getParent() == null){
					desktopPane.add(screenUpdateOrganizer);
				}
				screenUpdateOrganizer.setVisible(true);
				desktopPane.moveToFront(screenUpdateOrganizer);
				try {
					screenUpdateOrganizer.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}
		
		private class SearchOrganizerMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				
				screenSearchOrganizer = OrganizerSearchScreenP.getInstanceOrganizerSearchScreenP();
				//desktopPane.add(screenSearchOrganizer);
				if(screenSearchOrganizer.getParent() == null){
					desktopPane.add(screenSearchOrganizer);
				}
				screenSearchOrganizer.setVisible(true);
				desktopPane.moveToFront(screenSearchOrganizer);
				try {
					screenSearchOrganizer.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}
		
		private class ListAllOrganizerMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				
				screenListAllOrganizer = OrganizerListAllScreenP.getInstanceOrganizerListAllScreenP();
				//desktopPane.add(screenListAllOrganizer);
				if(screenListAllOrganizer.getParent() == null){
					desktopPane.add(screenListAllOrganizer);
				}
				screenListAllOrganizer.setVisible(true);
				desktopPane.moveToFront(screenListAllOrganizer);
				try {
					screenListAllOrganizer.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}
		//#endif
		

}