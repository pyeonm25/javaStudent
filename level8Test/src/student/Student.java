package student;

public class Student {
	private int stuNo;
	private String name;
	private String stuId;
	private double avg;
	private static int hackbun = 1001;
	
	public static void setInitHackbun() {
		hackbun = 1001;
	}
	public Student(int stuNo, String name, String stuId) {
		super();
		this.stuNo = stuNo;
		this.name = name;
		this.stuId = stuId;
		hackbun+=1;
	}
	
	public Student(String name, String stuId) {
		super();
		this.stuNo = hackbun;
		this.name = name;
		this.stuId = stuId;
		hackbun+=1;
	}
	public int getStuNo() {
		return stuNo;
	}
	public void setStuNo(int stuNo) {
		this.stuNo = stuNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	
	public String saveData() {
		return String.format("%s/%s/%s/%.2f\n", stuNo, name, stuId, avg);
	}
	@Override
	public String toString() {
		return String.format("%s\t%s\t%s\t 평균 %.2f점 \n", stuNo, name, stuId, avg);
	}
	
	
	
}
