package cloverrose.freedimension.imple;

import java.util.LinkedList;
import java.util.List;
import cloverrose.freedimension.IFreeDimension;

abstract class SumAlg<T extends Number>{
    protected abstract T add(T x,T y);  
    protected abstract Double div(T x,Integer y);
    
    void sumR(IFreeDimension<T> data,int indexs[],T val,boolean res[]){
        //Error Check------------------------------------------------------------------
        for(int c=0;c<data.getDimension();c++){
            if(indexs[c]==0){
                throw new RuntimeException(String.format(
                        "index[%d]=%d is wrong",c,indexs[c]));
            }
        }
        //Error Check------------------------------------------------------------------
        sumRIter(data,indexs,val, res, 0);
    }
    private void sumRIter(IFreeDimension<T> data,int indexs[],T val,boolean res[],int d){
        if(d==indexs.length){
            data.setAt(indexs, add(data.getAt(indexs), val));
        }else{
            sumRIter(data,indexs,val,res,d+1);
            if(res[d]){
                sumRIter(data,set0(indexs,d),val,res,d+1);
            }
        }
    }
    private int[] set0(int vals[],int idx){
        return setN(vals,idx,0);
    }
    private int[] setN(int vals[],int idx,int n){
        int[] ret=new int[vals.length];
        for(int i=0;i<vals.length;i++){
            ret[i]=vals[i];
        }
        ret[idx]=n;
        return ret;
    }
    
    
    IFreeDimension<Double> div(IFreeDimension<T> data,IFreeDimension<Integer> inc){
        IFreeDimension<Double> ret=new FreeDimension<Double>(data.getSizes(),0.0);
        List<int[]> indexsList=makeIndexsList(data.getSizes());
        for(int[] indexs : indexsList){
            ret.setAt(indexs, div(data.getAt(indexs),inc.getAt(indexs)));
        }
        return ret;
    }

    /**
     * make all pattern arrays of int(0..sizes[x])
     * @param indexs
     * @param d
     * @return
     */
    private List<int[]> makeIndexsList(int[] sizes){
        int[] indexs=new int[sizes.length];
        return makeIndexsList(indexs, 0,sizes);
    }
    private List<int[]> makeIndexsList(int indexs[],int d,int[] sizes){
        List<int[]> ret=new LinkedList<int[]>();
        if(d==sizes.length){
            ret.add(indexs);
        }else{
            for(int n=0;n<sizes[d];n++){
                ret.addAll(makeIndexsList(setN(indexs,d,n),d+1,sizes));
            }
        }
        return ret;
    }   
}
