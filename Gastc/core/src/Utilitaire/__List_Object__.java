package Utilitaire;

import __Tile_Object__.*;
import __Weapon__.*;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;

public class __List_Object__
{
	
	protected static ArrayList<__Weapon__> _ListWeapon_ = new ArrayList<__Weapon__>(); // Liste contenant toutes les armes de base
	protected static String[] _IndexWeapon_;
	protected static Texture[] _TextureWeapon_;
	protected static ArrayList<__Tile_Object__> _ListUnit_ = new ArrayList<__Tile_Object__>(); // Liste contenant tous les objets de base
	protected static String[] _IndexUnit_; // Tableau contenant les noms des objets (pour recuperer leur index)
	protected static Texture[] _TextureUnit_;
	
	protected static Texture[] _TextureTeamTile_;
	
	protected static String[] _ListWeaponName_ = {"__Melee_Weapon__", "__Distance_Weapon__"};
	protected static String[] _ListUnitName_ = {"__Tile_Object__", "__Obstacle__", "__Entity__", "__Build__", "__Melee_Unit__", "__Distance_Unit__", "__Worker_Unit__", "__Scout_Unit__", "__Defence_Build__", "__Production_Build__", "__Ressource_Build__", "__Tech_Build__"};
			
	public static void initialiser()
	{
		String ContenuFichier, ContenuFichier2;
		String[] ContenuFichierFragmente, ContenuFichierFragmente2;
		
		// Création de la liste des armes
		for (String l : _ListWeaponName_)
		{
			ContenuFichier = Gestion_Fichier.lire("listFiles/objects/" + l + "/list.txt");
			ContenuFichierFragmente = ContenuFichier.split("\n");
			if (ContenuFichierFragmente[0] != "") // Si le premier element est une chaine vide, alors il n'y a pas d'element
			{
				for (int i=0 ; i<ContenuFichierFragmente.length ; i++)
				{
					ContenuFichier2 = Gestion_Fichier.lire("listFiles/objects/" + l + "/" + ContenuFichierFragmente[i] + ".txt");
					ContenuFichierFragmente2 = ContenuFichier2.split("\n");
					if (l == "__Melee_Weapon__")
					{
						_ListWeapon_.add(new __Melee_Weapon__(ContenuFichierFragmente[i], ContenuFichierFragmente2[0], Boolean.valueOf(ContenuFichierFragmente2[1]), Integer.valueOf(ContenuFichierFragmente2[2]), Integer.valueOf(ContenuFichierFragmente2[3])));
					}
					else // Armes à distance
					{
						_ListWeapon_.add(new __Distance_Weapon__(ContenuFichierFragmente[i], ContenuFichierFragmente2[0], Boolean.valueOf(ContenuFichierFragmente2[1]), Integer.valueOf(ContenuFichierFragmente2[2]), Integer.valueOf(ContenuFichierFragmente2[3]), Integer.valueOf(ContenuFichierFragmente2[4]), Double.valueOf(ContenuFichierFragmente2[5]), ContenuFichierFragmente2[6]));
					}
				}
			}
		}
		_IndexWeapon_ = new String[_ListWeapon_.size()];
		for (int i=0 ; i<_ListWeapon_.size() ; i++)
		{
			_IndexWeapon_[i] = _ListWeapon_.get(i).getName();
		}
		
		sortWeapon(); // Trie les armes
		
		createTextureWeapon(); // Initialise toutes les textures des armes
		
		// Création de la liste des Tile Objects
		for (String l : _ListUnitName_)
		{
			ContenuFichier = Gestion_Fichier.lire("listFiles/objects/" + l + "/list.txt");
			ContenuFichierFragmente = ContenuFichier.split("\n");
			if (ContenuFichierFragmente[0] != "") // Si le premier element est une chaine vide, alors il n'y a pas d'element
			{
				for (int i=0 ; i<ContenuFichierFragmente.length ; i++)
				{
					ContenuFichier2 = Gestion_Fichier.lire("listFiles/objects/" + l + "/" + ContenuFichierFragmente[i] + ".txt");
					ContenuFichierFragmente2 = ContenuFichier2.split("\n");
					if (l == "__Tile_Object__")
					{
						_ListUnit_.add(new __Tile_Object__(ContenuFichierFragmente[i], ContenuFichierFragmente2[0], Boolean.valueOf(ContenuFichierFragmente2[1])));
					}
					else if (l == "__Obstacle__")
					{
						_ListUnit_.add(new __Obstacle__(ContenuFichierFragmente[i], ContenuFichierFragmente2[0], Boolean.valueOf(ContenuFichierFragmente2[1])));
					}
					else if (l == "__Entity__")
					{
						_ListUnit_.add(new __Entity__(ContenuFichierFragmente[i], ContenuFichierFragmente2[0], Boolean.valueOf(ContenuFichierFragmente2[1]), Integer.valueOf(ContenuFichierFragmente2[2]), Integer.valueOf(ContenuFichierFragmente2[3]), Integer.valueOf(ContenuFichierFragmente2[4]), 0));
					}
					else if (l == "__Worker_Unit__")
					{
						_ListUnit_.add(new __Worker_Unit__(ContenuFichierFragmente[i], ContenuFichierFragmente2[0], Boolean.valueOf(ContenuFichierFragmente2[1]), Integer.valueOf(ContenuFichierFragmente2[2]), Integer.valueOf(ContenuFichierFragmente2[3]), Integer.valueOf(ContenuFichierFragmente2[4]), 0, Double.valueOf(ContenuFichierFragmente2[5])));
					}
					else if (l == "__Scout_Unit__")
					{
						_ListUnit_.add(new __Scout_Unit__(ContenuFichierFragmente[i], ContenuFichierFragmente2[0], Boolean.valueOf(ContenuFichierFragmente2[1]), Integer.valueOf(ContenuFichierFragmente2[2]), Integer.valueOf(ContenuFichierFragmente2[3]), Integer.valueOf(ContenuFichierFragmente2[4]), 0, Double.valueOf(ContenuFichierFragmente2[5])));
					}
					else if (l == "__Melee_Unit__")
					{
						_ListUnit_.add(new __Melee_Unit__(ContenuFichierFragmente[i], ContenuFichierFragmente2[0], Boolean.valueOf(ContenuFichierFragmente2[1]), Integer.valueOf(ContenuFichierFragmente2[2]), Integer.valueOf(ContenuFichierFragmente2[3]), Integer.valueOf(ContenuFichierFragmente2[4]), 0, Double.valueOf(ContenuFichierFragmente2[5]), Double.valueOf(ContenuFichierFragmente2[6]), ((__Melee_Weapon__)_ListWeapon_.get(searchWeapon(ContenuFichierFragmente2[7]))).clone()));
					}
					else if (l == "__Distance_Unit__")
					{
						_ListUnit_.add(new __Distance_Unit__(ContenuFichierFragmente[i], ContenuFichierFragmente2[0], Boolean.valueOf(ContenuFichierFragmente2[1]), Integer.valueOf(ContenuFichierFragmente2[2]), Integer.valueOf(ContenuFichierFragmente2[3]), Integer.valueOf(ContenuFichierFragmente2[4]), 0, Double.valueOf(ContenuFichierFragmente2[5]), Double.valueOf(ContenuFichierFragmente2[6]), ((__Distance_Weapon__)_ListWeapon_.get(searchWeapon(ContenuFichierFragmente2[7]))).clone()));
					}
					else if (l == "__Build__")
					{
						_ListUnit_.add(new __Build__(ContenuFichierFragmente[i], ContenuFichierFragmente2[0], Boolean.valueOf(ContenuFichierFragmente2[1]), Integer.valueOf(ContenuFichierFragmente2[2]), Integer.valueOf(ContenuFichierFragmente2[3]), Integer.valueOf(ContenuFichierFragmente2[4]), 0, Boolean.valueOf(ContenuFichierFragmente2[5])));
					}
					else if (l == "__Defence_Build__")
					{
						_ListUnit_.add(new __Defence_Build__(ContenuFichierFragmente[i], ContenuFichierFragmente2[0], Boolean.valueOf(ContenuFichierFragmente2[1]), Integer.valueOf(ContenuFichierFragmente2[2]), Integer.valueOf(ContenuFichierFragmente2[3]), Integer.valueOf(ContenuFichierFragmente2[4]), 0, Boolean.valueOf(ContenuFichierFragmente2[5]), Double.valueOf(ContenuFichierFragmente2[6]), ((__Distance_Weapon__)_ListWeapon_.get(searchWeapon(ContenuFichierFragmente2[7]))).clone()));
					}
					else if (l == "__Ressource_Build__")
					{
						_ListUnit_.add(new __Ressource_Build__(ContenuFichierFragmente[i], ContenuFichierFragmente2[0], Boolean.valueOf(ContenuFichierFragmente2[1]), Integer.valueOf(ContenuFichierFragmente2[2]), Integer.valueOf(ContenuFichierFragmente2[3]), Integer.valueOf(ContenuFichierFragmente2[4]), 0, Boolean.valueOf(ContenuFichierFragmente2[5])));
					}
					else if (l == "__Production_Build__")
					{
						_ListUnit_.add(new __Production_Build__(ContenuFichierFragmente[i], ContenuFichierFragmente2[0], Boolean.valueOf(ContenuFichierFragmente2[1]), Integer.valueOf(ContenuFichierFragmente2[2]), Integer.valueOf(ContenuFichierFragmente2[3]), Integer.valueOf(ContenuFichierFragmente2[4]), 0, Boolean.valueOf(ContenuFichierFragmente2[5])));
					}
					else // tech build
					{
						_ListUnit_.add(new __Tech_Build__(ContenuFichierFragmente[i], ContenuFichierFragmente2[0], Boolean.valueOf(ContenuFichierFragmente2[1]), Integer.valueOf(ContenuFichierFragmente2[2]), Integer.valueOf(ContenuFichierFragmente2[3]), Integer.valueOf(ContenuFichierFragmente2[4]), 0, Boolean.valueOf(ContenuFichierFragmente2[5])));
					}
				}
			}
		}
		_IndexUnit_ = new String[_ListUnit_.size()];
		for (int i=0 ; i<_ListUnit_.size() ; i++)
		{
			_IndexUnit_[i] = _ListUnit_.get(i).getName();
		}
		
		sortUnit();
		
		createTextureUnit();
		
		// Initalisation des textures d'equipes :
		
		_TextureTeamTile_ = new Texture[10];
		_TextureTeamTile_[0] = new Texture("pictures/objects/tileTeam/white.png");
		_TextureTeamTile_[1] = new Texture("pictures/objects/tileTeam/orange.png");
		_TextureTeamTile_[2] = new Texture("pictures/objects/tileTeam/green.png");
		_TextureTeamTile_[3] = new Texture("pictures/objects/tileTeam/blue.png");
		_TextureTeamTile_[4] = new Texture("pictures/objects/tileTeam/red.png");
		_TextureTeamTile_[5] = new Texture("pictures/objects/tileTeam/yellow.png");
		_TextureTeamTile_[6] = new Texture("pictures/objects/tileTeam/purple.png");
		_TextureTeamTile_[7] = new Texture("pictures/objects/tileTeam/pink.png");
		_TextureTeamTile_[8] = new Texture("pictures/objects/tileTeam/brown.png");
		_TextureTeamTile_[9] = new Texture("pictures/objects/tileTeam/black.png");
	}
	
