package cloverrose.freedimension.imple;

import cloverrose.freedimension.IFreeDimension;
import cloverrose.freedimension.ISumuper;

public class Sumuper implements ISumuper{
    private final IFreeDimension<Long> data;
    private final int dimension;
    private final LongSumAlg alg;
    
    public Sumuper(int sizes[]){
        this.dimension=sizes.length;
        this.data=new FreeDimension<Long>(sizes,0L);
        this.alg=new LongSumAlg();
    }
    
    @Override
    public IFreeDimension<Long> getData(){
        return this.data;
    }
    @Override
    public void sum(int indexs[],long val){
        this.sumR(indexs,val,Util.makeDefaultRes(dimension));
    }
    @Override
    public void sumR(int indexs[],long val,boolean res[]){
        alg.sumR(data, indexs, val, res);
    }
    @Override
    public IFreeDimension<Double> div(IFreeDimension<Integer> inc){
        return alg.div(data, inc);
    }

    private class LongSumAlg extends SumAlg<Long>{
        @Override
        protected Long add(Long x, Long y) {
            return new Long(x+y);
        }
        @Override
        protected Double div(Long x, Integer y) {
            return new Double(((double)x)/y);
        }
    }
}