package rise.splcc.derivation;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.tools.ant.util.WorkerAnt;

public class DerivationScreen extends JFrame {

	private JPanel contentPane;
	private JCheckBox chckbxSpeaker;
	private JCheckBox chckbxOrganizer;
	private JCheckBox chckbxReviewer; 
	private JCheckBox chckbxGenerateEventProgram;
	private JCheckBox chckbxGenerateEventImportantDates;
	private JCheckBox chckbxWorkshopActivity;
	private JCheckBox chckbxTutorialActivity;
	private JCheckBox chckbxPanelActivity;
	private JCheckBox chckbxMinicurso;
	private JCheckBox chckbxRoundOfReview;
	private JCheckBox chckbxSimpleReview ;
	private JCheckBox chckbxRegistrationuseractivity;
	private JCheckBox chckbxRegistrationspeakeractivity;
	private JCheckBox chckbxRegistrationorganizeractivity;
	private JCheckBox chckbxReportfrequencyPerEvent;
	private JCheckBox chckbxReportfrequencyPerActivity;
	private JCheckBox chckbxReportlistOfAuthors;
	private JCheckBox chckbxCheckingCopyatestado;
	private JCheckBox chckbxCheckingCopycertificado;
	private JCheckBox chckbxPaymentaVista;
	private JCheckBox chckbxPaymentdeposit;
	private JCheckBox chckbxPaymentcredit ;
	private JCheckBox chckbxSubmissionpartial;
	private JCheckBox chckbxSubmissioncomplete;
	private JCheckBox chckbxAssignmentchairIndication;
//	private JCheckBox chckbxAssignmentpreferenceIndication;
	private JCheckBox chckbxAssignmentautomatic;
	private JCheckBox chckbxConflictOfInterestAutomatic;
//	private JCheckBox chckbxConflictOfInterestManual;
	private JCheckBox chckbxNotificationdeadline;
	private JCheckBox chckbxNotificationacceptacerejection;
	private JCheckBox chckbxNotificationpaperAssignment;
	private JCheckBox chckbxInsertAuthors;
	private JCheckBox chckbxBugtrack ;
	private JCheckBox chckbxReceipt ;
	private JCheckBox chckbxMainTrack;
	
