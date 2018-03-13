package __Tile_Object__;

import __Weapon__.__Distance_Weapon__;

public class __Defence_Build__ extends __Build__
{
	
	// ###############
	// ## INSTANCES ##
	// ###############
	
	//		- Variables d'instances -
	
	protected __Distance_Weapon__ _Arme_; // une arme tirant des projectiles à distance
	protected float _AttackSpeed_; // vitesse de l'unité quand elle attaque
	
	//		- Instances de classe -
	
	/* Nothing Here */
	
	// ###################
	// ## CONSTRUCTEURS ##
	// ###################
	
	//		- Constructeurs -
	
	public __Defence_Build__(String Name, String Picture, boolean Official, int Life, int Armor, int Vision, int Team, boolean PreventDefeat, double AttackSpeed, __Distance_Weapon__ Arme)
	{
		super(Name, Picture, Official, Life, Armor, Vision, Team, PreventDefeat);
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
	
	public __Defence_Build__ clone()
	{
		return new __Defence_Build__(_Name_, _Picture_, _Official_, _Life_, _Armor_, _Vision_, _Team_, _PreventDefeat_, _AttackSpeed_, _Arme_);
	}
	
	//		- Accesseurs -
	
	//	|Getter
	
	public __Distance_Weapon__ getWeapon()
	{
		return _Arme_;
	}
	
	//	|Setter
	
	public void upChronometreForAttackSpeed()
	{
		_Chronometre_ = (int) ((_Chronometre_ + (_AttackSpeed_*30)) % 1000);
	}

}
