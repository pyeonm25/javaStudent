package subject;

public class Subject {

	private int stuNo;
	private String subName;
	private int score;

	public Subject(int stuNo, String subName, int score) {
		this.stuNo = stuNo;
		this.subName = subName;
		this.score = score;
	}

	public int getStuNo() {
		return stuNo;
	}

	public void setStuNo(int stuNo) {
		this.stuNo = stuNo;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String saveData() {
		return String.format("%s/%s/%s\n", stuNo, subName, score);
	}

	@Override
	public String toString() {
		String data = "";
		data += subName + " " + score + "점";
		return data;
	}
}
