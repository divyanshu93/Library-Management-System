
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnector {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	public DBConnector() {
		/*
		 * Scanner sc=new Scanner(System.in); System.out.println(
		 * "Enter username for database = "); String username = sc.nextLine();
		 * System.out.println("Enter password for database = "); String password
		 * = sc.nextLine();
		 */
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library_Project", "root", "div@1993");
			if (conn != null)
				System.out.println("Connected to database");
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// public void insert(String userName, String fileName , byte[] encFileKey,
	// byte[] encHashKey,String hashValue,String doubleHashPass) throws
	// SQLException{
	//// String query=new String("select * from keystore");
	//// rs=stmt.executeQuery(query);
	//// while(rs.next()){
	//// System.out.println("ID: "+rs.getString(1)+" NAME "+rs.getString(2));
	//// }
	// PreparedStatement p = conn.prepareStatement("insert into keystore
	// values(?,?,?,?,?,?)");
	//// Scanner sc=new Scanner(System.in);
	//// System.out.println("Enter user= ");
	//// String user = sc.nextLine();
	//// System.out.println("Enter fileName= ");
	//// String filename = sc.nextLine();
	//// System.out.println("Enter encfilekey= ");
	//// String encfilekey = sc.nextLine();
	//// System.out.println("Enter enchashkey= ");
	//// String enchashkey = sc.nextLine();
	//// System.out.println("Enter hashvalue= ");
	//// String hashvalue = sc.nextLine();
	//// System.out.println("Enter doublehashpass= ");
	//// String doublehashpass = sc.nextLine();
	//
	//
	// p.setString(1, userName);
	// p.setString(2, fileName);
	// p.setBytes(3, encFileKey);
	// p.setBytes(4, encHashKey);
	// p.setString(5, hashValue);
	// p.setString(6, doubleHashPass);
	//
	// p.executeUpdate();
	//
	//// query=new String("select * from keystore");
	//// rs=stmt.executeQuery(query);
	//// while(rs.next()){
	////
	//// System.out.println(rs.getString(1)+" "+rs.getString(2)+"
	// "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+"
	// "+rs.getString(6));
	//// }
	//
	// }//end method
	//
	// public DbReturns getData(String userName,String fileName){
	//
	// ResultSet rs=null;
	// String input = "select * from keystore where user= '" + userName + "' and
	// filename = '" +fileName + "'";
	// DbReturns obj1 =new DbReturns();
	// try {
	// rs= stmt.executeQuery(input);
	// while(rs.next()){
	// //System.out.println(Arrays.toString(b1) + "\n"+
	// rs.getString(5)+"\n"+rs.getString(6));
	// obj1.setUserName(rs.getString(1));
	// obj1.setFileName(rs.getString(2));
	// obj1.setFileEncKey(rs.getBytes(3));
	// obj1.setHashEncKey(rs.getBytes(4));
	// obj1.setHashValue(rs.getString(5));
	// obj1.setHhkey(rs.getString(6));
	//
	// }
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return obj1;
	//
	//
	// }

	// adding some sample test code.

	public void getData(String ISBN) {

		ResultSet rs = null;
		String input = "select * from Borrower  where Fname='" + ISBN + "'";

		try {
			rs = stmt.executeQuery(input);
			while (rs.next()) {

				System.out.println(rs.getString(1) + "  " + rs.getString(2));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// public void gettable() throws IOException{
	// File outfile=new File("out.txt");
	// FileWriter fWriter = new FileWriter(outfile, true);
	// PrintWriter pWriter = new PrintWriter(fWriter);
	// ResultSet rs = null;
	// String isbn,authors;
	// String[] out=new String[10];
	// String temp = "select isbn13, author from books";
	// try{
	// rs = stmt.executeQuery(temp);
	// while(rs.next()){
	// isbn=rs.getString(1);
	// authors=rs.getString(2);
	// out=authors.split(",");
	//
	// for(int i=0;i< out.length;i++){
	//
	// pWriter.println(isbn+","+out[i]);
	//
	// }
	// }
	// }catch(SQLException e){
	// e.printStackTrace();
	// }
	// }

	// find book in library database
	public ArrayList<String> getResult(String isbn, String title, String author) {

		ResultSet rs = null;
		ArrayList<String> output = new ArrayList<>();
		String input = "select Book.ISBN, Book.Title, Authors.fullname, Library_Branch.Branch_id, Library_Branch.Branch_name, Book_copies.No_of_copies from Book, Book_author, Authors, Library_Branch, Book_copies where Book.ISBN = Book_author.ISBN and Book_author.Author_id = Authors.Author_id and Book_copies.Book_id = Book.ISBN and Book_copies.Branch_id = Library_Branch.Branch_id";
		String s1 = " and Book.ISBN like concat('%','" + isbn + "','%')";
		String s2 = " and Book.Title like concat('%','" + title + "','%')";
		String s3 = " and Authors.fullname like concat('%','" + author + "','%')";

		if (isbn.length() != 0) {
			input += s1;
		}
		if (title.length() != 0) {
			input += s2;
		}
		if (author.length() != 0) {
			input += s3;
		}

		try {
			rs = stmt.executeQuery(input);
			while (rs.next()) {
				output.add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " "
						+ rs.getString(5) + " " + rs.getString(6));
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
						+ rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return output;

	}

	// check out book from library database
	public String doCheckout(String isbn, String branchid, String cardno) {
		ResultSet rs1, rs2, rs3, rs4 = null;
		String book_loans = "select Count(ISBN) from Book_loans where ISBN = '" + isbn + "' and Branch_id = '"
				+ branchid + "'";
		String no_of_copies = "select No_of_copies from Book_copies where Book_id='" + isbn + "' and Branch_id='"
				+ branchid + "'";
		String borrowercount = "select Count(*) from Book_loans where Card_no = '" + cardno + "'";
		String input = "insert into Book_loans(ISBN, Branch_id, Card_no, Date_out, Due_date) values('" + isbn + "','"
				+ branchid + "','" + cardno + "',curdate(),date_add(curdate(), interval 14 day))";
		String getloanid = "select Loan_id from Book_loans where ISBN='" + isbn + "' and Branch_id='" + branchid
				+ "' and Card_no='" + cardno + "'";
		try {
			String r1 = "";
			String r2 = "";
			String r3 = "";
			String r4 = "";
			String updateFines = "";
			rs1 = stmt.executeQuery(book_loans);
			while (rs1.next()) {
				r1 = rs1.getString(1);
			}
			rs2 = stmt.executeQuery(no_of_copies);
			while (rs2.next()) {
				r2 = rs2.getString(1);
			}
			if (r1.equals(r2)) {
				return "No more book available";
			}
			rs3 = stmt.executeQuery(borrowercount);
			while (rs3.next()) {
				r3 = rs3.getString(1);
			}
			if (Integer.valueOf((r3)) >= 3) {
				return "Borrower has reached the limit of book loan";
			}
			int result = stmt.executeUpdate(input);

			// 100 is the loan_id which has been added to the book_loan table.

			// stmt.executeUpdate()
			if (result == 1) {
				rs4 = stmt.executeQuery(getloanid);
				System.out.println(getloanid);
				while (rs4.next()) {
					r4 = rs4.getString(1);
				}
				updateFines = "insert into Fines values(" + r4 + ",0,0)";
				stmt.executeUpdate(updateFines);
				return "Book loan successfull";
			}
			// run and tested on isbn = '9780001047976', branch_id = '2',
			// card_no = 'ID000888';
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Book loan successfull";

	}

	// check in book back to library database
	public ArrayList<String> getCheckinResult(String bookid, String cardno, String name) {
		ResultSet rs = null;
		ArrayList<String> output = new ArrayList<>();
		String[] fullname = name.split(" ");
		String fname = fullname[0];
		String lname = "";
		if (fullname.length > 1) {
			lname = fullname[1];
		}
		String input = "select Book_loans.* from Book_loans, Borrower where Book_loans.Card_no = Borrower.Card_no";
		String s1 = " and ISBN like concat('%','" + bookid + "','%')";
		String s2 = " and Borrower.Card_no like concat('%','" + cardno + "','%')";
		String s3 = " and (Fname like concat('%','" + fname + "','%') or Lname = " + lname + ")";
		if (bookid.length() != 0) {
			input += s1;
		}
		if (cardno.length() != 0) {
			input += s2;
		}
		if (name.length() != 0) {
			input += s3;
		}

		try {
			rs = stmt.executeQuery(input);
			while (rs.next()) {
				output.add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " "
						+ rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return output;
	}

	public String docheckin(String loanid) throws SQLException {
		ResultSet dateDifference = null;
		int rs = 0;
		int rs1 = 0;
		String returnStatement = "";
		String updateFineAmt = "";
		// set current date of checkin
		String updateQuery = "update Book_loans set Date_in = curdate() where Loan_id = '" + loanid + "'";
		System.out.println(updateQuery);
		// set paid boolean = 1 that is fine has been paid
		String updateFines = "update Fines set paid = '1' where Loan_id ='" + loanid + "'";
		// calculate the total days after due date
		String calculateFine = "select DATEDIFF(curdate(),Due_date) from Book_loans where Loan_id='" + loanid + "'";
		rs = stmt.executeUpdate(updateQuery);
		if (rs == 1) {
			returnStatement = "Check in updated sucessfully";
		} else {
			returnStatement = "Check in not sucessfull";
		}

		rs1 = stmt.executeUpdate(updateFines);
		long dateDiff = 0;
		// the number of days difference is returned here.not the fine.
		dateDifference = stmt.executeQuery(calculateFine);
		while (dateDifference.next()) {
			dateDiff = dateDifference.getLong(1);

		}
		if (dateDiff > 0) {
			// update the amount in case we exceed the due date.
			updateFineAmt = "update Fines set fine_amt=" + String.valueOf(dateDiff * 0.25) + "Loan_id=" + loanid;
			stmt.executeUpdate(updateFineAmt);
		}

		return returnStatement;

	}

	public String createRecord(String ssn, String fname, String lname, String phone, String address) {
		ResultSet rs1, rs2 = null;
		String ssncheck = "select ssn from Borrower where Ssn ='" + ssn + "'";
		String idcount = "select count(*) from Borrower";

		String card_id = "";

		try {
			rs1 = stmt.executeQuery(ssncheck);

			while (rs1.next()) {
				String r1 = rs1.getString(1);
				if (ssn.equals(r1)) {
					return "Ssn already exists in the database";
				}
			}
			rs2 = stmt.executeQuery(idcount);
			while (rs2.next()) {
				// creating unique Card_id
				int card_no = Integer.valueOf(rs2.getString(1)) + 1;
				card_id = String.valueOf(card_no);
				for (int i = card_id.length(); i < 6; i++) {
					card_id = "0" + card_id;
				}
				card_id = "ID" + card_id;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		String input = "insert into Borrower (Card_no, Ssn, Fname, Lname, Phone, Address) values ('" + card_id + "','"
				+ ssn + "','" + fname + "','" + lname + "','" + phone + "','" + address + "')";
		try {
			int rs3 = stmt.executeUpdate(input);
			// run test by inserting following values
			// 'ID001001','000-00-0000','Divyanshu','Paliwal','469-688-3108','7220
			// McCallum Blvd'
			// another test with inserted values
			// 'ID001002','000-00-1000','Divyanshu','Paliwal','4567'
			if (rs3 == 1) {
				return "Record Created";
			} else {
				return "Record not Created";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	boolean updateFines() throws SQLException {

		String query = "select Loan_id from Fines";
		ResultSet rs, dateDifference;

		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			// our update was not successful.
			return false;

		}
		ArrayList<String> arr = new ArrayList<>();
		while (rs.next()) {
			arr.add(rs.getString(1));
		}
		for (String loanId : arr) {

			String calculateFine = "select DATEDIFF(curdate(),Due_date) from Book_loans where Loan_id=";
			String updateQuery;
			calculateFine += loanId;
			long dateDiff = 0;
			// the number of days difference is returned here.not the fine.
			dateDifference = stmt.executeQuery(calculateFine);
			while (dateDifference.next()) {
				dateDiff = dateDifference.getLong(1);

			}
			if (dateDiff > 0) {
				// update the amount in case we exceed the due date.
				updateQuery = "update Fines set fine_amt=" + String.valueOf(dateDiff * 0.25)
						+ "where paid=0 and Loan_id=" + loanId;
				stmt.executeUpdate(updateQuery);
			}
		}
		return true;
	}

	public Double DisplayFines(String cardno, int check) {
		ResultSet rs = null;
		String input = "select sum(fine_amt) from Fines,Book_loans where Fines.Loan_id = Book_loans.Loan_id and Card_no='"
				+ cardno + "'";
		String finepaid = " and Fines.paid ='1'";
		String finedue = " and Fines.paid = '0'";
		Double output = 0.0;

		if (check == 2) {
			input += finepaid;
		} else if (check == 3) {
			input += finedue;
		}

		try {
			rs = stmt.executeQuery(input);
			if (rs == null) {
				return output;
			}
			while (rs.next()) {
				output = rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return output;
	}

}// end class