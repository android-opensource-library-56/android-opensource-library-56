/**
 * 
 */
package jp.mydns.sys1yagi.android.greendaosample.history;

import java.text.SimpleDateFormat;

import jp.mydns.sys1yagi.android.greendaosample.DaoSession;
import jp.mydns.sys1yagi.android.greendaosample.R;
import jp.mydns.sys1yagi.android.greendaosample.Todo;
import jp.mydns.sys1yagi.android.greendaosample.TodoDao;
import jp.mydns.sys1yagi.android.greendaosample.todo.TodoDaoOpenHelper;
import jp.mydns.sys1yagi.android.greendaosample.todo.TodoLoader;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

class HistoryCursorAdapter extends CursorAdapter implements
        LoaderManager.LoaderCallbacks<Cursor> {
    private static class ViewHolder {
        TextView todo;
        TextView addDate;
        TextView endDate;
    }

    private LayoutInflater mInflator;
    private Fragment mFragment;
    private DaoSession mDaoSession;
    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    public HistoryCursorAdapter(Fragment fragment, DaoSession daoSession) {
        super(fragment.getActivity(), null, false);
        mDaoSession = daoSession;
        mInflator = (LayoutInflater) fragment.getActivity().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mFragment = fragment;
    }

    @Override
    public void bindView(View paramView, Context paramContext,
            Cursor paramCursor) {
        Todo todo = mDaoSession.getTodoDao().readEntity(paramCursor, 0);
        if (todo != null) {
            ViewHolder holder = (ViewHolder) paramView.getTag();
            holder.todo.setText(todo.getTodo());
            holder.addDate.setText("開始:" + mFormat.format(todo.getAddDate()));
            if (todo.getEndDate() != null) {
                holder.endDate.setText("終了:"
                        + mFormat.format(todo.getEndDate()));
            }
        }
    }

    @Override
    public View newView(Context paramContext, Cursor paramCursor,
            ViewGroup paramViewGroup) {
        View view = mInflator.inflate(R.layout.history_item, null);
        ViewHolder holder = new ViewHolder();
        holder.todo = (TextView) view.findViewById(R.id.todo);
        holder.addDate = (TextView) view.findViewById(R.id.add_date);
        holder.endDate = (TextView) view.findViewById(R.id.end_date);
        view.setTag(holder);
        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        String addDateColumn = TodoDao.Properties.EndDate.columnName;
        String orderBy = addDateColumn + " DESC";
        return new TodoLoader(mFragment.getActivity(),
                mDaoSession.getDatabase(), TodoDao.TABLENAME, mDaoSession
                        .getTodoDao().getAllColumns(),
                TodoDao.Properties.Status.columnName + "=?",
                new String[] { Integer
                        .toString(TodoDaoOpenHelper.STATUS_ARCHIVE) }, orderBy);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        this.swapCursor(null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        this.swapCursor(cursor);
    }
}
