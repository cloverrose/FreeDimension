package cloverrose.freedimension.imple;

class Util {
    static boolean[] makeDefaultRes(int dimension){
        boolean[] _res_=new boolean[dimension];
        for(int i=0;i<dimension;i++){
            _res_[i]=true;
        }
        return _res_;
    }
}
