package __Weapon__;

abstract public class __Weapon__ {

	// ###############
	// ## INSTANCES ##
	// ###############
	
	//		- Variables d'instances -
	
	protected String _Name_; // Nom de l'arme
	protected String _Picture_; // Image de l'objet
	protected boolean _Official_; // true: l'objet est un objet de base du jeu et ne peut pas etre supprime/modifie
	protected int _Damage_; // dégats infligés, sans compter défense
    protected byte _AreaDamage_; // zone où les dégâts seront infligés (généralement 1)
	
	//		- Instances de classe -
	
    protected static int _Number_ = 0; // Compte le nombre de __Weapon__ total
	
	// ###################
	// ## CONSTRUCTEURS ##
	// ###################
	
	//		- Constructeurs -
	
	public __Weapon__(String Name, String Picture, boolean Official, int Damage, int AreaDamage)
	{
		_Name_ = Name;
		_Picture_ = Picture;
		_Official_ = Official;
		_Damage_ = Damage;
		_AreaDamage_ = (byte)AreaDamage;
		_Number_++;
	}
	
	//		- Destructeurs -
	
	/* Nothing Here */
	
	// ##############
	// ## METHODES ##
	// ##############
	
	//		- Methodes -
	
	/* Nothing Here */
	
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
	
	public int getDamage()
	{
		return _Damage_;
	}
	
	public short getPortee()
	{
		return 1;
	}
	
	//	|Setter
	
	/* Nothing Here */
	
}
