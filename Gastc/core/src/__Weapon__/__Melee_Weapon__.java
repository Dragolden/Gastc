package __Weapon__;

public class __Melee_Weapon__ extends __Weapon__ {

	// ###############
	// ## INSTANCES ##
	// ###############
	
	//		- Variables d'instances -
	
	/* Nothing Here */
	
	//		- Instances de classe -
	
	/* Nothing Here */
	
	// ###################
	// ## CONSTRUCTEURS ##
	// ###################
	
	//		- Constructeurs -
	
	public __Melee_Weapon__(String Name, String Picture, boolean Official, int Damage, int AreaDamage)
	{
		super(Name, Picture, Official, Damage, AreaDamage);
	}
	
	//		- Destructeurs -
	
	/* Nothing Here */
	
	// ##############
	// ## METHODES ##
	// ##############
	
	//		- Methodes -
	
	public __Melee_Weapon__ clone()
	{
		return new __Melee_Weapon__(_Name_, _Picture_, _Official_, _Damage_, _AreaDamage_);
	}
	
	//		- Accesseurs -
	
	//	|Getter
	
	/* Nothing Here */
	
	//	|Setter
	
	/* Nothing Here */
	
}
