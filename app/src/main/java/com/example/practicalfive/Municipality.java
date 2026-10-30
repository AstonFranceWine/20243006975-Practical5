package com.example.practicalfive;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Municipality extends AppCompatActivity {

    private String currentCity = "";
    private String currentUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        // 1. 绑定 UI 组件
        TextView tvCityName = findViewById(R.id.tvCityName);
        ImageView ivCityImage = findViewById(R.id.ivCityImage);
        TextView tvCityInfo = findViewById(R.id.tvCityInfo);
        Button btnVisitSite = findViewById(R.id.btnVisitSite);

        // 2. 接收从 MainActivity 传过来的数据
        String passedName = getIntent().getStringExtra("MUNICIPALITY_NAME");
        // 再次校验，防止数据被篡改或为空
        currentCity = TextProcessor.processMunicipality(passedName);

        if (currentCity == null) {
            Toast.makeText(this, "Error: Invalid data received.", Toast.LENGTH_SHORT).show();
            finish(); // 关闭当前页面
            return;
        }

        // 3. 准备变量来存储对应城市的资源
        int colorResId = 0;
        int imageResId = 0;
        int arrayIndex = 0;

        // 根据城市名称设置对应的颜色、图片、网址和数组索引
        // 注意：题目要求的数组顺序是 Beijing, Shanghai, Chongqing, Tianjin
        switch (currentCity) {
            case "Beijing":
                colorResId = R.color.municipality_Beijing;
                imageResId = R.drawable.beijing;
                currentUrl = "https://english.beijing.gov.cn/";
                arrayIndex = 0;
                break;
            case "Shanghai":
                colorResId = R.color.municipality_shanghai;
                imageResId = R.drawable.shanghai;
                currentUrl = "https://english.shanghai.gov.cn/";
                arrayIndex = 1;
                break;
            case "Chongqing":
                colorResId = R.color.municipality_chongqing;
                imageResId = R.drawable.chongqing;
                currentUrl = "https://english.cq.gov.cn/";
                arrayIndex = 2;
                break;
            case "Tianjin":
                colorResId = R.color.municipality_tianjin;
                imageResId = R.drawable.tianjin;
                currentUrl = "https://en.tj.gov.cn/";
                arrayIndex = 3;
                break;
        }

        // 4. 动态修改 UI
        tvCityName.setText(currentCity);

        // 设置标题背景色和文字颜色（使用浅色文字保证深色背景可读）
        tvCityName.setBackgroundColor(ContextCompat.getColor(this, colorResId));
        tvCityName.setTextColor(ContextCompat.getColor(this, R.color.color_textlight));

        // 设置图片
        ivCityImage.setImageResource(imageResId);

        // 获取字符串数组里的介绍文字
        String[] infoArray = getResources().getStringArray(R.array.municipalities_array);
        tvCityInfo.setText(infoArray[arrayIndex]);

        // 设置介绍文字的颜色与城市主题色一致
        tvCityInfo.setTextColor(ContextCompat.getColor(this, colorResId));

        // 设置按钮背景颜色（使用 tint）
        btnVisitSite.getBackground().setTint(ContextCompat.getColor(this, colorResId));
        btnVisitSite.setTextColor(ContextCompat.getColor(this, R.color.color_textlight));

        // 5. 点击按钮：使用隐式 Intent 打开网页
        btnVisitSite.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentUrl));
            try {
                startActivity(browserIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "No application found to open the website.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}