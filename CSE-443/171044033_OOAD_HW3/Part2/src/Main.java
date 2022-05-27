/**
 * Main class for testing
 */
public class Main {

	/**
	 * test method
	 */
	public static void testEmailBook() {
		MailComponent allStudents = new CompositeMailComponent("allstudents@gtu.edu.tr");
		MailComponent allcengStudents = new CompositeMailComponent("allcengstudents@gtu.edu.tr");
		MailComponent cengStudents4th = new CompositeMailComponent("cengstudents4th@gtu.edu.tr");
		MailComponent eeStudents3th = new CompositeMailComponent("eestudents3th@gtu.edu.tr");
		MailComponent cengStudengs3th = new CompositeMailComponent("cengstudents3th@gtu.edu.tr");
		MailComponent engineeringStudents = new CompositeMailComponent("allenginneringstudents@gtu.edu.tr");

		cengStudengs3th.add(new LeafMailComponent("Ozan Yesiller", "ozanyesiller@xyz.com"));
		cengStudengs3th.add(new LeafMailComponent("Ali Terzi", "aliterzi@xyz.com"));

		cengStudents4th.add(new LeafMailComponent("Oguzhan Ucar", "oguzhanucar@xyz.com"));
		cengStudents4th.add(new LeafMailComponent("Miralem Pjanic", "miralem@xyz.com"));
		cengStudents4th.add(new LeafMailComponent("Rıdvan Destan", "ridvandestan@xyz.com"));

		allcengStudents.add(cengStudengs3th);
		allcengStudents.add(cengStudents4th);

		eeStudents3th.add(new LeafMailComponent("Cumhur Elektrik", "cumhurr@xyz.com"));
		eeStudents3th.add(new LeafMailComponent("Onur Sari", "onursarii@xyz.com"));
		eeStudents3th.add(new LeafMailComponent("Burak Akgun", "burakk@xyz.com"));

		allStudents.add(new LeafMailComponent("Ali Velioglu", "alivelioglu@xyz.com"));
		allStudents.add(new LeafMailComponent("Yaprak Ogrenci", "yaprakkOgrenci@xyz.com"));
		allStudents.add(new LeafMailComponent("Selim Ertan", "ertanselimm@xyz.com"));

		engineeringStudents.add(allcengStudents);
		engineeringStudents.add(eeStudents3th);

		allStudents.add(engineeringStudents);

		allStudents.print();
	}

	/**
	 * launcher method
	 * @param args cl arguments
	 */
	public static void main(String[] args) {
		testEmailBook();
	}
}
