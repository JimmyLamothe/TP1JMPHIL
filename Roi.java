public class Roi extends Piece
{
    public Roi(boolean est_blanc, int colonne, int ligne, Echiquier echiquier)
    {
	super(est_blanc, colonne, ligne, echiquier);
    }
    public String representationAscii()
    {
	if (this.estBlanc() == true)
	    {
		return "R";
	    }
	else
	    {
		return "r";
	    }
    }
    public String representationUnicode()
    {
	if (this.estBlanc() == true)
	    {
		return "\u2654";
	    }
	else
	    {
		return "\u265A";
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
		return valide;
	    }
	// Vérifier que la pièce n'a pas déjà été capturée.
	if (this.estCapture() == true)
	    {
		System.out.println(2);
		valide = false;
		return valide;
	    }
	// Vérifier que la nouvelle case est une case valide de l'échiquier.
	if (echiquier.caseValide(nouvelle_colonne, nouvelle_ligne) == false)
	    {
		System.out.println(3);
		valide = false;
		return valide;
	    }
	// Vérifier qu'il n'y a pas une pièce de la même couleur sur la nouvelle case.
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
	// On limite les mouvements de plus d'une case.
	if (Math.abs(diff_ligne) < 1 || Math.abs(diff_colonne) > 1)
	    {
		System.out.println(5);
		valide = false;
		return valide;
	    }
	return valide;
    }
}