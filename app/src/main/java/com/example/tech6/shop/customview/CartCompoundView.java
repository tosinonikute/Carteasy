package com.example.tech6.shop.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tech6.shop.R;

/**
 * Created by tech6 on 3/17/16.
 */
public class CartCompoundView  extends LinearLayout {

    private TextView productName;
    private TextView productDesc;
    private TextView productPrice;
    private TextView otherDetails;
    private ImageView productImage;

    public CartCompoundView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        TypedArray typedArray;
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.Options);

        int descriptionColor = typedArray.getColor(R.styleable.Options_descriptionColor, Color.BLACK);
        int textColor = typedArray.getColor(R.styleable.Options_textColor, Color.BLACK);

        typedArray.recycle();

        initializeViews(context);

        productName = (TextView) this.findViewById(R.id.productname);
        productDesc = (TextView) this.findViewById(R.id.productdesc);
        otherDetails = (TextView) this.findViewById(R.id.otherdetails);
        productPrice = (TextView) this.findViewById(R.id.productprice);
        productImage = (ImageView) this.findViewById(R.id.productimage);

    }

    public void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cartcompoundview, this);
    }

    public void setProductNameColor(int color) {
        productName.setTextColor(color);
    }

    public void setProductDescColor(int color) {
        productDesc.setTextColor(color);
    }

    public void setOtherDetailsColor(int color) {
        otherDetails.setTextColor(color);
    }

    public void setProductImage(Bitmap bp) {
        productImage.setImageBitmap(bp);
    }




}
