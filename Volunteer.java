public class Volunteer {
	String name;
	String phone_num;
	String start_date;
	String end_date;
	String contents;
	
	public String tostringBuff() {		// ������ ������ ī�� Volunteer ����
		String VO = "VO/" + name + "/" + phone_num + "/" + start_date + "/" + end_date + "/" + contents + "/";
		return VO;
	}
}