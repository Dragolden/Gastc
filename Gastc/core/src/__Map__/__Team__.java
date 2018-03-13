package __Map__;

public class __Team__ {
	
	// ###############
	// ## INSTANCES ##
	// ###############
	
	//		- Variables d'instances -
	
	byte[][] _ListTeam_; // chaque index correspond a la liste des joueurs d'une equipe
	
	//		- Instances de classe -
	
	/* Nothing Here */
	
	// ###################
	// ## CONSTRUCTEURS ##
	// ###################
	
	//		- Constructeurs -
	
	public __Team__(String TeamString)
	{
		String[] ListTeamString = TeamString.split(",");
		
		_ListTeam_ = new byte[ListTeamString.length][];
		String[][] ListTeamTemporaire = new String[ListTeamString.length][];
		for (int i=0 ; i<ListTeamString.length ; i++)
		{
			ListTeamTemporaire[i] = ListTeamString[i].split(":");
			_ListTeam_[i] = new byte[ListTeamTemporaire[i].length];
			for (int l=0 ; l<ListTeamTemporaire[i].length ; l++)
			{
				_ListTeam_[i][l] = Byte.valueOf(ListTeamTemporaire[i][l]);
			}
		}
	}
	
	//		- Destructeurs -
	
	/* Nothing Here */
	
	// ##############
	// ## METHODES ##
	// ##############
	
	//		- Methodes -
	
	public boolean areAlly(byte Joueur1, byte Joueur2)
	{
		if (Joueur1 == Joueur2)
		{
			return true;
		}
		
		for (int i=0 ; i<_ListTeam_.length ; i++) // Pour chaque equipe
		{
			for (int l=0 ; l<_ListTeam_[i].length ; l++) // Pour chaque joueur
			{
				if (_ListTeam_[i][l] == Joueur1)
				{
					for (int j=l+1 ; j<_ListTeam_[i].length ; j++)
					{
						if (_ListTeam_[i][j] == Joueur2)
						{
							return true;
						}
					}
					return false;
				}
				else if (_ListTeam_[i][l] == Joueur2)
				{
					for (int j=l+1 ; j<_ListTeam_[i].length ; j++)
					{
						if (_ListTeam_[i][l] == Joueur1)
						{
							return true;
						}
					}
					return false;
				}
			}
		}
		return false;
	}
	
	//		- Accesseurs -
	
	//	|Getter
	
	/* Nothing Here */
	
	//	|Setter
	
	/* Nothing Here */
	
}
