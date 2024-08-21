// EJERCICIO 01

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

// EJERCICIO 02

#ifndef L12_ANIMATION_H
#define L12_ANIMATION_H
//---------------------------------------------------------------------------
// Programming 3D to Newbies : Indiana Aiki
//---------------------------------------------------------------------------
#include <GL/gl.h>
#include "Scene.h"
//---------------------------------------------------------------------------
class L12_Animation : public Scene
{
public:

   L12_Animation( )
   {
   
   }

   void draw( )
   {
      background();
      drawRotate();
   }

private:
     

   void background ( )
   { 
      glClearColor(0,0,0.2,1);
   }

   void drawRotate( )
   {
      double a;
      a=750*seconds();
      
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

// EJERCICIO 03

#ifndef L13_SQUARE_H
#define L13_SQUARE_H
//---------------------------------------------------------------------------
// Programming 3D to Newbies : Indiana Aiki
//---------------------------------------------------------------------------
#include <GL/gl.h>
#include "Scene.h"
//---------------------------------------------------------------------------
class L13_Square : public Scene
{
private:

   double a;
   
public:

   L13_Square( )
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
      drawRotate();
      
      a=90;
      drawRotate();
      
      a=180;
      drawRotate();
      
      a=270;
      drawRotate();
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