	public static void sortWeapon() // Tri des deux listes simultanément
	{
		int l;
		String IntermediaireNom;
		ArrayList<__Weapon__> IntermediaireObjet = new ArrayList<__Weapon__>(1); // Necessaire car on ne connait pas le type

	    for (int i=1 ; i<_IndexWeapon_.length ; i++) // On parcours le tableau
	    {
	        if (_IndexWeapon_[i].compareTo(_IndexWeapon_[i-1]) < 0) // Si deux elements cote-a-cote ne sont pas dans le bon ordre
	        {
	            l = i-1;
	            while (l > 0 && _IndexWeapon_[i].compareTo(_IndexWeapon_[l-1]) < 0) // On parcours en arriere pour mettre l'element superieur a la bonne place
	            {
	                l -= 1;
	            }

	            IntermediaireNom = _IndexWeapon_[i]; // Pour inverser
	            IntermediaireObjet.add(_ListWeapon_.get(i));
	            for (int j=i ; j>l ; j--) //On decale les elements d'une case jusqu'a la case trouvee
	            {
	            	_IndexWeapon_[j] = _IndexWeapon_[j-1];
	            	_ListWeapon_.set(j, _ListWeapon_.get(j-1));
	            }
	            _IndexWeapon_[l] = IntermediaireNom;
	            _ListWeapon_.set(l, IntermediaireObjet.get(0));
	            IntermediaireObjet.remove(0);
	        }
	    }
	}
	
