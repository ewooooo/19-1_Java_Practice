import java.util.InputMismatchException;
import java.util.Scanner;


class node{
	
	private contact data; //컨택트타입 레퍼런스
	private node link; //링크타입 레퍼런스
	
	void change_contact(contact a) {data=a;} //data를 contact타입레퍼런스 a 매개변수로 받아서변경
	void change_link(node a ) {link =a;}//링크를 
	node get_link() {return link;} //사용위해 get함수구현
	contact get_data() {return data;}//사용위해 get함수구현
	
	node(contact x){
		data = x;
	} //생성자 (기본생성자 없으므로 사용원할시 만들어줘야함)
}// 연결리스트 노드


class List{
	private static int nodenumber=0;   //static 메소드에서 접근하기 위해 static으로 선언
	private node header;
	
	void change_header(node a){header=a;}
	static int get_nodenumber() {return nodenumber;}  //전역 함수로 만들기 위해 static으로 구현
	
	//////////////////이미 등록된 번호인 경우 출력하는 오류메시지 추가했습니다.
	void insert(node a, String number) {
		a.get_data().change_number(a.get_data().get_number().replace("-", ""));
		String num=a.get_data().get_number();
		node z = Search_Equalnumber(header,num);
		
		if(z == null) {//일치하는 연락처가 없는 경우 등록
		if(header == null) {
			header =a;
			List.nodenumber++;
			System.out.println("정상등록되었습니다.");
		}//하나도 없을경우(첫번째노드로 삽입시)
		
		else {
			node tmp=header;
			while(tmp.get_link()!=null){
				tmp=tmp.get_link();
			}
			tmp.change_link(a);
			List.nodenumber++;
			System.out.println("정상등록되었습니다.");
		}//그외의 경우
		}
		
		else//일치하는 경우 오류메시지 출력
			System.out.println("이미 등록된 전화번호입니다.");
		
		
	}//삽입함수
	
	
	
	/////////////////////
	
	
	
	 void print_allnodes() {
		node a =get_header(); //a에 헤더레퍼런스 대입
		if(a==null) {
			 System.out.println("저장된 연락처가 없습니다. ");
			 return;
		}
		contact x= a.get_data();//x에 첫번째노드의 data레퍼런스 대입
		
		for(int i=1;;i++) {//첫번째노드부터 출력
		 System.out.print(i+".  "+"이름:"+x.get_name()+"  ");
		 System.out.print("나이:"+x.get_age()+"  ");
		 System.out.println("연락처:"+x.get_number()+"  ");
		 a=a.get_link();
			if(a==null)break;//만약 다음노드없을경우(리스트 마지막노드라서 link가 null인경우)
			x=a.get_data();
		}
		
		 System.out.println("모두 출력했습니다");
		} // 모든노드 data 출력
	 
	void delete(node a) {
		node tmp=header;
		while(tmp.get_link()!=null){ //첫번째 노드가 아닐 
		if(tmp.get_link()==a) {
			tmp.change_link(a.get_link());
			a.change_link(null);
			nodenumber--;
			return;
			}
		tmp=tmp.get_link();
	
		}
		
////////////////
	header=header.get_link();
	nodenumber--;
	////////////////첫번째 노드일 경우
	return;
		
		//현재 노드가 하나라면 아래의 코드
		
		
		
	}//a 노드레퍼런스와 같은 레퍼런스 만나면 삭제 함수
	

	void delete(int i) {//i번째행 삭제
		
		if(i>nodenumber) {
			System.out.println("입력이 잘못되었습니다(저장된 연락처수보다 입력된 값이 더 큽니다.)");  //존재하지 않는 행
			return;
						}
		////////////////////////////////////////////
		if(i==1) {//첫번째 행을 삭제했을 때, 메시지를 출력하기위해서 코드 순서를 조금 바꿨어요
			contact x= header.get_data();
			System.out.println("["+x.get_name()+" "+x.get_age()+" "+x.get_number()+"] 가 삭제되었습니다.");  
			header=header.get_link();
			nodenumber--;
		return;
		
		}//첫번째노드삭제시
		///////////////////////////////////////////
		node removed=header;
		node tmp=null; //식제되는 노드 link값 가져오기위해 생성
		for(int j=1;j<i;j++){
			tmp=removed;//tmp는 지워지는노드 이전노드
			removed=removed.get_link();//removed
			}//i번째행을 삭제하기위해 i-1번만큼 연결리스트이동
		tmp.change_link(removed.get_link());
		nodenumber--;
		contact x= removed.get_data();
		System.out.println("["+x.get_name()+" "+x.get_age()+" "+x.get_number()+"] 가 삭제되었습니다.");
		return;
	}//행순서이용해서 삭제
	
