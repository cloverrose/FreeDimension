package cloverrose.freedimension.imple;

import cloverrose.freedimension.IFreeDimension;
import cloverrose.freedimension.IIncrementer;

public class Incrementer implements IIncrementer{
    private final IFreeDimension<Integer> data;
    private final int dimension;
    private final IntegerSumAlg alg;
    
    public Incrementer(int sizes[]){
        this.dimension=sizes.length;
        this.data=new FreeDimension<Integer>(sizes,0);
        this.alg=new IntegerSumAlg();
    }
 
    @Override
    public IFreeDimension<Integer> getData(){
        return this.data;
    }   
    @Override
    public void inc(int indexs[]){
        this.incR(indexs,Util.makeDefaultRes(dimension));
    }
    @Override
    public void incR(int indexs[],boolean res[]){
        alg.sumR(data, indexs, 1, res);
    }
    
    private class IntegerSumAlg extends SumAlg<Integer>{
        protected Integer add(Integer x,Integer y){
            return new Integer(x+y);
        }
        @Override
        protected Double div(Integer x, Integer y) {
            return new Double(((double)x)/y);
        }
    }
    /**
     * DEMO
     * @param args
     */
    public static void main(String[] args){
        boolean x=false;
        if(x){//Demo inc
            int[] sizes={3,5};
            IIncrementer inc=new Incrementer(sizes);
            System.out.println(inc.getData().toString());
            
            if(x){
                int[] indexs={1,2};
                inc.inc(indexs);
                System.out.println(inc.getData().toString());
            }
            if(x){
                int[] indexs={1,4};
                inc.inc(indexs);
                System.out.println(inc.getData().toString());
            }
        }
        if(!x){
            int[] sizes={3,5};
            IIncrementer incA=new Incrementer(sizes);
            IIncrementer incB=new Incrementer(sizes);
            
            int[] indexs={1,2};
            boolean[] res={true,true};
            incA.inc(indexs);
            incB.incR(indexs,res);
            System.out.println("[Demo] incR is SAME as inc, if res has no false.");
            System.out.println(incA.getData().toString());
            System.out.println(incB.getData().toString());

        }
        if(x){//Demo error index[0] must 1 or 2
            int[] sizes={3,5};
            IIncrementer inc=new Incrementer(sizes);
            System.out.println(inc.getData().toString());
            
            if(x){
                int[] indexs={-1,2};
                inc.inc(indexs);// index[0]=-1 is wrong
                System.out.println(inc.getData().toString());
            }
            if(x){
                int[] indexs={0,2};
                inc.inc(indexs);// index[0]=0 is wrong
                System.out.println(inc.getData().toString());
            }
            if(x){
                int[] indexs={3,2};
                inc.inc(indexs);// index[0]=3 is wrong
                System.out.println(inc.getData().toString());
            }
        }
    }
}
