package me.mattlogan.rhymecity.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.mattlogan.rhymecity.R;
import me.mattlogan.rhymecity.ui.activity.ToolbarActivity;
import me.mattlogan.rhymecity.ui.adapter.RhymesAdapter;

public class RhymesFragment extends Fragment {

    private static String RHYME_LIST_KEY = "rhyme_list";

    @InjectView(R.id.rhymes_list_view) ListView listView;

    private List<String> rhymeList;

    public static RhymesFragment newInstance(List<String> rhymeList) {
        RhymesFragment fragment = new RhymesFragment();

        Bundle args = new Bundle();
        args.putSerializable(RHYME_LIST_KEY, (Serializable) rhymeList);
        fragment.setArguments(args);

        return fragment;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rhymeList = (List<String>) getArguments().getSerializable(RHYME_LIST_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rhymes, container, false);
        ButterKnife.inject(this, view);
        listView.setAdapter(new RhymesAdapter(getActivity(), rhymeList));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((ToolbarActivity) getActivity()).getToolbar().setTitle(getString(R.string.rhymes));
    }
}
