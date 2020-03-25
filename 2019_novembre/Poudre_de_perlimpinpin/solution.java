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

public class IsoContest {
    static int[] s, c, p, cp;
    static int n, m, cap;
    static Integer[][] dp;
    static int compute(int index, int capacity) {
        if(index > n + m - 1) {
            return 0;
        }
        if(dp[index][capacity] != null) {
            return dp[index][capacity];
        }
        int best = 0;
        if(index < n) {
            best = Math.max(best, compute(index + 1, capacity));
            if(capacity - c[index] >= 0) {
                best = Math.max(best, compute(index + 1, capacity - c[index]) + s[index]);
            }
        }else {
            best = Math.max(best, compute(index + 1, capacity));
            int curIndex = index - n;
            for(int i = 1; i <= Math.min(cp[curIndex], capacity); ++i) {
                best = Math.max(best, compute(index + 1, capacity - i) + i * p[curIndex]);
            }
        }
        return dp[index][capacity] = best;
    }
    
    public static void main( String[] argv ) throws Exception {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		cap = in.nextInt();
		s = new int[n];
		c = new int[n];
		p = new int[m];
		cp = new int[m];
		dp = new Integer[n + m + 1][cap + 1];
		
		for(int i = 0; i < n; ++i) {
		    s[i] = in.nextInt();
		    c[i] = in.nextInt();
		}
		for(int i = 0; i < m; ++i) {
		    p[i] = in.nextInt();
		    cp[i] = in.nextInt();
		}
        System.out.println(compute(0, cap));
	}
}
