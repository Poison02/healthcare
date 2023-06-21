package cdu.zch.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {

    // 枚举的实验测试信息
    private String[][] packages =
            {
                    {"Package 1 : Full Body Checkup", "", "", "", "999" },
                    {"Package 2 : Blood Glucose Fasting", "", "", "", "299" },
                    {"Package 3 : COVID-19 Antibody - IgG", "", "", "", "899" },
                    {"Package 4 : Thyroid Check ", "", "", "", "499" },
                    {"Package 5 : Immunity Check", "", "", "", "699" }
            };

    private String[] package_details = {
            "Blood Glucose Fasting\n" +
                    " Complete Hemogram\n" +
                    "HbA1c\n" +
                    " Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "LDH Lactate Dehydrogenase,Serum\n" +
                    "Lipid Profile\n" +
                    "Liver Function Test",
            "BLood Glucose Fasting ",
            "COVID-19 Antibody - IgG",
            "Thyroid Profile-Total (T3，T4 & TSH Ultra-sensitive)",
            "Complete Hemognam\n" +
                    "CRP (C Reactive Protein) Quantitative,Serum\n" +
                    " Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "Vitamin D Total-25 Hydnoxy\n" +
                    "Liver Function Test\n" +
                    "Lipid Profile"
    };
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoToCart, btnBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btnGoToCart = findViewById(R.id.buttonBMCartCheckout);
        btnBack = findViewById(R.id.buttonBMCartBack);
        listView = findViewById(R.id.listViewCartBM);

        // 返回Home
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost:" + packages[i][4] + "/-");
            list.add(item);
        }

        sa = new SimpleAdapter(
                this,
                list,
                R.layout.multi_lines,
                new String[] {"line1", "line2", "line3", "line4", "line5"},
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );
        listView.setAdapter(sa);

        // 点击某一栏进入实验测试详情页面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(LabTestActivity.this, LabTestDetailsActivity.class);
                it.putExtra("text1", packages[position][0]);
                it.putExtra("text2", package_details[position]);
                it.putExtra("text3", packages[position][4]);
                startActivity(it);
            }
        });

        // 去购物车界面
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));
            }
        });
    }
}