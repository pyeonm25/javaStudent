package student;

import java.util.ArrayList;

import subject.Subject;
import subject.SubjectDAO;
import utils.Utils;

public class StudentDAO {

	ArrayList<Student> list;
	int hakbun;

	public StudentDAO() {
		list = new ArrayList<>();
		hakbun = 1001;
	}

	private void printAllStuList() {
		for (Student stu : list)
			System.out.println(stu);

		System.out.println("--------------");
	}

	public void loadStudentData(String data) {
		if (data.isBlank()) {
			Utils.printMsg("데이터가 존재하지않습니다");
			return;
		}
		list = new ArrayList<>();
		//hakbun = 1001;
		Student.setInitHackbun();
		
		String[] temp = data.split(",");
		for (int i = 0; i < temp.length; i++) {
			String info[] = temp[i].split("/");
			insertStudent(new Student(Integer.parseInt(info[0]), info[1], info[2]));
			//updateHakbun();
		}

		// printAllStuList();
	}

	private boolean isExsitData() {
		if (list.size() == 0) {
			Utils.printMsg(" 학생 데이터가 존재하지 않습니다");
			return false;
		}
		return true;
	}

	private int updateHakbun() {
		return hakbun++;
	}

	private void insertStudent(Student stu) {
		list.add(stu);
	}

	private int getStuIdxById(String id) {
		int idx = 0;
		for (Student s : list) {
			if (id.equals(s.getStuId()))
				return idx;
			idx += 1;
		}
		return -1;
	}

	public boolean isExsitStuNo(int stuNo) {
		if (!isExsitData())
			return false;
		for (Student s : list) {
			if (stuNo == s.getStuNo())
				return true;
		}
		return false;
	}

	public void addStudent() {
		String id = Utils.getString("[추가] 아이디 입력>>");
		if (getStuIdxById(id) != -1) {
			Utils.printMsg("이미 존재하는 아이디 입니다");
		}
		String name = Utils.getString("이름 입력>>");
	//	insertStudent(new Student(updateHakbun(), name, id));
		insertStudent(new Student(name, id));
		Utils.printMsg("학생 한명 추가 완료");
	}

	public void printAllStudent(SubjectDAO subDAO, ArrayList<Student> list) {
		if (!isExsitData())
			return;
		for (Student stu : list) {
			System.out.println(stu);
			System.out.println(subDAO.getAllSubjectAStudentByStuNo(stu.getStuNo()));
			System.out.println("-----------------");
		}
	}

	public void printAllStudentByScore(SubjectDAO subDAO) {
		if (!isExsitData())
			return;
		Utils.printMsg("학생 목록( 점수 내림차순 )");
		System.out.println();
		// 점수 오름차순 출력
		ArrayList<Student> tempList = new ArrayList<Student>();
		for (Student student : list) {
			tempList.add(student);
		}
		
		// 평균계산
		for (Student student : list) {
			double avg = subDAO.getTotalScoreAgv(student.getStuNo());
			student.setAvg(avg);
		}

		// 평균 오름차순으로 정렬
		for (int i = 0; i < tempList.size(); i++) {
			for (int j = i; j < tempList.size(); j++) {
				if (tempList.get(i).getAvg() < tempList.get(j).getAvg()) {
					Student temp = tempList.get(i);
					tempList.set(i, tempList.get(j));
					tempList.set(j, temp);
				}
			}
		}
		printAllStudent(subDAO, tempList);
	}

	private void removeAStudent(int idx) {
		list.remove(idx);
	}

	public int deleteAStudent() {
		if (!isExsitData())
			return -1;
		// 학생 아이디 입력받아서 해당 학생 삭제
		String stuId = Utils.getString("삭제할 학생 아이디 입력>>");
		int idx = getStuIdxById(stuId);
		if (idx == -1) {
			System.out.println("해당 학생이 존재하지 않습니다.");
			return -1;
		}
		Student stu = list.get(idx);

		removeAStudent(idx);
		System.out.println();
		Utils.printMsg(stu + " 삭제 완료 ");

		return stu.getStuNo();

	}

	public String getAStuNameByStuNo(int stuNo) {
		for (Student stu : list) {
			if (stuNo == stu.getStuNo())
				return stu.getName();
		}
		return null;

	}

	public String getAllListToData() {
		String data = "";
		for (Student stu : list)
			data += stu.saveData();
		return data;
	}

}
