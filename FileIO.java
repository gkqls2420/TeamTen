import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {
	static ClientMediator mediator;

	public FileIO(ClientMediator m) {
		mediator = m;
	}

	static boolean checkBeforeFile(File file) {
		if (file.exists()) {
			if (file.isFile() && file.canRead()) {
				return true;
			}
		}
		return false;
	}

	static void FileInput() {			// 카드 파일에서 값을 읽어와 카드 클래스에 저장

		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(mediator.file));

			String name = br.readLine();
			mediator.CM.CRD.name = name;

			String phone_num = br.readLine();
			mediator.CM.CRD.phone_num = phone_num;

			String VMS_ID = br.readLine();
			mediator.CM.CRD.VMS_ID = VMS_ID;

			String date = br.readLine();
			mediator.CM.CRD.date = date;

			br.close();
		} catch (Exception e) {
			System.out.println("오류" + e);
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				System.out.println("파일 닫기 오류" + e);
			}
		}
	}

	static void FileOutput() {			// 카드 클래스에서 값을 불러와 파일에 저장
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(mediator.file));
			bw.write(mediator.CM.CRD.name); 
			bw.newLine(); // 개행 즉 엔터 역할
			bw.write(mediator.CM.CRD.phone_num); 
			bw.newLine(); // 개행 즉 엔터 역할
			bw.write(mediator.CM.CRD.VMS_ID); 
			bw.newLine(); // 개행 즉 엔터 역할
			bw.write(mediator.CM.CRD.date);
			
		} catch (Exception e) {
			System.out.println("오류" + e);
		} finally {
			try {
				bw.close();
			} catch (Exception e) {
				System.out.println("파일 닫기 오류" + e);
			}
		}
	}

	static void refreshCardData(String date) {	// 서버로부터 날짜값을 불러와 카드 파일에 새로 저장
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(mediator.file));
			bw.write(mediator.CM.CRD.name); 
			bw.newLine(); // 개행 즉 엔터 역할
			bw.write(mediator.CM.CRD.phone_num); 
			bw.newLine(); // 개행 즉 엔터 역할
			bw.write(mediator.CM.CRD.VMS_ID); 
			bw.newLine(); // 개행 즉 엔터 역할
			bw.write(date);
			
		} catch (Exception e) {
			System.out.println("오류" + e);
		} finally {
			try {
				bw.close();
			} catch (Exception e) {
				System.out.println("파일 닫기 오류" + e);
			}
		}

	}

}
