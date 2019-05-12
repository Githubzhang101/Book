package com.mingrisoft;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

public class CubeRenderer implements GLSurfaceView.Renderer {
	private final GLCube cube;		//���������

	public CubeRenderer() {
		cube = new GLCube();	//ʵ�������������
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glClearColor(0.7f, 0.9f, 0.9f, 1.0f); // ���ô��屳����ɫ
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);	//���ö�����������
		gl.glDisable(GL10.GL_DITHER);// �رտ�����
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST); // ����ϵͳ��͸�ӽ�������
		gl.glShadeModel(GL10.GL_SMOOTH); // ������Ӱƽ��ģʽ
		gl.glEnable(GL10.GL_DEPTH_TEST); // ������Ȳ���
		gl.glDepthFunc(GL10.GL_LEQUAL); // ������Ȳ��Ե�����

	}

	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);// �����ɫ�������Ȼ���
		gl.glMatrixMode(GL10.GL_MODELVIEW);	//����ʹ��ģ�;�����б任
		gl.glLoadIdentity(); // ��ʼ����λ����
		// ��ʹ��GL_MODELVIEWģʽʱ�����������ӵ㣬Ҳ���ǹ۲��
		GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
		gl.glRotatef(1000, -0.1f, -0.1f, 0.05f); // ��ת������ϵ
		// ����������
		cube.draw(gl);

	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);	//����OpenGL�����Ĵ�С
		float ratio = (float) width / height; // ����͸���Ӵ��Ŀ�ȡ��߶ȱ�
		gl.glMatrixMode(GL10.GL_PROJECTION); // ����ǰ����ģʽ��ΪͶӰ����
		gl.glLoadIdentity(); // ��ʼ����λ����
		// ����͸���Ӵ��Ŀռ��С
		GLU.gluPerspective(gl, 45.0f, ratio, 1, 100f);

	}

}
