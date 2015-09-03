package rise.splcc.ui2;

public class AssignmentManagementScreenP extends JInternalFrame {

	//#if ${Assignmentautomatic} == "T"
	private JButton btnGenerate;
	//#endif

	private void init(){
		original();
		
		//#if ${Assignmentautomatic} == "T"
		GenerateButtonAction generateAction = new GenerateButtonAction();
		//#endif
		
		//#if ${Assignmentautomatic} == "T"
		btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(248, 273, 117, 29);
		contentPane.add(btnGenerate);
		//#endif
		
		//#if ${Assignmentautomatic} == "T"
		btnGenerate.addActionListener(generateAction);
		//#endif
		
	}
	
	//#if ${Assignmentautomatic} == "T"
	private class GenerateButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			String submissao = comboBoxSubmission.getSelectedItem().toString();
			List<Reviewer> reviewerList = new ArrayList<Reviewer>();
			if(submissao.equals("")){
				JOptionPane.showMessageDialog(getContentPane(),
						"Selecione uma Submissão", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
			}else{
				try {
					int subId = RiSEEventMainScreenP.facade.getSubmissionIdByTitle(submissao);
					Submission sub = RiSEEventMainScreenP.facade.searchSubmission(subId);
					String keywords = sub.getKeywords();
					String keywordsSplit[] = keywords.split(Pattern.quote(","));
					reviewerList = RiSEEventMainScreenP.facade.getReviewers();
					boolean flag;

					
					for(Reviewer r : reviewerList){
						flag = false;
						ReviewerTableModel model;
						String knowledgeAreaSplit[] = r.getKnowledgeArea().split(Pattern.quote(","));						
						for(String know : knowledgeAreaSplit){
							flag = false;
							for(String key : keywordsSplit){
								if(know.equals(key)){
									listaRevisoresSelecionados.add(r);
									model = new ReviewerTableModel(listaRevisoresSelecionados);
									tableSelectReviewer.setModel(model);
									flag = true;
									break;
								}
							}
							if(flag == true){
								break;
							}
						}
						if(listaRevisoresSelecionados.size() == 3){
							break;
						}
					}
					
					if(listaRevisoresSelecionados.size() < 3){
					
						if(listaRevisoresSelecionados.isEmpty()){
							int i = 0;
							ReviewerTableModel model;
							while(i<3){
								listaRevisoresSelecionados.add(reviewerList.get(i));
								model = new ReviewerTableModel(listaRevisoresSelecionados);
								tableSelectReviewer.setModel(model);
								i++;
							}
							
						}else{
							int i = listaRevisoresSelecionados.size();
							ReviewerTableModel model;
							while(i<3){
								listaRevisoresSelecionados.add(reviewerList.get(i));
								model = new ReviewerTableModel(listaRevisoresSelecionados);
								tableSelectReviewer.setModel(model);
								i++;
							}
							
						}
						
					}
					
					
				} catch (RepositoryException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				} catch (SubmissionNotFoundException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				} catch (SubmissionAlreadyInsertedException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
			}
				
		}
	}
	//#endif
	
}
