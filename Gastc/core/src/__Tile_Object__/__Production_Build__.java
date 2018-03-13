package __Tile_Object__;

public class __Production_Build__ extends __Build__
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
	
	public __Production_Build__(String Name, String Picture, boolean Official, int Life, int Armor, int Vision, int Team, boolean PreventDefeat)
	{
		super(Name, Picture, Official, Life, Armor, Vision, Team, PreventDefeat);
	}
	
	//		- Destructeurs -
	
	/* Nothing Here */
	
	// ##############
	// ## METHODES ##
	// ##############
	
	//		- Methodes -
	
	public __Production_Build__ clone()
	{
		return new __Production_Build__(_Name_, _Picture_, _Official_, _Life_, _Armor_, _Vision_, _Team_, _PreventDefeat_);
	}
	
	//		- Accesseurs -
	
	//	|Getter
	
	/* Nothing Here */
	
	//	|Setter
	
	/* Nothing Here */

}
