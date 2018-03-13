package __Tile_Object__;

public class __Obstacle__ extends __Tile_Object__
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
	
	public __Obstacle__(String Name, String Picture, boolean Official)
	{
		super(Name, Picture, Official);
	}
	
	//		- Destructeurs -
	
	/* Nothing Here */
	
	// ##############
	// ## METHODES ##
	// ##############
	
	//		- Methodes -
	
	public __Obstacle__ clone()
	{
		return new __Obstacle__(_Name_, _Picture_, _Official_);
	}
	
	//		- Accesseurs -
	
	//	|Getter
	
	/* Nothing Here */
	
	//	|Setter
	
	/* Nothing Here */
	
}
