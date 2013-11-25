package jp.mydns.sys1yagi.android.jsonpullparsersample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Yahoo Pipeを通してはてなブックマークの新着のRSSをjsonに変換しています。
 * データのサンプルはassets/json.txtにあります。
 * jsonの形式に従ってJson Pull Parser用モデルを設計します。
 * YahooPipeJsonクラスが基点となるモデルクラスです。
 * 各種モデルクラスはjson.txtの形式のデータを処理する為に作られています。
 * RSSのエントリを取り出す事が目的なので、一部のデータを省略していますが正しく動作します。
 * @author yagitoshihiro
 *
 */
public class JsonPullParserSampleActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_pull_parser_sample);
    }
}
