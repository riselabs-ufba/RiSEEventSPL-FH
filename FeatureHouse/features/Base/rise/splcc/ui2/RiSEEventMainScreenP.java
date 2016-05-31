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

	public static RiSEventFacade facade; // caso a variabilidade de tela login
											// seja retirada o sistema deve
											// iniciar dessa tela

	private static RiSEEventMainScreenP instanceRiSEEventMainScreenP;

	private UserInsertScreenP screenInsertUser;
	private UserRemoveScreenP screenRemoveUser;
	private UserUpdateScreenP screenUpdateUser;
	private UserSearchScreenP screenSearchUser;
	private UserListAllScreenP screenListAllUser;

	// private UserManagementScreenP screenManagementUser;

	private EventInsertScreenP screenInsertEvent;

	private EventRemoveScreenP screenRemoveEvent;
	private EventUpdateScreenP screenUpdateEvent;
	private EventSearchScreenP screenSearchEvent;
	private EventListAllScreenP screenListAllEvent;
	private EventManagementScreenP screenManagementEvent;

	private RegistrationInsertScreenP screenInsertRegistration;
	private RegistrationSearchScreenP screenSearchRegistration;
	private RegistrationListAllScreenP screenListAllRegistration;
	private RegistrationManagementScreenP screenManagementRegistration;
	private RegistrationUpdateScreenP screenUpdateRegistration;
	private RegistrationRemoveScreenP screenRemoveRegistration;

	private static JLabel labelImagem;

	private JDesktopPane desktopPane;

	public static RiSEventFacade getFacade() {
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

	private JMenuBar menuBar = new JMenuBar(); // moved out from constructor due
												// compiler issue
	private JMenu mnArchieve = new JMenu("Archieve"); // moved out due
														// references issue
	private JMenu mnRegistration = new JMenu("Registration"); // moved out due
																// references
																// issue
	private JMenu mnReports = new JMenu("Reports"); // moved out due references
													// issue
	private JMenu mnPayment = new JMenu("Payment"); // moved out due references
													// issue
	private JMenu menu = new JMenu("CheckingCopy"); // moved out due references
													// issue
	private JMenu mnEvent = new JMenu("Event"); // moved out due references
												// issue
	private JMenu mnSubmission = new JMenu("Submission"); // moved out due
															// references issue

	/**
	 * Create the frame.
	 */
	public RiSEEventMainScreenP() {
		init();
	}

	public void init() {

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		ExitMenuAction exitMenuAction = new ExitMenuAction();

		InsertUserMenuAction insertUserAction = new InsertUserMenuAction();
		RemoveUserMenuAction removeUserAction = new RemoveUserMenuAction();
		UpdateUserMenuAction updateUserAction = new UpdateUserMenuAction();
		SearchUserMenuAction searchUserAction = new SearchUserMenuAction();
		ListAllUserMenuAction listAllUserAction = new ListAllUserMenuAction();

		// ManagementUserMenuAction managementUserAction = new
		// ManagementUserMenuAction();

		InsertEventMenuAction insertEventAction = new InsertEventMenuAction();

		RemoveEventMenuAction removeEventAction = new RemoveEventMenuAction();
		UpdateEventMenuAction updateEventAction = new UpdateEventMenuAction();
		SearchEventMenuAction searchEventAction = new SearchEventMenuAction();
		ListAllEventMenuAction listAllEventAction = new ListAllEventMenuAction();
		ManagementEventMenuAction managementEventAction = new ManagementEventMenuAction();

		InsertRegistrationMenuAction insertRegistrationAction = new InsertRegistrationMenuAction();
		RemoveRegistrationMenuAction removeRegistrationAction = new RemoveRegistrationMenuAction();
		UpdateRegistrationMenuAction updateRegistrationAction = new UpdateRegistrationMenuAction();
		SearchRegistrationMenuAction searchRegistrationAction = new SearchRegistrationMenuAction();
		ListAllRegistrationMenuAction listAllRegistrationAction = new ListAllRegistrationMenuAction();
		ManagementRegistrationMenuAction managementRegistrationAction = new ManagementRegistrationMenuAction();

		//
		// ADICIONADO
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
		ImageIcon image = new ImageIcon(getClass().getResource(
				"/images/riseLabs.png"));
		Image imag = image.getImage().getScaledInstance(labelImagem.getWidth(),
				labelImagem.getHeight(), Image.SCALE_SMOOTH);
		labelImagem.setIcon(new ImageIcon(imag));
		desktopPane.add(labelImagem);

		menuBar.setBounds(32, 12, 1078, 22);
		contentPane.add(menuBar);

		menuBar.add(mnArchieve);

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

		// JMenuItem mntmUserManagement = new JMenuItem("User Management");
		// mnUser.add(mntmUserManagement);
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

		JMenuItem mntmRegistrationManagement = new JMenuItem(
				"Registration Management");
		mnRegistration.add(mntmRegistrationManagement);

		// #if ${ReportsListofAuthors} == "T" or ${ReportsFrequencyperActivity}
		// == "T" or ${ReportsFrequencyperEvent} == "T" or
		// ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
		menuBar.add(mnReports);
		// #endif

		mntmExit.addActionListener(exitMenuAction);

		mntmInsert.addActionListener(insertUserAction);

		mntmRemove.addActionListener(removeUserAction);

		mntmUpdate.addActionListener(updateUserAction);

		mntmSearch.addActionListener(searchUserAction);

		mntmListall.addActionListener(listAllUserAction);

		// mntmUserManagement.addActionListener(managementUserAction);

		mntmInsert_4.addActionListener(insertEventAction);

		mntmRemove_4.addActionListener(removeEventAction);
		mntmUpdate_4.addActionListener(updateEventAction);
		mntmSearch_4.addActionListener(searchEventAction);
		mntmListall_4.addActionListener(listAllEventAction);
		mntmEventManagement.addActionListener(managementEventAction);

		mntmInsert_10.addActionListener(insertRegistrationAction);
		mntmRemove_10.addActionListener(removeRegistrationAction);
		mntmUpdate_10.addActionListener(updateRegistrationAction);
		mntmSearch_10.addActionListener(searchRegistrationAction);
		mntmListall_10.addActionListener(listAllRegistrationAction);
		mntmRegistrationManagement
				.addActionListener(managementRegistrationAction);
	}

	private class ExitMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	private class InsertUserMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			screenInsertUser = UserInsertScreenP.getInstanceUserInsertScreenP();
			if (screenInsertUser.getParent() == null) {
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

	private class RemoveUserMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			screenRemoveUser = UserRemoveScreenP.getInstanceUserRemoveScreenP();
			if (screenRemoveUser.getParent() == null) {
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

	private class UpdateUserMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			screenUpdateUser = UserUpdateScreenP.getInstanceUserUpdateScreenP();
			// desktopPane.add(screenUpdateUser);
			if (screenUpdateUser.getParent() == null) {
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

	private class SearchUserMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			screenSearchUser = UserSearchScreenP.getInstanceUserSearchScreenP();
			// desktopPane.add(screenSearchUser);
			if (screenSearchUser.getParent() == null) {
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

	private class ListAllUserMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			screenListAllUser = UserListAllScreenP
					.getInstanceUserListAllScreenP();
			// desktopPane.add(screenListAllUser);
			if (screenListAllUser.getParent() == null
					&& screenListAllUser.getParent() != desktopPane) {
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

	// private class ManagementUserMenuAction implements ActionListener{
	//
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// screenManagementUser =
	// UserManagementScreenP.getInstanceUserManagementScreenP();
	// if(screenManagementUser.getParent() == null &&
	// screenManagementUser.getParent() != desktopPane){
	// desktopPane.add(screenManagementUser);
	// }
	// screenManagementUser.setVisible(true);
	// desktopPane.moveToFront(screenManagementUser);
	// try {
	// screenManagementUser.setSelected(true);
	// } catch (PropertyVetoException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	// }
	// }


	// EVENT
	private class InsertEventMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			screenInsertEvent = EventInsertScreenP
					.getInstanceEventInsertScreenP();
			// desktopPane.add(screenInsertEvent);
			if (screenInsertEvent.getParent() == null) {
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

	private class RemoveEventMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			screenRemoveEvent = EventRemoveScreenP
					.getInstanceEventRemoveScreenP();
			// desktopPane.add(screenRemoveEvent);
			if (screenRemoveEvent.getParent() == null) {
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

	private class UpdateEventMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			screenUpdateEvent = EventUpdateScreenP
					.getInstanceEventUpdateScreenP();
			// desktopPane.add(screenUpdateEvent);
			if (screenUpdateEvent.getParent() == null) {
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

	private class SearchEventMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			screenSearchEvent = EventSearchScreenP
					.getInstanceEventSearchScreenP();
			// desktopPane.add(screenSearchEvent);
			if (screenSearchEvent.getParent() == null) {
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

	private class ListAllEventMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			screenListAllEvent = EventListAllScreenP
					.getInstanceEventListAllScreenP();
			// desktopPane.add(screenListAllEvent);
			if (screenListAllEvent.getParent() == null) {
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

	private class ManagementEventMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			screenManagementEvent = EventManagementScreenP
					.getInstanceEventManagementScreenP();
			// desktopPane.add(screenManagementEvent);
			if (screenManagementEvent.getParent() == null) {
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

	// REGISTRATION
	private class InsertRegistrationMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			screenInsertRegistration = RegistrationInsertScreenP
					.getInstanceRegistrationInsertScreenP();
			// desktopPane.add(screenInsertRegistration);
			if (screenInsertRegistration.getParent() == null) {
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

	private class RemoveRegistrationMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			screenRemoveRegistration = RegistrationRemoveScreenP
					.getInstanceRegistrationRemoveScreenP();
			// desktopPane.add(screenRemoveRegistration);
			if (screenRemoveRegistration.getParent() == null) {
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

	private class UpdateRegistrationMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			screenUpdateRegistration = RegistrationUpdateScreenP
					.getInstanceRegistrationUpdateScreenP();
			// desktopPane.add(screenUpdateRegistration);
			if (screenUpdateRegistration.getParent() == null) {
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

	private class SearchRegistrationMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			screenSearchRegistration = RegistrationSearchScreenP
					.getInstanceRegistrationSearchScreenP();
			// desktopPane.add(screenSearchRegistration);
			if (screenSearchRegistration.getParent() == null) {
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

	private class ListAllRegistrationMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			screenListAllRegistration = RegistrationListAllScreenP
					.getInstanceRegistrationListAllScreenP();
			// desktopPane.add(screenListAllRegistration);
			if (screenListAllRegistration.getParent() == null) {
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

	private class ManagementRegistrationMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			screenManagementRegistration = RegistrationManagementScreenP
					.getInstanceRegistrationManagementScreenP();
			// desktopPane.add(screenManagementRegistration);
			if (screenManagementRegistration.getParent() == null) {
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

}
