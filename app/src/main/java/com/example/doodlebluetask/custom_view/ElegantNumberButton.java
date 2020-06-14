package com.example.doodlebluetask.custom_view;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.doodlebluetask.R;
import com.google.android.material.button.MaterialButton;


public class ElegantNumberButton extends RelativeLayout {
    private Context context;
    private AttributeSet attrs;
    private int styleAttr;
    private OnClickListener mListener;
    private int initialNumber;
    private int lastNumber;
    private int currentNumber;
    private int finalNumber;
    private TextView textView;
    private OnValueChangeListener mOnValueChangeListener;

    private LinearLayout mainLayout;

    public MaterialButton addBtn, subtractBtn, addBtnView;

    public ElegantNumberButton(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public ElegantNumberButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;
        initView();
    }

    public ElegantNumberButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.attrs = attrs;
        this.styleAttr = defStyleAttr;
        initView();
    }

    private void initView() {
        inflate(context, R.layout.layout, this);
        final Resources res = getResources();
        final int defaultColor = res.getColor(R.color.primary);
        final int defaultTextColor = res.getColor(R.color.black_900);
        final Drawable defaultDrawable = res.getDrawable(R.drawable.background);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ElegantNumberButton,
                styleAttr, 0);

        initialNumber = a.getInt(R.styleable.ElegantNumberButton_initialNumber, 0);
        finalNumber = a.getInt(R.styleable.ElegantNumberButton_finalNumber, Integer.MAX_VALUE);
        float textSize = a.getDimension(R.styleable.ElegantNumberButton_textSize, 23);
        int color = a.getColor(R.styleable.ElegantNumberButton_backGroundColor, defaultColor);
        int textColor = a.getColor(R.styleable.ElegantNumberButton_textColor, defaultTextColor);
        Drawable drawable = a.getDrawable(R.styleable.ElegantNumberButton_backgroundDrawable);
        mainLayout = findViewById(R.id.mainLayout);
        subtractBtn = findViewById(R.id.subtract_btn);
        addBtn = findViewById(R.id.add_btn);
        textView = findViewById(R.id.number_counter);
        addBtnView = findViewById(R.id.add_temp);

        LinearLayout mLayout = findViewById(R.id.layout);

        subtractBtn.setTextColor(textColor);
        addBtn.setTextColor(textColor);
        textView.setTextColor(textColor);
        subtractBtn.setTextSize(textSize);
        addBtn.setTextSize(textSize);
        textView.setTextSize(textSize);

        if (drawable == null) {
            drawable = defaultDrawable;
        }
        assert drawable != null;
        drawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC));
        mLayout.setBackground(drawable);

        textView.setText(String.valueOf(initialNumber));

        currentNumber = initialNumber;
        lastNumber = initialNumber;


        if (initialNumber == 0) {
            updateVisibility(0);
        }

        addBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumber(String.valueOf(1), true);
            }
        });

        subtractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                int num = Integer.valueOf(textView.getText().toString());
                setNumber(String.valueOf(num - 1), true);


            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                int num = Integer.valueOf(textView.getText().toString());
                setNumber(String.valueOf(num + 1), true);
            }
        });
        a.recycle();
    }

    private void callListener(View view) {
        if (mListener != null) {
            mListener.onClick(view);
        }

        if (mOnValueChangeListener != null) {
            if (lastNumber != currentNumber) {
                mOnValueChangeListener.onValueChange(this, lastNumber, currentNumber);
            }
        }
    }

    public String getNumber() {
        return String.valueOf(currentNumber);
    }

    public void setNumber(String number) {
        updateVisibility(Integer.parseInt(number));
        lastNumber = currentNumber;
        this.currentNumber = Integer.parseInt(number);
        if (this.currentNumber > finalNumber) {
            this.currentNumber = finalNumber;
        }
        if (this.currentNumber < initialNumber) {
            this.currentNumber = initialNumber;
        }
        textView.setText(String.valueOf(currentNumber));
    }

    public void updateVisibility(int number) {

        if (number == 0) {
            addBtnView.setVisibility(VISIBLE);
            mainLayout.setVisibility(GONE);
        } else {
            addBtnView.setVisibility(GONE);
            mainLayout.setVisibility(VISIBLE);
        }

    }

    public void setNumber(String number, boolean notifyListener) {
        updateVisibility(Integer.parseInt(number));
        setNumber(number);
        if (notifyListener) {
            callListener(this);
        }
    }

    public void setOnElegantClickListener(OnClickListener onClickListener) {
        this.mListener = onClickListener;
    }

    public void setOnValueChangeListener(OnValueChangeListener onValueChangeListener) {
        mOnValueChangeListener = onValueChangeListener;
    }

    @FunctionalInterface
    public interface OnClickListener {
        void onClick(View view);
    }

    public interface OnValueChangeListener {
        void onValueChange(ElegantNumberButton view, int oldValue, int newValue);
    }

    public void setRange(Integer startingNumber, Integer endingNumber) {
        this.initialNumber = startingNumber;
        this.finalNumber = endingNumber;
    }

    public void updateColors(int backgroundColor, int textColor) {
        this.textView.setBackgroundColor(backgroundColor);
        this.addBtn.setBackgroundColor(backgroundColor);
        this.subtractBtn.setBackgroundColor(backgroundColor);

        this.textView.setTextColor(textColor);
        this.addBtn.setTextColor(textColor);
        this.subtractBtn.setTextColor(textColor);
    }

    public void updateTextSize(int unit, float newSize) {
        this.textView.setTextSize(unit, newSize);
        this.addBtn.setTextSize(unit, newSize);
        this.subtractBtn.setTextSize(unit, newSize);
    }
}

