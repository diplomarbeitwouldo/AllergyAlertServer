package Models;

public class Allergy {
	private String name;
	private int id;

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public Allergy(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
