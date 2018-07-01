package prime;

public class main {

	public static void main(String[] args) {
		Grafo g=new Grafo("grafo.in");
		Grafo g1=new Grafo(g.getCantN());
		int sum=g.prim( g1);
		g1.imprimir("grafo1.in");
		
	}

}
