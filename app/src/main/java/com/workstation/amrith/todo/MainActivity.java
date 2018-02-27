package com.workstation.amrith.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userName,passWord;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText)findViewById(R.id.username);
        passWord = (EditText)findViewById(R.id.password);

        login = (Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String username = userName.getText().toString();
                String password = passWord.getText().toString();

                if(username.equals(""))
                {
                    userName.setError("Please Enter Username");
                    return;
                }
                else if(password.equals(""))
                {
                    passWord.setError("Please Enter Password");
                    return;
                }
                else
                {
                    if (username.equals("admin") && password.equals("admin")) {
                        startActivity(new Intent(MainActivity.this, Todo.class));
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Auth Error",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });
    }
}
