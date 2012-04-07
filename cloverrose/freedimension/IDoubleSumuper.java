package cloverrose.freedimension;
/**
 * this class is similar to ISumuper.
 * ONLY DIFFERENCE is add val is double type (not long)
 * @author cloverrose
 *
 */
public interface IDoubleSumuper {
    IFreeDimension<Double> getData();
    void sum(int[] indexs,double val);
    void sumR(int[] indexs,double val,boolean[] res);
    IFreeDimension<Double> div(IFreeDimension<Integer> counter);
}