	public static void sortUnit()
	{
		int l;
		String IntermediaireNom;
		ArrayList<__Tile_Object__> IntermediaireObjet = new ArrayList<__Tile_Object__>(1); // Necessaire car on ne connait pas le type

	    for (int i=1 ; i<_IndexUnit_.length ; i++) // On parcours le tableau
	    {  	
	        if (_IndexUnit_[i].compareTo(_IndexUnit_[i-1]) < 0) // Si deux elements cote-a-cote ne sont pas dans le bon ordre
	        {
	            l = i-1;
	            while (l > 0 && _IndexUnit_[i].compareTo(_IndexUnit_[l-1]) < 0) // On parcours en arriere pour mettre l'element superieur a la bonne place
	            {
	                l -= 1;
	            }

	            IntermediaireNom = _IndexUnit_[i]; // Pour inverser
	            IntermediaireObjet.add(_ListUnit_.get(i));
	            for (int j=i ; j>l ; j--) //On decale les elements d'une case jusqu'a la case trouvee
	            {
	            	_IndexUnit_[j] = _IndexUnit_[j-1];
	            	_ListUnit_.set(j, _ListUnit_.get(j-1));
	            }
	            _IndexUnit_[l] = IntermediaireNom;
	            _ListUnit_.set(l, IntermediaireObjet.get(0));
	            IntermediaireObjet.remove(0);
	        }
	    }
	}
	
