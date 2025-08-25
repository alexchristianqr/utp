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

// EJERCICIO 02

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
      // drawSquare();
      drawPolygon(60);
      //drawPolygon(45);
   }

private:    

   void background( )
   { 
      glClearColor(0,0,0.2,1);
   }   
   
   drawPolygon(int grados)
   {
    a=0;
   while(a<360)
   {
   drawRotate();
   a+=grados;
   }
   }

   void drawSquare(int grados)
   {
   a=0;
   while(a<360)
   {
   drawRotate();
   a+=grados;
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
   glVertex3d(-5,-12,0);
   glVertex3d(5,-12,0);
   }
   glEnd();
   }

};
//---------------------------------------------------------------------------
#endif

// TAREA:

#ifndef L14_WHILE_H
#define L14_WHILE_H
//---------------------------------------------------------------------------
// Programming 3D to Newbies : Indiana Aiki
//---------------------------------------------------------------------------
#include <GL/gl.h>
#include "Scene.h"

#include "iostream"
#include "stdlib.h"

using namespace std;
//---------------------------------------------------------------------------
class L14_While : public Scene
{

   // Tamaño del tablero
int SIZE = 23;
float WIDTH = 25.0f; // Ancho de la ventana
float HEIGHT = 25.0f; // Altura de la ventana



private:
   double a;
   int tablero[2][2] = {
   {0,0},
   {0,0}
   };
   
   


public:

   L14_While( )
   {   

   }

   void draw( )
   {
      background();
      //drawBox();
       drawBoard();
      // imprimirTablero();
      // drawSquare(360);
      // drawPolygon(60);
      // drawPolygon(45);
   }

private: 



// Función para dibujar una celda
void drawCell(int row, int col) {
    float x_start = (col - SIZE / 2.0f) * (WIDTH / SIZE);
    float y_start = (SIZE / 2.0f - row) * (HEIGHT / SIZE);
    float cell_width = WIDTH / SIZE;
    float cell_height = HEIGHT / SIZE;
    
    // Colores para cada celda
float colors[SIZE][SIZE][3] = {
    {{1.0f, 0.0f, 0.0f}, {0.0f, 1.0f, 0.0f}, {0.0f, 0.0f, 1.0f}, {1.0f, 1.0f, 0.0f}},
    {{0.0f, 1.0f, 1.0f}, {1.0f, 0.0f, 1.0f}, {0.5f, 0.5f, 0.5f}, {0.0f, 0.5f, 0.5f}},
    {{0.5f, 0.0f, 0.5f}, {0.5f, 0.5f, 0.0f}, {0.0f, 0.5f, 0.5f}, {0.5f, 0.5f, 1.0f}},
    {{0.5f, 1.0f, 0.5f}, {1.0f, 0.5f, 0.5f}, {0.5f, 1.0f, 1.0f}, {1.0f, 1.0f, 0.5f}}
};

    glBegin(GL_QUADS);
        glColor3fv(colors[row][col]);
        glVertex2f(x_start, y_start);
        glVertex2f(x_start + cell_width, y_start);
        glVertex2f(x_start + cell_width, y_start - cell_height);
        glVertex2f(x_start, y_start - cell_height);
    glEnd();
}

// Función para dibujar el tablero
void drawBoard() {
    for (int row = 0; row < SIZE; ++row) {
        for (int col = 0; col < SIZE; ++col) {
            drawCell(row, col);
        }
    }
}

// Función para dibujar el tablero
void drawBoard22() {
    // Establecer el color de línea (blanco)
    glColor3f(1.0f, 1.0f, 1.0f);
    
    // Dibujar líneas horizontales
    for (int i = 1; i < SIZE; ++i) {
        float y = i * HEIGHT / SIZE - HEIGHT / 2;
        glBegin(GL_LINES);
        glVertex2f(-WIDTH / 2, y);
        glVertex2f(WIDTH / 2, y);
        glEnd();
    }

    // Dibujar líneas verticales
    for (int i = 1; i < SIZE; ++i) {
      glColor3d(1.0, 0, 0.8);
        float x = i * WIDTH / SIZE - WIDTH / 2;
        glBegin(GL_LINES);
        glVertex2f(x, HEIGHT / 2);
        glVertex2f(x, -HEIGHT / 2);
        glEnd();
    }
}

      int imprimirTablero()
      {
      //for(int i=1; i<4; i++)
      //{
      // drawSquare(i);
      
      //}
      
         int num2 = 12345; 
         printf("%d\n",num2); 
      }

   void background( )
   { 
      //glClearColor(0,0,0.2,1);
   }   
   
   drawPolygon(int grados)
   {
    a=0;
   while(a<360)
   {
   drawRotate();
   a+=grados;
   }
   }

   void drawSquare(int grados)
   {
   a=0;
   while(a<360)
   {
   drawRotate();
   a+=grados;
   }

   }

   void drawRotate( )
   {
   glPushMatrix();
   glRotated(a,0,0,1);
   drawTriangle();
   glPopMatrix();
   }
   
   void drawBox( )
   {  
   double l,s;
   l=16;
   s= l/2.0;
   
   glColor3d(1.0, 0, 0.8);
   glLineWidth(5);
   
   
   // glColor3d(1.0,0,1);
   glBegin(GL_LINES);
   glVertex3d(-s,s,0); glVertex3d(-s,-s,0);
   glVertex3d(-s,-s,0); glVertex3d(s,-s,0);
   glVertex3d(s,-s,0); glVertex3d(s,s,0);
   glVertex3d(s,s,0); glVertex3d(-s,s,0);
   glEnd();
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


