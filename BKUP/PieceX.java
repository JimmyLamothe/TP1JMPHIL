
public class PieceX extends Piece{

	public PieceX(boolean est_blanc, int colonne, int ligne, Echiquier echiquier) {
		super(est_blanc, colonne, ligne, echiquier);
	
	}

	  public String representationAscii(){
		  return "x";
	  }
	  public String representationUnicode()
	    {
		  return"x";
	    }
}
