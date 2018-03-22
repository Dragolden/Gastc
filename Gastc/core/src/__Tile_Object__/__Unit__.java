package __Tile_Object__;

abstract public class __Unit__ extends __Entity__
{
	
	// ###############
	// ## INSTANCES ##
	// ###############
	
	//		- Variables d'instances -
	
	protected float _Speed_; // vitesse de l'unite quand elle bouge (correspond a _Speed_*30 frames pour bouger)
	// exemple : _Speed_ de 1, l'unite attendra 30 frames pour bouger de nouveau (plus c'est bas, plus l'unite bougera vite)
	
	//		- Instances de classe -
	
	/* Nothing Here */
	
	// ###################
	// ## CONSTRUCTEURS ##
	// ###################
	
	//		- Constructeurs -
	
	public __Unit__(String Name, String Picture, boolean Official, int Life, int Armor, int Vision, int Team, double Speed)
	{
		super(Name, Picture, Official, Life, Armor, Vision, Team);
		_Speed_ = (float)Speed;
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
	
	/* Nothing Here */
	
	//	|Setter
	
	public void upChronometreForSpeed()
	{
		_Chronometre_ = (int) ((_Chronometre_ + (_Speed_*30)) % 10000);
	}
	
}
