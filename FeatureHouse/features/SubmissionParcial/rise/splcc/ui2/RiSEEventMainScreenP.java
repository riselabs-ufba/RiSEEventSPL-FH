package rise.splcc.ui2;


public class RiSEEventMainScreenP extends JFrame {

	// #if ${SubmissionParcial} == "T"
	private SubmissionPartialInsertScreenP screenInsertSubmissionPartial;

	// #endif

	public void init() {

		original();
		// #if ${SubmissionParcial} == "T"
		InsertSubmissionPartialMenuAction insertSubmissionPartialAction = new InsertSubmissionPartialMenuAction();
		// #endif

		// #if ${SubmissionParcial} == "T"
		JMenuItem mntmInsertPartial = new JMenuItem("Insert Partial");
		mnSubmission.add(mntmInsertPartial);
		// #endif

		// #if ${SubmissionParcial} == "T"
		mntmInsertPartial.addActionListener(insertSubmissionPartialAction);
		// #endif
	}

	// SUBMISSION
	// #if ${SubmissionParcial} == "T"
	private class InsertSubmissionPartialMenuAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			screenInsertSubmissionPartial = SubmissionPartialInsertScreenP
					.getInstanceSubmissionPartialInsertScreenP();
			// desktopPane.add(screenInsertSubmission);
			if (screenInsertSubmissionPartial.getParent() == null) {
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
	// #endif

}
