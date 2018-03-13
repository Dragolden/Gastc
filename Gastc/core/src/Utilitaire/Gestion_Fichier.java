package Utilitaire;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Gestion_Fichier
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
		
	/* Nothing Here */
		
	//		- Destructeurs -
		
	/* Nothing Here */
		
	// ##############
	// ## METHODES ##
	// ##############
		
	//		- Methodes -
		
	public static String lire(String Source) // Lit le fichier "Source" et renvoit le contenue entier
	{
		String Texte = "";
		String Ligne;
		
		try 
		{
			// Ouvre le fichier en mode logique
			InputStream _LFichier1_ = new FileInputStream(Source);
			InputStreamReader _LFichier2_ = new InputStreamReader(_LFichier1_);
			BufferedReader _LFichier3_ = new BufferedReader(_LFichier2_);
				
			while ((Ligne = _LFichier3_.readLine()) != null) // Lit le fichier ligne par ligne
			{
				Texte += Ligne + "\n"; // le stock dans une variable
			}
			Texte = Texte.substring(0, Texte.length()-1); // Enlève le dernier saut à la ligne
			
			_LFichier3_.close(); // Ferme le fichier
			
		} catch (Exception Exception1) { System.out.println(Exception1.toString());}
		
		return Texte;
	}
	
	public static void ecrire(String Source, String Texte) // Ecrit dans le fichier "Source" le texte "Texte"
	{
		try 
		{
			// Ouvre le fichier en mode logique
			FileWriter _EFichier1_ = new FileWriter(Source);
			BufferedWriter _EFichier2_ = new BufferedWriter(_EFichier1_);
			PrintWriter _EFichier3_ = new PrintWriter(_EFichier2_);
				
			_EFichier3_.println(Texte.replaceAll("(\r\n|\n|\r)", "\r\n")); // Ecrit dans le fichier
			
			_EFichier3_.close(); // Ferme le fichier
			
		} catch (Exception Exception1) { System.out.println(Exception1.toString());}
	}
		
	//		- Accesseurs -
		
	//	|Getter
		
	/* Nothing Here */
		
	//	|Setter
		
	/* Nothing Here */

}
