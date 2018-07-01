package prime;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Grafo {
	private int cantN;
	private int [][]grafo;
	public int getCantN() {
		return cantN;
	}
	
	public Grafo(String name)
	{
		try {					
		FileReader fr=new FileReader(name);
		BufferedReader br=new BufferedReader(fr);
		String linea;
		linea=br.readLine();
		cantN=Integer.parseInt(linea);
		grafo=new int [cantN][cantN];
		for(int i=0;i < cantN ; i++)
		{
			for(int u=0;u<cantN; u++)
				grafo[i][u]=0;
		}
		String datos[];
		while((linea=br.readLine())!=null)
		{
			datos=linea.split(" ");
			grafo[Integer.parseInt(datos[0])-1][Integer.parseInt(datos[2])-1]=Integer.parseInt(datos[1]);
			grafo[Integer.parseInt(datos[2])-1][Integer.parseInt(datos[0])-1]=Integer.parseInt(datos[1]);
		}
		br.close();
		}catch(Exception e) {e.printStackTrace();}
		
	}
	
	
	
	public Grafo(int cantN)
	{
		this.cantN=cantN;
		grafo=new int [cantN][cantN];
		for(int i=0;i < cantN ; i++)
		{
			for(int u=0;u<cantN; u++)
				grafo[i][u]=0;
		}
		
	}
	public void setCantN(int cantN) {
		this.cantN = cantN;
	}
	public void setGrafo(int f,int c,int v)
	{
		grafo[f][c]=v;
		
	}
	public int getGrafo(int f,int c)
	{
	  return grafo[f][c];
	  
	}
	
	public int getN()
	  {
		  return cantN;
	  }
	public int prim(Grafo GrafoMin)
	{
		ColaPrioridad c =new ColaPrioridad();
		boolean []conjuntoSolucion=new boolean[cantN];
		for(int i =0;i <cantN;i++)
			conjuntoSolucion[i]=false;
		int nodosProcesados=1;
		int costoTot=0;
		Arista ar = null;
		int actual=1;   	//aca tendria que elejir un random de todos los nodos
		
		while(nodosProcesados< cantN)
		{
		agragarAristas(this,actual,c,conjuntoSolucion);
		conjuntoSolucion[actual-1]=true;
		
		if(!c.isEmpty())
		{
			ar=c.poll();
			while(!c.isEmpty()&&conjuntoSolucion[ar.getNodoDestino()-1])
				ar=c.poll();
			actual=ar.getNodoDestino();
			GrafoMin.grafo[ar.getNodoOrigen()-1][ar.getNodoDestino()-1]=ar.getPeso();
			GrafoMin.grafo[ar.getNodoDestino()-1][ar.getNodoOrigen()-1]=ar.getPeso();
		
		}
		costoTot+=ar.getPeso();
		nodosProcesados++;
		}
		return costoTot;
	}
	public static void agragarAristas(Grafo g,int actual,ColaPrioridad c,boolean []sol)
	{int cost;
		for(int destino=1;destino<=g.getCantN();destino++)
		{
			if((cost=g.getGrafo(actual-1, destino-1))!=0)
			{
				if(!sol[destino-1])
					c.add(new Arista(actual,destino,cost));
			}
		}
	}
	
	
	
	
	public void imprimir(String name)
	{
		try {
			FileWriter fr=new FileWriter(name);
			PrintWriter pw=new PrintWriter(fr);
			for (int i = 0; i < cantN -1; i++) {
				for (int j = i+1; j < cantN; j++) 
				{	
					if((grafo[i][j])!=0)
					pw.println((i+1)+" "+(j+1)+" "+grafo[i][j]);
				}
					
			}
			pw.close();
		}catch(Exception e ) {e.printStackTrace();}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
