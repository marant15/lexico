package lexico;

import java.util.regex.Pattern;

public class Token {
	int x;
	int y;
	String cadena;
	int clas;
	boolean ver;
	String caract;
	public Token(int x, int y, String palabra, boolean pos, int clas){
		this.x=x;
		this.y=y;
		cadena = palabra;
		this.clas=clas;
		this.ver = false;
		if(!pos)this.caract="error";
		else this.caract = "no def";
	}
	public Token(int x, int y, String palabra, boolean pos, int clas,boolean ver,String caract){
		this.x=x;
		this.y=y;
		cadena = palabra;
		this.clas=clas;
		this.ver = ver;
		this.caract=caract;
	}
	public void print(){
		//System.out.println(caract+": "+ cadena + " "+x+" "+y);
		Code.push(new tokenzz(x,y,cadena,caract));
	}
	public void verificar(){
		if(ver){
			print();
		}else{
			if(clas>0){
				int pos;
				if(clas == 2 || clas==4)pos = cadena.indexOf(39);
				else pos = cadena.indexOf(34);
				if(pos>1){
					String sub = cadena.substring(pos-2,pos);
					String sub1 = cadena.substring(pos-1,pos);
					if(sub.equals("fr")||sub.equals("fR")||sub.equals("Fr")||sub.equals("FR")||
							sub.equals("rf")||sub.equals("rF")||sub.equals("Rf")||sub.equals("RF")){
						if(pos == 2){
							caract = "String literal";
							ver = true;
							print();
						}else{
							Token tok = new Token(x, y, cadena.substring(0,pos-2), true, 0);
							tok.verificar();
							tok = new Token(x, y+pos-2,cadena.substring(pos-2, cadena.length()) , true, 0,true,"String literal");
							tok.verificar();
						}
					}else if(sub.equals("br")||sub.equals("bR")||sub.equals("Br")||sub.equals("BR")||
							sub.equals("rb")||sub.equals("rB")||sub.equals("Rb")||sub.equals("RB")){
						if(pos == 2){
							caract = "bytes literal";
							ver = true;
							print();
						}else{
							Token tok = new Token(x, y, cadena.substring(0,pos-2), true, 0);
							tok.verificar();
							tok = new Token(x, y+pos-2,cadena.substring(pos-2, cadena.length()) , true, 0,true,"bytes literal");
							tok.verificar();
						}
					}else if("ruRUfF".contains(sub1)){
						Token tok = new Token(x, y, cadena.substring(0,pos-1), true, 0);
						tok.verificar();
						tok = new Token(x, y+pos-1,cadena.substring(pos-1, cadena.length()) , true, 0,true,"String literal");
						tok.verificar();
					}else if("bB".contains(sub1)){
						Token tok = new Token(x, y, cadena.substring(0,pos-1), true, 0);
						tok.verificar();
						tok = new Token(x, y+pos-1,cadena.substring(pos-1, cadena.length()) , true, 0,true,"bytes literal");
						tok.verificar();
					}else{
						Token tok = new Token(x, y, cadena.substring(0,pos), true, 0);
						tok.verificar();
						tok = new Token(x, y+pos,cadena.substring(pos, cadena.length()) , true, 0,true,"Strings literal");
						tok.verificar();
					}
				}else if(pos>0){
					if("ruRUfF".contains(""+cadena.charAt(0))){
						caract = "String literal";
						ver = true;
						print();
					}else if("bB".contains(""+cadena.charAt(0))){
						caract = "bytes literal";
						ver = true;
						print();
					}else{
						Token tok = new Token(x, y, cadena.substring(0,pos-1), true, 0);
						tok.verificar();
						tok = new Token(x, y+1,cadena.substring(pos, cadena.length()) , true, 0,true,"String literal");
						tok.verificar();
					}
				}else{
					ver = true;
					caract = "string literal";
					print();
				}
			}else{
				if(MatrizDol.palabrares(cadena)){
					caract = "Keyword";
					ver = true;
					print();
				}
				else if((cadena.charAt(0)>=97 && cadena.charAt(0)<= 122)|| (cadena.charAt(0)>=65 && cadena.charAt(0)<= 90) || cadena.charAt(0)== 95){
					if(cadena.charAt(0)!= 95){
						boolean punto = false;
						for (int i = 0; i < cadena.length(); i++) {
							if(cadena.codePointAt(i)==46){
								Token tok = new Token(x, y,cadena.substring(0, i) , true, 0,true,"Identifier");
								tok.verificar();
								tok = new Token(x, y+i,"." , true, 0,true,"Delimiter");
								tok.verificar();
								tok = new Token(x, y+i+1,cadena.substring(i+1, cadena.length()) , true, 0);
								tok.verificar();
								i++;
								punto = true;
							}
						}
						if(!punto){
							caract = "Identifier";
							ver = true;
							print();
						}
					}
					
					else {
						caract = "Rco_identifier";
						ver = true;
						print();
					}
				}else {
					Pattern bin = Pattern.compile("0(b|B)(_?(0|1))+");
					Pattern oct = Pattern.compile("0(o|O)(_?(0|1|2|3|4|5|6|7))+");
					Pattern hex = Pattern.compile("0(x|X)(_?(0|1|2|3|4|5|6|7|8|9|a|b|c|d|e|f|A|B|C|D|F|E))+");
					Pattern dec = Pattern.compile("((1|2|3|4|5|6|7|8|9)(_?\\d)*)|(0(_?0)*)");
					String dp = "(\\d(_?\\d)*)";
					String exp = "(e|E)(\\+|-)?"+dp;
					String pf = "("+dp+"?\\."+dp+")|("+dp+"\\.)";
					String fl = "("+pf+"|(("+dp+"|"+pf+")"+exp+"))";
					Pattern flot = Pattern.compile(fl);
					Pattern im = Pattern.compile("("+fl+"|"+dp+")(j|J)");
				    if(bin.matcher(cadena).matches()){
				    	caract="Integer literals";
				    	ver = true;
				    }else if(oct.matcher(cadena).matches()){
				    	caract="Integer literals";
				    	ver = true;
				    }else if(hex.matcher(cadena).matches()){
				    	caract="Integer literals";
				    	ver = true;
				    }else if(dec.matcher(cadena).matches()){
				    	caract="Integer literals";
				    	ver = true;
				    }else if(flot.matcher(cadena).matches()){
				    	caract="float literals";
				    	ver = true;
				    }else if(im.matcher(cadena).matches()){
				    	caract="imaginary literals";
				    	ver = true;
				    }
					print();
				}
			}
		}
	}

}
