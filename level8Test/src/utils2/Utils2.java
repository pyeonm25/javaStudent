package utils2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Utils2 {
	private Utils2() {};
	private static Utils2 instance;
	
	public static Utils2 getInstance() {
		if(instance == null) instance = new Utils2();
		return instance;
	}

	private Scanner sc = new Scanner(System.in);
	private Random rd = new Random();
	private final String CUR_PATH = System.getProperty("user.dir") + "\\src\\" + Utils2.class.getPackageName()
			+ "\\";

	public int getValue(String msg, int start, int end) {
		System.out.println("[" + start + " ~ " + end + "]" + msg);
		while (true) {
			try {
				int num = sc.nextInt();
				sc.nextLine();
				if (num < start || num > end) {
					System.out.printf("%d - %d 사이 값 입력해주세요 %n", start, end);
					continue;
				}
				return num;
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("숫자값을 입력하세요");
				break;
			}
		}
		return -1;
	}

	public String getString(String msg) {
		System.out.print(msg);
		return sc.next();
	}

	public  String loadDataFromFile(String fileName) {
		String file = CUR_PATH + fileName;
		String data = "";
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr);) {
			int idx = 0;
			while (true) {
				String line = br.readLine();

				if (line == null) {
					break;
				}
				data += idx == 0 ? line : "," + line;
				idx++;
			}

		} catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않습니다.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;

	}

	public int getRdNum(int start, int end) {
		int num = rd.nextInt(end - start + 1) + start;
		return num;
	}

	public void saveDataToFile(String fileName, String data) {
		if (data.isBlank()) {
			printMsg("데이터가 존재하지않습니다");
			return;
		}
		String file = CUR_PATH + fileName;
		try (FileWriter fw = new FileWriter(CUR_PATH + fileName);) {
			fw.write(data);
			System.out.println(file + "파일저장 성공 ");
		} catch (IOException e) {
			System.out.println(file + "파일저장 실패 ");
			e.printStackTrace();
		}

	}

	public void printMsg(String msg) {
		System.out.println("[ " + msg + " ]");
	}

	public String loadDataToFile(String fileName) {
		String file = CUR_PATH + fileName;
		String data = "";
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr);) {
			int idx = 0;
			while (true) {
				String line = br.readLine();

				if (line == null) {
					break;
				}
				data += idx == 0 ? line : "," + line;
				idx++;
			}
			System.out.println(file + "파일로드 성공 ");
		} catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않습니다.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(file + "파일로드 실패 ");
			e.printStackTrace();
		}

		return data;

	}

}
