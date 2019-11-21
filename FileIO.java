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

	static void FileInput() {			// ī�� ���Ͽ��� ���� �о�� ī�� Ŭ������ ����

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
			System.out.println("����" + e);
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				System.out.println("���� �ݱ� ����" + e);
			}
		}
	}

	static void FileOutput() {			// ī�� Ŭ�������� ���� �ҷ��� ���Ͽ� ����
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(mediator.file));
			bw.write(mediator.CM.CRD.name); 
			bw.newLine(); // ���� �� ���� ����
			bw.write(mediator.CM.CRD.phone_num); 
			bw.newLine(); // ���� �� ���� ����
			bw.write(mediator.CM.CRD.VMS_ID); 
			bw.newLine(); // ���� �� ���� ����
			bw.write(mediator.CM.CRD.date);
			
		} catch (Exception e) {
			System.out.println("����" + e);
		} finally {
			try {
				bw.close();
			} catch (Exception e) {
				System.out.println("���� �ݱ� ����" + e);
			}
		}
	}

	static void refreshCardData(String date) {	// �����κ��� ��¥���� �ҷ��� ī�� ���Ͽ� ���� ����
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(mediator.file));
			bw.write(mediator.CM.CRD.name); 
			bw.newLine(); // ���� �� ���� ����
			bw.write(mediator.CM.CRD.phone_num); 
			bw.newLine(); // ���� �� ���� ����
			bw.write(mediator.CM.CRD.VMS_ID); 
			bw.newLine(); // ���� �� ���� ����
			bw.write(date);
			
		} catch (Exception e) {
			System.out.println("����" + e);
		} finally {
			try {
				bw.close();
			} catch (Exception e) {
				System.out.println("���� �ݱ� ����" + e);
			}
		}

	}

}
