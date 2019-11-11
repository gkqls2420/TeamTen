import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {
	static ClientMediator mediator;
	
	public FileIO(ClientMediator m)
	{
	  mediator = m;
	}
	
	static boolean checkBeforeFile(File file){
		if(file.exists()){
			if(file.isFile() && file.canRead()){
				return true;
			}
		}
		return false;
	}
	

	static void FileInput() { 
		try {
			if(checkBeforeFile(mediator.file)) {
				BufferedReader br = new BufferedReader(new FileReader(mediator.file));
				String name = br.readLine();
				mediator.CM.card.name = name;
				
				String phone_num = br.readLine();
				mediator.CM.card.phone_num = phone_num;
					
				String VMS_ID = br.readLine();
				mediator.CM.card.VMS_ID = VMS_ID;
				
				String date = br.readLine();		
				mediator.CM.card.date = date;
				
				br.close();
			}
			else {
				System.out.println("카드가 없습니다");
				mediator.handle("make_Card");
			}
		}catch (IOException e) {
		      e.printStackTrace();
	    }
	}
	
	static void FileOutput() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(mediator.file));
			bw.write(mediator.CM.card.name); //출력
	        bw.newLine(); //개행 즉 엔터 역할
	    	bw.write(mediator.CM.card.phone_num); //출력
	        bw.newLine(); //개행 즉 엔터 역할
	    	bw.write(mediator.CM.card.VMS_ID); //출력
	        bw.newLine(); //개행 즉 엔터 역할

			
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}

