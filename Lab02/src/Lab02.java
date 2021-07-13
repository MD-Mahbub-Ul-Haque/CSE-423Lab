import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;

public class Lab02 implements GLEventListener{
   
	private GLU glu;
   @Override
   public void display(GLAutoDrawable drawable) {
      final GL2 gl = drawable.getGL().getGL2();
    //Student Id-18101428
      //For 2
      DDA(gl, -30, 30, -10, 30);
      DDA(gl, -10, 30, -10, 10);
      DDA(gl, -10, 10, -30, 10);
      DDA(gl, -30, 10, -30, -10);
      DDA(gl, -30, -10, -10, -10);
      
      //for 8
      DDA(gl, 10, -10, 10, 30);
      DDA(gl, 10, 30, 30, 30);
      DDA(gl, 30, 30, 30, -10);
      DDA(gl, 30, -10, 10, -10);
      DDA(gl, 10, -10, 10, 10);
      DDA(gl, 10, 10, 30, 10);
      
      
   }
   @Override
   public void dispose(GLAutoDrawable arg0) {
      //method body
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
      // method body
   }
   
   
   public void DDA(GL2 gl, float x1, float y1, float x2, float y2) {
       
       gl.glPointSize(3.0f);
       gl.glColor3d(255, 255, 255);
       
     //write your own code
       
       float dx = x2-x1;
       float dy = y2-y1;
       float m = dy/dx;
       float iteration;
       float x = x1;
       float y = y1;
       if (Math.abs(dx)>Math.abs(dy)) {
       iteration = Math.abs(dx);
       } else {
       iteration = Math.abs(dy);
       }
       float x_Inc;
       float y_Inc;
       if (m>-1 && m<1) {
       if (x2>x1) {
       x_Inc = 1;
       y_Inc = m;
       } else {
       x_Inc = -1;
       y_Inc = -m;
       }
       } else {
       if (y2>y1) {
       x_Inc = 1/m;
       y_Inc = 1;
       } else {
       x_Inc = -1/m;
       y_Inc = -1;
       }
       }
       for (int s=1; s<=iteration; s++) {
       //drawing the points
       gl.glBegin (GL2.GL_POINTS);
       gl.glVertex3f(x,y,0);
       gl.glEnd();
       x+=x_Inc;
       y+=y_Inc;
       }
       
    }

   
   
   
   
   
   public static void main(String[] args) {
      //getting the capabilities object of GL2 profile
      final GLProfile profile = GLProfile.get(GLProfile.GL2);
      GLCapabilities capabilities = new GLCapabilities(profile);
      // The canvas 
      final GLCanvas glcanvas = new GLCanvas(capabilities);
      Lab02 l = new Lab02();
      glcanvas.addGLEventListener(l);
      glcanvas.setSize(400, 400);
      //creating frame
      final JFrame frame = new JFrame ("DDA");
      //adding canvas to frame
      frame.getContentPane().add(glcanvas);
      frame.setSize(frame.getContentPane().getPreferredSize());
      frame.setVisible(true);
   }//end of main
}//end of classimport javax.media.opengl.GL2;