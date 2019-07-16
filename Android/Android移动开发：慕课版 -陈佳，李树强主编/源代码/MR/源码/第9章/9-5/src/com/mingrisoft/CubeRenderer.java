package com.mingrisoft;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

public class CubeRenderer implements GLSurfaceView.Renderer {
	private final GLCube cube;		//立方体对象

	public CubeRenderer() {
		cube = new GLCube();	//实例化立方体对象
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glClearColor(0.7f, 0.9f, 0.9f, 1.0f); // 设置窗体背景颜色
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);	//启用顶点坐标数组
		gl.glDisable(GL10.GL_DITHER);// 关闭抗抖动
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST); // 设置系统对透视进行修正
		gl.glShadeModel(GL10.GL_SMOOTH); // 设置阴影平滑模式
		gl.glEnable(GL10.GL_DEPTH_TEST); // 启用深度测试
		gl.glDepthFunc(GL10.GL_LEQUAL); // 设置深度测试的类型

	}

	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);// 清除颜色缓存和深度缓存
		gl.glMatrixMode(GL10.GL_MODELVIEW);	//设置使用模型矩阵进行变换
		gl.glLoadIdentity(); // 初始化单位矩阵
		// 当使用GL_MODELVIEW模式时，必须设置视点，也就是观察点
		GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
		gl.glRotatef(1000, -0.1f, -0.1f, 0.05f); // 旋转总坐标系
		// 绘制立方体
		cube.draw(gl);

	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);	//设置OpenGL场景的大小
		float ratio = (float) width / height; // 计算透视视窗的宽度、高度比
		gl.glMatrixMode(GL10.GL_PROJECTION); // 将当前矩阵模式设为投影矩阵
		gl.glLoadIdentity(); // 初始化单位矩阵
		// 设置透视视窗的空间大小
		GLU.gluPerspective(gl, 45.0f, ratio, 1, 100f);

	}

}
