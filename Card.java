public class Card {

	String name;
	String phone_num;
	String date;
	String VMS_ID;

	public String tostringBuff() {		// 서버에 보내는 카드 String 제작
		String CRD = "CRD/" + name + "/" + phone_num + "/" + date + "/" + VMS_ID + "/";
		return CRD;
	}

}
