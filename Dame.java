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
		return "U+2655";
	    }
	else
	    {
		return "U+265B";
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
	// Vérifier que la pièce a bien été déplacée.
	if (diff_colonne == 0 && diff_ligne == 0)
	    {
		valide = false;
	    }
	// Vérifier que la pièce n'a pas déjà été capturée.
	if (this.estCapture() == true)
	    {
		valide = false;
	    }
	// Vérifier que la nouvelle case est une case valide de l'échiquier.
	if (echiquier.caseValide(nouvelle_colonne, nouvelle_ligne) == false)
	    {
		valide = false;
	    }
	// Vérifier qu'il n'y a pas une pièce de la même couleur sur la nouvelle case.
	// NOTE: Vérifier ce qui arrive avec le retour NULL!
	if (echiquier.examinePiece(nouvelle_colonne, nouvelle_ligne).estBlanc() ==
	    this.estBlanc())
	    {
		valide = false;
	    }
	// On limite les mouvements de plus d'une case.
	if (Math.abs(diff_ligne) < 1 || Math.abs(diff_colonne) > 1)
	    {
		valide = false;
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
		valide = false;
		return valide;
	    }
	// On vérifie qu'il n'y a pas de cases occupées avant la nouvelle case.
	// Test de mouvement sur une colonne.
	if(direction == "colonne")
	    {
		if (diff_ligne > 0)
		    {
			for (int i = nouvelle_ligne; i > ligne; i--)
			    {
				if(echiquier.examinePiece(colonne, i) != null)
				    {
					valide = false;
					return valide;
				    }
			    }
		    }
		else
		    {
			for (int i = nouvelle_ligne; i < ligne; i++)
			    {
				if(echiquier.examinePiece(colonne, i) != null)
				    {
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
			for (int i = nouvelle_colonne; i > colonne; i--)
			    {
				if(echiquier.examinePiece(ligne, i) != null)
				    {
					valide = false;
					return valide;
				    }
			    }
		    }
		else
		    {
			for (int i = nouvelle_colonne; i < colonne; i++)
			    {
				if(echiquier.examinePiece(ligne, i) != null)
				    {
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
			for (int i = 1; i < diff_ligne; i++)
			    {
				if(echiquier.examinePiece(colonne + i, ligne + i) != null)
				    {
					valide = false;
					return valide;
				    }
			    }
		    }
		else
		    {
			for (int i = 1; i < Math.abs(diff_ligne); i++)
			    {
				if(echiquier.examinePiece(colonne + i, ligne - i) != null)
				    {
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
					valide = false;
					return valide;
				    }
			    }
		    }
		else
		    {
			for (int i = 1; i < Math.abs(diff_ligne); i++)
			    {
				if(echiquier.examinePiece(colonne -1, ligne - i) != null)
				    {
					valide = false;
					return valide;
				    }
			    }
		    }		
	    }	
	return valide;
    }
}