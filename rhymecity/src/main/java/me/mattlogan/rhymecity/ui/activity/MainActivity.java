package me.mattlogan.rhymecity.ui.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.ProgressBar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.mattlogan.rhymecity.R;
import me.mattlogan.rhymecity.ui.fragment.SearchFragment;

public class MainActivity extends ActionBarActivity
        implements ToolbarActivity, ProgressIndicatorActivity, FragmentContainerActivity {

    @InjectView(R.id.main_activity_toolbar) Toolbar toolbar;
    @InjectView(R.id.main_activity_progress_indicator) ProgressBar progressIndicator;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        fragmentManager = getFragmentManager();

        if (savedInstanceState == null) {
            goToSearchFragment();
        }
    }

    private void goToSearchFragment() {
        fragmentManager.beginTransaction()
                .add(R.id.main_activity_fragment_container, SearchFragment.newInstance())
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public ProgressBar getProgressIndicator() {
        return progressIndicator;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.main_activity_fragment_container;
    }
}
