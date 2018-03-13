package __Map__;

import com.gastc_main.game.Gastc;

import Utilitaire.Gestion_Fichier;

public class __Map__ {

	// ###############
	// ## INSTANCES ##
	// ###############
	
	//		- Variables d'instances -
	
	protected __Tile__[][] _ListeTile_;
	protected __Team__ _ListTeam_;
	
	//		- Instances de classe -
	
	/* Nothing Here */
	
	// ###################
	// ## CONSTRUCTEURS ##
	// ###################
	
	//		- Constructeurs -
	
	public __Map__(String NomFichier)
	{
		String DataMap = Gestion_Fichier.lire("listFiles/maps/" + NomFichier + ".txt");
		String[] ListeLigne = DataMap.split("\n"); // On récupère ligne pas ligne
		
		// On prend les statistiques et on initialise la carte
		String[] StatistiqueString = ListeLigne[0].split(";");
		String[] SizeMapString = StatistiqueString[0].split("x");
		Short[] SizeMap = {Short.valueOf(SizeMapString[0]), Short.valueOf(SizeMapString[1])};
		_ListeTile_ = new __Tile__[SizeMap[1]][SizeMap[0]];
		
		_ListTeam_ = new __Team__(StatistiqueString[1]);
		
		// On recupere chaque case individuellement
		String[][] ListeCase = new String[SizeMap[1]][];
		for (int i=1 ; i<SizeMap[1]+1 ; i++) // la premiere donnee est la taille de la carte
		{
			ListeCase[i-1] = ListeLigne[i].split(";");
		}
		
		// Charge chaque case
		for (int y=0 ; y<SizeMap[1] ; y++)
		{
			for (int x=0 ; x<SizeMap[0] ; x++)
			{
				_ListeTile_[y][x] = new __Tile__();
				_ListeTile_[y][x].initializeObject(ListeCase[y][x]);
			}
		}
	}
	
	//		- Destructeurs -
	
	/* Nothing Here */
	
	// ##############
	// ## METHODES ##
	// ##############
	
	//		- Methodes -
	
	public void displayTexture()
	{
		for (int y=0 ; y<sizeY() ; y++)
		{
			for (int x=0 ; x<sizeX() ; x++)
			{
				_ListeTile_[y][x].displayTexture(x*40+20, 680-y*40-60);
			}
		}
	}
	
	// Attaque d'une unite
	public void actionnistAttack(int PositionAttackerX, int PositionAttackerY, int PositionDefenderX, int PositionDefenderY)
	{
		if (_ListeTile_[PositionDefenderY][PositionDefenderX].getActionner() != null)
		{
				//Attaque:
				_ListeTile_[PositionDefenderY][PositionDefenderX].getActionner().hadDamage(_ListeTile_[PositionAttackerY][PositionAttackerX].getActionnist().getWeapon().getDamage());
				//Puis met le chronometre a jour:
				_ListeTile_[PositionAttackerY][PositionAttackerX].getActionnist().upChronometreForAttackSpeed();
				if (_ListeTile_[PositionDefenderY][PositionDefenderX].getActionner().getLife() == 0)
				{
					if (_ListeTile_[PositionDefenderY][PositionDefenderX].getActionner().isPreventDefeat())
					{
						_ListeTile_[PositionDefenderY][PositionDefenderX].setActionner(null);
						if (lookIfMapFinished())
						{
							Gastc.setActualVision((byte)1);
						}
					}
					else
					{
						_ListeTile_[PositionDefenderY][PositionDefenderX].setActionner(null);
					}
				}
		}
		else // sinon il n'y a qu'une unite
		{
				// Attaque
				_ListeTile_[PositionDefenderY][PositionDefenderX].getActionnist().hadDamage(_ListeTile_[PositionAttackerY][PositionAttackerX].getActionnist().getWeapon().getDamage());
				//Puis met le chronometre a jour:
				_ListeTile_[PositionAttackerY][PositionAttackerX].getActionnist().upChronometreForAttackSpeed();
				if (_ListeTile_[PositionDefenderY][PositionDefenderX].getActionnist().getLife() == 0)
				{
					_ListeTile_[PositionDefenderY][PositionDefenderX].setActionnist(null);
				}
		}
	}
	
