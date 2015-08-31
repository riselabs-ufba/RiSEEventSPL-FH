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

	//#if ${Speaker} == "T"
	private SpeakerInsertScreenP screenInsertSpeaker;
	private SpeakerRemoveScreenP screenRemoveSpeaker;
	private SpeakerUpdateScreenP screenUpdateSpeaker;
	private SpeakerSearchScreenP screenSearchSpeaker;
	private SpeakerListAllScreenP screenListAllSpeaker;
	//#endif

	/**
	 * Create the frame.
	 */
	public void init() {
		original();
		initSpeaker();
	}
	public void initSpeaker(){ 
		//#if ${Speaker} == "T"
		InsertSpeakerMenuAction insertSpeakerAction = new InsertSpeakerMenuAction();
		RemoveSpeakerMenuAction removeSpeakerAction = new RemoveSpeakerMenuAction();
		UpdateSpeakerMenuAction updateSpeakerAction = new UpdateSpeakerMenuAction();
		SearchSpeakerMenuAction searchSpeakerAction = new SearchSpeakerMenuAction();
		ListAllSpeakerMenuAction listAllSpeakerAction = new ListAllSpeakerMenuAction();
		//#endif
		
		//#if ${Speaker} == "T"
		JMenu mnSpeaker = new JMenu("Speaker");
		menuBar.add(mnSpeaker);
		
		JMenuItem mntmInsert_1 = new JMenuItem("Insert");
		mnSpeaker.add(mntmInsert_1);
		
		JMenuItem mntmRemove_1 = new JMenuItem("Remove");
		mnSpeaker.add(mntmRemove_1);
		
		JMenuItem mntmUpdate_1 = new JMenuItem("Update");
		mnSpeaker.add(mntmUpdate_1);
		
		JMenuItem mntmSearch_1 = new JMenuItem("Search");
		mnSpeaker.add(mntmSearch_1);
		
		JMenuItem mntmListall_1 = new JMenuItem("ListAll");
		mnSpeaker.add(mntmListall_1);
		//#endif
		
		//#if ${Speaker} == "T"
		mntmInsert_1.addActionListener(insertSpeakerAction);
		mntmRemove_1.addActionListener(removeSpeakerAction);
		mntmUpdate_1.addActionListener(updateSpeakerAction);
		mntmSearch_1.addActionListener(searchSpeakerAction);
		mntmListall_1.addActionListener(listAllSpeakerAction);
		//#endif
		
	}
	

	//SPEAKER
	
	//#if ${Speaker} == "T"
	private class InsertSpeakerMenuAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenInsertSpeaker = SpeakerInsertScreenP.getInstanceSpeakerInsertScreenP();
		//	desktopPane.add(screenInsertSpeaker);
			if(screenInsertSpeaker.getParent() == null){
				desktopPane.add(screenInsertSpeaker);
			}
			screenInsertSpeaker.setVisible(true);
			desktopPane.moveToFront(screenInsertSpeaker);
			try {
				screenInsertSpeaker.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	
	private class RemoveSpeakerMenuAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenRemoveSpeaker = SpeakerRemoveScreenP.getInstanceSpeakerRemoveScreenP();
			//desktopPane.add(screenRemoveSpeaker);
			if(screenRemoveSpeaker.getParent() == null){
				desktopPane.add(screenRemoveSpeaker);
			}
			screenRemoveSpeaker.setVisible(true);
			desktopPane.moveToFront(screenRemoveSpeaker);
			try {
				screenRemoveSpeaker.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	
	private class UpdateSpeakerMenuAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenUpdateSpeaker = SpeakerUpdateScreenP.getInstanceSpeakerUpdateScreenP();
			//desktopPane.add(screenUpdateSpeaker);
			if(screenUpdateSpeaker.getParent() == null){
				desktopPane.add(screenUpdateSpeaker);
			}
			screenUpdateSpeaker.setVisible(true);
			desktopPane.moveToFront(screenUpdateSpeaker);
			try {
				screenUpdateSpeaker.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}  
	}
	
	private class SearchSpeakerMenuAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenSearchSpeaker = SpeakerSearchScreenP.getInstanceSpeakerSearchScreenP();
			//desktopPane.add(screenSearchSpeaker);
			if(screenSearchSpeaker.getParent() == null){
				desktopPane.add(screenSearchSpeaker);
			}
			screenSearchSpeaker.setVisible(true);
			desktopPane.moveToFront(screenSearchSpeaker);
			try {
				screenSearchSpeaker.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}  
	}
	
	private class ListAllSpeakerMenuAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenListAllSpeaker = SpeakerListAllScreenP.getInstanceSpeakerListAllScreenP();
			//desktopPane.add(screenListAllSpeaker);
			if(screenListAllSpeaker.getParent() == null){
				desktopPane.add(screenListAllSpeaker);
			}
			screenListAllSpeaker.setVisible(true);
			desktopPane.moveToFront(screenListAllSpeaker);
			try {
				screenListAllSpeaker.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}  
	}
	//#endif
		
		
}

