package b12app.vyom.com.flowit.datasource;

public interface IDataSource {

    String getData();

    interface DbCallback {
        void onSuccess();

        void onFailure();
    }

    interface NetworkCallback {
        void onSuccess();

        void onFailure();
    }


}
