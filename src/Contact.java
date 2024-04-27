import java.io.Serializable;
import java.util.Random;

public class Contact implements Serializable {
	String name, phone, email, gender = "Not Specified", hometown, occupation;
	String[] hobbies;

	public Contact() {
		name = phone = "";
		Random r = new Random();

		for (int i = 0; i < 7; i++) {
			phone += r.nextInt(10);
		}

		for (int i = 0; i < 5; i++) {
			name += (char) (r.nextInt('Z' + 1 - 'A') + 'A'); // [A, Z + 1)
		}
	}

	public Contact(String name, String phone) {
		this.name = name;
		this.phone = phone;
	}

	public Contact(String name, String phone, String email, String gender, String hometown, String occupation,
			String[] hobbies) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.hometown = hometown;
		this.occupation = occupation;
		this.hobbies = hobbies;
	}

	public String toString() {
		return name + " (" + gender + ") : " + phone;
	}
}
