package b12app.vyom.com.flowit.datasource;

public interface IDataSource {

    interface DbCallback{
        void onSuccess();
        void onFailure();
    }

    interface NetworkCallback{
        void onSuccess();
        void onFailure();
    }
}
