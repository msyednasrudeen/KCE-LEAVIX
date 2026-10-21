package com.kce.leavix;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class MainActivity extends Activity {

    private final int BLUE = Color.rgb(23, 70, 162);
    private final int YELLOW = Color.rgb(255, 201, 40);
    private final int DARK = Color.rgb(25, 39, 67);
    private final int GRAY = Color.rgb(105, 119, 143);

    private LinearLayout root;
    private EditText idInput, passwordInput;
    private String selectedRole = "Student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(BLUE);
        getWindow().setNavigationBarColor(Color.WHITE);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        showLogin();
    }

    private GradientDrawable shape(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        return d;
    }

    private TextView text(String value, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }

    private void showLogin() {
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(26, 28, 26, 28);
        root.setBackgroundColor(Color.WHITE);
        scroll.addView(root);

        TextView logo = text("K", 35, BLUE, true);
        logo.setGravity(Gravity.CENTER);
        logo.setBackground(shape(YELLOW, 22));
        root.addView(logo, new LinearLayout.LayoutParams(66, 66));

        addSpace(18);

        root.addView(text("KCE LEAVIX", 29, BLUE, true));
        root.addView(text("SMART LEAVE MANAGEMENT SYSTEM", 11, GRAY, true));
        addSpace(8);
        root.addView(text("KINGS COLLEGE OF ENGINEERING", 13, DARK, true));
        root.addView(text("Computer Science and Engineering", 13, GRAY, false));

        addSpace(32);
        root.addView(text("Welcome back!", 25, DARK, true));
        root.addView(text("Sign in to manage your leave applications.", 14, GRAY, false));
        addSpace(24);

        root.addView(text("Login as", 14, DARK, true));
        addSpace(10);

        LinearLayout roles = new LinearLayout(this);
        roles.setOrientation(LinearLayout.HORIZONTAL);

        String[] roleNames = {"Student", "Staff", "HOD"};
        for (String role : roleNames) {
            TextView button = text(role, 14, role.equals(selectedRole) ? Color.WHITE : BLUE, true);
            button.setGravity(Gravity.CENTER);
            button.setPadding(8, 13, 8, 13);
            button.setBackground(shape(role.equals(selectedRole) ? BLUE : 0xFFF0F4FC, 12));

            LinearLayout.LayoutParams rp =
                    new LinearLayout.LayoutParams(0, -2, 1);
            rp.setMargins(0, 0, 8, 0);
            roles.addView(button, rp);

            button.setOnClickListener(v -> {
                selectedRole = role;
                showLogin();
            });
        }
        root.addView(roles);

        addSpace(20);
        root.addView(text("College ID / Register Number", 13, DARK, true));
        addSpace(7);

        idInput = new EditText(this);
        idInput.setSingleLine(true);
        idInput.setTextSize(15);
        idInput.setHint("Enter your college ID");
        idInput.setPadding(15, 12, 15, 12);
        idInput.setBackground(shape(0xFFF3F6FB, 12));
        root.addView(idInput, new LinearLayout.LayoutParams(-1, 54));

        addSpace(16);
        root.addView(text("Password", 13, DARK, true));
        addSpace(7);

        passwordInput = new EditText(this);
        passwordInput.setSingleLine(true);
        passwordInput.setTextSize(15);
        passwordInput.setHint("Enter your password");
        passwordInput.setInputType(129);
        passwordInput.setPadding(15, 12, 15, 12);
        passwordInput.setBackground(shape(0xFFF3F6FB, 12));
        root.addView(passwordInput, new LinearLayout.LayoutParams(-1, 54));

        addSpace(22);

        Button login = new Button(this);
        login.setText("SIGN IN");
        login.setTextColor(Color.WHITE);
        login.setTextSize(15);
        login.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        login.setBackground(shape(BLUE, 14));
        root.addView(login, new LinearLayout.LayoutParams(-1, 54));

        login.setOnClickListener(v -> {
            String id = idInput.getText().toString().trim();
            String pass = passwordInput.getText().toString();

            if (id.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this,
                        "College ID and password enter pannunga",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,
                        "Firebase authentication next step-la connect pannuvom",
                        Toast.LENGTH_LONG).show();
            }
        });

        addSpace(14);

        Button google = new Button(this);
        google.setText("Continue with Google");
        google.setTextColor(BLUE);
        google.setTextSize(14);
        google.setBackground(shape(0xFFF0F4FC, 14));
        root.addView(google, new LinearLayout.LayoutParams(-1, 52));

        google.setOnClickListener(v ->
                Toast.makeText(this,
                        "Google Sign-In next step-la connect pannuvom",
                        Toast.LENGTH_SHORT).show());

        addSpace(24);
        TextView footer = text(
                "CSE DEPARTMENT  •  APPLY SMART. APPROVE FAST.",
                10, GRAY, true);
        footer.setGravity(Gravity.CENTER);
        root.addView(footer);

        setContentView(scroll);
    }

    private void addSpace(int height) {
        View space = new View(this);
        root.addView(space,
                new LinearLayout.LayoutParams(1, height));
    }
}
