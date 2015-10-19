public class Echiquier
{
    // NOTE: À implémenter
    public Echiquier()
    {

    }
    // NOTE: À implémenter.
    public Piece examinePiece(int colonne, int ligne)
    {
	return new Piece(true, 0, 0, new Echiquier());
    }
    // NOTE: À implémenter.
    public Piece prendsPiece(int colonne, int ligne)
    {
	return new Piece(true, 0, 0, new Echiquier());
    }
    // NOTE: À implémenter.
    public void capturePiece(int colonne, int ligne)
    {

    }
    // NOTE: À implémenter.
    public void posePiece(Piece p, int colonne, int ligne)
    {

    }
    // NOTE: À implémenter.
    public boolean caseValide(int colonne, int ligne)
    {
	return true;
    }
}