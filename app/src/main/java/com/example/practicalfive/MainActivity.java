package com.example.practicalfive;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etMunicipality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 通过 ID 找到我们刚才在 XML 中写的输入框
        etMunicipality = findViewById(R.id.etMunicipality);

        // 2. 监听键盘上的 "Go" (前往) 键被按下的动作
        etMunicipality.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE) {

                // 获取用户输入的内容
                String input = etMunicipality.getText().toString();

                // 3. 调用你等下要创建的 TextProcessor 类进行校验
                String validatedName = TextProcessor.processMunicipality(input);

                if (validatedName == null) {
                    // 如果返回 null，说明输入为空或输入了不支持的城市（如 Guangzhou）
                    Toast.makeText(MainActivity.this, "Invalid input! Please enter Beijing, Shanghai, Tianjin, or Chongqing.", Toast.LENGTH_LONG).show();
                } else {
                    // 如果输入有效（例如输入了 beijing，变成了 Beijing）
                    // 4. 使用显式 Intent 跳转到第二个界面 Municipality
                    Intent intent = new Intent(MainActivity.this, Municipality.class);

                    // 5. 把验证后的名字通过 Extra 传递过去，键名定为 "MUNICIPALITY_NAME"
                    intent.putExtra("MUNICIPALITY_NAME", validatedName);
                    startActivity(intent);

                    // 清空输入框，方便下次输入
                    etMunicipality.setText("");
                }
                return true; // 告诉系统我们已经处理了这个按键
            }
            return false;
        });
    }
}