	// Attaque d'un batiment
	public void actionnerAttack(int PositionAttackerX, int PositionAttackerY, int PositionDefenderX, int PositionDefenderY)
	{
		if (_ListeTile_[PositionDefenderY][PositionDefenderX].getActionner() != null)
		{
				//Attaque:
				_ListeTile_[PositionDefenderY][PositionDefenderX].getActionner().hadDamage(_ListeTile_[PositionAttackerY][PositionAttackerX].getActionner().getWeapon().getDamage());
				//Puis met le chronometre a jour:
				_ListeTile_[PositionAttackerY][PositionAttackerX].getActionner().upChronometreForAttackSpeed();
				if (_ListeTile_[PositionDefenderY][PositionDefenderX].getActionner().getLife() == 0)
				{
					if (_ListeTile_[PositionDefenderY][PositionDefenderX].getActionner().isPreventDefeat())
					{
						_ListeTile_[PositionDefenderY][PositionDefenderX].setActionner(null);
						if (lookIfMapFinished())
						{
							Gastc.setActualVision((byte)1);
						}
					}
					else
					{
						_ListeTile_[PositionDefenderY][PositionDefenderX].setActionner(null);
					}
				}
		}
		else // sinon il n'y a qu'une unite
		{
				// Attaque
				_ListeTile_[PositionDefenderY][PositionDefenderX].getActionnist().hadDamage(_ListeTile_[PositionAttackerY][PositionAttackerX].getActionner().getWeapon().getDamage());
				//Puis met le chronometre a jour:
				_ListeTile_[PositionAttackerY][PositionAttackerX].getActionner().upChronometreForAttackSpeed();
				if (_ListeTile_[PositionDefenderY][PositionDefenderX].getActionnist().getLife() == 0)
				{
					_ListeTile_[PositionDefenderY][PositionDefenderX].setActionnist(null);
				}
		}
	}
	
	//Mouvement vers une case
	public void moveTeleport(int PositionX, int PositionY, int PositionMoveX, int PositionMoveY)
	{
		// Met le chronometre a jour:
		_ListeTile_[PositionY][PositionX].getActionnist().upChronometreForSpeed();
		// On deplace l'unite:
		_ListeTile_[PositionMoveY][PositionMoveX].setActionnist(_ListeTile_[PositionY][PositionX].getActionnist());
		// On l'enleve de sa case d'origine:
		_ListeTile_[PositionY][PositionX].setActionnist(null);
	}
	
	//Mouvement vers le haut
	public void moveUp(int PositionX, int PositionY)
	{
		// Met le chronometre a jour:
		_ListeTile_[PositionY][PositionX].getActionnist().upChronometreForSpeed();
		// On deplace l'unite:
		_ListeTile_[PositionY-1][PositionX].setActionnist(_ListeTile_[PositionY][PositionX].getActionnist());
		// On l'enleve de sa case d'origine:
		_ListeTile_[PositionY][PositionX].setActionnist(null);
	}
	
	//Mouvement vers le bas
	public void moveDown(int PositionX, int PositionY)
	{
		// Met le chronometre a jour:
		_ListeTile_[PositionY][PositionX].getActionnist().upChronometreForSpeed();
		// On deplace l'unite:
		_ListeTile_[PositionY+1][PositionX].setActionnist(_ListeTile_[PositionY][PositionX].getActionnist());
		// On l'enleve de sa case d'origine:
		_ListeTile_[PositionY][PositionX].setActionnist(null);
	}
		
	//Mouvement vers la gauche
	public void moveLeft(int PositionX, int PositionY)
	{
		// Met le chronometre a jour:
		_ListeTile_[PositionY][PositionX].getActionnist().upChronometreForSpeed();
		// On deplace l'unite:
		_ListeTile_[PositionY][PositionX-1].setActionnist(_ListeTile_[PositionY][PositionX].getActionnist());
		// On l'enleve de sa case d'origine:
		_ListeTile_[PositionY][PositionX].setActionnist(null);
	}
		
	//Mouvement vers la droite
	public void moveRight(int PositionX, int PositionY)
	{
		// Met le chronometre a jour:
		_ListeTile_[PositionY][PositionX].getActionnist().upChronometreForSpeed();
		// On deplace l'unite:
		_ListeTile_[PositionY][PositionX+1].setActionnist(_ListeTile_[PositionY][PositionX].getActionnist());
		// On l'enleve de sa case d'origine:
		_ListeTile_[PositionY][PositionX].setActionnist(null);
	}
	
