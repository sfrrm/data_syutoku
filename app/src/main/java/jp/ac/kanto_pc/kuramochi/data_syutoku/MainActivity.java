package jp.ac.kanto_pc.kuramochi.data_syutoku;

//時刻合わせボタン作成
//Ver3　
/*
* グラフ表示     　×
* データ切り替え   ×
* アップロード     ×
* ダウンロード     ×
* Webアクセス      ○
* */

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import java.util.ArrayList;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private LineChart mChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //グラフの作成
        setContentView(R.layout.activity_main);

        mChart = findViewById(R.id.line_chart);

        // Grid背景色
        mChart.setDrawGridBackground(false);

        // no description text
        mChart.getDescription().setEnabled(false);

        // Grid縦軸を破線
        XAxis xAxis = mChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = mChart.getAxisLeft();
        // Y軸最大最小設定
        leftAxis.setAxisMaximum(150f);
        leftAxis.setAxisMinimum(0f);
        // Grid横軸を破線
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(true);

        // 右側の目盛り
        mChart.getAxisRight().setEnabled(false);

        // add data
        setData();

        mChart.animateX(2500);
        //mChart.invalidate();

        // dont forget to refresh the drawing
        // mChart.invalidate();


        // 全ボタンを設定
        Button button = findViewById(R.id.button);//Web
        Button button2 = findViewById(R.id.button2);//ダウンロード
        Button button3 = findViewById(R.id.button3);//アップロード
        Button button4 = findViewById(R.id.button4);//右
        Button button5 = findViewById(R.id.button5);//左
        Button button6 = findViewById(R.id.button6);//時刻合わせ

        // TextView の設定
        textView = findViewById(R.id.text_view);

        //Webページボタン
        button.setOnClickListener(new View.OnClickListener() {
            //Uri uri = Uri.parse("https://www.msn.com/ja-jp/money/markets/");//URLの指定
            Uri uri = Uri.parse("http://10.100.56.165/test.php");//URLの指定
            Intent i = new Intent(Intent.ACTION_VIEW,uri);

            @Override
            public void onClick(View view) {//ボタンが押された時
                textView.setText("Webページへ");//文字列の変更
                startActivity(i);//URLにアクセス


            }
        });
        //ダウンロードボタン
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//ボタンが押された時
                textView.setText("ダウンロード");//文字列の変更
            }
        });
        //アップロードボタン
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//ボタンが押された時
                textView.setText("アップロード");//文字列の変更
            }
        });
        //ダウンロードボタン
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//ボタンが押された時
                textView.setText("次のファイルへ");//文字列の変更
            }
        });
        //アップロードボタン
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//ボタンが押された時
                textView.setText("前のファイルへ");//文字列の変更
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//ボタンが押された時
                textView.setText("時刻合わせ");//文字列の変更
            }
        });

    }
    private void setData() {
        // Entry()を使ってLineDataSetに設定できる形に変更してarrayを新しく作成
        int data[] = {116, 111, 112, 121, 102, 83,
                99, 101, 74, 105, 120, 112,
                109, 102, 107, 93, 82, 99,
                110, 12,  100,  111,11,13,
                110, 12,  100,  111,11,13,
        };

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            values.add(new Entry(i, data[i], null, null));
        }

        LineDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {

            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "温度");

            set1.setDrawIcons(false);
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(0f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            set1.setFillColor(Color.WHITE);

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets

            // create a data object with the datasets
            LineData lineData = new LineData(dataSets);

            // set data
            mChart.setData(lineData);
        }
    }
}
