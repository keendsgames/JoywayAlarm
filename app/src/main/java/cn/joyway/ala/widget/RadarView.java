package cn.joyway.ala.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import androidx.annotation.IntDef;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import cn.joyway.ala.R;

public class RadarView extends FrameLayout {

    private int viewSize=500;
    private Paint mPaintSector;
    public boolean isstart = false;
    //旋转效果起始角度
    private int start = 0;

    private Matrix matrix;

    public final static int CLOCK_WISE=1;
    public final static int ANTI_CLOCK_WISE=-1;

    @IntDef({ CLOCK_WISE, ANTI_CLOCK_WISE })
    @interface RADAR_DIRECTION {

    }
    //默认为顺时针
    private final static int DEFAULT_DIERCTION=CLOCK_WISE;

    //设定雷达扫描方向
    private int direction=DEFAULT_DIERCTION;

    private boolean threadRunning = true;

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public RadarView(Context context) {
        super(context);
        initPaint();

    }

    public void setViewSize(Context context,int wideDipValue)
    {
        viewSize = dip2px(context,wideDipValue);
        initPaint();
    }

    private int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private void initPaint() {
        setBackgroundColor(Color.TRANSPARENT);

        //暗绿色的画笔
        mPaintSector = new Paint();
        //mPaintSector.setColor(Color.parseColor("#00ffff"));
        mPaintSector.setColor(getResources().getColor(R.color.theme_color_heavy));
        mPaintSector.setAntiAlias(true);
        //mShader = new SweepGradient(viewSize / 2, viewSize / 2, Color.TRANSPARENT, Color.parseColor("#006666"));
        //Color clr2 = Color.parseColor("#20B2AA");Color.parseColor("#006666")
        Shader mShader = new SweepGradient(viewSize / 2, viewSize / 2,
                getResources().getColor(R.color.theme_color_light),
                getResources().getColor(R.color.theme_color_heavy));
        mPaintSector.setShader(mShader);

    }

    /*public void setViewSize(int size) {
        this.viewSize = size;
        setMeasuredDimension(viewSize, viewSize);
    }*/

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(viewSize, viewSize);
    }

    public void start() {
        ScanThread mThread = new ScanThread(this);
        mThread.setName("radar");
        mThread.start();
        threadRunning = true;
        isstart = true;
    }

    public void stop() {
        if (isstart) {
            threadRunning = false;
            isstart = false;
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //根据matrix中设定角度，不断绘制shader,呈现出一种扇形扫描效果
        canvas.concat(matrix);
        canvas.drawCircle(viewSize / 2, viewSize / 2, viewSize/2, mPaintSector);
        super.onDraw(canvas);
    }

    public void setDirection(@RADAR_DIRECTION int direction) {
        if (direction != CLOCK_WISE && direction != ANTI_CLOCK_WISE) {
            throw new IllegalArgumentException("Use @RADAR_DIRECTION constants only!");
        }
        this.direction = direction;
    }



    protected class ScanThread extends Thread
    {

        private RadarView view;

        ScanThread(RadarView view)
        {
            this.view = view;
        }

        @Override
        public void run() {
            while (threadRunning) {

                if (isstart) {
                    view.post(new Runnable() {
                        public void run() {
                            start = start + 1;
                            matrix = new Matrix();
                            matrix.preRotate(direction*start,viewSize/2,viewSize/2);
                            view.invalidate();
                        }
                    });
                    try {
                        Thread.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}