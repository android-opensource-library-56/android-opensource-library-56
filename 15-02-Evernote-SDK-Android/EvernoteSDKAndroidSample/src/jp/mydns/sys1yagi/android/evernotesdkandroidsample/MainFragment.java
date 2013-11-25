package jp.mydns.sys1yagi.android.evernotesdkandroidsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.evernote.client.android.AsyncNoteStoreClient;
import com.evernote.client.android.ClientFactory;
import com.evernote.client.android.EvernoteSession;
import com.evernote.client.android.EvernoteSession.EvernoteService;
import com.evernote.client.android.OnClientCallback;
import com.evernote.edam.notestore.NoteFilter;
import com.evernote.edam.notestore.NoteList;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.NoteSortOrder;

public class MainFragment extends ListFragment {
    private final static String TAG = MainFragment.class.getSimpleName();
    EvernoteService mEvernoteService = EvernoteService.SANDBOX;

    private SessionProvider mProvider;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    public MainFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View header = layoutInflater.inflate(R.layout.add_note, null);
        getListView().addHeaderView(header);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1);
        setListAdapter(adapter);

        getListView().setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos,
                    long id) {
                Log.d(TAG, "item click:" + pos);
                if (pos == 0) {
                    // add
                    Intent intent = new Intent(getActivity(),
                            EditNoteActivity.class);
                    startActivity(intent);
                } else {
                    // edit
                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        // ここで読みに行く
        ClientFactory factory = mProvider.getEvernoteSession()
                .getClientFactory();
        try {
            AsyncNoteStoreClient client = factory.createNoteStoreClient();

            NoteFilter filter = new NoteFilter();
            filter.setOrder(NoteSortOrder.UPDATED.getValue());
            filter.setWords("evernote_sample");
            client.findNotes(filter, 0, 20, new OnClientCallback<NoteList>() {
                public void onSuccess(NoteList data) {
                    ArrayAdapter<String> adapter = (ArrayAdapter<String>) getListAdapter();
                    adapter.clear();
                    for (Note note : data.getNotes()) {
                        adapter.add(note.getTitle());
                    }
                    adapter.notifyDataSetChanged();
                };

                @Override
                public void onException(Exception exception) {
                    exception.printStackTrace();
                    Toast.makeText(getActivity(), "接続に失敗しました",
                            Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "接続に失敗しました", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mProvider = (SessionProvider) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SessionProvider");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface SessionProvider {
        public EvernoteSession getEvernoteSession();
    }
}
