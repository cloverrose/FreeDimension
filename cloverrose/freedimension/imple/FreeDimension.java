package cloverrose.freedimension.imple;

import java.util.ArrayList;
import java.util.List;
import cloverrose.freedimension.IFreeDimension;


public class FreeDimension<T> implements IFreeDimension<T> {
    /**
     * array's dimension
     * [[v11,v12],[v21,v22]]->dimension=2
     * [[[v111,v112],[v121,v122]],[[v211,v212],[v221,v222]]]->dimension=3
     */
    private final int dimension;
    
    /**
     * size of each dimension
     * [[[v111,v112],[v121,v122]],[[v211,v212],[v221,v222]],[[v311,v312],[v321,v322]]]->sizes={3,2,2}
     */
    private final int[] sizes;
    
    /**
     * this class's main field
     * user can change ONLY this field through setAt method.
     */
    private final List<T> ret;

    public FreeDimension(int[] sizes,T ini){
        this.dimension=sizes.length;
        
        this.sizes=new int[this.dimension];
        for(int c=0;c<this.dimension;c++){
            this.sizes[c]=sizes[c];
        }
        
        int len=1;
        for(int c=0;c<this.dimension;c++){
            len*=sizes[c];
        }
        this.ret=new ArrayList<T>(len);
        for(int i=0;i<len;i++){
            this.ret.add(ini);
        }
    }
    
    @Override
    public T getAt(int[] indexs) {
        int index=this.calcIndex(indexs);
        return this.ret.get(index);
    }
    @Override
    public void setAt(int[] indexs, T value) {
        int index=this.calcIndex(indexs);
        this.ret.set(index,value);
    }
    private int calcIndex(int[] indexs){
        //Error Check------------------------------------------------------------------
        if(indexs.length!=this.dimension){
            throw new RuntimeException(String.format(
                    "indexs.length(%d) != dimension(%d)",indexs.length,this.dimension));
        }
        for(int c=0;c<this.dimension;c++){
            if(indexs[c]<0 || indexs[c]>=this.sizes[c]){
                throw new RuntimeException(String.format(
                        "index[%d]=%d is wrong",c,indexs[c]));
            }
        }
        //Error Check------------------------------------------------------------------
        
        int index=0;
        int slideSize=1;
        for(int c=0;c<this.dimension;c++){
            index+=slideSize*indexs[this.dimension-1-c];
            slideSize*=this.sizes[this.dimension-1-c];
        }
        return index;
    }
    
    @Override
    public int getDimension(){
        return this.dimension;
    }
    @Override
    public int[] getSizes() {
        int[] ret=new int[this.dimension];
        for(int c=0;c<this.dimension;c++){
            ret[c]=this.sizes[c];
        }
        return ret;
    }
    @Override
    public String toString(){
        return toString(0,0);
    }
    private String toString(int d,int slideSize){
        if(d==this.dimension){
            return this.ret.get(slideSize).toString();
        }
        
        String s="";
        boolean first=true;
        int[] slideSizes=this.calcSlideSizes();
        for(int i=0;i<this.sizes[d];i++){
            if(first){
                first=false;
            }else{
                s+=",";
            }
            s+=toString(d+1,slideSize+slideSizes[d]*i);
        }
        return String.format("[%s]",s);
    }
    /**
     * make help data for toString()
     * if this sizes={2,3}
     * => return {3,1}
     * @return
     */
    private int[] calcSlideSizes(){
        int[] ret=new int[this.dimension];
        int temp=1;
        for(int c=0;c<this.dimension;c++){
            ret[this.dimension-1-c]=temp;
            temp*=sizes[this.dimension-1-c];
        }
        return ret;
    }

}
