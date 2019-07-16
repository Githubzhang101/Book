package com.mingrisoft;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

public class CubeRenderer implements GLSurfaceView.Renderer {
	private final GLCube cube;		//立方体对象
	private Context context;
	private long startTime;			//保存开始时间

	public CubeRenderer(Context context) {
		cube = new GLCube();	//实例化立方体对象
		this.context=context;
		startTime=System.currentTimeMillis();
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glClearColor(0.7f, 0.9f, 0.9f, 1.0f); // 设置窗体背景颜色
		gl.glClearColor(0.08f, 0.16f, 0.39f, 1.0f); // 设置窗体背景颜色	
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);	//启用顶点坐标数组
		gl.glDisable(GL10.GL_DITHER);// 关闭抗抖动
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST); // 设置系统对透视进行修正
		gl.glShadeModel(GL10.GL_SMOOTH); // 设置阴影平滑模式
		gl.glEnable(GL10.GL_DEPTH_TEST); // 启用深度测试
		gl.glDepthFunc(GL10.GL_LEQUAL); // 设置深度测试的类型
		/***********************透明效果*************************************/
        gl.glDisable(GL10.GL_DEPTH_TEST);		//关闭深度测试
        gl.glEnable(GL10.GL_BLEND);	//打开混合
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);	//使用alpha通道值进行混色，从而达到透明效果
        /********************************************************************/		
		/*************************应用纹理贴图*******************************/
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);	//启用贴图坐标数组
		gl.glEnable(GL10.GL_TEXTURE_2D);//启用纹理贴图
		cube.loadTexture(gl, context, R.drawable.mr);//进行纹理贴图
		/********************************************************************/
		/*************************光照效果*******************************/
		//为物体添加环境光和散射光
		float matAmbient[]=new float[]{1,1,1,1};	//定义材质的环境光
		float matDiffuse[]=new float[]{1,1,1,1};	//定义材质的散射光
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, matAmbient,0);	//设置材质的环境光
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, matDiffuse,0);	//设置材质的散射光
		//添加光线
		float lightAmbient[]=new float[]{0.2f,0.2f,0.2f,1};	//定义环境光
		float lightDiffuse[]=new float[]{1,1,1,1};	//定义散射光
		float lightPos[]=new float[]{1,1,1,1};	//定义光源的位置
		gl.glEnable(GL10.GL_LIGHTING);	//启用光源
		gl.glEnable(GL10.GL_LIGHT0);	//启用0号光源
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, lightAmbient,0);	//设置环境光
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, lightDiffuse, 0);	//设置散射光
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0);	//设置光源的位置
		/********************************************************************/
	}

	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);// 清除颜色缓存和深度缓存
		gl.glMatrixMode(GL10.GL_MODELVIEW);	//设置使用模型矩阵进行变换
		gl.glLoadIdentity(); // 初始化单位矩阵
		// 当使用GL_MODELVIEW模式时，必须设置视点，也就是观察点
		GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
		gl.glRotatef(1000, -0.1f, -0.1f, 0.05f); // 旋转总坐标系
		
		/********************旋转*****************************************/
		long elapsed = System.currentTimeMillis() - startTime;	//计算逝去的时间
		gl.glRotatef(elapsed * (30f / 1000f), 0, 1, 0);	//在y轴上旋转30度
		gl.glRotatef(elapsed * (15f / 1000f), 1, 0, 0);	//在x轴上旋转15度
		/*****************************************************************/
		cube.draw(gl);// 绘制立方体
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

