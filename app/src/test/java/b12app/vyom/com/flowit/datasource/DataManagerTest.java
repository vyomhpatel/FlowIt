package b12app.vyom.com.flowit.datasource;

import android.support.v4.app.FragmentActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * @Package b12app.vyom.com.flowit.datasource
 * @FileName DataManagerTest
 * @Date 5/2/18, 11:05 PM
 * @Author Created by fengchengding
 * @Description FlowIt
 */
public class DataManagerTest {

    private DataManager mDataManager;

    private static Throwable throwable = new Throwable();

    private Object object = new Object();

    @Mock
    private IDataSource localDataSource;

    @Mock
    private IDataSource remoteDataSource;

    @Mock
    private IDataSource.DbCallback dbCallback;

    @Mock
    private IDataSource.NetworkCallback networkCallback;

    @Captor
    private ArgumentCaptor<IDataSource.DbCallback> dbCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<IDataSource.NetworkCallback> networkCallbackArgumentCaptor;

    @Before
    public void setupDataManager() {
        MockitoAnnotations.initMocks(this);

        mDataManager = DataManager.getInstance(remoteDataSource, localDataSource);
    }

    @After
    public void destroyInstance() throws Exception {
        DataManager.destroyInstance();
    }

    @Test
    public void queryTaskList(FragmentActivity activity, IDataSource.NetworkCallback networkCallback) throws Exception {
        // First call to API
        mDataManager.queryTaskList(activity, networkCallback);

        verify(remoteDataSource).queryTaskList(activity, networkCallbackArgumentCaptor.capture());

        networkCallbackArgumentCaptor.getValue().onFailure(throwable);

        // Verify the remote data source is queried
        verify(remoteDataSource).queryTaskList(activity, networkCallbackArgumentCaptor.capture());

        // Trigger callback so tasks are cached
        networkCallbackArgumentCaptor.getValue().onSuccess(object);

        // Second call to API
        mDataManager.queryTaskList(activity, networkCallbackArgumentCaptor.capture()); // Second call to API
    }

    @Test
    public void updateProject() throws Exception {
    }

    @Test
    public void getMemberList() throws Exception {
    }

    @Test
    public void updateMember() throws Exception {
    }

    @Test
    public void updateTask() throws Exception {
    }

    @Test
    public void getTaskMemberList() throws Exception {
    }

    @Test
    public void queryTaskListByUser() throws Exception {
    }

    @Test
    public void queryProjectList() throws Exception {
    }

    @Test
    public void updateSubTask() throws Exception {
    }

    @Test
    public void querySubTaskList() throws Exception {
    }

    @Test
    public void querySubTaskListByName() throws Exception {
    }

}