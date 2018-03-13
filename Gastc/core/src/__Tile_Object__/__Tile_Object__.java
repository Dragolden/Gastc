package __Tile_Object__;

public class __Tile_Object__
{
	
	// ###############
	// ## INSTANCES ##
	// ###############
	
	//		- Variables d'instances -
	
	protected String _Name_; // nom de l'objet
	protected String _Picture_; // Image de l'objet
	protected boolean _Official_; // true: l'objet est un objet de base du jeu et ne peut pas etre supprime/modifie
	protected int _ID_; // id de l'objet, unique à chacun, les premiers se retrouverons dans la liste des caractéristiques de base
	
	//		- Instances de classe -
	
	protected static int _Number_ = 0; // Compte le nombre de __Tile_Object__ total
	
	// ###################
	// ## CONSTRUCTEURS ##
	// ###################
	
	//		- Constructeurs -
	
	public __Tile_Object__(String Name, String Picture, boolean Official)
	{
		_Name_ = Name;
		_Picture_ = Picture;
		_Official_ = Official;
		
		_ID_ = _Number_;
		_Number_++;
	}
	
	//		- Destructeurs -
	
	/* Nothing Here */
	
	// ##############
	// ## METHODES ##
	// ##############
	
	//		- Methodes -
	
	public __Tile_Object__ clone()
	{
		return new __Tile_Object__(_Name_, _Picture_, _Official_);
	}
	
	//		- Accesseurs -
	
	//	|Getter
	
	public String getName()
	{
		return _Name_;
	}
	
	public String getPicture()
	{
		return _Picture_;
	}
	
	//	|Setter
	
	/* Nothing Here */
	
}
