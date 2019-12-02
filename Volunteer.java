public class Volunteer {
	String name;
	String phone_num;
	String start_date;
	String end_date;
	String contents;
	
	public String tostringBuff() {		// 서버에 보내는 카드 Volunteer 제작
		String VO = "VO/" + name + "/" + phone_num + "/" + start_date + "/" + end_date + "/" + contents + "/";
		return VO;
	}
}