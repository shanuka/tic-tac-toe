package com.example.tictac.view;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import com.example.tictac.GameActivity;
import com.example.tictac.R;
import com.example.tictac.model.Board;

public class BoardView extends View {

    private static final int LINE_THICK = 5;
    private static final int ELT_STROKE_WIDTH = 15;
    private int width, height, eltW, eltH;
    private Paint gridPaint, oPaint, xPaint;
    private Board board;
    private GameActivity activity;


    public BoardView(Context context) {
        super(context);

    }

    public BoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        gridPaint = new Paint();
        oPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        oPaint.setColor(Color.RED);
        oPaint.setStyle(Paint.Style.STROKE);
        oPaint.setStrokeWidth(ELT_STROKE_WIDTH);
        xPaint = new Paint(oPaint);
        xPaint.setColor(Color.BLUE);
    }

    public void setMainActivity(GameActivity a) {
        activity = a;
    }

    public void setGameEngine(Board g) {
        board = g;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        height = View.MeasureSpec.getSize(heightMeasureSpec);
        width = View.MeasureSpec.getSize(widthMeasureSpec);
        eltW = (width - LINE_THICK) / 3;
        eltH = (height - LINE_THICK) / 3;

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawGrid(canvas);
        drawBoard(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!board.isEnded() && event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) (event.getX() / eltW);
            int y = (int) (event.getY() / eltH);
            char win = board.play(x, y);
            invalidate();

            if (win != ' ') {
                activity.gameEnded(win);
            }

            activity.getCurrentPlayer(board.getCurrentPlayer());


        }

        return super.onTouchEvent(event);
    }

    private void drawBoard(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                drawElt(canvas, board.getElt(i, j), i, j);
            }
        }
    }

    private void drawGrid(Canvas canvas) {
        for (int i = 0; i < 2; i++) {
            // vertical lines
            float left = eltW * (i + 1);
            float right = left + LINE_THICK;
            float top = 0;
            float bottom = height;

            canvas.drawRect(left, top, right, bottom, gridPaint);

            // horizontal lines
            float left2 = 0;
            float right2 = width;
            float top2 = eltH * (i + 1);
            float bottom2 = top2 + LINE_THICK;

            canvas.drawRect(left2, top2, right2, bottom2, gridPaint);
        }
    }

    private void drawElt(Canvas canvas, char c, int x, int y) {
        float cx = (eltW * x) + eltW / 2;
        float cy = (eltH * y) + eltH / 2;
        if (c == 'O') {

            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.o_mark);
            canvas.drawBitmap(b, cx - b.getWidth() + b.getWidth() / 2, cy - b.getHeight() + b.getHeight() / 2, oPaint);

        } else if (c == 'X') {


            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.x_mark);
            canvas.drawBitmap(b, cx - b.getWidth() + b.getWidth() / 2, cy - b.getHeight() + b.getHeight() / 2, oPaint);

        }
    }

}