package subject;

import java.util.ArrayList;
import java.util.Arrays;

import student.Student;
import student.StudentDAO;
import utils.Utils;

public class SubjectDAO {
	ArrayList<Subject> list;

	
	
	public SubjectDAO() {
		list = new ArrayList<>();
	}

	private void printAllSubList() {

		for (Subject sub : list)
			System.out.println(sub);
		System.out.println("--------------");
	}

	public void loadSubjectData(String data) {
		if (data.isBlank()) {
			Utils.printMsg("데이터가 존재하지않습니다");
			return;
		}
		list = new ArrayList<>();
		String[] temp = data.split(",");
		for (int i = 0; i < temp.length; i++) {
			String info[] = temp[i].split("/");
			insertSubject(new Subject(Integer.parseInt(info[0]), info[1], Integer.parseInt(info[2])));
		}
		// printAllSubList();
	}

	private void insertSubject(Subject sub) {
		list.add(sub);
	}

	public String getAllSubjectAStudentByStuNo(int stuNo) {
		String data = "";
		for (Subject sub : list) {
			if (stuNo == sub.getStuNo())
				data += sub + "  ";
		}

		return data.equals("") ? "[ no subject data] " : data;
	}

	private boolean isExsitData() {
		if (list.size() == 0) {
			Utils.printMsg(" 과목 데이터가 존재하지 않습니다");
			return false;
		}
		return true;
	}

	private void removeASubject(int idx) {
		list.remove(idx);
	}

	public void deleteSubjectsByStudentNo(int stuNo) {
		if (!isExsitData() || stuNo == -1)
			return;
		
		
		for (int i = 0; i < list.size(); i += 1) {
			if (list.get(i).getStuNo() == stuNo) {
				removeASubject(i);
				i -= 1;
			}
		}

	}

	private ArrayList<String> subjectNameList(int stuNo) {
		ArrayList<String> list = new ArrayList<String>();
		for (Subject sub : this.list) {
			if (sub.getStuNo() == stuNo)
				list.add(sub.getSubName());
		}
		return list;
	}

	public void addSubjectToAStudent(StudentDAO stuDAO) {
		if (!isExsitData())
			return;
		int stuNo = Utils.getValue("학번 입력>>", 1001, 9999);

		if (!stuDAO.isExsitStuNo(stuNo)) {
			Utils.printMsg("존재하지 않는 학생 번호 입니다");
			return;
		}

		ArrayList<String> nameList = subjectNameList(stuNo);
		if (nameList.size() != 0) {
			System.out.println(nameList);
		}

		String name = Utils.getString("추가 과목 이름 입력 >>");
		if (isExsitSubjectName(nameList, name)) {
			Utils.printMsg("이미 존재한 과목 이름 입니다");
			return;
		}
		
		insertSubject(new Subject(stuNo, name, Utils.getRdNum(50, 100)));
		Utils.printMsg("과목 추가 완료");

	}

	private boolean isExsitSubjectName(ArrayList<String> nameList, String name) {
		for (String subName : nameList) {
			if (subName.equals(name)) {
				return true;
			}
		}
		return false;
	}

	private int getSubIdxByStuNoSubName(int stuNo, String subName) {
		int idx = 0;
		for (Subject s : list) {
			if (s.getStuNo() == stuNo && s.getSubName().equals(subName)) {
				return idx;
			}
			idx++;
		}
		return -1;
	}

	public void deleteASubject(StudentDAO stuDAO) {
		if (!isExsitData())
			return;
		int stuNo = Utils.getValue("학번 입력>>", 1001, 9999);

		if (!stuDAO.isExsitStuNo(stuNo)) {
			Utils.printMsg("존재하지 않는 학생 번호 입니다");
			return;
		}

		ArrayList<String> nameList = subjectNameList(stuNo);
		if (nameList.size() == 0) {
			System.out.println("해당 학생 과목 데이터가 없습니다");
			return;
		}

		System.out.println(nameList);
		String name = Utils.getString("삭제 과목 이름 입력 >>");
		if (!isExsitSubjectName(nameList, name)) {
			Utils.printMsg("존재 하지 않는 과목 이름");
			return;
		}
		removeASubject(getSubIdxByStuNoSubName(stuNo, name));

		Utils.printMsg(" 과목 삭제 완료");

	}

	public double getTotalScoreAgv(int stuNo) {
		int cnt = 0;
		int total = 0;
		for (Subject sub : list) {
			if (stuNo == sub.getStuNo()) {
				cnt += 1;
				total += sub.getScore();
			}
		}

		if (cnt != 0)
			return total * 1.0 / cnt;
		
		return 0;
	}

	public void printAllSubjectBySubName(StudentDAO stuDAO) {
		if (!isExsitData())
			return;
		ArrayList<Subject> tempList = new ArrayList<Subject>();

		String name = Utils.getString("검색 과목 이름 입력 >>");

		for (Subject sub : list) {
			if (sub.getSubName().equals(name)) {
				tempList.add(sub);
			}

		}

		if (tempList.size() == 0) {
			Utils.printMsg("해당 과목 이름 데이터가 존재하지 않습니다");
			return;
		}

		String[] names = new String[tempList.size()];
		int[] scores = new int[tempList.size()];

		int idx = 0;
		for (Subject sub : tempList) {
			names[idx] = stuDAO.getAStuNameByStuNo(sub.getStuNo());
			scores[idx++] = sub.getScore();
		}

		for (int i = 0; i < names.length; i += 1) {
			String minName = names[i];
			for (int k = i; k < names.length; k += 1) {
				if (minName.compareTo(names[k]) > 0) {
					minName = names[k];

					String temp = names[i];
					names[i] = names[k];
					names[k] = temp;

					int temp2 = scores[i];
					scores[i] = scores[k];
					scores[k] = temp2;

				}
			}
		}

		for (int i = 0; i < names.length; i += 1) {
			System.out.printf("%s %d점 \n", names[i], scores[i]);
		}

	}

	public String getAllListToData() {
		String data = "";
		for (Subject sub : list)
			data += sub.saveData();
		return data;
	}

}