	private JButton btnBack;
	private JButton btnDerivate;
	private JButton btnClean;
	private  List<Feature> featureList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DerivationScreen frame = new DerivationScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DerivationScreen() {
		setTitle("Product Derivation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		BackButtonAction backAction = new BackButtonAction(); 
		CleanButtonAction cleanAction = new CleanButtonAction(); 
		DerivateButtonAction derivateAction = new DerivateButtonAction();
		SpeakerCheckBoxAction speakerCheckAction = new SpeakerCheckBoxAction();
		OrganizerCheckBoxAction organizerCheckAction = new OrganizerCheckBoxAction();
		ReviewerCheckBoxAction reviewerCheckBoxAction = new ReviewerCheckBoxAction ();
		EventProgramCheckBoxAction eventProgramCheckBoxAction = new EventProgramCheckBoxAction ();
		EventImportantDatesCheckBoxAction eventImportantDatesCheckBoxAction = new EventImportantDatesCheckBoxAction ();
		ActivityWorkshopCheckBoxAction activityWorkshopCheckBoxAction = new ActivityWorkshopCheckBoxAction ();
		ActivityTutorialCheckBoxAction activityTutorialCheckBoxAction = new ActivityTutorialCheckBoxAction ();
		ActivityPainelCheckBoxAction activityPainelCheckBoxAction = new ActivityPainelCheckBoxAction ();
		ActivityMinicursoCheckBoxAction activityMinicursoCheckBoxAction = new ActivityMinicursoCheckBoxAction ();
		ReviewRoundofReviewCheckBoxAction reviewRoundofReviewCheckBoxAction = new ReviewRoundofReviewCheckBoxAction ();
		ReviewSimpleReviewCheckBoxAction reviewSimpleReviewCheckBoxAction = new ReviewSimpleReviewCheckBoxAction ();
     	RegistrationUserActivityCheckBoxAction registrationUserActivityCheckBoxAction = new RegistrationUserActivityCheckBoxAction ();
		RegistrationSpeakerActivityCheckBoxAction registrationSpeakerActivityCheckBoxAction = new RegistrationSpeakerActivityCheckBoxAction ();
		RegistrationOrganizerActivityCheckBoxAction  registrationOrganizerActivityCheckBoxAction= new RegistrationOrganizerActivityCheckBoxAction ();
		ReportsFrequencyperEventCheckBoxAction reportsFrequencyperEventCheckBoxAction = new ReportsFrequencyperEventCheckBoxAction ();
		ReportsFrequencyperActivityCheckBoxAction reportsFrequencyperActivityCheckBoxAction = new ReportsFrequencyperActivityCheckBoxAction ();
		ReportsListofAuthorsCheckBoxAction reportsListofAuthorsCheckBoxAction = new ReportsListofAuthorsCheckBoxAction ();
		CheckingCopyAtestadoCheckBoxAction checkingCopyAtestadoCheckBoxAction = new CheckingCopyAtestadoCheckBoxAction ();
		CheckingCopyCertificadoCheckBoxAction checkingCopyCertificadoCheckBoxAction = new CheckingCopyCertificadoCheckBoxAction ();
		PaymentAvistaCheckBoxAction paymentAvistaCheckBoxAction = new PaymentAvistaCheckBoxAction ();
		PaymentDepositoCheckBoxAction paymentDepositoCheckBoxAction = new PaymentDepositoCheckBoxAction ();
		PaymentCartaoCheckBoxAction paymentCartaoCheckBoxAction = new PaymentCartaoCheckBoxAction ();
		SubmissionParcialCheckBoxAction submissionParcialCheckBoxAction = new SubmissionParcialCheckBoxAction ();
		SubmissionCompletaCheckBoxAction submissionCompletaCheckBoxAction = new SubmissionCompletaCheckBoxAction ();
		AssignmentChairindicationCheckBoxAction assignmentChairindicationCheckBoxAction = new AssignmentChairindicationCheckBoxAction ();
//		AssignmentPreferenceindicationCheckBoxAction assignmentPreferenceindicationCheckBoxAction = new AssignmentPreferenceindicationCheckBoxAction ();
		AssignmentautomaticCheckBoxAction assignmentautomaticCheckBoxAction = new AssignmentautomaticCheckBoxAction ();
     	ConflictofinterestAutomaticCheckBoxAction conflictofinterestAutomaticCheckBoxAction = new ConflictofinterestAutomaticCheckBoxAction ();
//		ConflictofinterestManualCheckBoxAction conflictofinterestManualCheckBoxAction = new ConflictofinterestManualCheckBoxAction ();
		NotificationsDeadlineCheckBoxAction  notificationsDeadlineCheckBoxAction = new NotificationsDeadlineCheckBoxAction ();
		NotificationsAceptanceRejectionCheckBoxAction notificationsAceptanceRejectionCheckBoxAction = new NotificationsAceptanceRejectionCheckBoxAction ();
		NotificationsPaperAssignemntCheckBoxAction notificationsPaperAssignemntCheckBoxAction = new NotificationsPaperAssignemntCheckBoxAction ();
		InsertAuthorsCheckBoxAction  insertAuthorsCheckBoxAction = new InsertAuthorsCheckBoxAction ();
		BugsCheckBoxAction bugsCheckBoxAction = new BugsCheckBoxAction ();
		ReceiptCheckBoxAction receiptCheckBoxAction = new ReceiptCheckBoxAction ();
		ActivityMainTrackCheckBoxAction activityMainTrackCheckBoxAction = new ActivityMainTrackCheckBoxAction ();
		
		btnBack = new JButton("Back");
		btnBack.setBounds(436, 379, 117, 29);
		contentPane.add(btnBack);
		
		btnDerivate = new JButton("Derivate");
		btnDerivate.setBounds(170, 379, 117, 29);
		contentPane.add(btnDerivate);
		
		chckbxSpeaker = new JCheckBox("Speaker");
		chckbxSpeaker.setBounds(49, 60, 128, 23);
		contentPane.add(chckbxSpeaker);
		
		chckbxOrganizer = new JCheckBox("Organizer");
		chckbxOrganizer.setBounds(49, 79, 128, 23);
		contentPane.add(chckbxOrganizer);
		
		chckbxReviewer = new JCheckBox("Reviewer");
		chckbxReviewer.setBounds(49, 98, 128, 23);
		contentPane.add(chckbxReviewer);
		
		chckbxGenerateEventProgram = new JCheckBox("Generate Event Program");
		chckbxGenerateEventProgram.setBounds(49, 116, 181, 23);
		contentPane.add(chckbxGenerateEventProgram);
		
		chckbxGenerateEventImportantDates = new JCheckBox("Generate Event Important Dates");
		chckbxGenerateEventImportantDates.setBounds(49, 134, 231, 23);
		contentPane.add(chckbxGenerateEventImportantDates);
		
		chckbxWorkshopActivity = new JCheckBox("Workshop Activity");
		chckbxWorkshopActivity.setBounds(49, 151, 161, 23);
		contentPane.add(chckbxWorkshopActivity);
		
		chckbxTutorialActivity = new JCheckBox("Tutorial Activity");
		chckbxTutorialActivity.setBounds(49, 169, 139, 23);
		contentPane.add(chckbxTutorialActivity);
		
		chckbxPanelActivity = new JCheckBox("Panel Activity");
		chckbxPanelActivity.setBounds(49, 186, 128, 23);
		contentPane.add(chckbxPanelActivity);
		
		chckbxMinicurso = new JCheckBox("Minicurso");
		chckbxMinicurso.setBounds(49, 204, 128, 23);
		contentPane.add(chckbxMinicurso);
		
		chckbxRoundOfReview = new JCheckBox("Round of Review");
		chckbxRoundOfReview.setBounds(49, 239, 150, 23);
		contentPane.add(chckbxRoundOfReview);
		
		chckbxSimpleReview = new JCheckBox("Simple Review");
		chckbxSimpleReview.setBounds(49, 256, 128, 23);
		contentPane.add(chckbxSimpleReview);
		
		chckbxRegistrationuseractivity = new JCheckBox("Registration (User -> Activity)");
		chckbxRegistrationuseractivity.setBounds(49, 274, 222, 23);
		contentPane.add(chckbxRegistrationuseractivity);
		
		chckbxRegistrationspeakeractivity = new JCheckBox("Registration (Speaker->Activity)");
		chckbxRegistrationspeakeractivity.setBounds(49, 291, 243, 23);
		contentPane.add(chckbxRegistrationspeakeractivity);
		
		chckbxRegistrationorganizeractivity = new JCheckBox("Registration (Organizer->Activity)");
		chckbxRegistrationorganizeractivity.setBounds(49, 309, 254, 23);
		contentPane.add(chckbxRegistrationorganizeractivity);
		
		chckbxReportfrequencyPerEvent = new JCheckBox("Report (Frequency per Event)");
		chckbxReportfrequencyPerEvent.setBounds(322, 42, 211, 23);
		contentPane.add(chckbxReportfrequencyPerEvent);
		
		chckbxReportfrequencyPerActivity = new JCheckBox("Report (Frequency per Activity)");
		chckbxReportfrequencyPerActivity.setBounds(322, 60, 231, 23);
		contentPane.add(chckbxReportfrequencyPerActivity);
		
		chckbxReportlistOfAuthors = new JCheckBox("Report (List of Authors)");
		chckbxReportlistOfAuthors.setBounds(322, 79, 181, 23);
		contentPane.add(chckbxReportlistOfAuthors);
		
		chckbxCheckingCopyatestado = new JCheckBox("Checking Copy (Atestado)");
		chckbxCheckingCopyatestado.setBounds(322, 98, 211, 23);
		contentPane.add(chckbxCheckingCopyatestado);
		
		chckbxCheckingCopycertificado = new JCheckBox("Checking Copy (Certificado)");
		chckbxCheckingCopycertificado.setBounds(322, 116, 211, 23);
		contentPane.add(chckbxCheckingCopycertificado);
		
		chckbxPaymentaVista = new JCheckBox("Payment (A vista)");
		chckbxPaymentaVista.setBounds(322, 134, 181, 23);
		contentPane.add(chckbxPaymentaVista);
		
		chckbxPaymentdeposit = new JCheckBox("Payment (Deposit)");
		chckbxPaymentdeposit.setBounds(322, 151, 168, 23);
		contentPane.add(chckbxPaymentdeposit);
		
		chckbxPaymentcredit = new JCheckBox("Payment (Credit)");
		chckbxPaymentcredit.setBounds(322, 169, 168, 23);
		contentPane.add(chckbxPaymentcredit);
		
		chckbxSubmissionpartial = new JCheckBox("Submission (Partial)");
		chckbxSubmissionpartial.setBounds(322, 186, 168, 23);
		contentPane.add(chckbxSubmissionpartial);
		
		chckbxSubmissioncomplete = new JCheckBox("Submission (Complete)");
		chckbxSubmissioncomplete.setBounds(322, 204, 181, 23);
		contentPane.add(chckbxSubmissioncomplete);
		
		chckbxAssignmentchairIndication = new JCheckBox("Assignment (Chair Indication)");
		chckbxAssignmentchairIndication.setBounds(322, 221, 231, 23);
		contentPane.add(chckbxAssignmentchairIndication);
		
//		chckbxAssignmentpreferenceIndication = new JCheckBox("Assignment(Preference Indication)");
//		chckbxAssignmentpreferenceIndication.setBounds(322, 239, 262, 23);
//		contentPane.add(chckbxAssignmentpreferenceIndication);
		
		chckbxAssignmentautomatic = new JCheckBox("Assignment (Automatic)");
		chckbxAssignmentautomatic.setBounds(322, 256, 211, 23);
		contentPane.add(chckbxAssignmentautomatic);
		
		chckbxConflictOfInterestAutomatic = new JCheckBox("Conflict of Interest (Automatic)");
		chckbxConflictOfInterestAutomatic.setBounds(322, 274, 231, 23);
		contentPane.add(chckbxConflictOfInterestAutomatic);
		
//		chckbxConflictOfInterestManual = new JCheckBox("Conflict of Interest ( Manual)");
//		chckbxConflictOfInterestManual.setBounds(322, 291, 221, 23);
//		contentPane.add(chckbxConflictOfInterestManual);
		
		chckbxNotificationdeadline = new JCheckBox("Notification (Deadline)");
		chckbxNotificationdeadline.setBounds(324, 309, 179, 23);
		contentPane.add(chckbxNotificationdeadline);
		
		chckbxNotificationacceptacerejection = new JCheckBox("Notification (Acceptace/Rejection)");
		chckbxNotificationacceptacerejection.setBounds(324, 326, 260, 23);
		contentPane.add(chckbxNotificationacceptacerejection);
		
		chckbxNotificationpaperAssignment = new JCheckBox("Notification (Paper Assignment)");
		chckbxNotificationpaperAssignment.setBounds(324, 344, 260, 23);
		contentPane.add(chckbxNotificationpaperAssignment);
		
		chckbxInsertAuthors = new JCheckBox("Insert Authors");
		chckbxInsertAuthors.setBounds(49, 361, 128, 23);
		contentPane.add(chckbxInsertAuthors);
		
		chckbxBugtrack = new JCheckBox("BugTrack");
		chckbxBugtrack.setBounds(49, 344, 128, 23);
		contentPane.add(chckbxBugtrack);
		
		chckbxReceipt = new JCheckBox("Receipt");
		chckbxReceipt.setBounds(49, 326, 128, 23);
		contentPane.add(chckbxReceipt);
		
		chckbxMainTrack = new JCheckBox("Main Track");
		chckbxMainTrack.setBounds(49, 221, 128, 23);
		contentPane.add(chckbxMainTrack);
		
		btnClean = new JButton("Clean");
		btnClean.setBounds(299, 379, 117, 29);
		contentPane.add(btnClean);
		
		btnBack.addActionListener(backAction);
		btnClean.addActionListener(cleanAction);
		btnDerivate.addActionListener(derivateAction);
		chckbxSpeaker.addActionListener(speakerCheckAction);
		chckbxOrganizer.addActionListener(organizerCheckAction);
		chckbxReviewer.addActionListener(reviewerCheckBoxAction);		 
		chckbxGenerateEventProgram.addActionListener(eventProgramCheckBoxAction);
		chckbxGenerateEventImportantDates.addActionListener(eventImportantDatesCheckBoxAction);
		chckbxWorkshopActivity.addActionListener(activityWorkshopCheckBoxAction);
		chckbxTutorialActivity.addActionListener(activityTutorialCheckBoxAction);
		chckbxPanelActivity.addActionListener(activityPainelCheckBoxAction);
		chckbxMinicurso.addActionListener(activityMinicursoCheckBoxAction);
		chckbxRoundOfReview.addActionListener(reviewRoundofReviewCheckBoxAction);
		chckbxSimpleReview.addActionListener(reviewSimpleReviewCheckBoxAction) ;
		chckbxRegistrationuseractivity.addActionListener(registrationUserActivityCheckBoxAction);
		chckbxRegistrationspeakeractivity.addActionListener(registrationSpeakerActivityCheckBoxAction);
		chckbxRegistrationorganizeractivity.addActionListener(registrationOrganizerActivityCheckBoxAction);
		chckbxReportfrequencyPerEvent.addActionListener(reportsFrequencyperEventCheckBoxAction);
		chckbxReportfrequencyPerActivity.addActionListener(reportsFrequencyperActivityCheckBoxAction);
		chckbxReportlistOfAuthors.addActionListener(reportsListofAuthorsCheckBoxAction);
		chckbxCheckingCopyatestado.addActionListener(checkingCopyAtestadoCheckBoxAction);
		chckbxCheckingCopycertificado.addActionListener(checkingCopyCertificadoCheckBoxAction);
		chckbxPaymentaVista.addActionListener(paymentAvistaCheckBoxAction);
		chckbxPaymentdeposit.addActionListener(paymentDepositoCheckBoxAction);
		chckbxPaymentcredit.addActionListener(paymentCartaoCheckBoxAction) ;
		chckbxSubmissionpartial.addActionListener(submissionParcialCheckBoxAction);
		chckbxSubmissioncomplete.addActionListener(submissionCompletaCheckBoxAction);
		chckbxAssignmentchairIndication.addActionListener(assignmentChairindicationCheckBoxAction);
//		chckbxAssignmentpreferenceIndication.addActionListener(assignmentPreferenceindicationCheckBoxAction);
		chckbxAssignmentautomatic.addActionListener(assignmentautomaticCheckBoxAction);
		chckbxConflictOfInterestAutomatic.addActionListener(conflictofinterestAutomaticCheckBoxAction);
//		chckbxConflictOfInterestManual.addActionListener(conflictofinterestManualCheckBoxAction);
		chckbxNotificationdeadline.addActionListener(notificationsDeadlineCheckBoxAction);
		chckbxNotificationacceptacerejection.addActionListener(notificationsAceptanceRejectionCheckBoxAction);
		chckbxNotificationpaperAssignment.addActionListener(notificationsPaperAssignemntCheckBoxAction);
		chckbxInsertAuthors.addActionListener(insertAuthorsCheckBoxAction);
		chckbxBugtrack.addActionListener(bugsCheckBoxAction) ;
		chckbxReceipt.addActionListener(receiptCheckBoxAction) ;
		chckbxMainTrack.addActionListener(activityMainTrackCheckBoxAction);
		
	//	carregarCheck();
		
	}
	
	private void carregarCheck(){
		if(Derivator.getInstanceDerivator().getStatus("Speaker").equals("T"))
			chckbxSpeaker.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("Organizer").equals("T"))
			chckbxOrganizer.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("Reviewer").equals("T"))
			chckbxReviewer.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("EventProgram").equals("T"))
			chckbxGenerateEventProgram.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("EventImportantDates").equals("T"))
			chckbxGenerateEventImportantDates.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("ActivityWorkshop").equals("T"))
			chckbxWorkshopActivity.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("ActivityTutorial").equals("T"))
			chckbxTutorialActivity.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("ActivityPainel").equals("T"))
			chckbxPanelActivity.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("ActivityMinicurso").equals("T"))
			chckbxMinicurso.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("ReviewRoundofReview").equals("T"))
			chckbxRoundOfReview.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("ReviewSimpleReview").equals("T"))
			chckbxSimpleReview.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("RegistrationUserActivity").equals("T"))
			chckbxRegistrationuseractivity.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("RegistrationSpeakerActivity").equals("T"))
			chckbxRegistrationspeakeractivity.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("RegistrationOrganizerActivity").equals("T"))
			chckbxRegistrationorganizeractivity.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("ReportsFrequencyperEvent").equals("T"))
			chckbxReportfrequencyPerEvent.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("ReportsFrequencyperActivity").equals("T"))
			chckbxReportfrequencyPerActivity.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("ReportsListofAuthors").equals("T"))
			chckbxReportlistOfAuthors.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("CheckingCopyAtestado").equals("T"))
			chckbxCheckingCopyatestado.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("CheckingCopyCertificado").equals("T"))
			chckbxCheckingCopycertificado.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("PaymentAvista").equals("T"))
			chckbxPaymentaVista.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("PaymentDeposito").equals("T"))
			chckbxPaymentdeposit.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("PaymentCartao").equals("T"))
			chckbxPaymentcredit.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("SubmissionParcial").equals("T"))
			chckbxSubmissionpartial.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("SubmissionCompleta").equals("T"))
			chckbxSubmissioncomplete.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("AssignmentChairindication").equals("T"))
			chckbxAssignmentchairIndication.setSelected(true);