	//Chercher l'unite ennemie la plus proche de la position (pour une unite)
	public int[] searchActionnistClosestUnit(int PositionX, int PositionY)
	{
		int[] ClosestPosition = {sizeX()*2, sizeY()*2}; // Initialise a la valeur la plus haute
		
		for (int l=0 ; l<sizeY() ; l++)
		{
			for (int i=0 ; i<sizeX() ; i++)
			{
				if (!_ListeTile_[l][i].isInFog() || (_ListeTile_[l][i].getActionner() != null && _ListeTile_[l][i].getActionner().isPreventDefeat()))
				{
					if (_ListeTile_[l][i].getActionner() != null)
					{
						if (l != PositionY || i != PositionX)
						{
							if (!_ListTeam_.areAlly(_ListeTile_[PositionY][PositionX].getActionnist().getTeam(), _ListeTile_[l][i].getActionner().getTeam()))
							{
								if (Math.abs(PositionY-l)+Math.abs(PositionX-i) < Math.abs(PositionY-ClosestPosition[1])+Math.abs(PositionX-ClosestPosition[0]))
								{
									ClosestPosition[0] = i;
									ClosestPosition[1] = l;
								}
							}
						}
					}
					else if (_ListeTile_[l][i].getActionnist() != null)
					{
						if (l != PositionY || i != PositionX)
						{
							if (!_ListTeam_.areAlly(_ListeTile_[PositionY][PositionX].getActionnist().getTeam(), _ListeTile_[l][i].getActionnist().getTeam()))
							{
								if (Math.abs(PositionY-l)+Math.abs(PositionX-i) < Math.abs(PositionY-ClosestPosition[1])+Math.abs(PositionX-ClosestPosition[0]))
								{
									ClosestPosition[0] = i;
									ClosestPosition[1] = l;
								}
							}
						}
					}
				}
			}
		}
		
		if (ClosestPosition[0] == sizeX()*2 && ClosestPosition[1] == sizeY()*2) // plus d'ennemis
		{
			ClosestPosition[0] = PositionX;
			ClosestPosition[1] = PositionY;
		}
		return ClosestPosition;
	}
	
	//Chercher l'unite ennemie la plus proche de la position (pour un batiment)
	public int[] searchActionnerClosestUnit(int PositionX, int PositionY)
	{
		int[] ClosestPosition = {sizeX()*2, sizeY()*2}; // Initialise a la valeur la plus haute
		
		for (int l=0 ; l<sizeY() ; l++)
		{
			for (int i=0 ; i<sizeX() ; i++)
			{
				if (!_ListeTile_[l][i].isInFog() || (_ListeTile_[l][i].getActionner() != null && _ListeTile_[l][i].getActionner().isPreventDefeat()))
				{
					if (_ListeTile_[l][i].getActionner() != null)
					{
						if (l != PositionY || i != PositionX)
						{
							if (!_ListTeam_.areAlly(_ListeTile_[PositionY][PositionX].getActionner().getTeam(), _ListeTile_[l][i].getActionner().getTeam()))
							{
								if (Math.abs(PositionY-l)+Math.abs(PositionX-i) < Math.abs(PositionY-ClosestPosition[1])+Math.abs(PositionX-ClosestPosition[0]))
								{
									ClosestPosition[0] = i;
									ClosestPosition[1] = l;
								}
							}
						}
					}
					else if (_ListeTile_[l][i].getActionnist() != null)
					{
						if (l != PositionY || i != PositionX)
						{
							if (!_ListTeam_.areAlly(_ListeTile_[PositionY][PositionX].getActionner().getTeam(), _ListeTile_[l][i].getActionnist().getTeam()))
							{
								if (Math.abs(PositionY-l)+Math.abs(PositionX-i) < Math.abs(PositionY-ClosestPosition[1])+Math.abs(PositionX-ClosestPosition[0]))
								{
									ClosestPosition[0] = i;
									ClosestPosition[1] = l;
								}
							}
						}
					}
				}
			}
		}
		
		if (ClosestPosition[0] == sizeX()*2 && ClosestPosition[1] == sizeY()*2) // plus d'ennemis
		{
			ClosestPosition[0] = PositionX;
			ClosestPosition[1] = PositionY;
		}
		return ClosestPosition;
	}
	
