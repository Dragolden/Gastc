package __Map__;

import com.gastc_main.game.*;
import __Tile_Object__.*;
import Utilitaire.__List_Object__;

public class __Tile__ {

	// ###############
	// ## INSTANCES ##
	// ###############
	
	//		- Variables d'instances -
	
	// 4 objets différents possibles sur la classe
	protected __Tile_Object__ _Background_; // Une image désignant l'arrière-plan
	// Generalement constant, n'empêche aucun autre objet
	protected __Obstacle__ _Wall_; // Un obstacle
	// Empêche une action
	protected __Unit__ _Actionnist_; // Une unité qui se déplaçera souvent
	// Empêche un Actionner d'une autre équipe
	protected __Entity__ _Actionner_; // Un bâtiment notamment, produisant des unités par exemple
	// Empêche un Actionnist d'une autre équipe
	
	protected boolean _FogOfWar_;
	// Si il y a du brouillard de guerre ou non
	
	//		- Instances de classe -
	
	/* Nothing Here */
	
	// ###################
	// ## CONSTRUCTEURS ##
	// ###################
	
	//		- Constructeurs -
	
	public __Tile__()
	{
		// Initialise avec 4 objets vide:
		_Background_ = null;
		_Wall_ = null;
		_Actionnist_ = null;
		_Actionner_ = null;
	}
	
	//		- Destructeurs -
	
	/* Nothing Here */
	
	// ##############
	// ## METHODES ##
	// ##############
	
	//		- Methodes -
	
	public void initializeObject(String FormeTile) // Place les objets avec un String sous la forme <Background>,<Wall>,<Actionnist>:<Team>,<Actionner>:<Team>
	{
		// Récupère chaque objet individuellement
		String[] ListeObjet = FormeTile.split(",");
		// Actionnist et Actionner
		String[] ListeObjet_Actionnist = ListeObjet[2].split(":");
		String[] ListeObjet_Actionner = ListeObjet[3].split(":");
		
		// Ajout des Entités aux cases:
		if (ListeObjet[0].compareTo("null") != 0)
		{_Background_ = (__Tile_Object__)__List_Object__.takeUnit(ListeObjet[0]);}
		if (ListeObjet[1].compareTo("null") != 0)
		{_Wall_ = (__Obstacle__)__List_Object__.takeUnit(ListeObjet[1]);}
		if (ListeObjet_Actionnist[0].compareTo("null") != 0)
		{_Actionnist_ = (__Unit__)__List_Object__.takeUnit(ListeObjet_Actionnist[0]).clone();
		_Actionnist_.setTeam(Integer.valueOf(ListeObjet_Actionnist[1]));}
		if (ListeObjet_Actionner[0].compareTo("null") != 0)
		{_Actionner_ = (__Entity__)__List_Object__.takeUnit(ListeObjet_Actionner[0]).clone();
		_Actionner_.setTeam(Integer.valueOf(ListeObjet_Actionner[1]));}
		
		// Initialisation du brouillard de guerre
		_FogOfWar_ = false;
	}
	
	public void displayTexture(int PositionX, int PositionY)
	{
		if (_Background_ != null)
		{
			Gastc.display(__List_Object__.takeTextureUnit(_Background_.getName()), PositionX, PositionY);
		}
		if (_Wall_ != null)
		{
			Gastc.display(__List_Object__.takeTextureUnit(_Wall_.getName()), PositionX, PositionY);
		}
		if (!_FogOfWar_) // Sans brouillard de guerre
		{
			if (_Actionner_ != null)
			{
				Gastc.display(__List_Object__.takeTeamTexture(_Actionner_.getTeam()), PositionX+3, PositionY+3);
				Gastc.display(__List_Object__.takeTextureUnit(_Actionner_.getName()), PositionX+4, PositionY+4);
			}
			if (_Actionnist_ != null && _Actionner_ == null) // Pour ne pas afficher une unite par dessus un batiment
			{
				Gastc.display(__List_Object__.takeTeamTexture(_Actionnist_.getTeam()), PositionX+3, PositionY+3);
				Gastc.display(__List_Object__.takeTextureUnit(_Actionnist_.getName()), PositionX+4, PositionY+4);
			}
		}
		else // Avec brouillard de guerre
		{
			if (_Actionner_ != null)
			{
				if (_Actionner_.isPreventDefeat()) // affiche que les batiments qui empeche la defaite
				{
					Gastc.display(__List_Object__.takeTeamTexture(_Actionner_.getTeam()), PositionX+3, PositionY+3);
					Gastc.display(__List_Object__.takeTextureUnit(_Actionner_.getName()), PositionX+4, PositionY+4);
				}
			}
			Gastc.displayFog(PositionX, PositionY);
		}
	}
	
	//		- Accesseurs -
	
	//	|Getter
	
	public __Tile_Object__ getBackground()
	{
		return _Background_;
	}
	
	public __Obstacle__ getWall()
	{
		return _Wall_;
	}
	
	public __Unit__ getActionnist()
	{
		return _Actionnist_;
	}
	
	public __Entity__ getActionner()
	{
		return _Actionner_;
	}
	
	public boolean actionnerIsDefenceBuild()
	{
		return (_Actionner_ instanceof __Defence_Build__);
	}
	
	public boolean isInFog()
	{
		return _FogOfWar_;
	}
	
	//	|Setter
	
	public void setBackground(__Tile_Object__ Object)
	{
		_Background_ = Object;
	}
	
	public void setWall(__Obstacle__ Object)
	{
		_Wall_ = Object;
	}
	
	public void setActionnist(__Unit__ Object)
	{
		_Actionnist_ = Object;
	}
	
	public void setActionner(__Entity__ Object)
	{
		_Actionner_ = Object;
	}
	
	public void setFog(boolean Fog)
	{
		_FogOfWar_ = Fog;
	}
	
}