//		if(Derivator.getInstanceDerivator().getStatus("AssignmentPreferenceindication").equals("T"))
//			chckbxAssignmentpreferenceIndication.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("Assignmentautomatic").equals("T"))
			chckbxAssignmentautomatic.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("ConflictofinterestAutomatic").equals("T"))
			chckbxConflictOfInterestAutomatic.setSelected(true);
//		if(Derivator.getInstanceDerivator().getStatus("ConflictofinterestManual").equals("T"))
//			chckbxConflictOfInterestManual.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("NotificationsDeadline").equals("T"))
			chckbxNotificationdeadline.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("NotificationsAceptanceRejection").equals("T"))
			chckbxNotificationacceptacerejection.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("NotificationsPaperAssignemnt").equals("T"))
			chckbxNotificationpaperAssignment.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("InsertAuthors").equals("T"))
			chckbxInsertAuthors.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("Bugs").equals("T"))
			chckbxBugtrack.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("Receipt").equals("T"))
			chckbxReceipt.setSelected(true);
		if(Derivator.getInstanceDerivator().getStatus("ActivityMainTrack").equals("T"))
			chckbxMainTrack.setSelected(true);
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		
		}
	}
	
	private class CleanButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i=1; i<34; i++){
				Derivator.getInstanceDerivator().getFeatureList().get(i).setStatus("F");
			}
			chckbxAssignmentautomatic.setSelected(false);
			chckbxAssignmentchairIndication.setSelected(false);
