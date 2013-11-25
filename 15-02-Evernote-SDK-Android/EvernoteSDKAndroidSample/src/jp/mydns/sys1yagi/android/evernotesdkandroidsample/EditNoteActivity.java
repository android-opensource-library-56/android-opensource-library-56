package jp.mydns.sys1yagi.android.evernotesdkandroidsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.evernote.client.android.AsyncNoteStoreClient;
import com.evernote.client.android.ClientFactory;
import com.evernote.client.android.EvernoteSession;
import com.evernote.client.android.EvernoteUtil;
import com.evernote.client.android.OnClientCallback;
import com.evernote.edam.type.Note;

public class EditNoteActivity extends Activity {
    private EditNoteActivity This() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        findViewById(R.id.cancel_button).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        findViewById(R.id.ok_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 保存処理
                EditText titleEdit = EditText.class
                        .cast(findViewById(R.id.note_title));
                EditText contentEdit = EditText.class
                        .cast(findViewById(R.id.note_content));
                String title = titleEdit.getText().toString();
                String content = contentEdit.getText().toString();

                if (title.isEmpty()) {
                    titleEdit.setError("タイトルを入力して下さい");
                } else if (content.isEmpty()) {
                    contentEdit.setError("本文を入力して下さい");
                } else {
                    Note note = new Note();
                    note.setTitle(title);
                    note.setContent(EvernoteUtil.NOTE_PREFIX + content
                            + EvernoteUtil.NOTE_SUFFIX);
                    note.addToTagNames("evernote_sample");
                    saveNote(note);
                }
            }
        });
    }

    private void saveNote(Note note) {
        EvernoteSession session = EvernoteSessionManager.getInstance(this);
        ClientFactory factory = session.getClientFactory();
        try {
            AsyncNoteStoreClient client = factory.createNoteStoreClient();
            // TODO progress dialogを出す
            // TODO updateの場合を考慮する
            client.createNote(note, new OnClientCallback<Note>() {
                @Override
                public void onSuccess(Note data) {
                    Toast.makeText(This(), "保存が完了しました", Toast.LENGTH_SHORT)
                            .show();
                    setResult(RESULT_OK);
                    finish();
                }

                @Override
                public void onException(Exception exception) {
                    exception.printStackTrace();
                    Toast.makeText(This(), "保存に失敗しました", Toast.LENGTH_SHORT)
                            .show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "保存に失敗しました", Toast.LENGTH_SHORT).show();
            return;
        }
    }

}
