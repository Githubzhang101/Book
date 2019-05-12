package com.mingrisoft;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * @author Administrator 定义一个简单的模型：立方体
 */
public class GLCube {
	private final IntBuffer mVertexBuffer;	//顶点坐标数据缓冲

	public GLCube() {

		int one = 65536;
		int half = one / 2;
		int vertices[] = {
	            // 前面
	            -half, -half, half, half, -half, half,
	            -half, half, half, half, half, half,
	            // 背面
	            -half, -half, -half, -half, half, -half,
	            half, -half, -half, half, half, -half,
	            // 左面
	            -half, -half, half, -half, half, half,
	            -half, -half, -half, -half, half, -half,
	            // 右面
	            half, -half, -half, half, half, -half,
	            half, -half, half, half, half, half,
	            // 上面
	            -half, half, half, half, half, half,
	            -half, half, -half, half, half, -half,
	            // 下面
	            -half, -half, half, -half, -half, -half,
	            half, -half, half, half, -half, -half, 
			};	//定义顶点位置
		//创建顶点坐标数据缓冲
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());	//设置字节顺序
		mVertexBuffer = vbb.asIntBuffer();//转换为int型缓冲
		mVertexBuffer.put(vertices);	//向缓冲中放入顶点坐标数据
		mVertexBuffer.position(0);//设置缓冲区的起始位置
	}

	public void draw(GL10 gl) {
		gl.glVertexPointer(3, GL10.GL_FIXED, 0, mVertexBuffer);	//为画笔指定顶点坐标数据
		// 绘制FRONT和BACK两个面
		gl.glColor4f(1, 0, 0, 1);
		gl.glNormal3f(0, 0, 1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);	//绘制图形
		gl.glColor4f(1, 0, 0.5f, 1);
		gl.glNormal3f(0, 0, -1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);	//绘制图形
		// 绘制LEFT和RIGHT两个面
		gl.glColor4f(0, 1, 0, 1);
		gl.glNormal3f(-1, 0, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);	//绘制图形
		gl.glColor4f(0, 1, 0.5f, 1);
		gl.glNormal3f(1, 0, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);	//绘制图形
		// 绘制TOP和BOTTOM两个面
		gl.glColor4f(0, 0, 1, 1);
		gl.glNormal3f(0, 1, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);	//绘制图形
		gl.glColor4f(0, 0, 0.5f, 1);
		gl.glNormal3f(0, -1, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);	//绘制图形
	}
}
