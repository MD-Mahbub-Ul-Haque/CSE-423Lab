import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;

public class MidPointLine implements GLEventListener {

	private GLU glu;

@Override
public void display(GLAutoDrawable drawable) {
final GL2 gl = drawable.getGL().getGL2();
//For 1,
MidPointLine(gl, -20, 30, 0, 30);
MidPointLine(gl, 0, 30, -1, 15);
MidPointLine(gl, -15, 15, 0, 15);
MidPointLine(gl, -15, 15, -20, 0);
MidPointLine(gl, -20, 0, 0, 0);
//For 8,
MidPointLine(gl, 10, 30, 30, 30);

MidPointLine(gl, 10, 0, 30, 0);
MidPointLine(gl, 10, 30, 30, 0);

MidPointLine(gl, 30, 30, 10, 0);
MidPointLine(gl, 10, 0, 30, 30);
}
	@Override
	public void dispose(GLAutoDrawable arg0) {

	}

	@Override
	public void init(GLAutoDrawable gld) {
		GL2 gl = gld.getGL().getGL2();
		glu = new GLU();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glViewport(-100, -50, 50, 100);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluOrtho2D(-100.0, 100.0, -100.0, 100.0);
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {

	}

	public void MidPointLine(GL2 gl, int x1, int y1, int x2, int y2) {
		gl.glPointSize(3.0f);
		gl.glColor3d(255, 255, 255);

		int zone = findZone(x1, y1, x2, y2);
		int[] newValues = convertToZone_0(gl, x1, y1, x2, y2, zone);

		int newX1 = newValues[0];
		int newY1 = newValues[1];
		int newX2 = newValues[2];
		int newY2 = newValues[3];
		int dx = newX2 - newX1;
		int dy = newY2 - newY1;
		int d = 2 * dy - dx;
		int E = 2 * dy;
		int NE = 2 * (dy - dx);
		int X = newX1;
		int Y = newY1;
		while (X <= newX2) {
			OriginalZone(gl, X, Y, zone);

			X++;
			if (d > 0) {
				d += NE;
				Y++;
			} else {
				d += E;
			}
		}
	}

	public static int findZone(int x1, int y1, int x2, int y2) {

		int dx = x2 - x1;
		int dy = y2 - y1;
		int zone = 0;
		if (Math.abs(dx) >= Math.abs(dy)) {

			if (dx >= 0 && dy > 0) {
				zone = 0;

			} else if (dx < 0 && dy > 0) {

				zone = 3;

			} else if (dx < 0 && dy < 0) {

				zone = 4;

			} else if (dx > 0 && dy < 0) {

				zone = 7;

			}
		} else {

			if (dx > 0 && dy > 0) {
				zone = 1;

			} else if (dx < 0 && dy > 0) {

				zone = 2;

			} else if (dx < 0 && dy < 0) {

				zone = 5;

			} else if (dx > 0 && dy < 0) {

				zone = 6;

			}

		}

		return zone;

	}

	public static int[] convertToZone_0(GL2 gl, int X1, int Y1, int X2, int Y2, int zone) {
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		if (zone == 0) {
			x1 = X1;
			y1 = Y1;
			x2 = X2;
			y2 = Y2;

		} else if (zone == 1) {

			x1 = Y1;
			y1 = X1;
			x2 = Y2;
			y2 = X2;

		} else if (zone == 2) {

			x1 = Y1;
			y1 = -X1;
			x2 = Y2;
			y2 = -X2;
		} else if (zone == 3) {
			x1 = -X1;
			y1 = Y1;
			x2 = -X2;
			y2 = Y2;

		} else if (zone == 4) {
			x1 = -X1;
			y1 = -Y1;
			x2 = -X2;
			y2 = -Y2;
		} else if (zone == 5) {
			x1 = -Y1;
			y1 = -X1;
			x2 = -Y2;
			y2 = -X2;
		} else if (zone == 6) {
			x1 = -Y1;
			y1 = X1;
			x2 = -Y2;
			y2 = X2;

		} else if (zone == 7) {

			x1 = X1;
			y1 = -Y1;
			x2 = X2;
			y2 = -Y2;

		}
		return new int[] { x1, y1, x2, y2 };

	}

	public static void OriginalZone(GL2 gl, int X, int Y, int zone) {

		int i = 0, j = 0;
		if (zone == 0) {
			i = X;
			j = Y;

		} else if (zone == 1) {

			i = Y;
			j = X;

		} else if (zone == 2) {

			i = -Y;
			j = X;

		} else if (zone == 3) {

			i = -X;
			j = Y;

		} else if (zone == 4) {

			i = -X;
			j = -Y;

		} else if (zone == 5) {

			i = -Y;
			j = -X;

		} else if (zone == 6) {

			i = Y;
			j = -X;

		} else if (zone == 7) {

			i = X;
			j = -Y;

		}
		plot(gl, i, j);

	}

	public static void plot(GL2 gl, int x, int y) {

		gl.glBegin(GL2.GL_POINTS);

		gl.glVertex2d(x, y);
		gl.glEnd();
	}

	public static void main(String[] args) {

		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);

		final GLCanvas glcanvas = new GLCanvas(capabilities);
		MidPointLine l = new MidPointLine();
		glcanvas.addGLEventListener(l);
		glcanvas.setSize(400, 400);

		final JFrame frame = new JFrame("straight Line");

		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
	}
}