	/////////////////이름, 연락처로 삭제하는 함수
	void delete_information(String information) {
		information=information.replace("-", "");
		node z = Search_Equalname(header,information);
		node y = Search_Equalnumber(header,information);
		if ((z==null&&y==null))
				System.out.println("존재하지 않는 연락처/이름 입니다.");  
		
		else if((z!=null&&z.get_data().get_name() != information)){ 
			
			delete(z);
			System.out.println("삭제되었습니다.");
		}
		else if((y.get_data().get_number() != information)){ 
			
			delete(y);
			System.out.println("삭제되었습니다.");
		}
		else 
			System.out.println("존재하지 않는 연락처/이름 입니다.");   
		 
	}
	//////////////////////////////////////////////////////
	public node Search_Node(node test_node,String sTest) {  //검색할 때 사용하는 search_node 함수
		sTest=sTest.replace("-", "");
		if(test_node==null) {	//현재노드가 비었으면 null 반환
			 return null;
		}else {
			while(test_node != null) { //현재지점부터 끝까지 검사
				if(-1 !=test_node.get_data().get_name().indexOf(sTest)  )	//입력값과 노드값에 같은 내용이 있는지 확인
					return test_node;		//검색결과 있는 노드 반환

				else if(-1 != test_node.get_data().get_number().indexOf(sTest)) 
					return test_node;
				else
					test_node= test_node.get_link();	//다음노드 지정
			}
			return null;	//검색결과 없으면 null 반환
		}
	}
	//////////////////////////////////////////////////
	
	public node Search_Equalname(node test_node,String sTest) {  //검색할 때 사용하는 search_node 함수
		if(test_node==null) {	//현재노드가 비었으면 null 반환
			 return null;
		}else {
			while(test_node != null) { //현재지점부터 끝까지 검사
				if(sTest.equals(test_node.get_data().get_name())  )	//입력값과 노드값에 같은 내용이 있는지 확인
					
					return test_node;		//검색결과 있는 노드 반환
			
				else
					test_node= test_node.get_link();	//다음노드 지정
			}
			return null;	//검색결과 없으면 null 반환
		}
	}
	
	public node Search_Equalnumber(node test_node,String sTest) {  //검색할 때 사용하는 search_node 함수
		if(test_node==null) {	//현재노드가 비었으면 null 반환
			 return null;
		}else {
			while(test_node != null) { //현재지점부터 끝까지 검사
				if(sTest.equals(test_node.get_data().get_number()) )	//입력값과 노드값에 같은 내용이 있는지 확인
					return test_node;		//검색결과 있는 노드 반환
				else
					test_node= test_node.get_link();	//다음노드 지정
			}
			return null;	//검색결과 없으면 null 반환
		}
	}
	
	
	
	
	
	
	
