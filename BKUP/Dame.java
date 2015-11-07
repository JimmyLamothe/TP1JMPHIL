public class Dame extends Piece
{
    public Dame(boolean est_blanc, int colonne, int ligne, Echiquier echiquier)
    {
	super(est_blanc, colonne, ligne, echiquier);
    }
    public String representationAscii()
    {
	if (this.estBlanc() == true)
	    {
		return "D";
	    }
	else
	    {
		return "d";
	    }
    }
    public String representationUnicode()
    {
	if (this.estBlanc() == true)
	    {
		return "\u2655";
	    }
	else
	    {
		return "\u265B";
	    }
    }
    public boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne)
    {
	int diff_colonne = nouvelle_colonne - this.getColonne();
	int diff_ligne = nouvelle_ligne - this.getLigne();
	int ligne = this.getLigne();
	int colonne = this.getColonne();
	Echiquier echiquier = getEchiquier();
        boolean valide = true;
	String direction = "";
	boolean pieceNouvelleCase = false;
	// Infos pour debogage - eliminer dans jeu final.
	System.out.println("colonne1:" + this.getColonne() + " ligne1:" + this.getLigne());
	System.out.println("colonne2:" + nouvelle_colonne + " ligne2:" + nouvelle_ligne);
	System.out.println("diffcolonne:" + diff_colonne + " diffligne:" + diff_ligne);
	if (echiquier.examinePiece(nouvelle_colonne, nouvelle_ligne) != null)
	{
	    pieceNouvelleCase = true;
	}
	// Vérifier que la pièce a bien été déplacée.
	if (diff_colonne == 0 && diff_ligne == 0)
	    {
		System.out.println(1);
		valide = false;
	    }
	// Vérifier que la pièce n'a pas déjà été capturée.
	if (this.estCapture() == true)
	    {
		System.out.println(2);
		valide = false;
	    }
	// Vérifier que la nouvelle case est une case valide de l'échiquier.
	if (echiquier.caseValide(nouvelle_colonne, nouvelle_ligne) == false)
	    {
		System.out.println(3);
		valide = false;
	    }
	// Vérifier qu'il n'y a pas une pièce de la même couleur sur la nouvelle case.
	if (pieceNouvelleCase)
	{
	    if (echiquier.examinePiece(nouvelle_colonne, nouvelle_ligne).estBlanc() ==
		this.estBlanc())
	    {
		System.out.println(4);
		valide = false;
		return valide;
	    }
	}
	// On vérifie que le mouvement se fait sur une colonne, une ligne, ou une diagonale.
	if (diff_ligne != 0 && diff_colonne == 0)
	    {
		direction = "colonne";
	    }
	else if(diff_ligne == 0 && diff_colonne != 0)
	    {
		direction = "ligne";
	    }
	else if (Math.abs(diff_ligne) == Math.abs(diff_colonne))
	    {
		direction = "diagonale";
	    }
	else
	    {
		System.out.println(6);
		valide = false;
		return valide;
	    }
	// On vérifie qu'il n'y a pas de cases occupées avant la nouvelle case.
	// Test de mouvement sur une colonne.
	if(direction == "colonne")
	    {
		if (diff_ligne > 0)
		    {
			for (int i = nouvelle_ligne - 1; i > ligne; i--)
			    {
				if(echiquier.examinePiece(colonne, i) != null)
				    {
					System.out.println(7);
					valide = false;
					return valide;
				    }
			    }
		    }
		else
		    {
			for (int i = nouvelle_ligne + 1; i < ligne; i++)
			    {
				if(echiquier.examinePiece(colonne, i) != null)
				    {
					System.out.println(8);
					valide = false;
					return valide;
				    }
			    }
		    }		
	    }
	// Test de mouvement sur une ligne.
	else if(direction == "ligne")
	    {
		if (diff_colonne > 0)
		    {
			for (int i = nouvelle_colonne - 1; i > colonne; i--)
			    {
				if(echiquier.examinePiece(i, ligne) != null)
				    {
					System.out.println(9);
					valide = false;
					return valide;
				    }
			    }
		    }
		else
		    {
			for (int i = nouvelle_colonne + 1; i < colonne; i++)
			    {
				if(echiquier.examinePiece(i, ligne) != null)
				    {
					System.out.println(10);
					valide = false;
					return valide;
				    }
			    }
		    }		
	    }
	// Test de mouvement sur une diagonale.
	else if(diff_colonne > 0)
	    {
		if (diff_ligne > 0)
		    {
			for (int i = 1; i < diff_ligne + 1; i++)
			    {
				if(echiquier.examinePiece(colonne + i, ligne + i) != null)
				    {
					System.out.println(11);
					valide = false;
					return valide;
				    }
			    }
		    }
		else
		    {
			for (int i = 1; i < Math.abs(diff_ligne) +1; i++)
			    {
				if(echiquier.examinePiece(colonne + i, ligne - i) != null)
				    {
					System.out.println(12);
					valide = false;
					return valide;
				    }
			    }
		    }		
	    }
	else 
	    {
		if (diff_ligne > 0)
		    {
			for (int i = 1; i < diff_ligne; i++)
			    {
				if(echiquier.examinePiece(colonne - i, ligne + i) != null)
				    {
					System.out.println(13);
					valide = false;
					return valide;
				    }
			    }
		    }
		else
		    {
			for (int i = 1; i < Math.abs(diff_ligne); i++)
			    {
				if(echiquier.examinePiece(colonne -i, ligne - i) != null)
				    {
					System.out.println(14);
					valide = false;
					return valide;
				    }
			    }
		    }		
	    }	
	return valide;
    }
}