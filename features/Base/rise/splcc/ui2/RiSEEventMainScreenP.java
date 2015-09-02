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

	private JPanel contentPane;
	
    public static RiSEventFacade facade; // caso a variabilidade de tela login seja retirada o sistema deve iniciar dessa tela
	
	private static RiSEEventMainScreenP instanceRiSEEventMainScreenP;      
	

	private UserInsertScreenP screenInsertUser;	
	private UserRemoveScreenP screenRemoveUser;
	private UserUpdateScreenP screenUpdateUser;	
	private UserSearchScreenP screenSearchUser;	
	private UserListAllScreenP screenListAllUser;

	//private UserManagementScreenP screenManagementUser;
	//#if ${Reviewer} == "T"
	private ReviewerInsertScreenP screenInsertReviewer;
	private ReviewerRemoveScreenP screenRemoveReviewer;
	private ReviewerUpdateScreenP screenUpdateReviewer;
	private ReviewerSearchScreenP screenSearchReviewer;
	private ReviewerListAllScreenP screenListAllReviewer;
	//#endif
	
	
	private EventInsertScreenP screenInsertEvent;
	 
	private EventRemoveScreenP screenRemoveEvent;
	private EventUpdateScreenP screenUpdateEvent;
	private EventSearchScreenP screenSearchEvent;
	private EventListAllScreenP screenListAllEvent;
	private EventManagementScreenP screenManagementEvent;
	
	//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
	private ReviewInsertScreenP screenInsertReview;
	private ReviewRemoveScreenP screenRemoveReview;
	private ReviewUpdateScreenP screenUpdateReview;
	private ReviewSearchScreenP screenSearchReview;
	private ReviewListAllScreenP screenListAllReview;
	private ReviewManagementScreenP screenManagementReview;
	private ReviewResultsScreenP screenResultsReview;
	//#endif
	
	//#if ${SubmissionCompleta} == "T"
	private SubmissionCompleteInsertScreenP screenInsertSubmission;
	//#endif
	//#if ${SubmissionParcial} == "T" 
	private SubmissionPartialInsertScreenP screenInsertSubmissionPartial;
	//#endif
	//#if ${SubmissionCompleta} == "T" or ${SubmissionParcial} == "T"
	private SubmissionSearchScreenP screenSearchSubmission;
	private SubmissionListAllScreenP screenListAllSubmission;
	private SubmissionRemoveScreenP screenRemoveSubmission;
	//#endif
	//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
	private AssignmentInsertScreenP screenInsertAssignment;
	private AssignmentRemoveScreenP screenRemoveAssignment;
	private AssignmentListAllScreenP screenListAllAssignment;
	private AssignmentManagementScreenP screenManagementAssignment;
	private AssignmentSearchScreenP screenSearchAssignment;
	//#endif
	
	private RegistrationInsertScreenP screenInsertRegistration;
	private RegistrationSearchScreenP screenSearchRegistration;
	private RegistrationListAllScreenP screenListAllRegistration;
	private RegistrationManagementScreenP screenManagementRegistration;
	private RegistrationUpdateScreenP screenUpdateRegistration;
	private RegistrationRemoveScreenP screenRemoveRegistration;
	
	//#if ${Bugs} == "T"
	private BugtrackScreenP screenBugtrack;
	//#endif
	
	private static JLabel labelImagem;
	
	private JDesktopPane desktopPane;
	
	
	public static RiSEventFacade getFacade(){
		return RiSEventFacade.getInstance();
	}
	
	public static RiSEEventMainScreenP getInstanceRiSEEventMainScreenP() {
		if (instanceRiSEEventMainScreenP == null) {
			RiSEEventMainScreenP.instanceRiSEEventMainScreenP = new RiSEEventMainScreenP();
		}
		return RiSEEventMainScreenP.instanceRiSEEventMainScreenP;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RiSEEventMainScreenP frame = new RiSEEventMainScreenP();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JMenuBar menuBar = new JMenuBar(); // moved out from constructor due compiler issue
	private JMenu mnRegistration = new JMenu("Registration"); // moved out due references issue
	private JMenu mnReports = new JMenu("Reports");  // moved out due references issue
	private JMenu mnPayment = new JMenu("Payment"); // moved out due references issue
	private JMenu menu = new JMenu("CheckingCopy"); // moved out due references issue
	private JMenu mnEvent = new JMenu("Event"); // moved out due references issue
	
	/**
	 * Create the frame.
	 */
	public RiSEEventMainScreenP() {
		init();
	}
	
			
	public void init(){
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		ExitMenuAction exitMenuAction = new ExitMenuAction();
		
	
		InsertUserMenuAction insertUserAction = new InsertUserMenuAction();	
		RemoveUserMenuAction removeUserAction = new RemoveUserMenuAction();	
		UpdateUserMenuAction updateUserAction = new UpdateUserMenuAction();		
		SearchUserMenuAction searchUserAction = new SearchUserMenuAction();
		ListAllUserMenuAction listAllUserAction = new ListAllUserMenuAction();

		//ManagementUserMenuAction managementUserAction = new ManagementUserMenuAction();
		//#if ${Reviewer} == "T"
		InsertReviewerMenuAction insertReviewerAction = new InsertReviewerMenuAction();
		RemoveReviewerMenuAction removeReviewerAction = new RemoveReviewerMenuAction();
		UpdateReviewerMenuAction updateReviewerAction = new UpdateReviewerMenuAction();
		SearchReviewerMenuAction searchReviewerAction = new SearchReviewerMenuAction();
		ListAllReviewerMenuAction listAllReviewerAction = new ListAllReviewerMenuAction();
		//#endif
		
		InsertEventMenuAction insertEventAction = new InsertEventMenuAction();
		
		RemoveEventMenuAction removeEventAction = new RemoveEventMenuAction();
		UpdateEventMenuAction updateEventAction = new UpdateEventMenuAction();
		SearchEventMenuAction searchEventAction = new SearchEventMenuAction();
		ListAllEventMenuAction listAllEventAction = new ListAllEventMenuAction();
		ManagementEventMenuAction managementEventAction = new ManagementEventMenuAction();
		//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
		InsertReviewMenuAction insertReviewAction = new InsertReviewMenuAction();
		RemoveReviewMenuAction removeReviewAction = new RemoveReviewMenuAction();
		UpdateReviewMenuAction updateReviewAction = new UpdateReviewMenuAction();
		SearchReviewMenuAction searchReviewAction = new SearchReviewMenuAction();
		ListAllReviewMenuAction listAllReviewAction = new ListAllReviewMenuAction();
		ManagementReviewMenuAction managementReviewAction = new ManagementReviewMenuAction();
		ResultsReviewMenuAction resultsReviewAction = new ResultsReviewMenuAction();
		//#endif
		
		//#if ${SubmissionCompleta} == "T"
		InsertSubmissionMenuAction insertSubmissionAction = new InsertSubmissionMenuAction();
		//#endif
		//#if ${SubmissionParcial} == "T" 
		InsertSubmissionPartialMenuAction insertSubmissionPartialAction = new InsertSubmissionPartialMenuAction();
		//#endif
		//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
		SearchSubmissionMenuAction searchSubmissionAction = new SearchSubmissionMenuAction();
		ListAllSubmissionMenuAction listAllSubmissionAction = new ListAllSubmissionMenuAction();
		RemoveSubmissionMenuAction removeSubmissionAction = new RemoveSubmissionMenuAction();
		//#endif
		//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
		InsertAssignmentMenuAction insertAssignmentAction = new InsertAssignmentMenuAction();
		RemoveAssignmentMenuAction removeAssignmentAction = new RemoveAssignmentMenuAction();
		ListAllAssignmentMenuAction listAllAssignmentAction = new ListAllAssignmentMenuAction();
		ManagementAssignmentMenuAction managementAssignmentAction = new ManagementAssignmentMenuAction();
		SearchAssignmentMenuAction searchAssignmentAction = new SearchAssignmentMenuAction();
		//#endif
		
		InsertRegistrationMenuAction insertRegistrationAction = new InsertRegistrationMenuAction();
		RemoveRegistrationMenuAction removeRegistrationAction = new RemoveRegistrationMenuAction();
		UpdateRegistrationMenuAction updateRegistrationAction = new UpdateRegistrationMenuAction();
		SearchRegistrationMenuAction searchRegistrationAction = new SearchRegistrationMenuAction();
		ListAllRegistrationMenuAction listAllRegistrationAction = new ListAllRegistrationMenuAction();
		ManagementRegistrationMenuAction managementRegistrationAction = new ManagementRegistrationMenuAction();
		
		//#if ${Bugs} == "T"
		BugtrackScreenMenuAction bugtrackAction = new BugtrackScreenMenuAction();
		//#endif
		
		//		
		//ADICIONADO
		RiSEEventMainScreenP.facade = RiSEventFacade.getInstance();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1120, 691);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		desktopPane.setBounds(6, 36, 1104, 624);
		contentPane.add(desktopPane);
		
		labelImagem = new JLabel("");
		labelImagem.setBounds(210, 164, 648, 193);
		ImageIcon image = new ImageIcon(getClass().getResource("/images/riseLabs.png"));
		Image imag = image.getImage().getScaledInstance(labelImagem.getWidth(), labelImagem.getHeight(), Image.SCALE_SMOOTH);
		labelImagem.setIcon(new ImageIcon(imag));
		desktopPane.add(labelImagem);
		
		
		menuBar.setBounds(32, 12, 1078, 22);
		contentPane.add(menuBar);
		
		JMenu mnArchieve = new JMenu("Archieve");
		menuBar.add(mnArchieve);
		
		//#if ${Bugs} == "T"		
		JMenuItem mntmBugtrack = new JMenuItem("Bugtrack");
		mnArchieve.add(mntmBugtrack);
		//#endif
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnArchieve.add(mntmExit);

		JMenu mnUser = new JMenu("User");
		menuBar.add(mnUser);
		
		JMenuItem mntmInsert = new JMenuItem("Insert");
		mnUser.add(mntmInsert);
		
		JMenuItem mntmRemove = new JMenuItem("Remove");
		mnUser.add(mntmRemove);
		
		JMenuItem mntmUpdate = new JMenuItem("Update");
		mnUser.add(mntmUpdate);
		
		JMenuItem mntmSearch = new JMenuItem("Search");
		mnUser.add(mntmSearch);
		
		JMenuItem mntmListall = new JMenuItem("ListALL");
		mnUser.add(mntmListall);

		
//		JMenuItem mntmUserManagement = new JMenuItem("User Management");
//		mnUser.add(mntmUserManagement);
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
		JMenu mnEvent = new JMenu("Event");
		menuBar.add(mnEvent);
		
		JMenuItem mntmInsert_4 = new JMenuItem("Insert");
		mnEvent.add(mntmInsert_4);
		
		JMenuItem mntmRemove_4 = new JMenuItem("Remove");
		mnEvent.add(mntmRemove_4);
		
		JMenuItem mntmUpdate_4 = new JMenuItem("Update");
		mnEvent.add(mntmUpdate_4);
		
		JMenuItem mntmSearch_4 = new JMenuItem("Search");
		mnEvent.add(mntmSearch_4);
		
		JMenuItem mntmListall_4 = new JMenuItem("ListAll");
		mnEvent.add(mntmListall_4);
		
		JMenuItem mntmEventManagement = new JMenuItem("Event Management");
		mnEvent.add(mntmEventManagement);
		//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
		JMenu mnReview = new JMenu("Review");
		menuBar.add(mnReview);
		
		JMenuItem mntmInsert_6 = new JMenuItem("Insert");
		mnReview.add(mntmInsert_6);
		
		JMenuItem mntmRemove_6 = new JMenuItem("Remove");
		mnReview.add(mntmRemove_6);
		
		JMenuItem mntmUpdate_6 = new JMenuItem("Update");
		mnReview.add(mntmUpdate_6);
		
		JMenuItem mntmSearch_6 = new JMenuItem("Search");
		mnReview.add(mntmSearch_6);
		
		JMenuItem mntmListall_6 = new JMenuItem("ListAll");
		mnReview.add(mntmListall_6);
		
		JMenuItem mntmReviewManagement = new JMenuItem("Review Management");
		mnReview.add(mntmReviewManagement);
		
		JMenuItem mntmReviewResults = new JMenuItem("Review Results");
		mnReview.add(mntmReviewResults);
		//#endif
		
		menuBar.add(mnRegistration);

		JMenuItem mntmInsert_10 = new JMenuItem("Insert");
		mnRegistration.add(mntmInsert_10);
		
		JMenuItem mntmRemove_10 = new JMenuItem("Remove");
		mnRegistration.add(mntmRemove_10);
		
		JMenuItem mntmUpdate_10 = new JMenuItem("Update");
		mnRegistration.add(mntmUpdate_10);
		
		JMenuItem mntmSearch_10 = new JMenuItem("Search");
		mnRegistration.add(mntmSearch_10);
		
		JMenuItem mntmListall_10 = new JMenuItem("ListAll");
		mnRegistration.add(mntmListall_10);
		
		JMenuItem mntmRegistrationManagement = new JMenuItem("Registration Management");
		mnRegistration.add(mntmRegistrationManagement);
		
		//#if ${ReportsListofAuthors} == "T" or ${ReportsFrequencyperActivity} == "T" or ${ReportsFrequencyperEvent} == "T" or ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"	
		menuBar.add(mnReports);
		//#endif

		//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
		JMenu mnSubmission = new JMenu("Submission");
		menuBar.add(mnSubmission);
		//#if ${SubmissionCompleta} == "T"
		JMenuItem mntmInsertComplete = new JMenuItem("Insert Complete");
		mnSubmission.add(mntmInsertComplete);
		//#endif
		//#if ${SubmissionParcial} == "T" 
		JMenuItem mntmInsertPartial = new JMenuItem("Insert Partial");
		mnSubmission.add(mntmInsertPartial);
		//#endif
		JMenuItem mntmRemove_8 = new JMenuItem("Remove");
		mnSubmission.add(mntmRemove_8);
		
		JMenuItem mntmSearch_8 = new JMenuItem("Search");
		mnSubmission.add(mntmSearch_8);
		
		JMenuItem mntmListall_8 = new JMenuItem("List All");
		mnSubmission.add(mntmListall_8);
		//#endif
		//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
		JMenu mnAssignment = new JMenu("Assignment");
		menuBar.add(mnAssignment);
		
		JMenuItem mntmInsert_9 = new JMenuItem("Insert");
		mnAssignment.add(mntmInsert_9);
		
		JMenuItem mntmRemove_9 = new JMenuItem("Remove");
		mnAssignment.add(mntmRemove_9);
		
		JMenuItem mntmSearch_9 = new JMenuItem("Search");
		mnAssignment.add(mntmSearch_9);
		
		JMenuItem mntmListall_9 = new JMenuItem("List All");
		mnAssignment.add(mntmListall_9);
		
		JMenuItem mntmAssignmentManagement = new JMenuItem("Assignment Management");
		mnAssignment.add(mntmAssignmentManagement);
		//#endif
		mntmExit.addActionListener(exitMenuAction);

		mntmInsert.addActionListener(insertUserAction);
		
		mntmRemove.addActionListener(removeUserAction);
		
		mntmUpdate.addActionListener(updateUserAction);
		
		mntmSearch.addActionListener(searchUserAction);
		
		mntmListall.addActionListener(listAllUserAction);

		//mntmUserManagement.addActionListener(managementUserAction);
		//#if ${Reviewer} == "T"
		mntmInsert_2.addActionListener(insertReviewerAction);
		mntmRemove_2.addActionListener(removeReviewerAction);
		mntmUpdate_2.addActionListener(updateReviewerAction);
		mntmSearch_2.addActionListener(searchReviewerAction);
		mntmListall_2.addActionListener(listAllReviewerAction);
		//#endif

		mntmInsert_4.addActionListener(insertEventAction);
		
		mntmRemove_4.addActionListener(removeEventAction);
		mntmUpdate_4.addActionListener(updateEventAction);
		mntmSearch_4.addActionListener(searchEventAction);
		mntmListall_4.addActionListener(listAllEventAction);
		mntmEventManagement.addActionListener(managementEventAction);
		//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
		mntmInsert_6.addActionListener(insertReviewAction);
		mntmRemove_6.addActionListener(removeReviewAction);
		mntmUpdate_6.addActionListener(updateReviewAction);
		mntmSearch_6.addActionListener(searchReviewAction);
		mntmListall_6.addActionListener(listAllReviewAction);
		mntmReviewManagement.addActionListener(managementReviewAction);
		mntmReviewResults.addActionListener(resultsReviewAction);
		//#endif
		
		//#if ${SubmissionCompleta} == "T"
		mntmInsertComplete.addActionListener(insertSubmissionAction);
		//#endif
		//#if ${SubmissionParcial} == "T" 
		mntmInsertPartial.addActionListener(insertSubmissionPartialAction);
		//#endif
		//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
		mntmSearch_8.addActionListener(searchSubmissionAction);
		mntmListall_8.addActionListener(listAllSubmissionAction);
		mntmRemove_8.addActionListener(removeSubmissionAction);
		//#endif
		//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
		mntmInsert_9.addActionListener(insertAssignmentAction);
		mntmSearch_9.addActionListener(searchAssignmentAction);
		mntmRemove_9.addActionListener(removeAssignmentAction);
		mntmListall_9.addActionListener(listAllAssignmentAction);
		mntmAssignmentManagement.addActionListener(managementAssignmentAction);
		//#endif
		
		mntmInsert_10.addActionListener(insertRegistrationAction);
		mntmRemove_10.addActionListener(removeRegistrationAction);
		mntmUpdate_10.addActionListener(updateRegistrationAction);
		mntmSearch_10.addActionListener(searchRegistrationAction);
		mntmListall_10.addActionListener(listAllRegistrationAction);
		mntmRegistrationManagement.addActionListener(managementRegistrationAction);
		
		//#if ${Bugs} == "T"
		mntmBugtrack.addActionListener(bugtrackAction);
		//#endif
	}
	
	private class ExitMenuAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	} 

	private class InsertUserMenuAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenInsertUser = UserInsertScreenP.getInstanceUserInsertScreenP();
			if(screenInsertUser.getParent() == null){
				desktopPane.add(screenInsertUser);
			}
			screenInsertUser.setVisible(true);
			desktopPane.moveToFront(screenInsertUser);
			try {
				screenInsertUser.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	
	private class RemoveUserMenuAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenRemoveUser = UserRemoveScreenP.getInstanceUserRemoveScreenP();
			if(screenRemoveUser.getParent() == null){
				desktopPane.add(screenRemoveUser);
			}
			screenRemoveUser.setVisible(true);
			desktopPane.moveToFront(screenRemoveUser);
			try {
				screenRemoveUser.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	
	private class UpdateUserMenuAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenUpdateUser = UserUpdateScreenP.getInstanceUserUpdateScreenP();
		//	desktopPane.add(screenUpdateUser);
			if(screenUpdateUser.getParent() == null){
				desktopPane.add(screenUpdateUser);
			}
			screenUpdateUser.setVisible(true);
			desktopPane.moveToFront(screenUpdateUser);
			try {
				screenUpdateUser.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}  
	}

	private class SearchUserMenuAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenSearchUser = UserSearchScreenP.getInstanceUserSearchScreenP();
		//	desktopPane.add(screenSearchUser);
			if(screenSearchUser.getParent() == null){
				desktopPane.add(screenSearchUser);
			}
			screenSearchUser.setVisible(true);
			desktopPane.moveToFront(screenSearchUser);
			try {
				screenSearchUser.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}  
	}

	private class ListAllUserMenuAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenListAllUser = UserListAllScreenP.getInstanceUserListAllScreenP();
			//desktopPane.add(screenListAllUser);
			if(screenListAllUser.getParent() == null && screenListAllUser.getParent() != desktopPane){
				desktopPane.add(screenListAllUser);
			}
			screenListAllUser.setVisible(true);
			desktopPane.moveToFront(screenListAllUser);
			try {
				screenListAllUser.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}  
	}

