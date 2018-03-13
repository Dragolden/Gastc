package __Tile_Object__;

public class __Scout_Unit__ extends __Unit__
{
	
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
	
	public __Scout_Unit__(String Name, String Picture, boolean Official, int Life, int Armor, int Vision, int Team, double Speed)
	{
		super(Name, Picture, Official, Life, Armor, Vision, Team, Speed);
	}
	
	//		- Destructeurs -
	
	/* Nothing Here */
	
	// ##############
	// ## METHODES ##
	// ##############
	
	//		- Methodes -
	
	public __Scout_Unit__ clone()
	{
		return new __Scout_Unit__(_Name_, _Picture_, _Official_, _Life_, _Armor_, _Vision_, _Team_, _Speed_);
	}
	
	//		- Accesseurs -
	
	//	|Getter
	
	/* Nothing Here */
	
	//	|Setter
	
	/* Nothing Here */
	
}
