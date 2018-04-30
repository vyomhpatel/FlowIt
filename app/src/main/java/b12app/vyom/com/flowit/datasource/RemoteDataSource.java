package b12app.vyom.com.flowit.datasource;

public class RemoteDataSource implements IDataSource {


    private static RemoteDataSource instance ;

    private RemoteDataSource(){

    }

    public static RemoteDataSource getInstance(){
        if (instance == null) {
            synchronized (RemoteDataSource.class){
                if (instance == null) {
                    instance = new RemoteDataSource() ;
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
