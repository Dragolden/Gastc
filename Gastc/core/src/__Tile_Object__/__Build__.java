package __Tile_Object__;

public class __Build__ extends __Entity__
{
	
	// ###############
	// ## INSTANCES ##
	// ###############
	
	//		- Variables d'instances -
	
	protected boolean _PreventDefeat_;
	
	//		- Instances de classe -
	
	/* Nothing Here */
	
	// ###################
	// ## CONSTRUCTEURS ##
	// ###################
	
	//		- Constructeurs -
	
	public __Build__(String Name, String Picture, boolean Official, int Life, int Armor, int Vision, int Team, boolean PreventDefeat)
	{
		super(Name, Picture, Official, Life, Armor, Vision, Team);
		_PreventDefeat_ = PreventDefeat;
	}
	
	//		- Destructeurs -
	
	/* Nothing Here */
	
	// ##############
	// ## METHODES ##
	// ##############
	
	//		- Methodes -
	
	public __Build__ clone()
	{
		return new __Build__(_Name_, _Picture_, _Official_, _Life_, _Armor_, _Vision_, _Team_, _PreventDefeat_);
	}
	
	public boolean isPreventDefeat()
	{
		return _PreventDefeat_;
	}
	
	//		- Accesseurs -
	
	//	|Getter
	
	/* Nothing Here */
	
	//	|Setter
	
	/* Nothing Here */
	
}
