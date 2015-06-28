package com.kartiks.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Movie;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.kartiks.sample.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class GifImageView extends View {

	private long mMovieStart;
	Context context;
	private Movie movie;
	private Paint p;
	private int width;//,height;
	
	String resourse;
	
	double movieWidth,movieHeight,factor,scaledMoveWidth,scaledMovieHeight;

	public GifImageView(Context context, AttributeSet attrs, int defStyleAttr) {

		super(context, attrs, defStyleAttr);
		this.context = context;
		p = new Paint();
		p.setAntiAlias(true);
		// height=metrics.heightPixels;

		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GifImageView, 0, 0);
		try {
			resourse = a.getString(R.styleable.GifImageView_gifResourseId);
			// LoggerGeneral.e("res "+resourse);
			/*if (resourse != null) {
				initializeStreamFromXML(resourse);
			}*/
			invalidate();

		} finally {
			a.recycle();
		}
	}

	public GifImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		p = new Paint();
		p.setAntiAlias(true);
		
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GifImageView, 0, 0);
		try {
			resourse = a.getString(R.styleable.GifImageView_gifResourseId);
			/*if (resourse != null) {
				initializeStreamFromXML(resourse);
			}*/
			invalidate();

		} finally {
			a.recycle();
		}
		
		
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		
		width=w;
		//height=h;
		if (resourse != null) {
			initializeStreamFromXML(resourse);
		}
		
		
	}

	public GifImageView(Context context) {
		super(context);
		p = new Paint();
		p.setAntiAlias(true);
		invalidate();
		// height=metrics.heightPixels;
		this.context = context;
	}

	public void initializeStream(String path) {

		// LoggerGeneral.e("initializing..");
		// InputStream is=context.getResources().openRawResource(R.raw.pb);
		File f = new File(path);
		if (f.exists()) {
			FileInputStream fis;
			try {
				fis = new FileInputStream(f);
				// directlt decoding fis doesnt work,put it in bis
				commonInitialization(fis);

			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
		}
	}

	private void initializeStreamFromXML(String gifAssetName) {

		//LoggerGeneral.e("initializing..");
		// InputStream is=context.getResources().openRawResource(R.raw.pb);
		try {
			//LoggerGeneral.e("initializing..1");
			InputStream is = context.getAssets().open(gifAssetName);
			
			commonInitialization(is);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	
	public void commonInitialization(InputStream is){
		
		
		BufferedInputStream bis = new BufferedInputStream(is);
		bis.mark(100000);
		// v.imp ,doesnt work without mark as well ,other wise we get
		// java.io.IOException: Mark has been invalidated.
		//LoggerGeneral.e("initializing..2");
		movie = Movie.decodeStream(bis);
		
		movieWidth=movie.width();
		movieHeight=movie.height();
		
		if(movieWidth>width){
			
			factor = movieWidth / width;
			scaledMoveWidth = movieWidth / factor;
			scaledMovieHeight = movieHeight / factor;
			
			LayoutParams params = this.getLayoutParams();
			params.width =(int) scaledMoveWidth;
			params.height=(int)scaledMovieHeight;
			this.setLayoutParams(params);
		
		}else{
			
			scaledMoveWidth=movieWidth;
			scaledMovieHeight=movieHeight;
			
			LayoutParams params = this.getLayoutParams();
			params.height =(int) movieHeight;
			params.width=(int)movieWidth;
			this.setLayoutParams(params);
		}
		
		
		
		invalidate();
		requestLayout();
	}
	
	

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.TRANSPARENT);

		if (movie == null) {

			canvas.drawPaint(p);
			return;
		}

		long now = android.os.SystemClock.uptimeMillis();

		if (mMovieStart == 0) { // first time
			mMovieStart = now;
		}

		if (movie != null) {
			int dur = movie.duration();

			if (dur == 0) {
				dur = 1000;
			}

			int relTime = (int) ((now - mMovieStart) % dur);
			movie.setTime(relTime);
			
			Bitmap bm = Bitmap.createBitmap((int) movieWidth, (int) movieHeight, Bitmap.Config.ARGB_8888);

			Canvas c = new Canvas(bm);
			movie.draw(c, 0, 0);

			
			bm = Bitmap.createScaledBitmap(bm, (int) scaledMoveWidth, (int) scaledMovieHeight, true);
			canvas.drawBitmap(bm, new Matrix(), p);

			invalidate();
		}
	}

}