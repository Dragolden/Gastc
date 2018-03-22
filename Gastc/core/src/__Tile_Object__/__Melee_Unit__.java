package __Tile_Object__;

import __Weapon__.__Melee_Weapon__;

public class __Melee_Unit__ extends __Unit__
{
	
	// ###############
	// ## INSTANCES ##
	// ###############
	
	//		- Variables d'instances -
	
	protected __Melee_Weapon__ _Arme_; // une arme pour attaquer une case adjacente
	protected float _AttackSpeed_; // vitesse de l'unité quand elle attaque
	
	//		- Instances de classe -
	
	/* Nothing Here */
	
	// ###################
	// ## CONSTRUCTEURS ##
	// ###################
	
	//		- Constructeurs -
	
	public __Melee_Unit__(String Name, String Picture, boolean Official, int Life, int Armor, int Vision, int Team, double Speed, double AttackSpeed, __Melee_Weapon__ Arme)
	{
		super(Name, Picture, Official, Life, Armor, Vision, Team, Speed);
		_AttackSpeed_ = (float)AttackSpeed;
		_Arme_ = Arme.clone();
	}
	
	//		- Destructeurs -
	
	/* Nothing Here */
	
	// ##############
	// ## METHODES ##
	// ##############
	
	//		- Methodes -
	
	public void attaquer(__Entity__ Cible)
	{
		Cible.hadDamage(_Arme_.getDamage());
	}
	
	public __Melee_Unit__ clone()
	{
		return new __Melee_Unit__(_Name_, _Picture_, _Official_, _Life_, _Armor_, _Vision_, _Team_, _Speed_, _AttackSpeed_, _Arme_);
	}
	
	//		- Accesseurs -
	
	//	|Getter
	
	public __Melee_Weapon__ getWeapon()
	{
		return _Arme_;
	}
	
	//	|Setter
	
	public void upChronometreForAttackSpeed()
	{
		_Chronometre_ = (int) ((_Chronometre_ + (_AttackSpeed_*30)) % 10000);
	}
	
}
