package jp.mydns.sys1yagi.android.acrasample;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import android.app.Application;

//GAE上で動作しているacra-reporterにクラッシュレポートを送信します。
//送信先のacra-reporterは筆者が運用しているため、コンソールへのアクセスはできません。
//また、予告なく運用を停止する場合があります。
@ReportsCrashes(formKey="", 
formUri="http://sys1yagi-acra-reporter.appspot.com/acrareport",
formUriBasicAuthLogin="acraSample",
formUriBasicAuthPassword="acraSample",
mode=ReportingInteractionMode.DIALOG,
resDialogTitle=R.string.crash_title,
resDialogText=R.string.crash_text,
resDialogIcon=R.drawable.ic_dialog_warn
)
public class AcraSampleApplication extends Application {
    @Override
    public void onCreate() {
        ACRA.init(this);
        
        //Custom Senderをセットする
        //ACRA.getErrorReporter().setReportSender(new MySender());
        super.onCreate();
    }
}
