package b12app.vyom.com.flowit.datasource;

public class LocalDataSource implements IDataSource {

private static LocalDataSource instance ;

private LocalDataSource(){

}

public static LocalDataSource getInstance(){
    if (instance == null) {
        synchronized (LocalDataSource.class){
            if (instance == null) {
                instance = new LocalDataSource() ;
            }
        }
    }
    return instance ;
}

    @Override
    public String getData() {
        return null;
    }
}
