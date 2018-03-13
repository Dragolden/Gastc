package __Weapon__;

public class __Distance_Weapon__ extends __Weapon__ {

	// ###############
	// ## INSTANCES ##
	// ###############
	
	//		- Variables d'instances -
	
	protected short _Portee_; // Indique le nombre de case auquel l'unité avec l'arme pourra attaquer
	protected float _ProjectileSpeed_; // Indique la vitesse du projectile (en case/sec)
	protected String _ProjectilePicture_; // Image du projectile (pointé vers le haut)
	
	//		- Instances de classe -
	
	/* Nothing Here */
	
	// ###################
	// ## CONSTRUCTEURS ##
	// ###################
	
	//		- Constructeurs -
	
	public __Distance_Weapon__(String Name, String Picture, boolean Official, int Damage, int AreaDamage, int Portee, double ProjectileSpeed, String ProjectilePicture)
	{
		super(Name, Picture, Official, Damage, AreaDamage);
		_Portee_ = (short)Portee;
		_ProjectileSpeed_ = (float)ProjectileSpeed;
		_ProjectilePicture_ = ProjectilePicture;
	}
	
	//		- Destructeurs -
	
	/* Nothing Here */
	
	// ##############
	// ## METHODES ##
	// ##############
	
	//		- Methodes -
	
	public __Distance_Weapon__ clone()
	{
		return new __Distance_Weapon__(_Name_, _Picture_, _Official_, _Damage_, _AreaDamage_, _Portee_, _ProjectileSpeed_, _ProjectilePicture_);
	}
	
	//		- Accesseurs -
	
	//	|Getter
	
	public short getPortee()
	{
		return _Portee_;
	}
	
	//	|Setter
	
	/* Nothing Here */
	
}
