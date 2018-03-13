package __Tile_Object__;

import com.gastc_main.game.Gastc;

import __Weapon__.__Weapon__;

public class __Entity__ extends __Tile_Object__
{
	
	// ###############
	// ## INSTANCES ##
	// ###############
	
	//		- Variables d'instances -
	
	protected int _Life_; // Nombre de point de vie, si < 0 alors l'unité meurt
	protected int _Armor_; // Diminue le nombre de dégâts reçus
	protected short _Vision_; // Nombre de case que l'unité voit
	protected byte _Team_; // Equipe de l'unité
	
	protected int _Chronometre_;
	
	//		- Instances de classe -
	
	/* Nothing Here */
	
	// ###################
	// ## CONSTRUCTEURS ##
	// ###################
	
	//		- Constructeurs -
	
	public __Entity__(String Name, String Picture, boolean Official, int Life, int Armor, int Vision, int Team)
	{
		super(Name, Picture, Official);
		_Life_ = Life;
		_Armor_ = Armor;
		_Vision_ = (short)Vision;
		_Team_ = (byte)Team;
		
		_Chronometre_ = (int) ((Gastc.getChronometre()) + (5 + Math.random()*(11-5)) % 1000); // Bouge entre 5 et 10 frames apres creation
	}
	
	//		- Destructeurs -
	
	/* Nothing Here */
	
	// ##############
	// ## METHODES ##
	// ##############
	
	//		- Methodes -
	
	public void hadDamage(int Damage)
	{
		if (Damage - _Armor_ < 1) // Degats minimum
		{
			_Life_ -= 1;
		}
		else
		{
			_Life_ -= Damage - _Armor_;
		}
		
		if (_Life_ < 0)
		{
			_Life_ = 0;
		}
	}
	
	public __Entity__ clone()
	{
		return new __Entity__(_Name_, _Picture_, _Official_, _Life_, _Armor_, _Vision_, _Team_);
	}
	
	//		- Accesseurs -
	
	//	|Getter
	
	public int getLife()
	{
		return _Life_;
	}
	
	public byte getTeam()
	{
		return _Team_;
	}
	
	public short getVision()
	{
		return _Vision_;
	}
	
	public __Weapon__ getWeapon()
	{
		return null;
	}
	
	public int getChronometre()
	{
		return _Chronometre_;
	}
	
	public boolean isPreventDefeat()
	{
		return false;
	}
	
	//	|Setter
	
	public void setTeam(int Team)
	{
		_Team_ = (byte)Team;
	}
	
	public void upChronometreForAttackSpeed()
	{
		// Aucun effet sur une entite qui n'attaque pas
	}
	
}
