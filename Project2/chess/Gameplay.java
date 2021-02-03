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
		System.out.println("Player1의 Nation을 선택하십시오(1:China, 2:America)");
		for(;;) {
			try {
				i=scn.nextInt();
				if(i==1) {
					P1=new china(1); //선턴이면 생성자 1로 준다
					break;
				}
				else if(i==2) {
					P1=new america(1);	//선턴이면 생성자 1로 준다
					break;
				}
				else
					System.out.println("1 또는 2를 입력하십시오");
			}catch(InputMismatchException e){
				System.out.println("숫자를 입력하세요. 다시 입력하십시오.");
				scn.next();
				continue;
			}
			
		}
		
		System.out.println("Player2의 Nation을 선택하십시오(1:China, 2:America)");
		for(;;) {
			try {
				i=scn.nextInt();
				if(i==1) {
					P2=new china(2);	//후턴이면 생성자 2로 준다
					break;
				}
				else if(i==2) {
					P2=new america(2);	//후턴이면 생성자 2로 준다
					break;
				}
				else
					System.out.println("1 또는 2를 입력하십시오");
			}catch(InputMismatchException e){
				System.out.println("숫자를 입력하세요. 다시 입력하십시오.");
				scn.next();
				continue;
			}
		}
	}
	
	
	public void GameStart() {
		select_nation();
		int whoTurn = 1; //p1=1 , p2=2 , Gameover = -1
		while(whoTurn != -1) {
			whoTurn= Turn(whoTurn);	//턴에 따른 player 값 주고 턴 종료시 player 저장/ 턴 초기화시 같은 player값 저장 후 다시 실행.
		}
		

		
	}
	private int Turn(int whoTurn) {
		int menu;
		
		map_print();
		
		System.out.println("Player"+(whoTurn)+" turn");
		System.out.println("1. Move");
		System.out.println("2. Attack or heal(힐러 선택시)");
		System.out.println("3. Change turn");
		
		while(true) {
			try { //예외처리 시작
				
				menu=scn.nextInt();
				break;
				
			}catch(InputMismatchException e){
				System.out.println("숫자를 입력하세요. 다시 입력하십시오.");
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
				
				try { //예외처리 시작
					System.out.println("좌표를 입력하십시오.(가로 세로 순)");
					x = scn.nextInt();
					y = scn.nextInt();   //원래 좌표 저장
					
					if(map[y][x].player == whoTurn) { //선탁한 유닛이 자신의 유닛이 맞는지 확인.
						selUnit = map[y][x];
						unit1 = selUnit.toString().substring(6,7);   //unit1에 선택한 유닛 이름 저장
						break;
					}else {
						System.out.println("자신의 유닛을 선택하십시오.");
						continue;
					}
					
				}catch(InputMismatchException e){
					System.out.println("숫자를 입력하세요. 다시 입력하십시오.");
					scn.next();
					continue;
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println("잘못입력하였습니다. 다시 입력하십시오.");
					continue;
				}catch(NullPointerException e){
					System.out.println("유닛이 있는 좌표를 입력하세요. 다시 입력하십시오.");
					continue;
				}
			}
			
			
			System.out.println(selUnit.player+unit1+"이 선택되었습니다.");
			for(;;) {
				
				try { //예외처리 시작
					System.out.println("움직일 좌표를 입력하십시오.(가로 세로 순) (턴 초기화 -1)");
					p = scn.nextInt();
					if(p==-1) {
						System.out.println("Turn을 초기화 합니다.");
						return whoTurn;	//턴 초기화
					}else {
						q = scn.nextInt();    //이동할 좌표 저장
					}
					
					A = selUnit.move(p, q);
					
					if(A == true) {  //이동이 됐을 경우
						System.out.println(unit1+"가 ("+x+", "+y+") 에서 ("+p+", "+q+") 로 이동하였습니다.");
						break;
					}
					else
						System.out.println("이동 불가능한 범위입니다.다시 입력하십시오");
				}catch(InputMismatchException e){
					System.out.println("숫자를 입력하세요. 다시 입력하십시오.");
					scn.next();
					continue;
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println("잘못입력하였습니다. 다시 입력하십시오.");
					continue;
				}
			}
		}
		
		
		else if(menu == 2){
			for(;;) {
				
				try { //예외처리 시작
				
					System.out.println("좌표를 입력하십시오.(가로 세로 순)");
					x = scn.nextInt();
					y = scn.nextInt();   //원래 좌표 저장
					
					if(map[y][x].player==whoTurn) {
						selUnit = map[y][x];
						unit1 = selUnit.toString().substring(6,7);   //unit1에 선택한 유닛 이름 저장 //null 예외처리 걸리는 부분.
						break;
					}else {
						System.out.println("자신의 유닛을 선택하십시오.");
						continue;
					}
			
				}catch(InputMismatchException e){
					System.out.println("숫자를 입력하세요. 다시 입력하십시오.");
					scn.next();
					continue;
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println("잘못입력하였습니다. 다시 입력하십시오.");
					continue;
				}catch(NullPointerException e){
					System.out.println("유닛이 있는 좌표를 입력하세요. 다시 입력하십시오.");
					continue;
				}
			}
		
			
			System.out.println(selUnit.player+unit1+"이 선택되었습니다.");
			for(;;) {
				try { //예외처리 시작
					
					System.out.println("공격하거나 치유 할 가로축 좌표를 입력하십시오.(가로 세로 순)(턴 초기화 -1)");
					p = scn.nextInt();
					if(p==-1) {
						System.out.println("Turn을 초기화 합니다.");
						return whoTurn;	//턴 초기화
					}else {
						q = scn.nextInt();    //공격또는 치유 받는 좌표 저장
					}
					
					
					unit2 = map[q][p].toString().substring(6,7);  //공격 또는 치유 받는 유닛 이름 저장
					if(unit1.equals("H")) {//힐러일 경우
						if( map[q][p].player!=map[y][x].player) {
							System.out.println("다른 팀을 치유할 수 없습니다. 치유할 대상을 다시 입력해주십시오.");
							continue;
						}
						A = map[y][x].heal(map[q][p]);
						if(A == true) {
							System.out.println(selUnit.player+unit1+"가 "+map[q][p].player+unit2+"를 치유하였습니다.(최대 체력이 넘을 시 최대 체력으로 유지됩니다.");
							break;
						}
						else
							System.out.println("치유 불가능한 범위입니다.");
					}
					else{
						if( map[q][p].player==map[y][x].player) {
							System.out.println("같은팀을 공격할 수 없습니다. 공격대상을 다시 입력해주십시오.");
							continue;
						}
						Unit atkobj=map[q][p];
						A = map[y][x].attack(map[q][p]);
						if(A == true) {
							System.out.println(selUnit.player+unit1+"가 "+atkobj.player+unit2+"를 공격하였습니다.");
							break;
						}
						else
							System.out.println("공격 불가능한 범위입니다.다시 입력하십시오");
					}
					
				}catch(InputMismatchException e){
					System.out.println("숫자를 입력하세요. 다시 입력하십시오.");
					scn.next();
					continue;
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println("잘못입력하였습니다. 다시 입력하십시오.");
					continue;
				}catch(NullPointerException e){
					System.out.println("유닛이 있는 좌표를 입력하세요. 다시 입력하십시오.");
					continue;
				}
			}
			
		}else if(menu == 3) {	//상대방에게 턴 넘기기
			if(whoTurn == 1) {
				return 2;
			}else {
				return 1;
			}
		}else {
			System.out.println("입력이 잘못되었습니다.다시 입력하십시오.");
			return whoTurn;
		}
			
	
		
		if(P1.king.health<=0) {	//게임 종료 및 진행 조건.
			System.out.println("P1의 왕이죽었습니다. P2의 승리입니다.");
			return -1;
		}
		else if(P2.king.health<=0) {
			System.out.println("P2의 왕이죽었습니다. P1의 승리입니다.");
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