	public void offensiveMove(int PositionUnitX, int PositionUnitY)
	{
		int[] PositionEnnemi = searchActionnistClosestUnit(PositionUnitX, PositionUnitY);
		
		if (PositionEnnemi[0] != PositionUnitX || PositionEnnemi[1] != PositionUnitY) // plus d'ennemis sinon
		{
			short Distance = (short)(Math.abs(PositionUnitY-PositionEnnemi[1])+Math.abs(PositionUnitX-PositionEnnemi[0]) -1);
			
			if (Distance < _ListeTile_[PositionUnitY][PositionUnitX].getActionnist().getWeapon().getPortee()) // peut attaquer
			{
				actionnistAttack(PositionUnitX, PositionUnitY, PositionEnnemi[0], PositionEnnemi[1]);
			}
			else // ne peut pas attaquer
			{
				byte Superieur = 1; // y
				if  (Math.abs(PositionEnnemi[0] - PositionUnitX) >= Math.abs(PositionEnnemi[1] - PositionUnitY))
				{
					Superieur = 0; // x
				}
		
		        if (PositionEnnemi[0] >= PositionUnitX)
		        {
		            if (Superieur == 0) //Droite
		            {
		            	if (_ListeTile_[PositionUnitY][PositionUnitX+1].getActionnist() == null && _ListeTile_[PositionUnitY][PositionUnitX+1].getWall() == null)
		            	{
		            		moveRight(PositionUnitX, PositionUnitY);
		            	}
		            }
		            else
		            {
		                if (PositionEnnemi[1] >= PositionUnitY) // Bas
		                {
		                	if (_ListeTile_[PositionUnitY+1][PositionUnitX].getActionnist() == null && _ListeTile_[PositionUnitY+1][PositionUnitX].getWall() == null)
			            	{
		                		moveDown(PositionUnitX, PositionUnitY);
			            	}
		                }
		                else // Haut
		                {
		                	if (_ListeTile_[PositionUnitY-1][PositionUnitX].getActionnist() == null && _ListeTile_[PositionUnitY-1][PositionUnitX].getWall() == null)
			            	{
		                		moveUp(PositionUnitX, PositionUnitY);
			            	}
		                }
		            }
		        }
		        else
		        {
		        	if (Superieur == 0) //Gauche
		            {
		        		if (_ListeTile_[PositionUnitY][PositionUnitX-1].getActionnist() == null && _ListeTile_[PositionUnitY][PositionUnitX-1].getWall() == null)
		            	{
		        			moveLeft(PositionUnitX, PositionUnitY);
		            	}
		            }
		            else
		            {
		                if (PositionEnnemi[1] >= PositionUnitY)
		                {
		                	if (_ListeTile_[PositionUnitY+1][PositionUnitX].getActionnist() == null && _ListeTile_[PositionUnitY+1][PositionUnitX].getWall() == null)
			            	{
		                		moveDown(PositionUnitX, PositionUnitY); // Bas
			            	}
		                }
		                else
		                {
		                	if (_ListeTile_[PositionUnitY-1][PositionUnitX].getActionnist() == null && _ListeTile_[PositionUnitY-1][PositionUnitX].getWall() == null)
			            	{
		                		moveUp(PositionUnitX, PositionUnitY); // Haut
			            	}
		                }
		            }
		        }
			}
		}
	}
	
	public void buildAttackClosest(int PositionBuildX, int PositionBuildY)
	{
		int[] PositionEnnemi = searchActionnerClosestUnit(PositionBuildX, PositionBuildY);
		
		if (PositionBuildX != PositionEnnemi[0] || PositionBuildY != PositionEnnemi[1]) // Sinon plus d'ennemis
		{
			short Distance = (short)(Math.abs(PositionBuildY-PositionEnnemi[1])+Math.abs(PositionBuildX-PositionEnnemi[0]) -1);
			
			if (Distance < _ListeTile_[PositionBuildY][PositionBuildX].getActionner().getWeapon().getPortee()) // peut attaquer
			{
				actionnerAttack(PositionBuildX, PositionBuildY, PositionEnnemi[0], PositionEnnemi[1]);
			}
		}
	}
	
	public void useOffensiveMove() // Fait bouger offensivement toutes les unites de la carte et fait attaquer les batiments defensifs
	{
		for (int l=0 ; l<sizeY() ; l++)
		{
			for (int i=0 ; i<sizeX() ; i++)
			{
				if (_ListeTile_[l][i].getActionnist() != null) // Deplacement offensif d'une unite
				{
					// On verifie si le chronometre est depasse avec une marge pour le retour a zero:
					if (Gastc.getChronometre() >= _ListeTile_[l][i].getActionnist().getChronometre() && Math.abs(_ListeTile_[l][i].getActionnist().getChronometre() - Gastc.getChronometre()) < 5000)
					{
						offensiveMove(i, l);
					}
				}
				if (_ListeTile_[l][i].getActionner() != null) // Attaque d'un batiment
				{
					if (_ListeTile_[l][i].actionnerIsDefenceBuild())
					{
						if (Gastc.getChronometre() >= _ListeTile_[l][i].getActionner().getChronometre() && Math.abs(_ListeTile_[l][i].getActionner().getChronometre() - Gastc.getChronometre()) < 5000)
						{
							buildAttackClosest(i, l);
						}
					}
				}
			}
		}
	}
	
