import java.io.BufferedReader;
import java.util.Arrays;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;

public class KnapsackG {

	public static void prep() {

		File f = new File("entrada.txt");
		try  (Scanner entrada = new Scanner(f)) {
			int c=entrada.nextInt();
			int[] a = new int[c];
			int[] b = new int[c];
			for(int i=0;i<a.length;i++) {
				a[i]=entrada.nextInt();
			}
			for(int i=0;i<b.length;i++) {
				b[i]=entrada.nextInt();
			}
			int d = entrada.nextInt();
			System.out.println(solve(c, a, b, d));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String solve(int cantidad, int[] ganancias, int[] pesos, int capacidad) {
		String pck ="";
		int gananciaF=0;
	    int pesoAct;
	    int[] pesoOrd;
	    String res="El maximo beneficio es ";
	    //casos base
		if(ganancias.length!=pesos.length) {
			return null	;
		}

		for(int i =0;i<cantidad;i++) {
			if(ganancias[i]<1) {
				return null;
			}
			if(pesos[i]<1) {
				return null;
			}
		}
		//arreglar lista en orden descendiente
		int[] gananciasOr = Arrays.copyOf(ganancias, cantidad);
		ganancias=sort(ganancias);
		//acomodar la lista de pesos
		pesoOrd=sortPesos(ganancias, gananciasOr, pesos, cantidad);
		//llenamos la mochila con el metodo greedy
		pesoAct = 0;
        int w =0;
        while(pesoAct <= capacidad && w < cantidad){
            if(pesoOrd[w] <= (capacidad - pesoAct)) {
                pesoAct += pesoOrd[w];
                gananciaF += ganancias[w];
                pck=pck+" Peso:"+pesoOrd[w]+" Valor: "+ganancias[w]+", ";

            }
            w++;
        }
		res = res+gananciaF+pck;
		return res;
	}

	public static int[] sortPesos(int[] gananciaN, int[] gananciaV, int[] pesos, int cantidad){
	    int[] pesoOrd =new int[cantidad];
		for(int i = 0; i < cantidad; i++){
	            int j = 0;
	            boolean match = false;
	            while (!match && j <cantidad){
	                if(gananciaN[i] == gananciaV[j]){
	                    gananciaV [j] = Integer.MAX_VALUE;
	                    pesoOrd[i] = pesos[j];
	                    match = true;
	                }
	                j++;
	            }
	       }
	        return pesoOrd;
	   }


	public static int[] sort(int[] arr){
		int temp;
		for (int i = 0; i < arr.length; i++) {
	            for (int j = i+1; j < arr.length; j++) {
	               if(arr[i] < arr[j]) {
	                   temp = arr[i];
	                   arr[i] = arr[j];
	                   arr[j] = temp;
	               }
	            }
	        }
		 return arr;
	}

	public long TimeToFind(int cantidad, int[] ganancias, int[] peso, int capacidad){
        long start = System.nanoTime();
        String solucion = solve(cantidad, ganancias, peso, capacidad);
        long end = System.nanoTime();
        long duracion =  end- start;
        return duracion;
    }

	public static void main(String args[]) {
		prep();
		int a = 3;
		int d = 7;
		int[] b = {6, 3, 1};
		int[] c = {3, 2, 1};
		System.out.println(solve(a,b,c,d));

	}


}
