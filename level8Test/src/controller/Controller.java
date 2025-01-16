package controller;

import student.StudentDAO;
import subject.SubjectDAO;
import utils2.Utils2;

public class Controller {
	private StudentDAO stuDAO;
	private SubjectDAO subDAO;
	public Controller(){
		stuDAO = new StudentDAO();
		subDAO = new SubjectDAO();
//		
//		stuDAO.loadStudentData(Utils.loadDataToFile("student.txt"));
//		subDAO.loadSubjectData(Utils.loadDataToFile("subject.txt"));
	}
	private void mainMenu() {
		System.out.println("[1]학생추가"); // 학번(1001) 자동증가 : 학생ID 중복 저장 불가
		System.out.println("[2]학생삭제"); // 학생 ID 입력 후 삭제, 과목도 같이 삭제
		System.out.println("[3]학생과목추가"); // 학번 입력후 점수 랜덤 50-100 : 과목이름 중복 저장불가능
		System.out.println("[4]학생과목삭제"); // 학번 입력 후 과목 이름 받아서 해당 과목에서 학생 1명 삭제
		System.out.println("[5]전체학생목록"); // 점수로(내림차순) 출력
		System.out.println("[6]한 과목 학생목록"); // 과목이름 입력받아서 해당 과목 학생이름과 과목점수 출력
		                                        // (이름의 오름차순 출력)
		System.out.println("[7]파일 저장");
		System.out.println("[8]파일 로드");
		System.out.println("[0]종료");

	}

	
	public void run() {
		 Utils2 utils = Utils2.getInstance();
		//StudentDAO 안에서 Subject 객체 생성 x 
		// SubjectDAO 안에서 Student 객체 생성 x 
		
		while(true){
			mainMenu();
			//int sel = Utils.getValue("메뉴 선택", 0, 8);
			int sel = Utils2.getInstance().getValue("메뉴 선택", 0, 8);
			if(sel == 0) {
				System.out.println("프로그램 종료");
				break;
			}else if(sel == 1) {
				stuDAO.addStudent();
			}else if(sel == 2) {
				 subDAO.deleteSubjectsByStudentNo(stuDAO.deleteAStudent());
			}else if(sel == 3) {
				subDAO.addSubjectToAStudent(stuDAO);
			}else if(sel == 4) {
				subDAO.deleteASubject(stuDAO);
			}else if(sel == 5) {
				//stuDAO.printAllStudent(subDAO);
				stuDAO.printAllStudentByScore(subDAO);
			}else if(sel == 6) {
				subDAO.printAllSubjectBySubName(stuDAO);
			}else if(sel == 7) {
				 utils.saveDataToFile("student.txt" , stuDAO.getAllListToData() );
				 utils.saveDataToFile("subject.txt" , subDAO.getAllListToData() );
			}else if(sel == 8) {
				stuDAO.loadStudentData(utils.loadDataToFile("student.txt"));
				subDAO.loadSubjectData(utils.loadDataToFile("subject.txt"));
			}

		}
	}
}
