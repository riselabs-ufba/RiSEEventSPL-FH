package rise.splcc.repository;

public class SubmissionRepositoryBDR implements SubmissionRepository {
	
	//#if ${SubmissionCompleta} == "T"
	@Override
	public void insert(File attachment, int idActivity) throws RepositoryException {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;

		FileInputStream input = null;

		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/EeventDB", "root", "password");

			// 2. Prepare statement
			String sql = "INSERT INTO submission" + 
			             "(idActivity, type, abstract, keywords, title, attachment) Values" +
					      "(?,?,?,?,?,?)";
			myStmt = myConn.prepareStatement(sql);

			// 3. Set parameter for resume file name
			input = new FileInputStream(attachment);
			myStmt.setInt(1, idActivity);
			myStmt.setString(2, "");
			myStmt.setString(3, "");
			myStmt.setString(4, "");
			myStmt.setString(5, "");
			myStmt.setBinaryStream(6,input);

			System.out.println("Reading input file: " + attachment.getAbsolutePath());

			// 4. Execute statement
			System.out.println("\nStoring resume in database: " + attachment);
			System.out.println(sql);

			myStmt.executeUpdate();

			System.out.println("\nCompleted successfully!");
			
			if (input != null) {
				input.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		} 
	}
	//#endif

}