	public boolean lookIfMapFinished()
	{
		byte OneOfTeams;
		for (int l=0 ; l<sizeY() ; l++)
		{
			for (int i=0 ; i<sizeX() ; i++)
			{
				if (_ListeTile_[l][i].getActionner() != null)
				{
					if (_ListeTile_[l][i].getActionner().isPreventDefeat())
					{
						OneOfTeams = _ListeTile_[l][i].getActionner().getTeam();
						for (int k=l ; k<sizeY() ; k++)
						{
							for (int j=i ; j<sizeX() ; j++)
							{
								if (_ListeTile_[k][j].getActionner() != null)
								{
									if (_ListeTile_[k][j].getActionner().isPreventDefeat())
									{
										if (!_ListTeam_.areAlly(OneOfTeams, _ListeTile_[k][j].getActionner().getTeam()))
										{
											return false; // Il reste au moins deux equipes
										}
									}
								}
							}
						}
						return true; // Il ne reste qu'une equipe
					}
				}
			}
		}
		return true; // Il ne reste aucune equipe
	}
	
	public void activateFog()
	{
		// On remet la carte cachee
		for (int l=0 ; l<sizeY() ; l++) // pour
		{
			for (int i=0 ; i<sizeX() ; i++) // chaque case
			{
				_ListeTile_[l][i].setFog(true);
			}
		}

		// On met la vision pour les elements vus par les allies
		short Vision;
		for (int l=0 ; l<sizeY() ; l++) // pour
		{
			for (int i=0 ; i<sizeX() ; i++) // chaque case
			{
				
				if (_ListeTile_[l][i].getActionnist() != null)
				{
					if (_ListeTile_[l][i].getActionnist().getTeam() == 1 || _ListTeam_.areAlly((byte)1, _ListeTile_[l][i].getActionnist().getTeam()))
					{
						Vision = _ListeTile_[l][i].getActionnist().getVision();
						for (int k=-Vision ; k<Vision+1 ; k++)
						{
							for (int j=-Vision ; j<Vision+1 ; j++)
							{
								// Pour ne pas se retrouver hors de l'ecran
								if (l+k >= 0 && l+k < sizeY() && (i+j >= 0 && i+j < sizeX()))
								{
									// On enleve les coins
									if (!((k==-Vision && j==-Vision) || (k==Vision && j==-Vision) || (k==-Vision && j==Vision) || (k==Vision && j==Vision)))
									{
										_ListeTile_[l+k][i+j].setFog(false);
									}
								}
							}
						}
					}
				}
				if (_ListeTile_[l][i].getActionner() != null)
				{
					if (_ListeTile_[l][i].getActionner().getTeam() == 1 || _ListTeam_.areAlly((byte)1, _ListeTile_[l][i].getActionner().getTeam()))
					{
						Vision = _ListeTile_[l][i].getActionner().getVision();
						for (int k=-Vision ; k<Vision+1 ; k++)
						{
							for (int j=-Vision ; j<Vision+1 ; j++)
							{
								// Pour ne pas se retrouver hors de l'ecran
								if (l+k >= 0 && l+k < sizeY() && (i+j >= 0 && i+j < sizeX()))
								{
									// On enleve les coins
									if (!((k==-Vision && j==-Vision) || (k==Vision && j==-Vision) || (k==-Vision && j==Vision) || (k==Vision && j==Vision)))
									{
										_ListeTile_[l+k][i+j].setFog(false);
									}
								}
							}
						}
					}
				}
				
			}
		}
	}
	
	//		- Accesseurs -
	
	//	|Getter
	
	public __Tile__ getTile(int PositionX, int PositionY)
	{
		return _ListeTile_[PositionY][PositionX];
	}
	
	public int sizeX()
	{
		return _ListeTile_[0].length;
	}
	
	public int sizeY()
	{
		return _ListeTile_.length;
	}
	
	//	|Setter
	
	/* Nothing Here */
	
}
