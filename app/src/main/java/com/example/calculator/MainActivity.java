package com.example.calculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

    private static final String TAG = "numbers";
    Button btn_1; // 数字1
    Button btn_2; // 数字2
    Button btn_3; // 数字3
    Button btn_4; // 数字4
    Button btn_5; // 数字5
    Button btn_6; // 数字6
    Button btn_7; // 数字7
    Button btn_8; // 数字8
    Button btn_9; // 数字9
    Button btn_0; // 数字0
    Button btn_clear; // 清0
    Button btn_del; // 删除健
    Button btn_divide; // 除号
    Button btn_multiply; // *号
    Button btn_minus; // -号
    Button btn_plus; // +号
    Button btn_point; // 小数点
    Button btn_equals; // =
    TextView et_Text; // 显示文本

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取页面上的控件
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_equals = (Button) findViewById(R.id.btn_equals);
        et_Text = (TextView) findViewById(R.id.tv_Text);

        // 按钮的单击事件
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_0.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_equals.setOnClickListener(this);

        //初始化展示区
        num_init();
    }

    class numBuffer {
        double value = 0;
        boolean empty = true;
        boolean intable = false;

        numBuffer(double d) {
            value = d;
            empty = false;
            if (isIntegerForDouble(d)) {
                intable = true;
            } else intable = false;
        }

        boolean isIntegerForDouble(double obj) {
            double eps = 1e-10;  // 精度范围
            return obj - Math.floor(obj) < eps;
        }

        void clear() {
            value = 0;
            empty = true;
            intable = false;
        }
    }

    class opBuffer {
        String value = null;
        boolean empty = true;

        opBuffer(String str) {
            value = str;
            empty = false;
        }

        void clear() {
            value = null;
            empty = true;
        }
    }

    //定义第一个操作数和第二个操作数
    numBuffer double1, double2;
    double d1, d2;
    //定义运算符
    opBuffer op;
    String operator = "";
    //输入标识符
    boolean input_flag = true;

    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    @Override
    public void onClick(View v) {
        String str = et_Text.getText().toString();

        switch (v.getId()) {
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_0:
            case R.id.btn_point:
                // 点击数字按钮和小数点时，在文本内追加内容
                num_concate(v, input_flag);
                break;
            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                input_flag = false;
                d1 = Double.parseDouble(et_Text.getText().toString());
                double1 = new numBuffer(d1);
                // 获取点击的运算符
                operator = ((Button) v).getText().toString();
                op = new opBuffer(operator);
                break;
            case R.id.btn_clear:
                // 清空缓冲区、清空文本内容
                clear();
                break;
            case R.id.btn_del:
                // 点击删除按钮，删除一个字符
                delete();
                break;
            case R.id.btn_equals:
                // 计算结果方法，获取第二个输入的数字
                // int start = str.lastIndexOf(operator);
                // d2 = Double.parseDouble(str.substring(start + 1, str.length()));
                if (input_flag) {
                    d2 = Double.parseDouble(et_Text.getText().toString());
                    double2 = new numBuffer(d2);
                }
                else {
                    if (double2 != null)
                        double1 = double2;
                    d2 = Double.parseDouble(et_Text.getText().toString());
                    double2 = new numBuffer(d2);
                }
                getResult(double2, double1, op);
                input_flag = false;
                break;
        }
    }

    // 计算结果
    private void getResult(numBuffer double1, numBuffer double2, opBuffer op) {
        // 计算结果
        Log.d(TAG, "getResult: " + double1.value + double2.value);
        double result = 0;
        if (op.value.equals("+")) {
            result = double1.value + double2.value;
        } else if (op.value.equals("-")) {
            result = double1.value - double2.value;
        } else if (op.value.equals("×")) {
            result = double1.value * double2.value;
        } else if (op.value.equals("÷")) {
            if (double2.value == 0) {
                result = 0;
            } else {
                result = double1.value / double2.value;
            }
        }

        // 如果不包含小数点和除法运算
        if (double1.intable && double2.intable && (op.value != "÷")) {
            et_Text.setText(((int) result) + "");
        } else {
            et_Text.setText(result + "");
        }
    }

    @SuppressLint("SetTextI18n")
    private void num_concate(View v, boolean flag) {
        String str = et_Text.getText().toString();
        if (((Button) v).getText().toString().equals(".") && flag && str.equals("0")) {
            et_Text.setText(str + ((Button) v).getText().toString());
            input_flag = true;
        } else if (str.equals("0") && flag) {
            et_Text.setText(((Button) v).getText().toString());
            input_flag = true;
        } else if (!str.equals("0") && flag) {
            et_Text.setText(str + ((Button) v).getText().toString());
            input_flag = true;
        } else if (!flag) {
            et_Text.setText(((Button) v).getText().toString());
            input_flag = true;
        }
    }

    private void clear() {
        d1 = 0;
        d2 = 0;
        operator = "";
        double1 = null;
        double2 = null;
        op = null;
        et_Text.setText("0");
    }

    private void delete() {
        String str = et_Text.getText().toString();
        if (str != null && !str.equals("0")) {
            if (str.length() > 1)
                str = str.substring(0, str.length() - 1);
            else
                str = "0";
            et_Text.setText(str);
        } else {
            et_Text.setText("0");
        }
    }

    private void num_init() {
        et_Text.setText("0");
    }

}