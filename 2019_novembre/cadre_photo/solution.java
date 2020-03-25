
/****************************************************************
/***/
/***/
/** SOLUTION BY MaxHeap*/
/***/
/***/
/********************************************************************/
/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;
import java.util.*;
import java.util.function.*;

public class IsoContest {
public static void main( String[] argv ) throws Exception {
		Scanner in = new Scanner(System.in);
		PriorityQueue<Integer> cables = new PriorityQueue<>();
		PriorityQueue<Req> requests = new PriorityQueue<>(
		    Comparator.comparingInt(
		        (Req rr) -> rr.from
		).thenComparingInt(rr -> rr.to));
		
		int n = in.nextInt();
		int m = in.nextInt();
		int[] ans = new int[m];
		for(int i = 1; i <= n; ++i) {
		    cables.add(i);
		}
		for(int i = 0; i < m; ++i) {
		    Req r = new Req(in.nextInt(), in.nextInt(), i);
		    requests.add(r);
		}
		List<Req> seen = new ArrayList<>();
		boolean[] rem = new boolean[m];
		
		while(!requests.isEmpty()) { 
	        Req cur = requests.poll();	    
	        if(cables.isEmpty()) { 
	            for(Req req: seen) {
	                if(req.to <= cur.from & !rem[req.index]) {
	                    cables.add(ans[req.index]);
	                    rem[req.index] = true;
	                }
	            }
	        }
            if(cables.isEmpty()) {
              System.out.println("pas possible");
              return;
            } else {
	            ans[cur.index] = cables.poll();
	            seen.add(cur);
	        }
		}
		for(int i = 0; i < m; ++i) {
		    if(i > 0) System.out.print(" ");
		    System.out.print(ans[i]);
		}
		System.out.println();
    }
	static class Req { 
	    int from, to, index;
	    public Req(int from, int to, int index) {
	        this.from = from;
	        this.to = to;
	        this.index = index;
	    }
	}
}

