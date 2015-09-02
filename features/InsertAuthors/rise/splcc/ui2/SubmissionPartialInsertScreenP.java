//#if ${SubmissionParcial} == "T" 
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JInternalFrame;

import rise.splcc.data.Activity;
//#if ${InsertAuthors} == "T"
import rise.splcc.data.Author;
//#endif
import rise.splcc.data.Submission;
//#if ${InsertAuthors} == "T"
import rise.splcc.data.SubmissionAuthor;
//#endif
import rise.splcc.data.SubmissionUser;
import rise.splcc.data.User;
import rise.splcc.data.Submission.TypeSubmission;
//#if ${InsertAuthors} == "T"
import rise.splcc.exception.AuthorAlreadyInsertedException;
//#endif
import rise.splcc.exception.RepositoryException;

import rise.splcc.exception.SubmissionAlreadyInsertedException;
//#if ${InsertAuthors} == "T"
import rise.splcc.exception.SubmissionAuthorAlreadyInsertedException;

//#endif
import rise.splcc.exception.SubmissionUserAlreadyInsertedException;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SubmissionPartialInsertScreenP extends JInternalFrame {

	private void init() {
		original();

		JButton btnInsertNewAuthor = new JButton("Insert New Author");
		btnInsertNewAuthor.setBounds(208, 470, 141, 29);
		getContentPane().add(btnInsertNewAuthor);

		// #if ${InsertAuthors} == "T"
		InsertNewAuthorButtonAction insertNewAuthorAction = new InsertNewAuthorButtonAction();
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
			// #if ${InsertAuthors} == "T"
		} catch (SubmissionAuthorAlreadyInsertedException e1) {
			JOptionPane.showMessageDialog(getContentPane(), e1.toString(),
					"Erro", JOptionPane.INFORMATION_MESSAGE);
			e1.printStackTrace();
		// #endif
		}catch (RepositoryException e1) {
		JOptionPane.showMessageDialog(getContentPane(),
				e1.toString(), "Erro",
				JOptionPane.INFORMATION_MESSAGE);
		e1.printStackTrace();
	}
}
}
// #endif
