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
	//#if ${RegistrationSpeakerActivity} == "T"
	private ActivitySpeakerManagementScreenP screenActivitySpeakerManagement;
	//#endif
	//#if ${ReportsFrequencyperActivity} == "T" 
	private FrequencyPerActivityScreenP screenFrequencyPerActivity;
	//#endif
	//#if ${ReportsFrequencyperEvent} == "T"
	private FrequencyPerEventScreenP screenFrequencyPerEvent;
	//#endif
	//#if ${ReportsListofAuthors} == "T"
	private ListOfAuthorsPerActivityScreenP screenListOfAuthorsPerActivity;
	//#endif
	
	//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
	private PaymentInsertScreenP screenInsertPayment;
	private PaymentRemoveScreenP screenRemovePayment;
	private PaymentUpdateScreenP screenUpdatePayment;
	private PaymentSearchScreenP screenSearchPayment;
	private PaymentListAllScreenP screenListAllPayment;
	private PaymentManagementScreenP screenManagementPayment;
	//#endif
	//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
	private CheckingCopyInsertScreenP screenInsertCheckingCopy;
	private CheckingCopyRemoveScreenP screenRemoveCheckingCopy;
	private CheckingCopyUpdateScreenP screenUpdateCheckingCopy;
	private CheckingCopySearchScreenP screenSearchCheckingCopy;
	private CheckingCopyListAllScreenP screenListAllCheckingCopy;
	private CheckingCopyManagementScreenP screenManagementCheckingCopy;
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
	//#if ${Receipt} == "T" 
	private ReceiptScreenP screenReceipt;
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

	public JMenuBar menuBar = new JMenuBar(); // moved out from constructor due compiler issue
	public JMenu mnRegistration = new JMenu("Registration"); // moved out due references issue
	
	/**
	 * Create the frame.
	 */
	public RiSEEventMainScreenP() {
		init();
	}
	
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