	public static void createTextureWeapon()
	{
		_TextureWeapon_ = new Texture[_IndexWeapon_.length];
		for (int i=0 ; i<_IndexWeapon_.length ; i++)
		{
			_TextureWeapon_[i] = new Texture("pictures/objects/weapon/" + _ListWeapon_.get(i).getPicture() + ".png");
		}
	}
	
	public static void createTextureUnit()
	{
		_TextureUnit_ = new Texture[_IndexUnit_.length];
		for (int i=0 ; i<_IndexUnit_.length ; i++)
		{
			_TextureUnit_[i] = new Texture("pictures/objects/unit/" + _ListUnit_.get(i).getPicture() + ".png");
		}
	}
	
	public static int searchWeapon(String Weapon) // Recherche dichotomique: nécessite le tableau trié
	{
		int Min = 0, Max = _IndexWeapon_.length;
		int Localisation = (Min+Max)/2;
		int Comparaison;
		while ((Max-Min) >= 0) // Tant qu'il reste des cases
		{
			Comparaison = _IndexWeapon_[Localisation].compareTo(Weapon);
			if (Comparaison == 0) // On a trouvé l'objet
			{
				return Localisation;
			}
			else if (Comparaison > 0) // L'objet est en dessous
			{
				Max = Localisation-1;
			}
			else // L'objet est au dessus
			{
				Min = Localisation+1;
			}
			Localisation = (Min+Max)/2;
		}

		return -1; // Pas trouvé
	}
	
	public static int searchUnit(String Unit) // Recherche dichotomique: nécessite le tableau trié
	{
		int Min = 0, Max = _IndexUnit_.length;
		int Localisation = (Min+Max)/2;
		int Comparaison;
		
		while ((Max-Min) >= 0) // Tant qu'il reste des cases
		{
			// BUG: il semble il y avoir deux erreurs: on cherche un null (erreur ailleurs), et il y a des petites erreurs dans l'ordre des listes

			Comparaison = _IndexUnit_[Localisation].compareTo(Unit);
			if (Comparaison == 0) // On a trouvé l'objet
			{
				return Localisation;
			}
			else if (Comparaison > 0) // L'objet est en dessous
			{
				Max = Localisation-1;
			}
			else // L'objet est au dessus
			{
				Min = Localisation+1;
			}
			Localisation = (Min+Max)/2;
		}
		
		return -1; // Pas trouvé
	}
	
	public static __Weapon__ takeWeapon(String Weapon)
	{
		return _ListWeapon_.get(searchUnit(Weapon));
	}
	
	public static __Tile_Object__ takeUnit(String Unit)
	{
		return _ListUnit_.get(searchUnit(Unit));
	}
	
	public static Texture takeTextureWeapon(String Weapon)
	{
		return _TextureWeapon_[searchUnit(Weapon)];
	}
	
	public static Texture takeTextureUnit(String Unit)
	{
		return _TextureUnit_[searchUnit(Unit)];
	}
	
	public static Texture takeTeamTexture(int Team)
	{
		if (Team < 10)
		{
			return _TextureTeamTile_[Team];
		}
		else
		{
			return _TextureTeamTile_[0]; // toutes les equipes au dessus de 9 sont blanches
		}
	}
}
