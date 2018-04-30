package b12app.vyom.com.flowit.datasource;

public class DataManager implements IDataSource {
    private IDataSource remoteDataSource;
    private IDataSource localDataSource;
    private static DataManager INSTANCE = null;

    private DataManager(IDataSource remoteDataSource, IDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    public static DataManager getInstance(IDataSource remoteDataSource, IDataSource localDataSource){
        if (INSTANCE == null){
            INSTANCE = new DataManager(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    public String showRemoteContent(){
        //Todo what you want do on the original data
        return remoteDataSource.getData();
    }

    public String showLocalContent(){
        //Todo what you want do on the original data
        return localDataSource.getData();
    }

    @Override
    public String getData() {
        return null;
    }
}