	public void search() {
		System.out.print("이름 혹은 번호 검색(일부도 가능) : ");		
		String sTtest = ContactMain.s.next();
		sTtest=sTtest.replace("-", "");
		node testNode = header;		//시작지점 header로 초기화
		testNode = Search_Node(testNode,sTtest);
		
		if(testNode == null) {//일치하는 노드가 없는 경우 (검색어를 포함하는 연락처가 없는 경우)
			
			System.out.println("검색결과: 0건");   //검색결과 0건 메시지 출력      (중첩으로 하지 않으니 null이 될때까지 검색을 하여서, 검색 결과가 있어도 마지막에 항상 검색결과 0건 메시지가 출력되어서 중첩 if 문 사용했습니다)
			return;
		}
		else { // 검색어를 포함하는 연락처가 있는 경우
			while(true) { //검색어를 포함하는 모든 연락처를 찾을 때까지 무한반복		
			testNode = Search_Node(testNode,sTtest);	//검색 메소드 호출(검색시작할노드, 입력문자열)
			if(testNode==null) {	//더이상 검색어를 포함하지 않는 경우
				return;
			}
			else {
				contact x= testNode.get_data();			//위에 올 출력 하신거에서 가져왔어요 ㅎㅎ
				System.out.print("이름:"+x.get_name()+"  ");
				System.out.print("나이:"+x.get_age()+"  ");
				System.out.println("연락처:"+x.get_number()+"  ");
			}
			testNode= testNode.get_link();	//추가로 만족하는 노드 있는지 확인하기 위해 현재노드 다음부터 다시 검사.
		}
		}
	}
	//////////////////////////////////////////////////////
	
	
	void changedata(int i,int j) {
		contact ctmp;
		node ntmp=header;
		node ntmp2=header;
		int k;
		for(k=1;k<i;k++) {
			ntmp=ntmp.get_link();
		}
		for(k=1;k<j;k++) {
			ntmp2=ntmp2.get_link();
		}
	ctmp=ntmp.get_data();	
	ntmp.change_contact(ntmp2.get_data());
	ntmp2.change_contact(ctmp);
	}//행데이터변경
	
	
	
	
	
	
	node get_header() {return header;}	
	
	

}//연결리스트 헤더 및 메소드 구현



class contact{
	private String name;
	private int age;
	////
	private	String number;
	//////
	String get_name(){return name;}
	String get_number(){return number;}
	int get_age() {return age;}//get함수 구현
	
	void change_name(String s) {name=s;}
	void change_age(int i) {age=i;}
	void change_number(String s) {number=s;} //변경할경우 
	
	
	contact(int i){
	
		 System.out.println("이름 입력하십시오.");	 
		// System.out.print("\n");
		name=ContactMain.s.nextLine();
		
		
		/////////
		for(int num = 0; num < 1; num++) {
			 System.out.print("나이를 입력하십시오.\n");
		 try{
			 age=ContactMain.s.nextInt();
			 ContactMain.s.nextLine();
		 }
		 
		 catch(InputMismatchException e) {
			 System.out.println("숫자가 아닙니다. 다시 입력하세요.");
			 ContactMain.s.nextLine();
			 num--;
			 continue;
		 }
		}
		////////////나이에 문자를 입력했을 경우 예외처리 발생
		 System.out.print("연락처를 입력하십시오.");
		 System.out.print("\n");
		number=ContactMain.s.nextLine();
	
	}// 사용자가 연락처내용 입력하는경우 위해 생성자 구분 매개변수로 정수형줘서 사용    예)contact(1)  <-메인함수에서 이렇게 사용중
	contact(){}//기본생성자 만들어줌
	
}






