package chess;

abstract class nation {
	int player;
	
	Healer healer[];
	CKnight knight[];
	ISoldier soldier[];
	King king;
	Arrow arrow[];

	abstract void makeUnit(int sel);
}
 
class china extends nation{
	
	public china(int sel) {		//선턴이면 왼쪽이고 인자를 1로 받음 
		this.player=sel;
		makeUnit(sel);			//후턴이면 오른쪽이고 인자를 2로 받음
	}
	void makeUnit(int sel) {		//1이면 왼쪽 2면 오른쪽
		int xsel = sel;
		if(sel == 1) {
			xsel = 0;		//왼쪽과 오른쪽 유닛 대칭 배치.
		}else {
			xsel = 10;
		}
		int i=0;
		healer=new Healer[2];
		healer[0]=new Healer(Math.abs(xsel-1),2,sel);	//x값만 대칭
		healer[1]=new Healer(Math.abs(xsel-1),8,sel);
		
		knight= new CKnight[4];
		knight[0]=new CKnight(Math.abs(xsel-1),1,sel);
		knight[1]=new CKnight(Math.abs(xsel-1),3,sel);
		knight[2]=new CKnight(Math.abs(xsel-1),7,sel);
		knight[3]=new CKnight(Math.abs(xsel-1),9,sel);
		
		arrow=new Arrow[7];
		arrow[0] = new Arrow(Math.abs(xsel-1),0,sel);
		arrow[1] = new Arrow(xsel,2,sel);
		arrow[2] = new Arrow(xsel,3,sel);
		arrow[3] = new Arrow(Math.abs(xsel-1),5,sel);
		arrow[4] = new Arrow(xsel,7,sel);
		arrow[5] = new Arrow(xsel,8,sel);
		arrow[6] = new Arrow(Math.abs(xsel-1),10,sel);
		
		soldier= new ISoldier[10];
		for(i=0;i<5;i++)
			soldier[i]=new ISoldier(Math.abs(xsel-2),i,sel);
		for(i=0;i<5;i++)
			soldier[i]=new ISoldier(Math.abs(xsel-2),i+6,sel);
		
		king = new King(xsel,5,sel);
	}
}

class america extends nation{
	public america(int sel) {
		makeUnit(sel);
		this.player=sel;
	}
	void makeUnit(int sel) {
		int xsel = sel;
		
		if(sel == 1) {
			xsel = 0;		//왼쪽과 오른쪽 유닛 대칭 배치.
		}else {
			xsel = 10;
		}
		healer=new Healer[4];
		healer[0]=new Healer(Math.abs(xsel-1),1,sel);
		healer[1]=new Healer(Math.abs(xsel-1),3,sel);
		healer[2]=new Healer(Math.abs(xsel-1),7,sel);
		healer[3]=new Healer(Math.abs(xsel-1),9,sel);
		
		knight= new CKnight[8];
		knight[0]=new CKnight(Math.abs(xsel-2),1,sel);
		knight[1]=new CKnight(Math.abs(xsel-2),3,sel);
		knight[2]=new CKnight(Math.abs(xsel-1),4,sel);
		knight[4]=new CKnight(Math.abs(xsel-2),5,sel);
		knight[5]=new CKnight(Math.abs(xsel-1),6,sel);
		knight[6]=new CKnight(Math.abs(xsel-2),7,sel);
		knight[7]=new CKnight(Math.abs(xsel-2),9,sel);
		
		arrow=new Arrow[5];
		arrow[0] = new Arrow(Math.abs(xsel-1),0,sel);
		arrow[1] = new Arrow(Math.abs(xsel-1),2,sel);
		arrow[2] = new Arrow(Math.abs(xsel-1),5,sel);
		arrow[3] = new Arrow(Math.abs(xsel-1),8,sel);
		arrow[4] = new Arrow(Math.abs(xsel-1),10,sel);
	
		soldier= new ISoldier[4];
		soldier[0]=new ISoldier(Math.abs(xsel-2),0,sel);
		soldier[1]=new ISoldier(Math.abs(xsel-2),2,sel);
		soldier[2]=new ISoldier(Math.abs(xsel-2),8,sel);
		soldier[3]=new ISoldier(Math.abs(xsel-2),10,sel);
		
		king = new King(xsel,5,sel);
	}
}

 
 