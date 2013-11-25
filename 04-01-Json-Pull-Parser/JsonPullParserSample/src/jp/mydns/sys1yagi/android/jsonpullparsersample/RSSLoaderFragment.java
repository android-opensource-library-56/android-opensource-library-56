/**
 * 
 */
package jp.mydns.sys1yagi.android.jsonpullparsersample;

import jp.mydns.sys1yagi.android.jsonpullparsersample.model.RSS;
import jp.mydns.sys1yagi.android.jsonpullparsersample.model.YahooPipeJsonGen;
import net.vvakame.util.jsonpullparser.util.OnJsonObjectAddListener;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class RSSLoaderFragment extends ListFragment {

    private ArrayAdapter<RSS> mAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getListView().setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos,
                    long id) {
                RSS rss = (RSS) adapter.getItemAtPosition(pos);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(rss.getLink()));
                startActivity(intent);
            }
        });

        new AsyncTask<String, RSS, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                String url = params[0];
                try {
                    YahooPipeJsonGen.get(
                            getActivity().getAssets().open("json.txt"),
                            new OnJsonObjectAddListener() {
                                // YahooPipeJsonGen.get(new
                                // URL(url).openStream(), new
                                // OnJsonObjectAddListener() {
                                @Override
                                public void onAdd(Object obj) {
                                    if (obj instanceof RSS) {
                                        publishProgress((RSS) obj);
                                    }
                                }
                            });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(RSS... values) {
                if (mAdapter == null) {
                    mAdapter = new ArrayAdapter<RSS>(getActivity(),
                            R.layout.list_item, R.id.text);
                    setListAdapter(mAdapter);
                }
                for (RSS rss : values) {
                    mAdapter.add(rss);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }.execute("http://pipes.yahoo.com/pipes/pipe.run?_id=DJEg41Ac3BG8IAI2E5PZnA&_render=json&url=http%3a%2f%2fb%2ehatena%2ene%2ejp%2fentrylist%3fsort%3dhot%26threshold%3d100%26mode%3drss");
    }
}
