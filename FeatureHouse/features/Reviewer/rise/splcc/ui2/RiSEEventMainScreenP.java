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

	//#if ${Reviewer} == "T"
	private ReviewerInsertScreenP screenInsertReviewer;
	private ReviewerRemoveScreenP screenRemoveReviewer;
	private ReviewerUpdateScreenP screenUpdateReviewer;
	private ReviewerSearchScreenP screenSearchReviewer;
	private ReviewerListAllScreenP screenListAllReviewer;
	//#endif
	
	
	public JMenuBar menuBar = new JMenuBar(); // moved out from constructor due compiler issue
	public JMenu mnRegistration = new JMenu("Registration"); // moved out due references issue
	
	
	
	public void init(){
		
		original();
		
		//#if ${Reviewer} == "T"
		InsertReviewerMenuAction insertReviewerAction = new InsertReviewerMenuAction();
		RemoveReviewerMenuAction removeReviewerAction = new RemoveReviewerMenuAction();
		UpdateReviewerMenuAction updateReviewerAction = new UpdateReviewerMenuAction();
		SearchReviewerMenuAction searchReviewerAction = new SearchReviewerMenuAction();
		ListAllReviewerMenuAction listAllReviewerAction = new ListAllReviewerMenuAction();
		//#endif
		
		//#if ${Reviewer} == "T"
		JMenu mnReviewer = new JMenu("Reviewer");
		menuBar.add(mnReviewer);
		
		JMenuItem mntmInsert_2 = new JMenuItem("Insert");
		mnReviewer.add(mntmInsert_2);
		
		JMenuItem mntmRemove_2 = new JMenuItem("Remove");
		mnReviewer.add(mntmRemove_2);
		
		JMenuItem mntmUpdate_2 = new JMenuItem("Update");
		mnReviewer.add(mntmUpdate_2);
		
		JMenuItem mntmSearch_2 = new JMenuItem("Search");
		mnReviewer.add(mntmSearch_2);
		
		JMenuItem mntmListall_2 = new JMenuItem("ListAll");
		mnReviewer.add(mntmListall_2);
		//#endif
		
		//#if ${Reviewer} == "T"
		mntmInsert_2.addActionListener(insertReviewerAction);
		mntmRemove_2.addActionListener(removeReviewerAction);
		mntmUpdate_2.addActionListener(updateReviewerAction);
		mntmSearch_2.addActionListener(searchReviewerAction);
		mntmListall_2.addActionListener(listAllReviewerAction);
		//#endif

	}
	
	//REVIEWER
	//#if ${Reviewer} == "T"
	private class InsertReviewerMenuAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenInsertReviewer = ReviewerInsertScreenP.getInstanceReviewerInsertScreenP();
			//desktopPane.add(screenInsertReviewer);
			if(screenInsertReviewer.getParent() == null){
				desktopPane.add(screenInsertReviewer);
			}
			screenInsertReviewer.setVisible(true);
			desktopPane.moveToFront(screenInsertReviewer);
			try {
				screenInsertReviewer.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	
	private class RemoveReviewerMenuAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenRemoveReviewer = ReviewerRemoveScreenP.getInstanceReviewerRemoveScreenP();
			//desktopPane.add(screenRemoveReviewer);
			if(screenRemoveReviewer.getParent() == null){
				desktopPane.add(screenRemoveReviewer);
			}
			screenRemoveReviewer.setVisible(true);
			desktopPane.moveToFront(screenRemoveReviewer);
			try {
				screenRemoveReviewer.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	
	private class UpdateReviewerMenuAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenUpdateReviewer = ReviewerUpdateScreenP.getInstanceReviewerUpdateScreenP();
			//desktopPane.add(screenUpdateReviewer);
			if(screenUpdateReviewer.getParent() == null){
				desktopPane.add(screenUpdateReviewer);
			}
			screenUpdateReviewer.setVisible(true);
			desktopPane.moveToFront(screenUpdateReviewer);
			try {
				screenUpdateReviewer.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}  
	}
	
	private class SearchReviewerMenuAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenSearchReviewer = ReviewerSearchScreenP.getInstanceReviewerSearchScreenP();
		//	desktopPane.add(screenSearchReviewer);
			if(screenSearchReviewer.getParent() == null){
				desktopPane.add(screenSearchReviewer);
			}
			screenSearchReviewer.setVisible(true);
			desktopPane.moveToFront(screenSearchReviewer);
			try {
				screenSearchReviewer.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}  
	}
	
	private class ListAllReviewerMenuAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenListAllReviewer = ReviewerListAllScreenP.getInstanceReviewerListAllScreenP();
		//	desktopPane.add(screenListAllReviewer);
			if(screenListAllReviewer.getParent() == null){
				desktopPane.add(screenListAllReviewer);
			}
			screenListAllReviewer.setVisible(true);
			desktopPane.moveToFront(screenListAllReviewer);
			try {
				screenListAllReviewer.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}  
	}
	//#endif
}
