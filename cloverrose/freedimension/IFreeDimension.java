package cloverrose.freedimension;

public interface IFreeDimension<T> {
    /**
     * get value at indexs
     * @param indexs
     * @return
     */
    T getAt(int[] indexs);
    
    /**
     * set value at indexs
     * @param indexs
     * @param value
     */
    void setAt(int[] indexs,T value);
    
    /**
     * get dimension
     * @return
     */
    int getDimension();
    /**
     * get sizes
     * @return
     */
    int[] getSizes();
    
    @Override
    String toString();
}
