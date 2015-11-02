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
		return "\u2659";
	    }
	else
	    {
		return "\u265F";
	    }
    }
    public boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne)
    {
	int diff_colonne = nouvelle_colonne - this.getColonne();
	int diff_ligne = nouvelle_ligne - this.getLigne();
	Echiquier echiquier = getEchiquier();
	boolean valide = true;
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
	    }
	}
	// D'abord on inverse diff_ligne si la pièce est noire.
	if (this.estNoir())
	    {
		diff_ligne = -diff_ligne;
	    }

	// On limite les mouvements inverses, nuls ou de plus de trois cases.
	if (diff_ligne < 1 || diff_ligne > 2)
	    {
		System.out.println(5);		
		valide = false;
	    }
	// On limite les mouvements de deux cases hors de la case de départ.
	if (diff_ligne == 2 && this.getLigne() != 1)
	    {
		System.out.println(6);
		valide = false;
	    }
	// On limite les mouvements de plus d'une colonne.
	if (diff_colonne > 1 || diff_colonne < -1)
	    {
		System.out.println(7);
		valide = false;
	    }
	// On limite les mouvements de une colonne aux captures.
	if (diff_colonne == 1)
	    {
		if(diff_ligne > 1)
		    {
			System.out.println(8);
			valide = false;
		    }
		if(echiquier.examinePiece(nouvelle_colonne, nouvelle_ligne) == null)
		    {
			System.out.println(9);
			valide = false;
		    }
	    }
	return valide;
    }
}