#ifndef L14_WHILE_H
#define L14_WHILE_H
//---------------------------------------------------------------------------
// Programming 3D to Newbies : Indiana Aiki
//---------------------------------------------------------------------------
#include <GL/gl.h>
#include "Scene.h"
//---------------------------------------------------------------------------
class L14_While : public Scene
{
private:
   double a;
public:

   L14_While( )
   {   

   }

   void draw( )
   {
      background();
      drawSquare();
   }

private:    

   void background( )
   { 
      glClearColor(0,0,0.2,1);
   }   

   void drawSquare( )
   {
   a=0;
   while(a<360)
   {
   drawRotate();
   a+=90;
   }

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
   glColor3d(1.0,0,0.8);
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
