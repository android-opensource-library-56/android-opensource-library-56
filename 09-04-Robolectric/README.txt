実機やエミュレータなしでAndroidアプリケーションをテストするためのライブラリ
サポートバージョン: Android 1.6 以上
License: Apache 2.0
タイプ: Mavan
公式サイト: http://robolectric.org/

2つのサンプルコードの内、RobolectricSampleTestはJavaプロジェクトです。
RobolectricSampleTestをインポートしたあとに必要な手順は以下の通りです。

・ビルドパスのSourceでRobolectricSample/testをリンクする
・ビルドパスのProjectsにRobolectricSampleを追加する
・ビルドパスのLibrariesにjUnit4を追加する
・ビルドパスのLibrariesにandroid.jarを追加する

詳細な導入手順や使い方については書籍を参照して下さい。
