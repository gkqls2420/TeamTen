public class Card {

	String name;
	String phone_num;
	String date;
	String VMS_ID;

	public String tostringBuff() {		// ������ ������ ī�� String ����
		String CRD = "CRD/" + name + "/" + phone_num + "/" + date + "/" + VMS_ID + "/";
		return CRD;
	}

}
