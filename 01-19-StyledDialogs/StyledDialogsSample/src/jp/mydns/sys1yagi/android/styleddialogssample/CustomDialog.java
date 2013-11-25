/**
 * 
 */
package jp.mydns.sys1yagi.android.styleddialogssample;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import eu.inmite.android.lib.dialogs.ISimpleDialogListener;
import eu.inmite.android.lib.dialogs.SimpleDialogFragment;

/**
 * @author yagitoshihiro
 * 
 */
public class CustomDialog extends SimpleDialogFragment {

    public static void show(FragmentActivity activity) {
        new CustomDialog().show(activity.getSupportFragmentManager(), "custom");
    }

    @Override
    protected Builder build(Builder builder) {
        builder.setTitle("シェア")
                .setView(
                        LayoutInflater.from(getActivity()).inflate(
                                R.layout.custom_dialog, null))
                .setPositiveButton("シェアする", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ISimpleDialogListener listener = getDialogListener();
                        if (listener != null) {
                            listener.onPositiveButtonClicked(100);
                        }
                        dismiss();
                    }
                }).setNegativeButton("閉じる", new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
        return builder;
    }
}