public class ContactMain { //메인함수들어가는 클래스
	static Scanner s=new Scanner(System.in); //scanner 여러개 사용하지않기위해 static으로 구현 다른 클래스에서 사용시 ContactMain.s로 사용
	
	
	static void print_menu() {
		System.out.println("##########연락처관리##########");
		System.out.println("1.연락처 출력");
		System.out.println("2.연락처 입력");
		System.out.println("3.연락처 삭제");
		System.out.println("4.연락처로 검색");
		System.out.println("5.연락처 순서 변경");
		System.out.println("6.끝내기");
		System.out.println("##########################");
	}//메뉴출력하는 함수 구현 
	
	
	public static void main(String[] args) {
		List contactlist=new List();
		
		
		int i=0;
		
		for(;;) {//사용자가 종료할때까지 무한반복(break문들거갈때까지)
		
			print_menu();//메뉴출력
			
			
			
			///////////메뉴에 문자를 입력했을 경우 예외처리
			for(int num = 0; num < 1; num++) {
				System.out.print("원하는 메뉴번호:");
			 try{
				 i=s.nextInt();
					s.nextLine();
			 }
			 
			 catch(InputMismatchException e) {
				 System.out.println("숫자가 아닙니다. 다시 입력하세요.");
				 s.nextLine();
				 num--;
				 continue;
			 }
			}
		///////////////////////
			
			
			if(i==1) {
				contactlist.print_allnodes();
			}//모든노드 출력
			else if (i==2) {
				contact x=new contact(1);
				node y= new node(x);
				contactlist.insert(y, x.get_number());	///이미 등록된 연락처인지 검사하기위해 number 정보 넘겨줌
				
			}//삽입함수
			
			
			/////////////
			else if (i==3) {
				
				for(int num = 0; num < 1; num++) {
					System.out.print("1. 행 번호로 삭제하기  2. 이름/연락처로 삭제하기 ");
				 try{
					 int menu=s.nextInt();//둘 중 선택한 값
						s.nextLine();
						if(menu == 1) {
							
							for(int j = 0; j < 1; j++) {
								System.out.print("삭제할 행 :");
							 try{
								 int row = s.nextInt();
									s.nextLine();
									contactlist.delete(row);
							 }
							 
							 catch(InputMismatchException e) { //삭제할 행에 문자 입력시 예외처리
								 System.out.println("숫자가 아닙니다. 다시 입력하세요.");
								 s.nextLine();
								 j--;
								 continue;
							 }
							}
							
							
							}
						else if (menu == 2) {
							System.out.print("삭제할 이름 또는 연락처: ");
							String information = s.next();
							contactlist.delete_information(information);
							
						}
				 }
				 
				 catch(InputMismatchException e) {//삭제 메뉴에 숫자가 아닌 문자 입력시 예외처리
					 System.out.println("숫자가 아닙니다. 다시 입력하세요.");
					 s.nextLine();
					 num--;
					 continue;
				 }
				}
				
			
				
			}//행과 이름/연락처로 삭제
			///////////////
			else if (i==4) {
				contactlist.search();
			}
			else if (i==5) {
				int row1 = 0;
				int row2 = 0;
				
					for(int num = 0; num < 1; num++) {
						System.out.print("순서변경원하는 첫번째행 번호:");
					 try{
						 row1=s.nextInt();//행번호입력
							s.nextLine();
							if(row1>List.get_nodenumber()) {//입력된 연락처보다 큰 수 입력시 오류메시지 출력
								System.out.println("입력이 잘못되었습니다(저장된 연락처수보다 입력된 값이 더 큽니다.)");
								num--;
								 continue;
							}
	
					 }
					 
					 catch(InputMismatchException e) {//행번호에 문자 입력시 예외처리
						 System.out.println("숫자가 아닙니다. 다시 입력하세요.");
						 s.nextLine();
						 num--;
						 continue;
					 }
					 
					}
					//첫번째행에 대한 for문과 예외처리문
					
					for(int j = 0; j < 1; j++) {
					System.out.print("순서변경원하는 두번째행 번호:");
			
				 try{
						
						row2=s.nextInt();//행번호입력
						s.nextLine();
						if(row2>List.get_nodenumber()) {//입력된 연락처보다 큰 수 입력시 오류메시지 출력
							System.out.println("입력이 잘못되었습니다(저장된 연락처수보다 입력된 값이 더 큽니다.)");
							 j--;
							 continue;
						}
						
				 }
				 
				 catch(InputMismatchException e) {//행번호에 문자 입력시 예외처리
					 System.out.println("숫자가 아닙니다. 다시 입력하세요.");
					 s.nextLine();
					 j--;
					 continue;
				 }			 
				
				}//두번째행에 대한 for문과 예외처리문
					contactlist.changedata(row1,row2);//정상적으로 입력되었을 때 연락처 순서 변경 
					System.out.println("순서가 변경되었습니다.");
				
			}
		
		
			else if (i==6) {break;}//끝내기
			else {System.out.println("메뉴번호가 잘못되었습니다. 다시 입력해주세요");}
			
			
			
		}
		
s.close();//scanner클래스 닫아주기
		}
	
}
