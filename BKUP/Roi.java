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
	// On vérifie pour le roque blanc.
	if(this.estBlanc() && this.getColonne() == 4 && this.getLigne() == 0 && !this.aBouge())
	{
	    Echiquier chessboard = this.getEchiquier();
	    Piece tour_dame = chessboard.examinePiece(0,0);
	    Piece cavalier_dame = chessboard.examinePiece(1,0);
	    Piece fou_dame = chessboard.examinePiece(2,0);
	    Piece dame = chessboard.examinePiece(3,0);
	    Piece fou_roi = chessboard.examinePiece(5,0);
	    Piece cavalier_roi = chessboard.examinePiece(6,0);
	    Piece tour_roi = chessboard.examinePiece(7,0);
	    // On vérifie pour le roque côté dame.
	    if(nouvelle_colonne == 2 && nouvelle_ligne == 0 && tour_dame != null)
	    {
		if(cavalier_dame == null && fou_dame == null && dame == null &&
		   tour_dame instanceof Tour && !tour_dame.aBouge())
		    {
			System.out.println("Roque dame permis");
			tour_dame.deplace(3,0);
			valide = true;
			return valide;
		    }
	    }
	    // On vérifie pour le roque côté roi.
	    if(nouvelle_colonne == 6 && nouvelle_ligne == 0 && tour_roi != null)
	    {
		if(cavalier_roi == null && fou_roi == null && !tour_roi.aBouge())
		    {
			System.out.println("Roque roi permis");
			tour_roi.deplace(5,0);
			valide = true;
			return valide;
		    }
	    }
	}
	// On vérifie pour le roque noir.
	if(this.estNoir() && this.getColonne() == 4 && this.getLigne() == 7 && !this.aBouge())
	{
	    Echiquier chessboard = this.getEchiquier();
	    Piece tour_dame = chessboard.examinePiece(0,7);
	    Piece cavalier_dame = chessboard.examinePiece(1,7);
	    Piece fou_dame = chessboard.examinePiece(2,7);
	    Piece dame = chessboard.examinePiece(3,7);
	    Piece fou_roi = chessboard.examinePiece(5,7);
	    Piece cavalier_roi = chessboard.examinePiece(6,7);
	    Piece tour_roi = chessboard.examinePiece(7,7);
	    // On vérifie pour le roque côté dame.
	    if(nouvelle_colonne == 2 && nouvelle_ligne == 7 && tour_dame != null)
	    {
		if(cavalier_dame == null && fou_dame == null && dame == null &&
		   tour_dame instanceof Tour && !tour_dame.aBouge())
		    {
			System.out.println("Roque dame permis");
			tour_dame.deplace(3,7);
			valide = true;
			return valide;
		    }
	    }
	    // On vérifie pour le roque côté roi.
	    if(nouvelle_colonne == 6 && nouvelle_ligne == 7 && tour_roi != null)
	    {
		if(cavalier_roi == null && fou_roi == null && !tour_roi.aBouge())
		    {
			System.out.println("Roque roi permis");
			tour_roi.deplace(5,7);
			valide = true;
			return valide;
		    }
	    }
	}
	// On limite les mouvements de plus d'une case.
	if (Math.abs(diff_ligne) > 1 || Math.abs(diff_colonne) > 1)
	    {
		System.out.println(5);
		valide = false;
		return valide;
	    }
	return valide;
    }
}