/**
 * 
 */
package jp.mydns.sys1yagi.android.stickylistheaderssample;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class MyAdapter extends ArrayAdapter<String> implements
        StickyListHeadersAdapter {
    private LayoutInflater mLayoutInflater;

    public MyAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
        mLayoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public long getHeaderId(int position) {
        return position / 5;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.listitem_header,
                    null, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        int groupId = position / 5;
        holder.text.setText("Group" + groupId);

        return convertView;
    }

    static class ViewHolder {
        public TextView text;

        public ViewHolder(View root) {
            text = (TextView) root.findViewById(R.id.header_text);
        }
    }
}
