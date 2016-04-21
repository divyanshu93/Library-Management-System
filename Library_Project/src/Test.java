import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {

		DBConnector db = new DBConnector();

		db.getData("MARK");
		db.getResult("9780001047976", "Brave New World", "Tony Britton");

	}

}
