package cloverrose.freedimension;

public interface IIncrementer {
    IFreeDimension<Integer> getData();
    
    /**
     * increment value at 'indexs',
     * also increment value at indexs which replaced index(in 'indexs') with 0 
     * 
     * EXAMPLE
     * now 'indexs'{x,y}={1,3},then increment value at {1,3},{1,0},{0,3},{0,0}
     * x\y| 0  1  2  3
     * ----------------
     *  0 | o        o
     *  1 | o        o
     *  2 | 
     *  3 | 
     * So this data update like this.
     * x\y| 0  1  2  3      x\y| 0  1  2  3
     * ----------------     ---------------
     *  0 | 2  0  1  0       0 | 4  0  1  1
     *  1 | 1  0  1  0   ->  1 | 2  0  1  1
     *  2 | 0  0  0  0       2 | 0  0  0  0
     *  3 | 0  0  0  0       3 | 0  0  0  0
     *  
     * NOTICE
     * 'indexs' MUST be Non-Zero
     * @param indexs
     */  
    void inc(int[] indexs);
    
    /**
     * increment value at 'indexs',
     * also increment value at indexs which replaced index(in 'indexs') with 0 ,where 'res' is true
     * 
     * DIFFERENCE
     * incR is different from inc.
     * if 'res' has no true,  then incR increment value at ONLY 'indexs'.
     * if 'res' has no false, then incR is SAME as inc.
     * 
     *  
     * EXAMPLE 1
     * now 'indexs'{x,y}={1,3} and 'res'{x,y}={true,false},then increment value at {1,3},{0,3}
     * x\y| 0  1  2  3
     * ----------------
     *  0 |          o
     *  1 |          o
     *  2 | 
     *  3 | 
     * 
     * EXAMPLE 2
     * now 'indexs'{x,y}={1,3} and 'res'{x,y}={false,true},then increment value at {1,3},{1,0}
     * x\y| 0  1  2  3
     * ----------------
     *  0 |           
     *  1 | o        o
     *  2 | 
     *  3 | 
     *   
     * EXAMPLE 3
     * now 'indexs'{x,y}={1,3} and 'res'{x,y}={false,false},then increment value at ONLY {1,3}
     * x\y| 0  1  2  3
     * ----------------
     *  0 |           
     *  1 |          o
     *  2 | 
     *  3 | 
     *   
     * NOTICE
     * 'indexs' MUST be Non-Zero
     * @param indexs
     * @param res
     */
    void incR(int[] indexs,boolean[] res);
}
