package rise.splcc.ui2;

//#if ${InsertAuthors} == "T"
import javax.swing.JOptionPane;

import rise.splcc.data.Author;
//#endif
//#if ${InsertAuthors} == "T"
import rise.splcc.data.SubmissionAuthor;
//#endif
//#if ${InsertAuthors} == "T"
import rise.splcc.exception.AuthorAlreadyInsertedException;
import rise.splcc.exception.RepositoryException;
//#endif
//#if ${InsertAuthors} == "T"
import rise.splcc.exception.SubmissionAuthorAlreadyInsertedException;

//#endif

public class SubmissionCompleteInsertScreenP extends JInternalFrame {

	// #if ${InsertAuthors} == "T"
	private JLabel lblAuthorId;
	private JLabel lblIdAuthor;

	// #endif

	private void init() {
		original();

		// #if ${InsertAuthors} == "T"
		InsertNewAuthorButtonAction insertNewAuthorAction = new InsertNewAuthorButtonAction();
		// #endif
		// #if ${InsertAuthors} == "T"
		JLabel lblAuthor = new JLabel("Author Name:");
		lblAuthor.setBounds(16, 319, 87, 16);
		contentPane.add(lblAuthor);

		JLabel lblFiliation = new JLabel("Filiation:");
		lblFiliation.setBounds(21, 353, 61, 16);
		contentPane.add(lblFiliation);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(16, 385, 61, 16);
		contentPane.add(lblEmail);

		textFieldauthorName = new JTextField();
		textFieldauthorName.setBounds(112, 313, 352, 27);
		contentPane.add(textFieldauthorName);
		textFieldauthorName.setColumns(10);

		textFieldFiliation = new JTextField();
		textFieldFiliation.setBounds(78, 347, 386, 27);
		contentPane.add(textFieldFiliation);
		textFieldFiliation.setColumns(10);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(78, 381, 268, 27);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		JButton btnInsertNewAuthor = new JButton("Insert New Author");
		btnInsertNewAuthor.setBounds(181, 432, 141, 29);
		contentPane.add(btnInsertNewAuthor);

		lblAuthorId = new JLabel("Author Id:");
		lblAuthorId.setBounds(476, 319, 76, 16);
		contentPane.add(lblAuthorId);

		lblIdAuthor = new JLabel("");
		lblIdAuthor.setBounds(567, 319, 61, 16);
		contentPane.add(lblIdAuthor);
		// #endif
		// #if ${InsertAuthors} == "T"
		btnInsertNewAuthor.addActionListener(insertNewAuthorAction);
		// #endif
		// #if ${InsertAuthors} == "T"
		loadLastAuthorIndex();
		// #endif
	}

	// #if ${InsertAuthors} == "T"
	private void loadLastAuthorIndex() {
		try {
			lblIdAuthor.setText(String.valueOf(RiSEEventMainScreenP.facade
					.getAuthorLastId()));
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.toString(),
					"Erro", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}

	// #endif

	// #if ${InsertAuthors} == "T"
	private class InsertNewAuthorButtonAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// clearAuthorFields();
			loadLastAuthorIndex();

			Author author = new Author();

			String nameAuthor = textFieldauthorName.getText();
			String filiation = textFieldFiliation.getText();
			String email = textFieldEmail.getText();

			if (nameAuthor.equals(" ") || filiation.equals(" ")
					|| email.equals(" ")) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Não pode haver campo vazio.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else {
				try {
					author.setName(nameAuthor);
					author.setFiliation(filiation);
					author.setEmail(email);
					// Insere na tabela de authors
					RiSEEventMainScreenP.facade.insertAuthor(author);

					JOptionPane.showMessageDialog(getContentPane(),
							"Author inserido com sucesso", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (AuthorAlreadyInsertedException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				} catch (RepositoryException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
	}

	// #endif

	private void doInsertAuthorTask() {
		original();
		try {
			// #if ${InsertAuthors} == "T"
			// Inserir na tabela de submissionAUTHORS
			SubmissionAuthor submissionAuthor = new SubmissionAuthor();
			// retirada tela login
			// int idCorrespondingAuthor =
			// RiSEEventLoginScreen.usuarioLogado.getIdUser();
			// retirada tela login
			int idCorrespondingAuthor = Integer.valueOf(lblIdUserLogado
					.getText());
			submissionAuthor.setIdAuthor(idCorrespondingAuthor);
			submissionAuthor.setIdSubmission(Integer
					.valueOf(lblLastSubmissionId.getText()));
			submissionAuthor.setIdActivity(RiSEEventMainScreenP.facade
					.getActivityIdByName(comboBoxActivityName.getSelectedItem()
							.toString()));
			RiSEEventMainScreenP.facade
					.insertSubmissionAuthor(submissionAuthor);
			// #endif
		} catch (RepositoryException e1) {
			JOptionPane.showMessageDialog(getContentPane(),
					e1.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e1.printStackTrace();
		}
		// #if ${InsertAuthors} == "T"
		catch (SubmissionAuthorAlreadyInsertedException e1) {
			JOptionPane.showMessageDialog(getContentPane(), e1.toString(),
					"Erro", JOptionPane.INFORMATION_MESSAGE);
			e1.printStackTrace();
		}
		// #endif
	}

}
