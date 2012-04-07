package cloverrose.freedimension;
/**
 * sum up long value
 * If you want to sum up double value,use IDoubleSumuper.
 * @author cloverrose
 *
 */
public interface ISumuper {
    IFreeDimension<Long> getData();
    
    /**
     * add 'val' to value at 'indexs',
     * also add 'val' to value at indexs which replaced index(in 'indexs') with 0 
     * 
     * EXAMPLE
     * now 'indexs'{x,y}={1,3},then add 'val' to value at {1,3},{1,0},{0,3},{0,0}
     * x\y| 0  1  2  3
     * ----------------
     *  0 | o        o
     *  1 | o        o
     *  2 | 
     *  3 | 
     * So if 'val'=5 then this data update like this.
     * x\y| 0  1  2  3      x\y| 0  1  2  3
     * ----------------     ---------------
     *  0 | 2  0  1  0       0 |12  0  1  5
     *  1 | 1  0  1  0   ->  1 | 6  0  1  5
     *  2 | 0  0  0  0       2 | 0  0  0  0
     *  3 | 0  0  0  0       3 | 0  0  0  0
     *  
     * NOTICE
     * 'indexs' MUST be Non-Zero
     * @param indexs
     * @param val
     */    
    void sum(int[] indexs,long val);
    
    /**
     * add 'val' to value at 'indexs',
     * also add 'val' to value at indexs which replaced index(in 'indexs') with 0 ,where 'res' is true
     * 
     * DIFFERENCE
     * sumR is different from sum.
     * if 'res' has no true,  then sumR add 'val' to value at ONLY 'indexs'.
     * if 'res' has no false, then sumR is SAME as sum.
     * 
     *  
     * EXAMPLE 1
     * now 'indexs'{x,y}={1,3} and 'res'{x,y}={true,false},then add 'val' to value at {1,3},{0,3}
     * x\y| 0  1  2  3
     * ----------------
     *  0 |          o
     *  1 |          o
     *  2 | 
     *  3 | 
     * 
     * EXAMPLE 2
     * now 'indexs'{x,y}={1,3} and 'res'{x,y}={false,true},then add 'val' to value at {1,3},{1,0}
     * x\y| 0  1  2  3
     * ----------------
     *  0 |           
     *  1 | o        o
     *  2 | 
     *  3 | 
     *   
     * EXAMPLE 3
     * now 'indexs'{x,y}={1,3} and 'res'{x,y}={false,false},then add 'val' to value at ONLY {1,3}
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
     * @param val
     * @param res
     */
    void sumR(int[] indexs,long val,boolean[] res);
    
    /**
     * divide sumup data by 'counter'.
     * you can get AVERAGE
     * 
     * NOTICE
     * 'counter' MUST be same size of THIS
     * @param counter
     * @return
     */
    IFreeDimension<Double> div(IFreeDimension<Integer> counter);
}