//			chckbxAssignmentpreferenceIndication.setSelected(false);
			chckbxBugtrack.setSelected(false);
			chckbxCheckingCopyatestado.setSelected(false);
			chckbxCheckingCopycertificado.setSelected(false);
			chckbxConflictOfInterestAutomatic.setSelected(false);
			chckbxGenerateEventImportantDates.setSelected(false);
//			chckbxConflictOfInterestManual.setSelected(false);
			chckbxGenerateEventProgram.setSelected(false);
			chckbxInsertAuthors.setSelected(false);
			chckbxMinicurso.setSelected(false);
			chckbxNotificationacceptacerejection.setSelected(false);
			chckbxNotificationdeadline.setSelected(false);
			chckbxNotificationpaperAssignment.setSelected(false);
			chckbxOrganizer.setSelected(false);
			chckbxPanelActivity.setSelected(false);
			chckbxPaymentaVista.setSelected(false);
			chckbxPaymentcredit.setSelected(false);
			chckbxPaymentdeposit.setSelected(false);
			chckbxReceipt.setSelected(false);
			chckbxRegistrationorganizeractivity.setSelected(false);
			chckbxRegistrationspeakeractivity.setSelected(false);
			chckbxRegistrationuseractivity.setSelected(false);
			chckbxReportfrequencyPerActivity.setSelected(false);
			chckbxReportfrequencyPerEvent.setSelected(false);
			chckbxReportlistOfAuthors.setSelected(false);
			chckbxReviewer.setSelected(false);
			chckbxRoundOfReview.setSelected(false);
			chckbxSimpleReview.setSelected(false);
			chckbxSpeaker.setSelected(false);
			chckbxSubmissioncomplete.setSelected(false);
			chckbxSubmissionpartial.setSelected(false);
			chckbxTutorialActivity.setSelected(false);
			chckbxWorkshopActivity.setSelected(false);
			chckbxMainTrack.setSelected(false);
		}
	}
	
	private class DerivateButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
		
		AntExecutor.derivate();
		}
	}
	
	private class SpeakerCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("Speaker");
			if(chckbxSpeaker.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(1).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(1).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class OrganizerCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("Organizer");
			if(chckbxOrganizer.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(2).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(2).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class ReviewerCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("Reviewer");
			if(chckbxReviewer.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(3).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(3).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class EventProgramCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("EventProgram");
			if(chckbxGenerateEventProgram.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(4).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(4).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class EventImportantDatesCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("EventImportantDates");
			if(chckbxGenerateEventImportantDates.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(5).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(5).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class ActivityWorkshopCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("ActivityWorkshop");
			if(chckbxWorkshopActivity.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(6).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(6).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class ActivityTutorialCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("ActivityTutorial");
			if(chckbxTutorialActivity.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(7).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(7).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}

	private class ActivityPainelCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("ActivityPainel");
			if(chckbxPanelActivity.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(8).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(8).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class ActivityMinicursoCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("ActivityMinicurso");
			if(chckbxMinicurso.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(9).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(9).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}

	private class ReviewRoundofReviewCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("ReviewRoundofREview");
			if(chckbxRoundOfReview.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(10).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(10).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class ReviewSimpleReviewCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("ReviewSimpleReview");
			if(chckbxSimpleReview.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(11).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(11).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class RegistrationUserActivityCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("RegistrationUserActivity");
			if(chckbxRegistrationuseractivity.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(12).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(12).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class RegistrationSpeakerActivityCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("RegistrationSpeakerActivity");
			if(chckbxRegistrationspeakeractivity.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(13).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(13).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class RegistrationOrganizerActivityCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("RegistrationOrganizerActivity");
			if(chckbxRegistrationorganizeractivity.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(14).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(14).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class ReportsFrequencyperEventCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("ReportsFrequencyperEvent");
			if(chckbxReportfrequencyPerEvent.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(15).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(15).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class ReportsFrequencyperActivityCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("ReportsFrequencyperActivity");
			if(chckbxReportfrequencyPerActivity.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(16).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(16).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class ReportsListofAuthorsCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("ReportsListofAuthors");
			if(chckbxReportlistOfAuthors.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(17).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(17).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class CheckingCopyAtestadoCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("CheckingCopyAtestado");
			if(chckbxCheckingCopyatestado.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(18).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(18).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class CheckingCopyCertificadoCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("CheckingCopyCertificado");
			if(chckbxCheckingCopycertificado.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(19).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(19).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class PaymentAvistaCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("PaymentAvista");
			if(chckbxPaymentaVista.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(20).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(20).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class PaymentDepositoCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("PaymentDeposito");
			if(chckbxPaymentdeposit.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(21).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(21).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class PaymentCartaoCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("PaymentCartao");
			if(chckbxPaymentcredit.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(22).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(22).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class SubmissionParcialCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("SubmissionParcial");
			if(chckbxSubmissionpartial.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(23).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(23).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class SubmissionCompletaCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("SubmissionCompleta");
			if(chckbxSubmissioncomplete.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(24).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(24).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class AssignmentChairindicationCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("AssignmentChairindication");
			if(chckbxAssignmentchairIndication.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(25).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(25).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
//	private class AssignmentPreferenceindicationCheckBoxAction  implements ActionListener{ 
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			Feature feature = new Feature();
//			String s;
//			feature.setName("AssignmentPreferenceindication");
//			if(chckbxA.isSelected() == true){
//				s = "T";  
//				Derivator.getInstanceDerivator().getFeatureList().get(26).setStatus(s);
//			}else{
//				s = "F";
//				Derivator.getInstanceDerivator().getFeatureList().get(26).setStatus(s);
//			}
//			checkDerivation(feature.getName(), s);
//		    Derivator.getInstanceDerivator().modifyXML();
//		}
//	}
	
	private class AssignmentautomaticCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("Assignmentautomatic");
			if(chckbxAssignmentautomatic.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(26).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(26).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class ConflictofinterestAutomaticCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("ConflictofinterestAutomatic");
			if(chckbxConflictOfInterestAutomatic.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(27).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(27).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
//	private class ConflictofinterestManualCheckBoxAction  implements ActionListener{ 
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			Feature feature = new Feature();
//			String s;
//			feature.setName("ConflictofinterestManual");
//			if(chckbxRegistrationuseractivity.isSelected() == true){
//				s = "T";  
//				Derivator.getInstanceDerivator().getFeatureList().get(29).setStatus(s);
//			}else{
//				s = "F";
//				Derivator.getInstanceDerivator().getFeatureList().get(29).setStatus(s);
//			}
//			checkDerivation(feature.getName(), s);
//		    Derivator.getInstanceDerivator().modifyXML();
//		}
//	}
	
	private class NotificationsDeadlineCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("NotificationsDeadline");
			if(chckbxNotificationdeadline.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(28).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(28).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class NotificationsAceptanceRejectionCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("NotificationsAceptanceRejection");
			if(chckbxNotificationacceptacerejection.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(29).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(29).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class NotificationsPaperAssignemntCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("NotificationsPaperAssignemnt");
			if(chckbxNotificationpaperAssignment.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(30).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(30).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class InsertAuthorsCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("InsertAuthors");
			if(chckbxInsertAuthors.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(31).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(31).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class BugsCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			feature.setName("Bugs");
			if(chckbxBugtrack.isSelected() == true)
				Derivator.getInstanceDerivator().getFeatureList().get(32).setStatus("T");
			else
				Derivator.getInstanceDerivator().getFeatureList().get(32).setStatus("F");
		
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	private class ReceiptCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			String s;
			feature.setName("Receipt");
			if(chckbxReceipt.isSelected() == true){
				s = "T";  
				Derivator.getInstanceDerivator().getFeatureList().get(33).setStatus(s);
			}else{
				s = "F";
				Derivator.getInstanceDerivator().getFeatureList().get(33).setStatus(s);
			}
			checkDerivation(feature.getName(), s);
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}

	
	private class ActivityMainTrackCheckBoxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Feature feature = new Feature();
			feature.setName("ActivityMainTrack");
			if(chckbxMainTrack.isSelected() == true)
				Derivator.getInstanceDerivator().getFeatureList().get(34).setStatus("T");
			else
				Derivator.getInstanceDerivator().getFeatureList().get(34).setStatus("F");
		
		    Derivator.getInstanceDerivator().modifyXML();
		}
	}
	
	// ********************* METODO PARA CHECAR A DERIVACAO ******************************
	
		
	
	public void checkDerivation(String featureName, String s){
		//verificacao feature 1
		if(featureName.equals("Speaker")){
			if(s.equals("F")){
				Derivator.getInstanceDerivator().getFeatureList().get(13).setStatus("F");
				chckbxRegistrationspeakeractivity.setSelected(false);
			}
		}
		//verificacao feature 2		
		if(featureName.equals("Organizer")){
			if(s.equals("F")){
				Derivator.getInstanceDerivator().getFeatureList().get(14).setStatus("F");
				chckbxRegistrationorganizeractivity.setSelected(false);
			}
		}
		
		//verificacao feature 3, 4 e 5 nao precisa feature sem dependencia
		
		//verificacao feature 6
		if(featureName.equals("ActivityWorkshop")){
			if(s.equals("F")){
				String tutorial = Derivator.getInstanceDerivator().getFeatureList().get(7).getStatus();
				String painel = Derivator.getInstanceDerivator().getFeatureList().get(8).getStatus();
				String minicurso = Derivator.getInstanceDerivator().getFeatureList().get(9).getStatus();
				if(tutorial.equals("F") && painel.equals("F") && minicurso.equals("F")){
					Derivator.getInstanceDerivator().getFeatureList().get(12).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(13).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(14).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(15).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(16).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(17).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(23).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(24).setStatus("F");
					chckbxRegistrationuseractivity.setSelected(false);
					chckbxRegistrationspeakeractivity.setSelected(false);
					chckbxRegistrationorganizeractivity.setSelected(false);
					chckbxReportfrequencyPerActivity.setSelected(false);
					chckbxReportfrequencyPerEvent.setSelected(false);
					chckbxReportlistOfAuthors.setSelected(false);
					chckbxSubmissioncomplete.setSelected(false);
					chckbxSubmissionpartial.setSelected(false);
				}
			}
		}
		
		//verificacao feature 7
		if(featureName.equals("ActivityTutorial")){
			if(s.equals("F")){
				String workshop = Derivator.getInstanceDerivator().getFeatureList().get(7).getStatus();
				String painel = Derivator.getInstanceDerivator().getFeatureList().get(8).getStatus();
				String minicurso = Derivator.getInstanceDerivator().getFeatureList().get(9).getStatus();
				if(workshop.equals("F") && painel.equals("F") && minicurso.equals("F")){
					Derivator.getInstanceDerivator().getFeatureList().get(12).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(13).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(15).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(16).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(17).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(23).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(24).setStatus("F");
					chckbxRegistrationuseractivity.setSelected(false);
					chckbxRegistrationspeakeractivity.setSelected(false);
					chckbxReportfrequencyPerActivity.setSelected(false);
					chckbxReportfrequencyPerEvent.setSelected(false);
					chckbxReportlistOfAuthors.setSelected(false);
					chckbxSubmissioncomplete.setSelected(false);
					chckbxSubmissionpartial.setSelected(false);
				}
			}
		}
		
		//verificacao feature 8
		if(featureName.equals("ActivityPainel")){
			if(s.equals("F")){
				String workshop = Derivator.getInstanceDerivator().getFeatureList().get(7).getStatus();
				String tutorial = Derivator.getInstanceDerivator().getFeatureList().get(8).getStatus();
				String minicurso = Derivator.getInstanceDerivator().getFeatureList().get(9).getStatus();
				if(workshop.equals("F") && tutorial.equals("F") && minicurso.equals("F")){
					Derivator.getInstanceDerivator().getFeatureList().get(12).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(13).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(15).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(16).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(17).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(23).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(24).setStatus("F");
					chckbxRegistrationuseractivity.setSelected(false);
					chckbxRegistrationspeakeractivity.setSelected(false);
					chckbxReportfrequencyPerActivity.setSelected(false);
					chckbxReportfrequencyPerEvent.setSelected(false);
					chckbxReportlistOfAuthors.setSelected(false);
					chckbxSubmissioncomplete.setSelected(false);
					chckbxSubmissionpartial.setSelected(false);
				}
			}
		}
		
		//verificacao feature 9
		if(featureName.equals("ActivityMinicurso")){
			if(s.equals("F")){
				String tutorial = Derivator.getInstanceDerivator().getFeatureList().get(7).getStatus();
				String painel = Derivator.getInstanceDerivator().getFeatureList().get(8).getStatus();
				String workshop = Derivator.getInstanceDerivator().getFeatureList().get(9).getStatus();
				if(tutorial.equals("F") && painel.equals("F") && workshop.equals("F")){
					Derivator.getInstanceDerivator().getFeatureList().get(12).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(13).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(14).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(15).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(16).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(17).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(23).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(24).setStatus("F");
					chckbxRegistrationuseractivity.setSelected(false);
					chckbxRegistrationspeakeractivity.setSelected(false);
					chckbxRegistrationorganizeractivity.setSelected(false);
					chckbxReportfrequencyPerActivity.setSelected(false);
					chckbxReportfrequencyPerEvent.setSelected(false);
					chckbxReportlistOfAuthors.setSelected(false);
					chckbxSubmissioncomplete.setSelected(false);
					chckbxSubmissionpartial.setSelected(false);
				}
			}
		}
		
		//verificacao feature 10		
		if(featureName.equals("ReviewRoundofREview")){
			if(s.equals("T")){
				Derivator.getInstanceDerivator().getFeatureList().get(24).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(11).setStatus("F");
				chckbxSubmissioncomplete.setSelected(true);
				chckbxSimpleReview.setSelected(false);
			}else{

				String reviewSimples = Derivator.getInstanceDerivator().getFeatureList().get(11).getStatus();
				if(reviewSimples.equals("T")){
					Derivator.getInstanceDerivator().getFeatureList().get(24).setStatus("T");
					chckbxSubmissioncomplete.setSelected(true);
				}else{
					Derivator.getInstanceDerivator().getFeatureList().get(29).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(30).setStatus("F");
					chckbxNotificationacceptacerejection.setSelected(false);
					chckbxNotificationpaperAssignment.setSelected(false);
				}
			}
		}
		
		//verificacao feature 11		
		if(featureName.equals("ReviewSimpleReview")){
			if(s.equals("T")){
				Derivator.getInstanceDerivator().getFeatureList().get(24).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(10).setStatus("F");
				chckbxSubmissioncomplete.setSelected(true);
				chckbxRoundOfReview.setSelected(false);
			}else{

				String roundReview = Derivator.getInstanceDerivator().getFeatureList().get(10).getStatus();
				if(roundReview.equals("T")){
					Derivator.getInstanceDerivator().getFeatureList().get(24).setStatus("T");
					chckbxSubmissioncomplete.setSelected(true);
				}else{
					Derivator.getInstanceDerivator().getFeatureList().get(29).setStatus("F");
					Derivator.getInstanceDerivator().getFeatureList().get(30).setStatus("F");
					chckbxNotificationacceptacerejection.setSelected(false);
					chckbxNotificationpaperAssignment.setSelected(false);
				}
			}
		}	
		
		//verificacao feature 12		
		if(featureName.equals("RegistrationUserActivity")){
			if(s.equals("T")){
				Derivator.getInstanceDerivator().getFeatureList().get(6).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(7).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(8).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(9).setStatus("T");
				chckbxWorkshopActivity.setSelected(true);
				chckbxTutorialActivity.setSelected(true);
				chckbxMinicurso.setSelected(true);
				chckbxPanelActivity.setSelected(true);
			}else{
				Derivator.getInstanceDerivator().getFeatureList().get(15).setStatus("F");
				Derivator.getInstanceDerivator().getFeatureList().get(16).setStatus("F");
				Derivator.getInstanceDerivator().getFeatureList().get(20).setStatus("F");
				Derivator.getInstanceDerivator().getFeatureList().get(21).setStatus("F");
				Derivator.getInstanceDerivator().getFeatureList().get(22).setStatus("F");
				Derivator.getInstanceDerivator().getFeatureList().get(23).setStatus("F");
				Derivator.getInstanceDerivator().getFeatureList().get(24).setStatus("F");
				chckbxReportfrequencyPerEvent.setSelected(false);
				chckbxReportfrequencyPerActivity.setSelected(false);
				chckbxPaymentaVista.setSelected(false);
				chckbxPaymentcredit.setSelected(false);
				chckbxPaymentdeposit.setSelected(false);
				chckbxSubmissioncomplete.setSelected(false);
				chckbxSubmissionpartial.setSelected(false);
			}
		}
		
		//verificacao feature 13
		if(featureName.equals("RegistrationSpeakerActivity")){
			if(s.equals("T")){
				Derivator.getInstanceDerivator().getFeatureList().get(6).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(7).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(8).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(9).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(1).setStatus("T");
				chckbxWorkshopActivity.setSelected(true);
				chckbxTutorialActivity.setSelected(true);
				chckbxMinicurso.setSelected(true);
				chckbxPanelActivity.setSelected(true);
				chckbxSpeaker.setSelected(true);
			}
		}
		
		//verificacao feature 14
		if(featureName.equals("RegistrationOrganizerActivity")){
			if(s.equals("T")){
				Derivator.getInstanceDerivator().getFeatureList().get(6).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(9).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(2).setStatus("T");
				chckbxWorkshopActivity.setSelected(true);
				chckbxMinicurso.setSelected(true);
				chckbxOrganizer.setSelected(true);
			}
		}
		
		//verificacao feature 15
		if(featureName.equals("ReportsFrequencyperEvent")){
			if(s.equals("T")){
				Derivator.getInstanceDerivator().getFeatureList().get(6).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(7).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(8).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(9).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(12).setStatus("T");
				chckbxWorkshopActivity.setSelected(true);
				chckbxTutorialActivity.setSelected(true);
				chckbxMinicurso.setSelected(true);
				chckbxPanelActivity.setSelected(true);
				chckbxRegistrationorganizeractivity.setSelected(true);
			}
		}
		
		//verificacao feature 16
		if(featureName.equals("ReportsFrequencyperActivity")){
			if(s.equals("T")){
				Derivator.getInstanceDerivator().getFeatureList().get(6).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(7).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(8).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(9).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(12).setStatus("T");
				chckbxWorkshopActivity.setSelected(true);
				chckbxTutorialActivity.setSelected(true);
				chckbxMinicurso.setSelected(true);
				chckbxPanelActivity.setSelected(true);
				chckbxRegistrationorganizeractivity.setSelected(true);
			}
		}
		
		//verificacao feature 17		
		if(featureName.equals("ReportsListofAuthors")){
			if(s.equals("T")){
				Derivator.getInstanceDerivator().getFeatureList().get(6).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(7).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(8).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(9).setStatus("T");
				chckbxWorkshopActivity.setSelected(true);
				chckbxTutorialActivity.setSelected(true);
				chckbxMinicurso.setSelected(true);
				chckbxPanelActivity.setSelected(true);
			}
		}
			
		//verificacao feature 18 e 19 nao precisa feature sem dependencia
		
		//verificacao feature 20
		if(featureName.equals("PaymentAvista")){
			if(s.equals("T")){
				Derivator.getInstanceDerivator().getFeatureList().get(12).setStatus("T");
				chckbxRegistrationuseractivity.setSelected(true);
			}else{
				
				//VERIFICAR SE OS OUTROS PAYMENTS ESTAO MARCADOS SE TODOS FOREM FALSOS RECEIPT TAMBEM SERA
				String deposito = Derivator.getInstanceDerivator().getFeatureList().get(21).getStatus();
				String cartao = Derivator.getInstanceDerivator().getFeatureList().get(22).getStatus();
				if(deposito.equals("F") && cartao.equals("F")){
					Derivator.getInstanceDerivator().getFeatureList().get(33).setStatus("F");
					chckbxReceipt.setSelected(false);
				}
			}
		}

		//verificacao feature 21
		if(featureName.equals("PaymentDeposito")){
			if(s.equals("T")){
				Derivator.getInstanceDerivator().getFeatureList().get(12).setStatus("T");
				chckbxRegistrationuseractivity.setSelected(true);
			}else{
				
				//VERIFICAR SE OS OUTROS PAYMENTS ESTAO MARCADOS SE TODOS FOREM FALSOS RECEIPT TAMBEM SERA
				String avista = Derivator.getInstanceDerivator().getFeatureList().get(20).getStatus();
				String cartao = Derivator.getInstanceDerivator().getFeatureList().get(22).getStatus();
				if(avista.equals("F") && cartao.equals("F")){
					Derivator.getInstanceDerivator().getFeatureList().get(33).setStatus("F");
					chckbxReceipt.setSelected(false);
				}
			}
		}
		
		//verificacao feature 22
		if(featureName.equals("PaymentCartao")){
			if(s.equals("T")){
				Derivator.getInstanceDerivator().getFeatureList().get(12).setStatus("T");
				chckbxRegistrationuseractivity.setSelected(true);
			}else{
				
				//VERIFICAR SE OS OUTROS PAYMENTS ESTAO MARCADOS SE TODOS FOREM FALSOS RECEIPT TAMBEM SERA
				String deposito = Derivator.getInstanceDerivator().getFeatureList().get(21).getStatus();
				String avista = Derivator.getInstanceDerivator().getFeatureList().get(20).getStatus();
				if(deposito.equals("F") && avista.equals("F")){
					Derivator.getInstanceDerivator().getFeatureList().get(33).setStatus("F");
					chckbxReceipt.setSelected(false);
				}
			}
		}
		
		//verificacao feature 23
		if(featureName.equals("SubmissionParcial")){
			if(s.equals("T")){
				Derivator.getInstanceDerivator().getFeatureList().get(6).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(7).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(8).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(9).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(12).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(24).setStatus("T");
				chckbxWorkshopActivity.setSelected(true);
				chckbxTutorialActivity.setSelected(true);
				chckbxMinicurso.setSelected(true);
				chckbxPanelActivity.setSelected(true);
				chckbxRegistrationuseractivity.setSelected(true);
				chckbxSubmissioncomplete.setSelected(true);
			}else{
				String submissionCompleta = Derivator.getInstanceDerivator().getFeatureList().get(24).getStatus();
				if(submissionCompleta.equals("F")){
					Derivator.getInstanceDerivator().getFeatureList().get(31).setStatus("F");
					chckbxInsertAuthors.setSelected(false);
				}
			}
		}
		
		
		//verificacao feature 24
		if(featureName.equals("SubmissionCompleta")){
			if(s.equals("T")){
				Derivator.getInstanceDerivator().getFeatureList().get(6).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(7).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(8).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(9).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(12).setStatus("T");
				chckbxWorkshopActivity.setSelected(true);
				chckbxTutorialActivity.setSelected(true);
				chckbxMinicurso.setSelected(true);
				chckbxPanelActivity.setSelected(true);
				chckbxRegistrationuseractivity.setSelected(true);
			}else{
				Derivator.getInstanceDerivator().getFeatureList().get(10).setStatus("F");
				Derivator.getInstanceDerivator().getFeatureList().get(11).setStatus("F");
				Derivator.getInstanceDerivator().getFeatureList().get(23).setStatus("F");
				Derivator.getInstanceDerivator().getFeatureList().get(25).setStatus("F");
		//		Derivator.getInstanceDerivator().getFeatureList().get(26).setStatus("F");
				Derivator.getInstanceDerivator().getFeatureList().get(26).setStatus("F");
				Derivator.getInstanceDerivator().getFeatureList().get(31).setStatus("F");
				chckbxSimpleReview.setSelected(false);
				chckbxRoundOfReview.setSelected(false);
				chckbxSubmissionpartial.setSelected(false);
				chckbxAssignmentautomatic.setSelected(false);
				chckbxAssignmentchairIndication.setSelected(false);
//				chckbxAssignmentpreferenceIndication.setSelected(false);
				chckbxInsertAuthors.setSelected(false);
			}
		}
		
		//verificacao feature 25
		if(featureName.equals("AssignmentChairindication")){
			if(s.equals("T")){
				Derivator.getInstanceDerivator().getFeatureList().get(24).setStatus("T");
				chckbxSubmissioncomplete.setSelected(true);
			}
		}
		
		//verificacao feature 26
//		if(featureName.equals("AssignmentPreferenceindication")){
//			if(s.equals("T")){
//				Derivator.getInstanceDerivator().getFeatureList().get(24).setStatus("T");
//				Derivator.getInstanceDerivator().getFeatureList().get(27).setStatus("F");
//				chckbxSubmissioncomplete.setSelected(true);
//				chckbxAssignmentautomatic.setSelected(false);
//			}else{
//				Derivator.getInstanceDerivator().getFeatureList().get(28).setStatus("F");
//				Derivator.getInstanceDerivator().getFeatureList().get(29).setStatus("F");
//				Derivator.getInstanceDerivator().getFeatureList().get(30).setStatus("F");
//				Derivator.getInstanceDerivator().getFeatureList().get(32).setStatus("F");
//				chckbxConflictOfInterestAutomatic.setSelected(false);
////				chckbxConflictOfInterestManual.setSelected(false);
//				chckbxNotificationdeadline.setSelected(false);
//				chckbxNotificationpaperAssignment.setSelected(false);
//			}
//		}
		
		//verificacao feature 26
		if(featureName.equals("Assignmentautomatic")){
			if(s.equals("T")){
				Derivator.getInstanceDerivator().getFeatureList().get(24).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(26).setStatus("F");
				chckbxSubmissioncomplete.setSelected(true);
				chckbxAssignmentautomatic.setSelected(false);
			}else{
				Derivator.getInstanceDerivator().getFeatureList().get(27).setStatus("F");
//				Derivator.getInstanceDerivator().getFeatureList().get(29).setStatus("F");
				Derivator.getInstanceDerivator().getFeatureList().get(28).setStatus("F");
				Derivator.getInstanceDerivator().getFeatureList().get(30).setStatus("F");
				chckbxConflictOfInterestAutomatic.setSelected(false);
//				chckbxConflictOfInterestManual.setSelected(false);
				chckbxNotificationdeadline.setSelected(false);
				chckbxNotificationpaperAssignment.setSelected(false);
			}
		}
		
		//verificacao feature 27
		if(featureName.equals("ConflictofinterestAutomatic")){
			if(s.equals("T")){
			//	Derivator.getInstanceDerivator().getFeatureList().get(26).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(26).setStatus("T");
		//		Derivator.getInstanceDerivator().getFeatureList().get(29).setStatus("F");
//				chckbxAssignmentpreferenceIndication.setSelected(true);
				chckbxAssignmentautomatic.setSelected(true);
//				chckbxConflictOfInterestManual.setSelected(false);
			}
		}
		
		//verificacao feature 29
//		if(featureName.equals("ConflictofinterestManual")){
//			if(s.equals("T")){
//				Derivator.getInstanceDerivator().getFeatureList().get(26).setStatus("T");
//				Derivator.getInstanceDerivator().getFeatureList().get(27).setStatus("T");
//				Derivator.getInstanceDerivator().getFeatureList().get(28).setStatus("F");
////				chckbxAssignmentpreferenceIndication.setSelected(true);
//				chckbxAssignmentautomatic.setSelected(true);
//				chckbxConflictOfInterestAutomatic.setSelected(false);
//			}
//		}
		
		//verificacao feature 28
		if(featureName.equals("NotificationsDeadline")){
			if(s.equals("T")){
//				Derivator.getInstanceDerivator().getFeatureList().get(26).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(26).setStatus("T");
//				chckbxAssignmentpreferenceIndication.setSelected(true);
				chckbxAssignmentautomatic.setSelected(true);
			}
		}
		
		//verificacao feature 29
		if(featureName.equals("NotificationsAceptanceRejection")){
			if(s.equals("T")){
				String roundReview = Derivator.getInstanceDerivator().getFeatureList().get(10).getStatus();
				String simpleReview = Derivator.getInstanceDerivator().getFeatureList().get(11).getStatus();
				if(roundReview.equals("F") && simpleReview.equals("F") ){
					Derivator.getInstanceDerivator().getFeatureList().get(11).setStatus("T");
					chckbxSimpleReview.setSelected(true);
				}else{
					if(roundReview.equals("T")){
						Derivator.getInstanceDerivator().getFeatureList().get(11).setStatus("F");
						chckbxSimpleReview.setSelected(false);
					}
					if(simpleReview.equals("T")){
						Derivator.getInstanceDerivator().getFeatureList().get(11).setStatus("F");
						chckbxSimpleReview.setSelected(false);
					}
				}
			}
		}
		
		//verificacao feature 30
		if(featureName.equals("NotificationsPaperAssignemnt")){
			if(s.equals("T")){
				String roundReview = Derivator.getInstanceDerivator().getFeatureList().get(10).getStatus();
				String simpleReview = Derivator.getInstanceDerivator().getFeatureList().get(11).getStatus();
//				String assignmentPreference = Derivator.getInstanceDerivator().getFeatureList().get(26).getStatus();
				String assignmentAutomatic = Derivator.getInstanceDerivator().getFeatureList().get(26).getStatus();
				if(roundReview.equals("F") && simpleReview.equals("F") ){
					Derivator.getInstanceDerivator().getFeatureList().get(11).setStatus("T");
					chckbxSimpleReview.setSelected(true);
				}
				if(/*assignmentPreference.equals("F") &&*/ assignmentAutomatic.equals("F") ){
					Derivator.getInstanceDerivator().getFeatureList().get(26).setStatus("T");
					chckbxAssignmentautomatic.setSelected(true);
				}
			}
		}
		
		//verificacao feature 31		
		if(featureName.equals("InsertAuthors")){
			if(s.equals("T")){
				Derivator.getInstanceDerivator().getFeatureList().get(23).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(24).setStatus("T");
				chckbxSubmissionpartial.setSelected(true);
				chckbxSubmissioncomplete.setSelected(true);
			}
		}
		
		//verificacao feature 32 nao precisa feature sem dependencia
		//verificacao feature 33		
		if(featureName.equals("Receipt")){
			if(s.equals("T")){
				Derivator.getInstanceDerivator().getFeatureList().get(20).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(21).setStatus("T");
				Derivator.getInstanceDerivator().getFeatureList().get(22).setStatus("T");
				chckbxPaymentaVista.setSelected(true);
				chckbxPaymentcredit.setSelected(false);
				chckbxPaymentdeposit.setSelected(false);
			}
		}
		
		
		//verificacao feature 34 nao precisa feature sem dependencia		
	
	}
}
