package com.mingrisoft;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * @author Administrator ����һ���򵥵�ģ�ͣ�������
 */
public class GLCube {
	private final IntBuffer mVertexBuffer;	//�����������ݻ���

	public GLCube() {

		int one = 65536;
		int half = one / 2;
		int vertices[] = {
	            // ǰ��
	            -half, -half, half, half, -half, half,
	            -half, half, half, half, half, half,
	            // ����
	            -half, -half, -half, -half, half, -half,
	            half, -half, -half, half, half, -half,
	            // ����
	            -half, -half, half, -half, half, half,
	            -half, -half, -half, -half, half, -half,
	            // ����
	            half, -half, -half, half, half, -half,
	            half, -half, half, half, half, half,
	            // ����
	            -half, half, half, half, half, half,
	            -half, half, -half, half, half, -half,
	            // ����
	            -half, -half, half, -half, -half, -half,
	            half, -half, half, half, -half, -half, 
			};	//���嶥��λ��
		//���������������ݻ���
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());	//�����ֽ�˳��
		mVertexBuffer = vbb.asIntBuffer();//ת��Ϊint�ͻ���
		mVertexBuffer.put(vertices);	//�򻺳��з��붥����������
		mVertexBuffer.position(0);//���û���������ʼλ��
	}

	public void draw(GL10 gl) {
		gl.glVertexPointer(3, GL10.GL_FIXED, 0, mVertexBuffer);	//Ϊ����ָ��������������
		// ����FRONT��BACK������
		gl.glColor4f(1, 0, 0, 1);
		gl.glNormal3f(0, 0, 1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);	//����ͼ��
		gl.glColor4f(1, 0, 0.5f, 1);
		gl.glNormal3f(0, 0, -1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);	//����ͼ��
		// ����LEFT��RIGHT������
		gl.glColor4f(0, 1, 0, 1);
		gl.glNormal3f(-1, 0, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);	//����ͼ��
		gl.glColor4f(0, 1, 0.5f, 1);
		gl.glNormal3f(1, 0, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);	//����ͼ��
		// ����TOP��BOTTOM������
		gl.glColor4f(0, 0, 1, 1);
		gl.glNormal3f(0, 1, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);	//����ͼ��
		gl.glColor4f(0, 0, 0.5f, 1);
		gl.glNormal3f(0, -1, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);	//����ͼ��
	}
}
