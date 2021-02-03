package chess;

abstract class Unit {
	protected int maxhealth;
	protected int x,y;//��ġ ��ǥ
	protected int demege,healing;
	public int health, player;//���� �Ƿ� �������� //p1 =1 , p2 =2
	

	abstract boolean movelimit(int gox, int goy);
	abstract boolean heallimit(Unit obj);
	abstract boolean attacklimit(Unit obj);

	public boolean attack(Unit obj) {	
		if (attacklimit(obj)) {
			obj.health= obj.health-this.demege;
			
			if(obj.health <= 0)	{
				Gameplay.map[obj.y][obj.x]=null;
			}
			return true;
		}
		else 
			return false;	
	}
	
	public boolean move(int gox, int goy) {
		if(Gameplay.map[goy][gox]!=null)
			return false;
		if(movelimit(gox,goy)) {
			Gameplay.map[this.y][this.x]=null;	//UI���� ���� �̵� ǥ���� ���� ����
			this.x=gox;
			this.y=goy;
			Gameplay.map[goy][gox]=this;		//UI���� ���� �̵� ǥ���� ���� ����
				return true;
		}
		else 
			return false;
	}
	public boolean heal(Unit obj) {
		if (heallimit(obj)&&this.healing!=0) {
			obj.health= obj.health+this.healing;
		if(obj.maxhealth<obj.health)
			obj.health=obj.maxhealth;
			return true;
		}
		else 
			return false;
	}

}




//�ú� ���ݷ� 3 ü��12 
class Arrow extends Unit{
	
	Arrow(int inix, int iniy,int sel){
		player=sel;
		health=12;
		demege=3;
		healing=0;
		maxhealth=health;
		x=inix;
		y=iniy;
		Gameplay.map[iniy][inix]=this;	//UI���� map���� ����� ���� ����
	}
	
	boolean attacklimit(Unit obj) {
		if (obj.x==x&&obj.y==y)
			return false;
		else if(obj.x==x) {
			if(obj.y-y<=4&&obj.y-y>=-4)
				return true;
					}
		else if(obj.y==y) {
			if(obj.x-x<=4&&obj.x-x>=-4)
				return true;
					}
		return false;
	}

	boolean movelimit(int gox, int goy) {
		 if (gox==x&&goy==y)
			 return false;
		 else if(gox==x) {
			if(goy-y<=2&&goy-y>=-2)
				return true;
					}
		 else if(goy==y) {
			 if(gox-x<=2&&gox-x>=-2)
				return true;
					}

				
		
		return false;
		
	}
	
	boolean heallimit(Unit obj) {
		return false;
	}
}

//���ݷ� 6, ü�� 15
class CKnight extends Unit{
	
	CKnight(int inix,int iniy,int sel){
		player=sel;
		health=15;
		demege=6;
		healing=0;
		maxhealth=health;
		x=inix;
		y=iniy;
		Gameplay.map[iniy][inix]=this;		//UI���� map���� ����� ���� ����
	}
	
	boolean attacklimit(Unit obj) {
		if (obj.x==x&&obj.y==y)
			return false;
		else if((obj.x-x<=1&&obj.x-x>=-1)&&(obj.y-y<=1&&obj.y-y>=-1))
				return true;
		
		return false;
	}
	
	boolean movelimit(int gox, int goy) {
		if (gox==x&&goy==y)
			return false;
		
		else if((gox-x<=3&&gox-x>=-3)&&(goy-y<=3&&goy-y>=-3))
			return true;

		return false;
		
	}
	
	boolean heallimit(Unit obj) {
			return false;
	}


	
}

//���ݷ� 4,ü��24
class ISoldier extends Unit{
	
	ISoldier(int inix, int iniy,int sel){
		player=sel;
		health=24;
		demege=4;
		healing=0;
		maxhealth=health;
		x=inix;
		y=iniy;
		Gameplay.map[iniy][inix]=this; 		//UI���� map���� ����� ���� ����
	}
	
	boolean attacklimit(Unit obj) {
		if (obj.x==x&&obj.y==y)
			return false;
		else if((obj.x-x<=1&&obj.x-x>=-1)&&(obj.y-y<=1&&obj.y-y>=-1))
				return true;
		
		return false;
	}
	
	boolean movelimit(int gox, int goy) {
		if (gox==x&&goy==y)
			return false;
		
		else if((gox-x<=1&&gox-x>=-1)&&(goy-y<=1&&goy-y>=-1))
			return true;

		return false;
		
	}
	
	boolean heallimit(Unit obj) {
			return false;
	}
}

//ü��15, ���ݷ� 0
class King extends Unit{
	
	King(int inix, int iniy,int sel){
		player=sel;
		health=15;
		demege=0;
		healing=0;
		maxhealth=health;
		x=inix;
		y=iniy;
		Gameplay.map[iniy][inix]=this; 		//UI���� map���� ����� ���� ����
	}
	
	boolean attacklimit(Unit obj) {
		return false;
	}
	
	boolean movelimit(int gox, int goy) {
		if (gox==x&&goy==y)
			return false;
		
		else if((gox-x<=1&&gox-x>=-1)&&(goy-y<=1&&goy-y>=-1))
			return true;

		return false;
		
	}
	
	boolean heallimit(Unit obj) {
			return false;
	}
}

//ü�� 10���ݷ�1��4
class Healer extends Unit{
	
	Healer(int inix,int iniy,int sel){
		player=sel;
		health=15;
		demege=0;
		healing=4;
		maxhealth=health;
		x=inix;
		y=iniy;
		Gameplay.map[iniy][inix]=this; 		//UI���� map���� ����� ���� ����
	}
	
	boolean attacklimit(Unit obj) {
		
		return false;
	}
	
	boolean movelimit(int gox, int goy) {
		if (gox==x&&goy==y)
			return false;
		
		else if((gox-x<=3&&gox-x>=-3)&&(goy-y<=3&&goy-y>=-3))
			return true;

		return false;
		
	}
	
	boolean heallimit(Unit obj) {
		if (obj.x==x&&obj.y==y)
			return false;
		else if((obj.x-x<=1&&obj.x-x>=-1)&&(obj.y-y<=1&&obj.y-y>=-1))
				return true;
		
		return false;
	}
}
