import express from 'express';
import bodyParser from 'body-parser';
import { createConnection } from 'mysql2/promise';

const app = express();

app.use(bodyParser.json());

const port = 3000;

app.get('/', (req, res) => {
  res.send('API en nodejs - Desarrollado por Alex Christian!');
});

const configDB = {
  host: '127.0.0.1',
  port: 3306,
  user: 'root',
  password: '',
  database: 'mydb',
};

app.get('/alumnos', async (req, res) => {
  try {
    const cnx = await createConnection(configDB);
    const [rows] = await cnx.execute('SELECT a.*, c.car_nombre FROM alumno a INNER JOIN carrera c ON a.car_id = c.car_id');
    return res.json({ message: 'Todos los alumnos', data: rows });
  } catch (error) {
    return res.status(500).json({ error });
  }
});

app.post('/alumno/registrar', async (req, res) => {
  try {
  
    const { alu_codigo,alu_nombres,alu_apellidos, alu_edad, alu_genero, car_id } = req.body;
    const cnx = await createConnection(configDB);
    const [result] = await cnx.execute(
      'INSERT INTO alumno (alu_codigo, alu_nombres, alu_apellidos, alu_edad, alu_genero, car_id) VALUES (?, ?, ?, ?, ?, ?)',
      [alu_codigo, alu_nombres, alu_apellidos, alu_edad, alu_genero, car_id]
    );
    return res.json({ message: 'Alumno registrado', data: { alu_id: result.insertId, alu_codigo, alu_nombres, alu_apellidos, alu_edad, alu_genero, car_id } });
  } catch (error) {
    return res.status(500).json({ error });
  }
});

app.get('/carreras', async (req, res) => {
  try {
    const cnx = await createConnection(configDB);
    const [rows] = await cnx.execute('SELECT * FROM carrera');
    return res.json({ message: 'Todas las carreras', data: rows });
  } catch (error) {
    return res.status(500).json({ error });
  }
});

app.get('/varones', async (req, res) => {
  try {
    const cnx = await createConnection(configDB);
    const [rows] = await cnx.execute('SELECT * FROM alumno WHERE alu_genero = "M"');
    return res.json({ message: 'Solo alumnos masculinos', data: rows });
  } catch (error) {
    return res.status(500).json({ error });
  }
});

app.get('/mujeres', async (req, res) => {
  try {
    const cnx = await createConnection(configDB);
    const [rows] = await cnx.execute('SELECT * FROM alumno WHERE alu_genero = "F"');
    return res.json({ message: 'Solo alumnos femeninos', data: rows });
  } catch (error) {
    return res.status(500).json({ error });
  }
});

app.get('/mayores', async (req, res) => {
  try {
    const cnx = await createConnection(configDB);
    const [rows] = await cnx.execute('SELECT * FROM alumno WHERE alu_edad > 18');
    return res.json({ message: 'Solo alumnos mayores de edad', data: rows });
  } catch (error) {
    return res.status(500).json({ error });
  }
});

app.listen(port, () => {
  console.log(`Server is running at http://localhost:${port}`);
});
