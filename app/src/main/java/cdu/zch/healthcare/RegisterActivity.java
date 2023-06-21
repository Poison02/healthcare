package cdu.zch.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername, edEmail, edPassword, edConfirmPassword;
    Button registerBtn;
    TextView toLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edUsername = findViewById(R.id.editTextRegisterUsername);
        edEmail = findViewById(R.id.editTextRegisterEmail);
        edPassword = findViewById(R.id.editTextRegisterPassword);
        edConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        registerBtn = findViewById(R.id.RegisterBtn);
        toLogin = findViewById(R.id.ToLogin);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirmPassword = edConfirmPassword.getText().toString();
                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                if (username.length() == 0 || email.length() == 0
                        || password.length() == 0 || confirmPassword.length() == 0) {
                    Toast.makeText(getApplicationContext(), "请输入对应的栏位", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(confirmPassword) == 0)  {
                        if (isValid(password)) {
                            db.register(username, email, password);
                            Toast.makeText(getApplicationContext(), "注册成功！", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "密码必须包含8个字母、数字、特殊符号！", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "两次输入的密码不一致！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    // 判断输入的密码是否合法，合法的判断标准：数字、字母、特殊字符加起来是否八个及以上
    public static boolean isValid(String passwordHere) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (passwordHere.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < passwordHere.length(); p++) {
                if (Character.isLetter(passwordHere.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < passwordHere.length(); r++) {
                if (Character.isDigit(passwordHere.charAt(r))) {
                    f2 = 1;
                }
            }
            for (int s = 0; s < passwordHere.length(); s++) {
                char c = passwordHere.charAt(s);
                if (c >= 33 && c <= 46 || c == 64) {
                    f3 = 1;
                }
            }
            return f1 == 1 && f2 == 1 && f3 == 1;
        }
    }
}