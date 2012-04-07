package cloverrose.freedimension.imple;

import cloverrose.freedimension.IDoubleSumuper;
import cloverrose.freedimension.IFreeDimension;

public class DoubleSumuper implements IDoubleSumuper{
    private final IFreeDimension<Double> data;
    private final int dimension;
    private final DoubleSumAlg alg;

    public DoubleSumuper(int sizes[]){
        this.dimension=sizes.length;
        this.data=new FreeDimension<Double>(sizes,0.0);
        this.alg=new DoubleSumAlg();
    }
    
    @Override
    public IFreeDimension<Double> getData(){
        return this.data;
    }
    @Override
    public void sum(int indexs[],double val){
        this.sumR(indexs,val,Util.makeDefaultRes(dimension));
    }
    @Override
    public void sumR(int indexs[],double val,boolean res[]){
        alg.sumR(data, indexs, val, res);
    }
    @Override
    public IFreeDimension<Double> div(IFreeDimension<Integer> inc){
        return alg.div(data, inc);
    }
    
    private class DoubleSumAlg extends SumAlg<Double>{
        @Override
        protected Double add(Double x, Double y) {
            return new Double(x+y);
        }
        @Override
        protected Double div(Double x, Integer y) {
            return new Double(((double)x)/y);
        }
    }
}
