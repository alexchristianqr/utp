#ifndef L23_CAMERA_H
#define L23_CAMERA_H
//---------------------------------------------------------------------------
// Programming Juegos C++ : Videojuegos
//---------------------------------------------------------------------------
#include <GL/gl.h>
#include "Scene.h"
//---------------------------------------------------------------------------
class L23_Camera : public Scene
{
private:
      double aCam;
      double aCub;
   
public:
   L23_Camera( )
   {   
      
   }

   void draw( )
   {
      background();
      drawAnimation();
   }

   void click( )
   {
      aCam=aCam+2;
      if(aCam>60){
         aCam=60;
      }
   }

private:    

   void background( )
   { 
      glClearColor(0,0,0.2,1);
   }   

   void camera( )
   {
      glRotated(aCam,1,0,0);
   }    

   void drawAnimation( )
   {   
      double a;
      a=30*seconds();
      glPushMatrix();
         glRotated(a,0,1,0);
         drawRotate();
         
      glPopMatrix();
   }   

   void drawCube( )
   {   
      glLineWidth(2);
      glColor3d(0.6,0.6,1.0);
      
      aCub=0;
      while(aCub<360){
            drawRotate();
            aCub=aCub+90;
      }
   }

   void drawRotate( )
   {
      glPushMatrix();
         glRotated(aCub,0,1,0);
         drawSquare();
      glPopMatrix();
   }

   void drawSquare( )
   {
      double a;
      double b;
      double c;
      double x;
      double y;
      double z;
      
      double l;
      double m;
      double n;
      double o;
      
      glBegin(GL_TRIANGLES);
      {
         //Pecho
         x=0;y=3;z=0;a=10;b=16;c=3;
         glColor3d(1,1,0);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         
         x=0;y=3;z=0;a=12;b=14;c=3;
         glColor3d(1,1,0);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         
         x=0;y=3;z=0;a=14;b=12;c=3;
         glColor3d(1,1,0);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         
         l=8;
         //brazo izquierdo
         x=0+l;y=6;z=0;a=2;b=2;c=2;
         glColor3d(1,1,0);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         
         x=1.5+l;y=1;z=0;a=2;b=10;c=2;
         glColor3d(1,1,0);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         
         //Rostro
         x=3;y=7;z=1.5;a=3;b=4;c=1;
         glColor3d(0,0,0);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         
         x=-3;y=7;z=1.5;a=3;b=4;c=1;
         glColor3d(0,0,0);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         
         x=4;y=8;z=2;a=1;b=2;c=1;
         glColor3d(1,1,1);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         
         x=-2;y=8;z=2;a=1;b=2;c=1;
         glColor3d(1,1,1);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         
         
         x=0;y=4;z=1.5;a=5;b=3;c=5;
         glColor3d(1,1,0);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         
         x=0;y=4;z=1.5;a=1;b=4;c=5;
         glColor3d(1,1,0);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         
         x=0;y=4;z=1.5;a=1;b=4;c=5;
         glColor3d(1,1,0);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         
         x=0;y=4;z=3;a=1;b=1;c=4;
         glColor3d(0,0,0);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         
         m=-8;
         //brazo derecho
         x=0+m;y=6;z=0;a=2;b=2;c=2;
         glColor3d(1,1,0);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         
         x=-1.5+m;y=1;z=0;a=2;b=10;c=2;
         glColor3d(1,1,0);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         
         
         //Pierna derecha
         x=-3;y=-10;z=0;a=2;b=10;c=2;
         glColor3d(1,1,0);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         
         x=-3;y=-14;z=1;a=2;b=2;c=4;
         glColor3d(1,1,0);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         
         
         //Pierna Izquierda
         x=3;y=-10;z=0;a=2;b=10;c=2;
         glColor3d(1,1,0);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         
         x=3;y=-14;z=1;a=2;b=2;c=4;
         glColor3d(1,1,0);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         
         x=0;y=-2;z=-3;a=1;b=2;c=4;
         glColor3d(1,1,0);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x+(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z+(c)/2);
         glVertex3d(x-(a)/2,y-(b)/2,z-(c)/2);
         glVertex3d(x-(a)/2,y+(b)/2,z-(c)/2);
      }
      glEnd();
   }

};
//---------------------------------------------------------------------------
#endif
