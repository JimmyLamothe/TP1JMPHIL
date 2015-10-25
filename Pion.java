public class Pion extends Piece
{
    public Pion(boolean est_blanc, int colonne, int ligne, Echiquier echiquier)
    {
	super(est_blanc, colonne, ligne, echiquier);
    }
    public String representationAscii()
    {
	if (this.estBlanc() == true)
	    {
		return "P";
	    }
	else
	    {
		return "p";
	    }
    }
    public String representationUnicode()
    {
	if (this.estBlanc() == true)
	    {
		return "U+2659";
	    }
	else
	    {
		return "U+265F";
	    }
    }
    public boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne)
    {
	int diff_colonne = nouvelle_colonne - this.getColonne();
	int diff_ligne = nouvelle_ligne - this.getLigne();
	Echiquier echiquier = getEchiquier();
	boolean valide = true;
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

	// D'abord on inverse diff_ligne si la pièce est noire.
	if (this.estNoir())
	    {
		diff_ligne = -diff_ligne;
	    }

	// On limite les mouvements inverses, nuls ou de plus de trois cases.
	if (diff_ligne < 1 || diff_ligne > 2)
	    {
		valide = false;
	    }
	// On limite les mouvements de deux cases hors de la case de départ.
	if (diff_ligne == 2 && this.getLigne() != 1)
	    {
		valide = false;
	    }
	// On limite les mouvements de plus d'une colonne.
	if (diff_colonne > 1 || diff_colonne < 1)
	    {
		valide = false;
	    }
	// On limite les mouvements de une colonne aux captures.
	if (diff_colonne == 1)
	    {
		if(diff_ligne > 1)
		    {
			valide = false;
		    }
		if(echiquier.examinePiece(nouvelle_colonne, nouvelle_ligne) == null)
		    {
			valide = false;
		    }
	    }
	return valide;
    }
}