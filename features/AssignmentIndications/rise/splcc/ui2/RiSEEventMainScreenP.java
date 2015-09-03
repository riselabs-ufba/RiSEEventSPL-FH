package rise.splcc.ui2;

public class RiSEEventMainScreenP extends JFrame {

	// #if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
	private AssignmentInsertScreenP screenInsertAssignment;
	private AssignmentRemoveScreenP screenRemoveAssignment;
	private AssignmentListAllScreenP screenListAllAssignment;
	private AssignmentManagementScreenP screenManagementAssignment;
	private AssignmentSearchScreenP screenSearchAssignment;

	// #endif

	public void init() {

		original();

		// #if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} ==
		// "T"
		InsertAssignmentMenuAction insertAssignmentAction = new InsertAssignmentMenuAction();
		RemoveAssignmentMenuAction removeAssignmentAction = new RemoveAssignmentMenuAction();
		ListAllAssignmentMenuAction listAllAssignmentAction = new ListAllAssignmentMenuAction();
		ManagementAssignmentMenuAction managementAssignmentAction = new ManagementAssignmentMenuAction();
		SearchAssignmentMenuAction searchAssignmentAction = new SearchAssignmentMenuAction();
		// #endif

		// #if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} ==
		// "T"
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

		JMenuItem mntmAssignmentManagement = new JMenuItem(
				"Assignment Management");
		mnAssignment.add(mntmAssignmentManagement);
		// #endif

		// #if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} ==
		// "T"
		mntmInsert_9.addActionListener(insertAssignmentAction);
		mntmSearch_9.addActionListener(searchAssignmentAction);
		mntmRemove_9.addActionListener(removeAssignmentAction);
		mntmListall_9.addActionListener(listAllAssignmentAction);
		mntmAssignmentManagement.addActionListener(managementAssignmentAction);
		// #endif
	}

	// Assignemnt
	// #if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
	private class InsertAssignmentMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			screenInsertAssignment = AssignmentInsertScreenP
					.getInstanceAssignmentInsertScreenP();
			// desktopPane.add(screenInsertAssignment);
			if (screenInsertAssignment.getParent() == null) {
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

	private class SearchAssignmentMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			screenSearchAssignment = AssignmentSearchScreenP
					.getInstanceAssignmentSearchScreenP();
			// desktopPane.add(screenSearchAssignment);
			if (screenSearchAssignment.getParent() == null) {
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

	private class RemoveAssignmentMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			screenRemoveAssignment = AssignmentRemoveScreenP
					.getInstanceAssignmentRemoveScreenP();
			// desktopPane.add(screenRemoveAssignment);
			if (screenRemoveAssignment.getParent() == null) {
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

	private class ListAllAssignmentMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			screenListAllAssignment = AssignmentListAllScreenP
					.getInstanceAssignmentListAllScreenP();
			// desktopPane.add(screenListAllAssignment);
			if (screenListAllAssignment.getParent() == null) {
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

	private class ManagementAssignmentMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			screenManagementAssignment = AssignmentManagementScreenP
					.getInstanceAssignmentManagementScreenP();
			// desktopPane.add(screenManagementAssignment);
			if (screenManagementAssignment.getParent() == null) {
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
	// #endif

}
