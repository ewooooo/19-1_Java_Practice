import java.util.InputMismatchException;
import java.util.Scanner;


class node{
	
	private contact data; //����ƮŸ�� ���۷���
	private node link; //��ũŸ�� ���۷���
	
	void change_contact(contact a) {data=a;} //data�� contactŸ�Է��۷��� a �Ű������� �޾Ƽ�����
	void change_link(node a ) {link =a;}//��ũ�� 
	node get_link() {return link;} //������� get�Լ�����
	contact get_data() {return data;}//������� get�Լ�����
	
	node(contact x){
		data = x;
	} //������ (�⺻������ �����Ƿ� �����ҽ� ����������)
}// ���Ḯ��Ʈ ���


class List{
	private static int nodenumber=0;   //static �޼ҵ忡�� �����ϱ� ���� static���� ����
	private node header;
	
	void change_header(node a){header=a;}
	static int get_nodenumber() {return nodenumber;}  //���� �Լ��� ����� ���� static���� ����
	
	//////////////////�̹� ��ϵ� ��ȣ�� ��� ����ϴ� �����޽��� �߰��߽��ϴ�.
	void insert(node a, String number) {
		a.get_data().change_number(a.get_data().get_number().replace("-", ""));
		String num=a.get_data().get_number();
		node z = Search_Equalnumber(header,num);
		
		if(z == null) {//��ġ�ϴ� ����ó�� ���� ��� ���
		if(header == null) {
			header =a;
			List.nodenumber++;
			System.out.println("�����ϵǾ����ϴ�.");
		}//�ϳ��� �������(ù��°���� ���Խ�)
		
		else {
			node tmp=header;
			while(tmp.get_link()!=null){
				tmp=tmp.get_link();
			}
			tmp.change_link(a);
			List.nodenumber++;
			System.out.println("�����ϵǾ����ϴ�.");
		}//�׿��� ���
		}
		
		else//��ġ�ϴ� ��� �����޽��� ���
			System.out.println("�̹� ��ϵ� ��ȭ��ȣ�Դϴ�.");
		
		
	}//�����Լ�
	
	
	
	/////////////////////
	
	
	
	 void print_allnodes() {
		node a =get_header(); //a�� ������۷��� ����
		if(a==null) {
			 System.out.println("����� ����ó�� �����ϴ�. ");
			 return;
		}
		contact x= a.get_data();//x�� ù��°����� data���۷��� ����
		
		for(int i=1;;i++) {//ù��°������ ���
		 System.out.print(i+".  "+"�̸�:"+x.get_name()+"  ");
		 System.out.print("����:"+x.get_age()+"  ");
		 System.out.println("����ó:"+x.get_number()+"  ");
		 a=a.get_link();
			if(a==null)break;//���� �������������(����Ʈ ���������� link�� null�ΰ��)
			x=a.get_data();
		}
		
		 System.out.println("��� ����߽��ϴ�");
		} // ����� data ���
	 
