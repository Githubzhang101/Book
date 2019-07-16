package com.mingrisoft;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

/**
 * @author Administrator ����һ���򵥵�ģ�ͣ�������
 */
public class GLCube {
	private final IntBuffer mVertexBuffer; // �����������ݻ���
	private IntBuffer mTextureBuffer; // ������ͼ���ݻ���

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
	    }; // ���嶥��λ��
		// ���������������ݻ���
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder()); // �����ֽ�˳��
		mVertexBuffer = vbb.asIntBuffer();// ת��Ϊint�ͻ���
		mVertexBuffer.put(vertices); // �򻺳��з��붥����������
		mVertexBuffer.position(0);// ���û���������ʼλ��

		/********************** ������ͼ *********************************************/
		int texCoords[] = {
				// ǰ��
				0, one, one, one, 0, 0, one, 0,
				// ����
				one, one, one, 0, 0, one, 0, 0,
				// ����
				one, one, one, 0, 0, one, 0, 0,
				// ����
				one, one, one, 0, 0, one, 0, 0,
				// ����
				one, 0, 0, 0, one, one, 0, one,
				// ����
				0, 0, 0, one, one, 0, one, one, };	//������ͼ��������
		ByteBuffer tbb = ByteBuffer.allocateDirect(texCoords.length * 4);
		tbb.order(ByteOrder.nativeOrder()); // �����ֽ�˳��
		mTextureBuffer = tbb.asIntBuffer();// ת��Ϊint�ͻ���
		mTextureBuffer.put(texCoords); // �򻺳��з�����ͼ��������
		mTextureBuffer.position(0);// ���û���������ʼλ��
		/***************************************************************************/
	}

	public void draw(GL10 gl) {
		gl.glVertexPointer(3, GL10.GL_FIXED, 0, mVertexBuffer); // Ϊ����ָ��������������
		// ����FRONT��BACK������
		gl.glColor4f(1, 1, 1, 1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4); // ����ͼ��
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4); // ����ͼ��
		// ����LEFT��RIGHT������
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4); // ����ͼ��
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4); // ����ͼ��
		// ����TOP��BOTTOM������
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4); // ����ͼ��
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4); // ����ͼ��
		/********************** ������ͼ *********************************************/
		gl.glTexCoordPointer(2, GL10.GL_FIXED, 0, mTextureBuffer);//Ϊ����ָ����ͼ��������
		/***************************************************************************/
	}

	/**
	 * 
	 * ���ܣ�����������ͼ
	 * 
	 * @param gl
	 * @param context
	 * @param resource
	 */
	void loadTexture(GL10 gl, Context context, int resource) {
		Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),
				resource);	//����λͼ
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bmp, 0);	//ʹ��ͼƬ��������
		bmp.recycle();	//�ͷ���Դ
	}
}
