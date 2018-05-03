package b12app.vyom.com.flowit.tabfragment.project;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.datasource.IDataSource;
import b12app.vyom.com.flowit.model.Project;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @Package b12app.vyom.com.flowit.tabfragment.project
 * @FileName ProjectFgtPresenterTest
 * @Date 5/2/18, 10:48 PM
 * @Author Created by fengchengding
 * @Description FlowIt
 */
public class ProjectFgtPresenterTest {

    @Mock
    private ProjectFgtContract.IView fragmentView;
    @Mock
    private DataManager mDataManager;
    @Mock
    private IDataSource.NetworkCallback networkCallback;

    @Captor
    private ArgumentCaptor<IDataSource.NetworkCallback> networkCallbackArgumentCaptor;

    private ProjectFgtPresenter projectFgtPresenter;

    @Before
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);

        //generate mock object
        fragmentView = mock(ProjectFgtContract.IView.class);
        mDataManager = mock(DataManager.class);

        projectFgtPresenter = new ProjectFgtPresenter(mDataManager, fragmentView);
    }


    @Test
    public void start() throws Exception {
    }

    @Test
    public void createPresenter_setsThePresenterToView(){

        // Then the presenter is set to the view
        verify(fragmentView).setPresenter(projectFgtPresenter);
    }

    @Test
    public void getProjectList() throws Exception {

        // Then a task is saved in the repository and the view updated
        verify(mDataManager).queryProjectList(networkCallbackArgumentCaptor.capture()); // saved to the model

        // Trigger callback so tasks are cached
        networkCallbackArgumentCaptor.getValue().onSuccess(new Object());

        verify(fragmentView).initRecyclerView(new Project()); // shown in the UI
    }

    @Test
    public void rvItemClick() throws Exception {
    }

}