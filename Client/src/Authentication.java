public class Authentication
{
	ClientMediator mediator;

  public Authentication(ClientMediator m)
  {
    mediator = m;
  }

  boolean auth_volunteer(){

	  // 봉사 일지가 제대로 작성되었는지 확인 (양식, 날짜 등등)
	  
	  //제대로 입력되었다면
	  return true;
	  
  }

  boolean auth_card() {

	  // 카드가 제대로 작성이 되었는지 (VMS를 썼다면 양식에 맞는지, 전화번호 뒷자리는 int형 4자리가 맞는지 등등의 조건)
	  
	  //제대로 입력되었다면
	  return true;
	  
  }
}
