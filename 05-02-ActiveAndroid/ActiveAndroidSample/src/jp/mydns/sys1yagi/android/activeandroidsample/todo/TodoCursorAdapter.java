package jp.mydns.sys1yagi.android.activeandroidsample.todo;

import java.text.SimpleDateFormat;
import java.util.Date;

import jp.mydns.sys1yagi.android.activeandroidsample.R;
import jp.mydns.sys1yagi.android.activeandroidsample.model.Todo;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;

class TodoCursorAdapter extends CursorAdapter implements
        LoaderManager.LoaderCallbacks<Cursor> {
    private TodoCursorAdapter This() {
        return this;
    }

    private static class ViewHolder {
        TextView todo;
        TextView addDate;
        CheckBox marked;
    }

    private LayoutInflater mInflator;
    private Fragment mFragment;
    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    public TodoCursorAdapter(Fragment fragment) {
        super(fragment.getActivity(), null, false);
        mInflator = (LayoutInflater) fragment.getActivity().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mFragment = fragment;
    }

    @Override
    public void bindView(View paramView, Context paramContext,
            Cursor paramCursor) {
        Todo todo = new Todo();
        todo.loadFromCursor(paramCursor);

        ViewHolder holder = (ViewHolder) paramView.getTag();
        holder.todo.setText(todo.getTodo());
        holder.addDate.setText(mFormat.format(todo.getAddDate()));
        holder.marked.setTag(todo);
        holder.marked.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                    boolean isChecked) {
                Todo todo = (Todo) buttonView.getTag();
                if (isChecked) {
                    todo.setStatus(TodoDaoHelper.STATUS_END);
                    todo.setEndDate(new Date());
                } else {
                    todo.setStatus(TodoDaoHelper.STATUS_NEW);
                    todo.setEndDate(null);
                }
                todo.save();
                mFragment.getLoaderManager().restartLoader(
                        TodoFragment.TODO_LOAD_ID, null, This());
            }
        });
        holder.marked.setChecked(todo.getStatus() == TodoDaoHelper.STATUS_END);

    }

    @Override
    public View newView(Context paramContext, Cursor paramCursor,
            ViewGroup paramViewGroup) {
        View view = mInflator.inflate(R.layout.todo_item, null);
        ViewHolder holder = new ViewHolder();
        holder.todo = (TextView) view.findViewById(R.id.todo);
        holder.addDate = (TextView) view.findViewById(R.id.add_date);
        holder.marked = (CheckBox) view.findViewById(R.id.marked);
        view.setTag(holder);
        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {

        String orderBy = Todo.COLUMN_ADD_DATE + " DESC";
        return new TodoLoader(
                mFragment.getActivity(),
                ActiveAndroid.getDatabase(),
                Todo.TABLENAME,
                null,
                Todo.COLUMN_STATUS + "!=?",
                new String[] { Integer.toString(TodoDaoHelper.STATUS_ARCHIVE) },
                orderBy);
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
