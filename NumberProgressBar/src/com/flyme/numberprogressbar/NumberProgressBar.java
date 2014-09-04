package com.flyme.numberprogressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class NumberProgressBar extends View {

	private Paint contentPaint;
	private Paint backPaint;

	public NumberProgressBar(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initConfig();
	}
	public NumberProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initConfig();
	}
	public NumberProgressBar(Context context) {
		super(context);
		initConfig();
	}
	
	
	 @Override
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	        setMeasuredDimension(measure(widthMeasureSpec,true), measure(heightMeasureSpec,false));
	   }
	 
	 private int measure(int measureSpec,boolean isWidth){
	        int result;
	        int mode = MeasureSpec.getMode(measureSpec);
	        int size = MeasureSpec.getSize(measureSpec);
	        int padding = isWidth?getPaddingLeft()+getPaddingRight():getPaddingTop()+getPaddingBottom();
	        if(mode == MeasureSpec.EXACTLY){
	            result = size;
	        }else{
	            result = isWidth ? getSuggestedMinimumWidth() : getSuggestedMinimumHeight();
	            result += padding;
	            if(mode == MeasureSpec.AT_MOST){
	                if(isWidth) {
	                    result = Math.max(result, size);
	                }
	                else{
	                    result = Math.min(result, size);
	                }
	            }
	        }
	        return result;
	    }
	
	
	private void initConfig(){
		contentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		contentPaint.setColor(Color.rgb(74, 153, 223));
		
		backPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		backPaint.setColor(Color.rgb(204, 204, 204));
		
		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setColor(Color.rgb(74, 153, 223));
	}
	
	
	private static final int DEFULT_MAX = 100 ;
	private static final int DEFULT_TEXT_STROKE_WIDTH = 6 ;
	private int textStrokeWidth = DEFULT_TEXT_STROKE_WIDTH;
	private boolean isMearsure = false;
	private int max = DEFULT_MAX ;
	
	
	
	//85bcea
	@Override
	protected void onDraw(Canvas canvas) {
		if (!isMearsure) {
			measureSize();
		}
		String text = ""+String.format("%d" ,plan * 100 / max) +"%";
		float width = textPaint.measureText(text);
		
		startx = plan * mLength / max ;
		
		if ((startx +width) >= mLength) {
			startx =   mLength - width;
		}else {
			canvas.drawLine(startx+mLeft + width , progressBarY, mLength + mLeft, progressBarY, backPaint);
		}
		canvas.drawText(text, startx + mLeft , textHeight, textPaint);
		canvas.drawLine(mLeft, progressBarY, startx + mLeft , progressBarY, contentPaint);
	}
	
	private static final int DEFULT_TEXT_SIZE = 35;
	private int mLeft = 0;
	private int mRight = 0;
	private int mWidth = 0 ;
	private int mLength = 0 ;
	private int plan = 0 ;
	private Paint textPaint;
	private float startx = 0;
	private int mHeight;
	private float progressBarY = 0 ;
	private float textSize = DEFULT_TEXT_SIZE ;
	private float textHeight = 0 ;
	private void measureSize(){
		mLeft = getPaddingLeft();
		mRight = getPaddingRight();
		mWidth = getWidth();
		mHeight = getHeight();
		mLength = mWidth - mLeft - mRight ;
		isMearsure = true;
		backPaint.setStrokeWidth(textStrokeWidth);
		contentPaint.setStrokeWidth(textStrokeWidth);
		textPaint.setTextSize(textSize);
		progressBarY = mHeight / 2.0f + textStrokeWidth/2.0f;
		textHeight = mHeight/2.0f - (textPaint.ascent() + textPaint.descent())/2.0f;
	}
	
	
	
	public void setProgress(int plan){
		this.plan = plan;
		invalidate();
	}
	
	
	public void setMax(int max){
		this.max = max;
	}
	
	public void setTextColor(int color){
		if (textPaint != null) {
			textPaint.setColor(color);
		}
	}
	public void setBackColor(int color){
		if (backPaint != null) {
			backPaint.setColor(color);
		}
	}
	public void setContentColor(int color){
		if (contentPaint != null) {
			contentPaint.setColor(color);
		}
	}
	
	public void setTextSize(int size){
		textPaint.setTextSize(size);
	}
	
	
	public void setProgressBarHeight(float height){
		if (contentPaint != null) {
			contentPaint.setStrokeWidth(height);
			backPaint.setStrokeWidth(height);
		}
	}
	
}