	void delete(node a) {
		node tmp=header;
		while(tmp.get_link()!=null){ //ù��° ��尡 �ƴ� 
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
	////////////////ù��° ����� ���
	return;
		
		//���� ��尡 �ϳ���� �Ʒ��� �ڵ�
		
		
		
	}//a ��巹�۷����� ���� ���۷��� ������ ���� �Լ�
	

	void delete(int i) {//i��°�� ����
		
		if(i>nodenumber) {
			System.out.println("�Է��� �߸��Ǿ����ϴ�(����� ����ó������ �Էµ� ���� �� Ů�ϴ�.)");  //�������� �ʴ� ��
			return;
						}
		////////////////////////////////////////////
		if(i==1) {//ù��° ���� �������� ��, �޽����� ����ϱ����ؼ� �ڵ� ������ ���� �ٲ���
			contact x= header.get_data();
			System.out.println("["+x.get_name()+" "+x.get_age()+" "+x.get_number()+"] �� �����Ǿ����ϴ�.");  
			header=header.get_link();
			nodenumber--;
		return;
		
		}//ù��°��������
		///////////////////////////////////////////
		node removed=header;
		node tmp=null; //�����Ǵ� ��� link�� ������������ ����
		for(int j=1;j<i;j++){
			tmp=removed;//tmp�� �������³�� �������
			removed=removed.get_link();//removed
			}//i��°���� �����ϱ����� i-1����ŭ ���Ḯ��Ʈ�̵�
		tmp.change_link(removed.get_link());
		nodenumber--;
		contact x= removed.get_data();
		System.out.println("["+x.get_name()+" "+x.get_age()+" "+x.get_number()+"] �� �����Ǿ����ϴ�.");
		return;
	}//������̿��ؼ� ����
	
	/////////////////�̸�, ����ó�� �����ϴ� �Լ�
	void delete_information(String information) {
		information=information.replace("-", "");
		node z = Search_Equalname(header,information);
		node y = Search_Equalnumber(header,information);
		if ((z==null&&y==null))
				System.out.println("�������� �ʴ� ����ó/�̸� �Դϴ�.");  
		
		else if((z!=null&&z.get_data().get_name() != information)){ 
			
			delete(z);
			System.out.println("�����Ǿ����ϴ�.");
		}
		else if((y.get_data().get_number() != information)){ 
			
			delete(y);
			System.out.println("�����Ǿ����ϴ�.");
		}
		else 
			System.out.println("�������� �ʴ� ����ó/�̸� �Դϴ�.");   
		 
	}
	//////////////////////////////////////////////////////
	public node Search_Node(node test_node,String sTest) {  //�˻��� �� ����ϴ� search_node �Լ�
		sTest=sTest.replace("-", "");
		if(test_node==null) {	//�����尡 ������� null ��ȯ
			 return null;
		}else {
			while(test_node != null) { //������������ ������ �˻�
				if(-1 !=test_node.get_data().get_name().indexOf(sTest)  )	//�Է°��� ��尪�� ���� ������ �ִ��� Ȯ��
					return test_node;		//�˻���� �ִ� ��� ��ȯ

				else if(-1 != test_node.get_data().get_number().indexOf(sTest)) 
					return test_node;
				else
					test_node= test_node.get_link();	//������� ����
			}
			return null;	//�˻���� ������ null ��ȯ
		}
	}
	//////////////////////////////////////////////////
	
	public node Search_Equalname(node test_node,String sTest) {  //�˻��� �� ����ϴ� search_node �Լ�
		if(test_node==null) {	//�����尡 ������� null ��ȯ
			 return null;
		}else {
			while(test_node != null) { //������������ ������ �˻�
				if(sTest.equals(test_node.get_data().get_name())  )	//�Է°��� ��尪�� ���� ������ �ִ��� Ȯ��
					
					return test_node;		//�˻���� �ִ� ��� ��ȯ
			
				else
					test_node= test_node.get_link();	//������� ����
			}
			return null;	//�˻���� ������ null ��ȯ
		}
	}
	
	public node Search_Equalnumber(node test_node,String sTest) {  //�˻��� �� ����ϴ� search_node �Լ�
		if(test_node==null) {	//�����尡 ������� null ��ȯ
			 return null;
		}else {
			while(test_node != null) { //������������ ������ �˻�
				if(sTest.equals(test_node.get_data().get_number()) )	//�Է°��� ��尪�� ���� ������ �ִ��� Ȯ��
					return test_node;		//�˻���� �ִ� ��� ��ȯ
				else
					test_node= test_node.get_link();	//������� ����
			}
			return null;	//�˻���� ������ null ��ȯ
		}
	}
	
	
	
	
	
	
	
	public void search() {
		System.out.print("�̸� Ȥ�� ��ȣ �˻�(�Ϻε� ����) : ");		
		String sTtest = ContactMain.s.next();
		sTtest=sTtest.replace("-", "");
		node testNode = header;		//�������� header�� �ʱ�ȭ
		testNode = Search_Node(testNode,sTtest);
		
		if(testNode == null) {//��ġ�ϴ� ��尡 ���� ��� (�˻�� �����ϴ� ����ó�� ���� ���)
			
			System.out.println("�˻����: 0��");   //�˻���� 0�� �޽��� ���      (��ø���� ���� ������ null�� �ɶ����� �˻��� �Ͽ���, �˻� ����� �־ �������� �׻� �˻���� 0�� �޽����� ��µǾ ��ø if �� ����߽��ϴ�)
			return;
		}
		else { // �˻�� �����ϴ� ����ó�� �ִ� ���
			while(true) { //�˻�� �����ϴ� ��� ����ó�� ã�� ������ ���ѹݺ�		
			testNode = Search_Node(testNode,sTtest);	//�˻� �޼ҵ� ȣ��(�˻������ҳ��, �Է¹��ڿ�)
			if(testNode==null) {	//���̻� �˻�� �������� �ʴ� ���
				return;
			}
			else {
				contact x= testNode.get_data();			//���� �� ��� �ϽŰſ��� �����Ծ�� ����
				System.out.print("�̸�:"+x.get_name()+"  ");
				System.out.print("����:"+x.get_age()+"  ");
				System.out.println("����ó:"+x.get_number()+"  ");
			}
			testNode= testNode.get_link();	//�߰��� �����ϴ� ��� �ִ��� Ȯ���ϱ� ���� ������ �������� �ٽ� �˻�.
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
	}//�൥���ͺ���
	
	
	
	
	
	
	node get_header() {return header;}	
	
	

}//���Ḯ��Ʈ ��� �� �޼ҵ� ����



class contact{
	private String name;
	private int age;
	////
	private	String number;
	//////
	String get_name(){return name;}
	String get_number(){return number;}
	int get_age() {return age;}//get�Լ� ����
	
	void change_name(String s) {name=s;}
	void change_age(int i) {age=i;}
	void change_number(String s) {number=s;} //�����Ұ�� 
	
	
	contact(int i){
	
		 System.out.println("�̸� �Է��Ͻʽÿ�.");	 
		// System.out.print("\n");
		name=ContactMain.s.nextLine();
		
		
		/////////
		for(int num = 0; num < 1; num++) {
			 System.out.print("���̸� �Է��Ͻʽÿ�.\n");
		 try{
			 age=ContactMain.s.nextInt();
			 ContactMain.s.nextLine();
		 }
		 
		 catch(InputMismatchException e) {
			 System.out.println("���ڰ� �ƴմϴ�. �ٽ� �Է��ϼ���.");
			 ContactMain.s.nextLine();
			 num--;
			 continue;
		 }
		}
		////////////���̿� ���ڸ� �Է����� ��� ����ó�� �߻�
		 System.out.print("����ó�� �Է��Ͻʽÿ�.");
		 System.out.print("\n");
		number=ContactMain.s.nextLine();
	
	}// ����ڰ� ����ó���� �Է��ϴ°�� ���� ������ ���� �Ű������� �������༭ ���    ��)contact(1)  <-�����Լ����� �̷��� �����
	contact(){}//�⺻������ �������
	
}






public class ContactMain { //�����Լ����� Ŭ����
	static Scanner s=new Scanner(System.in); //scanner ������ ��������ʱ����� static���� ���� �ٸ� Ŭ�������� ���� ContactMain.s�� ���
	
	
	static void print_menu() {
		System.out.println("##########����ó����##########");
		System.out.println("1.����ó ���");
		System.out.println("2.����ó �Է�");
		System.out.println("3.����ó ����");
		System.out.println("4.����ó�� �˻�");
		System.out.println("5.����ó ���� ����");
		System.out.println("6.������");
		System.out.println("##########################");
	}//�޴�����ϴ� �Լ� ���� 
	
	
	public static void main(String[] args) {
		List contactlist=new List();
		
		
		int i=0;
		
		for(;;) {//����ڰ� �����Ҷ����� ���ѹݺ�(break����Ű�������)
		
			print_menu();//�޴����
			
			
			
			///////////�޴��� ���ڸ� �Է����� ��� ����ó��
			for(int num = 0; num < 1; num++) {
				System.out.print("���ϴ� �޴���ȣ:");
			 try{
				 i=s.nextInt();
					s.nextLine();
			 }
			 
			 catch(InputMismatchException e) {
				 System.out.println("���ڰ� �ƴմϴ�. �ٽ� �Է��ϼ���.");
				 s.nextLine();
				 num--;
				 continue;
			 }
			}
		///////////////////////
			
			
			if(i==1) {
				contactlist.print_allnodes();
			}//����� ���
			else if (i==2) {
				contact x=new contact(1);
				node y= new node(x);
				contactlist.insert(y, x.get_number());	///�̹� ��ϵ� ����ó���� �˻��ϱ����� number ���� �Ѱ���
				
			}//�����Լ�
			
			
			/////////////
			else if (i==3) {
				
				for(int num = 0; num < 1; num++) {
					System.out.print("1. �� ��ȣ�� �����ϱ�  2. �̸�/����ó�� �����ϱ� ");
				 try{
					 int menu=s.nextInt();//�� �� ������ ��
						s.nextLine();
						if(menu == 1) {
							
							for(int j = 0; j < 1; j++) {
								System.out.print("������ �� :");
							 try{
								 int row = s.nextInt();
									s.nextLine();
									contactlist.delete(row);
							 }
							 
							 catch(InputMismatchException e) { //������ �࿡ ���� �Է½� ����ó��
								 System.out.println("���ڰ� �ƴմϴ�. �ٽ� �Է��ϼ���.");
								 s.nextLine();
								 j--;
								 continue;
							 }
							}
							
							
							}
						else if (menu == 2) {
							System.out.print("������ �̸� �Ǵ� ����ó: ");
							String information = s.next();
							contactlist.delete_information(information);
							
						}
				 }
				 
				 catch(InputMismatchException e) {//���� �޴��� ���ڰ� �ƴ� ���� �Է½� ����ó��
					 System.out.println("���ڰ� �ƴմϴ�. �ٽ� �Է��ϼ���.");
					 s.nextLine();
					 num--;
					 continue;
				 }
				}
				
			
				
			}//��� �̸�/����ó�� ����
			///////////////
			else if (i==4) {
				contactlist.search();
			}
			else if (i==5) {
				int row1 = 0;
				int row2 = 0;
				
					for(int num = 0; num < 1; num++) {
						System.out.print("����������ϴ� ù��°�� ��ȣ:");
					 try{
						 row1=s.nextInt();//���ȣ�Է�
							s.nextLine();
							if(row1>List.get_nodenumber()) {//�Էµ� ����ó���� ū �� �Է½� �����޽��� ���
								System.out.println("�Է��� �߸��Ǿ����ϴ�(����� ����ó������ �Էµ� ���� �� Ů�ϴ�.)");
								num--;
								 continue;
							}
	
					 }
					 
					 catch(InputMismatchException e) {//���ȣ�� ���� �Է½� ����ó��
						 System.out.println("���ڰ� �ƴմϴ�. �ٽ� �Է��ϼ���.");
						 s.nextLine();
						 num--;
						 continue;
					 }
					 
					}
					//ù��°�࿡ ���� for���� ����ó����
					
					for(int j = 0; j < 1; j++) {
					System.out.print("����������ϴ� �ι�°�� ��ȣ:");
			
				 try{
						
						row2=s.nextInt();//���ȣ�Է�
						s.nextLine();
						if(row2>List.get_nodenumber()) {//�Էµ� ����ó���� ū �� �Է½� �����޽��� ���
							System.out.println("�Է��� �߸��Ǿ����ϴ�(����� ����ó������ �Էµ� ���� �� Ů�ϴ�.)");
							 j--;
							 continue;
						}
						
				 }
				 
				 catch(InputMismatchException e) {//���ȣ�� ���� �Է½� ����ó��
					 System.out.println("���ڰ� �ƴմϴ�. �ٽ� �Է��ϼ���.");
					 s.nextLine();
					 j--;
					 continue;
				 }			 
				
				}//�ι�°�࿡ ���� for���� ����ó����
					contactlist.changedata(row1,row2);//���������� �ԷµǾ��� �� ����ó ���� ���� 
					System.out.println("������ ����Ǿ����ϴ�.");
				
			}
		
		
			else if (i==6) {break;}//������
			else {System.out.println("�޴���ȣ�� �߸��Ǿ����ϴ�. �ٽ� �Է����ּ���");}
			
			
			
		}
		
s.close();//scannerŬ���� �ݾ��ֱ�
		}
	
}
