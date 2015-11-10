/*
 * auteur : Philippe Lavoie et Jean-Michel
 * Devoir 1: JeuEchec
 * 			Cette partie du Devoir 1 contient la fonction main
 * 
 */

import java.io.*;
import java.util.Scanner;
public class JeuEchec
{
    public static boolean testValide(Echiquier e, Piece p, int colonne1, int ligne1,
				     int colonne2, int ligne2, boolean blanc)
    {
	// Piece sur cette case?
	if (p == null)
	{
	    return false;
	}
	// Piece du bon joueur?
	if(p.estBlanc() != blanc)
	{
	    return false;
	}
	if(!p.deplacementValide(colonne2, ligne2))
	{
	    return false;
	}
	return true;
    }
    public static boolean unicode = true;
    public static void main(String[] args)
    {
	boolean finpartie = false;
	boolean tourblanc = true;
	boolean tournoir = false;
	boolean deplacement = false;
	Scanner scan = new Scanner(System.in);	
	//décision sur type affichage
	
	try
	{
	    if(args[0].equals("ascii"))
	    {
		unicode = false;
	    }
	}
	catch(ArrayIndexOutOfBoundsException e2)
	    {
		System.out.println("Defaulting to Unicode, please enter ascii" +
				   " as a command-line argument for ascii display");
		System.out.println();
	    }
	Echiquier echiquier = null;
	echiquier = new Echiquier();
	if(unicode == true)
	{
	    echiquier.afficheUnicode();
	} 
	else 
	{
	    echiquier.afficheAscii();
	}
	//
	//début boucle des joueurs
	//
	while(finpartie == false)
	{
	    //
	    //tour des blanc
	    //
	    while(tourblanc==true)
	    {	
		System.out.println("Joueur blanc?");
		String moveblanc = scan.nextLine();
		
		try
		{
		    boolean valide = true;
		    // converti la colonne en chiffre
		    int colonne1 = 10;
		    switch(moveblanc.substring(0,1))
			{
			case "a" : 
			    colonne1 =0;
			    break;
			case "b" :  
			    colonne1 =1;
			    break;
			case "c" :  
			    colonne1 =2;
			    break;
			case "d" :  
			    colonne1 =3;
			    break;
			case "e" :  
			    colonne1 =4;
			    break;
			case "f" :  
			    colonne1 =5;
			    break;
			case "g" :  
			    colonne1 =6;
			    break;
			case "h" :  
			    colonne1 =7;
			    break;
			}
		    //converti les lignes en chiffre
		    int ligne1, ligne2;
		    ligne1 = Integer.parseInt(moveblanc.substring(1,2))-1;
		    //affiche les déplacement possible
		    if(echiquier.caseValide(colonne1,ligne1) && moveblanc.length() < 3)
		    {
		    	echiquier.afficheDeplacement(colonne1,ligne1);
		     deplacement = true;
		    }
		    
		    // converti la colonne2 en chiffre
		    int colonne2 = 10;
		    switch(moveblanc.substring(3,4))
			{
			case "a" :  
			    colonne2 =0;
			    break;
			case "b" :  
			    colonne2 =1;
			    break;
			case "c" :  
			    colonne2 =2;
			    break;
			case "d" :  
			    colonne2 =3;
			    break;
			case "e" :  
			    colonne2 =4;
			    break;
			case "f" :  
			    colonne2 =5;
			    break;
			case "g" :  
			    colonne2 =6;
			    break;
			case "h" :  
			    colonne2 =7;
			    break;
			}
		   //convertie la 2e colonne
		    ligne2 = Integer.parseInt(moveblanc.substring(4,5))-1;
		    //test si le mouvement est valide
		    if(echiquier.caseValide(colonne1,ligne1)&& echiquier.caseValide(colonne2,ligne2))
		    {
		    Piece aDeplacer = echiquier.examinePiece(colonne1, ligne1);
			if (testValide(echiquier, aDeplacer, colonne1, ligne1, colonne2, ligne2, tourblanc))
			{
			    tourblanc = false;
			    tournoir = true;
			    //echiquier.afficheDeplacement(colonne1,ligne1);
			}
			else
			{
			    valide = false;
			    System.out.println("Ce n'est pas un déplacement valide.");
			}
			
		    }
		    else
		    {
			valide = false;
			System.out.println("Ce n'est pas un déplacement valide.");
		    }
		    if (echiquier.examinePiece(colonne2, ligne2) != null && valide)
		    {
			echiquier.capturePiece(colonne2, ligne2);
		    }		    
		    if(valide)
		    {
			(echiquier.examinePiece(colonne1, ligne1)).deplace(colonne2, ligne2);
		    }
		    if(echiquier.examinePiece(colonne2, ligne2) instanceof Pion && ligne2 == 7)
		    {
			System.out.println("Votre pion peut être promu. Rentrez votre choix.");
			System.out.println("D = Dame - T = Tour - F = Fou - C = Cavalier.");
			String promotion = "";
			while(promotion.length() != 1)
			{
			    promotion = scan.nextLine();
			    Piece piece_promue;
			    switch(promotion.toUpperCase())
				{
				case "D":
				    System.out.println("Dame");
				    piece_promue = new Dame(true,colonne2,ligne2,echiquier);
				    echiquier.posePiece(piece_promue, colonne2, ligne2);
				    break;
				case "T":
				    piece_promue = new Tour(true,colonne2,ligne2,echiquier);
				    echiquier.posePiece(piece_promue, colonne2, ligne2);
				    break;
				case "F":
				    piece_promue = new Fou(true,colonne2,ligne2,echiquier);
				    echiquier.posePiece(piece_promue, colonne2, ligne2);
				    break;
				case "C":
				    piece_promue = new Cavalier(true,colonne2,ligne2,echiquier);
				    echiquier.posePiece(piece_promue, colonne2, ligne2);
				    break;
				default:
				    promotion = "";
				    System.out.println("Choix invalide. D = Dame, T = Tour, F = Fou, C = Cavalier.");
				}
			}
		    }
		}
		catch(NumberFormatException e1)
		{
		    System.out.println("Ce n'est pas un déplacement valide.");
		    
		    //e1.printStackTrace();
		}
		catch(StringIndexOutOfBoundsException e2)
		{
			if(deplacement == false){
		    System.out.println("Ce n'est pas un déplacement valide.");
			}
			deplacement = false;
		    //e2.printStackTrace();
		}
		catch(NullPointerException e3)
		{
		   System.out.println("Ce n'est pas un déplacement valide.");	
		}
	    }
	    if(unicode == true)
		{
		    echiquier.afficheUnicode();
		} 
	    else 
		{
		    echiquier.afficheAscii();
		}

	    //
	    //tour des noirs
	    //
	    while(tournoir==true)
	    {	
		System.out.println("Joueur noir?");
		String movenoir = scan.nextLine();
		try
		{
		    boolean valide = true;
		    // converti la colonne en chiffre
		    int colonne1 = 10;
		    switch(movenoir.substring(0,1))
			{
			case "a" : 
			    colonne1 =0;
			    break;
			case "b" :  
			    colonne1 =1;
			    break;
			case "c" :  
			    colonne1 =2;
			    break;
			case "d" :  
			    colonne1 =3;
			    break;
			case "e" :  
			    colonne1 =4;
			    break;
			case "f" :  
			    colonne1 =5;
			    break;
			case "g" :  
			    colonne1 =6;
			    break;
			case "h" :  
			    colonne1 =7;
			    break;
			}
		    //converti les lignes en chiffre
		    int ligne1, ligne2;
		    ligne1 = Integer.parseInt(movenoir.substring(1,2))-1;
		    //affiche les déplacement possible
		    if(echiquier.caseValide(colonne1,ligne1) && movenoir.length() < 3)
		    {
		    	echiquier.afficheDeplacement(colonne1,ligne1);
		     deplacement = true;
		    }
		    // converti la colonne2 en chiffre
		    int colonne2 = 10;
		    switch(movenoir.substring(3,4))
			{
			case "a" :  
			    colonne2 =0;
			    break;
			case "b" :  
			    colonne2 =1;
			    break;
			case "c" :  
			    colonne2 =2;
			    break;
			case "d" :  
			    colonne2 =3;
			    break;
			case "e" :  
			    colonne2 =4;
			    break;
			case "f" :  
			    colonne2 =5;
			    break;
			case "g" :  
			    colonne2 =6;
			    break;
			case "h" :  
			    colonne2 =7;
			    break;
			}
		    //converti les lignes en chiffre
		    ligne2 = Integer.parseInt(movenoir.substring(4,5))-1;
		    
		    //test si le mouvement est valide
		    if(echiquier.caseValide(colonne1,ligne1)&& echiquier.caseValide(colonne2,ligne2))
		    {
		    Piece aDeplacer = echiquier.examinePiece(colonne1, ligne1);
			if (testValide(echiquier, aDeplacer, colonne1, ligne1, colonne2, ligne2, tourblanc))
			{
			    tourblanc = true;
			    tournoir = false;
			}
			else
			{
			    valide = false;
			    System.out.println("Ce n'est pas un déplacement valide.");
			}
		    }
		    else
		    {
			valide = false;
			System.out.println("Ce n'est pas un déplacement valide.");
		    }
		    if (echiquier.examinePiece(colonne2, ligne2) != null)
		    {
			echiquier.capturePiece(colonne2, ligne2);
		    }
		    if(valide)
		    {
			(echiquier.examinePiece(colonne1, ligne1)).deplace(colonne2, ligne2);
		    }		   
		    if(echiquier.examinePiece(colonne2, ligne2) instanceof Pion && ligne2 == 0)
		    {
			System.out.println("Votre pion peut être promu. Rentrez votre choix.");
			System.out.println("D = Dame - T = Tour - F = Fou - C = Cavalier.");
			String promotion = "";
			while(promotion.length() != 1)
			{
			    promotion = scan.nextLine();
			    Piece piece_promue;
			    switch(promotion.toUpperCase())
				{
				case "D":
				    System.out.println("Dame");
				    piece_promue = new Dame(false,colonne2,ligne2,echiquier);
				    echiquier.posePiece(piece_promue, colonne2, ligne2);
				    break;
				case "T":
				    piece_promue = new Tour(false,colonne2,ligne2,echiquier);
				    echiquier.posePiece(piece_promue, colonne2, ligne2);
				    break;
				case "F":
				    piece_promue = new Fou(false,colonne2,ligne2,echiquier);
				    echiquier.posePiece(piece_promue, colonne2, ligne2);
				    break;
				case "C":
				    piece_promue = new Cavalier(false,colonne2,ligne2,echiquier);
				    echiquier.posePiece(piece_promue, colonne2, ligne2);
				    break;
				default:
				    promotion = "";
				    System.out.println("Choix invalide. D = Dame, T = Tour, F = Fou, C = Cavalier.");
				}
			}
		    }
		}
		catch(NumberFormatException e1)
		{
		    System.out.println("Ce n'est pas un déplacement valide.");
		    //e1.printStackTrace();
		}
		catch(StringIndexOutOfBoundsException e2)
		{
			if(deplacement == false){
			    System.out.println("Ce n'est pas un déplacement valide.");
				}
				deplacement = false;
		    //e2.printStackTrace();
		}
		catch(NullPointerException e3)
		{
		   System.out.println("Ce n'est pas un déplacement valide.");	
		}
	    }
	    if(unicode == true)
		{
		    echiquier.afficheUnicode();
		} 
	    else 
		{
		    echiquier.afficheAscii();
		}
	}
    }
}
