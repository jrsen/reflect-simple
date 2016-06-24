package com.jrsen.reflectionsimple;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.reflect.annotation.ClassParams;
import com.reflect.annotation.StringParams;
import com.reflect.core.Field;
import com.reflect.core.Method;
import com.reflect.core.Reflection;
import com.reflect.core.StaticField;
import com.reflect.core.StaticMethod;

public class MainActivity extends Activity {

    public static StaticField<String> TAG;

    @ClassParams({Bundle.class})
    public static Method restoreManagedDialogs;

    @StringParams({"com.reflect.datatype.BaseInt"})
    public static StaticMethod<String> savedDialogArgsKeyFor;

    public static Field<WindowManager> mWindowManager;

    static {
        Reflection.init("android.app.Activity", MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.text);
        StringBuilder builder = new StringBuilder();
        builder.append(TAG.get() + "\n");
        builder.append(restoreManagedDialogs.toString() + "\n");
        builder.append(savedDialogArgsKeyFor.invoke(2) + "\n");
        builder.append(mWindowManager.get(this) + "\n");
        textView.setText(builder);

        WindowManager windowManager = mWindowManager.get(this);
        Button button = new Button(this);
        button.setText("fuck");
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        windowManager.addView(button, params);
    }
}
