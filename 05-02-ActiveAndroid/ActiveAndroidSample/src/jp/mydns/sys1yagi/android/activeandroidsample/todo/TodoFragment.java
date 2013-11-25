package jp.mydns.sys1yagi.android.activeandroidsample.todo;

import java.util.Date;

import jp.mydns.sys1yagi.android.activeandroidsample.R;
import jp.mydns.sys1yagi.android.activeandroidsample.model.Todo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

public class TodoFragment extends Fragment {
    private final static String TAG = TodoFragment.class.getSimpleName();
    public static final int TODO_LOAD_ID = 0;

    private TodoCursorAdapter mAdapter;

    public TodoFragment() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new TodoCursorAdapter(this);
        ListView listView = (ListView) getView().findViewById(R.id.list_view);
        listView.setAdapter(mAdapter);

        View view = getView();
        final EditText editText = (EditText) view.findViewById(R.id.edit_todo);
        view.findViewById(R.id.add_todo).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // add db
                        String todoText = editText.getText().toString();
                        if (!"".equals(todoText)) {
                            Todo todo = new Todo();
                            todo.setTodo(todoText);
                            todo.setAddDate(new Date());
                            todo.setStatus(TodoDaoHelper.STATUS_NEW);
                            todo.setEndDate(null);
                            editText.setText("");
                            todo.save();
                            reload();
                        }
                    }
                });

        getLoaderManager().initLoader(TODO_LOAD_ID, null, mAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, null);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getLoaderManager().destroyLoader(0);
    }

    public void reload() {
        getLoaderManager().restartLoader(TODO_LOAD_ID, null, mAdapter);
    }
}
