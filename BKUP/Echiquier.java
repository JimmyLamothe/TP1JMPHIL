/*
 * auteur : Philippe Lavoie et Jean-Michel Laprise
 * Devoir 1: Echiquier
 * 			Cette partie du Devoir1 est l'affichage du jeu 
 * 
 */

public class Echiquier {
    private Piece [][] tableau_de_jeu = new Piece[8][8];
    private Piece [] blancs_captures = new Piece[16];
    private Piece [] noirs_captures = new Piece[16];
    //constructeur
    public Echiquier(){	
	tableau_de_jeu[0][7] = new Tour(false,0,7,this);
	tableau_de_jeu[7][7] = new Tour(false,7,7,this);
	tableau_de_jeu[1][7] = new Cavalier(false,1,7,this);
	tableau_de_jeu[6][7] = new Cavalier(false,6,7,this);
	tableau_de_jeu[2][7] = new Fou(false,2,7,this);
	tableau_de_jeu[5][7] = new Fou(false,5,7,this);
	tableau_de_jeu[3][7] = new Dame(false,3,7,this);
	tableau_de_jeu[4][7] = new Roi(false,4,7,this);
	for(int x=0; x<8;x++){
	    tableau_de_jeu[x][6] = new Pion(false,x,6,this);
	}
	for(int x=2; x<6;x++){
	    for(int y=0; y<8;y++){
		tableau_de_jeu[y][x]=null;
	    }
	}
	for(int x=0; x<8;x++){
	    tableau_de_jeu[x][1] = new Pion(true,x,1,this);
	}
	tableau_de_jeu[0][0] = new Tour(true,0,0,this);
	tableau_de_jeu[7][0] = new Tour(true,7,0,this);
	tableau_de_jeu[1][0] = new Cavalier(true,1,0,this);
	tableau_de_jeu[6][0] = new Cavalier(true,6,0,this);
	tableau_de_jeu[2][0] = new Fou(true,2,0,this);
	tableau_de_jeu[5][0] = new Fou(true,5,0,this);
	tableau_de_jeu[3][0] = new Dame(true,3,0,this);
	tableau_de_jeu[4][0] = new Roi(true,4,0,this);
    }
    public boolean caseValide(int colonne, int ligne){
	if((colonne>=0) && (colonne<8)&&(ligne>=0) && (ligne<8)){
	    return true;
	}
	else{
	    return false;
	}
    }
    public Piece examinePiece(int colonne, int ligne){
	return tableau_de_jeu[colonne][ligne];	
    }
    public Piece prendsPiece(int colonne, int ligne)
    {
	Piece p = tableau_de_jeu[colonne][ligne];
	tableau_de_jeu[colonne][ligne] = null;
	return p;
    }
    public void posePiece(Piece p, int colonne, int ligne)
    {
	tableau_de_jeu[colonne][ligne] = p;
    }
    public void capturePiece(int colonne, int ligne)
    {
    boolean ajout = true;
	Piece p = tableau_de_jeu[colonne][ligne];
	tableau_de_jeu[colonne][ligne] = null;
	if(p.estBlanc())
	{
	    for(int i = 0; i < 16; i++)
	    {
		if(blancs_captures[i] == null && ajout )
		    {
			blancs_captures[i] = p;
			ajout = false;
		    }
	    }
	}
	else
	{
	    for(int i = 0; i < 16; i++)
	    {
		if(noirs_captures[i] == null && ajout)
		    {
			noirs_captures[i] = p;
			ajout =false;
		    }
	    }
	}
    }
    public void afficheDeplacement(int colonne, int ligne){
    	Piece p = tableau_de_jeu[colonne][ligne];
    	for(int y=0;y<8;y++){
    	for(int x=0;x<8;x++){
    		if(p.deplacementValide(y, x) && tableau_de_jeu[y][x] == null){
    			tableau_de_jeu[y][x] = new PieceX(true,0,0,this);
    		}
    		if(p.deplacementValide(y, x) && tableau_de_jeu[y][x] !=null){
    			tableau_de_jeu[y][x].modeX = true;
    		}
    	}}
    	if(JeuEchec.unicode){
    	afficheUnicode();}
    	else{
    	afficheAscii();
    	}
    	for(int y=0;y<8;y++){
        	for(int x=0;x<8;x++){
        		if(tableau_de_jeu[y][x] != null)
        		tableau_de_jeu[y][x].modeX = false;
       	if(tableau_de_jeu[y][x] != null)
       	if(tableau_de_jeu[y][x].representationUnicode().equals("x")){	
       		tableau_de_jeu[y][x] = null;
       		
        	}
    }}}
    //
    // affiche jeu
    //
    //Mode Ascii()
    //
    public void afficheAscii(){
	System.out.print("Les noirs ont capturé: ");
	for(int z=0;z<16;z++){
	if(blancs_captures[z] != null)
	System.out.print(blancs_captures[z].representationAscii());
	}
	System.out.println("");	
	System.out.println("  a  b  c  d  e  f  g  h");
	System.out.println(" -------------------------");
	for(int x=7;x>=0;x--){
	    System.out.print((x+1)+"|");
	    for(int y=0;y<8;y++){
		if(tableau_de_jeu[y][x] ==null){
		    System.out.print("."+"  ");
		}else{
		    System.out.print(tableau_de_jeu[y][x].representationAscii()+"  ");
		}
	    }
	    System.out.print("|"+(x+1));
	    System.out.println("");	}
	System.out.println(" -------------------------");
	System.out.println("  a  b  c  d  e  f  g  h");
	System.out.println("");	
	System.out.print("Les blancs ont capturé: ");
	for(int z=0;z<16;z++){
		if(noirs_captures[z] != null)
		System.out.print(noirs_captures[z].representationAscii());
		}
	System.out.println("");	
    }
    //
    // Mode unicode()
    //
    public void afficheUnicode(){
	System.out.print("Les noirs ont capturé: ");
	for(int z=0;z<16;z++){
	if(blancs_captures[z] != null)
	System.out.print(blancs_captures[z].representationUnicode());
	}
	System.out.println("");	
	System.out.println("   a   b   c   d   e   f   g   h");
	System.out.println(" ┌───┬───┬───┬───┬───┬───┬───┬───┐");
	for(int x=7;x>=0;x--){
	    System.out.print((x+1)+"|");
	    for(int y=0;y<8;y++){
		if(tableau_de_jeu[y][x] ==null){
		    System.out.print("  "+" |");
		}else{
		    System.out.print(" "+tableau_de_jeu[y][x].representationUnicode()+" |");
		}
	    }
	    System.out.print(x+1);
	    System.out.println("");	
	    if(x!=0){
		System.out.println(" ├───┼───┼───┼───┼───┼───┼───┼───┤");
	    }
	}
	System.out.println(" └───┴───┴───┴───┴───┴───┴───┴───┘");
	System.out.println("   a   b   c   d   e   f   g   h");
	System.out.println("");	
	System.out.print("Les blancs ont capturé: ");
	for(int z=0;z<16;z++){
	if(noirs_captures[z] != null)
	System.out.print(noirs_captures[z].representationUnicode());
	}
	System.out.println("");	
    }
}
