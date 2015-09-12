package me.mattlogan.rhymecity.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.mattlogan.rhymecity.R;

public final class RhymesAdapter extends BaseAdapter {

    private final Context context;
    private final List<String> rhymeList;

    public RhymesAdapter(Context context, List<String> rhymeList) {
        this.context = context;
        this.rhymeList = rhymeList;
    }

    @Override
    public int getCount() {
        return rhymeList == null ? 0 : rhymeList.size();
    }

    @Override
    public Object getItem(int position) {
        return rhymeList == null ? null : rhymeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        @Bind(R.id.rhyme_list_item_text) TextView rhymeText;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder vh;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.rhyme_list_item, parent, false);
            vh = new ViewHolder();
            ButterKnife.bind(vh, view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        vh.rhymeText.setText(rhymeList.get(position));

        return view;
    }
}
