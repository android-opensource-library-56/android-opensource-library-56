package jp.mydns.sys1yagi.android.holographlibrarysample;

import java.util.ArrayList;

import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;
import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;
import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;
import com.echo.holographlibrary.BarGraph.OnBarClickedListener;
import com.echo.holographlibrary.LineGraph.OnPointClickedListener;
import com.echo.holographlibrary.PieGraph.OnSliceClickedListener;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;

public class HoloGraphLibrarySampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holo_graph_library_sample);
        initBarGraph();
        initLineGrapth();
        initPieGraph();
    }

    private LinePoint createPointForLine(int x, int y) {
        LinePoint p = new LinePoint();
        p.setX(x);
        p.setY(y);
        return p;
    }

    /**
     * 折れ線グラフを初期化する。
     */
    private void initLineGrapth() {
        LineGraph lineGraph = (LineGraph) findViewById(R.id.linegraph);

        Line line = new Line();
        line.addPoint(createPointForLine(0, 5));
        line.addPoint(createPointForLine(2, 8));
        line.addPoint(createPointForLine(4, 6));
        line.addPoint(createPointForLine(8, 10));
        line.addPoint(createPointForLine(10, 4));
        line.setColor(Color.parseColor("#FFBB33"));

        lineGraph.addLine(line);
        lineGraph.setRangeY(0, 10);
        lineGraph.setOnPointClickedListener(new OnPointClickedListener() {
            @Override
            public void onClick(int lineIndex, int pointIndex) {

            }
        });

    }

    private PieSlice createPieForGraph(String colorCode, String title, int value) {
        PieSlice slice = new PieSlice();
        slice.setColor(Color.parseColor(colorCode));
        slice.setTitle(title);
        slice.setValue(value);
        return slice;
    }

    /**
     * パイグラフを初期化する。
     */
    private void initPieGraph() {
        PieGraph pieGraph = (PieGraph) findViewById(R.id.piegraph);
        pieGraph.addSlice(createPieForGraph("#99CC00", "米", 20));
        pieGraph.addSlice(createPieForGraph("#FFBB33", "パン", 15));
        pieGraph.addSlice(createPieForGraph("#AA66CC", "シリアル", 8));
        pieGraph.addSlice(createPieForGraph("#CC2266", "キャッサバ", 3));
        pieGraph.setOnSliceClickedListener(new OnSliceClickedListener() {
            @Override
            public void onClick(int index) {
            }
        });
    }

    private Bar createPointForBar(String colorCode, String name, int value) {
        Bar bar = new Bar();
        bar.setColor(Color.parseColor(colorCode));
        bar.setName(name);
        bar.setValue(value);
        return bar;
    }

    /**
     * 棒グラフを初期化する。
     */
    private void initBarGraph() {
        ArrayList<Bar> points = new ArrayList<Bar>();
        points.add(createPointForBar("#99CC00", "X社", 11));
        points.add(createPointForBar("#990000", "Y社", 7));

        BarGraph barGraph = (BarGraph) findViewById(R.id.bargraph);
        barGraph.setBars(points);

        barGraph.setOnBarClickedListener(new OnBarClickedListener() {
            @Override
            public void onClick(int index) {

            }
        });
    }
}
