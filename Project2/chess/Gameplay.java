package chess;

import java.util.InputMismatchException;
import java.util.Scanner;


class Gameplay {
	public static Scanner scn=new Scanner(System.in);
	 
	private nation P1,P2;
	
	public static Unit map[][];	
	
	public Gameplay(){
		map=new Unit[11][11];
	}

	private void select_nation() {
		int i;
		System.out.println("Player1�� Nation�� �����Ͻʽÿ�(1:China, 2:America)");
		for(;;) {
			try {
				i=scn.nextInt();
				if(i==1) {
					P1=new china(1); //�����̸� ������ 1�� �ش�
					break;
				}
				else if(i==2) {
					P1=new america(1);	//�����̸� ������ 1�� �ش�
					break;
				}
				else
					System.out.println("1 �Ǵ� 2�� �Է��Ͻʽÿ�");
			}catch(InputMismatchException e){
				System.out.println("���ڸ� �Է��ϼ���. �ٽ� �Է��Ͻʽÿ�.");
				scn.next();
				continue;
			}
			
		}
		
		System.out.println("Player2�� Nation�� �����Ͻʽÿ�(1:China, 2:America)");
		for(;;) {
			try {
				i=scn.nextInt();
				if(i==1) {
					P2=new china(2);	//�����̸� ������ 2�� �ش�
					break;
				}
				else if(i==2) {
					P2=new america(2);	//�����̸� ������ 2�� �ش�
					break;
				}
				else
					System.out.println("1 �Ǵ� 2�� �Է��Ͻʽÿ�");
			}catch(InputMismatchException e){
				System.out.println("���ڸ� �Է��ϼ���. �ٽ� �Է��Ͻʽÿ�.");
				scn.next();
				continue;
			}
		}
	}
	
	
	public void GameStart() {
		select_nation();
		int whoTurn = 1; //p1=1 , p2=2 , Gameover = -1
		while(whoTurn != -1) {
			whoTurn= Turn(whoTurn);	//�Ͽ� ���� player �� �ְ� �� ����� player ����/ �� �ʱ�ȭ�� ���� player�� ���� �� �ٽ� ����.
		}
		

		
	}
	private int Turn(int whoTurn) {
		int menu;
		
		map_print();
		
		System.out.println("Player"+(whoTurn)+" turn");
		System.out.println("1. Move");
		System.out.println("2. Attack or heal(���� ���ý�)");
		System.out.println("3. Change turn");
		
		while(true) {
			try { //����ó�� ����
				
				menu=scn.nextInt();
				break;
				
			}catch(InputMismatchException e){
				System.out.println("���ڸ� �Է��ϼ���. �ٽ� �Է��Ͻʽÿ�.");
				scn.next();
				continue;
			}
		}
		
		int x, y, p, q;
		boolean A;
		String unit1, unit2;
		Unit selUnit;
		
		if(menu == 1){
			for(;;) {
				
				try { //����ó�� ����
					System.out.println("��ǥ�� �Է��Ͻʽÿ�.(���� ���� ��)");
					x = scn.nextInt();
					y = scn.nextInt();   //���� ��ǥ ����
					
					if(map[y][x].player == whoTurn) { //��Ź�� ������ �ڽ��� ������ �´��� Ȯ��.
						selUnit = map[y][x];
						unit1 = selUnit.toString().substring(6,7);   //unit1�� ������ ���� �̸� ����
						break;
					}else {
						System.out.println("�ڽ��� ������ �����Ͻʽÿ�.");
						continue;
					}
					
				}catch(InputMismatchException e){
					System.out.println("���ڸ� �Է��ϼ���. �ٽ� �Է��Ͻʽÿ�.");
					scn.next();
					continue;
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println("�߸��Է��Ͽ����ϴ�. �ٽ� �Է��Ͻʽÿ�.");
					continue;
				}catch(NullPointerException e){
					System.out.println("������ �ִ� ��ǥ�� �Է��ϼ���. �ٽ� �Է��Ͻʽÿ�.");
					continue;
				}
			}
			
			
			System.out.println(selUnit.player+unit1+"�� ���õǾ����ϴ�.");
			for(;;) {
				
				try { //����ó�� ����
					System.out.println("������ ��ǥ�� �Է��Ͻʽÿ�.(���� ���� ��) (�� �ʱ�ȭ -1)");
					p = scn.nextInt();
					if(p==-1) {
						System.out.println("Turn�� �ʱ�ȭ �մϴ�.");
						return whoTurn;	//�� �ʱ�ȭ
					}else {
						q = scn.nextInt();    //�̵��� ��ǥ ����
					}
					
					A = selUnit.move(p, q);
					
					if(A == true) {  //�̵��� ���� ���
						System.out.println(unit1+"�� ("+x+", "+y+") ���� ("+p+", "+q+") �� �̵��Ͽ����ϴ�.");
						break;
					}
					else
						System.out.println("�̵� �Ұ����� �����Դϴ�.�ٽ� �Է��Ͻʽÿ�");
				}catch(InputMismatchException e){
					System.out.println("���ڸ� �Է��ϼ���. �ٽ� �Է��Ͻʽÿ�.");
					scn.next();
					continue;
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println("�߸��Է��Ͽ����ϴ�. �ٽ� �Է��Ͻʽÿ�.");
					continue;
				}
			}
		}
		
		
		else if(menu == 2){
			for(;;) {
				
				try { //����ó�� ����
				
					System.out.println("��ǥ�� �Է��Ͻʽÿ�.(���� ���� ��)");
					x = scn.nextInt();
					y = scn.nextInt();   //���� ��ǥ ����
					
					if(map[y][x].player==whoTurn) {
						selUnit = map[y][x];
						unit1 = selUnit.toString().substring(6,7);   //unit1�� ������ ���� �̸� ���� //null ����ó�� �ɸ��� �κ�.
						break;
					}else {
						System.out.println("�ڽ��� ������ �����Ͻʽÿ�.");
						continue;
					}
			
				}catch(InputMismatchException e){
					System.out.println("���ڸ� �Է��ϼ���. �ٽ� �Է��Ͻʽÿ�.");
					scn.next();
					continue;
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println("�߸��Է��Ͽ����ϴ�. �ٽ� �Է��Ͻʽÿ�.");
					continue;
				}catch(NullPointerException e){
					System.out.println("������ �ִ� ��ǥ�� �Է��ϼ���. �ٽ� �Է��Ͻʽÿ�.");
					continue;
				}
			}
		
			
			System.out.println(selUnit.player+unit1+"�� ���õǾ����ϴ�.");
			for(;;) {
				try { //����ó�� ����
					
					System.out.println("�����ϰų� ġ�� �� ������ ��ǥ�� �Է��Ͻʽÿ�.(���� ���� ��)(�� �ʱ�ȭ -1)");
					p = scn.nextInt();
					if(p==-1) {
						System.out.println("Turn�� �ʱ�ȭ �մϴ�.");
						return whoTurn;	//�� �ʱ�ȭ
					}else {
						q = scn.nextInt();    //���ݶǴ� ġ�� �޴� ��ǥ ����
					}
					
					
					unit2 = map[q][p].toString().substring(6,7);  //���� �Ǵ� ġ�� �޴� ���� �̸� ����
					if(unit1.equals("H")) {//������ ���
						if( map[q][p].player!=map[y][x].player) {
							System.out.println("�ٸ� ���� ġ���� �� �����ϴ�. ġ���� ����� �ٽ� �Է����ֽʽÿ�.");
							continue;
						}
						A = map[y][x].heal(map[q][p]);
						if(A == true) {
							System.out.println(selUnit.player+unit1+"�� "+map[q][p].player+unit2+"�� ġ���Ͽ����ϴ�.(�ִ� ü���� ���� �� �ִ� ü������ �����˴ϴ�.");
							break;
						}
						else
							System.out.println("ġ�� �Ұ����� �����Դϴ�.");
					}
					else{
						if( map[q][p].player==map[y][x].player) {
							System.out.println("�������� ������ �� �����ϴ�. ���ݴ���� �ٽ� �Է����ֽʽÿ�.");
							continue;
						}
						Unit atkobj=map[q][p];
						A = map[y][x].attack(map[q][p]);
						if(A == true) {
							System.out.println(selUnit.player+unit1+"�� "+atkobj.player+unit2+"�� �����Ͽ����ϴ�.");
							break;
						}
						else
							System.out.println("���� �Ұ����� �����Դϴ�.�ٽ� �Է��Ͻʽÿ�");
					}
					
				}catch(InputMismatchException e){
					System.out.println("���ڸ� �Է��ϼ���. �ٽ� �Է��Ͻʽÿ�.");
					scn.next();
					continue;
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println("�߸��Է��Ͽ����ϴ�. �ٽ� �Է��Ͻʽÿ�.");
					continue;
				}catch(NullPointerException e){
					System.out.println("������ �ִ� ��ǥ�� �Է��ϼ���. �ٽ� �Է��Ͻʽÿ�.");
					continue;
				}
			}
			
		}else if(menu == 3) {	//���濡�� �� �ѱ��
			if(whoTurn == 1) {
				return 2;
			}else {
				return 1;
			}
		}else {
			System.out.println("�Է��� �߸��Ǿ����ϴ�.�ٽ� �Է��Ͻʽÿ�.");
			return whoTurn;
		}
			
	
		
		if(P1.king.health<=0) {	//���� ���� �� ���� ����.
			System.out.println("P1�� �����׾����ϴ�. P2�� �¸��Դϴ�.");
			return -1;
		}
		else if(P2.king.health<=0) {
			System.out.println("P2�� �����׾����ϴ�. P1�� �¸��Դϴ�.");
			return -1;
		}else {
			if(whoTurn == 1) {
				return 2;
			}else {
				return 1;
			}
		}
	}
	
	
	private void map_print()  {
		
		System.out.println("        0             1             2             3             4             5              6             7            8             9            10 ");
		System.out.println("_________________________________________________________________________________________________________________________________________________________");
		for (int i=0 ; i<11 ; i++) {
		
			if(i<10) {
				System.out.print(i+" ");
				for(int j=0; j<11; j++) {
					if(Gameplay.map[i][j] == null) {
						System.out.print("|           | ");
					}
					else {					
						System.out.print("|     "+map[i][j].player+map[i][j].toString().substring(6,7)+"    | ");
					}
				}
			}
			else {
				System.out.print(i);
				for(int j=0; j<11; j++) {
					if(Gameplay.map[i][j] == null) {
						System.out.print("|           | ");
					}
					else {
						System.out.print("|     "+map[i][j].player+map[i][j].toString().substring(6,7)+"    | ");
					}
				}
			}
			
			
			System.out.println("");
			System.out.println("_________________________________________________________________________________________________________________________________________________________");
		}
	}
	
	
}

