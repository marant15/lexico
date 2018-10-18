package lexico;

public class MatrizDol {
	public static int verificar(char ant,String palabra,int x, int y,int c, String pal) {
		String antp = palabra;
		String[][] car= {
				{"+=","D","2"}, {"->","D","2"},{"-=","D","2"},{"**","O","2"},{"*=","D","2"},
				{"//","O","2"},{"/=","D","2"},{"%=","D","2"},{"@=","D","2"},{"<<","O","2"},
				{"<=","O","2"},{">=","O","2"},{">>","O","2"},{"&=","D","2"},{"|=","D","2"},
				{"^=","D","2"},{"!=","O","2"},{"==","O","2"},
				{"*","O","1"},{"/","O","1"},{"%","O","1"},{"<","O","1"},
				{">","O","1"},{"&","O","1"},{"|","O","1"},{"^","O","1"},{"~","O","1"},{"=","D","1"},{"(","D","1"},
				{")","D","1"},{"[","D","1"},{"]","D","1"},{"{","D","1"},{"}","D","1"},{",","D","1"},{":","D","1"},
				{";","D","1"},{"@","O","1"},{"\\","E","1"},{"+","O","1"},{"-","O","1"},{".","D","1"}};
		Token tok;
		String caract="";
		if(palabra.length()==3) {
			for (int i = 0; i < 4; i++) {
				if(palabra.equals(car[i][0])) {
					if(car[i][1].equals("O"))caract="Operator";
					else if(car[i][1].equals("D"))caract="Delimiter";
					else caract="Especial";
					if(pal!=""){
						tok = new Token(x,y-c,pal, true, 0);
						tok.verificar();
					}
					tok = new Token(x,y,car[i][0], true, 0,true,caract);
					tok.verificar();
					return Integer.parseInt(car[i][2]);
				}
			}
			palabra= palabra.substring(0, 2);
		}
		if(palabra.length()==2) {
			for (int i = 4; i < 22; i++) {
				if(palabra.equals(car[i][0])) {
					if(car[i][1].equals("O"))caract="Operator";
					else if(car[i][1].equals("D"))caract="Delimiter";
					else caract="Especial";
					if(pal!=""){
						tok = new Token(x,y-c,pal, true, 0);
						tok.verificar();
					}
					tok = new Token(x,y,car[i][0], true, 0,true,caract);
					tok.verificar();
					return Integer.parseInt(car[i][2]);
				}
			}
			palabra= palabra.substring(0, 1);
		}
		if (palabra.length()==1) {
			for (int i = 21; i < car.length-3; i++) {
				if(palabra.equals(car[i][0])) {
					if(car[i][1].equals("O"))caract="Operator";
					else if(car[i][1].equals("D"))caract="Delimiter";
					else caract="Especial";
					if(pal!=""){
						tok = new Token(x,y-c,pal, true, 0);
						tok.verificar();
					}
					tok = new Token(x,y,car[i][0], true, 0,true,caract);
					tok.verificar();
					return Integer.parseInt(car[i][2]);
				}
			}
			
		}
		return 0;
	}
	public static boolean palabrares(String cadena){
		String[] car = {"falso","clase","retorno","para","intentar",
		"verdadero","mientras","and","osi","si","or","sino","importar","terminar","ent","decimal","marwin","car","palabra","publico","privado"};
		for (int i = 0; i < car.length; i++) {
			if (cadena.equals(car[i]))return true;
		}
		return false;
	}
}
