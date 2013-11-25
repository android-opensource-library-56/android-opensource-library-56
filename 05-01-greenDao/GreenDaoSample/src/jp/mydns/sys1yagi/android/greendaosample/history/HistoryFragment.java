package jp.mydns.sys1yagi.android.greendaosample.history;

import jp.mydns.sys1yagi.android.greendaosample.DaoSession;
import jp.mydns.sys1yagi.android.greendaosample.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HistoryFragment extends Fragment {
    private final static String TAG = HistoryFragment.class.getSimpleName();
    public static final int HISTORY_LOAD_ID = 0;
    private DaoSession mDaoSession;
    private HistoryCursorAdapter mAdapter;

    public HistoryFragment(DaoSession daoSession) {
        mDaoSession = daoSession;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new HistoryCursorAdapter(this, mDaoSession);
        ListView listView = (ListView) getView().findViewById(R.id.list_view);
        listView.setAdapter(mAdapter);

        getLoaderManager().initLoader(HISTORY_LOAD_ID, null, mAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, null);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getLoaderManager().destroyLoader(0);
    }

    public void reload() {
        getLoaderManager().restartLoader(HISTORY_LOAD_ID, null, mAdapter);
    }
}
