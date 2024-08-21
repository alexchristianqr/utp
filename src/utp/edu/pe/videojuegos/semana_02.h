#ifndef L11_ROTATECLICK_H
#define L11_ROTATECLICK_H
//---------------------------------------------------------------------------
// Programming 3D to Newbies : Indiana Aiki
//---------------------------------------------------------------------------
#include <GL/gl.h>
#include "Scene.h"
//---------------------------------------------------------------------------
class L11_RotateClick : public Scene
{
private:
double a;

public:

   L11_RotateClick( )
   {
      a=0;
   }

   void draw( )
   {
      background();
      drawRotate();
   }

   void click( )
   { 
      a+=90;
   
   if(a>=360)
   {
      a=0;
   }

   }

private:    

   void background ( )
   { 
      glClearColor(0,0,0.2,1);
   }

   void drawRotate( )
   {
      glPushMatrix();
         glRotated(a,0,0,1);
         drawTriangle();
      glPopMatrix();
   }

   void drawTriangle( )
   {  
      glColor3d(1.0, 0.0, 0.8);
      glBegin(GL_TRIANGLES);
      {
         glVertex3d(0,0,0);
         glVertex3d(-10,-10,0);
         glVertex3d(10,-10,0);
      }
      glEnd();

   }

};
//---------------------------------------------------------------------------
#endif