//	private class ManagementUserMenuAction  implements ActionListener{ 
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			screenManagementUser = UserManagementScreenP.getInstanceUserManagementScreenP();
//			if(screenManagementUser.getParent() == null && screenManagementUser.getParent() != desktopPane){
//				desktopPane.add(screenManagementUser);
//			}
//			screenManagementUser.setVisible(true);
//			desktopPane.moveToFront(screenManagementUser);
//			try {
//				screenManagementUser.setSelected(true);
//			} catch (PropertyVetoException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}  
//	}
	
	
	
	
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
		
		
		//EVENT
		
		private class InsertEventMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				
				screenInsertEvent = EventInsertScreenP.getInstanceEventInsertScreenP();
				//desktopPane.add(screenInsertEvent);
				if(screenInsertEvent.getParent() == null){
					desktopPane.add(screenInsertEvent);
				}
				screenInsertEvent.setVisible(true);
				desktopPane.moveToFront(screenInsertEvent);
				try {
					screenInsertEvent.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}  
		}
		
		
		private class RemoveEventMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				
				screenRemoveEvent = EventRemoveScreenP.getInstanceEventRemoveScreenP();
				//desktopPane.add(screenRemoveEvent);
				if(screenRemoveEvent.getParent() == null){
					desktopPane.add(screenRemoveEvent);
				}
				screenRemoveEvent.setVisible(true);
				desktopPane.moveToFront(screenRemoveEvent);
				try {
					screenRemoveEvent.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}  
		}
		
		private class UpdateEventMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				
				screenUpdateEvent = EventUpdateScreenP.getInstanceEventUpdateScreenP();
			//	desktopPane.add(screenUpdateEvent);
				if(screenUpdateEvent.getParent() == null){
					desktopPane.add(screenUpdateEvent);
				}
				screenUpdateEvent.setVisible(true);
				desktopPane.moveToFront(screenUpdateEvent);
				try {
					screenUpdateEvent.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}
		
		private class SearchEventMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				
				screenSearchEvent = EventSearchScreenP.getInstanceEventSearchScreenP();
				//desktopPane.add(screenSearchEvent);
				if(screenSearchEvent.getParent() == null){
					desktopPane.add(screenSearchEvent);
				}
				screenSearchEvent.setVisible(true);
				desktopPane.moveToFront(screenSearchEvent);
				try {
					screenSearchEvent.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}
		
		private class ListAllEventMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				
				screenListAllEvent = EventListAllScreenP.getInstanceEventListAllScreenP();
				//desktopPane.add(screenListAllEvent);
				if(screenListAllEvent.getParent() == null){
					desktopPane.add(screenListAllEvent);
				}
				screenListAllEvent.setVisible(true);
				desktopPane.moveToFront(screenListAllEvent);
				try {
					screenListAllEvent.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}
		
		private class ManagementEventMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				screenManagementEvent = EventManagementScreenP.getInstanceEventManagementScreenP();
				//desktopPane.add(screenManagementEvent);
				if(screenManagementEvent.getParent() == null){
					desktopPane.add(screenManagementEvent);
				}
				screenManagementEvent.setVisible(true);
				desktopPane.moveToFront(screenManagementEvent);
				try {
					screenManagementEvent.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}
		
		//REVIEW
		//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
		private class InsertReviewMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenInsertReview = ReviewInsertScreenP.getInstanceReviewInsertScreenP();
				//desktopPane.add(screenInsertReview);
				if(screenInsertReview.getParent() == null){
					desktopPane.add(screenInsertReview);
				}
				screenInsertReview.setVisible(true);
				desktopPane.moveToFront(screenInsertReview);
				try {
					screenInsertReview.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}  
		}

		private class RemoveReviewMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenRemoveReview = ReviewRemoveScreenP.getInstanceReviewRemoveScreenP();
				//desktopPane.add(screenRemoveReview);
				if(screenRemoveReview.getParent() == null){
					desktopPane.add(screenRemoveReview);
				}
				screenRemoveReview.setVisible(true);
				desktopPane.moveToFront(screenRemoveReview);
				try {
					screenRemoveReview.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}  
		}

		private class UpdateReviewMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenUpdateReview = ReviewUpdateScreenP.getInstanceReviewUpdateScreenP();
				//desktopPane.add(screenUpdateReview);
				if(screenUpdateReview.getParent() == null){
					desktopPane.add(screenUpdateReview);
				}
				screenUpdateReview.setVisible(true);
				desktopPane.moveToFront(screenUpdateReview);
				try {
					screenUpdateReview.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}

		private class SearchReviewMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenSearchReview = ReviewSearchScreenP.getInstanceReviewSearchScreenP();
				//desktopPane.add(screenSearchReview);
				if(screenSearchReview.getParent() == null){
					desktopPane.add(screenSearchReview);
				}
				screenSearchReview.setVisible(true);
				desktopPane.moveToFront(screenSearchReview);
				try {
					screenSearchReview.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}

		private class ListAllReviewMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				screenListAllReview = ReviewListAllScreenP.getInstanceReviewListAllScreenP();
				//desktopPane.add(screenListAllReview);
				if(screenListAllReview.getParent() == null){
					desktopPane.add(screenListAllReview);
				}
				screenListAllReview.setVisible(true);
				desktopPane.moveToFront(screenListAllReview);
				try {
					screenListAllReview.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}

		private class ManagementReviewMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				screenManagementReview = ReviewManagementScreenP.getInstanceReviewManagementScreenP();
				//desktopPane.add(screenManagementReview);
				if(screenManagementReview.getParent() == null){
					desktopPane.add(screenManagementReview);
				}
				screenManagementReview.setVisible(true);
				desktopPane.moveToFront(screenManagementReview);
				try {
					screenManagementReview.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}
		
		private class ResultsReviewMenuAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				screenResultsReview = ReviewResultsScreenP.getInstanceReviewResultsScreenP();
				//desktopPane.add(screenManagementReview);
				if(screenResultsReview.getParent() == null){
					desktopPane.add(screenResultsReview);
				}
				screenResultsReview.setVisible(true);
				desktopPane.moveToFront(screenResultsReview);
				try {
					screenResultsReview.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		}
		//#endif
		
		
				
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
			//#if ${SubmissionParcial} == "T" 
				private class InsertSubmissionPartialMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {

						screenInsertSubmissionPartial = SubmissionPartialInsertScreenP.getInstanceSubmissionPartialInsertScreenP();
						//desktopPane.add(screenInsertSubmission);
						if(screenInsertSubmissionPartial.getParent() == null){
							desktopPane.add(screenInsertSubmissionPartial);
						}  
						screenInsertSubmissionPartial.setVisible(true);
						desktopPane.moveToFront(screenInsertSubmissionPartial);
						try {
							screenInsertSubmissionPartial.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}  
				}
				//#endif
				//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"	
				private class RemoveSubmissionMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {

						screenRemoveSubmission = SubmissionRemoveScreenP.getInstanceSubmissionRemoveScreenP();
						//desktopPane.add(screenRemoveSubmission);
						if(screenRemoveSubmission.getParent() == null){
							desktopPane.add(screenRemoveSubmission);
						}
						screenRemoveSubmission.setVisible(true);
						desktopPane.moveToFront(screenRemoveSubmission);
						try {
							screenRemoveSubmission.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}  
				}

				private class SearchSubmissionMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {

						screenSearchSubmission = SubmissionSearchScreenP.getInstanceSubmissionSearchScreenP();
						//desktopPane.add(screenSearchSubmission);
						if(screenSearchSubmission.getParent() == null){
							desktopPane.add(screenSearchSubmission);
						}
						screenSearchSubmission.setVisible(true);
						desktopPane.moveToFront(screenSearchSubmission);
						try {
							screenSearchSubmission.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}  
				}

				private class ListAllSubmissionMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {

						screenListAllSubmission = SubmissionListAllScreenP.getInstanceSubmissionListAllScreenP();
					//	desktopPane.add(screenListAllSubmission);
						if(screenListAllSubmission.getParent() == null){
							desktopPane.add(screenListAllSubmission);
						}
						screenListAllSubmission.setVisible(true);
						desktopPane.moveToFront(screenListAllSubmission);
						try {
							screenListAllSubmission.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}  
				}

				//#endif
				
				//Assignemnt
				//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
				private class InsertAssignmentMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {

						screenInsertAssignment = AssignmentInsertScreenP.getInstanceAssignmentInsertScreenP();
						//desktopPane.add(screenInsertAssignment);
						if(screenInsertAssignment.getParent() == null){
							desktopPane.add(screenInsertAssignment);
						}
						screenInsertAssignment.setVisible(true);
						desktopPane.moveToFront(screenInsertAssignment);
						try {
							screenInsertAssignment.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}  
				}
				
				private class SearchAssignmentMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {

						screenSearchAssignment = AssignmentSearchScreenP.getInstanceAssignmentSearchScreenP();
						//desktopPane.add(screenSearchAssignment);
						if(screenSearchAssignment.getParent() == null){
							desktopPane.add(screenSearchAssignment);
						}
						screenSearchAssignment.setVisible(true);
						desktopPane.moveToFront(screenSearchAssignment);
						try {
							screenSearchAssignment.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}  
				}
				
				private class RemoveAssignmentMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {
						
						screenRemoveAssignment = AssignmentRemoveScreenP.getInstanceAssignmentRemoveScreenP();
						//desktopPane.add(screenRemoveAssignment);
						if(screenRemoveAssignment.getParent() == null){
							desktopPane.add(screenRemoveAssignment);
						}
						screenRemoveAssignment.setVisible(true);
						desktopPane.moveToFront(screenRemoveAssignment);
						try {
							screenRemoveAssignment.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}  
				}
				
				private class ListAllAssignmentMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {
						
						screenListAllAssignment = AssignmentListAllScreenP.getInstanceAssignmentListAllScreenP();
						//desktopPane.add(screenListAllAssignment);
						if(screenListAllAssignment.getParent() == null){
							desktopPane.add(screenListAllAssignment);
						}
						screenListAllAssignment.setVisible(true);
						desktopPane.moveToFront(screenListAllAssignment);
						try {
							screenListAllAssignment.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}  
				}
				
				private class ManagementAssignmentMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {
						screenManagementAssignment = AssignmentManagementScreenP.getInstanceAssignmentManagementScreenP();
						//desktopPane.add(screenManagementAssignment);
						if(screenManagementAssignment.getParent() == null){
							desktopPane.add(screenManagementAssignment);
						}
						screenManagementAssignment.setVisible(true);
						desktopPane.moveToFront(screenManagementAssignment);
						try {
							screenManagementAssignment.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}  
				}
				//#endif
		
		//REGISTRATION
		
				private class InsertRegistrationMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {

						screenInsertRegistration = RegistrationInsertScreenP.getInstanceRegistrationInsertScreenP();
						//desktopPane.add(screenInsertRegistration);
						if(screenInsertRegistration.getParent() == null){
							desktopPane.add(screenInsertRegistration);
						}
						screenInsertRegistration.setVisible(true);
						desktopPane.moveToFront(screenInsertRegistration);
						try {
							screenInsertRegistration.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}  
				}

				private class RemoveRegistrationMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {

						screenRemoveRegistration = RegistrationRemoveScreenP.getInstanceRegistrationRemoveScreenP();
						//desktopPane.add(screenRemoveRegistration);
						if(screenRemoveRegistration.getParent() == null){
							desktopPane.add(screenRemoveRegistration);
						}
						screenRemoveRegistration.setVisible(true);
						desktopPane.moveToFront(screenRemoveRegistration);
						try {
							screenRemoveRegistration.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}  
				}

				private class UpdateRegistrationMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {

						screenUpdateRegistration = RegistrationUpdateScreenP.getInstanceRegistrationUpdateScreenP();
						//desktopPane.add(screenUpdateRegistration);
						if(screenUpdateRegistration.getParent() == null){
							desktopPane.add(screenUpdateRegistration);
						}
						screenUpdateRegistration.setVisible(true);
						desktopPane.moveToFront(screenUpdateRegistration);
						try {
							screenUpdateRegistration.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}  
				}

				private class SearchRegistrationMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {

						screenSearchRegistration = RegistrationSearchScreenP.getInstanceRegistrationSearchScreenP();
						//desktopPane.add(screenSearchRegistration);
						if(screenSearchRegistration.getParent() == null){
							desktopPane.add(screenSearchRegistration);
						}
						screenSearchRegistration.setVisible(true);
						desktopPane.moveToFront(screenSearchRegistration);
						try {
							screenSearchRegistration.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}  
				}

				private class ListAllRegistrationMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {

						screenListAllRegistration = RegistrationListAllScreenP.getInstanceRegistrationListAllScreenP();
						//desktopPane.add(screenListAllRegistration);
						if(screenListAllRegistration.getParent() == null){
							desktopPane.add(screenListAllRegistration);
						}
						screenListAllRegistration.setVisible(true);
						desktopPane.moveToFront(screenListAllRegistration);
						try {
							screenListAllRegistration.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}  
				}

				private class ManagementRegistrationMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {
						screenManagementRegistration = RegistrationManagementScreenP.getInstanceRegistrationManagementScreenP();
						//desktopPane.add(screenManagementRegistration);
						if(screenManagementRegistration.getParent() == null){
							desktopPane.add(screenManagementRegistration);
						}
						screenManagementRegistration.setVisible(true);
						desktopPane.moveToFront(screenManagementRegistration);
						try {
							screenManagementRegistration.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}  
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
